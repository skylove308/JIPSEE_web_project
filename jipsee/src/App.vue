<template>
  <div
    id="app"
    class="grid grid-rows-[65px_auto] grid-cols-1 h-screen w-full notosans text-black">
    <the-header></the-header>
    <router-view />
  </div>
</template>
<script>
import TheHeader from "@/views/TheHeader";
export default {
  components: {
    TheHeader,
  },
};
</script>
