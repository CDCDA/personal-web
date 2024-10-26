import { createRouter, createWebHashHistory, RouteRecordRaw } from 'vue-router';
import useThemeStore from '@/store/modules/theme.ts';
import { autoClearTimer } from '@/utils/timer';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'login',
    meta: { remark: '登录', isHidden: true, cache: false },
    component: () => import('@/views/login/login.vue')
  },
  {
    path: '/home',
    name: 'home',
    meta: { remark: '首页', preload: true },
    component: () => import(/* webpackChunkName: "about" */ '@/views/home/index.vue')
  },
  {
    path: '/manage',
    name: 'manage',
    meta: { remark: '管理页', isHidden: true },
    component: () => import('@/views/manage/index.vue'),
    children: [
      {
        path: '/personalInfo',
        name: 'personalInfo',
        meta: { remark: '个人信息', isHidden: true, svgIcon: '人员信息' },
        component: () => import('@/views/manage/personalInfo/index.vue')
      },
      {
        path: '/blogManage',
        name: 'blogManage',
        meta: { remark: '博客管理', isHidden: true, svgIcon: '博客' },
        component: () => import('@/views/manage/blogManage/index.vue')
      },
      {
        path: '/typeManage',
        name: 'typeManage',
        meta: { remark: '分类管理', isHidden: true, svgIcon: '分类' },
        component: () => import('@/views/manage/typeManage/index.vue')
      },
      {
        path: '/tagManage',
        name: 'tagManage',
        meta: { remark: '标签管理', isHidden: true, svgIcon: '标签' },
        component: () => import('@/views/manage/tagManage/index.vue')
      },
      {
        path: '/essayManage',
        name: 'essayManage',
        meta: { remark: '随笔管理', isHidden: true, svgIcon: '随笔' },
        component: () => import('@/views/manage/essayManage/index.vue')
      },
      {
        path: '/albumManage',
        name: 'albumManage',
        meta: { remark: '相册管理', isHidden: true, svgIcon: '相册' },
        component: () => import('@/views/manage/albumManage/index.vue')
      },
      {
        path: '/gameManage',
        name: 'gameManage',
        meta: { remark: '游戏管理', isHidden: true, svgIcon: '游戏' },
        component: () => import('@/views/manage/gameManage/index.vue')
      },
      {
        path: '/dramaManage',
        name: 'dramaManage',
        meta: { remark: '影视管理', isHidden: true, svgIcon: '影视' },
        component: () => import('@/views/manage/dramaManage/index.vue')
      },
      {
        path: '/gourmetManage',
        name: 'gourmetManage',
        meta: { remark: '美食管理', isHidden: true, svgIcon: '美食' },
        component: () => import('@/views/manage/gourmetManage/index.vue')
      },
      {
        path: '/musicManage',
        name: 'musicManage',
        meta: { remark: '音乐管理', isHidden: true, svgIcon: '音乐' },
        component: () => import('@/views/manage/musicManage/index.vue')
      },
      {
        path: '/wallpaperManage',
        name: 'wallpaperManage',
        meta: { remark: '壁纸管理', isHidden: true, svgIcon: '壁纸' },
        component: () => import('@/views/manage/wallpaperManage/index.vue')
      },
      {
        path: '/dictManage',
        name: 'dictManage',
        meta: { remark: '字典管理', isHidden: true, svgIcon: '字典' },
        component: () => import('@/views/manage/dictManage/index.vue')
      },
      {
        path: '/logManage',
        name: 'logManage',
        meta: { remark: '更新日志管理', isHidden: true, svgIcon: '日志' },
        component: () => import('@/views/manage/logManage/index.vue')
      }
    ]
  },
  {
    path: '/profile',
    name: 'profile',
    meta: { remark: '', isHidden: true },
    component: () => import('@/views/profile/index.vue')
  },

  {
    path: '/blogEditor',
    name: 'blogEditor',
    meta: { remark: '博客编辑' },
    component: () => import('@/views/blog/blogEditor/index.vue')
  },
  {
    path: '/blogDisplay',
    name: 'blogDisplay',
    meta: { remark: '博客展示' },
    component: () => import('@/views/blog/blogDisplay.vue')
  },
  {
    path: '/blogEditor',
    name: 'blogEditor',
    meta: { remark: '博客编辑' },
    component: () => import('@/views/blog/blogEditor/index.vue')
  },
  {
    path: '/blogType',
    name: 'blogType',
    meta: { remark: '分类', icon: 'FolderOpened', parent: 'blog' },
    component: () => import('@/views/blog/blogType/index.vue')
  },
  {
    path: '/blogTypePage',
    name: 'blogTypePage',
    meta: { remark: '博客分类详情' },
    component: () => import('@/views/blog/blogType/blogTypePage.vue')
  },
  {
    path: '/blogTag',
    name: 'blogTag',
    meta: { remark: '标签', icon: 'PriceTag', parent: 'blog' },
    component: () => import('@/views/blog/blogTag/index.vue')
  },
  {
    path: '/blogTagPage',
    name: 'blogTagPage',
    meta: { remark: '博客标签详情' },
    component: () => import('@/views/blog/blogTag/blogTagPage.vue')
  },
  {
    path: '/statistics',
    name: 'statistics',
    meta: { remark: '统计', icon: 'Histogram', parent: 'blog' },
    component: () => import('@/views/blog/statistics/index.vue')
  },
  {
    path: '/album',
    name: 'album',
    meta: { remark: '相册', icon: 'Camera', parent: 'user' },
    component: () => import('@/views/user/album/index.vue')
  },
  {
    path: '/albumPage',
    name: 'albumPage',
    meta: { remark: '相册详情' },
    component: () => import('@/views/user/album/albumPage.vue')
  },
  {
    path: '/essay',
    name: 'essay',
    meta: { remark: '随笔', icon: 'Notebook', parent: 'user' },
    component: () => import('@/views/user/essay/index.vue')
  },
  {
    path: '/essayEditor',
    name: 'essayEditor',
    meta: { remark: '随笔编辑' },
    component: () => import('@/views/user/essay/essayEditor.vue')
  },
  {
    path: '/equipment',
    name: 'equipment',
    meta: { remark: '装备', icon: 'Suitcase', parent: 'user' },
    component: () => import('@/views/user/equipment/index.vue')
  },
  {
    path: '/music',
    name: 'music',
    meta: { remark: '音乐', icon: 'Headset', parent: 'user' },
    component: () => import('@/views/user/music/index.vue')
  },
  {
    path: '/svg',
    name: 'svg',
    meta: { remark: 'svg集合', svgIcon: 'svg图片', parent: 'assembly' },
    component: () => import('@/views/assembly/svg/index.vue')
  },
  {
    path: '/slice',
    name: 'slice',
    meta: { remark: '切片', icon: 'Orange', parent: 'assembly' },
    component: () => import('@/views/assembly/slice/index.vue'),
    children: [
      {
        path: '/rubiks',
        name: 'rubiks',
        meta: {
          remark: '魔方',
          introduction: '普通3x3动态魔方',
          url: 'http://1.92.159.74:8008/魔方.png'
        },
        component: () => import('@/views/assembly/slice/rubiks/index.vue')
      },
      {
        path: '/rotatingRubik',
        name: 'rotatingRubik',
        meta: {
          remark: '图片旋转魔方',
          introduction: '可旋转展示6面图片的魔方',
          url: 'http://1.92.159.74:8008/图片旋转魔方.png'
        },
        component: () => import('@/views/assembly/slice/rotatingRubik/index.vue')
      },
      {
        path: '/3dMenu',
        name: '3dMenu',
        meta: {
          remark: '3d菜单',
          introduction: '3d菜单',
          url: 'http://1.92.159.74:8008/3d菜单.png'
        },
        component: () => import('@/views/assembly/slice/3dMenu/index.vue')
      },
      {
        path: '/cardFlip',
        name: 'cardFlip',
        meta: {
          remark: '卡片翻转',
          introduction: '卡片翻转',
          url: 'http://1.92.159.74:8008/卡片翻转.png'
        },
        component: () => import('@/views/assembly/slice/cardFlip/index.vue')
      },
      {
        path: '/lightButton',
        name: 'lightButton',
        meta: {
          remark: '闪光边框按钮',
          introduction: '文字滚动',
          url: 'http://1.92.159.74:8008/文字滚动.png'
        },
        component: () => import('@/views/assembly/slice/lightButton/index.vue')
      },
      {
        path: '/rollText',
        name: 'rollText',
        meta: {
          remark: '文字滚动',
          introduction: '文字滚动',
          url: 'http://1.92.159.74:8008/文字滚动.png'
        },
        component: () => import('@/views/assembly/slice/rollText/index.vue')
      },
      {
        path: '/heartLoading',
        name: 'heartLoading',
        meta: {
          remark: '心型加载',
          introduction: '心型加载',
          url: 'http://1.92.159.74:8008/心型加载.png'
        },
        component: () => import('@/views/assembly/slice/heartLoading/index.vue')
      },
      {
        path: '/neonRain',
        name: 'neonRain',
        meta: { remark: '霓虹雨', introduction: '雨', url: 'http://1.92.159.74:8008/雨.png' },
        component: () => import('@/views/assembly/slice/neonRain/index.vue')
      },
      {
        path: '/sideNav',
        name: 'sideNav',
        meta: {
          remark: '侧边导航',
          introduction: '侧边导航',
          url: 'http://1.92.159.74:8008/雨.png'
        },
        component: () => import('@/views/assembly/slice/sideNav/index.vue')
      },
      {
        path: '/stackCard',
        name: 'stackCard',
        meta: {
          remark: '堆叠卡',
          introduction: '堆叠卡',
          url: 'http://1.92.159.74:8008/雨.png'
        },
        component: () => import('@/views/assembly/slice/stackCard/index.vue')
      },
      {
        path: '/rotateMenu',
        name: 'rotateMenu',
        meta: {
          remark: '旋转菜单',
          introduction: '旋转菜单',
          url: 'http://1.92.159.74:8008/雨.png'
        },
        component: () => import('@/views/assembly/slice/rotateMenu/index.vue')
      }
    ]
  },
  {
    path: '/testField',
    name: 'testField',
    meta: { remark: '试验田', icon: 'OfficeBuilding', parent: 'assembly' },
    component: () => import('@/views/assembly/testField/index.vue'),
    children: [
      {
        path: '/vForm',
        name: 'vForm',
        meta: { remark: '低代码表单' },
        component: () => import('@/views/assembly/testField/vForm/index.vue')
      },
      {
        path: '/draggle',
        name: 'draggle',
        meta: { remark: '自由拖拽' },
        component: () => import('@/views/assembly/testField/draggle/index.vue')
      },
      {
        path: '/ganttChart',
        name: 'ganttChart',
        meta: { remark: '甘特图' },
        component: () => import('@/views/assembly/testField/dhxGanttChart/index.vue')
      },
      {
        path: '/editor',
        name: 'editor',
        meta: { remark: '富文本编辑器' },
        component: () => import('@/views/assembly/testField/editor/index.vue')
      },
      {
        path: '/canvas',
        name: 'canvas',
        meta: { remark: 'canvas动画' },
        component: () => import('@/components/sakura/index.vue')
      }
    ]
  },
  {
    path: '/personalProfile',
    name: 'personalProfile',
    meta: { remark: '个人信息', icon: 'User', parent: 'intro' },
    component: () => import('@/views/introduction/personalProfile/index.vue')
  },
  // {
  //   path: '/projectExperience',
  //   name: 'projectExperience',
  //   meta: { remark: '项目经历' },
  //   component: () => import('@/views/introduction/projectExperience/index.vue')
  // },
  // {
  //   path: '/fitness',
  //   name: 'fitness',
  //   meta: { remark: '运动', svgIcon: '运动', parent: 'other' },
  //   component: () => import('@/views/others/fitness/index.vue')
  // },
  {
    path: '/game',
    name: 'game',
    meta: { remark: '游戏', svgIcon: '游戏', parent: 'other' },
    component: () => import('@/views/others/game/index.vue')
  },
  {
    path: '/drama',
    name: 'drama',
    meta: { remark: '影视', icon: 'VideoCamera', parent: 'other' },
    component: () => import('@/views/others/dramaSeries/index.vue')
  },
  // {
  //   path: '/gourmet',
  //   name: 'gourmet',
  //   meta: { remark: '美食', icon: 'KnifeFork', parent: 'other' },
  //   component: () => import('@/views/others/gourmet/index.vue')
  // },
  {
    path: '/website',
    name: 'website',
    meta: { remark: '本站', svgIcon: '关于', parent: 'associate' },
    component: () => import('@/views/associate/website/index.vue')
  },
  {
    path: '/updateLog',
    name: 'updateLog',
    meta: { remark: '更新日志', svgIcon: '日志', parent: 'associate' },
    component: () => import('@/views/associate/updateLog/index.vue')
  },
  {
    path: '/refresh',
    name: 'refresh',
    component: () => import('@/components/blankTemplate.vue')
  },
  {
    path: '/:catchAll(.*)',
    name: '404',
    meta: { remark: '', isHidden: true },
    component: () => import('@/views/error/index.vue')
  },
  {
    path: '/error',
    name: 'error',
    meta: { remark: '', isHidden: true },
    component: () => import('@/views/error/index.vue')
  }
];
const router = createRouter({
  history: createWebHashHistory('/'),
  routes
});

const navShowRoute = ['login', 'register'];

//切换路由后回到顶部
router.afterEach((to: any) => {
  // console.log('当前路由', to);
  scrollToView();
  to = to;
  var themeStore = useThemeStore();
  if (!themeStore) return;
  if (navShowRoute.includes(to.name)) {
    themeStore.isShow = false;
    themeStore.isFooterShow = false;
  }
  // else if (to.name.includes('anage') || to.name.includes('personalInfo')) {
  //   themeStore.isFooterShow = false;
  //   themeStore.isShow = true;
  // }
  else {
    themeStore.isShow = true;
    autoClearTimer(() => {
      themeStore.isFooterShow = true;
    }, 3000);
  }
});

// 滚动到指定的位置
function scrollToView() {
  let el = document.querySelector('.el-main') as any;
  el?.scrollTo({ top: 0, behavior: 'smooth' });
}

export default router;
