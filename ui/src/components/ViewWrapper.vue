<template>
  <div class="m-4">
    <slot name="content" />
  </div>
</template>

<script lang="ts">
import { Component, Vue } from "vue-property-decorator";

@Component({})
export default class ViewWrapper extends Vue {}
</script>
