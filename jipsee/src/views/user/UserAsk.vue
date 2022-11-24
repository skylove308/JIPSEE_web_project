<template>
  <div class="flex flex-col items-end">
    <table class="mt-50 w-1200">
      <thead>
        <tr class="border-t-2 h-60 border-t-black border-b-1 border-b-gray-400">
          <th class="w-850">제목</th>
          <th class="w-100">답변</th>
          <th class="w-250">날짜</th>
        </tr>
      </thead>
      <tbody
        v-for="(ask, index) in asks"
        :key="index"
        @click="clickContent(index)">
        <tr class="h-60">
          <td class="pl-30">{{ ask.title }}</td>
          <td class="text-center">
            <div v-if="ask.comment == null">미답변</div>
            <div v-else>답변됨</div>
          </td>
          <td class="text-center">{{ ask.registerDate }}</td>
        </tr>
        <tr v-show="askNo == index && open" class="h-60">
          <td colspan="3">
            <board-ask-detail :ask="ask"></board-ask-detail>
          </td>
        </tr>
      </tbody>
    </table>
  </div>
</template>

<script>
import BoardAskDetail from "@/views/board/BoardAskDetail";
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";

export default {
  components: {
    BoardAskDetail,
  },
  data() {
    return {
      askNo: -1,
      open: false,
    };
  },
  created() {
    this.getAskList();
  },
  computed: {
    ...mapState(boardStore, ["asks"]),
  },
  methods: {
    ...mapActions(boardStore, ["getAskList"]),
    clickContent(index) {
      this.askNo = index;
      this.open = !this.open;
    },
  },
};
</script>

<style></style>
