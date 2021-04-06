import { ActionTree } from "vuex";
import axios from "axios";
import { RootState } from "@/store/types";
import { GithubState, Pull } from "./types";

export const actions: ActionTree<GithubState, RootState> = {
  async getPulls({ commit, rootState }): Promise<any> {
    try {
      const response = await axios({
        url: `${rootState.baseApiUrl}github/pulls`,
      });
      const pulls: Array<Pull> = response?.data;
      commit("setPulls", pulls);
    } catch (error) {
      console.error(error);
    }
  },
};
