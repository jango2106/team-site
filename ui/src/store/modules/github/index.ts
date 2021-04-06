import { Module } from "vuex";
import { getters } from "./getters";
import { actions } from "./actions";
import { mutations } from "./mutations";
import { RootState } from "../../types";
import { GithubState } from "./types";

export const state: GithubState = {
  pulls: [],
};

const namespaced = true;

export const github: Module<GithubState, RootState> = {
  namespaced,
  state,
  getters,
  actions,
  mutations,
};
