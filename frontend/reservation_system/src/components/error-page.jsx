import React from "react";
import { useRouteError } from "react-router-dom";

export default function ErrorPage() {
  const error = useRouteError();
  console.error(error);

  return (
    <div id="error-page">
      <h1>페이지를 찾을 수 없습니다.</h1>
      <p>**로 연락하세요.</p>
    </div>
  );
}
