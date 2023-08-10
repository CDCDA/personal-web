<template>
  <div>
    <title>边-多标签</title>
    <div id="mountNode"></div>
  </div>
</template>

<!-- <script src="https://gw.alipayobjects.com/os/lib/antv/g6/4.3.2/dist/g6.min.js"></script> -->
<script lang="ts">
/**
 * 本示例演示以下功能：
 * 鼠标 hover 节点更新节点样式及其标签文本
 * 鼠标 hover 边更新边样式及其标签文本
 * by 十吾
 */
// var INNER_CIRCLE_CLASS = 'node-inner-circle';
// var GRAPH_CONTAINER = 'mountNode';

// var tooltipEl = null;

// // 在指定的位置显示tooltip
// function showTooltip(message, position) {
//   if (!tooltipEl) {
//     var container = document.getElementById(GRAPH_CONTAINER);
//     tooltipEl = document.createElement('div');
//     tooltipEl.setAttribute('class', 'graph-tooltip el-icon-circle-plus-outline');
//     container.appendChild(tooltipEl);
//   }
//   tooltipEl.textContent = message;
//   // tooltip是相对于画布canvas element绝对定位，所以position的x，y必须是相对于画布的坐标
//   console.log(position);
//   tooltipEl.style.right = position.x + 'px';
//   tooltipEl.style.top = position.y + 'px';
//   tooltipEl.style.display = 'block';
//   //console.log("AD", tooltipEl);
// }
// var a = [
//   ((_ref = {
//     source: '11',
//     target: '12',
//     shape: 'cubic-vertical',
//     label: 'cubic-vertical',
//     color: '#722ed1',
//     size: 5,
//     style: {
//       lineDash: [2, 2]
//     }
//   }),
//   _defineProperty(_ref, 'label', 'quadratic'),
//   _defineProperty(_ref, 'labelCfg', {
//     position: 'center', // 其实默认就是 center，这里写出来便于理解
//     autoRotate: true,
//     style: {
//       lineWidth: 5,
//       fill: '#722ed1',
//       stroke: 'white' // 给文本添加白边和白色背景
//     }
//   }),
//   _ref)
// ];
// // 隐藏tooltip
// function hideTooltip() {
//   if (!tooltipEl) {
//     return;
//   }
//   tooltipEl.style.display = 'none';
// }
// var data = {
//   nodes: [
//     {
//       id: 'node1',
//       x: 100,
//       y: 100,
//       label: 'hover 前的\n节点文本 node1'
//     },
//     {
//       id: 'node2',
//       x: 400,
//       y: 100,
//       label: 'hover 前的\n节点文本 node2'
//     }
//   ],
//   edges: [
//     {
//       source: 'node1',
//       target: 'node2',
//       label: '',
//       labelCfg: {
//         refY: 10
//       }
//     }
//   ]
// };

// setTimeout(() => {
//   var graph = new G6.Graph({
//     container: 'mountNode',
//     width: window.innerWidth,
//     height: window.innerHeight,
//     defaultEdge: {
//       color: '#bae7ff',
//       lineAppendWidth: 3
//     },
//     nodeStateStyles: {
//       hover: {
//         fill: 'steelblue'
//       }
//     },
//     edgeStateStyles: {
//       hover: {
//         stroke: '#000',
//         lineWidth: 3
//       }
//     }
//   });
//   graph.data(data);
//   graph.render();
//   graph.on('edge:mouseenter', function (evt) {
//     //console.log("EVT", evt);
//     var target = evt.target;
//     var edge = evt.item;
//     let path = edge._cfg.keyShape.attrs.path;
//     let height = (path[0][2] + path[1][2]) / 2;
//     let width = (path[0][1] + path[1][1]) / 2;
//     console.log(evt);

//     //console.log("QE",edge._cfg.keyShape.attrs.path)
//     var model = edge.getModel();
//     model.oriLabel = model.label;
//     showTooltip('', {
//       x: width,
//       y: height + 65
//     });
//     // graph.setItemState(edge, "hover", true);
//     // graph.updateItem(edge, {
//     //   label: "hover 后",
//     //   labelCfg: {
//     //     style: {
//     //       fill: "#f00",
//     //     },
//     //   },
//     // });
//   });
//   // graph.on("edge:mouseleave", function (evt) {
//   //   var edge = evt.item;
//   //   var model = edge.getModel();
//   //   hideTooltip();
//   // });
// }, 500);
</script>
<style>
#graph-container canvas {
  border: 1px solid #ccc;
}
.node-event-demo .header {
  font-size: 14px;
  margin: 10px 0;
}
.graph-tooltip {
  font-size: 25px;
  border-radius: 12.5px;
  color: #545454;
  position: absolute;
}
</style>
