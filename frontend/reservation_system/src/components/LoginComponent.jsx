import React from "react";

import "../styles/login.scss";
import Button from "./Button";
import NormalInput from "./NormalInput";

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
          <NormalInput
            label={"학번"}
            type={"number"}
            onChangeFtn={handleStudentId}
          />
        </div>
        <div className="inputContainer">
          <NormalInput
            label={"비밀번호"}
            type={"password"}
            onChangeFtn={handlePassword}
          />
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
