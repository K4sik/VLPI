import React from "react";

import { Link } from "react-router-dom";
import taskTrue from "../../public/icons/task_true.png";
import taskFalse from "../../public/icons/task_false.png";

export default function Task(props) {
  console.log(props);

  return (
    <Link className="task-links" to={"" + props.data.id}>
      <div className="task">
        <div className="task-num">
          <p>{props.index + 1}</p>
        </div>
        <div className="task-text">
          <p>{props.data.name}</p>
          <p>
            {props.data.description}{" "}
            <span> | Created by @{props.data.authorName}</span>
          </p>
        </div>
        <div className="task-type">
          {window.location.pathname === "/modules/requirement/test" ? (
            <div>Testing</div>
          ) : (
            <div>Drag&Drop</div>
          )}
        </div>
        <div className="task-diff">
          {props.data.difficulty === "Easy" ? (
            <div>Easy</div>
          ) : props.data.difficulty === "Medium" ? (
            <div>Medium</div>
          ) : (
            <div>Hard</div>
          )}
        </div>
        <div className="task-done">
          {props.data.doneBefore ? (
            <img src={taskTrue} alt="Status" />
          ) : (
            <img src={taskFalse} alt="Status" />
          )}
        </div>
      </div>
    </Link>
  );
}
