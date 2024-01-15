import { useEffect } from 'react';
import React from 'react'
import IsLogin from '../components/IsLogin';

const RootPage = () => {
    // useEffect(() => {
    //     if (IsLogin()) {
    //         document.location.replace("/main");
    //     }
    // }, []);

    const handleLoginButtonClick = () => {
        document.location.href = "/login";
    };

    const handleLogoutButtonClick = () => {
        if(confirm("로그아웃 하시겠습니까?")) {
            localStorage.setItem("isLogin", false);
            document.location.replace("/");
        }
    };

    if (IsLogin()) { // 로그인이 되어있는 경우의 rendering
        return (
            <div>
                <p>This is Main page. After Login.</p>
                <button type='button' onClick={handleLogoutButtonClick}>로그아웃</button>
            </div>
        )
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