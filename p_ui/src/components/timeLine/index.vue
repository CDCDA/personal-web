<!--
 * @Description:  时间轴
-->
<template>
  <div class="timeline">
    <div class="timeline-main">
      <div class="entries">
        <div class="entry" v-for="item in goalList">
          <div class="title">
            <div class="title-text">{{ item.date }}</div>
          </div>
          <div class="body" v-if="item.goal">
            <p>{{ item.goal }}</p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup lang="ts">
import { ref } from 'vue';
const goalList = ref([
  {
    date: '2023-8',
    goal: '目标1'
  },
  {
    date: '2023-9',
    goal: ''
  },
  {
    date: '2023-10',
    goal: ''
  },
  {
    date: '2023-11',
    goal: '目标2'
  },
  {
    date: '2023-12',
    goal: '目标3'
  },
  {
    date: '2024-1',
    goal: '目标4'
  }
] as any);
</script>
