import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from "../views/Login.vue";


Vue.use(VueRouter)

const routes = [
	{
		path:'/all',
		name:'guanli',
		component: () => import('../views/All.vue'),
		redirect: "/all/index",
		children:[
			{
				path: '/all/index',
				name: '首页管理',
				icon: 'el-icon-s-home',
				component: () => import( '../views/index.vue')
			},
			{
				path: '/all/role',
				name: '角色管理',
				icon: 'el-icon-user',
				component: () => import( '../views/Role.vue')
			},
			{
				path: '/all/workersManagement',
				name: '用户管理',
				icon: 'el-icon-user-solid',
				component: () => import( '../views/WorkersManagement.vue')
			},
			{
				path: '/all/rankings',
				name: '总排行榜',
				icon: 'el-icon-s-order',
				component: () => import( '../views/rankings.vue')
			},
			{
				path: '/all/gradesInformation',
				name: '成绩信息',
				icon: 'el-icon-postcard',
				component: () => import('../views/gradesInformation.vue')
			},
			{
				path: '/all/systemNotice',
				name: '系统通知',
				icon: 'el-icon-chat-line-round',
				component: () => import( '../views/SystemNotice.vue')
			},
		]
	},
	{
			path: '/',
			name: 'Login',
			component: Login
	}
]

const router = new VueRouter({
	mode: 'history',
	base: process.env.BASE_URL,
	routes
})
router.beforeEach(function(to, from, next) {

    if (!localStorage.getItem("userName")) {
        if (to.path !== '/') {
            return next('/')
        }
    }
    next()
})

export default router
