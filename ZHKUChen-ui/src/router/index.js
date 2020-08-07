import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router)

/* Layout */
import Layout from '@/layout'

/**
 * Note: 路由配置项
 *
 * hidden: true                   // 当设置 true 的时候该路由不会再侧边栏出现 如401，login等页面，或者如一些编辑页面/edit/1
 * alwaysShow: true               // 当你一个路由下面的 children 声明的路由大于1个时，自动会变成嵌套的模式--如组件页面
 *                                // 只有一个时，会将那个子路由当做根路由显示在侧边栏--如引导页面
 *                                // 若你想不管路由下面的 children 声明的个数都显示你的根路由
 *                                // 你可以设置 alwaysShow: true，这样它就会忽略之前定义的规则，一直显示根路由
 * redirect: noRedirect           // 当设置 noRedirect 的时候该路由在面包屑导航中不可被点击
 * name:'router-name'             // 设定路由的名字，一定要填写不然使用<keep-alive>时会出现各种问题
 * meta : {
    roles: ['admin','editor']    // 设置该路由进入的权限，支持多个权限叠加
    title: 'title'               // 设置该路由在侧边栏和面包屑中展示的名字
    icon: 'svg-name'             // 设置该路由的图标，对应路径src/icons/svg
    breadcrumb: false            // 如果设置为false，则不会在breadcrumb面包屑中显示
  }
 */

// 公共路由
export const constantRoutes = [
    {
        path: '/redirect',
        component: Layout,
        hidden: true,
        children: [{
            path: '/redirect/:path*',
            component: () =>
                import ('@/views/redirect')
        }]
    },
    {
        path: '/',
        component: () =>
            import ('@/views/home.vue'),
        hidden: true
    },
    {
        path: '/admin/login',
        component: () =>
            import ('@/views/admin/login'),
        hidden: true
    },
    {
        path: '/company/login',
        component: () =>
            import ('@/views/company/login/index.vue'),
        hidden: true
    },
    {
        path: '/ordinaryUser/login',
        component: () =>
            import ('@/views/ordinaryUser/login/index.vue'),
        hidden: true
    },
    {
        path: '/company',
        component: Layout,
        //redirect: 'index',
        hidden: true,
        children: [
            {
                path: 'index',
                component: () =>
                    import ('@/views/company/index'),
                name: '工作台',
                icon: 'dashboard',
                noCache: true,
                affix: true,
                meta: { title: '工作台'}
            },
        ]
    },
    {
        path: '/ordinaryUser',
        component: Layout,
        //redirect: 'index',
        hidden: true,
        children: [
            {
                path: 'index',
                component: () =>
                    import ('@/views/ordinaryUser/index'),
                name: '用户工作台',
                icon: 'dashboard',
                noCache: true,
                affix: true,
                meta: { title: '用户工作台'}
            },
        ]
    },
    {
        path: '/company/register',
        component: () =>
            import ('@/views/company/register/index.vue'),
        hidder:true
    },
    {
        path: '/ordinaryUser/register',
        component: () =>
            import ('@/views/ordinaryUser/register/index.vue'),
        hidder:true
    },
    {
        path: '/404',
        component: () =>
            import ('@/views/error/404'),
        hidden: true
    },
    {
        path: '/401',
        component: () =>
            import ('@/views/error/401'),
        hidden: true
    },
    {
        path: '/admin',
        component: Layout,
        //redirect: 'index',
        hidden: true,
        children: [
        {
            path: 'index',
            component: () =>
                import ('@/views/admin/index'),
            name: '首页',
            meta: { title: '首页', icon: 'dashboard', noCache: true, affix: true }
        }]
    },
    {
        path: '/user',
        component: Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [{
            path: 'profile',
            component: () =>
                import ('@/views/system/user/profile/index'),
            name: 'Profile',
            meta: { title: '个人中心', icon: 'user' }
        }]
    },
    {
        path: '/dict',
        component: Layout,
        hidden: true,
        children: [{
            path: 'type/data/:dictId(\\d+)',
            component: () =>
                import ('@/views/system/dict/data'),
            name: 'Data',
            meta: { title: '字典数据', icon: '' }
        }]
    },
    {
        path: '/gen',
        component: Layout,
        hidden: true,
        children: [{
            path: 'edit',
            component: () =>
                import ('@/views/tool/gen/editTable'),
            name: 'GenEdit',
            meta: { title: '修改生成配置' }
        }]
    },
    {
        path: '/notice',
        component: Layout,
        //redirect: 'index',
        hidden: true,
        children: [
            {
                path: 'index',
                component: () =>
                    import ('@/views/system/notice/index'),
            }
        ]
    },
    {
        path: '/ordinaryUser/exams',
        component: () =>
            import ('@/views/ordinaryUser/exams/index'),
        hidden: true
    },
    {
        path: '/ordinaryUser/examRecord',
        component: () =>
            import ('@/views/ordinaryUser/examRecord/index'),
        hidden: true
    },
    {
        path: '/ordinaryUser/exams/start/:id',
        component: () =>
          import ('@/views/ordinaryUser/startExam'),
        hidden: true,
    },
    {
        path: '/ordinaryUser/examRecord/detail/:id',
        component: () =>
          import ('@/views/ordinaryUser/examScoreDetails'),
        hidden: true,
    },
    {
        path: '/assess/examManage/subjects',
        component:Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [{
            path: ':id',
            component: () =>
                import ('@/views/assess/examManage/examSubjects'),
            name: 'subjects',
            meta: { title: '考试题目管理', icon: 'user' }
        }]
    },
    {
        path: '/exam/score/detail',
        component:Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [{
            path: ':id',
            component: () =>
                import ('@/views/exam/scoreDetails'),
            name: 'detail',
            meta: { title: '成绩详情', icon: 'user' }
        }]
    },
    {
        path: '/assess/examManage/addSubjects',
        hidden: true,
        component: Layout,
        redirect: 'noredirect',
        children: [
            {
                path: ':id',
                component: () =>
                    import ('@/views/assess/examSubject'),
                name: 'addSubjects',
                meta: { title: '添加题目',icon: 'user'}
            }
        ]
    },
    {
        path: '/exam/mark',
        component:Layout,
        hidden: true,
        redirect: 'noredirect',
        children: [{
            path: ':id',
            component: () =>
                import ('@/views/exam/markExam'),
            name: 'mark',
            meta: { title: '批改', icon: 'user' }
        }]
    },
]

export default new Router({
    mode: 'history', // 去掉url中的#
    scrollBehavior: () => ({ y: 0 }),
    routes: constantRoutes
})