<template>
  <div class="blog-time-line">
    <el-timeline>
      <template v-for="(item, index) in timeLineData">
        <el-timeline-item :timestamp="item.time" placement="top" :key="index">
          <el-card>
            <h4>{{ item.operation }}</h4>
            <p>{{ item.submit }}</p>
          </el-card>
        </el-timeline-item>
      </template>
    </el-timeline>
  </div>
</template>
<script>
export default {
  data() {
    return {
      timeLineData: [
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
      ],
    };
  },
};
</script>
<style lang="less">
.blog-time-line {
  height: 100%;
  overflow: hidden;
  .el-timeline-item__wrapper {
    width: calc(100% - 50px);
    .el-timeline-item__timestamp {
      color: #fff;
    }
  }
  .el-timeline {
    ::-webkit-scrollbar-thumb {
      //   background: rgb(85 80 80 / 60%);
    }
    overflow: auto;
    height: 100%;
    padding: 20px 0px 0px 10px;
    .el-card {
      border: none;
      background-color: transparent;
      box-shadow: 0 2px 12px 0 #000000;
      .el-card__body {
        padding: 15px;

        h4 {
          margin: 0px;
          color: #fff;
        }
        p {
          margin: 10px 0 0 0;
          color: #fff;
        }
      }
    }
  }
}
</style>