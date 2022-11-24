<template>
  <div class="grid justify-items-center">
    <div class="grid grid-rows-[214px_64px_auto] grid-cols-1 w-1200 justify-items-center">
      <div class="pb-64 font-bold text-46 pt-80">문의하기</div>
      <div class="flex">
        <router-link :to="{ name: 'boardquestion' }">
          <button
            v-if="buttonAttribute == 'question'"
            @click="switchButton('question')"
            class="h-64 font-bold bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            자주 묻는 질문
          </button>
          <button
            v-else
            @click="switchButton('question')"
            class="h-64 font-bold bg-white border-yellow-400 text-16border-solid w-400 border-1 hover:brightness-90"
          >
            자주 묻는 질문
          </button>
        </router-link>
        <router-link :to="{ name: 'boardask' }">
          <button
            v-if="buttonAttribute == 'ask'"
            @click="switchButton('ask')"
            class="h-64 font-bold bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            1:1 문의
          </button>
          <button
            v-else
            @click="switchButton('ask')"
            class="h-64 font-bold bg-white border-l-0 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            1:1 문의
          </button>
        </router-link>
        <router-link :to="{ name: 'boardnotice' }">
          <button
            v-if="buttonAttribute == 'notice'"
            @click="switchButton('notice')"
            class="h-64 font-bold bg-yellow-400 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            공지사항
          </button>
          <button
            v-else
            @click="switchButton('notice')"
            class="h-64 font-bold bg-white border-l-0 border-yellow-400 border-solid text-16 w-400 border-1 hover:brightness-90"
          >
            공지사항
          </button>
        </router-link>
      </div>
      <router-view @buttonAttribute="setButton" class="w-1200 mb-100"></router-view>
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      buttonAttribute: "notice",
    };
  },
  methods: {
    switchButton(id) {
      switch (id) {
        case "question":
          this.buttonAttribute = "question";
          break;
        case "ask":
          this.buttonAttribute = "ask";
          break;
        case "notice":
          this.buttonAttribute = "notice";
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
