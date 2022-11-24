<template>
  <div class="flex items-center justify-center">
    <div class="relative flex flex-col items-center justify-center w-640">
      <div class="h-2 mt-20 bg-black w-640 mb-17"></div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-66">아이디*</div>
        <input
          v-if="click.userId && !user.userId"
          type="text"
          name="id"
          placeholder="아이디를 입력해주세요"
          v-model="user.userId"
          class="mr-12 border-2 border-pink-600 rounded-md w-330 h-46 ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <input
          v-else
          type="text"
          name="id"
          placeholder="아이디를 입력해주세요"
          v-model="user.userId"
          @click="onClick('userId')"
          class="mr-12 border-[#e5e5e5] rounded-md w-330 h-46 border-1 ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <button class="text-yellow-400 border-yellow-400 rounded-md w-120 h-46 border-1">중복확인</button>
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-84">비밀번호*</div>
        <input
          v-if="click.password && !user.password"
          type="password"
          name="password"
          placeholder="비밀번호를 입력해주세요"
          v-model="user.password"
          class="border-2 border-pink-600 rounded-md w-330 h-46 focus:border-yellow-400 focus:ring-yellow-400 ml-66"
        />
        <input
          v-else
          type="password"
          name="password"
          placeholder="비밀번호를 입력해주세요"
          v-model="user.password"
          @click="onClick('password')"
          class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md ml-66 focus:border-yellow-400 focus:ring-yellow-400"
        />
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-47">이름*</div>
        <input
          v-if="click.name && !user.name"
          type="text"
          name="name"
          placeholder="이름을 입력해주세요"
          v-model="user.name"
          class="border-2 border-pink-600 rounded-md w-330 h-46 ml-103 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <input
          v-else
          type="text"
          name="name"
          placeholder="이름을 입력해주세요"
          v-model="user.name"
          @click="onClick('name')"
          class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md ml-103 focus:border-yellow-400 focus:ring-yellow-400"
        />
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-66">닉네임*</div>
        <input
          v-if="click.nickName && !user.nickName"
          type="text"
          name="nickname"
          placeholder="닉네임을 입력해주세요"
          v-model="user.nickName"
          class="border-2 border-pink-600 rounded-md w-330 h-46 ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <input
          v-else
          type="text"
          name="nickname"
          placeholder="닉네임을 입력해주세요"
          v-model="user.nickName"
          @click="onClick('nickName')"
          class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-66">이메일*</div>
        <input
          v-if="click.email && !user.email"
          type="email"
          name="email"
          placeholder="예: ssafy@ssafy.com"
          v-model="user.email"
          class="mr-12 border-2 border-pink-600 rounded-md w-330 h-46 ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <input
          v-else
          type="email"
          name="email"
          placeholder="예: ssafy@ssafy.com"
          v-model="user.email"
          @click="onClick('email')"
          class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md ml-84 mr-12 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <button class="text-yellow-400 border-yellow-400 rounded-md w-120 h-46 border-1">중복확인</button>
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-66">휴대폰*</div>
        <input
          v-if="click.phone && !user.phone"
          type="text"
          name="phone"
          placeholder="예: 010-1234-5678"
          v-model="user.phone"
          class="border-2 border-pink-600 rounded-md w-330 h-46 ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
        <input
          v-else
          type="text"
          name="phone"
          placeholder="예: 010-1234-5678"
          v-model="user.phone"
          @click="onClick('phone')"
          class="w-330 h-46 border-1 border-[#e5e5e5] rounded-md ml-84 focus:border-yellow-400 focus:ring-yellow-400"
        />
      </div>
      <div class="flex items-center mb-20 w-612 h-46">
        <div class="h-24 w-37">성별</div>
        <div class="flex justify-between w-271 h-22 ml-122">
          <div class="flex items-center justify-between w-66 h-22">
            <input
              type="radio"
              name="sex"
              v-model="user.sex"
              value="남자"
              class="w-20 h-20 text-yellow-400 bg-gray-200 border-none checked:bg-none focus:ring-offset-0 focus:ring-0"
            />
            남자
          </div>
          <div class="flex items-center justify-between w-66 h-22">
            <input
              type="radio"
              name="sex"
              v-model="user.sex"
              value="여자"
              class="w-20 h-20 text-yellow-400 bg-gray-200 border-none checked:bg-none focus:ring-offset-0 focus:ring-0"
            />
            여자
          </div>
          <div class="flex items-center justify-between w-99 h-22">
            <input
              type="radio"
              name="sex"
              v-model="user.sex"
              value="선택안함"
              class="w-20 h-20 text-yellow-400 bg-gray-200 border-none checked:bg-none focus:ring-offset-0 focus:ring-0"
            />
            선택안함
          </div>
        </div>
      </div>
      <div class="flex mb-20 w-612 h-180">
        <div class="h-24 w-66">관심사</div>
        <div class="grid grid-cols-4 grid-rows-4 gap-5 w-330 h-180 ml-85">
          <label v-for="(interest, index) in interests" :key="index" class="cursor-pointer">
            <input type="checkbox" name="interest" v-model="user.interestList" :value="interest" class="sr-only peer" />
            <div
              class="flex items-center justify-center h-40 text-black border-yellow-400 bg rounded-5 text-14 border-1 peer-checked:text-white peer-checked:bg-yellow-400 focus:ring-offset-0 focus:ring-0"
            >
              {{ interest.interestName }}
            </div>
          </label>
        </div>
      </div>
      <div class="flex mb-20 w-612 h-90">
        <div class="h-24 w-80">관심 지역</div>
        <div class="grid grid-cols-4 grid-rows-2 gap-5 w-330 h-90 ml-73">
          <label v-for="(dong, index) in dongs" :key="index" class="cursor-pointer">
            <input
              type="checkbox"
              v-model="user.dongList"
              :value="{ dongCode: dong.dongCode }"
              class="sr-only peer"
              checked
            />
            <div
              class="flex items-center justify-center h-40 text-black border-yellow-400 bg rounded-5 text-14 border-1 peer-checked:bg-yellow-400 focus:ring-offset-0 focus:ring-0"
            >
              {{ dong.dongName | dongFormat }}
            </div>
          </label>
        </div>

        <button class="ml-12 text-yellow-400 border-yellow-400 rounded-md w-120 h-46 border-1" @click="ChangeViewModal">
          추가하기
        </button>
      </div>
      <div class="h-2 mt-20 bg-black w-640 mb-17"></div>
      <button
        class="mb-20 font-bold text-black bg-yellow-400 border-yellow-400 rounded-5 w-640 h-50 text-20"
        @click="modify"
      >
        수정하기
      </button>
      <button
        class="mb-20 font-bold text-black bg-pink-600 border-pink-600 rounded-5 w-640 h-50 text-20"
        @click="onUserDelete"
      >
        탈퇴하기
      </button>
      <user-sign-up-modal
        v-if="viewModal"
        @setUserRegion="setDongList"
        class="absolute left-[650px] bottom-160 bg-gray-200 w-180 h-224"
      ></user-sign-up-modal>
    </div>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
