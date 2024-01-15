<template>
  <div class="c-pagination">
    <ul class="pager clear" @click="onPage">
      <li
        :class="{ disabled: page <= 1 }"
        :aria-disabled="page <= 1"
        :arial-label="arialLabel(-1)"
        tabindex="0"
        class="pn prev"
        data-page="-1"
        role="button"
      >
        <span class="arrow"></span>
      </li>

      <div class="pagi-number" style="display: flex; align-items: center; margin-right: 30px">
        <div style="display: flex; align-items: center">
          <img src="@/assets/svg/total.svg" style="height: 35px; width: 35px" /><span
            >{{ total }}条</span
          >
        </div>
        <template v-for="(group, index) in slices">
          <li
            v-if="group[0] === -1"
            :key="'g' + index"
            :data-page="group[1]"
            :data-jumper="index"
            :arial-label="arialLabel(group[1])"
            class="pn jumper"
          >
            <span class="dots">...</span>
          </li>
          <li
            v-for="i in group"
            v-else
            :key="'l' + i"
            :class="{ active: page === i }"
            :data-page="i"
            :arial-label="arialLabel(i)"
            class="pn"
            role="button"
          >
            <span>{{ i }}</span>
          </li>
        </template>
        <div v-if="showSizes" class="page-size">
          <div class="page-select" @click="showPageList = !showPageList">
            {{ pageSize }}条/页
            <div v-if="showPageList" class="select-box">
              <div
                v-for="(item, index) in pageSizeList"
                :key="index"
                class="seleclt-opotion"
                @click.stop="onSize(item)"
              >
                {{ item }}
              </div>
            </div>
          </div>
        </div>
      </div>

      <li
        :class="{ disabled: page >= pages }"
        :aria-disabled="page >= pages"
        :arial-label="arialLabel(0)"
        tabindex="0"
        class="pn next"
        data-page="0"
        role="button"
      >
        <span class="arrow"></span>
      </li>
    </ul>
  </div>
</template>

