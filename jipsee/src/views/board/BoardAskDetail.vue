<template>
  <div>
    <div class="pl-40 pt-30 pb-30 bg-gray-50" v-html="ask.content"></div>
    <hr />
    <div v-if="ask.comment != null" class="pl-40 pt-30 pb-30 bg-gray-50">
      <div class="flex justify-between mb-10">
        <div>안녕하세요 <b>JIPSEE</b> 입니다.</div>
        <div class="mr-49">{{ ask.comment.registerDate }}</div>
      </div>

      <div v-html="ask.comment.content"></div>
    </div>
    <div v-else>
      <div v-if="isAdmin" class="flex flex-col mb-20 mt-50">
        <div class="mb-10 font-semibold text-16"><b>답변 달기</b></div>
        <ckeditor :editor="editor" v-model="comment.content" />
        <button class="mt-20 font-semibold text-white bg-yellow-700 w-100 h-50 place-self-end" @click="writeNewComment">
          작성하기
        </button>
      </div>
    </div>
  </div>
</template>

<script>
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import { mapState, mapActions } from "vuex";
const boardStore = "boardStore";
const userStore = "userStore";

export default {
  name: "BoardAskDetail",
  props: {
    ask: Object,
  },

  components: {
    ckeditor: CKEditor.component,
  },
  data: () => ({
    editor: ClassicEditor,
    comment: {
      boardId: null,
      content: "<p>답변할 내용을 적어주세요.</p>",
    },
    isAdmin: false,
  }),

  created() {
    this.comment.boardId = this.ask.boardId;
    if (this.userInfo.userId == "admin") this.isAdmin = true;
  },
  computed: {
    ...mapState(userStore, ["userInfo"]),
  },

  methods: {
    ...mapActions(boardStore, ["writeComment"]),
    writeNewComment() {
      this.writeComment(this.comment).then(() => {
        this.$router.go();
      });
    },
  },
};
</script>

<style>
.ck-editor__editable {
  min-height: 200px;
  min-width: 200px;
}
</style>
