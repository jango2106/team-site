<template>
  <b-row>
    <b-col class="p-0">
      <b-card-text class="text-right">{{ label }}:</b-card-text>
    </b-col>
    <b-col>
      <b-card-text class="text-left">
        <slot name="content" />
      </b-card-text>
    </b-col>
  </b-row>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class CardLabelAndSlot extends Vue {
  @Prop({required: true}) private label!: string;
}
</script>
