import React, { useEffect, useState } from 'react'
import { useRecoilState } from "recoil";
import { userInfoState } from '../utils/recoilState';
import secureLocalStorage from 'react-secure-storage';
import axios from 'axios';

const RootPage = () => {
    const [isLogin, setIsLogin] = useRecoilState(userInfoState);

    const id = secureLocalStorage.getItem("id");
    const role = secureLocalStorage.getItem("role");
    const token = secureLocalStorage.getItem("token");
    const name = secureLocalStorage.getItem("name");

    useEffect(() => {
        if (id !== null) {
            const option = {
                method: "GET",
                url: `/api/member/${id}`,
                headers: {
                    Authorization: `Bearer ${token}`,
                }
            };

            axios(option)
                .then(({ data }) => {
                    console.log(data.userName);
                    setUserName(data.userName);
                    secureLocalStorage.setItem("name", data.userName);
                })
                .catch((error) => {
                    console.log(error);
                    if (error.code === "ERR_BAD_REQUEST") {
                        alert("세션이 만료되었습니다. 다시 로그인 해주세요");
                        
                        setIsLogin(false);
                        secureLocalStorage.clear();

                        document.location.replace("/");
                    } else {
                        alert("사용자 정보를 불러올 수 없습니다.");
                        console.log(error);
                    }
                })
        }
    }, []);

    const handleLoginButtonClick = () => {
        document.location.href = "/login";
    };

    const handleLogoutButtonClick = () => {
        if(confirm("로그아웃 하시겠습니까?")) {
            setIsLogin(false);
            secureLocalStorage.clear();
            document.location.replace("/");
        }
    };

    const handleAdminButtonClick = () => {
        document.location.href = "/admin";
    }
    

    if (isLogin) { // 로그인이 되어있는 경우의 rendering
        if (role === "ROLE_ADMIN")  { // 관리자 권한인 경우의 rendering
            return (
                <div>
                    <p>Hello {name}. Your Role is {role}. This is Main page. After Login.</p>
                    <button type='button' onClick={handleLogoutButtonClick}>로그아웃</button>
                    <button type='button' onClick={handleAdminButtonClick}>관리자 페이지</button>
                </div>
            )
        } else { // 관리자 권한이 아닌 경우의 rendering
            return (
                <div>
                    <p>Hello {name}. Your Role is {role}. This is Main page. After Login.</p>
                    <button type='button' onClick={handleLogoutButtonClick}>로그아웃</button>
                </div>
            )
        }
    } else { // 로그인이 되어있지 않은 경우의 rendering
        return (
            <div>
                <p>This is Root page. Before Login.</p>
                <button type="button" onClick={handleLoginButtonClick}>로그인</button>
            </div>
        )
    }
}

export default RootPage