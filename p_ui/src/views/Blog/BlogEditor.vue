<template>
  <div class="mavon-editor-container page-main">
    <div class="mavon-editor-main">
      <div class="blog-editor-header">
        <el-button size="small" @click="contentSave" class="bt-save"
          >保存</el-button
        >
        <el-input
          placeholder="请输入标题"
          v-model="blogData.blogTitle"
        ></el-input>
        <el-button size="small" @click="releaseConfirm" class="bt-release"
          >发布</el-button
        >
      </div>
      <mavon-editor
        ref="md"
        v-model="blogData.blogText"
        :ishljs="editorOptions.ishljs"
        :defaultOpen="editorOptions.defaultOpen"
        :editable="editorOptions.editable"
        @change="updateDoc"
        @save="contentSave"
        @htmlCode="getHtml"
        codeStyle="atelier-cave-dark"
        toolbarsBackground="#191D1E"
        previewBackground="#191D1E"
        :toolbarsFlag="editorOptions.toolbarsFlag"
        :subfield="editorOptions.subfield"
      ></mavon-editor>
      <div class="add-tags">
        <div class="common-used-tags">
          <span class="tag-label">常用标签</span>
          <template v-for="(item, i) in commonLabelList">
            <el-button
              type="info"
              :key="i"
              size="mini"
              v-if="item"
              icon="el-icon-plus"
              class="icon-delete"
              @mouseenter.native="addTagOver(i)"
              @mouseleave.native="addTagLeave"
              @click="addTag(i)"
              >{{ item }}</el-button
            >
          </template>
        </div>
        <div class="custom-tags">
          <span class="tag-label">添加标签</span>
          <el-input
            v-model="customTag"
            placeholder="请输入"
            :maxlength="10"
            :clearable="true"
            class="tag-input"
          ></el-input>
          <el-button size="mini" @click="addCustomTag"
            ><i class="el-icon-plus">添加</i></el-button
          >
        </div>
        <div class="tags-list">
          <span class="tag-label">标签列表</span>
          <template v-for="(item, i) in labelList">
            <el-button
              type="info"
              :key="i"
              size="mini"
              v-if="item"
              @mouseenter.native="delTagOver(i)"
              @mouseleave.native="delTagLeave"
              >{{ item }}
              <i
                class="el-icon-delete icon-delete"
                v-if="isDeleteShow[i]"
                @click="deleteTag(i)"
              ></i
            ></el-button>
          </template>
        </div>
        <div class="add-type" v-if="isShowType">
          <span class="tag-label">分类选择</span>
          <el-cascader
            v-model="typeValue"
            :options="typeList"
            @change="selectChange"
          ></el-cascader>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import { createBlog, createBlogRecord } from "@/api/blog";
