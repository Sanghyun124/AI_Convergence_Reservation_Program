import React from "react";
import "../styles/basic_components.scss";

function NormalInput({ label, type, onChangeFtn }) {
  if (label != "email") {
    return (
      <div id="input-container">
        <label id="input-label">{label}</label>
        <input
          id="normal-input"
          type={type}
          onChange={onChangeFtn}
          autoComplete="off"
          required></input>
      </div>
    );
  } else {
    return (
      <div id="input-container">
        <label id="input-label">{label}</label>
        <input
          id="normal-input"
          type={type}
          onChange={onChangeFtn}
          pattern="[a-z0-9._%+-]+@+[a-z0-9._%+-]"
          title="이메일 형식에 맞게 입력해주세요"
          autoComplete="off"
          required></input>
      </div>
    );
  }
}

export default NormalInput;
