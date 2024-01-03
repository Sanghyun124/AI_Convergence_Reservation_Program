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

const InputComponent = ({ handleOnKey, handleStudentId, handlePassword, handleLoginClick }) => {
    return (
        <div style={Div}>
            <form style={Form} onKeyDown={handleOnKey}>
                <label>학번</label>
                <input type='text' onChange={handleStudentId} autoComplete='off'></input>

                <label>비밀번호</label>
                <input type='password' onChange={handlePassword} autoComplete='off'></input>

                <br></br>

                <div style={{textAlign: 'center'}}>
                    <button type="button" onClick={handleLoginClick}>로그인</button>
                </div>
            </form>
        </div>
    )
}

export default InputComponent