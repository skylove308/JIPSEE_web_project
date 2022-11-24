import { apiInstance } from "./index.js";

const api = apiInstance();

function sidoList(success, fail) {
  const url = `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=*00000000`;
  api.get(url).then(success).catch(fail);
}

function gugunList(sido, success, fail) {
  const url = `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${sido}*000000&is_ignore_zero=true`;
  api.get(url).then(success).catch(fail);
}

function dongList(gugun, success, fail) {
  const url = `https://grpc-proxy-server-mkvo6j4wsq-du.a.run.app/v1/regcodes?regcode_pattern=${gugun}*&is_ignore_zero=true`;
  api.get(url).then(success).catch(fail);
}

function houseList(filterList, success, fail) {
  api.post(`/deal/filter`, filterList).then(success).catch(fail);
}

function interestList(success, fail) {
  api.get(`/deal`).then(success).catch(fail);
}

function recommendHouseList(success, fail) {
  api.defaults.headers["refresh-token"] = sessionStorage.getItem("refresh-token");
  api.get(`/`).then(success).catch(fail);
}

export { sidoList, gugunList, dongList, houseList, interestList, recommendHouseList };
