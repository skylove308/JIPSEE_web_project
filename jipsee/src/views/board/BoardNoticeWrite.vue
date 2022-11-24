<template>
  <div class="flex flex-col mt-50">
    <input
      type="text"
      name="id"
      placeholder="제목을 입력해주세요"
      v-model="notice.title"
      class="w-full mb-15" />
    <ckeditor :editor="editor" v-model="notice.content" />
    <button
      class="mt-20 font-semibold text-white bg-yellow-700 w-100 h-50 place-self-end"
      @click="writeNewNotice">
      작성하기
    </button>
  </div>
</template>

<script>
import ClassicEditor from "@ckeditor/ckeditor5-build-classic";
import CKEditor from "@ckeditor/ckeditor5-vue2";
import { mapActions } from "vuex";
const boardStore = "boardStore";

export default {
  name: "CKEditor",
  components: {
    ckeditor: CKEditor.component,
  },
  data: () => ({
    editor: ClassicEditor,
    notice: {
      userId: "admin",
      title: null,
      content: "<p>공지할 내용을 적어주세요.</p>",
    },
  }),
  methods: {
    ...mapActions(boardStore, ["writeNoticeAdmin"]),
    writeNewNotice() {
      console.log(this.content);
      console.log(typeof this.content);
      this.writeNoticeAdmin(this.notice);
    },
  },
};
</script>

<style>
.ck-editor__editable {
  min-height: 400px;
  min-width: 1200px;
}
</style>
