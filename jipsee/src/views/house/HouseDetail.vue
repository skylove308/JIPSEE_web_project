<template>
  <div class="flex flex-col items-center">
    <div class="relative w-1000 h-500">
      <div v-if="house.houseInfo.img == null">
        <img class="object-cover w-1000 h-500" :src="require(`@/assets/sample.jpg`)" />
      </div>
      <div v-else>
        <img class="object-cover w-1000 h-500" :src="require(`@/assets/${house.houseInfo.houseId}/1.jpg`)" />
      </div>
      <button class="absolute h-40 font-bold bg-[#e7e7e7] rounded-5 bottom-10 left-10 w-90">
        <a href="http://localhost:8077/1/index.html">360˚ VR</a>
      </button>
    </div>
    <div class="flex flex-row w-1000 h-500 mt-100">
      <div class="relative w-400 h-500">
        <div v-if="like" class="absolute top-0 right-20" @click="onClickLike">
          <font-awesome-icon icon="fa-solid fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
        <div v-else class="absolute top-0 right-20" @click="onClickLike">
          <font-awesome-icon icon="fa-regular fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
        <div class="mb-10 font-bold text-24">
          {{ house.houseInfo.houseName }}
        </div>
        <div class="pb-10 font-bold text-24">
          <div v-if="house.dealType == '월세'">
            {{ house.dealType }}
            {{ house.deposit | changeMoneyUnit }} /
            {{ house.price | changeMoneyUnit }}
          </div>
          <div v-else>
            {{ house.dealType }}
            {{ house.price | changeMoneyUnit }}
          </div>
        </div>
        <div class="mb-10 text-18">
          {{ house.houseInfo.dong.sidoName }} {{ house.houseInfo.dong.gugunName }} {{ house.houseInfo.dong.dongName }}
        </div>
        <div>{{ house.houseInfo.houseType }} {{ house.area }}m² {{ house.floor }}층</div>
      </div>
      <house-map class="w-600 h-500"></house-map>
    </div>
    <div class="mt-100 mb-100"></div>
  </div>
</template>

<script>
import HouseMap from "@/views/house/HouseMap";
import { mapState, mapActions } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";

export default {
  components: {
    HouseMap,
  },
  data() {
    return {
      like: false,
    };
  },

  created() {
    let isLikeHouse = false;
    console.log(this.userInfo);
    if (this.userInfo && this.userInfo.likeList) {
      this.userInfo.likeList.forEach((house) => {
        if (house.dealId == this.house.dealId) {
          isLikeHouse = true;
          return false;
        }
      });
    }
    console.log(isLikeHouse);
    if (isLikeHouse) this.like = true;
    else this.like = false;
  },

  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },

  methods: {
    ...mapActions(userStore, ["userLikeHouse", "userUnLikeHouse", "getUserInfo"]),
    onClickLike() {
      if (!this.isLogin) alert("로그인이 필요합니다!");
      else {
        if (this.like) {
          this.userUnLikeHouse(this.house.dealId);
          this.like = false;
        } else {
          this.userLikeHouse({
            userId: this.userInfo.userId,
            dealId: this.house.dealId,
          });
          this.like = true;
        }
      }
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

<style></style>
