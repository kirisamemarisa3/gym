import Vue from 'vue'
import VueRouter from 'vue-router'

// 管理员页面
import Index from "@/views/admin/Index.vue";
import MemberManage from "@/views/admin/MemberManage";
import AddMembers from "@/views/admin/AddMembers";
import CourseManage from "@/views/admin/CourseManage";
import EquipmentManage from "@/views/admin/EquipmentManage";
import EmployeeManage from "@/views/admin/EmployeeManage";
import AddEmployee from "@/views/admin/AddEmployee";
import RegisterManage from "@/views/admin/RegisterManage";
import Echarts from "@/views/admin/Echarts";

// 布局组件
import Layout from "@/layout/Layout";
import MemberLayout from "@/layout/MemberLayout";

// 登录 & 错误页
import Login from "@/views/Login";
import Error from "@/components/Error";

// 会员页面
import VipCard from "@/views/member/VipCard";
import RechargeRecord from "@/views/member/RechargeRecord";
import OnlinePay from "@/views/member/OnlinePay";
import MyProfile from "@/views/member/MyProfile";
import ChangePassword from "@/views/member/ChangePassword";
import BuyRecord from "@/views/member/BuyRecord";
import AllCourse from "@/views/member/allCourse";
import PreRun from "@/views/member/PreRun.vue";

Vue.use(VueRouter)

// 解决重复点击导航报错
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

const routes = [
  {
    path: '/layout',
    name: 'Layout',
    component: Layout,
    redirect: '/layout/index',
    children: [
      { path: 'index', name: 'Index', component: Index },
      { path: 'memberManage', name: 'MemberManage', component: MemberManage },
      { path: 'addMembers', name: 'AddMembers', component: AddMembers },
      { path: 'courseManage', name: 'CourseManage', component: CourseManage },
      { path: 'equipmentManage', name: 'EquipmentManage', component: EquipmentManage },
      { path: 'employeeManage', name: 'EmployeeManage', component: EmployeeManage },
      { path: 'addEmployee', name: 'AddEmployee', component: AddEmployee },
      { path: 'registerManage', name: 'RegisterManage', component: RegisterManage },
      { path: 'echarts', name: 'Echarts', component: Echarts }
    ]
  },
  {
    path: '/memberLayout',
    name: 'MemberLayout',
    component: MemberLayout,
    redirect: '/memberLayout/allCourse',
    children: [
      { path: 'allCourse', name: 'AllCourse', component: AllCourse },
      { path: 'preRun', name: 'PreRun', component: PreRun },
      { path: 'buyRecord', name: 'BuyRecord', component: BuyRecord },
      { path: 'changePassword', name: 'ChangePassword', component: ChangePassword },
      { path: 'myProfile', name: 'MyProfile', component: MyProfile },
      { path: 'onlinePay', name: 'OnlinePay', component: OnlinePay },
      { path: 'rechargeRecord', name: 'RechargeRecord', component: RechargeRecord },
      { path: 'vipCard', name: 'VipCard', component: VipCard }
    ]
  },
  {
    path: '/login',
    name: 'Login',
    component: Login
  },
  {
    path: '/error',
    name: 'Error',
    component: Error
  },
  {
    path: '/',
    redirect: '/login' // 默认进入登录页
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

// ✅ 修复后的导航守卫 —— 与你的 Login.vue 存储方式一致
router.beforeEach((to, from, next) => {
  const adminInfo = localStorage.getItem('access-admin');
  const memberInfo = localStorage.getItem('access-member');

  const isAdminLoggedIn = adminInfo && adminInfo !== 'null' && adminInfo !== 'undefined';
  const isMemberLoggedIn = memberInfo && memberInfo !== 'null' && memberInfo !== 'undefined';

  // 如果目标是登录页
  if (to.path === '/login') {
    if (isAdminLoggedIn || isMemberLoggedIn) {
      // 已登录，跳转到对应首页
      if (isAdminLoggedIn) {
        next('/layout/index');
      } else if (isMemberLoggedIn) {
        next('/memberLayout/allCourse');
      } else {
        next(); // 安全兜底
      }
    } else {
      next(); // 未登录，允许进入登录页
    }
    return;
  }

  // 如果目标不是登录页
  if (isAdminLoggedIn || isMemberLoggedIn) {
    next(); // 已登录，放行
  } else {
    next('/login'); // 未登录，强制跳转到登录页
  }
});

export default router