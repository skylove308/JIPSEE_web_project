<template>
  <div class="flex flex-col items-center w-full">
    <div class="w-1200">
      <img src="@/assets/main.jpg" class="object-cover object-center w-full rounded-5 h-500" />
      <div>
        <div class="mb-10 font-bold mt-80 text-24">실시간 매물 TOP 10</div>
        <!-- Slider main container -->
        <div ref="swiper" class="swiper">
          <div class="swiper-wrapper">
            <!-- Slides -->
            <div
              v-for="(houseDeal, index) in recommendHouses.popularDealList"
              :key="index"
              class="swiper-slide"
              @click="onClickDetailHouseView(houseDeal)"
            >
              <div v-if="houseDeal.houseInfo.img == null">
                <img class="object-cover w-168 h-130" :src="require(`@/assets/sample.jpg`)" />
              </div>
              <div v-else>
                <img class="houseInfo-img rounded-5" :src="require(`@/assets/${houseDeal.houseInfo.houseId}/1.jpg`)" />
              </div>
              <div v-if="houseDeal.dealType == '월세'" class="mt-10 font-bold text-20">
                {{ houseDeal.dealType }} {{ houseDeal.deposit | changeMoneyUnit }} /
                {{ houseDeal.price | changeMoneyUnit }}
              </div>
              <div v-else class="mt-10 font-bold text-20">
                {{ houseDeal.dealType }}
                {{ houseDeal.price | changeMoneyUnit }}
              </div>
              <div class="text-14">
                {{ houseDeal.floor }}층, {{ houseDeal.area }}m², {{ houseDeal.houseInfo.buildYear }}년 건축
              </div>
            </div>
          </div>
        </div>

        <!-- 아파트 추천 리스트(동코드) -->
        <div v-for="(apartList, i) in recommendHouses.apartListBydong" :key="'apart' + i">
          <div
            v-if="recommendHouses.user.nickName != null"
            class="mt-20 mb-10 mr-20 font-bold text-24"
            v-text="`${recommendHouses.user.nickName}님을 위한 ${apartList[i].houseInfo.dongName} 추천 아파트`"
          ></div>
          <div
            v-else
            class="mt-20 mb-10 mr-20 font-bold text-24"
            v-text="`${apartList[i].houseInfo.dongName} 추천 아파트`"
          ></div>
          <div :ref="`swiper${i + 2}`" class="swiper">
            <div class="swiper-wrapper">
              <!-- Slides -->
              <div
                v-for="(houseDeal, index) in apartList"
                :key="index"
                class="swiper-slide"
                @click="onClickDetailHouseView(houseDeal)"
              >
                <div v-if="houseDeal.houseInfo.img == null">
                  <img class="object-cover w-168 h-130" :src="require(`@/assets/sample.jpg`)" />
                </div>
                <div v-else>
                  <img
                    class="houseInfo-img rounded-5"
                    :src="require(`@/assets/${houseDeal.houseInfo.houseId}/1.jpg`)"
                  />
                </div>
                <div v-if="houseDeal.dealType == '월세'" class="mt-10 font-bold text-20">
                  {{ houseDeal.dealType }} {{ houseDeal.deposit | changeMoneyUnit }} /
                  {{ houseDeal.price | changeMoneyUnit }}
                </div>
                <div v-else class="mt-10 font-bold text-20">
                  {{ houseDeal.dealType }}
                  {{ houseDeal.price | changeMoneyUnit }}
                </div>
                <div class="text-14">
                  {{ houseDeal.floor }}층, {{ houseDeal.area }}m², {{ houseDeal.houseInfo.buildYear }}년 건축
                </div>
              </div>
            </div>
          </div>
        </div>

        <!-- 원룸 추천 리스트(동코드) -->
        <div v-for="(oneRoomList, i) in recommendHouses.oneRoomListByDong" :key="'oneRoom' + i">
          <div
            v-if="recommendHouses.user.nickName != null"
            class="mt-20 mb-10 mr-20 font-bold text-24"
            v-text="`${recommendHouses.user.nickName}님을 위한 ${oneRoomList[i].houseInfo.dongName} 추천 원룸`"
          ></div>
          <div
            v-else
            class="mt-20 mb-10 mr-20 font-bold text-24"
            v-text="`${oneRoomList[i].houseInfo.dongName} 추천 주택/원룸`"
          ></div>
          <div :ref="`swiper${recommendHouses.apartListBydong.length + i + 2}`" class="swiper">
            <div class="swiper-wrapper">
              <!-- Slides -->
              <div
                v-for="(houseDeal, index) in oneRoomList"
                :key="index"
                class="swiper-slide"
                @click="onClickDetailHouseView(houseDeal)"
              >
                <div v-if="houseDeal.houseInfo.img == null">
                  <img class="object-cover w-168 h-130" :src="require(`@/assets/sample.jpg`)" />
                </div>
                <div v-else>
                  <img
                    class="houseInfo-img rounded-5"
                    :src="require(`@/assets/${houseDeal.houseInfo.houseId}/1.jpg`)"
                  />
                </div>
                <div v-if="houseDeal.dealType == '월세'" class="mt-10 font-bold text-20">
                  {{ houseDeal.dealType }} {{ houseDeal.deposit | changeMoneyUnit }} /
                  {{ houseDeal.price | changeMoneyUnit }}
                </div>
                <div v-else class="mt-10 font-bold text-20">
                  {{ houseDeal.dealType }}
                  {{ houseDeal.price | changeMoneyUnit }}
                </div>
                <div class="text-14">
                  {{ houseDeal.floor }}층, {{ houseDeal.area }}m², {{ houseDeal.houseInfo.buildYear }}년 건축
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
    <the-footer></the-footer>
  </div>
