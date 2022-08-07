import React, { useEffect, useState } from "react";
import Task from "../components/Task";

export default function TypeDragDrop() {
  const [dragTasks, setDragTasks] = useState([]);

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

    fetch("http://localhost:8080/tasks/type/2", dataRequest)
      .then((res) => {
        return res.json();
      })
      .then((data) => {
        console.log(data);
        setDragTasks(data);
      });
  }, []);

  return (
    <div className="task-block">
      <div className="task-list">
        {dragTasks.map((task, i) => (
          <Task data={task} key={task.id} index={i} />
        ))}
      </div>
    </div>
  );
}
