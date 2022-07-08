import Vue from 'vue'
import VueRouter from 'vue-router'
Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'Main',
        component: () => import('@/views/Main.vue'),
        redirect: '/Home',
        children: [
            {
                path: '/LogIn',
                name: 'LogIn',
                component: () => import('@/views/LogIn/LogIn.vue'),
            },
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

const router = new VueRouter({
    mode: 'history',
    routes
})

export default router
