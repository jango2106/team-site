<template>
  <ViewWrapper>
    <div slot="content" class="pull-request">
      <Title title="Pull Requests" subtitle="ASFA"/>
      <div class="d-flex justify-content-center flex-wrap">
        <PullRequestCard
          class="m-2 pull-card"
          v-for="pull in pulls"
          :key="pull.repoName + pull.pullNumber"
          :pull="pull"
        />
      </div>
    </div>
  </ViewWrapper>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";
import { State, Action } from "vuex-class";
import PullRequestCard from "@/components/PullRequestCard.vue";
import Title from "@/components/Title.vue";
import ViewWrapper from "@/components/ViewWrapper.vue";
import { GithubState, Pull } from "@/store/modules/github/types";
const githubNamespace = "github";

@Component({
  components: {
    PullRequestCard,
    Title,
    ViewWrapper
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
<style scoped>
/* Extra small devices (portrait phones, less than 576px) */
@media (max-width: 575.98px) {
  .pull-card {
    min-width: 85vw;
    max-width: 100vw;
    min-height: 20vh;
    max-height: 40vh;
  }
}

/* Small devices (landscape phones, 576px and up) */
@media (min-width: 576px) and (max-width: 767.98px) {
  .pull-card {
    min-width: 85vw;
    max-width: 85vw;
    min-height: 20vh;
    max-height: 40vh;
  }
}

/* Medium devices (tablets, 768px and up) */
@media (min-width: 768px) and (max-width: 991.98px) { 
  .pull-card {
    min-width: 40vw;
    max-width: 40vw;
    min-height: 20vh;
    max-height: 40vh;
  }
}

/* Large devices (desktops, 992px and up) */
@media (min-width: 992px) and (max-width: 1199.98px) {
  .pull-card {
    min-width: 35vw;
    max-width: 35vw;
    min-height: 20vh;
    max-height: 40vh;
  }
}

/* Extra large devices (large desktops, 1200px and up) */
@media (min-width: 1200px) {
  .pull-card {
    min-width: 25vw;
    max-width: 25vw;
    min-height: 20vh;
    max-height: 40vh;
  }
}

</style>

