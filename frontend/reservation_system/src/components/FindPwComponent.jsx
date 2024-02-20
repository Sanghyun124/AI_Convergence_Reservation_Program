import React from "react";

import Button from "./Button";
import NormalInput from "./NormalInput";

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
        <NormalInput
          label={"학번"}
          type={"number"}
          onChangeFtn={handleStudentId}
        />
        <NormalInput
          label={"이메일"}
          type={"email"}
          onChangeFtn={handleEmail}
        />
        <Button onClickFtn={handleFindPwClick} text={"비밀번호 찾기"} />
      </form>
    </div>
  );
};

export default FindPwComponent;
