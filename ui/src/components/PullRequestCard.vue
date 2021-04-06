<template>
  <div>
    <b-card :border-variant="getIconColor()">
      <b-card-header header-bg-variant="transparent">
        <b-icon
          class="mr-1 xl"
          :icon="getIconType()"
          :variant="getIconColor()"
        />
        <span>{{ pull.title }}</span>
      </b-card-header>
      <b-link target="_blank" :href="pull.link">Github Link</b-link>

      <b-container fluid>
        <div>
           <LabelAndValue v-if="getAgeInHrs() < 24" label="Age" :value="`${getAgeInHrs()} Hours`" />
           <LabelAndValue v-else label="Age" :value="`${getAgeInHrs() % 24 } Days`" />
        </div>
        <div>
           <LabelAndValue v-if="getLastUpdatedInHours() < 24" label="Last Updated" :value="`${getLastUpdatedInHours()} Hours`" />
           <LabelAndValue v-else label="Last Updated" :value="`${getLastUpdatedInHours() % 24 } Days`" />
        </div>

        <LabelAndValue label="Reviewers" :valueList="getReviewerNames()" />
      </b-container>
    </b-card>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { BootstrapVariants } from "@/constants/BootstrapVariants";
import LabelAndValue from "@/components/LabelAndValue.vue";
import { Pull } from "@/store/modules/github/types";

@Component({
  components: {
    LabelAndValue,
  },
})
export default class Header extends Vue {
  @Prop() private pull: Pull
  private ageValueInHrs?: number;
  private lastUpdatedInHrs?: number;

  getAgeInHrs(): number {
    if(this.ageValueInHrs){
      return this.ageValueInHrs
    } else {
      this.ageValueInHrs = this.getTimeDifferenceInHours(this.pull.createdDate);
      return this.ageValueInHrs
    }
  }
  getLastUpdatedInHours(): number {
    if(this.lastUpdatedInHrs){
      return this.lastUpdatedInHrs
    } else {
      this.lastUpdatedInHrs = this.getTimeDifferenceInHours(this.pull.lastUpdatedDate);
      return this.lastUpdatedInHrs
    }
  }
  getTimeDifferenceInHours(dateString: string): number {
    const milliseconds = Date.parse(dateString) - new Date().getUTCDate();
    return Math.floor(milliseconds / 1000 / 60 / 60)
  }
  getReviewerNames(): Array<string> {
    const reviewerNames = this.pull.reviewers.map(reviewer => reviewer.name)
    return reviewerNames.length > 0 ? reviewerNames : ["None"];
  }
  getIconColor(): string {
    const age = this.getAgeInHrs()
    if ((this.getAgeInHrs() > 2 && this.pull.reviewers.length === 0) || this.getAgeInHrs() > 4) {
      return BootstrapVariants.DANGER;
    } else if (
      (this.getAgeInHrs() > 1 && this.pull.reviewers.length === 0) ||
      (this.getAgeInHrs() > 2 && this.pull.reviewers.length !== 0)
    ) {
      return BootstrapVariants.WARNING;
    } else {
      return BootstrapVariants.INFO;
    }
  }

  getIconType(): string {
    if ((this.getAgeInHrs() > 2 && this.pull.reviewers.length === 0) || this.getAgeInHrs() > 4) {
      return "x-circle-fill";
    } else if (
      (this.getAgeInHrs() > 1 && this.pull.reviewers.length === 0) ||
      (this.getAgeInHrs() > 2 && this.pull.reviewers.length !== 0)
    ) {
      return "exclamation-circle-fill";
    } else {
      return "check-circle-fill";
    }
  }
}
</script>
