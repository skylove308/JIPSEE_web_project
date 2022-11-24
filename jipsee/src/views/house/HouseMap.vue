<template>
  <div id="map" class="relative w-[calc(100%-396px)] h-full">
    <ul id="category" v-show="category">
      <li id="BK9" data-order="bank">
        <span><img src="@/assets/map/bank.png" /></span>
        은행
      </li>
      <li id="MT1" data-order="shop">
        <span><img src="@/assets/map/shop.png" /></span>
        대형마트
      </li>
      <li id="PM9" data-order="drugstore">
        <span><img src="@/assets/map/drugstore.png" /></span>
        약국
      </li>
      <li id="OL7" data-order="gas-station">
        <span><img src="@/assets/map/gas-station.png" /></span>
        주유소
      </li>
      <li id="CE7" data-order="cafe">
        <span><img src="@/assets/map/cafe.png" /></span>
        카페
      </li>
      <li id="CS2" data-order="24-hours">
        <span><img src="@/assets/map/24-hours.png" /></span>
        편의점
      </li>
      <li id="PS3" data-order="kid-school">
        <span><img src="@/assets/map/kid-school.png" /></span>
        유치원
      </li>
      <li id="SC4" data-order="school">
        <span><img src="@/assets/map/school.png" /></span>
        학교
      </li>
      <li id="AC5" data-order="trainning">
        <span><img src="@/assets/map/training.png" /></span>
        학원
      </li>
      <li id="PK6" data-order="parking-area">
        <span><img src="@/assets/map/parking-area.png" /></span>
        주차장
      </li>
      <li id="SW8" data-order="train">
        <span><img src="@/assets/map/train.png" /></span>
        지하철역
      </li>
      <li id="CT1" data-order="musical">
        <span class="category_bg store"><img src="@/assets/map/musical.png" /></span>
        문화시설
      </li>
      <li id="PO3" data-order="city-hall">
        <span class="category_bg store"><img src="@/assets/map/city-hall.png" /></span>
        공공기관
      </li>
      <li id="AT4" data-order="trip">
        <span class="category_bg store"><img src="@/assets/map/trip.png" /></span>
        관광명소
      </li>
      <li id="FD6" data-order="restaurant">
        <span class="category_bg store"><img src="@/assets/map/restaurant.png" /></span>
        음식점
      </li>
      <li id="HP8" data-order="hospital">
        <span class="category_bg store"><img src="@/assets/map/hospital.png" /></span>
        병원
      </li>
      <li @click="viewCategory">
        <span class="w-full h-full"
          ><font-awesome-icon icon="fa-solid fa-angles-left" class="w-full h-full pt-10 text-yellow-400"
        /></span>
      </li>
    </ul>
    <button
      v-if="!category"
      @click="viewCategory"
      class="absolute z-10 bg-white border-yellow-400 border-1 rounded-5 top-10 left-10 h-50 w-170"
    >
      카테고리 펼치기 <font-awesome-icon icon="fa-solid fa-angles-right" class="text-yellow-400" />
    </button>
    <div><font-awesome-icon icon="fa-solid fa-angles-left" /></div>
  </div>
</template>

