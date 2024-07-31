<!--
 * @Description: 游戏
-->
<template>
  <div class="page-main game-main">
    <div class="game-top">
      <div
        class="game-top-item bounceInDown"
        :class="getAnimateTime()"
        v-for="item in gameShowData"
      >
        <img :src="item.coverUrl" />
      </div>
    </div>
    <div class="game-center">
      <div class="divider c-left animated-0s5">
        <svg-icon iconName="手柄" /> 单机游戏<span
          >我不知道我是谁，不知道我在哪，我只知道我要大开杀戒了</span
        >
      </div>
      <div class="single-game-list">
        <div
          class="single-game-item bounceInUp"
          :class="getAnimateTime()"
          @click="toOfficial(item)"
          v-for="item in gameList.filter((game:any)=>game.type=='0')"
        >
          <c-image :src="item.coverUrl" />
          <div class="single-game-info">
            <h3>{{ item.name }}</h3>
            <span class="no-wrap">{{ item.intro }}</span>
            <div class="item-cover">{{ item.intro }}</div>
          </div>
        </div>
      </div>
      <div class="divider c-left animated-0s5">
        <svg-icon iconName="手机" />
        手机游戏<span>打发时间，遗憾的是我是个上线即下线的老咸鱼</span>
      </div>
      <div class="single-game-list">
        <div
          class="single-game-item"
          @click="toOfficial(item)"
          v-for="item in gameList.filter((game:any)=>game.type=='1')"
        >
          <c-image :src="item.coverUrl" />
          <div class="single-game-info">
            <h3>{{ item.name }}</h3>
            <span class="no-wrap">{{ item.intro }}</span>
            <div class="item-cover">{{ item.intro }}</div>
          </div>
        </div>
      </div>
    </div>
    <div class="game-bottom"></div>
  </div>
</template>
<script setup lang="ts">
import { ref, onMounted } from 'vue';
import { listGame } from '@/api/game.ts';
const queryParams = ref({
  name: null,
  pageNum: 1,
  pageSize: 10
} as any);
const gameList = ref([] as any);
async function getList() {
  const { code, msg, data } = (await listGame({})) as any;
  if (code == 200) {
    gameList.value = data.list;
  }
}

