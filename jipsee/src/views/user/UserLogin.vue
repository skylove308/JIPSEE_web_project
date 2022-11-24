<template>
  <div class="flex items-center justify-center">
    <div class="flex flex-col items-center justify-center w-500 h-350 mb-100">
      <div class="h-24 font-bold w-72 mb-59 text-24">로그인</div>
      <div class="flex flex-col justify-between mb-40 w-447 h-115">
        <div class="flex items-center justify-between w-447 h-46">
          <div class="w-56 h-24">아이디</div>
          <input
            type="text"
            placeholder="아이디를 입력해주세요"
            v-model="user.userId"
            class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md"
          />
        </div>
        <div class="flex items-center justify-between w-447 h-46">
          <div class="w-[74px] h-24">비밀번호</div>
          <input
            type="password"
            placeholder="비밀번호를 입력해주세요"
            v-model="user.password"
            class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md"
          />
        </div>
      </div>
      <div class="flex justify-between w-447 h-50">
        <button class="text-black bg-yellow-400 rounded-md w-200 h-50 hover:brightness-90" @click="confirm">
          로그인
        </button>
        <button
          class="text-black bg-white border-yellow-400 rounded-md w-200 h-50 border-1 hover:brightness-90"
          @click="movePage"
        >
          가입하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";

const userStore = "userStore";

export default {
  name: "UserLogin",
  data() {
    return {
      // isLoginError: false,
      user: {
        userId: null,
        password: null,
      },
    };
  },
  computed: {
    ...mapState(userStore, ["isLogin", "isLoginError", "userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userConfirm", "getUserInfo"]),
    async confirm() {
      console.log(this.user.userId);
      await this.userConfirm(this.user);
      let token = sessionStorage.getItem("access-token");
      console.log("1. confirm() token >> " + token);
      if (this.isLogin) {
        await this.getUserInfo(token);
        console.log("4. confirm() userInfo :: ", this.userInfo);
        this.$router.push({ name: "main" });
      }
    },
    movePage() {
      this.$router.push({ name: "usersignup" });
    },
  },
};
</script>

<style></style>
