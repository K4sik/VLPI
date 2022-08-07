import React, { useState, useEffect } from "react";
import ProfilePicture from "../../public/images/profileTemplate.png";

export default function Profile() {
  const [userData, setUserData] = useState({
    averageGrade: 0,
    email: "",
    fullName: "",
    id: 1,
    tasks: [
      {
        date: [2021, 12, 6, 2, 12, 15, 876831000],
        name: "",
        type: "",
        difficulty: "",
        grade: 0,
        time: 60,
        id: 1,
      },
    ],
  });

  function display(seconds) {
    const format = (val) => `0${Math.floor(val)}`.slice(-2);
    const minutes = (seconds % 3600) / 60;
    return [minutes, seconds % 60].map(format).join(":");
  }

  useEffect(() => {
    const dataRequest = {
      method: "GET",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("Authorization"),
      },
      credentials: "include",
    };

    fetch("http://localhost:8080/users/statistic", dataRequest)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setUserData(data);
      });
  }, []);

  let avrGrade;

  if (userData.averageGrade > 90) {
    avrGrade = <div className="border-blue">{userData.averageGrade}</div>;
  } else if (userData.averageGrade > 75) {
    avrGrade = <div className="border-green">{userData.averageGrade}</div>;
  } else if (userData.averageGrade > 50) {
    avrGrade = <div className="border-yellow">{userData.averageGrade}</div>;
  } else if (userData.averageGrade > 25) {
    avrGrade = <div className="border-orange">{userData.averageGrade}</div>;
  } else if (userData.averageGrade > 0) {
    avrGrade = <div className="border-red">{userData.averageGrade}</div>;
  } else {
    avrGrade = <div>{userData.averageGrade}</div>;
  }

  return (
    <div className="wrapper-profile">
      <div className="profile-info">
        <div className="info-main">
          <div>
            <img src={ProfilePicture} alt="Profile" />
          </div>
          <p>{userData.fullName}</p>
        </div>
        <div className="info-add">
          <div className="add-first">
            <p>
              User ID - <span>#{userData.id}</span>
            </p>
            <p>Name - {userData.fullName}</p>
            {userData.userRole === "ROLE_STUDENT" ? (
              <p>
                Role - <span>Student</span>
              </p>
            ) : (
              <span>Administrator</span>
            )}
            <p>Email - {userData.email}</p>
          </div>
          <div className="add-second">
            {avrGrade}
            <p>Average Grade</p>
          </div>
        </div>
      </div>
      <div className="profile-stats">
        <p>Statistics</p>
        <table>
          <thead>
            <tr>
              <th>ID</th>
              <th>Name</th>
              <th>Type</th>
              <th>Difficulty</th>
              <th>Date</th>
              <th>Time</th>
              <th>Grade</th>
            </tr>
          </thead>
          <tbody>
            {userData.tasks.map((task) => (
              <tr>
                <td>{task.id}</td>
                <td>{task.name}</td>
                <td>{task.type}</td>
                <td>{task.difficulty}</td>
                <td>
                  {task.date[2]}/{task.date[1]}/{task.date[0]} - {task.date[3]}:
                  {task.date[4]}
                </td>
                <td>{display(task.time)}</td>
                <td>{task.grade}</td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
}
