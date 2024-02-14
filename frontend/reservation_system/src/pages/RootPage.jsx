import React, { useEffect } from "react";
import { useRecoilState } from "recoil";
import { userInfoState } from "../utils/recoilState";
import secureLocalStorage from "react-secure-storage";
import axios from "axios";
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

  if (isLogin) {
    // 로그인이 되어있는 경우의 rendering
    if (role === "ROLE_ADMIN") {
      // 관리자 권한인 경우의 rendering
      return (
        <>
          <Header />
          <div id="main">
            <p>안녕하세요. {name}님.</p>
            <p> Your Role is {role}. This is Main page. After Login.</p>
            <Button onClickFtn={handleLogoutButtonClick} text={"로그아웃"} />
            <Button
              onClickFtn={handleAdminButtonClick}
              text={"관리자 페이지"}
            />
          </div>
          <Footer />
        </>
      );
    } else {
      // 관리자 권한이 아닌 경우의 rendering
      return (
        <>
          <Header />
          <div id="main">
            <p>안녕하세요. {name}님.</p>
            <p>Your Role is {role}. This is Main page. After Login.</p>
            <Button onClickFtn={handleLogoutButtonClick} text={"로그아웃"} />
          </div>
          <Footer />
        </>
      );
    }
  } else {
    // 로그인이 되어있지 않은 경우의 rendering
    return (
      <>
        <Header />
        <div id="main">
          <p>AI융합학부 세미나실 예약 시스템에 오신 것을 환영합니다.</p>
          <Button onClickFtn={handleLoginButtonClick} text={"로그인"} />
        </div>
        <Footer />
      </>
    );
  }
};

export default RootPage;
