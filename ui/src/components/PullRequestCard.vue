<template>
  <b-card :border-variant="getIconColor()">
    <b-card-header header-bg-variant="transparent" class="p-0 pb-1">
      <b-icon
        class="mr-1 xl"
        :icon="getIconType()"
        :variant="getIconColor()"
      />
      <span>{{ pull.title }}</span>
    </b-card-header>
    <b-link target="_blank" :href="pull.link">Github Link</b-link>

    <b-container>
      <div>
        <CardLabelAndValue
          v-if="getAgeInHrs() < 24"
          label="Age"
          :value="`${getAgeInHrs()} Hours`"
        />
        <CardLabelAndValue
          v-else
          label="Age"
          :value="`${Math.floor(getAgeInHrs() / 24)} Days`"
        />
      </div>
      <div>
        <CardLabelAndValue
          v-if="getLastUpdatedInHours() < 24"
          label="Last Updated"
          :value="`${getLastUpdatedInHours()} Hours`"
        />
        <CardLabelAndValue
          v-else
          label="Last Updated"
          :value="`${Math.floor(getLastUpdatedInHours() / 24)} Days`"
        />
      </div>
      <CardLabelAndSlot v-if="pull.reviewers.length" label="Reviewers">
        <div slot="content">
          <div v-for="reviewer in pull.reviewers" :key="reviewer.id">
            <b-avatar class="mr-1" :src="reviewer.avatar_url" size="1.5rem" />
            <span>{{ reviewer.name }}</span>
          </div>
        </div>
      </CardLabelAndSlot>
      <CardLabelAndValue v-else label="Reviewers" value="None" />
    </b-container>
  </b-card>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";
import { BootstrapVariants } from "@/constants/BootstrapVariants";
import CardLabelAndValue from "@/components/CardLabelAndValue.vue";
import CardLabelAndSlot from "@/components/CardLabelAndSlot.vue";
import { Pull } from "@/store/modules/github/types";

@Component({
  components: {
    CardLabelAndValue,
    CardLabelAndSlot,
  },
})
export default class Header extends Vue {
  @Prop({required: true}) private pull!: Pull;
  private ageValueInHrs?: number;
  private lastUpdatedInHrs?: number;

  getAgeInHrs(): number {
    if (this.ageValueInHrs) {
      return this.ageValueInHrs;
    } else {
      this.ageValueInHrs = this.getTimeDifferenceInHours(this.pull.createdDate);
      return this.ageValueInHrs;
    }
  }
  getLastUpdatedInHours(): number {
    if (this.lastUpdatedInHrs) {
      return this.lastUpdatedInHrs;
    } else {
      this.lastUpdatedInHrs = this.getTimeDifferenceInHours(
        this.pull.lastUpdatedDate
      );
      return this.lastUpdatedInHrs;
    }
  }
  getTimeDifferenceInHours(dateString: string): number {
    const milliseconds = new Date().getTime() - Date.parse(dateString);
    return Math.floor(milliseconds / 1000 / 60 / 60);
  }
  getIconColor(): string {
    const age = this.getAgeInHrs();
    if ((age >= 48 && this.pull.reviewers.length === 0) || age >= 52) {
      return BootstrapVariants.DANGER;
    } else if (
      (age >= 24 && this.pull.reviewers.length === 0) ||
      (age >= 48 && this.pull.reviewers.length !== 0)
    ) {
      return BootstrapVariants.WARNING;
    } else {
      return BootstrapVariants.INFO;
    }
  }

  getIconType(): string {
    const age = this.getAgeInHrs();
    if ((age >= 48 && this.pull.reviewers.length === 0) || age >= 52) {
      return "x-circle-fill";
    } else if (
      (age >= 24 && this.pull.reviewers.length === 0) ||
      (age >= 48 && this.pull.reviewers.length !== 0)
    ) {
      return "exclamation-circle-fill";
    } else {
      return "check-circle-fill";
    }
  }
}
</script>
