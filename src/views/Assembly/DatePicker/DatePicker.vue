<template>
  <div class="datePicker">
    <el-date-picker
      ref="datePicker"
      v-model="value"
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
      pickerOptions: {
        disabledDate: (date) => {
          if (window.minDate) {
            if (date.getTime() < window.minDate.getTime()) {
              return true;
            }
          }
          return false;
        },
      },
    };
  },
  methods: {
    focus(focus) {
      if (focus.picker) {
        window.minDate = focus.picker.minDate;
        console.log(this);
      }
    },
  },
  mounted() {
    console.log(this.$refs.datePicker);
  },
};
</script>
<style lang="less" scoped>
.datePicker {
  margin: auto;
}
</style>