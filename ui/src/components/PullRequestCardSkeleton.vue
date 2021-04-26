<template>
  <b-card class="mt-0">
    <b-card-header header-bg-variant="transparent" class="p-0 m-0">
      <b-skeleton class="mx-auto mb-3"/>
    </b-card-header>
    <b-skeleton class="mx-auto mb-2 mt-2" width="20%"/>
    <b-container>
      <div>
        <b-skeleton class="mx-auto mb-2" width="25%"/>
        <b-skeleton class="mx-auto mb-2" width="50%"/>
      </div>
      <b-skeleton class="mx-auto" width="40%"/>
    </b-container>
  </b-card>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({})
export default class PullRequestCardSkeleton extends Vue {
}
</script>
