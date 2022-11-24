import jwtDecode from "jwt-decode";
import router from "@/router";
import {
  login,
  findById,
  tokenRegeneration,
  logout,
  signup,
  modify,
  deleteUser,
  likeHouse,
  unLikeHouse,
} from "@/api/user";

const userStore = {
  namespaced: true,
  state: {
    isLogin: false,
    isLoginError: false,
    userInfo: [],
    isValidToken: false,
  },
  getters: {
    checkUserInfo: function (state) {
      return state.userInfo;
    },
    checkToken: function (state) {
      return state.isValidToken;
    },
  },
  mutations: {
    SET_IS_LOGIN: (state, isLogin) => {
      state.isLogin = isLogin;
    },
    SET_IS_LOGIN_ERROR: (state, isLoginError) => {
      state.isLoginError = isLoginError;
    },
    SET_IS_VALID_TOKEN: (state, isValidToken) => {
      state.isValidToken = isValidToken;
    },
    SET_USER_INFO: (state, userInfo) => {
      state.userInfo = userInfo;
    },
  },
  actions: {
    async userConfirm({ commit }, user) {
      await login(
        user,
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            let refreshToken = data["refresh-token"];
            console.log("login success token created!!!! >> ", accessToken, refreshToken);
            commit("SET_IS_LOGIN", true);
            commit("SET_IS_LOGIN_ERROR", false);
            commit("SET_IS_VALID_TOKEN", true);
            sessionStorage.setItem("access-token", accessToken);
            sessionStorage.setItem("refresh-token", refreshToken);
          } else {
            commit("SET_IS_LOGIN", false);
            commit("SET_IS_LOGIN_ERROR", true);
            commit("SET_IS_VALID_TOKEN", false);
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    async getUserInfo({ commit, dispatch }, token) {
      let decodeToken = jwtDecode(token);
      console.log("2. getUserInfo() decodeToken :: ", decodeToken);
      await findById(
        decodeToken.userId,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_USER_INFO", data.userInfo);
            console.log("3. getUserInfo data >> ", data);
          } else {
            console.log("유저 정보 없음!!!!");
          }
        },
        async (error) => {
          console.log("getUserInfo() error code [토큰 만료되어 사용 불가능.] ::: ", error.response.status);
          commit("SET_IS_VALID_TOKEN", false);
          await dispatch("tokenRegeneration");
        }
      );
    },

    async tokenRegeneration({ commit, state }) {
      console.log("토큰 재발급 >> 기존 토큰 정보 : {}", sessionStorage.getItem("access-token"));
      await tokenRegeneration(
        JSON.stringify(state.userInfo),
        ({ data }) => {
          if (data.message === "success") {
            let accessToken = data["access-token"];
            console.log("재발급 완료 >> 새로운 토큰 : {}", accessToken);
            sessionStorage.setItem("access-token", accessToken);
            commit("SET_IS_VALID_TOKEN", true);
          }
        },
        async (error) => {
          // HttpStatus.UNAUTHORIZE(401) : RefreshToken 기간 만료 >> 다시 로그인!!!!
          if (error.response.status === 401) {
            console.log("갱신 실패");
            // 다시 로그인 전 DB에 저장된 RefreshToken 제거.
            await logout(
              state.userInfo.userId,
              ({ data }) => {
                if (data.message === "success") {
                  console.log("리프레시 토큰 제거 성공");
                } else {
                  console.log("리프레시 토큰 제거 실패");
                }
                alert("RefreshToken 기간 만료!!! 다시 로그인해 주세요.");
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
                commit("SET_IS_VALID_TOKEN", false);
                router.push({ name: "login" });
              },
              (error) => {
                console.log(error);
                commit("SET_IS_LOGIN", false);
                commit("SET_USER_INFO", null);
              }
            );
          }
        }
      );
    },

    async userLogout({ commit }, userId) {
      let msg = "로그아웃 실패!";
      await logout(
        userId,
        ({ data }) => {
          if (data.message === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
            msg = "로그아웃 되었습니다.";
            alert(msg);
          } else {
            alert(msg);
            console.log("유저 정보 없음!!!!");
          }
        },
        (error) => {
          console.log(error);
        }
      );
    },

    userSignup(context, user) {
      let msg = "회원 등록 처리시 문제가 발생했습니다.";
      signup(
        user,
        ({ data }) => {
          if (data === "success") {
            msg = "회원 등록이 완료되었습니다.";
            alert(msg);
            router.push({ name: "userlogin" });
          }
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },

    userModify({ dispatch }, user) {
      let msg = "회원 수정 처리시 문제가 발생했습니다.";
      modify(
        user,
        async ({ data }) => {
          if (data === "success") {
            let token = sessionStorage.getItem("access-token");
            msg = "회원 수정이 완료되었습니다.";
            await dispatch("getUserInfo", token);
            alert(msg);
            return token;
          }
        },
        (token) => {
          let newToken = sessionStorage.getItem("access-token");
          if (token != newToken) dispatch("getUserInfo", newToken);
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },

    userDelete({ commit }) {
      let msg = "회원 삭제 처리시 문제가 발생했습니다.";
      deleteUser(
        ({ data }) => {
          if (data === "success") {
            commit("SET_IS_LOGIN", false);
            commit("SET_USER_INFO", null);
            commit("SET_IS_VALID_TOKEN", false);
            msg = "회원 삭제가 완료되었습니다.";
            alert(msg);
            router.push({ name: "main" });
          }
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },

    userLikeHouse({ dispatch }, deal) {
      likeHouse(
        deal,
        async ({ data }) => {
          if (data === "success") {
            let token = sessionStorage.getItem("access-token");
            await dispatch("getUserInfo", token);
            return token;
          }
        },
        (token) => {
          let newToken = sessionStorage.getItem("access-token");
          if (token != newToken) dispatch("getUserInfo", newToken);
        },
        (error) => {
          console.log(error);
        }
      );
    },

    userUnLikeHouse({ dispatch }, dealId) {
      unLikeHouse(
        dealId,
        async ({ data }) => {
          if (data === "success") {
            let token = sessionStorage.getItem("access-token");
            await dispatch("getUserInfo", token);
            return token;
          }
        },
        (token) => {
          let newToken = sessionStorage.getItem("access-token");
          if (token != newToken) dispatch("getUserInfo", newToken);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default userStore;
