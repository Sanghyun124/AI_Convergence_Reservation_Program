import React, { useState } from 'react'
import axios from "axios";
import InputComponent from '../components/InputComponent';


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
                alert("로그인 성공");
                console.log(response);
                document.location.replace("/");
            })
            .catch((error) => {
                alert("아이디가 존재하지 않거나, 아이디 또는 비밀번호를 잘못 입력하셨습니다.");
                console.log(error);
            })

    }

    return (
        <div>
            <InputComponent
                handleOnKey={handleOnKeyPress}
                handleStudentId={handleStudentIdChange}
                handlePassword={handlePasswordChange}
                handleClick={handleLoginButtonClick}
            ></InputComponent>
        </div>
    )
}

export default LoginPage