import UserSignUpModal from "./UserSignUpModal.vue";

const userStore = "userStore";

export default {
  components: { UserSignUpModal },
  data() {
    return {
      viewModal: false,
      interests: [
        { interestId: "MT1", interestName: "대형마트" },
        { interestId: "CS2", interestName: "편의점" },
        { interestId: "PS3", interestName: "유치원" },
        { interestId: "SC4", interestName: "학교" },
        { interestId: "AC5", interestName: "학원" },
        { interestId: "PK6", interestName: "주차장" },
        { interestId: "OL7", interestName: "주유소" },
        { interestId: "SW8", interestName: "지하철역" },
        { interestId: "BK9", interestName: "은행" },
        { interestId: "CT1", interestName: "문화시설" },
        { interestId: "PO3", interestName: "공공기관" },
        { interestId: "AT4", interestName: "관광명소" },
        { interestId: "FD6", interestName: "음식점" },
        { interestId: "CE7", interestName: "카페" },
        { interestId: "HP8", interestName: "병원" },
        { interestId: "PM9", interestName: "약국" },
      ],
      dongs: [],
      user: {
        userId: null,
        password: null,
        name: null,
        nickName: null,
        email: null,
        phone: null,
        sex: null,
        interestList: [],
        dongList: [],
      },
      click: {
        userId: false,
        password: false,
        name: false,
        nickName: false,
        email: false,
        phone: false,
      },
    };
  },
  created() {
    this.user.userId = this.userInfo.userId;
    this.user.name = this.userInfo.name;
    this.user.nickName = this.userInfo.nickName;
    this.user.email = this.userInfo.email;
    this.user.phone = this.userInfo.phone;
    this.user.sex = this.userInfo.sex;
    this.user.interestList = this.userInfo.interestList;
    this.user.dongList = this.userInfo.dongList;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(userStore, ["userModify", "userDelete"]),
    modify() {
      this.userModify(this.user).then(() => {
        this.$emit("buttonAttribute", "like");
        this.$router.push({ name: "usermypage" });
      });
    },
    ChangeViewModal() {
      this.viewModal = !this.viewModal;
    },
    setDongList(data) {
      this.dongs.push(data);
      this.user.dongList.push({ dongCode: data.dongCode });
    },
    onClick(id) {
      this.click[id] = true;
    },
    onUserDelete() {
      this.userDelete().then(() => {
        this.$router.push({ name: "main" });
      });
    },
  },
  filters: {
    dongFormat(gugun) {
      if (gugun === "동") return "동";
      return gugun.split(" ")[2];
    },
  },
};
</script>

<style></style>
