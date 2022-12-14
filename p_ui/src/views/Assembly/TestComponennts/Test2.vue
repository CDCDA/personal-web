<template>
  <div class="tip">
    <div class="tip-trangle-left"></div>
    <div v-for="(item, i) in data" :key="i">
      <span>{{ item.type }}</span>
      <div v-for="(list, j) in item.children" :key="j" class="c">
        <div v-for="(citem, k) in list" :key="k" class="e">
          <div class="f">{{ citem.name }}</div>
        </div>
      </div>
      <div class="d"></div>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      data: [
        {
          type: "数据节点",
          children: [
            [
              {
                name: "新增数据",
              },
              {
                name: "更新数据",
              },
            ],
            [
              {
                name: "获取单条数据",
              },
              {
                name: "获取多条数据",
              },
            ],
          ],
        },
        {
          type: "分支节点",
          children: [
            [
              {
                name: "条件分支",
              },
            ],
          ],
        },
        {
          type: "连接器",
          children: [
            [
              {
                name: "连接器",
              },
              {
                name: "Rabbit消息直接推送",
              },
            ],
          ],
        },
      ],
    };
  },
};
</script>
<style lang="less" scoped>
.c {
  display: flex;
  justify-content: space-between;
  margin: 10px;
  height: 30px;
}
.f {
  border: 1px solid white;
  padding: 3px;
  margin: 3px;
  text-align: center;
  border-radius: 3px;
}
.e {
  width: 140px;
}
.tip {
  position: relative;
  margin: 20px 0 20px 0;
  width: 300px;
  background: #000;
  color: #fff;
  font-size: 13px;
  padding: 10px;
  /*设置圆角*/
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  border-radius: 5px;
  text-align: left;
}

/*提示框-左三角*/
.tip-trangle-left {
  position: absolute;
  bottom: 150px;
  left: -10px;
  width: 0;
  height: 0;
  border-top: 15px solid transparent;
  border-bottom: 15px solid transparent;
  border-right: 15px solid #000;
  border-radius: 3px;
}
.d {
  height: 1px;
  background-color: #fff;
}
</style>