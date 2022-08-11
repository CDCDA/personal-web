<template>
  <div :id="id"></div>
</template>
<script>
export default {
  data() {
    return {
      id: "",
      isFirstNull: true,
      editor: null, //编辑器
      current: null, //当前自定义提示
      defaultRemote: {},
      funcRemote: [], //函数
      tFuncRemote: [], //T$内函数
      cmOptions: {
        value: "", //编辑器初始显示文字
        language: "javascript", //语言支持自行查阅demo
        automaticLayout: true, //大小跟随父容器
        theme: "vs", //官方自带三种主题vs, hc-black, or vs-dark
        selectOnLineNumbers: true, //显示行号
        roundedSelection: false,
        readOnly: false, // 只读
        cursorStyle: "line", //光标样式
        automaticLayout: true, //自动布局
        glyphMargin: true, //字形边缘
        useTabStops: false,
        fontSize: 14, //字体大小
        autoIndent: false, //自动布局
        quickSuggestionsDelay: 50,
        lineNumbers: true,
        tabSize: 2,
      },
    };
  },
  props: [
    "value",
    "options",
    "state",
    "edit",
    "remote",
    "printRead",
    "read",
    "isEditable",
    "isFirst",
  ],
  methods: {
    getReportKey() {
      //随机Id
      return Math.random().toString(36).slice(-8);
    },
    initEditor(window,methodsList) {
      //创建编辑器
      this.editor = window.monaco.editor.create(
        document.getElementById(this.id),
        this.cmOptions
      );
      if (this.value) {
        //初始化编辑器内容
        this.editor.setValue(this.value);
      }
      //监听编辑器数据变化
      this.editor.onDidChangeModelContent((e) => {
        var val = this.editor.getValue();
        this.$emit("input", val);
      });
      //添加tlw方法提示
      if (methodsList) {
        this.current = window.monaco.languages.registerCompletionItemProvider(
          "javascript",
          {
            provideCompletionItems: function (model, position) {
              var word = model.getWordUntilPosition(position);
              var range = {
                startLineNumber: position.lineNumber,
                endLineNumber: position.lineNumber,
                startColumn: word.startColumn,
                endColumn: word.endColumn,
              };
              var suggestions = [];
              //console.log("methodsList1:", methodsList);
              Object.keys(methodsList).forEach((item) => {
                var tempItem = methodsList[item];
                var type = Object.prototype.toString.call(tempItem);
                if (
                  type === "[object Function]" ||
                  type === "[object String]"
                ) {
                  if (type === "[object Function]") {
                    tempItem = tempItem.toString();
                  }
                  suggestions.push({
                    label: item,
                    kind: window.monaco.languages.CompletionItemKind.Function,
                    insertText: item,
                    documentation: "",
                    detail: tempItem, // 提示内容后的说明
                    range: range,
                  });
                }
              });
              //console.log("suggestions:", suggestions);
              return {
                suggestions: suggestions,
              };
            },
            triggerCharacters: ["T", "$", "."],
          }
        );
      }
    },
  },
  watch: {
  },
  destroyed() {
    this.editor?.dispose();
    this.current?.dispose();
  },
  created() {
    //动态设置id，避免monaco-editor初始化冲突
    this.id = this.getReportKey();
  },
  mounted() {
    //初始化编辑器配置
    console.log("DDD",window)
    if (this.options) {
      this.setCmOptions();
    }
    this.initEditor(window)
  },
};
</script>
<style lang="less" scoped>
.editor-container {
  width: 100%;
  height: 460px;
  border: 1px solid #dcdfe6;
}
.full-editor-container {
  height: 100%;
  width: 100%;
  border: 1px solid #dcdfe6;
  overflow: hidden;
}
// ::v-deep .tlw-project-info .steps-panes-vertical > .steps-pane {
//   overflow: hidden;
// }
</style>


