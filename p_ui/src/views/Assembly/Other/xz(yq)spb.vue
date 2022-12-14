<template>
  <div style="background: white">
    <table border="1" style="width: 500px">
      <tr>
        <td colspan="6"></td>
      </tr>
      <tr>
        <td colspan="2">学号</td>
        <td colspan="2">姓名</td>
        <td colspan="2">年龄</td>
      </tr>
      <tr>
        <td colspan="3">学号</td>
        <td colspan="3">姓名</td>
      </tr>
      <!-- <tr>
        <th>Java</th>
        <th>SQL</th>
        <th>HTML</th>
        <th>Java</th>
        <th>SQL</th>
        <th>HTML</th>
      </tr> -->
    </table>
  </div>
</template>

<script>
//import WorkFlowMixin from "@/view/fromWorkFlowMixin.js";
export default {
  //mixins: [WorkFlowMixin],
  created() {
    // let id = this.$route.query.flowInstId;
    // this._created("/api/formdata/getReportdata" + this.$route.path + "/" + id);
  },
  methods: {
    
  },
};
</script>
<style lang="less">
.a {
  border: 1px solid black;
  border-radius: 4px;
  width: 100%;
}
.qqqq {
  width: 70%;
  background: black;
  display: inline-block;
}
.aaaa {
  width: 30%;
  display: inline-block;
}
</style>