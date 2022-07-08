<template>
  <div class="transfer-container" :id="id">
    <!-- <el-transfer
      class="tlw-transfer"
      v-model="dataModel"
      :titles="titles"
      :format="{
        noChecked: '${total}',
        hasChecked: '${checked}/${total}',
      }"
      :props="props"
      @change="handleChange"
      @left-check-change="leftCheckChange"
      @right-check-change="rightCheckChange"
      :data="data"
    >
      <span slot-scope="{ option }" @dblclick="dbClick(option)">{{
        option.name
      }}</span>
      <div class="transfer-footer" slot="left-footer">
        <el-button size="mini">取消</el-button>
      </div>
      <div class="transfer-footer" slot="right-footer">
        <el-button size="mini">确定</el-button>
      </div>
    </el-transfer>
    <el-select
      class="transfer-select"
      v-model="selectValue"
      allow-create
      default-first-option
      :popper-append-to-body="false"
      @change="selectChange"
    >
      <el-option :value="selectValue" :label="selectValue"
        ><el-tree
          :node-key="props.value"
          :default-expanded-keys="defaultExpandedKey"
          :data="serviceData"
          :props="defaultProps"
          @node-click="handleNodeClick"
        ></el-tree>
      </el-option>
    </el-select> -->
    <el-tree
      style="width: 250px"
      :data="serviceData"
      :props="defaultProps"
      @node-click="handleNodeClick"
    ></el-tree>
  </div>
</template>

<script>
export default {
  data() {
    return {
      id: this.getReportKey(),
      isLeft: false,
      isRight: false,
      dataModel: [],
      selectValue: "",
      titles: ["待选", "已选"],
      defaultProps: {
        children: "children",
        label: "label",
      },
      serviceData: [
        { id: 2, name: "研发部", parentId: 0 },
        { id: 4, name: "行政人事部", parentId: 2 },
        { id: 6, name: "行政人事部下面的行政", parentId: 4 },
        { id: 7, name: "行政人事部下面的人力资源", parentId: 4 },
        { id: 20, name: "研发下面的产品组", parentId: 2 },
        { id: 21, name: "研发下面的测试组", parentId: 2 },
        { id: 22, name: "研发下面的运维组", parentId: 2 },
        { id: 23, name: "研发下面的前端组", parentId: 2 },
        { id: 24, name: "研发下面的后台组", parentId: 2 },
        { id: 25, name: "研发下面的移动开发组", parentId: 2 },
        { id: 99, name: "移动组下面的组", parentId: 25 },
        { id: 98, name: "移动组下面的组", parentId: 25 },
      ],
      props: {
        key: "value",
        label: "name",
      },
      data: [
        { value: "1", name: "选项1" },
        { value: "2", name: "选项2" },
        { value: "3", name: "选项3" },
      ],
    };
  },
  props: ["value", "options", "state", "edit", "remote", "printRead", "read"],
  methods: {
    handleChange() {
      console.log("AAA", this.dataModel);
      console.log("BBB", this.data);
    },
    handleNodeClick(val) {
      console.log("NodeClick", val);
    },
    selectChange(val) {
      console.log("AD", this.data);
      this.serviceData.forEach((item, i) => {
        i == val ? (this.data = item) : "";
      });
    },
    leftCheckChange() {
      (this.isLeft = true), (this.isRight = false);
    },
    rightCheckChange() {
      (this.isLeft = false), (this.isRight = true);
    },
    insertSelectCSSItem() {
      //在穿梭框中插入下拉框
      let item = document.getElementById(this.id); //当前穿梭框
      let sNode = item.getElementsByClassName("transfer-select")[0];
      let pNode = item.getElementsByClassName("el-transfer-panel")[0];
      let rNode = item.getElementsByClassName("el-transfer-panel__body")[0]; //参考节点
      let rightBody = item.getElementsByClassName("el-transfer-panel__body")[1];
      rightBody.style.height = rightBody.offsetHeight + 40 + "px";
      pNode.insertBefore(sNode, rNode);
    },
    getReportKey() {
      //随机Id
      return Math.random().toString(36).slice(-8);
    },
    dbClick(option) {
      if (this.isRight && !this.isLeft) {
        this.$nextTick(function () {
          this.dataModel = this.dataModel.filter(
            (item) => item != option.value
          );
        });
      } else this.dataModel.push(option.value);
    },
    formatToTree(ary, pid) {
      //根据parentid处理数据为树形结构
      return ary
        .filter((item) =>
          pid == undefined ? item.parentId == 1 : item.parentId == pid
        )
        .map((item) => {
          // 通过父节点ID查询所有子节点
          item.children = this.formatToTree(ary, item.id);
          return item;
        });
    },
    formatTreeData(data) {
      data.forEach((item) => {
        item.children ? (item.leaf = false) : (item.leaf = false);
      });
    },
    initScroll() {
      this.$nextTick(() => {
        let scrollWrap = document.querySelectorAll(
          ".el-scrollbar .el-select-dropdown__wrap"
        )[0];
        let scrollBar = document.querySelectorAll(
          ".el-scrollbar .el-scrollbar__bar"
        );
        scrollWrap.style.cssText =
          "margin: 0px; max-height: none; overflow: hidden;";
        scrollBar.forEach((ele) => (ele.style.width = 0));
      });
    },
  },
  mounted() {
    //this.insertSelectCSSItem();
    console.log(this.formatToTree(this.serviceData));
    //this.formatTreeData(this.formatToTree(this.serviceData));
    console.log("AD", this.serviceData);
    this.initScroll();
    //console.log(JSON.stringify(formatToTree(data)));
  },
};
</script>

<style lang="less">
.transfer-container {
  text-align: center;
  .tlw-transfer {
    text-align: left;
    display: inline-block;
  }
  .transfer-select {
    .el-input .el-input__inner {
      border-color: #ebeef5;
      border-radius: 0px;
      border-left: none;
      border-right: none;
      border-top: none;
    }
  }
  .transfer-footer {
    text-align: center;
    margin-top: 3px;
  }
}
</style>
<style scoped>
  .el-scrollbar .el-scrollbar__view .el-select-dropdown__item{
    height: auto;
    max-height: 274px;
    padding: 0;
    overflow: hidden;
    overflow-y: auto;
  }
  .el-select-dropdown__item.selected{
    font-weight: normal;
  }
  ul li >>>.el-tree .el-tree-node__content{
    height:auto;
    padding: 0 20px;
  }
  .el-tree-node__label{
    font-weight: normal;
  }
  .el-tree >>>.is-current .el-tree-node__label{
    color: #409EFF;
    font-weight: 700;
  }
  .el-tree >>>.is-current .el-tree-node__children .el-tree-node__label{
    color:#606266;
    font-weight: normal;
  }
</style>