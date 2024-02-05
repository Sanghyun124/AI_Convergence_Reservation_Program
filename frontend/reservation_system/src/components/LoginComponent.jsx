import React from 'react'

const Div = {
    display: 'flex',
    justifyContent: 'center',
    alignItems: 'center',
    width: '100%',
    height: '100vh',
}

const Form = {
    display: 'flex',
    flexDirection: 'column',
    width: 200,
}

const Button1 = {
    width: 90,
    marginRight: 10,
}

const Button2 = {
    width: 90,
    marginLeft: 10,
}

const LoginComponent = ({ handleOnKey, handleStudentId, handlePassword, handleLoginClick, handleFindPwClick }) => {
    return (
        <div style={Div}>
            <form style={Form} onKeyDown={handleOnKey}>
                <label>학번</label>
                <input type='text' onChange={handleStudentId} autoComplete='off'></input>

                <label>비밀번호</label>
                <input type='password' onChange={handlePassword} autoComplete='off'></input>

                <br></br>

                <div style={{textAlign: 'center'}}>
                    <button type="button" style={Button1} onClick={handleLoginClick}>로그인</button>
                    <button type="button" style={Button2} onClick={handleFindPwClick}>비밀번호 찾기</button>
                </div>
            </form>
        </div>
    )
}

export default LoginComponent