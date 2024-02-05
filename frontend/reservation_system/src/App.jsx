import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import secureLocalStorage from "react-secure-storage";
import { useRecoilState } from "recoil";
import { userInfoState } from "./utils/recoilState";
import RootPage from "./pages/RootPage";
import LoginPage from "./pages/LoginPage";
import FindPasswordPage from "./pages/FindPasswordPage";
import AdminPage from "./pages/AdminPage";
import "./index.scss";

function App() {
  const [isLogin] = useRecoilState(userInfoState);
  const role = secureLocalStorage.getItem("role");

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RootPage />}></Route>{" "}
        {/* 로그인 여부와 상관없이 접속 가능 */}
        {isLogin ? (
          role === "ROLE_ADMIN" ? ( // 로그인이 되어있고 ADMIN 권한일 때만 접속 가능한 페이지
            <>
              <Route path="/admin" element={<AdminPage />}></Route>
            </>
          ) : (
            // 로그인이 되어있고 그 외 권한일 때만 접속 가능한 페이지
            <></>
          )
        ) : (
          // 로그인이 되어있으면 접속이 불가한 페이지
          <>
            <Route path="/login" element={<LoginPage />}></Route>
            <Route path="/findpw" element={<FindPasswordPage />}></Route>
          </>
        )}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
