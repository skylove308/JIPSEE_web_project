<template>
  <div class="sticky top-0 z-10 flex items-center justify-between w-full bg-white border-gray-200 shadow-md border-b-1">
    <div class="flex items-center ml-10">
      <router-link to="/" class="h-80 w-80"><img :src="logoImg" /></router-link>
      <router-link :to="{ name: 'houselist' }" class="ml-32 font-semibold text-18 hover:text-yellow-400"
        >매물탐색</router-link
      >
      <router-link
        :to="{ name: 'board' }"
        class="ml-32 font-semibold text-18 hover:text-yellow-400"
        @click="onClickBoard"
        >공지사항</router-link
      >
    </div>
    <div class="flex mr-16 w-700 h-35">
      <div class="relative w-500">
        <input
          type="text"
          name="search"
          id="search"
          v-model="keywordplace"
          class="absolute right-0 bg-gray-200 border-none float w-500 h-35 rounded-5"
        />
        <div class="absolute cursor-pointer float right-7 top-2 text-20" @click="setKeyword">
          <font-awesome-icon icon="fa-solid fa-magnifying-glass" />
        </div>
      </div>
      <div v-if="!isLogin" class="flex items-center justify-center w-200">
        <router-link :to="{ name: 'userlogin' }" class="ml-12 font-semibold text-18 hover:text-yellow-400"
          >로그인</router-link
        >
        <router-link :to="{ name: 'usersignup' }" class="ml-12 font-semibold text-18 hover:text-yellow-400"
          >회원가입</router-link
        >
      </div>
      <div v-else class="flex items-center justify-center w-200">
        <div class="ml-12 font-semibold cursor-pointer text-18 hover:text-yellow-400" @click="onClickLogout">
          로그아웃
        </div>
        <router-link
          :to="{ name: 'usermypage' }"
          class="ml-12 font-semibold cursor-pointer text-18 hover:text-yellow-400"
          @click="onClickMyPage"
          >마이페이지</router-link
        >
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapGetters, mapActions, mapMutations } from "vuex";

const userStore = "userStore";
const houseStore = "houseStore";
export default {
  data() {
    return {
      keywordplace: null,
      logoImg: require("@/assets/logo.gif"),
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "userInfo"]),
    ...mapState(houseStore, ["keyword"]),
    ...mapGetters(["checkUserInfo"]),
  },
  methods: {
    ...mapMutations(houseStore, ["SET_KEYWORD_SEARCH"]),
    ...mapActions(userStore, ["userLogout"]),
    // ...mapMutations(memberStore, ["SET_IS_LOGIN", "SET_USER_INFO"]),
    changeLogo(b) {
      if (b) this.logoImg = require("@/assets/logo.png");
      else this.logoImg = require("@/assets/logo.gif");
    },
    setKeyword() {
      this.SET_KEYWORD_SEARCH(this.keywordplace);
      if (this.$route.path != "/house/list") this.$router.push({ name: "houselist" });
    },
    onClickLogout() {
      // this.SET_IS_LOGIN(false);
      // this.SET_USER_INFO(null);
      // sessionStorage.removeItem("access-token");
      // if (this.$route.path != "/") this.$router.push({ name: "main" });
      console.log(this.userInfo.userId);
      //vuex actions에서 userLogout 실행(Backend에 저장 된 리프레시 토큰 없애기
      //+ satate에 isLogin, userInfo 정보 변경)
      // this.$store.dispatch("userLogout", this.userInfo.userid);
      this.userLogout(this.userInfo.userId);

      sessionStorage.removeItem("access-token"); //저장된 토큰 없애기
      sessionStorage.removeItem("refresh-token"); //저장된 토큰 없애기
      if (this.$route.path != "/") this.$router.push({ name: "main" });
    },
    onClickBoard() {
      this.$emit("buttonAttribute", "notice");
    },
    onClickMyPage() {
      this.$emit("buttonAttribute", "like");
    },
  },
};
</script>

<style></style>
