import React from "react";
import { Link } from "react-router-dom";

export default function LoginHeader() {
  return (
    <header>
      <Link to="/modules" id="logo">
        VLPI
      </Link>
      <nav>
        <Link to="/modules">Practice</Link>
        <Link to="/profile">Statistics</Link>
        <Link to="/login">Login</Link>
      </nav>
    </header>
  );
}
