<template>
  <div class="blog-time-line">
    <el-timeline>
      <template v-for="(item, index) in timeLineData">
        <el-timeline-item
          :timestamp="getTimeStamp(item.recordTime)"
          placement="top"
          :key="index"
        >
          <el-card>
            <div class="card-line">
              <p style="margin: 0px 5px 0px 0px">
                {{ item.recordType + "博客" }}
              </p>
              <p style="margin-top: 0px" @click="jumpToBlog(item.blogId)">
                {{ item.blogName }}
              </p>
            </div>
            <p>{{ userName }} 提交于 {{ item.recordTime }}</p>
          </el-card>
        </el-timeline-item>
      </template>
    </el-timeline>
  </div>
</template>
<script>
import { getBlogRecord, getBlogByBlogId } from "@/api/blog";
export default {
  data() {
    return {
      timeLineData: [
        {
          time: "2022-10-20",
          operation: "新增博客aaaaa",
          submit: "Tom 提交于2022-10-20:20:18",
        },
      ],
      userName: this.$store.state.userName,
      params: {
        userId: this.$store.state.userId,
        startRow: 0,
        pageSize: 10,
      },
    };
  },
  methods: {
    getTimeStamp(recordTime) {
      if (recordTime) return recordTime.substring(0, 10);
      return null;
    },

    //获取博客操作记录
    getBlogTimeLineData() {
      getBlogRecord(this.params)
        .then((res) => {
          console.log("博客操作记录", res);
          if (res.data) {
            this.timeLineData = [];
            res.data.forEach((e) => {
              this.timeLineData.push(e);
            });
            // console.log("SSSS", this.timeLineData);
          }
        })
        .catch((err) => {
          this.$message({
            message: "获取博客操作记录失败" + err,
            type: "error",
            offset: 0,
          });
        });
    },
    //博客跳转
    jumpToBlog(blogId) {
      getBlogByBlogId({ blogId: blogId }).then((res) => {
        console.log("博客数据", res);
        this.$router.push({
          path: "/BlogDisplay",
          query: { blogData: res.data },
        });
      });
    },
  },
  mounted() {
    this.getBlogTimeLineData();
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
      .card-line {
        display: flex;
        justify-content: space-around;
        align-items: center;
        a {
          color: #fff;
          margin-left: 5px;
          text-overflow: ellipsis;
          overflow: hidden;
          white-space: nowrap;
        }
      }
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