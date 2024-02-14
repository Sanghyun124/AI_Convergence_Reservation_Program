import React from "react";
import "../styles/header.scss";
import logo from "../images/ailogo.png";

function Header() {
  return (
    <div id="header">
      <img src={logo} alt="AI융합학부 로고" />
      <p className="title">세미나실 예약 시스템</p>
    </div>
  );
}

export default Header;
