<template>
  <b-row>
    <b-col class="p-0">
      <b-card-text class="text-right">{{ label }}:</b-card-text>
    </b-col>
    <b-col>
      <b-card-text
        v-for="item in getValues()"
        v-bind:key="item"
        class="text-left"
        >{{ item }}</b-card-text
      >
    </b-col>
  </b-row>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class Header extends Vue {
  @Prop() private label!: string;
  @Prop() private value?: string;
  @Prop() private valueList?: string[];

  getValues(): string[] {
    return this.valueList || [this.value];
  }
}
</script>
