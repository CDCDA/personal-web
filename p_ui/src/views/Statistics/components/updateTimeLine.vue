<template>
  <div class="update-time-line">
    <el-timeline>
      <div v-for="(item, index) in timeLineData" :key="index">
        <el-timeline-item
          :timestamp="getDateStr(item.recordTime)"
          placement="top"
        >
          <el-card>
            <div class="card-line">
              <p style="margin: 0px 5px 0px 0px">
                {{ item.recordOperation }}
              </p>
              <p style="margin-top: 0px" @click="jumpToBlog(item.blogId)">
                {{ item.blogName }}
              </p>
            </div>
            <p>{{ userName }} 提交于 {{ item.recordTime }}</p>
          </el-card>
        </el-timeline-item>
      </div>
    </el-timeline>
  </div>
</template>
<script>
import { createWebRecord, deleteWebRecord, getWebRecord } from "@/api/web";
export default {
  data() {
    return {
      timeLineData: [],
      userName: this.$store.state.userName,
      params: {
        // userId: this.$store.state.userId,
        startRow: 0,
        pageSize: 10,
      },
    };
  },
  methods: {
    getDateStr(recordTime) {
      if (recordTime) return recordTime.substring(0, 10);
      return null;
    },

    //获取网站操作记录
    getWebimeLineData() {
      getWebRecord(this.params)
        .then((res) => {
          console.log("博客操作记录", res);
          if (res.data) {
            this.timeLineData = [];
            res.data.forEach((e) => {
              this.timeLineData.push(e);
            });
            console.log("SSSS", this.timeLineData);
          }
        })
        .catch((err) => {
          this.$message({
            message: "获取网站修改记录失败" + err,
            type: "error",
            offset: 0,
          });
        });
    },
  },
  mounted() {
    this.getWebimeLineData();
  },
};
</script>
<style lang="less">
.update-time-line {
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
        justify-content: center;
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