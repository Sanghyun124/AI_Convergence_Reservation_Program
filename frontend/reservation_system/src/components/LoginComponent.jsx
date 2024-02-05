import React from "react";

import "../styles/Login.scss";

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
            type="text"
            onChange={handleStudentId}
            autoComplete="off"></input>
        </div>
        <div className="inputContainer">
          <label>비밀번호</label>
          <input
            type="password"
            onChange={handlePassword}
            autoComplete="off"></input>
        </div>

        <div className="buttonContainer">
          <button type="button" className="button" onClick={handleLoginClick}>
            로그인
          </button>
          <button type="button" className="button" onClick={handleFindPwClick}>
            비밀번호 찾기
          </button>
        </div>
      </form>
    </div>
  );
};

export default LoginComponent;
