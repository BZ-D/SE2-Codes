<template>
  <v-app>
    <div>
      <router-view />
    </div>
  </v-app>
</template>

<script lang="ts">
import Vue from "vue";

export default Vue.extend({
  name: "App",

  components: {},

  data: () => ({
    //
  })
});
</script>

<style lang="scss">
a {
  text-decoration: none;
}
</style>
