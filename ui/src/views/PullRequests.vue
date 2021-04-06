<template>
  <div class="pull-request">
    <b-container class="bv-example-row">
      <b-row>
        <b-col>1 of 3</b-col>
        <b-col>
          <PullRequestCard v-for="pull in pulls" :key="pull.repoName + pull.pullNumber"
            :pull="pull"
          />
        </b-col>
        <b-col>3 of 3</b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { State, Action, Getter } from "vuex-class";
import PullRequestCard from "@/components/PullRequestCard.vue";
import { GithubState, Pull } from "@/store/modules/github/types";
const githubNamespace = "github";

@Component({
  components: {
    PullRequestCard,
  },
})
export default class PullRequests extends Vue {
  @State(githubNamespace) github: GithubState;
  @State("pulls", {namespace: githubNamespace}) pulls: Array<Pull>
  @Action("getPulls", {namespace: githubNamespace}) getPulls: Promise<any>
  mounted() {
    this.getPulls();
  }
}
</script>
