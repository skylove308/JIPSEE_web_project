<template>
  <div class="grid justify-items-center">
    <div class="grid grid-rows-[40px_64px_auto] grid-cols-1 w-1200 justify-items-center">
      <div class=""></div>
      <div class="flex">
        <router-link :to="{ name: 'userlikehouse' }">
          <button
            v-if="buttonAttribute == 'like'"
            @click="switchButton('like')"
            class="h-64 font-bold text-white bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            관심 매물
          </button>
          <button
            v-else
            @click="switchButton('like')"
            class="h-64 font-bold bg-white border-yellow-400 text-16border-solid w-400 border-1 hover:brightness-90"
          >
            관심 매물
          </button>
        </router-link>
        <router-link :to="{ name: 'usermodify' }">
          <button
            v-if="buttonAttribute == 'modify'"
            @click="switchButton('modify')"
            class="h-64 font-bold text-white bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            정보 수정
          </button>
          <button
            v-else
            @click="switchButton('modify')"
            class="h-64 font-bold bg-white border-l-0 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            정보 수정
          </button>
        </router-link>
        <router-link :to="{ name: 'userask' }">
          <button
            v-if="buttonAttribute == 'ask'"
            @click="switchButton('ask')"
            class="h-64 font-bold text-white bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            1:1 문의 답변
          </button>
          <button
            v-else
            @click="switchButton('ask')"
            class="h-64 font-bold bg-white border-l-0 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            1:1 문의 답변
          </button>
        </router-link>
      </div>
      <router-view @buttonAttribute="setButton" class="w-1200"></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      buttonAttribute: "like",
    };
  },
  methods: {
    switchButton(id) {
      switch (id) {
        case "like":
          this.buttonAttribute = "like";
          break;
        case "modify":
          this.buttonAttribute = "modify";
          break;
        case "ask":
          this.buttonAttribute = "ask";
          break;
        default:
          break;
      }
    },
    setButton(id) {
      this.buttonAttribute = id;
    },
  },
};
</script>

<style></style>
