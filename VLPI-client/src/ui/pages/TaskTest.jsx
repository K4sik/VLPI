import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import { useNavigate } from "react-router-dom";

export default function TaskTest() {
  const { id } = useParams();
  const [testTaskData, setTestTaskData] = useState({
    authorEmail: "admin@admin.com",
    authorName: "Admin",
    description: "Creative description",
    difficulty: "Easy",
    id: 1,
    name: "Creative title",
    time: 300,
    details: [
      {
        id: 0,
        wording: "Question",
        requirements: [{ id: 1, text: "Variant" }],
      },
    ],
  });

  let navigate = useNavigate();

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

    fetch("http://localhost:8080/tasks/" + id, dataRequest)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setTestTaskData(data);
      });
  }, [id]);

  const [seconds, setSeconds] = useState(testTaskData.time);
  const [stop, setStop] = useState(false);

  useEffect(() => {
    if (!stop && seconds > 0) {
      setTimeout(() => setSeconds(seconds - 1), 1000);
    } else {
      document.getElementById("3").disabled = "true";
      document.getElementById("5").disabled = "true";
      document.getElementById("6").disabled = "true";
      document.getElementById("4").disabled = "true";
      document.getElementById("7").disabled = "true";
      document.getElementById("8").disabled = "true";
      document.getElementById("9").disabled = "true";
      document.getElementById("1").disabled = "true";
      document.getElementById("10").disabled = "true";
    }
  });

  function display(seconds) {
    const format = (val) => `0${Math.floor(val)}`.slice(-2);
    const minutes = (seconds % 3600) / 60;
    return [minutes, seconds % 60].map(format).join(":");
  }

  async function sendQuiz(e) {
    e.preventDefault();

    document.getElementById("3").disabled = "true";
    document.getElementById("5").disabled = "true";
    document.getElementById("6").disabled = "true";
    document.getElementById("4").disabled = "true";
    document.getElementById("7").disabled = "true";
    document.getElementById("8").disabled = "true";
    document.getElementById("9").disabled = "true";
    document.getElementById("1").disabled = "true";
    document.getElementById("10").disabled = "true";

    let test1, test2, test3;

    if (document.getElementById("3").checked) {
      test1 = 3;
    } else if (document.getElementById("5").checked) {
      test1 = 5;
    } else if (document.getElementById("6").checked) {
      test1 = 6;
    }

    if (document.getElementById("4").checked) {
      test2 = 4;
    } else if (document.getElementById("7").checked) {
      test2 = 7;
    } else if (document.getElementById("8").checked) {
      test2 = 8;
    }

    if (document.getElementById("9").checked) {
      test3 = 9;
    } else if (document.getElementById("1").checked) {
      test3 = 1;
    } else if (document.getElementById("10").checked) {
      test3 = 10;
    }

    // console.log("First Question - " + test1);
    // console.log("Second Question - " + test2);
    // console.log("Third Question - " + test3);

    const taskTime = testTaskData.time - seconds;
    setStop(true);

    document.getElementById("secondsTime").classList.add("timer-done");

    const dataRequest = {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
        Authorization: "Bearer " + localStorage.getItem("Authorization"),
      },
      body: JSON.stringify({
        id: id,
        time: taskTime,
        details: [
          {
            id: 1,
            requirements: [
              {
                id: test1,
              },
            ],
          },
          {
            id: 2,
            requirements: [
              {
                id: test2,
              },
            ],
          },
          {
            id: 3,
            requirements: [
              {
                id: test3,
              },
            ],
          },
        ],
      }),
      credentials: "include",
    };

    const res = await fetch(
      "http://localhost:8080/tasks/evaluation",
      dataRequest
    );
    const reg = await res.json();
    console.log(reg.grade);

    // eslint-disable-next-line no-restricted-globals
    const result = confirm(
      "Congratulations!\nYou completed task №" +
        id +
        " and scored " +
        reg.grade +
        " points.\n\nWant to return to the task selection page?"
    );

    if (result) {
      navigate("../modules/requirement/test");
    }
  }

  return (
    <div className="task-quiz-block">
      <div className="quiz-block">
        <form onSubmit={sendQuiz}>
          <div className="quiz-header">
            <p>Quiz</p>
            <p>Created by @{testTaskData.authorName}</p>
          </div>
          <hr />
          <div className="quiz-info">
            <p>Title - {testTaskData.name}</p>
            <p>Description - {testTaskData.description}</p>
            <p>
              Timer&nbsp;-{" "}
              {seconds > 0 ? (
                <span id="secondsTime">{display(seconds)}</span>
              ) : (
                <span className="timer-out">Out of Time! Try again.</span>
              )}
            </p>
          </div>

          {testTaskData.details.map((task) => (
            <div className="quiz-question">
              <p>
                {task.id}. {task.wording}
              </p>

              {task.requirements.map((req) => (
                <div>
                  <input
                    name={task.id}
                    type="radio"
                    value="lol"
                    id={req.id}
                    required
                  />
                  <label htmlFor={req.id}>{req.text}</label>
                </div>
              ))}
            </div>
          ))}

          <div className="quiz-footer">
            <button className="quiz-button" type="submit">
              FINISH
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}
