import axios from "axios";

// axios 객체 생성
export default axios.create({
  baseURL: "http://192.168.205.62:8080/zipsee",
  headers: {
    "Content-Type": "application/json;charset=utf-8",
  },
});
