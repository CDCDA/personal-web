<template>
  <div class="directory" v-if="titles && Object.keys(titles).length > 0">
    <div class="directory-header">
      <div>
        <span></span>
        <span>目录</span>
      </div>
      <span class="progress">{{ progress }}</span>
    </div>

    <div class="directory-content">
      <div
        v-for="title in titles"
        :key="title.id"
        @click="scrollToView(title.scrollTop)"
        :class="[
          'directory-item',
          currentTitle.id == title.id ? 'active' : 'not-active',
        ]"
        :style="{ marginLeft: title.level * 20 + 'px' }"
        :title="title.rawName"
      >
        {{ title.name }}
      </div>
    </div>
  </div>
</template>

<script>
import { reactive, ref } from "vue";

export default {
  name: "KilaKiladirectory",
  data() {
    return {
      titles: null,
      currentTitle: {},
      progress: null,
    };
  },
  mounted() {
    setTimeout(() => {
      this.titles = this.getTitles();
      // 监听滚动事件并更新样式
      window.addEventListener("scroll", this.scrollEvent, true);
    }, 200);
  },
  methods: {
    scrollEvent() {
      if (this.articleElement)
        this.progress =
          parseInt(
            (this.articleElement.scrollTop /
              (this.articleElement.scrollHeight -
                this.articleElement.clientHeight)) *
              100
          ) + "%";
      let visibleTitles = [];
      if (this.titles && this.titles.length) {
        let titles = this.titles;
        for (let i = titles.length - 1; i >= 0; i--) {
          const title = titles[i];
          if (title.scrollTop <= window.scrollY) {
            if (this.currentTitle.id === title.id) return;

            Object.assign(this.currentTitle, title);

            // 展开节点
            this.setChildrenVisible(title, true);
            visibleTitles.push(title);

            // 展开父节点
            let parent = title.parent;
            while (parent) {
              this.setChildrenVisible(parent, true);
              visibleTitles.push(parent);
              parent = parent.parent;
            }

            // 折叠其余节点
            for (const t of titles) {
              if (!visibleTitles.includes(t)) {
                this.setChildrenVisible(t, false);
              }
            }
            return;
          }
        }
      }
    },
    // 获取目录的标题
    getTitles() {
      let titles = [];
      let levels = ["h1", "h2", "h3", "h4"];
      // console.log("QWEQEW",this.html)
      let articleElement = document.querySelector("." + this.html);
      this.articleElement = articleElement;
      if (!articleElement) {
        return titles;
      }

      let elements = Array.from(articleElement.querySelectorAll("*"));
      // 调整标签等级
      let tagNames = new Set(elements.map((el) => el.tagName.toLowerCase()));
      for (let i = levels.length - 1; i >= 0; i--) {
        if (!tagNames.has(levels[i])) {
          levels.splice(i, 1);
        }
      }

      let serialNumbers = levels.map(() => 0);
      for (let i = 0; i < elements.length; i++) {
        const element = elements[i];
        let tagName = element.tagName.toLowerCase();
        let level = levels.indexOf(tagName);
        if (level == -1) continue;

        let id = tagName + "-" + element.innerText + "-" + i;
        let node = {
          id,
          level,
          parent: null,
          children: [],
          rawName: element.innerText,
          scrollTop: element.offsetTop,
        };

        if (titles.length > 0) {
          let lastNode = titles.at(-1);

          // 遇到子标题
          if (lastNode.level < node.level) {
            node.parent = lastNode;
            lastNode.children.push(node);
          }
          // 遇到上一级标题
          else if (lastNode.level > node.level) {
            serialNumbers.fill(0, level + 1);
            let parent = lastNode.parent;
            while (parent) {
              if (parent.level < node.level) {
                parent.children.push(node);
                node.parent = parent;
                break;
              }
              parent = parent.parent;
            }
          }
          // 遇到平级
          else if (lastNode.parent) {
            node.parent = lastNode.parent;
            lastNode.parent.children.push(node);
          }
        }

        serialNumbers[level] += 1;
        let serialNumber = serialNumbers.slice(0, level + 1).join(".");

        node.isVisible = node.parent == null;
        node.name = serialNumber + ". " + element.innerText;
        titles.push(node);
      }
      // console.log("QWE", titles);
      return titles;
    },
    // 滚动到指定的位置
    scrollToView(scrollTop) {
      console.log("SS", this.articleElement);
      this.articleElement.scrollTo({ top: scrollTop, behavior: "smooth" });
    },
    // 设置子节点的可见性
    setChildrenVisible(title, isVisible) {
      for (const child of title.children) {
        child.isVisible = isVisible;
      }
    },
  },
  // setup(props) {
  //   return { titles, currentTitle, progress, scrollToView };
  // },
  props: {
    html: {
      type: String,
      default: ".post-body .article-content",
    },
  },
};
</script>

<style lang="less">
.directory {
  border-radius: 8px;
  background-color: rgba(29, 32, 33, 0.6);
  box-shadow: 0 2px 12px 0 #000000;
  padding: 20px 24px;
  width: 100%;
  margin-top: 15px;
  box-sizing: border-box;
  .directory-header {
    text-align: left !important;
    margin-bottom: 15px;
    display: flex;
    justify-content: space-between;
    align-items: center;
  }

  .directory-icon {
    font-size: 18px;
    margin-right: 10px;
    color: #363c41;
  }

  .directory-header div > span {
    font-size: 17px;
    color: #fff;
  }

  .progress {
    color: #fff;
    font-style: italic;
    font-size: 140%;
  }

  .directory-content {
    max-height: calc(100vh - 120px);
    overflow: auto;
    margin-right: -24px;
    padding-right: 20px;
    text-align: left;
  }

  .directory-item {
    color: #fff;
    margin: 5px 0;
    line-height: 28px;
    cursor: pointer;
    transition: all 0.2s ease-in-out;
    font-size: 14px;
    padding: 2px 6px;
    display: -webkit-box;
    overflow: hidden;
    text-overflow: ellipsis;
    -webkit-line-clamp: 1;
    -webkit-box-orient: vertical;

    &:hover {
      color: #8d9daa;
    }
  }

  .active {
    //   background-color: #;
    color: white;

    &:hover {
      background-color: #828e97;
      color: white;
    }
  }
}
</style>
