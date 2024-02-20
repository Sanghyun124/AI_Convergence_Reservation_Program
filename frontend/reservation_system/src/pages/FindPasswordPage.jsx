import React from "react";
import { useState } from "react";
import axios from "axios";
import FindPwComponent from "../components/FindPwComponent";
import Header from "../components/Header";

const FindPasswordPage = () => {
  const [studentId, setStudentId] = useState("");
  const [email, setEmail] = useState("");

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      handleFindPwButtonClick();
    }
  };

  const handleStudentIdChange = (e) => {
    const { value } = e.target;
    setStudentId(value);
  };

  const handleEmailChange = (e) => {
    const { value } = e.target;
    setEmail(value);
  };

  const handleFindPwButtonClick = () => {
    const option = {
      method: "POST",
      url: "/api/member/password",
      data: {
        studentId: studentId,
        email: email,
      },
    };

    axios(option)
      .then((response) => {
        alert("임시 비밀번호가 등록된 이메일로 발송되었습니다.");
        document.location.href = "/";
      })
      .catch((error) => {
        alert(
          "아이디가 존재하지 않거나, 아이디 또는 이메일을 잘못 입력하셨습니다."
        );
      });
  };

  return (
    <div id="main">
      <Header page={"비밀번호 찾기"} />
      <FindPwComponent
        handleOnKey={handleOnKeyPress}
        handleStudentId={handleStudentIdChange}
        handleEmail={handleEmailChange}
        handleFindPwClick={handleFindPwButtonClick}></FindPwComponent>
    </div>
  );
};

export default FindPasswordPage;
