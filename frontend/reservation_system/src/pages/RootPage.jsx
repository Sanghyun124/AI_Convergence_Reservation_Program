import React, { useEffect } from "react";
import { useRecoilState } from "recoil";
import { userInfoState } from "../utils/recoilState";
import secureLocalStorage from "react-secure-storage";
import axios from "axios";

import "../styles/basic_components.scss";
import Header from "../components/Header";
import Footer from "../components/Footer";
import Button from "../components/Button";

const RootPage = () => {
  const [isLogin, setIsLogin] = useRecoilState(userInfoState);

  let id = secureLocalStorage.getItem("id");
  let role = secureLocalStorage.getItem("role");
  let token = secureLocalStorage.getItem("token");
  let name = secureLocalStorage.getItem("name");

  useEffect(() => {
    if (id !== null) {
      const option = {
        method: "GET",
        url: `/api/member/${id}`,
        headers: {
          Authorization: `Bearer ${token}`,
        },
      };

      axios(option)
        .then(({ data }) => {
          console.log(data.userName);
          secureLocalStorage.setItem("name", data.userName);
        })
        .catch((error) => {
          console.log(error);
          if (error.code === "ERR_BAD_REQUEST") {
            alert("세션이 만료되었습니다. 다시 로그인 해주세요");

            setIsLogin(false);
            secureLocalStorage.clear();

            document.location.replace("/");
          } else {
            alert("사용자 정보를 불러올 수 없습니다.");
            console.log(error);
          }
        });
    }
  }, []);

  const handleLoginButtonClick = () => {
    document.location.href = "/login";
  };

  const handleLogoutButtonClick = () => {
    if (confirm("로그아웃 하시겠습니까?")) {
      setIsLogin(false);
      secureLocalStorage.clear();
      document.location.replace("/");
    }
  };

  const handleAdminButtonClick = () => {
    document.location.href = "/admin";
  };

  return (
    <>
      <Header />
      {isLogin ? (
        role === "ROLE_ADMIN" ? (
          <div id="main">
            <Button onClickFtn={handleLogoutButtonClick} text={"로그아웃"} />
            <Button
              onClickFtn={handleAdminButtonClick}
              text={"관리자 페이지"}
            />
          </div>
        ) : (
          <div id="main">
            <Button onClickFtn={handleLogoutButtonClick} text={"로그아웃"} />
          </div>
        )
      ) : (
        <div id="main">
          <p className="title">
            AI융합학부 세미나실 예약 시스템에 오신 것을 환영합니다.
          </p>
          <div
            id="reservation-status"
            style={{
              display: "flex",
              flexDirection: "row",
              justifyContent: "space-evenly",
              width: "100%",
            }}>
            <div
              style={{
                border: "1px solid black",
                width: "100px",
                height: "100px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
              }}>
              <h4>세미나실 A</h4>
              <p>사용중(~15시)</p>
            </div>
            <div
              style={{
                border: "1px solid black",
                width: "100px",
                height: "100px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
              }}>
              <h4>세미나실 B</h4>
              <p>사용가능</p>
            </div>
            <div
              style={{
                border: "1px solid black",
                width: "100px",
                height: "100px",
                display: "flex",
                flexDirection: "column",
                alignItems: "center",
              }}>
              <h4>세미나실 C</h4>
              <p>사용중(~14시)</p>
            </div>
          </div>
          <Button onClickFtn={handleLoginButtonClick} text={"로그인"} />
        </div>
      )}
      <Footer />
    </>
  );
};

export default RootPage;
