<template>
  <div class="flex flex-wrap w-1200 self-start mt-20">
    <user-like-house-item
      v-for="(house, index) in likeHouse"
      :key="index"
      :houseItem="house"
      class="border-gray-200 cursor-pointer w-396 h-200 border-b-1 mb-20"></user-like-house-item>
  </div>
</template>

<script>
import { mapState } from "vuex";
import UserLikeHouseItem from "@/views/user/UserLikeHouseItem.vue";
const userStore = "userStore";
export default {
  components: {
    UserLikeHouseItem,
  },
  data() {
    return {
      likeHouse: null,
    };
  },
  created() {
    this.likeHouse = this.userInfo.likeList;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {},
};
</script>

<style></style>
