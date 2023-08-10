import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router';

const routes: Array<RouteRecordRaw> = [
  // {
  //     path: '/',
  //     name: 'login',
  //     component: () => import('@/views/LogIn/LogIn.vue'),
  // },
  // {
  //   path: '/preView',
  //   name: 'preView',
  //   component: () => import('@/views/preView.vue')
  // },
  {
    // path: '/Main',
    path: '/',
    name: 'Main',
    component: () => import('@/views/main.vue'),
    children: [
      {
        path: '/Home',
        name: 'Home',
        component: () => import('@/views/Home/Home.vue')
      },
      {
        path: '/Statistics',
        name: 'Statistics',
        component: () => import('@/views/Statistics/index.vue')
      },
      {
        path: '/User',
        name: 'User',
        component: () => import('@/views/User/index.vue'),
        children: [
          {
            path: '/PersonalPage',
            name: 'PersonalPage',
            component: () => import('@/views/User/components/personalPage.vue')
          },
          {
            path: '/BlogMagage',
            name: 'BlogMagage',
            component: () => import('@/views/User/components/blogMagage.vue')
          },
          {
            path: '/TypeMagage',
            name: 'TypeMagage',
            component: () => import('@/views/User/components/typeManage.vue')
          }
        ]
      },
      {
        path: '/Profile',
        name: 'Profile',
        component: () => import('@/views/Profile/index.vue')
      },
      {
        path: '/Assembly',
        name: 'Assembly',
        component: () => import('@/views/Assembly/index.vue'),
        children: [
          // {
          //   path: '/AntvG6',
          //   name: 'AntvG6',
          //   component: () => import('@/views/Assembly/AntvG6/FlowChart.vue')
          // },
          {
            path: '/Cascader',
            name: 'Cascader',
            component: () => import('@/views/Assembly/Cascader/Cascader.vue')
          },
          // {
          //   path: '/DatePicker',
          //   name: 'DatePicker',
          //   component: () => import('@/views/Assembly/DatePicker/DatePicker.vue')
          // },
          // {
          //   path: '/MonacoEditor',
          //   name: 'MonacoEditor',
          //   component: () => import('@/views/Assembly/MonacoEditor/MonacoEditor.vue')
          // },
          // {
          //   path: '/SelectTree',
          //   name: 'SelectTree',
          //   component: () => import('@/views/Assembly/SelectTree/SelectTree.vue')
          // },
          // {
          //   path: '/TestComponennts',
          //   name: 'TestComponennts',
          //   component: () => import('@/views/Assembly/TestComponennts/Test2.vue')
          // },
          {
            path: '/VForm',
            name: 'VForm',
            component: () => import('@/views/Assembly/VForm/index.vue')
          },
          {
            path: '/Rubiks',
            name: 'Rubiks',
            component: () => import('@/views/Assembly/Rubiks/index.vue')
          }
          // {
          //   path: '/Other',
          //   name: 'Other',
          //   component: () => import('@/views/Assembly/Other/xz(yq)spb.vue')
          // }
        ]
      },
      {
        path: '/Blog',
        name: 'Blog',
        component: () => import('@/views/Blog/Blog.vue')
      },
      {
        path: '/BlogEditor',
        name: 'BlogEditor',
        component: () => import('@/views/Blog/BlogEditor/index.vue')
      },
      {
        path: '/BlogDisplay',
        name: 'BlogDisplay',
        component: () => import('@/views/Blog/BlogDisplay.vue')
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
