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

const FindPwComponent = ({ handleOnKey, handleStudentId, handleEmail, handleFindPwClick }) => {
    return (
        <div style={Div}>
            <form style={Form} onKeyDown={handleOnKey}>
                <label>학번</label>
                <input type='text' onChange={handleStudentId} autoComplete='off'></input>

                <label>이메일</label>
                <input type='text' onChange={handleEmail} autoComplete='off'></input>

                <br></br>
                
                <button type="button" onClick={handleFindPwClick}>비밀번호 찾기</button>
            </form>
        </div>
    )
}

export default FindPwComponent