<script>
/*global kakao*/
import { mapState, mapMutations, mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  data() {
    return {
      map: null,
      marker: null,
      category: false,
    };
  },
  computed: {
    ...mapState(houseStore, ["house", "houses", "keyword", "filterList"]),
  },
  watch: {
    house() {
      if (this.house) {
        this.panTo(this.house.houseInfo.lat, this.house.houseInfo.lng);
        this.removeMarker();
        this.setMarker(this.house.houseInfo.lat, this.house.houseInfo.lng);
        console.log(this.house.houseInfo.lat, this.house.houseInfo.lng);
      }
    },
    keyword() {
      if (this.keyword) {
        this.initMap();
      }
    },
  },

  mounted() {
    window.kakao && window.kakao.maps ? this.initMap() : this.addKakaoMapScript();
  },
  methods: {
    ...mapActions(houseStore, ["getHouseList"]),
    ...mapMutations(houseStore, ["CLEAR_KEYWORD_LIST", "CLEAR_HOUSES_LIST", "SET_FILTER_DONGCODE"]),
    viewCategory() {
      this.category = !this.category;
    },
    initMap() {
      // 마커를 클릭했을 때 해당 장소의 상세정보를 보여줄 커스텀오버레이입니다
      var placeOverlay = new kakao.maps.CustomOverlay({ zIndex: 1 }),
        contentNode = document.createElement("div"), // 커스텀 오버레이의 컨텐츠 엘리먼트 입니다
        markers = [], // 마커를 담을 배열입니다
        currCategory = ""; // 현재 선택된 카테고리를 가지고 있을 변수입니다

      var mapContainer = document.getElementById("map"), // 지도를 표시할 div
        mapOption = {
          center: new kakao.maps.LatLng(37.566826, 126.9786567), // 지도의 중심좌표
          level: 3, // 지도의 확대 레벨
        };

      // 지도를 생성합니다
      var map = new kakao.maps.Map(mapContainer, mapOption);
      this.map = map;

      // 장소 검색 객체를 생성합니다
      var ps = new kakao.maps.services.Places(map);

      // 주소-좌표 변환 객체를 생성합니다

      var geocoder = new kakao.maps.services.Geocoder();
      var keyword = "";
      if (!this.keyword) keyword = "";
      else keyword = this.keyword;
      if (keyword)
        ps.keywordSearch(keyword, (data) => {
          console.log(data);
          this.panTo(data[0].y, data[0].x);
          console.log(keyword);
          geocoder.coord2RegionCode(data[0].x, data[0].y, (result) => {
            if (result) {
              this.CLEAR_HOUSES_LIST();
              this.SET_FILTER_DONGCODE(result[0].code);
              this.getHouseList(this.filterList);
            }
          });
        });

      this.CLEAR_KEYWORD_LIST();
      if (this.house) {
        this.panTo(this.house.houseInfo.lat, this.house.houseInfo.lng);
        this.setMarker(this.house.houseInfo.lat, this.house.houseInfo.lng);
      }

      // 지도에 idle 이벤트를 등록합니다
      kakao.maps.event.addListener(map, "idle", searchPlaces);

      // 커스텀 오버레이의 컨텐츠 노드에 css class를 추가합니다
      contentNode.className = "placeinfo_wrap";

      // 커스텀 오버레이의 컨텐츠 노드에 mousedown, touchstart 이벤트가 발생했을때
      // 지도 객체에 이벤트가 전달되지 않도록 이벤트 핸들러로 kakao.maps.event.preventMap 메소드를 등록합니다
      addEventHandle(contentNode, "mousedown", kakao.maps.event.preventMap);
      addEventHandle(contentNode, "touchstart", kakao.maps.event.preventMap);

      // 커스텀 오버레이 컨텐츠를 설정합니다
      placeOverlay.setContent(contentNode);

      // 각 카테고리에 클릭 이벤트를 등록합니다
      addCategoryClickEvent();

      // 엘리먼트에 이벤트 핸들러를 등록하는 함수입니다
      function addEventHandle(target, type, callback) {
        if (target.addEventListener) {
          target.addEventListener(type, callback);
        } else {
          target.attachEvent("on" + type, callback);
        }
      }

      // 카테고리 검색을 요청하는 함수입니다
      function searchPlaces() {
        if (!currCategory) {
          return;
        }

        // 커스텀 오버레이를 숨깁니다
        placeOverlay.setMap(null);

        // 지도에 표시되고 있는 마커를 제거합니다
        removeMarker();

        ps.categorySearch(currCategory, placesSearchCB, { useMapBounds: true });
      }

      // 장소검색이 완료됐을 때 호출되는 콜백함수 입니다
      function placesSearchCB(data, status) {
        if (status === kakao.maps.services.Status.OK) {
          // 정상적으로 검색이 완료됐으면 지도에 마커를 표출합니다
          displayPlaces(data);
        } else if (status === kakao.maps.services.Status.ZERO_RESULT) {
          // 검색결과가 없는경우 해야할 처리가 있다면 이곳에 작성해 주세요
        } else if (status === kakao.maps.services.Status.ERROR) {
          // 에러로 인해 검색결과가 나오지 않은 경우 해야할 처리가 있다면 이곳에 작성해 주세요
        }
      }

      // 지도에 마커를 표출하는 함수입니다
      function displayPlaces(places) {
        // 몇번째 카테고리가 선택되어 있는지 얻어옵니다
        // 이 순서는 스프라이트 이미지에서의 위치를 계산하는데 사용됩니다
        var order = document.getElementById(currCategory).getAttribute("data-order");

        for (var i = 0; i < places.length; i++) {
          // 마커를 생성하고 지도에 표시합니다
          var marker = addMarker(new kakao.maps.LatLng(places[i].y, places[i].x), order);

          // 마커와 검색결과 항목을 클릭 했을 때
          // 장소정보를 표출하도록 클릭 이벤트를 등록합니다
          (function (marker, place) {
            console.log(marker);
            kakao.maps.event.addListener(marker, "click", function () {
              console.log("hi");
              displayPlaceInfo(place);
            });
          })(marker, places[i]);
        }
      }

      // 마커를 생성하고 지도 위에 마커를 표시하는 함수입니다
      function addMarker(position, category) {
        var imageSrc = require(`@/assets/map/${category}.png`), // 마커 이미지 url, 스프라이트 이미지를 씁니다
          imageSize = new kakao.maps.Size(27, 28), // 마커 이미지의 크기
          markerImage = new kakao.maps.MarkerImage(imageSrc, imageSize),
          marker = new kakao.maps.Marker({
            position: position, // 마커의 위치
            image: markerImage,
          });

        marker.setMap(map); // 지도 위에 마커를 표출합니다
        markers.push(marker); // 배열에 생성된 마커를 추가합니다

        return marker;

        // var imageSrc = require(`@/assets/map/${category}.png`);
        // var content = `<div class=" w-30 h-30 hover:brightness-90">`;
        // content += `<img src="${imageSrc}" alt="" >`;

        // content += "</div>";

        // // 커스텀 오버레이를 생성합니다
        // var marker = new kakao.maps.CustomOverlay({
        //   position: position,
        //   content: content,
        //   xAnchor: 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
        //   yAnchor: 1.1, // 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
        // });

        // marker.setMap(map);
        // markers.push(marker);

        // return marker;
      }

      // 지도 위에 표시되고 있는 마커를 모두 제거합니다
      function removeMarker() {
        for (var i = 0; i < markers.length; i++) {
          markers[i].setMap(null);
        }
        markers = [];
      }

      // 클릭한 마커에 대한 장소 상세정보를 커스텀 오버레이로 표시하는 함수입니다
      function displayPlaceInfo(place) {
        var content =
          '<div class="placeinfo">' +
          '   <a class="title" href="' +
          place.place_url +
          '" target="_blank" title="' +
          place.place_name +
          '">' +
          place.place_name +
          "</a>";

        if (place.road_address_name) {
          content +=
            '    <span title="' +
            place.road_address_name +
            '">' +
            place.road_address_name +
            "</span>" +
            '  <span class="jibun" title="' +
            place.address_name +
            '">(지번 : ' +
            place.address_name +
            ")</span>";
        } else {
          content += '    <span title="' + place.address_name + '">' + place.address_name + "</span>";
        }

        content += '    <span class="tel">' + place.phone + "</span>" + "</div>" + '<div class="after"></div>';

        contentNode.innerHTML = content;
        placeOverlay.setPosition(new kakao.maps.LatLng(place.y, place.x));
        placeOverlay.setMap(map);
      }

      // 각 카테고리에 클릭 이벤트를 등록합니다
      function addCategoryClickEvent() {
        var category = document.getElementById("category"),
          children = category.children;

        for (var i = 0; i < children.length; i++) {
          children[i].onclick = onClickCategory;
        }
      }

      // 카테고리를 클릭했을 때 호출되는 함수입니다
      function onClickCategory() {
        var id = this.id,
          className = this.className;

        placeOverlay.setMap(null);

        if (className === "on") {
          currCategory = "";
          changeCategoryClass();
          removeMarker();
        } else {
          currCategory = id;
          changeCategoryClass(this);
          searchPlaces();
        }
      }

      // 클릭된 카테고리에만 클릭된 스타일을 적용하는 함수입니다
      function changeCategoryClass(el) {
        var category = document.getElementById("category"),
          children = category.children,
          i;

        for (i = 0; i < children.length; i++) {
          children[i].className = "";
        }

        if (el) {
          el.className = "on";
        }
      }
    },
    addKakaoMapScript() {
      const script = document.createElement("script");
      script.onload = () => kakao.maps.load(this.initMap);
      script.src =
        "http://dapi.kakao.com/v2/maps/sdk.js?autoload=false&appkey=a129da21c80496260f16ba43b6b9918b&libraries=services";
      document.head.appendChild(script);
    },
    panTo(lat, lng) {
      // 이동할 위도 경도 위치를 생성합니다
      var moveLatLon = new kakao.maps.LatLng(lat, lng);

      // 지도 중심을 부드럽게 이동시킵니다
      // 만약 이동할 거리가 지도 화면보다 크면 부드러운 효과 없이 이동합니다
      this.map.panTo(moveLatLon);
    },
    setMarker(lat, lng) {
      var imageSrc = require("@/assets/map/house.png");
      var content = '<div class="w-40 h-40 animate-bounce">';
      content += `<img src="${imageSrc}" alt="" >`;

      content += "</div>";

      var markerPosition = new kakao.maps.LatLng(lat, lng);

      // 커스텀 오버레이를 생성합니다
      this.marker = new kakao.maps.CustomOverlay({
        position: markerPosition,
        content: content,
        xAnchor: 0.5, // 커스텀 오버레이의 x축 위치입니다. 1에 가까울수록 왼쪽에 위치합니다. 기본값은 0.5 입니다
        yAnchor: 1.1, // 커스텀 오버레이의 y축 위치입니다. 1에 가까울수록 위쪽에 위치합니다. 기본값은 0.5 입니다
      });

      this.marker.setMap(this.map);
    },

    removeMarker() {
      this.marker.setMap(null);
    },
  },
};
</script>

