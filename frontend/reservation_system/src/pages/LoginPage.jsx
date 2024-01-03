import React, { useState } from 'react'
import axios from "axios";
import LoginComponent from '../components/LoginComponent';


const LoginPage = () => {
    const [studentId, setStudentId] = useState("");
    const [password, setPassword] = useState("");

    const handleStudentIdChange = (e) => {
        console.log("학번 입력");
        const { value } = e.target;
        setStudentId(value);
    };

    const handlePasswordChange = (e) => {
        console.log("패스워드 입력");
        const { value } = e.target;
        setPassword(value);
    };

    const handleOnKeyPress = (e) => {
        if(e.key === "Enter") {
            handleLoginButtonClick();
        }
    }

    const handleLoginButtonClick = () => {
        // console.log("버튼 누름");

        const option = {
            method: "POST",
            url: "/api/member/login",
            data: {
                studentId: studentId,
                password: password,
            }
        };

        axios(option)
            .then((response) => {
                console.log(response);
                localStorage.setItem("isLogin", true);
                document.location.replace("/main");
            })
            .catch((error) => {
                alert("아이디가 존재하지 않거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                console.log(error);
            })

    }

    return (
        <div>
            <LoginComponent
                handleOnKey={handleOnKeyPress}
                handleStudentId={handleStudentIdChange}
                handlePassword={handlePasswordChange}
                handleLoginClick={handleLoginButtonClick}
            ></LoginComponent>
        </div>
    )
}

export default LoginPage