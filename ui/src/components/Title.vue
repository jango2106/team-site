<template>
  <div class="text-left">
    <h1>{{title}}</h1>
    <h4 v-if="subtitle" class="text-secondary">{{subtitle}}</h4>
  </div>
</template>

<script lang="ts">
import { Component, Prop, Vue } from "vue-property-decorator";

@Component({})
export default class Header extends Vue {
  @Prop() private title: string;
  @Prop() private subtitle?: string;
}
</script>
