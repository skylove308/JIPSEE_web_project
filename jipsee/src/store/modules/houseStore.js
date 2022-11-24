/*global kakao*/
import { sidoList, gugunList, dongList, houseList, interestList, recommendHouseList } from "@/api/house.js";

const houseStore = {
  namespaced: true,
  state: {
    sidos: [],
    guguns: [],
    dongs: [],
    houses: [],
    house: null,
    minDealMoney: null,
    maxDealMoney: null,
    houseType: null,
    dealType: null,
    interests: [],
    recommendHouses: [],
    algoRecommendHouses: [],
    filterList: [],
    keyword: "",
  },
  getters: {},
  mutations: {
    SET_SIDO_LIST(state, sidos) {
      sidos.forEach((sido) => {
        state.sidos.push({ value: sido.code, text: sido.name });
      });
    },
    SET_GUGUN_LIST(state, guguns) {
      guguns.forEach((gugun) => {
        state.guguns.push({ value: gugun.code, text: gugun.name });
      });
    },
    SET_DONG_LIST(state, dongs) {
      dongs.forEach((dong) => {
        state.dongs.push({ value: dong.code, text: dong.name });
      });
    },
    SET_HOUSE_LIST(state, houses) {
      state.houses = houses;
    },
    SET_INTEREST_LIST(state, interests) {
      state.interests = interests;
    },
    SET_DETAIL_HOUSE(state, house) {
      state.house = house;
    },
    SET_RECOMMEND_HOUSE_LIST(state, recommendHouses) {
      state.recommendHouses = recommendHouses;
    },
    SET_ALGO_RECOMMEND_HOUSE_LIST(state, userInfo) {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a129da21c80496260f16ba43b6b9918b&libraries=services";
      document.head.appendChild(script);

      let houseList = [];
      state.recommendHouses.apartListBydong.forEach((apartList) => {
        apartList.forEach((e) => houseList.push(e));
      });

      console.log(houseList);
      console.log(userInfo);
      let result = [];
      let ps = new kakao.maps.services.Places();
      let tempHouse = null;
      houseList.forEach((house) => {
        userInfo.interestList.forEach((e) => {
          tempHouse = house;
          ps.categorySearch(e.interestId, placesSearchCB, {
            radius: 50,
            location: new kakao.maps.LatLng(tempHouse.houseInfo.lat, tempHouse.houseInfo.lng),
          });
        });
      });
      console.log("result", result);

      state.algoRecommendHouses = result;

      function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
          if (data.length >= 5) result.push(tempHouse);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }
    },
    SET_KEYWORD_SEARCH(state, keyword) {
      state.keyword = keyword;
    },
    SET_FILTER_DONGCODE(state, dongCode) {
      state.filterList.dongCode = dongCode;
    },
    CLEAR_SIDO_LIST(state) {
      state.sidos = [{ value: null, text: "시/도" }];
    },
    CLEAR_GUGUN_LIST(state) {
      state.guguns = [{ value: null, text: "구/군" }];
    },
    CLEAR_DONG_LIST(state) {
      state.dongs = [{ value: null, text: "동" }];
    },
    CLEAR_HOUSES_LIST(state) {
      state.houses = [];
    },
    CLEAR_HOUSE_LIST(state) {
      state.house = null;
    },
    CLEAR_FILTER_LIST(state) {
      state.filterList = {
        dongCode: null,
        houseTypeList: ["아파트", "원룸"],
        dealTypeList: ["매매", "전세", "월세"],
        minDealMoney: 0,
        maxDealMoney: 10000000000,
        minArea: 0,
        maxArea: 200,
      };
    },
    CLEAR_KEYWORD_LIST(state) {
      state.keyword = null;
    },
  },
  actions: {
    getSidoList({ commit }) {
      sidoList(
        ({ data }) => {
          commit("SET_SIDO_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getGugunList({ commit }, sido) {
      gugunList(
        sido,
        ({ data }) => {
          commit("SET_GUGUN_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getDongList({ commit }, gugun) {
      dongList(
        gugun,
        ({ data }) => {
          commit("SET_DONG_LIST", data.regcodes);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getHouseList({ commit }, filterList) {
      console.log(filterList);
      houseList(
        filterList,
        ({ data }) => {
          commit("SET_HOUSE_LIST", data);
          commit("SET_DETAIL_HOUSE", data[0]);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getInterestList({ commit }) {
      interestList(
        ({ data }) => {
          commit("SET_INTEREST_LIST", data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
    getRecommendHouseList({ commit }) {
      recommendHouseList(
        ({ data }) => {
          commit("SET_RECOMMEND_HOUSE_LIST", data);
          console.log(data);
        },
        (error) => {
          console.log(error);
        }
      );
    },
  },
};

export default houseStore;
