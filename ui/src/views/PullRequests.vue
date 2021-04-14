<template>
  <div class="pull-request">
    <Title title="Pull Requests" subtitle="ASFA"/>
    <b-container class="bv-example-row mt-1">
      <b-row>
        <b-col>
          <PullRequestCard
            class="pb-1"
            v-for="pull in pulls"
            :key="pull.repoName + pull.pullNumber"
            :pull="pull"
          />
        </b-col>
      </b-row>
    </b-container>
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { State, Action } from "vuex-class";
import PullRequestCard from "@/components/PullRequestCard.vue";
import Title from "@/components/Title.vue";
import { GithubState, Pull } from "@/store/modules/github/types";
const githubNamespace = "github";

@Component({
  components: {
    PullRequestCard,
    Title
  },
})
export default class PullRequests extends Vue {
  @State(githubNamespace) github: GithubState;
  @State("pulls", { namespace: githubNamespace }) pulls: Array<Pull>;
  @Action("getPulls", { namespace: githubNamespace })
  getPulls: () => Promise<void>;

  mounted(): void {
    this.getPulls();
  }
}
</script>
