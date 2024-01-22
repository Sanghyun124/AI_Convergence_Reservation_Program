import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import { useRecoilState } from "recoil";
import { userInfoState } from "./utils/recoilState";
import RootPage from "./pages/RootPage";
import LoginPage from "./pages/LoginPage";
import FindPasswordPage from "./pages/FindPasswordPage";
import TestPage from "./pages/TestPage";

function App() {
  const [isLogin] = useRecoilState(userInfoState);

  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RootPage />}></Route>
        {isLogin ? (
          <>
            <Route path="/test" element={<TestPage />}></Route>
          </>
        ) :
          <>
            <Route path="/login" element={<LoginPage />}></Route>
            <Route path="/findpw" element={<FindPasswordPage />}></Route>
          </>}
        <Route path="*" element={<Navigate to="/" />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
