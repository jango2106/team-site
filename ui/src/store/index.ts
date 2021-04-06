import Vue from "vue";
import Vuex, { StoreOptions } from "vuex";
import { RootState } from "./types";
import { github } from "./modules/github/index";

Vue.use(Vuex);

const store: StoreOptions<RootState> = {
  state: {
    version: "0.0.1",
  },
  modules: {
    github,
  },
};

export default new Vuex.Store<RootState>(store);
