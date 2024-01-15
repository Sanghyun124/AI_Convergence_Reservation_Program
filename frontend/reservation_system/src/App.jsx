import { BrowserRouter, Routes, Route } from "react-router-dom";
import RootPage from "./pages/RootPage";
// import MainPage from "./pages/MainPage";
import LoginPage from "./pages/LoginPage";
import FindPasswordPage from "./pages/FindPasswordPage";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<RootPage />}></Route>
        <Route path="/login" element={<LoginPage />}></Route>
        {/* <Route path="/main" element={<MainPage />}></Route> */}
        <Route path="/findpw" element={<FindPasswordPage />}></Route>
      </Routes>
    </BrowserRouter>
  );
}

export default App;
