import React, { createRef } from "react";
import iconUser from "../../public/icons/user.svg";
import iconLock from "../../public/icons/lock.svg";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function Login() {
  let navigate = useNavigate();

  let emailInput = createRef();
  let passInput = createRef();

  async function loginUser(e) {
    e.preventDefault();

    console.log("Email Input - " + emailInput.current.value);
    console.log("Password Input - " + passInput.current.value);

    const dataRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        email: emailInput.current.value,
        password: passInput.current.value,
      }),
      credentials: "include",
    };

    const res = await fetch("http://localhost:8080/auth/signin", dataRequest);

    if (res.ok) {
      const reg = await res.json();
      console.log(reg.token);

      if (reg.token === null) {
        alert("Error! Wrong Authorization Data");
      } else {
        localStorage.setItem("Authorization", reg.token);
        navigate("../modules");
      }
    } else {
      alert("Error with Authorization");
    }
  }

  return (
    <div className="login">
      <div className="login-block">
        <div className="login-logo">
          <p>VLPI</p>
        </div>
        <form onSubmit={loginUser}>
          <div className="input-block">
            <label htmlFor="email">Email</label>
            <div>
              <img src={iconUser} alt="User" />
              <input
                ref={emailInput}
                className="login-inputs"
                type="email"
                id="email"
                placeholder="Type your email"
                required
              />
            </div>
            <hr />
          </div>

          <div className="input-block">
            <label htmlFor="pass">Password</label>
            <div>
              <img src={iconLock} alt="Lock" />
              <input
                ref={passInput}
                className="login-inputs"
                type="password"
                id="pass"
                placeholder="Type your password"
                required
              />
            </div>
            <hr />
          </div>

          <Link id="for-pass" to="/contact">
            Forgot password?
          </Link>

          <button className="login-button" type="submit">
            LOGIN
          </button>
        </form>
      </div>
    </div>
  );
}