const singlePlayerGames = ref([
  {
    name: '只狼',
    coverUrl: 'https://img.fhyx.com/uploads/2020/10/30/20201030105417899.jpg',
    intro: '几十周目了，打铁真爽'
  },
  {
    name: '空洞骑士',
    coverUrl: 'http://ztc.f234.cn/uploadfile/2023/0315/20230315112024712.jpeg',
    intro: '大扑棱蛾子的无伤真难打，不过总算全辐辉了'
  },
  {
    name: '饥荒',
    coverUrl:
      'https://ms.bdimg.com/pacific/0/pic/-371556181_731703756.jpg?x=0&y=0&h=280&w=420&vh=280.00&vw=420.00&oh=280.00&ow=420.00',
    intro: '第一个破1000小时的，打了一堆mod'
  },
  {
    name: '鬼泣5',
    coverUrl:
      'https://fc6tn.baidu.com/it/u=2841533784,834218602&fm=202?x=0&y=0&h=276&w=414&vh=276.00&vw=414.00&oh=276.00&ow=414.00',
    intro: '日常打V哥，power！！！现在只会打V哥了（悲）'
  },
  {
    name: '求生之路',
    coverUrl:
      'https://ms.bdimg.com/pacific/0/pic/-718184106_-2012063269.png?x=0&y=0&h=314&w=471&vh=314.00&vw=471.00&oh=314.00&ow=471.00',
    intro: '少女之路？秒妹递药？靓仔登场？'
  },
  {
    name: '艾尔登法环',
    coverUrl:
      'https://ms.bdimg.com/pacific/0/pic/-185332895_1529944259.jpg?x=0&y=0&h=340&w=510&vh=340.00&vw=510.00&oh=340.00&ow=510.00',
    intro:
      '直奔主线，只打拦路的怪和boss，代价是打架很刮，砍一只小狼都要七八刀，跟boss激情对线，不拉人是我最后的倔强'
  },
  {
    name: '土豆兄弟',
    coverUrl:
      'https://ms.bdimg.com/pacific/0/pic/-67815175_605267454.jpg?x=0&y=0&h=340&w=510&vh=340.00&vw=510.00&oh=340.00&ow=510.00',
    intro: '全角色最高难度通关+全成就，某些角色有些难度，总体来说难度一般'
  },
  {
    name: '杀戮尖塔',
    coverUrl: 'https://n.sinaimg.cn/spider2020624/600/w1400h800/20200624/371d-ivmqpci4969792.jpg',
    intro: '卡牌肉鸽的神，可惜我不喜欢查攻略，直到现在还没法在20难度稳定碎心'
  },
  {
    name: '戴森球计划',
    coverUrl:
      'https://img0.baidu.com/it/u=1460300916,2089502722&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500',
    intro: '挺不错，但放一段时间就不想捡起来了'
  },
  {
    name: '古剑奇谭3',
    coverUrl: 'https://img01.vgtime.com/game/cover/2018/11/30/181130125035337.jpg',
    intro:
      '玩法画面之类自然和顶尖大作没法比，但塑造的史诗感很不错，而且主角是我喜欢的那种冷静带脑子的角色'
  },

  {
    name: '哈迪斯',
    coverUrl: 'https://p4.itc.cn/images01/20211227/6524cb46ee5e480385527e9c0461f2b0.png',
    intro: '很棒的肉鸽，隐藏剧情全打了，但只打到30热难度，打60+热的真是神仙'
  },
  {
    name: '暖雪',
    coverUrl:
      'https://img1.baidu.com/it/u=2018619886,3753949899&fm=253&fmt=auto&app=120&f=JPEG?w=800&h=500',
    intro:
      '不错的国产肉鸽，对比哈迪斯应该算爽游，后面好像更新了不少东西，不过我很早就打穿没再关注了'
  },
  {
    name: '森林',
    coverUrl:
      'https://img2.baidu.com/it/u=3106061578,1641696126&fm=253&fmt=auto&app=120&f=JPEG?w=1422&h=800',
    intro: '我才是野人！'
  },
  {
    name: '森林之子',
    coverUrl:
      'https://gimg3.baidu.com/game/src=https%3A%2F%2Fgameplus-platform.cdn.bcebos.com%2Fgameplus-platform%2Fupload%2Ffile%2Fsource%2F330c9f73ebfed6e71f3d8e768f3cd611.jpeg&refer=http%3A%2F%2Fwww.baidu.com&app=2014&size=w256&n=0&g=0n&q=100&fmt=auto?sec=1700586000&t=ecbd8a10137639fa02207a35deb7667d',
    intro: '跟人联机感觉不如玩七日杀有干劲。。。'
  },
  {
    name: '七日杀',
    coverUrl:
      'https://img1.baidu.com/it/u=2020081052,3483442506&fm=253&fmt=auto&app=138&f=JPEG?w=800&h=500',
    intro: '十年更新没出正式版，画质不咋地但确实挺好玩'
  },
  {
    name: 'GTA5',
    coverUrl:
      'https://img2.baidu.com/it/u=3807605925,638915677&fm=253&fmt=auto&app=138&f=JPEG?w=889&h=500',
    intro: '没玩下去，GTA5和大表哥这种不适合我，虽说我是白嫖的'
  }
]);

const gameShowData = JSON.parse(JSON.stringify(singlePlayerGames.value)).splice(0, 8);

const MobileGames = ref([
  {
    name: '星穹铁道',
    coverUrl: 'https://ecmb.bdimg.com/tam-ogel/-1220819060_331817839_486_486.jpg',
    intro: '不错的箱庭回合制+玩梗大师'
  },
  {
    name: '明日方舟',
    coverUrl:
      'https://gimg3.baidu.com/game/src=https%3A%2F%2Fgameplus-platform.cdn.bcebos.com%2Fgameplus-platform%2Fupload%2Ffile%2Fimg%2Ffad44df25ce28a8e46a3c45ba8897393%2Ffad44df25ce28a8e46a3c45ba8897393.png&refer=http%3A%2F%2Fwww.baidu.com&app=2014&size=w256&n=0&g=0n&q=100&fmt=auto?sec=1700240400&t=c859434307861fb2001f84d250cf37d9',
    intro: '攒了二十几万玉的老咸鱼'
  },
  {
    name: '月圆之夜',
    coverUrl: 'https://n.sinaimg.cn/sinacn/w1080h738/20180205/e95a-fyrhcqy7493032.jpg',
    intro: '挺好玩，对比杀戮尖塔算爽游，难度不高'
  }
]);
const animateTimeList = ['0s5', '0s7', '1s', '1s2', '1s5', '1s7', '2s', '2s2', '2s5', '2s7', '3s'];

