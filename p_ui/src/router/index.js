import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
    // {
    //     path: '/',
    //     name: 'login',
    //     component: () => import('@/views/LogIn/LogIn.vue'),
    // },
    {
        // path: '/Main',
        path: '/',
        name: 'Main',
        component: () => import('@/views/Main.vue'),
        children: [
            {
                path: '/Home',
                name: 'Home',
                component: () => import('@/views/Home/Home.vue'),
            },
            {
                path: '/Users',
                name: 'Users',
                component: () => import('@/views/Users/Users.vue'),
            },
            {
                path: '/Assembly',
                name: 'Assembly',
                component: () => import('@/views/Assembly/Assembly.vue'),
                children: [
                    {
                        path: '/AntvG6',
                        name: 'AntvG6',
                        component: () => import('@/views/Assembly/AntvG6/FlowChart.vue'),
                    },
                    {
                        path: '/Cascader',
                        name: 'Cascader',
                        component: () => import('@/views/Assembly/Cascader/Cascader.vue'),
                    }, {
                        path: '/DatePicker',
                        name: 'DatePicker',
                        component: () => import('@/views/Assembly/DatePicker/DatePicker.vue'),
                    },
                    {
                        path: '/MonacoEditor',
                        name: 'MonacoEditor',
                        component: () => import('@/views/Assembly/MonacoEditor/MonacoEditor.vue'),
                    },
                    {
                        path: '/SelectTree',
                        name: 'SelectTree',
                        component: () => import('@/views/Assembly/SelectTree/SelectTree.vue'),
                    },
                    {
                        path: '/TestComponennts',
                        name: 'TestComponennts',
                        component: () => import('@/views/Assembly/TestComponennts/Test.vue'),
                    },
                    {
                        path: '/Other',
                        name: 'Other',
                        component: () => import('@/views/Assembly/Other/xz(yq)spb.vue'),
                    },
                ]
            },
            {
                path: '/Blog',
                name: 'Blog',
                component: () => import('@/views/Blog/Blog.vue'),
            },
            {
                path: '/BlogEditor',
                name: 'BlogEditor',
                component: () => import('@/views/Blog/BlogEditor.vue'),
            }
        ]
    },
]
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push (location) {
    return originalPush.call(this, location).catch(err => err)
}
const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
