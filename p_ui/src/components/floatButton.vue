
<template>
  <div class="float-item-container">
    <el-dropdown
      trigger="click"
      ref="dropDown"
      v-drag
      class="float-item"
      draggable="false"
      placement="top"
    >
      <el-button icon="el-icon-menu" circle class="float-button"></el-button>
      <el-dropdown-menu slot="dropdown">
        <el-dropdown-item @click.native="toBlogEdit">
          <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
          <span>发布博客</span>
          <!-- </el-tooltip> -->
        </el-dropdown-item>
        <el-dropdown-item
          @click.native="toBlogEdit"
          :disabled="disabled ? true : false"
        >
          <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
          <span>编辑博客</span>
          <!-- </el-tooltip> -->
        </el-dropdown-item>
        <el-dropdown-item @click.native="toMyBlogs">
          <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
          <span>我的博客</span>
          <!-- </el-tooltip> -->
        </el-dropdown-item>
        <el-dropdown-item @click.native="addWebRecord">
          <!-- <el-tooltip class="item" effect="dark" content="" placement="left"> -->
          <span>修改记录</span>
          <!-- </el-tooltip> -->
        </el-dropdown-item>
      </el-dropdown-menu>
    </el-dropdown>
    <el-dialog> </el-dialog>
  </div>
</template>
<script >
import { createWebRecord } from "@/api/web";

export default {
  data() {
    return {
      webRecord: {
        time: "",
        operation: "",
        type: "",
      },
      blogData: this.$store.state.blogData,
      disabled: true,
    };
  },
  watch: {
    "$store.state.blogData": {
      handler(val, oldVal) {
        val
          ? (this.disabled = false && (this.blogData = val))
          : (this.disabled = true);
      },
      deep: true,
    },
  },
  methods: {
    addWebRecord() {
      createWebRecord(this.webRecord).then((res) => {
        if (res == "1") {
          this.$message({
            message: "提交成功",
            type: "success",
            offset: 0,
          });
        } else {
          this.$message({
            message: "提交失败" + res,
            type: "error",
            offset: 0,
          });
        }
      });
    },
    toBlogEdit() {
      this.blogData = this.$store.state.blogData;
      this.$router.push({
        path: "/BlogEditor",
        query: { blogData: this.blogData },
      });
    },
    toMyBlogs() {},
  },
  mounted() {
    let dropDown = document.querySelector(".float-item");
    dropDown.style.right = "50px";
    dropDown.style.bottom = "50px";
  },
};
</script>
<style lang="scss">
.float-item {
  position: fixed;
  cursor: pointer;
  .el-button.is-circle {
    padding: 10px;
    background: rgba(17, 17, 17, 0.8);
    color: #fff;
    i {
      font-size: 20px;
    }
  }
  .el-button.is-circle:hover {
    // background: #fff;
    border: 1px solid #dcdfe6;
    background: rgba(17, 17, 17, 0.8);
    color: #fff;
    // color: #606266;
  }
}
.el-dropdown-menu {
  background-color: rgba(29, 32, 33, 0.6);
  box-shadow: 0 2px 12px 0 #000000;
  border: none;
  padding: 0px;
  .el-dropdown-menu__item {
    background-color: transparent;
    span {
      color: #fff;
    }
    padding: 0px 12px;
  }
  .el-dropdown-menu__item:hover {
    background-color: rgba(54, 55, 56, 0.9);
    span {
      color: #fff;
    }
  }
}
</style>