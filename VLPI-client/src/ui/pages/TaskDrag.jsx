import React, { useEffect, useState } from "react";
import { useParams } from "react-router";
import Board from "../components/Board";
import Card from "../components/Card";
import { useNavigate } from "react-router-dom";

export default function TaskDrag() {
  const { id } = useParams();
  const [dragTaskData, setDragTaskData] = useState({
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
        requirements: [{ id: 1, text: "Variant", correct: false }],
      },
    ],
  });

  function display(seconds) {
    const format = (val) => `0${Math.floor(val)}`.slice(-2);
    const minutes = (seconds % 3600) / 60;
    return [minutes, seconds % 60].map(format).join(":");
  }

  const [seconds, setSeconds] = useState(dragTaskData.time);
  const [stop, setStop] = useState(false);

  useEffect(() => {
    if (!stop && seconds > 0) {
      setTimeout(() => setSeconds(seconds - 1), 1000);
    }
  });

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
        setDragTaskData(data);
      });
  }, [id]);

  let navigate = useNavigate();

  async function sendDrag(e) {
    e.preventDefault();

    const taskTime = dragTaskData.time - seconds;
    setStop(true);
    document.getElementById("secondsTime").classList.add("timer-done");

    let dragAnwers = [];

    const Drugs = document.getElementById("board-2");
    for (let i = 0; i < Drugs.children.length; i++) {
      dragAnwers.push({ id: Drugs.children[i].id });
    }
    console.log(dragAnwers);

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
            id: 4,
            requirements: dragAnwers,
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
      navigate("../modules/requirement/dragdrop");
    }
  }

  console.log(dragTaskData);

  return (
    <div className="task-quiz-block">
      <div className="quiz-block">
        <form onSubmit={sendDrag}>
          <div className="quiz-header">
            <p>Drag&Drop</p>
            <p>Created by @{dragTaskData.authorName}</p>
          </div>
          <hr />
          <div className="quiz-info">
            <p>Title - {dragTaskData.name}</p>
            <p>Description - {dragTaskData.description}</p>
            <p>
              Timer -{" "}
              {seconds > 0 ? (
                <span id="secondsTime">{display(seconds)}</span>
              ) : (
                <span className="timer-out">Out of Time! Try again.</span>
              )}
            </p>
          </div>

          <div className="quiz-question">
            <p>{dragTaskData.details[0].wording}</p>

            <div className="dragAll">
              <Board id="board-1" className="dragLeft">
                {dragTaskData.details[0].requirements.map((req) => (
                  <Card
                    id={req.id}
                    className="dragBlock"
                    draggable="true"
                    text={req.text}
                  />
                ))}
              </Board>

              <Board id="board-2" className="dragRight"></Board>
            </div>
          </div>

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