function getAnimateTime() {
  return `animated-${animateTimeList[Math.floor(Math.random() * 9) + 1]}`;
}

function toOfficial(item: any) {
  if (!item.url) return;
  window.open(item.url, '_blank');
}

onMounted(() => {
  getList();
});
</script>

<style lang="scss" scoped>
@include theme() {
  .game-main {
    background: transparent !important;
    backdrop-filter: none !important;
  }
  .game-top {
    height: 45vh;
    @include flex;
    border-radius: 8px;
    justify-content: space-between;
    margin-bottom: 10px;

    .game-top-item:not(:hover) {
      flex: 1;
      transition: all 0.4s linear;
    }
    .game-top-item:hover {
      // animation: slide-in 0.4s forwards linear;
      flex: 7;
      transition: all 0.4s linear;
      img {
        // filter: brightness(100%);
      }
    }
    .game-top-item {
      flex: 1;
      height: 100%;
      box-shadow: get('box-shadow');
      border-radius: 8px;
      overflow: hidden;
      margin-right: 10px;
      img {
        width: 100%;
        height: 100%;
        object-fit: cover;
        background-position: center;
      }
      &:last-child {
        margin-right: 0px;
      }
    }
  }

  .game-center {
    @include flex-column;
    .divider {
      font-size: 25px;
      color: get('font-color');
      font-weight: bold;
      width: calc(100% - 40px);
      text-align: left;
      margin: 15px 0px;
      background: get('background');
      box-shadow: get('box-shadow');
      padding: 10px;
      border-radius: 10px;
      @include flex;
      justify-content: start;
      .svg-icon-wrap {
        height: 30px;
        width: 30px;
        margin-right: 5px;
      }
      span {
        font-size: 18px;
        font-weight: 300;
        margin-left: 10px;
      }
    }
    .single-game-list {
      display: flex;
      justify-content: start;
      align-items: center;
      flex-wrap: wrap;
      width: calc(100%);
    }
    .single-game-item {
      box-shadow: get('box-shadow');
      background: get('background');
      position: relative;
      transition: all 0.2s linear;
      border-radius: 10px;
      width: calc(25% - 26px);
      margin: 10px;
      cursor: pointer;
      padding: 5px 3px;
      box-shadow: get('box-shadow');
      // height: 300px;
      aspect-ratio: 7/6;
      @include flex-column;
      justify-content: start;
      overflow: hidden;
      .el-image {
        width: calc(100% - 6px);
        border-radius: 8px;
        aspect-ratio: 7/4;
        object-fit: cover;
        background-position: center;
      }
      span {
        width: calc(100% - 20px);
        padding: 0px 10px;
        display: -webkit-box;
        -webkit-box-orient: vertical;
        -webkit-line-clamp: 2;
      }
    }
    .single-game-info {
      @include flex-column;
      transition: transform 0.3s linear;
      bottom: -6%;
      width: 100%;
      position: absolute;
      height: 60%;
      color: get('font-color');
    }
    .single-game-item:hover {
      // transform: scale(1.04);
      span {
        -webkit-line-clamp: 6;
      }
      .single-game-info {
        transform: translateY(-10px);
        background: get('background-no-tp');
      }
      img {
        filter: brightness(60%);
        // transform: scale(1.04);
      }
      // .item-cover {
      //   opacity: 1;
      //   top: 30%;
      //   filter: brightness(100%);
      // }
    }
    .item-cover {
      width: 100%;
      height: 70%;
      position: absolute;
      top: 320px;
      transition: all 0.5s linear;
      opacity: 0;
      background: get('background-no-tp');
      @include flex;
    }
  }
}
</style>
