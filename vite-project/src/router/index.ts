import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  {
    path: '/login',
    name: 'login',
    component: () => import('@/views/login/login.vue')
  },
  // {
  //   path: '/preView',
  //   name: 'preView',
  //   component: () => import('@/views/preView.vue')
  // },
  {
    // path: '/Main',
    path: '/',
    name: 'main',
    component: () => import('@/views/main.vue'),
    children: [
      {
        path: '/home',
        name: 'home',
        component: () => import('@/views/home/index.vue')
      },
      {
        path: '/draggle',
        name: 'draggle',
        component: () => import('@/views/draggle/draggle.vue')
      },
      {
        path: '/statistics',
        name: 'statistics',
        component: () => import('@/views/statistics/index.vue')
      },
      {
        path: '/user',
        name: 'user',
        component: () => import('@/views/user/index.vue'),
        children: [
          {
            path: '/personalPage',
            name: 'personalPage',
            component: () => import('@/views/user/components/personalPage.vue')
          },
          {
            path: '/blogMagage',
            name: 'blogMagage',
            component: () => import('@/views/user/components/blogMagage.vue')
          },
          {
            path: '/typeMagage',
            name: 'typeMagage',
            component: () => import('@/views/user/components/typeManage.vue')
          }
        ]
      },
      {
        path: '/profile',
        name: 'profile',
        component: () => import('@/views/profile/index.vue')
      },
      {
        path: '/assembly',
        name: 'assembly',
        component: () => import('@/views/assembly/index.vue'),
        children: [
          // {
          //   path: '/AntvG6',
          //   name: 'AntvG6',
          //   component: () => import('@/views/Assembly/AntvG6/flowChart.vue')
          // },
          {
            path: '/cascader',
            name: 'cascader',
            component: () => import('@/views/assembly/cascader/cascader.vue')
          },
          // {
          //   path: '/DatePicker',
          //   name: 'DatePicker',
          //   component: () => import('@/views/assembly/datePicker/datePicker.vue')
          // },
          // {
          //   path: '/MonacoEditor',
          //   name: 'MonacoEditor',
          //   component: () => import('@/views/assembly/monacoEditor/monacoEditor.vue')
          // },
          // {
          //   path: '/SelectTree',
          //   name: 'SelectTree',
          //   component: () => import('@/views/assembly/selectTree/selectTree.vue')
          // },
          {
            path: '/testComponents',
            name: 'testComponents',
            component: () => import('@/views/assembly/testComponents/test2.vue')
          },
          {
            path: '/vForm',
            name: 'vForm',
            component: () => import('@/views/assembly/vForm/index.vue')
          },
          {
            path: '/rubiks',
            name: 'rubiks',
            component: () => import('@/views/assembly/rubiks/index.vue')
          }
          // {
          //   path: '/Other',
          //   name: 'Other',
          //   component: () => import('@/views/assembly/Other/xz(yq)spb.vue')
          // }
        ]
      },
      {
        path: '/blog',
        name: 'blog',
        component: () => import('@/views/blog/blog.vue')
      },
      {
        path: '/blogEditor',
        name: 'blogEditor',
        component: () => import('@/views/blog/blogEditor/index.vue')
      },
      {
        path: '/blogDisplay',
        name: 'blogDisplay',
        component: () => import('@/views/blog/blogDisplay.vue')
      },
      {
        path: '/blogType',
        name: 'blogType',
        component: () => import('@/views/blog/blogType.vue')
      },
      {
        path: '/blogTag',
        name: 'blogTag',
        component: () => import('@/views/blog/blogTag.vue')
      },
      {
        path: '/album',
        name: 'album',
        component: () => import('@/views/user/album.vue')
      },
      {
        path: '/casualEssay',
        name: 'casualEssay',
        component: () => import('@/views/user/casualEssay.vue')
      },
      {
        path: '/equipment',
        name: 'equipment',
        component: () => import('@/views/user/equipment.vue')
      },
      {
        path: '/music',
        name: 'music',
        component: () => import('@/views/user/music.vue')
      },
      {
        path: '/slice',
        name: 'slice',
        component: () => import('@/views/assembly/slice/index.vue'),
        children: [
          {
            path: '/rotatingRubik',
            name: 'rotatingRubik',
            component: () => import('@/views/assembly/slice/rotatingRubik/index.vue')
          }
        ]
      },
      {
        path: '/testField',
        name: 'testField',
        component: () => import('@/views/assembly/testField/index.vue'),
        children: [
          {
            path: '/vForm',
            name: 'vForm',
            component: () => import('@/views/assembly/testField/vForm/index.vue')
          }
        ]
      },
      {
        path: '/personalProfile',
        name: 'personalProfile',
        meta: { remark: '个人信息' },
        component: () => import('@/views/introduction/personalProfile/index.vue')
      },
      {
        path: '/technologyStack',
        name: 'technologyStack',
        meta: { remark: '技术栈' },
        component: () => import('@/views/introduction/technologyStack/index.vue')
      },
      {
        path: '/projectExperience',
        name: 'projectExperience',
        meta: { remark: '项目经历' },
        component: () => import('@/views/introduction/projectExperience/index.vue')
      }
    ]
  }
];

// const path =
//   process.env.NODE_ENV === "development" ? "/app-vue/" : "/lowcode/app-vue/";

// console.log("PATH", path);

const router = createRouter({
  history: createWebHistory('/'),
  routes
});

// router.beforeEach((to, from, next) => {
//   /**
//    * @todo 后续需要使用 window.history.pushState方法对路由方法进行深度改造
//    *
//    * 判断是否为微前端的 '发布表单' 生成的复用页面
//    * 数据会在点击菜单切换时将id写入sessionStorage，然后从这获取
//    */
//   // const hit = /\/viewTemplate/;
//   // if ((!Object.keys(to.query).length) && hit.test(to.path)) {
//   //   const id: any = sessionStorage.getItem('id');
//   //   next({ path: to.path, query: JSON.parse(id) });
//   //   return;
//   // }
//   next();
// });

// export default router;

// import Vue from 'vue'
// import VueRouter from 'vue-router'
// Vue.use(VueRouter)

// const originalPush = VueRouter.prototype.push
// VueRouter.prototype.push = function push (location) {
//     return originalPush.call(this, location).catch(err => err)
// }
// const router = createRouter({
//     mode: 'history',
//     routes
// })

export default router;