<script>
export default {
  name: 'Pagination',
  props: {
    page: {
      type: Number,
      default: 1
    },
    total: {
      type: Number,
      default: 0
    },
    pageSize: {
      type: Number,
      default: 10
    },
    onPageChange: {
      type: Function,
      default: () => {}
    },
    onPageSizeChange: {
      type: Function,
      default: () => {}
    },
    // 选择分页size
    showSizes: {
      type: Boolean,
      default: false
    },
    // 页码list
    pageSizeList: {
      type: Array,
      default: [10, 20]
    }
  },
  data() {
    return {
      pages: 0,
      slices: [[1]],
      showPageList: false
    };
  },
  watch: {
    page() {
      this.updateSlices();
    },
    total() {
      this.updateSlices();
    },
    pageSize() {
      this.updateSlices();
    }
  },
  mounted() {
    this.updateSlices();
  },
  methods: {
    arialLabel(i) {
      if (i === -1) {
        return '上一页';
      }
      if (i === 0) {
        return '下一页';
      }
      return `第${i}页`;
    },
    updateSlices() {
      // console.log(this.page, this.total, this.pageSize)
      // eslint-disable-next-line no-multi-assign
      const pages = (this.pages = Math.ceil(this.total / this.pageSize));
      if (pages < 5) {
        this.slices = [
          Array(pages)
            .fill(1)
            .map((o, i) => i + 1)
        ];
      } else if (this.page < 4) {
        this.slices = [[1, 2, 3], [-1, 4], [pages]];
      } else if (pages - this.page < 3) {
        this.slices = [[1], [-1, 2], [pages - 2, pages - 1, pages]];
      } else {
        this.slices = [
          [1],
          [-1, 2],
          [this.page - 1, this.page, this.page + 1],
          [-1, this.page + 2],
          [pages]
        ];
      }
    },
    // 选择size
    onSize(e) {
      this.$emit('update:pageSize', e);
      this.onPageChange(1);
      this.showPageList = false;
      // this.onPageSizeChange(e);
      // //const page = Math.ceil(this.total / e);
      // this.onPageChange(1);
    },
    onPage(e) {
      let target = e.target;
      if (target.tagName === 'SPAN') {
        target = target.parentElement;
      }
      if (target.className.indexOf('disabled') !== -1 || target.tagName !== 'LI') {
        return;
      }

      const page = +target.getAttribute('data-page');
      const jumper = target.getAttribute('data-jumper');
      if (jumper) {
        // this.showJumper(+jumper, target);
        this.$emit('update:page', page);
        this.onPageChange(page);
      } else {
        this.$emit('update:page', this.calcNextPage(page));
        this.onPageChange(this.calcNextPage(page));
      }
    },
    calcNextPage(page) {
      return !page ? this.page + 1 : page < 0 ? this.page - 1 : page;
    },
    showJumper(num, target) {
      if (num && num > 0 && num <= this.pages) {
        const slices = [...this.slices];
        slices[num][2] = 1;
        this.slices = slices;
        setTimeout(() => {
          target.children[1].focus();
        }, 100);
      }
    },
    onJump(e) {
      const val = +e.target.value;
      if (val && val > 0 && val <= this.pages) {
        this.onPageChange(val);
      }
    },
    onBlur(e) {
      const num = +e.target.parentNode.getAttribute('data-jumper');
      const slices = [...this.slices];
      slices[num][2] = 0;
      this.slices = slices;
    }
  }
};
</script>
<style lang="scss" scoped>
@include theme() {
  .c-pagination {
    // font-size: $font-size-second;
    color: get('font-color');
    width: 100%;
    letter-spacing: 1.8px;
    font-weight: 400;
    line-height: 40px;
    display: flex;
    text-align: center;
    .pn:active {
      transform: translateY(2px);
    }

    & > .pager {
      display: flex;
      width: 100%;
      justify-content: space-between;
      white-space: nowrap;
      align-items: center;
      padding: 0px;
      margin: 0px;
    }
    .pn {
      border: 1px solid get('border-color');
      float: left;
      list-style: none;
      cursor: pointer;
      line-height: 45px;
      margin-left: 15px;
      text-align: center;
      background: get('re-font-color');
      border-radius: 2px;
      font-family: PingFangSC-Regular;
      font-size: 18px;
      color: get('font-color');
      letter-spacing: 1.8px;
      font-weight: 400;
      outline: none;

      height: 45px;
      width: 45px;
      border-radius: 10px;

      &:first-child {
        margin-left: 0;
      }
      &:hover:not(.disabled) {
        background: get('bk');
        border: 1px solid transparent;
        color: #fff;
      }
      &.active.active {
        background: get('bk');
        border-color: get('bk');
        border: 1px solid transparent;
        color: #fff;
      }
      & > .dots {
        display: block;
        font-weight: bold;
        line-height: 30px;
        padding-bottom: 6px;
      }
      & > input {
        color: #666;
        border: 0;
        font-family: Arial, sans-serif;
        font-size: 15px;
        max-width: 40px;
        padding: 2px 1px;
      }
      &.prev,
      &.next {
        color: get('font-color') 999;
        display: flex;
        align-items: center;
        justify-content: center;
      }
      &.prev {
        .arrow {
          width: 8px;
          height: 8px;
          display: block;
          margin-left: 4px;
          border-left: solid 1px currentColor;
          border-top: solid 1px currentColor;
          -webkit-transform: rotate(-45deg);
          transform: rotate(-45deg);
        }
      }
      &.next {
        .arrow {
          width: 8px;
          height: 8px;
          display: block;
          margin-right: 4px;
          border-bottom: solid 1px currentColor;
          border-right: solid 1px currentColor;
          -webkit-transform: rotate(-45deg);
          transform: rotate(-45deg);
        }
      }
      &.next,
      &.prev {
        color: #fff !important;
        background: get('bk');
        border: 1px solid transparent;
      }
      &.disabled {
        cursor: not-allowed;
        background: get('re-font-color');
        color: get('font-color') !important;
      }
    }
    & > .elevator {
      display: inline-block;
      color: #888f9c;
      font-size: 14px;
      height: 40px;
      line-height: 40px;
      margin-left: 20px;
      position: relative;
      vertical-align: top;

      & > .pagenum {
        appearance: none;
        background: transparent;
        color: #666;
        border: 1px solid #e6e7eb;
        border-radius: 0;
        font-size: 18px;
        margin: 0 10px 0 2px;
        padding-left: 10px;
        width: 60px;
        height: 40px;
      }

      & > .icn {
        display: block;
        border-top: 6px solid #5c5c5c;
        border-left: 5px solid transparent;
        border-right: 5px solid transparent;
        border-bottom: none;
        pointer-events: none;
        position: absolute;
        top: 17px;
        left: 142px;
      }
    }
    .page-size {
      display: flex;
      align-items: center;
      width: auto;
      height: 45px;
      padding: 10px;
      margin: 0px;
      .page-select {
        border: 1px solid get('border-color');
        white-space: nowrap;
        height: 100%;
        background: get('background');
        min-width: 55px;
        padding: 0 8px 0px 12px;
        margin: 0 8px;
        outline: none;
        display: flex;
        align-items: center;
        justify-content: center;
        position: relative;
        cursor: pointer;
        width: auto;
        border-radius: 8px;
        &::after {
          content: '';
          width: 0px;
          display: block;
          height: 0px;
          line-height: 0px;
          margin-left: 6px;
          border-top: 6px solid #d8d8d8;
          border-left: 6px solid #f6f7fc;
          border-right: 6px solid #f6f7fc;
        }
        .select-box {
          position: absolute;
          left: 0;
          width: 100%;
          bottom: 50px;
          border: 1px solid #eee;
          background: get('background-no-tp');
          border-radius: 8px;
          border: 1px solid get('border-color');
          z-index: 11;
          overflow: hidden;
          .seleclt-opotion {
            padding: 0 10px;
            &:hover {
              background: #f6f7fc;
            }
          }
        }
      }
    }
  }
}
</style>
