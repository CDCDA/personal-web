<template>
  <div class="mavon-editor-container page-main">
    <div class="mavon-editor-main">
      <div class="blog-editor-header">
        <el-button size="small" @click="saveBlog">保存</el-button>
        <el-input
          placeholder="请输入标题"
          v-model="blogData.blogTitle"
        ></el-input>
        <el-button size="small" @click="releaseBlog">发布</el-button>
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
        <div class="add-type">
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
import { createBlog } from "@/api/blog";
import Blog from "./Blog.vue";
export default {
  components: { Blog },
  data() {
    return {
      typeValue: "",
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
    selectChange(val) {},
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
      createBlog(data).then((res) => {
        console.log("结果", res);
      });
      // console.log("博客编辑", data, this.$refs.md.d_render);
    },
  },
  mounted() {
    if (
      localStorage.getItem("blogData") &&
      localStorage.getItem("labelList") &&
      localStorage.getItem("typeValue") && !this.$route.query.blogData
    ) {
      this.blogData = JSON.parse(localStorage.getItem("blogData"));
      this.labelList = JSON.parse(localStorage.getItem("labelList"));
      this.typeValue = JSON.parse(localStorage.getItem("typeValue"));
    }

    if (this.$route.query.blogData) {
      this.blogData = this.$route.query.blogData;
    }
  },
};
</script>
<style lang="less">
.mavon-editor-container {
  display: flex;
  flex-direction: column;
  width: 90%;
  padding: 30px 5%;
  height: calc(100% - 60px);
  .mavon-editor-main {
    height: calc(100% - 60px);
    .markdown-body {
      height: 500px;
    }
  }
  .add-tags {
    width: calc(100% - 20px);
    text-align: left;
    margin-top: 10px;
    background-color: #fff;
    /* box-shadow: 0 2px 12px 0 #000000; */
    /* color: #fff; */
    border-radius: 3px;
    padding: 10px;
  }
  .blog-editor-footer {
    margin-top: 15px;
  }
  .tags-list {
    margin: 15px 0 15px 10px;
    color: black;
  }
  .add-type {
    margin: 15px 0 15px 10px;
    color: black;
  }
  .common-used-tags {
    margin: 15px 0 0px 10px;
    color: black;
  }
  .icon-delete {
    margin-left: 5px;
  }
  .custom-tags {
    color: black;
    margin: 15px 0 0px 10px;
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
  }
}
</style>