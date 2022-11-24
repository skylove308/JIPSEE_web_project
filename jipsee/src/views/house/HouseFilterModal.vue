<template>
  <div class="grid grid-rows-[50px_90px_140px_140px] grid-cols-1 gap-10 bg-gray-100 w-450 h-450 border-1">
    <div class="flex items-center justify-center font-bold bg-white text-16 border-b-1 border-b-gray-200">필터</div>
    <div class="grid grid-cols-[200px_250px] grid-rows-[40px_50px] bg-white">
      <div class="flex items-center ml-20 font-bold text-16">주택유형</div>
      <div class="flex items-center font-bold text-16">거래유형</div>
      <div class="grid content-center grid-cols-2 mb-10 ml-20 w-150 justify-items-center">
        <label class="cursor-pointer">
          <input
            type="checkbox"
            name="housetype"
            v-model="filterList.houseTypeList"
            value="아파트"
            @change="searchHouse"
            class="sr-only peer"
            checked
          />
          <div
            class="flex items-center justify-center h-32 bg-white border-yellow-400 hover:brightness-90 w-70 text-14 border-1 peer-checked:border-black peer-checked:bg-yellow-400 focus:ring-offset-0 focus:ring-0"
          >
            아파트
          </div>
        </label>
        <label class="cursor-pointer">
          <input
            type="checkbox"
            name="housetype"
            v-model="filterList.houseTypeList"
            value="원룸"
            @change="searchHouse"
            class="sr-only peer"
            checked
          />
          <div
            class="flex items-center justify-center h-32 bg-white border-l-0 border-yellow-400 w-80 text-14 border-1 peer-checked:border-black hover:brightness-90 peer-checked:bg-yellow-400 focus:ring-offset-0 focus:ring-0"
          >
            원룸
          </div>
        </label>
      </div>
      <div class="grid content-center grid-cols-3 mb-10 w-210 justify-items-center">
        <label class="cursor-pointer">
          <input
            type="checkbox"
            name="dealtype"
            v-model="filterList.dealTypeList"
            value="매매"
            @change="searchHouse"
            class="sr-only peer"
            checked
          />
          <div
            class="flex items-center justify-center h-32 bg-white border-yellow-400 w-70 text-14 border-1 peer-checked:border-black peer-checked:bg-yellow-400 hover:brightness-90 focus:ring-offset-0 focus:ring-0"
          >
            매매
          </div>
        </label>
        <label class="cursor-pointer">
          <input
            type="checkbox"
            name="dealtype"
            v-model="filterList.dealTypeList"
            value="전세"
            @change="searchHouse"
            class="sr-only peer"
            checked
          />
          <div
            class="flex items-center justify-center h-32 bg-white border-l-0 border-yellow-400 w-70 text-14 border-1 peer-checked:border-black peer-checked:bg-yellow-400 hover:brightness-90 focus:ring-offset-0 focus:ring-0"
          >
            전세
          </div>
        </label>
        <label class="cursor-pointer">
          <input
            type="checkbox"
            name="dealtype"
            v-model="filterList.dealTypeList"
            value="월세"
            @change="searchHouse"
            class="sr-only peer"
            checked
          />
          <div
            class="flex items-center justify-center h-32 bg-white border-l-0 border-yellow-400 hover:brightness-90 w-70 text-14 border-1 peer-checked:border-black peer-checked:bg-yellow-400 focus:ring-offset-0 focus:ring-0"
          >
            월세
          </div>
        </label>
      </div>
    </div>
    <div class="grid items-center grid-rows-[40px_40px_60px] bg-white">
      <div class="ml-20 font-bold text-16">가격범위</div>
      <div class="ml-20">
        <input type="range" min="0" max="100" value="0" class="slider w-390" id="myRange" />
      </div>
      <div class="flex justify-center mb-10 ml-20 w-390">
        <input
          type="number"
          placeholder="0만원"
          name="minDealMoney"
          v-model.lazy="filterList.minDealMoney"
          @change="searchHouse"
          class="h-40 w-150"
        />
        <div class="flex items-center justify-center font-bold text-20 w-90">~</div>
        <input
          type="number"
          placeholder="1,000,000만원"
          name="maxDealMoney"
          v-model.lazy="filterList.maxDealMoney"
          @change="searchHouse"
          class="h-40 w-150"
        />
      </div>
    </div>
    <div class="grid items-center grid-rows-[40px_40px_60px] bg-white">
      <div class="ml-20 font-bold text-16">방크기</div>
      <div class="ml-20">
        <input type="range" min="0" max="100" value="0" class="slider w-390" id="myRange" />
      </div>
      <div class="flex justify-center mb-10 ml-20 w-390">
        <input
          type="number"
          placeholder="0m²"
          name="minDealMoney"
          v-model.lazy="filterList.minArea"
          @change="searchHouse"
          class="h-40 w-150"
        />
        <div class="flex items-center justify-center font-bold text-20 w-90">~</div>
        <input
          type="number"
          placeholder="200m²"
          name="maxDealMoney"
          v-model.lazy="filterList.maxArea"
          @change="searchHouse"
          class="h-40 w-150"
        />
      </div>
    </div>
    <div></div>
  </div>
</template>

<script>
import { mapState, mapMutations, mapActions } from "vuex";
const houseStore = "houseStore";
export default {
  props: {
    dongCode: String,
  },
  data() {
    return {
      minDealMoney: null,
      maxDealMoney: null,
    };
  },
  computed: {
    ...mapState(houseStore, ["filterList"]),
  },
  methods: {
    ...mapMutations(houseStore, ["CLEAR_HOUSES_LIST"]),
    ...mapActions(houseStore, ["getHouseList"]),
    searchHouse() {
      this.CLEAR_HOUSES_LIST();
      if (this.dongCode) this.getHouseList(this.filterList);
    },
  },
};
</script>

<style>
input[type="number"]::-webkit-inner-spin-button,
input[type="number"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  -moz-appearance: none;
  appearance: none;
  margin: 0;
}
</style>
