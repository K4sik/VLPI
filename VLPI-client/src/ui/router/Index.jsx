import React from "react";
import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";

import LoginHeader from "../components/header/LoginHeader";
import MainHeader from "../components/header/MainHeader";
import Login from "../pages/Login";
import Modules from "../pages/Modules";
import NotFound404 from "../pages/NotFound404";
import TypeDragDrop from "../pages/TypeDragDrop";
import Types from "../pages/Types";
import TypeTest from "../pages/TypeTest";
import TaskTest from "../pages/TaskTest";
import TaskDrag from "../pages/TaskDrag";
import Profile from "../pages/Profile";

export default function Index() {
  return (
    <div>
      <BrowserRouter>
        <Routes>
          <Route path="/login" element={<LoginHeader />} />
          <Route path="*" element={<MainHeader />} />
        </Routes>
        <Routes>
          <Route path="/" element={<Navigate to="/login" />} />
          <Route path="/login" element={<Login />} />
          <Route path="/modules" element={<Modules />} />
          <Route path="/profile" element={<Profile />} />
          <Route path="/modules/requirement" element={<Types />} />
          <Route path="/modules/requirement/test" element={<TypeTest />} />
          <Route
            path="/modules/requirement/dragdrop"
            element={<TypeDragDrop />}
          />
          <Route path="*" element={<NotFound404 />} />

          <Route path="/modules/requirement/test/:id" element={<TaskTest />} />
          <Route
            path="/modules/requirement/dragdrop/:id"
            element={<TaskDrag />}
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}
