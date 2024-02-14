import React from "react";

import "../styles/login.scss";
import Button from "./Button";

const LoginComponent = ({
  handleOnKey,
  handleStudentId,
  handlePassword,
  handleLoginClick,
  handleFindPwClick,
}) => {
  return (
    <div className="container">
      <form onKeyDown={handleOnKey}>
        <div className="inputContainer">
          <label>학번</label>
          <input
            type="number"
            onChange={handleStudentId}
            autoComplete="off"
            required></input>
        </div>
        <div className="inputContainer">
          <label>비밀번호</label>
          <input
            type="password"
            onChange={handlePassword}
            autoComplete="off"
            required></input>
        </div>

        <div className="buttonContainer">
          <Button onClickFtn={handleLoginClick} text={"로그인"} />
          <Button onClickFtn={handleFindPwClick} text={"비밀번호 찾기"} />
        </div>
      </form>
    </div>
  );
};

export default LoginComponent;
