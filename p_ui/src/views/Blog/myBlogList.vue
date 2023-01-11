<template>
  <div class="my-blog-main page-main">
    <div class="blog-list-header"></div>
    <el-form
      :inline="true"
      v-show="showSearch"
      label-width="120px"
      class="search-bar create-bar"
      :disabled="isShow"
      label-position="right"
      :rules="rules"
      ref="form"
      :model="form"
      :validate-on-rule-change="true"
    >
      <el-row>
        <el-col :span="6"></el-col>
        <el-col :span="6"> </el-col>
        <el-col :span="6"> </el-col>
        <el-col :span="6"></el-col>
      </el-row>
      <el-table
        :data="form.materialDetailsList"
        :row-class-name="rowMaterialDetailsIndex"
        @selection-change="handleMaterialDetailsSelectionChange"
        ref="materialDetails"
        class="materialDetails-table"
        border
        stripe
        :disabled="isShow"
        height="calc(100% - 165px)"
        :cell-style="{ padding: 0 + 'px' }"
        :header-cell-style="{ padding: '0px' }"
        :row-style="{ height: 0 + 'px' }"
      >
        <el-table-column type="selection" width="50" align="center" />
        <el-table-column label="项次" prop="itemNo" width="50" align="center">
        </el-table-column>
        <el-table-column label="项次" prop="itemNo" width="50" align="center">
        </el-table-column>
      </el-table>
    </el-form>
    <outgoing-order-dialog
      :open="isOrderOpen"
      :customerName="form.customerName"
      title="客户的外发订单"
      @orderResOpen="orderResOpen"
      @handleOrderData="handleOrderData"
    ></outgoing-order-dialog>
    <customer-dialog
      :open="isCustomerOpen"
      :customerId="customerId"
      @customerResOpen="customerResOpen"
      @handleCustomerData="handleCustomerData"
    ></customer-dialog>
  </div>
</template>

<script>
import {
  listDeliveryOrder,
  getDeliveryOrder,
  delDeliveryOrder,
  addDeliveryOrder,
  updateDeliveryOrder,
} from "@/api/crm/deliveryOrder";
import { getUserProfile } from "@/api/system/user";
import CustomerDialog from "../customerProfile/customerDialog.vue";
import OutgoingOrderDialog from "../outgoingOrder/outgoingOrderDialog";
import { routeJump } from "@/api/crm/routeJump.js";

import { listMaterialDetails } from "@/api/crm/materialDetails";

export default {
  name: "DeliveryOrder",
  components: {
    CustomerDialog,
    OutgoingOrderDialog,
  },
  watch: {
    "form.customerId"(val) {
      console.log("AA", val);
    },
  },
  computed: {
    activeIndex: {
      get: function () {
        return this.$store.getters.activeIndex;
      },
      set: function () {},
    },
    openTabs: {
      get: function () {
        return this.$store.getters.openTabs;
      },
      set: function () {},
    },
    customerId() {
      return this.form.customerId;
    },
  },
  data() {
    return {
      isOrderOpen: false,
      isCustomerOpen: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedMaterialDetails: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 送货订单表格数据
      deliveryOrderList: [],
      // 物料明细表格数据
      materialDetailsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        customerName: null,
        outgoingOrderId: null,
        deliveryType: null,
        deliverydate: null,
        address: null,
        purchaser: null,
        remarks: null,
      },
      mQueryParams: {
        pageNum: 1,
        pageSize: 10,
        customerName: null,
        outgoingOrderId: null,
        deliveryType: null,
        deliverydate: null,
        address: null,
        purchaser: null,
        remarks: null,
      },
      // 表单参数
      form: {
        materialDetailsList: [],
        customerName: null,
        deliveryType: "普通送货",
        outgoingOrderId: null,
      },
      // 表单校验
      rules: {
        customerName: [
          { required: true, message: "请选择客户", trigger: "blur" },
        ],
        deliveryType: [
          { required: true, message: "请选择送货类型", trigger: "blur" },
        ],
        outgoingOrderId: [
          {
            required: true,
            message: "请选择外发订单",
            trigger: "blur",
          },
        ],
        deliverydate: [
          { required: true, message: "请选择交货日期", trigger: "blur" },
        ],
        plannedDeliveryQuantity: [
          { required: true, message: "请选择客户", trigger: "blur" },
        ],
      },
      isShow: false,
    };
  },
  created() {
    this.getList();
  },
  mounted() {
    this.$nextTick(() => {
      this.getUser();
    });
    let query = this.$route.query;
    if (query.viewData && Object.keys(query.viewData).length > 1) {
      this.isShow = true;
      this.form = query.viewData;
      listMaterialDetails({
        deliveryOrderId: query.viewData.deliveryOrderId,
      }).then((response) => {
        this.form.materialDetailsList = response.rows;
      });
    } else if (query.alterData && Object.keys(query.alterData).length > 1) {
      this.form = query.alterData;
      listMaterialDetails({
        deliveryOrderId: query.alterData.deliveryOrderId,
      }).then((response) => {
        this.form.materialDetailsList = response.rows;
      });
    }
  },
  methods: {
    /** 查询送货订单列表 */
    getList() {
      this.loading = true;
      listDeliveryOrder(this.queryParams).then((response) => {
        this.deliveryOrderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        deliveryOrderId: null,
        customerName: null,
        outgoingOrderNo: null,
        deliveryType: null,
        deliverydate: null,
        address: null,
        purchaser: null,
        remarks: null,
      };
      this.form.materialDetailsList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map((item) => item.deliveryOrderId);
      this.single = selection.length !== 1;
      this.multiple = !selection.length;
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加送货订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const deliveryOrderId = row.deliveryOrderId || this.ids;
      getDeliveryOrder(deliveryOrderId).then((response) => {
        this.form = response.data;
        this.form.materialDetailsList = response.data.materialDetailsList;
        this.open = true;
        this.title = "修改送货订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate((valid) => {
        if (valid) {
          if (this.form.deliveryOrderId != null) {
            updateDeliveryOrder(this.form).then((response) => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDeliveryOrder(this.form).then((response) => {
              console.log("th", this.form);
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const deliveryOrderIds = row.deliveryOrderId || this.ids;
      this.$modal
        .confirm(
          '是否确认删除送货订单编号为"' + deliveryOrderIds + '"的数据项？'
        )
        .then(function () {
          return delDeliveryOrder(deliveryOrderIds);
        })
        .then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        })
        .catch(() => {});
    },
    /** 保存按钮操作 */
    handleSave() {},
    /** 查看更多操作 */
    handleViewMore(path, name) {
      routeJump(this, path, name);
    },
  },
};
</script>
<style lang="scss">
</style>
