import React from "react";
import "../styles/basic_components.scss";

function Button({ onClickFtn, text }) {
  return (
    <button type="button" id="normal-button" onClick={onClickFtn}>
      <p>{text}</p>
    </button>
  );
}

export default Button;
