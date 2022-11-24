import axios from "axios";

// local vue api axios instance
//192.168.205.62
function apiInstance() {
  const instance = axios.create({
    baseURL: "http://192.168.205.62:8080/zipsee",
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

// house deal API axios instance
function houseInstance() {
  const instance = axios.create({
    baseURL: process.env.VUE_APP_HOUSE_DEAL_URL,
    headers: {
      "Content-Type": "application/json;charset=utf-8",
    },
  });
  return instance;
}

export { apiInstance, houseInstance };
