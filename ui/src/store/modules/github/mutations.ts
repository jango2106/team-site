import { MutationTree } from "vuex";
import { GithubState, Pull } from "./types";

export const mutations: MutationTree<GithubState> = {
  setPulls(state, payload: Array<Pull>) {
    state.pulls = payload.sort((a, b) => {
      return a.repoName.localeCompare(b.repoName) || a.pullNumber - b.pullNumber;
    })
  },
};
