<template>
  <div class="mavon-editor-container">
    <mavon-editor
      v-model="content"
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
        <template v-for="(item, i) in commonTagsList">
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
            >{{item}}</el-button>
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
        <el-button size="mini"><i class="el-icon-plus">添加</i></el-button>
      </div>
      <div class="tags-list">
        <span class="tag-label">标签列表</span>
        <template v-for="(item, i) in tagsList">
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
    <div class="blog-editor-footer">
      <el-button @click="saveBlog">保存</el-button>
      <el-button @click="releaseBlog">发布</el-button>
    </div>
  </div>
</template>

<script>
import Blog from "./Blog.vue";
export default {
  components: { Blog },
  data() {
    return {
      content: "",
      typeValue: "",
      isDeleteShow: [],
      isAddShow: [],
      customTag: "",
      typeList: [
        {
          label: "前端",
          value: 1,
          children: [
            {
              label: "html",
              value: 2,
            },
            {
              label: "css",
              value: 3,
            },
            {
              label: "javascript",
              value: 4,
            },
            {
              label: "vue",
              value: 5,
            },
          ],
        },
        {
          label: "后端",
          children: [
            {
              label: "springboot",
              value: 6,
            },
            {
              label: "tomcat",
              value: 7,
            },
            {
              label: "servelet",
              value: 8,
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
      tagsList: ["vue", "html", "css", "springboot", "javascript"],
      commonTagsList: ["vue", "html", "css", "springboot", "javascript"],
    };
  },
  methods: {
    updateDoc(value, render) {
      // render 为 markdown 解析后的结果
    },
    selectChange(val) {},
    contentSave(value) {
      localStorage.setItem("blogContent", value);
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
      this.tagsList.splice(i, 1, "");
    },
    addTagOver(i) {
      this.isAddShow = [];
      this.isAddShow[i] = true;
    },
    addTagLeave() {
      this.isAddShow = [];
    },
    addTag(i) {
      this.tagsList.push(this.commonTagsList[i]);
    },
    saveBlog(){

    },
    releaseBlog(){
      
    }
  },
  mounted() {
    this.content = localStorage.getItem("blogContent");
  },
};
</script>
<style lang="less" scoped>
.mavon-editor-container {
  width: 80%;
  border: 2px solid black;
  border-radius: 5px;
  //background-color: #1b1f2b;
  color: aliceblue;
  margin: 0 auto;
  background-color: white;
}
.add-tags {
  width: 100%;
  background-color: white;
  text-align: left;
}
.blog-editor-footer{
  margin-bottom: 25px;
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
</style>