import React, { useState } from "react";
import axios from "axios";
import LoginComponent from "../components/LoginComponent";
import { useRecoilState } from "recoil";
import { userInfoState } from "../utils/recoilState";
import secureLocalStorage from "react-secure-storage";
import "../index.scss";

import Header from "../components/Header";
import Footer from "../components/Footer";

const LoginPage = () => {
  const [, setIsLogin] = useRecoilState(userInfoState);

  const [studentId, setStudentId] = useState("");
  const [password, setPassword] = useState("");

  const handleOnKeyPress = (e) => {
    if (e.key === "Enter") {
      handleLoginButtonClick();
    }
  };

  const handleStudentIdChange = (e) => {
    const { value } = e.target;
    setStudentId(value);
  };

  const handlePasswordChange = (e) => {
    const { value } = e.target;
    setPassword(value);
  };

  const handleLoginButtonClick = () => {
    // console.log("버튼 누름");

    const option = {
      method: "POST",
      url: "/api/member/login",
      data: {
        studentId: studentId,
        password: password,
      },
    };

    axios(option)
      .then(({ data }) => {
        console.log(data);
        setIsLogin(true);

        secureLocalStorage.setItem("id", data.id);
        secureLocalStorage.setItem("role", data.role);
        secureLocalStorage.setItem("token", data.token);

        document.location.replace("/");
      })
      .catch((error) => {
        alert(
          "아이디가 존재하지 않거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다."
        );
        console.log(error);
      });
  };

  const handleFindPwButtonClick = () => {
    document.location.href = "/findpw";
  };

  return (
    <div id="main">
      <Header page={"로그인"} />
      <LoginComponent
        handleOnKey={handleOnKeyPress}
        handleStudentId={handleStudentIdChange}
        handlePassword={handlePasswordChange}
        handleLoginClick={handleLoginButtonClick}
        handleFindPwClick={handleFindPwButtonClick}></LoginComponent>
      <Footer />
    </div>
  );
};

export default LoginPage;
