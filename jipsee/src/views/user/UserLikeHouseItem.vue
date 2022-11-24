<template>
  <div class="flex justify-center">
    <div class="grid grid-rows-[47px_153px] grid-cols-[168px_192px]">
      <div class="relative flex items-center col-span-2 font-bold">
        {{ houseItem.houseDeal.houseInfo.houseName }}
        <div v-if="like" class="absolute top-7 right-5" @click="onClickLike">
          <font-awesome-icon icon="fa-solid fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
        <div v-else class="absolute top-7 right-5" @click="onClickLike">
          <font-awesome-icon icon="fa-regular fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
      </div>
      <div class="grid justify-end">
        <div v-if="houseItem.houseDeal.houseInfo.img == null">
          <img class="object-cover w-168 h-130" :src="require(`@/assets/sample.jpg`)" />
        </div>
        <div v-else>
          <img
            class="object-cover w-168 h-130"
            :src="require(`@/assets/${houseItem.houseDeal.houseInfo.houseId}/1.jpg`)"
          />
        </div>
      </div>
      <div class="pt-10 pl-15">
        <div class="pb-10 font-bold text-18">
          <div v-if="houseItem.dealType == '월세'">
            {{ houseItem.houseDeal.dealType }}
            {{ houseItem.houseDeal.deposit | changeMoneyUnit }} /
            {{ houseItem.houseDeal.price | changeMoneyUnit }}
          </div>
          <div v-else>
            {{ houseItem.houseDeal.dealType }}
            {{ houseItem.houseDeal.price | changeMoneyUnit }}
          </div>
        </div>
        <div>{{ houseItem.houseDeal.area }}m² {{ houseItem.houseDeal.floor }}층</div>
        <button class="mt-20 bg-yellow-400 h-38 w-100" @click="onClickHouse">보러가기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  props: {
    houseItem: Object,
  },
  data() {
    return {
      like: true,
    };
  },
  computed: {
    ...mapState(houseStore, ["house"]),
    ...mapState(userStore, ["isLogin", "userInfo"]),
  },
  methods: {
    ...mapMutations(houseStore, ["SET_DETAIL_HOUSE"]),
    ...mapActions(userStore, ["userLikeHouse", "userUnLikeHouse"]),
    onClickHouse() {
      this.SET_DETAIL_HOUSE(this.houseItem.houseDeal);
      this.$router.push({ name: "housedetail" });
    },
    onClickLike() {
      if (!this.isLogin) alert("로그인이 필요합니다!");
      else {
        if (this.like) {
          this.userUnLikeHouse(this.houseItem.dealId);
          this.like = false;
        } else {
          this.userLikeHouse({
            userId: this.userInfo.userId,
            dealId: this.houseItem.dealId,
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
