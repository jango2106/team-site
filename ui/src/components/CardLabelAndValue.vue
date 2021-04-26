<template>
  <b-row>
    <b-col class="p-0">
      <b-card-text class="text-right">{{ label }}:</b-card-text>
    </b-col>
    <b-col>
      <b-card-text class="text-left">
        {{ value }}
      </b-card-text>
    </b-col>
  </b-row>
</template>
<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component
export default class CardLabelAndValue extends Vue {
  @Prop({required: true}) private label!: string;
  @Prop({required: true}) private value!: string;
}
</script>
