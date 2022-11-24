import { apiInstance } from "./index.js";

const api = apiInstance();

function noticeList(success, fail) {
  api.get(`/board/notice`).then(success).catch(fail);
}

function writeNotice(notice, success, fail) {
  api.post(`/board/notice`, notice).then(success).catch(fail);
}

function writeAsk(ask, success, success2, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.post(`/board/ask`, ask).then(success).then(success2).catch(fail);
}

function getArticle(articleno, success, fail) {
  api.get(`/board/${articleno}`).then(success).catch(fail);
}

function modifyArticle(article, success, fail) {
  api.put(`/board`, JSON.stringify(article)).then(success).catch(fail);
}

function deleteNotice(boardid, success, fail) {
  api.delete(`/board/notice/${boardid}`).then(success).catch(fail);
}

function askList(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.get(`/mypage/ask`).then(success).catch(fail);
}

function writeComment(comment, success, fail) {
  api.post(`/board/comment`, comment).then(success).catch(fail);
}

export { noticeList, writeNotice, getArticle, modifyArticle, deleteNotice, askList, writeAsk, writeComment };
