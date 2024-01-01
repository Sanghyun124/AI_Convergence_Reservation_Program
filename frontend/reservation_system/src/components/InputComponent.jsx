import React from 'react'

const InputComponent = ({ handleOnKey, handleStudentId, handlePassword, handleClick }) => {
    return (
        <div style={{display: 'flex', justifyContent: 'center', alignItems: 'center', width: '100%', height: '100vh'}}>
            <form style={{display: 'flex', flexDirection: 'column'}} onKeyDown={handleOnKey}>
                <label>학번</label>
                <input type='text' onChange={handleStudentId} autoComplete='off'></input>

                <label>비밀번호</label>
                <input type='password' onChange={handlePassword} autoComplete='off'></input>

                <br></br>

                <button type="button" onClick={handleClick}>로그인</button>
            </form>
        </div>
    )
}

export default InputComponent