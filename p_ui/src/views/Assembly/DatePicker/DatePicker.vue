<template>
  <div class="datePicker">
    <el-date-picker
      ref="datePicker"
      v-model="value"
      :default-value="nowDate"
      type="datetimerange"
      start-placeholder="开始日期"
      end-placeholder="结束日期"
      :picker-options="pickerOptions"
      unlink-panels
      @focus="focus"
    >
    </el-date-picker>
  </div>
</template>

<script>
export default {
  data() {
    return {
      value: "",
      isLink: true,
      pickerOptions: {},
      nowDate: [new Date(), new Date()],
    };
  },
  watch: {},
  methods: {
    focus(focus) {
      var picker = this.$refs.datePicker.$el.__vue__.picker;
      if (picker) {
        let a = document.getElementsByClassName("is-right")[0];
        let b = a.querySelectorAll("span")[1];
        b.addEventListener("click", () => {
          let { minDate, minVisibleDate, minVisibleTime } = picker;
          console.log(minVisibleDate + minVisibleTime);
          let maxDate = new Date(JSON.parse(JSON.stringify(minDate)));
          picker.$refs.maxTimePicker.selectableRange = [
            [minDate, new Date(maxDate.setHours(23, 59, 59))],
          ];
        });
      }
    },
  },
  mounted() {},
};
</script>
<style lang="less" scoped>
.datePicker {
  margin: auto;
}
</style>