import React from "react";
import "../styles/header.scss";
import "../styles/basic_components.scss";
import logo from "../images/ailogo.png";

function Header({ page }) {
  return (
    <div id="header">
      <a href="/">
        <img src={logo} alt="AI융합학부 로고" />
      </a>
      {page ? (
        <p className="title" id="header-title">
          세미나실 예약 시스템 - {page}
        </p>
      ) : (
        <p className="title" id="header-title">
          세미나실 예약 시스템
        </p>
      )}
    </div>
  );
}

export default Header;
