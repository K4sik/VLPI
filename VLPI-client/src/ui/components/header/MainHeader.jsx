import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";

import profileTemp from "../../../public/images/profileTemplate.png";

export default function MainHeader() {
  const [userFullname, setUserFullname] = useState("");

  useEffect(() => {
    let controller = new AbortController();

    const token = localStorage.getItem("Authorization");
    const dataRequest = {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: `Bearer ${token}`,
      },
      credentials: "include",
      signal: controller.signal,
    };

    const req = async () =>
      await fetch("http://localhost:8080/users/token", dataRequest).then(
        (response) => response.json()
      );
    req().then((data) => setUserFullname(data.fullName));

    return () => controller?.abort();
  }, []);

  return (
    <div>
      <header>
        <Link to="/modules" id="logo">
          VLPI
        </Link>
        <nav>
          <Link to="/modules">Practice</Link>
          <Link to="/profile">Statistics</Link>
          <Link to="/profile">
            <div className="profile">
              <div className="profile-img">
                <img src={profileTemp} alt="Profile" />
              </div>
              <p>{userFullname}</p>
            </div>
          </Link>
        </nav>
      </header>
    </div>
  );
}