<style>
.map_wrap,
.map_wrap * {
  margin: 0;
  padding: 0;
  font-family: "Malgun Gothic", dotum, "돋움", sans-serif;
  font-size: 12px;
}
.map_wrap {
  position: relative;
  width: 100%;
  height: 350px;
}
#category {
  position: absolute;
  font-size: 14px;
  top: 10px;
  left: 10px;
  border-radius: 5px;
  border: 1px solid #909090;
  box-shadow: 0 1px 1px rgba(0, 0, 0, 0.4);
  background: #fff;
  overflow: hidden;
  z-index: 2;
}
#category li {
  float: left;
  list-style: none;
  width: 65px;
  height: 65px;
  border-right: 1px solid #acacac;
  padding: 6px 0;
  text-align: center;
  cursor: pointer;
}
#category li.on {
  background: #eee;
}
#category li:hover {
  background: #ffe6e6;
  border-left: 1px solid #acacac;
  margin-left: -1px;
}
#category li:last-child {
  margin-right: 0;
  border-right: 0;
}
#category li span {
  display: block;
  margin: 0 auto 3px;
  width: 27px;
  height: 28px;
}

.placeinfo_wrap {
  position: absolute;
  z-index: 10;
  bottom: 28px;
  left: -150px;
  width: 300px;
}
.placeinfo {
  position: relative;
  width: 100%;
  border-radius: 6px;
  border: 1px solid #ccc;
  border-bottom: 2px solid #ddd;
  padding-bottom: 10px;
  background: #fff;
}
.placeinfo:nth-of-type(n) {
  border: 0;
  box-shadow: 0px 1px 2px #888;
}
.placeinfo_wrap .after {
  content: "";
  position: relative;
  margin-left: -12px;
  left: 50%;
  width: 22px;
  height: 12px;
  background: url("https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/vertex_white.png");
}

.placeinfo a:hover {
  color: black;
  filter: brightness(90%);
  text-decoration: none;
}
.placeinfo a,
.placeinfo span {
  display: block;
  text-overflow: ellipsis;
  overflow: hidden;
  white-space: nowrap;
}
.placeinfo span {
  margin: 7px 5px -4px 9px;
  cursor: default;
  font-size: 13px;
}
.placeinfo .title {
  font-weight: bold;
  font-size: 14px;
  border-radius: 6px 6px 0 0;
  margin: -1px -1px 0 -1px;
  padding: 10px;
  color: black;
  background: #fad203;
  background: #fad203 url(https://t1.daumcdn.net/localimg/localimages/07/mapapidoc/arrow_white.png) no-repeat right 14px
    center;
}
.placeinfo .tel {
  color: #0f7833;
}
.placeinfo .jibun {
  color: #999;
  font-size: 11px;
  margin-top: 0;
}
</style>
