import React from 'react'
import { useEffect } from 'react';
import IsLogin from '../components/IsLogin';

const AfterLogin = () => {
    useEffect(() => {
        if(!IsLogin()) {
            document.location.replace("/");
        }
    }, []);

    const handleLogoutButton = () => {
        if(confirm("로그아웃 하시겠습니까?")) {
            localStorage.setItem("isLogin", false);
            document.location.replace("/");
        }
    };

    return (
        <div>
            <p>This is Main page. After Login.</p>
            <button type='button' onClick={handleLogoutButton}>로그아웃</button>
        </div>
    )
}

export default AfterLogin