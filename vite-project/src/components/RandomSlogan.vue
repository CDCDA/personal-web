<!--
 * @Author: chenyd 1205489124@qq.com
 * @Date: 2023-01-24 19:29:51
 * @LastEditors: Please set LastEditors
 * @LastEditTime: 2023-09-21 16:00:42
 * @FilePath: \p_ui\src\components\RandomSlogan.vue
 * @Description: 这是默认设置,请设置`customMade`, 打开koroFileHeader查看配置 进行设置: https://github.com/OBKoro1/koro1FileHeader/wiki/%E9%85%8D%E7%BD%AE
-->
<template>
  <!-- <span class="random-slogan">{{ this.currentSlogan }}</span> -->
</template>

<script lang="ts">
// export default {
//   components: {},
//   data() {
//     return {
//       slogan: [
//         '武功再高，也怕菜刀。',
//         '要适当吃一点，才能减肥。',
//         '单相思没有结果，比如我爱数学。',
//         '人生最大的耻辱是什么？作弊失败！',
//         '虽然早睡早起身体好，但是晚睡晚起心情好。',
//         '所谓睡货，可概括为春困、夏疲、秋睡、冬眠八个字。',
//         '每次遇到不太好的感应水龙头，都觉得自己好像要饭。',
//         '永远年轻，永远装嫩，永远不知好歹，永远热泪盈眶。',
//         '为什么星期五离星期一这么近，星期一却离星期五那么远，这不科学。',
//         '小时候做错了很多事，现在想想十分后悔，如果给我再来一次的机会，我一定做的滴水不漏。',
//         '毁灭友情的方式有许多，最彻底的一种是借钱。',
//         '喜欢吃一样东西是藏不住的，就算捂住嘴巴，口水还是会从指缝中溢出来。',
//         '看见别人都那么努力，那么勤奋，那么意气风发地走在成功的路上，你问问自己，难道就不想成为他们的绊脚石吗？',
//         '你以为现在就是人生的低谷了吗，其实你还有很大的下跌空间呢。',
//         '有些人，一旦错过了，真得谢天谢地，锣鼓喧天，鞭炮齐鸣。',
//         '在这个薄情的年代，要想别人对你念念不忘，最好的办法就是欠钱不还。',
//         '有人喜欢你的脸，有人喜欢你的声音，有人喜欢你的性格，有人喜欢你的生活，而我不一样，我不喜欢你。'
//       ],
//       currentSlogan: '',
//       timer: ''
//     };
//   },
//   methods: {
//     getRandomSlogan() {
//       let index = Math.floor(Math.random() * this.slogan.length);
//       return this.slogan[index];
//     }
//   },
//   mounted() {
//     this.currentSlogan = this.getRandomSlogan();
//     setInterval(() => {
//       this.currentSlogan = this.getRandomSlogan();
//     }, 3000);
//   }
// };
</script>
<style lang="scss" scoped>
@include theme() {
  .random-slogan {
    border: solid 1px gray;
    padding: 10px 2px 10px 15px;
    font-size: 20px;
    color: get('font-color');
    width: auto;
    border-radius: 5px;
  }
}
</style>
