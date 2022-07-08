<template>
  <div>
    <div>
      <random-slogan></random-slogan>
    </div>
    <button class="bt-login">LOG IN</button>
  </div>
</template>

<script>
import RandomSlogan from "@/components/RandomSlogan.vue";

export default {
  components: {
    RandomSlogan,
  },
  data() {
    return {};
  },
};
</script>
