<!--
 * @Description: 网站更新记录
-->
<template>
  <div class="update-time-line">
    <el-timeline>
      <div v-for="(item, index) in timeLineData" :key="index">
        <el-timeline-item :timestamp="item.updateTime || item.createTime" placement="top">
          <el-card>
            <div class="card-line">
              <p style="margin: 0 5px 0 0">
                {{ item.updateTime ? '更新' : '发布' }}
              </p>
            </div>
          </el-card>
        </el-timeline-item>
      </div>
    </el-timeline>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listBlog, getBlogById } from '@/api/blog.ts';
import useUserStore from '@/store/modules/user';

const userStore = useUserStore();
const timeLineData = ref([] as any);

async function getList() {
  const { code, msg, data } = (await listBlog({ userId: userStore.userId })) as any;
  if (code === 200 && data) {
    timeLineData.value = data.list;
  }
}

function viewBlog() {}

onMounted(() => {
  getList();
});
</script>
<style lang="scss">
@include theme() {
  .update-time-line {
    height: 100%;
    overflow: hidden;
    .el-timeline-item__wrapper {
      width: calc(100% - 50px);
      .el-timeline-item__timestamp {
        color: get('font-color');
      }
    }
    .el-timeline {
      ::-webkit-scrollbar-thumb {
        //   background: rgb(85 80 80 / 60%);
      }
      overflow: auto;
      height: 100%;
      padding: 20px 0 0 10px;
      .el-card {
        border: none;
        background: transparent;
        box-shadow: get('box-shadow');
        .card-line {
          display: flex;
          justify-content: center;
          align-items: center;
          a {
            color: get('font-color');
            margin-left: 5px;
            text-overflow: ellipsis;
            overflow: hidden;
            white-space: nowrap;
          }
        }
        .el-card__body {
          padding: 15px;

          h4 {
            margin: 0;
            color: get('font-color');
          }
          p {
            margin: 10px 0 0 0;
            color: get('font-color');
          }
        }
      }
    }
  }
}
</style>
