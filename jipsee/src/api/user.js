import { apiInstance } from "./index.js";

const api = apiInstance();

async function login(user, success, fail) {
  console.log("login", user);
  await api.post(`/user/login`, JSON.stringify(user)).then(success).catch(fail);
}

async function findById(userid, success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  await api.get(`/user/info/${userid}`).then(success).catch(fail);
}

async function tokenRegeneration(user, success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token"); //axios header에 refresh-token 셋팅
  await api.post(`/user/refresh`, user).then(success).catch(fail);
}

async function logout(userid, success, fail) {
  await api.get(`/user/logout/${userid}`).then(success).catch(fail);
}

function signup(user, success, fail) {
  api.post(`/user`, user).then(success).catch(fail);
}

function modify(user, success, success2, fail) {
  api.put(`/mypage/modify`, user).then(success).then(success2).catch(fail);
}

function deleteUser(success, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.delete(`/mypage/delete`).then(success).catch(fail);
}

function likeHouse(deal, success, success2, fail) {
  api.post(`/deal/like`, deal).then(success).then(success2).catch(fail);
}

function unLikeHouse(dealId, success, success2, fail) {
  api.defaults.headers["access-token"] = sessionStorage.getItem("access-token");
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.delete(`/deal/unlike/${dealId}`, dealId).then(success).then(success2).catch(fail);
}

export { login, findById, tokenRegeneration, logout, signup, modify, deleteUser, likeHouse, unLikeHouse };
