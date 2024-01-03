const IsLogin = () => {
    const value = JSON.parse(localStorage.getItem("isLogin"));

    return value;
}

export default IsLogin