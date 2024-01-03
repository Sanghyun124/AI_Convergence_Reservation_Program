import { useEffect } from 'react';
import React from 'react'
import IsLogin from '../components/IsLogin';

const Home = () => {
    useEffect(() => {
        if(IsLogin()) {
            document.location.replace("/main");
        }
    }, []);

    const handleButtonClick = () => {
        document.location.href = "/login";
    };

    return (
        <div>
            <p>This is Root page. Before Login.</p>
            <button type="button" onClick={handleButtonClick}>로그인</button>
        </div>
    )
}

export default Home