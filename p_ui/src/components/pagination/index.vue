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
