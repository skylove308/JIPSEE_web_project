<template>
  <div class="flex flex-col items-end">
    <table class="mt-50 w-1200">
      <thead>
        <tr class="border-t-2 h-60 border-t-black border-b-1 border-b-gray-400">
          <th class="w-950">제목</th>
          <th class="w-250">날짜</th>
        </tr>
      </thead>
      <tbody v-for="(notice, index) in notices" :key="index" @click="clickContent(index)">
        <tr class="h-60">
          <td class="pl-30">{{ notice.title }}</td>
          <td class="text-center">{{ notice.registerDate }}</td>
        </tr>
        <tr v-show="noticeNo == index" class="h-60">
          <td colspan="2">
            <board-notice-detail :notice="notice"></board-notice-detail>
          </td>
        </tr>
      </tbody>
    </table>
    <router-link v-if="isAdmin" :to="{ name: 'boardnoticewrite' }">
      <button class="mt-20 font-semibold text-white bg-yellow-700 w-100 h-50">글쓰기</button>
    </router-link>
  </div>
</template>

<script>
import BoardNoticeDetail from "@/views/board/BoardNoticeDetail";
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
const userStore = "userStore";

export default {
  components: {
    BoardNoticeDetail,
  },
  data() {
    return {
      noticeNo: -1,
      isAdmin: false,
    };
  },
  created() {
    this.getNoticeList();
    if (this.userInfo.userId == "admin") this.isAdmin = true;
  },
  computed: {
    ...mapState(boardStore, ["notices"]),
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(boardStore, ["getNoticeList"]),
    clickContent(index) {
      this.noticeNo = index;
    },
  },
};
</script>

<style></style>
