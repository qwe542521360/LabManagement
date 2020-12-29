import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import StuExpPrst from '../views/StuExpPrst.vue'
import StuExpView from '../views/StuExpView.vue'
import TchExpEdit from '../views/TchExpEdit.vue'
import TchExpView from '../views/TchExpView.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/stuExpPrst/:expId/prst',
    name: 'StuExpPrst',
    component: StuExpPrst
  },
  {
    path: '/stuExpView',
    name: 'StuExpPrst',
    component: StuExpView
  },
  {
    path: '/tchExpEdit/add',
    name: 'TchExpEdit',
    component: TchExpEdit
  },
  {
    path: '/tchExpEdit/:expId/edit',
    name: 'TchExpEdit',
    component: TchExpEdit
  },
  {
    path: '/tchExpView',
    name: 'TchExpView',
    component: TchExpView
  }
]

const router = new VueRouter({
  routes
})

export default router
