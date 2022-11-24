<template>
  <div class="flex justify-center">
    <div class="grid grid-rows-[47px_153px] grid-cols-[168px_192px]">
      <div class="relative flex items-center col-span-2 font-bold">
        {{ houseOneItem.houseInfo.houseName }}
        <div v-if="like" class="absolute top-7 right-5" @click="onClickLike">
          <font-awesome-icon icon="fa-solid fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
        <div v-else class="absolute top-7 right-5" @click="onClickLike">
          <font-awesome-icon icon="fa-regular fa-heart" class="text-red-500 cursor-pointer w-30 h-30" />
        </div>
      </div>
      <div class="grid justify-end">
          <div v-if="houseOneItem.houseInfo.img == null">
            <img class="w-168 h-130 object-cover" :src="require(`@/assets/sample.jpg`)" />
          </div>
          <div v-else>
            <img class="w-168 h-130 object-cover" :src="require(`@/assets/${houseOneItem.houseInfo.houseId}/1.jpg`)" />
          </div>
      </div>
      <div class="pt-10 pl-15">
        <div class="pb-10 font-bold text-18">
          <div v-if="houseOneItem.dealType == '월세'">
            {{ houseOneItem.dealType }}
            {{ houseOneItem.deposit | changeMoneyUnit }} /
            {{ houseOneItem.price | changeMoneyUnit }}
          </div>
          <div v-else>
            {{ houseOneItem.dealType }}
            {{ houseOneItem.price | changeMoneyUnit }}
          </div>
        </div>
        <div>{{ houseOneItem.area }}m² {{ houseOneItem.floor }}층</div>
        <button class="mt-20 bg-yellow-400 h-38 w-100" @click="onClickHouse">보러가기</button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions, mapMutations } from "vuex";
const houseStore = "houseStore";
const userStore = "userStore";
export default {
  name: "HouseListItem",
  props: {
    houseItem: Object,
  },
  data() {
    return {
      houseOneItem: null,
      like: false,
    };
  },
  created() {
    if (this.houseItem) this.houseOneItem = this.houseItem;
    else this.houseOneItem = this.house;

    let isLikeHouse = false;
    if (this.userInfo.likeList) {
      this.userInfo.likeList.forEach((house) => {
        if (house.dealId == this.houseOneItem.dealId) {
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
    ...mapMutations(houseStore, ["SET_DETAIL_HOUSE"]),
    ...mapActions(userStore, ["userLikeHouse", "userUnLikeHouse"]),
    onClickHouse() {
      this.SET_DETAIL_HOUSE(this.houseItem);
      this.$router.push({ name: "housedetail" });
    },
    onClickLike() {
      if (!this.isLogin) alert("로그인이 필요합니다!");
      else {
        if (this.like) {
          this.userUnLikeHouse(this.houseOneItem.dealId);
          this.like = false;
        } else {
          this.userLikeHouse({
            userId: this.userInfo.userId,
            dealId: this.houseOneItem.dealId,
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