import Blog from "./Blog.vue";
export default {
  components: { Blog },
  data() {
    return {
      isShowType: true,
      typeValue: [],
      isDeleteShow: [],
      isAddShow: [],
      customTag: "",
      blogData: {
        blogTitle: "",
        blogText: "",
        blogCreateTime: "",
        blogUpdateTime: "",
        blogType: "",
        blogLabel: [],
        blogId: null,
        userId: this.$store.state.userId,
      },
      typeList: [
        {
          label: "前端",
          value: "1",
          children: [
            {
              label: "html",
              value: "1-1",
            },
            {
              label: "css",
              value: "1-2",
            },
            {
              label: "javascript",
              value: "1-3",
            },
            {
              label: "vue",
              value: "1-4",
            },
          ],
        },
        {
          label: "后端",
          value: "2",
          children: [
            {
              label: "springboot",
              value: "2-1",
            },
            {
              label: "tomcat",
              value: "2-2",
            },
            {
              label: "servelet",
              value: "2-3",
            },
          ],
        },
        {
          label: "数据库",
          value: "3",
          children: [
            {
              label: "mysql",
              value: "3-1",
            },
            {
              label: "dbeaver",
              value: "3-2",
            },
          ],
        },
        {
          label: "问题记录",
          value: "2",
          children: [
            {
              label: "springboot",
              value: "2-1",
            },
            {
              label: "tomcat",
              value: "2-2",
            },
            {
              label: "servelet",
              value: "2-3",
            },
          ],
        },
      ],
      editorOptions: {
        ishljs: true, //代码高亮
        defaultOpen: "",
        editable: true,
        toolbarsFlag: true,
        subfield: true, //双栏
      },
      labelList: [],
      commonLabelList: ["vue", "html", "css", "springboot", "javascript"],
    };
  },
  methods: {
    updateDoc(value, render) {
      // render 为 markdown 解析后的结果
    },
    selectChange(val) {
      console.log("AADD", this.typeValue);
    },
    contentSave(value) {
      //保存到localstorage
      localStorage.setItem("blogData", JSON.stringify(this.blogData));
      localStorage.setItem("labelList", JSON.stringify(this.labelList));
      localStorage.setItem("typeValue", JSON.stringify(this.typeValue));
      this.$message({
        message: "保存成功",
        type: "success",
        offset: 700,
      });
    },
    getHtml(value) {
      console.log("value");
    },
    delTagOver(i) {
      this.isDeleteShow = [];
      this.isDeleteShow[i] = true;
    },
    delTagLeave() {
      this.isDeleteShow = [];
    },
    deleteTag(i) {
      this.labelList.splice(i, 1, "");
    },
    addTagOver(i) {
      this.isAddShow = [];
      this.isAddShow[i] = true;
    },
    addTagLeave() {
      this.isAddShow = [];
    },
    addTag(i) {
      this.labelList.push(this.commonLabelList[i]);
    },
    addCustomTag() {
      this.labelList.push(this.customTag);
    },
    saveBlog() {},
    releaseConfirm() {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.releaseBlog();
        })
        .catch(() => {
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
    releaseBlog() {
      let data = this.blogData;
      data.blogCreateTime = this.dateTimeToString(new Date());
      data.blogUpdateTime = data.blogCreateTime;
      data.blogLabel = "";
      this.labelList.forEach((label, i) => {
        if (i != this.labelList.length) data.blogLabel += label + ",";
      });
      let list = this.typeList;
      let blogType = "";
      if (this.typeValue.length > 0)
        this.typeValue.forEach((type) => {
          list.forEach((e) => {
            if (e.value == type) {
              blogType += e.label + "/";
              list = e.children;
            }
          });
        });
      blogType = blogType.substring(0, blogType.length - 1);
      data.blogType = blogType;
      data.blogLabel = data.blogLabel.substring(0, data.blogLabel.length - 1);
      data.blogText = data.blogText;
      data.blogMk = this.$refs.md.d_render;
      data.blogLabelList = null;
      // console.log("QQQQ", data);
      createBlog(data).then((res) => {
        localStorage.clear();
        if (res == "1") {
          this.$message({
            message: "发布成功",
            type: "success",
            offset: 700,
          });
        } else {
          this.$message({
            message: "发布失败" + res,
            type: "error",
            offset: 700,
          });
        }
      });
      // console.log("博客编辑", data, this.$refs.md.d_render);
    },
    async reload() {
      this.isShowType = false;
      await this.$nextTick();
      this.isShowType = true;
    },
    findTypeValue(list, type, i) {
      list.forEach((t) => {
        if (t.label == type[i]) {
          this.typeValue.push(t.value);
          if (t.children && type[++i]) {
            this.findTypeValue(t.children, type, i);
          }
        }
      });
    },
  },
  mounted() {
    if (
      localStorage.getItem("blogData") &&
      localStorage.getItem("labelList") &&
      localStorage.getItem("typeValue") &&
      !this.$route.query.blogData
    ) {
      this.blogData = JSON.parse(localStorage.getItem("blogData"));
      this.labelList = JSON.parse(localStorage.getItem("labelList"));
      this.typeValue = JSON.parse(localStorage.getItem("typeValue"));
    }
    if (this.$route.query.blogData) {
      this.blogData = this.$route.query.blogData;
      this.blogData.blogLabelList.forEach((label) => {
        this.labelList.push(label.name);
      });
      this.typeValue = [];
      let type = this.blogData.blogType.split("/");
      this.findTypeValue(this.typeList, type, 0);
      this.reload(this.isShowType);
    }
  },
};
</script>
<style lang="less">
.mavon-editor-container.page-main {
  span,
  p,
  textarea,
  button,
  div {
    color: #fff;
  }
  .v-note-wrapper .v-note-op .v-left-item .op-icon,
  .v-note-wrapper .v-note-op .v-right-item .op-icon {
    color: #fff;
  }
  .v-note-wrapper.markdown-body.shadow {
    box-shadow: 0 2px 12px 0 #000000 !important;
    background-color: transparent;
  }
  .v-note-panel,
  .el-button,
  .el-input__inner,
  .add-tags {
    background-color: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    border: none;
    color: #fff;
    text-align: center;
  }
  .v-note-op {
    border: none;
    background: transparent !;
    border-bottom: 1px solid black;
  }
  .content-input-wrapper,
  .content-input,
  .auto-textarea-input,
  .v-show-content {
    background-color: transparent !important;
  }
  display: flex;
  flex-direction: column;
  width: 90%;
  padding: 30px 5%;
  height: calc(100% - 55px);
  .mavon-editor-main {
    .markdown-body {
      height: 500px;
    }
  }
  .add-tags {
    width: calc(100% - 20px);
    text-align: left;
    margin-top: 5px;
    // margin-bottom: 20px;
    border-radius: 3px;
    padding: 10px;
  }
  .blog-editor-footer {
    margin-top: 15px;
  }
  .tags-list,
  .add-type {
    margin: 15px 0 15px 10px;
  }
  .common-used-tags,
  .custom-tags {
    margin: 15px 0 0px 10px;
  }
  .icon-delete {
    margin-left: 5px;
  }
  .tag-label {
    margin: 0 5px 0 10px;
    width: 50px;
    white-space: nowrap;
  }
  .tag-input {
    width: 100px;
  }
  ::v-deep .el-input__inner {
    height: 30px;
    line-height: 30px;
  }
  .blog-editor-header {
    display: flex;
    margin-bottom: 5px;
    .bt-save {
      margin-right: 5px;
    }
    .bt-release {
      margin-left: 5px;
    }
  }
  //编辑器工具栏
  .op-icon.dropdown-wrapper.dropdown .popup-dropdown {
    background-color: transparent;
    box-shadow: 0 2px 12px 0 #000000;
    border: none;
    .dropdown-item {
      background-color: rgba(29, 32, 33, 1);
      span {
        color: #fff;
      }
    }
  }
  .v-note-wrapper .v-note-op .op-icon:hover {
    background: #e0dede;
    color: black;
  }
  .v-note-wrapper .v-note-op .v-right-item .op-icon.selected {
    background-color: rgba(29, 32, 33, 1);
  }
  //代码块
  .markdown-body .highlight pre,
  .markdown-body pre {
    background: transparent;
    box-shadow: 0 2px 12px 0 #000000;
  }
}
</style>
