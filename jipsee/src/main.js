import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import "../dist/output.css";

/* import the fontawesome core */
import { library } from "@fortawesome/fontawesome-svg-core";

/* import font awesome icon component */
import { FontAwesomeIcon } from "@fortawesome/vue-fontawesome";

/* import specific icons */
import { faMagnifyingGlass, faHeart, faAnglesLeft, faAnglesRight } from "@fortawesome/free-solid-svg-icons";
import { faHeart as faHeartRegular } from "@fortawesome/free-regular-svg-icons";

/* add icons to the library */
library.add(faMagnifyingGlass);
library.add(faHeart);
library.add(faHeartRegular);
library.add(faAnglesLeft);
library.add(faAnglesRight);

/* add font awesome icon component */
Vue.component("font-awesome-icon", FontAwesomeIcon);

Vue.config.productionTip = false;

new Vue({
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
