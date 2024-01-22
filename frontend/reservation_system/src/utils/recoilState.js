import { atom } from 'recoil';
import { recoilPersist } from 'recoil-persist';

const { persistAtom } = recoilPersist({
    key: "sessionStorage",
    storage: sessionStorage,    
});

export const userInfoState = atom({
    key: "isLogin",
    default: false,
    effects_UNSTABLE: [persistAtom],
});