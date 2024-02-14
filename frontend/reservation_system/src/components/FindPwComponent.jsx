import React from "react";

import Button from "./Button";

const Div = {
  display: "flex",
  justifyContent: "center",
  alignItems: "center",
  width: "100%",
  height: "100vh",
};

const Form = {
  display: "flex",
  flexDirection: "column",
  width: 200,
};

const FindPwComponent = ({
  handleOnKey,
  handleStudentId,
  handleEmail,
  handleFindPwClick,
}) => {
  return (
    <div style={Div}>
      <form style={Form} onKeyDown={handleOnKey}>
        <label>학번</label>
        <input
          type="number"
          onChange={handleStudentId}
          autoComplete="off"
          required></input>
        <label>이메일</label>
        <input
          type="email"
          onChange={handleEmail}
          autoComplete="off"
          pattern="[a-z0-9._%+-]+@+[a-z0-9._%+-]"
          title="이메일 형식에 맞게 입력해주세요"
          required></input>
        <Button onClickFtn={handleFindPwClick} text={"비밀번호 찾기"} />
      </form>
    </div>
  );
};

export default FindPwComponent;