</template>

<script>
import Swiper, { Navigation, Pagination, Autoplay } from "swiper";
import "swiper/swiper-bundle.min.css";
import TheFooter from "@/views/TheFooter.vue";
import { mapState, mapMutations, mapActions } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";

export default {
  components: { TheFooter },
  data() {
    return {
      algorithmRecommend: null,
    };
  },
  created() {
    this.getRecommendHouseList().then(() => console.log(this.recommendHouses));
  },
  mounted() {
    // this.algorithmRecommend = this.SET_ALGO_RECOMMEND_HOUSE_LIST(this.userInfo);
    let user = this.recommendHouses.user;
    console.log("user", user);

    let apartRecommendSize = this.recommendHouses.apartListBydong.length;
    console.log(apartRecommendSize);

    let oneRoomRecommendSize = this.recommendHouses.oneRoomListByDong.length;
    console.log(oneRoomRecommendSize);

    let num = 2;

    new Swiper(this.$refs.swiper, {
      //실시간 top 10
      modules: [Navigation, Pagination, Autoplay],
      grabCursor: true,
      slidesPerView: 4.3,
      spaceBetween: 10,
    });

    for (let i = 0; i < apartRecommendSize; i++) {
      //아파트 추천 리스트(동코드)
      new Swiper(this.$refs["swiper" + num], {
        modules: [Navigation, Pagination, Autoplay],
        grabCursor: true,
        slidesPerView: 4.3,
        spaceBetween: 10,
        freeMode: true,
      });

      num++;
    }

    for (let i = 0; i < oneRoomRecommendSize; i++) {
      //원룸 추천 리스트(동코드)
      new Swiper(this.$refs["swiper" + num], {
        modules: [Navigation, Pagination, Autoplay],
        grabCursor: true,
        slidesPerView: 4.3,
        spaceBetween: 10,
        freeMode: true,
      });

      num++;
    }

    //알고리즘 매물 추천 리스트
    new Swiper(this.$refs["swiper" + num], {
      modules: [Navigation, Pagination, Autoplay],
      grabCursor: true,
      slidesPerView: 4.3,
      spaceBetween: 10,
      freeMode: true,
    });
  },
  computed: {
    ...mapState(houseStore, ["recommendHouses", "house"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapMutations(houseStore, ["SET_DETAIL_HOUSE", "SET_ALGO_RECOMMEND_HOUSE_LIST"]),
    ...mapActions(houseStore, ["getRecommendHouseList"]),
    onClickDetailHouseView(house) {
      this.SET_DETAIL_HOUSE(house);
      this.$router.push({ name: "housedetail" });
    },
  },
  filters: {
    changeMoneyUnit(money) {
      if (money >= 100000000)
        return (
          Math.floor(money / 100000000) +
          "억 " +
          (Math.floor((money % 100000000) / 10000000) == 0 ? "" : Math.floor((money % 100000000) / 10000000) + "천")
        );
      else if (money >= 10000000) return money / 10000000 + "천";
      return money / 10000;
    },
  },
};
</script>

<style scoped>
.swiper {
  width: 100%;
  height: 250px;
}

.swiper-slide {
  display: flex;
  flex-direction: column;
}

.houseInfo-img {
  width: 100%;
  height: 150px;
}
</style>
