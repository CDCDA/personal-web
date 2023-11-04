/*
 * @Description:
 */
// 拖拽的指令
export const drag = {
  beforeMount(el: any, binding: any) {
    alert('SS');
    // 自定义属性，判断是否可拖拽
    if (!binding.value) return;
    let oDiv = el; //当前元素
    var startLeft = 0; //定义位置初始变量
    var startTop = 0;
    oDiv.style.cssText += ';cursor:move;';
    // let menu = document.querySelector(".el-dropdown-menu");
    //禁止选择网页上的文字
    document.onselectstart = function () {
      return false;
    };
    oDiv.onmousedown = function (e: any) {
      //鼠标按下，计算当前元素距离可视区的距离
      let disX = e.clientX - oDiv.offsetLeft;
      let disY = e.clientY - oDiv.offsetTop;
      document.onmousemove = function (e) {
        //通过事件委托，计算移动的距离
        let l = e.clientX - disX;
        let t = e.clientY - disY;

        //重置style,避免初始定位影响下拉选项的位置
        oDiv.removeAttribute('style');
        //移动当前元素
        oDiv.style.left = l + 'px';
        oDiv.style.top = t + 'px';
      };
      document.onmouseup = function () {
        document.onmousemove = null;
        document.onmouseup = null;
      };
      //判断是否点击
      oDiv.onclick = function (e: any) {
        if (e.clientX !== startLeft || e.clientY !== startTop) {
          (startLeft = 0), //位置发生变化为拖拽
            (startTop = 0);
          return;
        }
      };
      //return false不加的话可能导致黏连，就是拖到一个地方时div粘在鼠标上不下来，相当于onmouseup失效
      return false;
    };
  }
};
