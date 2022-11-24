import { writeNotice, noticeList, askList, writeAsk, writeComment, deleteNotice } from "@/api/board";
import router from "@/router";

const boardStore = {
  namespaced: true,
  state: {
    notices: [],
    notice: [],
    asks: [],
    ask: [],
    comment: [],
  },
  getters: {},
  mutations: {
    SET_NOTICE_LIST(state, notices) {
      state.notices = notices;
    },
    SET_ASK_LIST(state, asks) {
      state.asks = asks;
    },
  },
  actions: {
    getNoticeList({ commit }) {
      noticeList(
        ({ data }) => {
          console.log(data);
          commit("SET_NOTICE_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    writeNoticeAdmin(context, notice) {
      let msg = "등록 처리시 문제가 발생했습니다.";
      writeNotice(
        notice,
        ({ data }) => {
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            alert(msg);
          }

          router.push({ name: "boardnotice" });
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },
    writeAsk({ dispatch }, ask) {
      let msg = "1:1 답변 문의 중 문제가 발생했습니다.";
      writeAsk(
        ask,
        async ({ data }) => {
          if (data === "success") {
            let token = sessionStorage.getItem("access-token");
            msg = "1:1 문의 등록이 완료되었습니다.";
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
    getAskList({ commit }) {
      askList(
        ({ data }) => {
          console.log(data);
          commit("SET_ASK_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    writeComment(context, comment) {
      let msg = "등록 처리시 문제가 발생했습니다.";
      writeComment(
        comment,
        ({ data }) => {
          if (data === "success") {
            msg = "등록이 완료되었습니다.";
            alert(msg);
          }
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },
    deleteNotice(context, boardId) {
      let msg = "삭제시 문제가 발생했습니다.";
      deleteNotice(
        boardId,
        ({ data }) => {
          if (data === "success") {
            msg = "삭제가 완료되었습니다.";
            alert(msg);
          }
        },
        (error) => {
          alert(msg);
          console.log(error);
        }
      );
    },
  },
};

export default boardStore;
