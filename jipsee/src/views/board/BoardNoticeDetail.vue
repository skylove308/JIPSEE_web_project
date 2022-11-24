<template>
  <div class="flex flex-col">
    <div class="pl-40 pt-30 pb-30 bg-gray-50" v-html="notice.content"></div>
    <button
      v-if="isAdmin"
      class="mt-20 font-semibold text-white bg-yellow-700 w-100 h-50 place-self-end"
      @click="deleteAdminNotice"
    >
      삭제하기
    </button>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
const userStore = "userStore";

export default {
  name: "BoardNoticeDetail",
  props: {
    notice: Object,
  },
  data() {
    return {
      isAdmin: false,
    };
  },
  created() {
    if (this.userInfo.userId == "admin") this.isAdmin = true;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },
  methods: {
    ...mapActions(boardStore, ["deleteNotice"]),
    deleteAdminNotice() {
      console.log(this.notice.boardId);
      this.deleteNotice(this.notice.boardId).then(() => {
        this.$router.go();
      });
    },
  },
};
</script>

<style></style>
