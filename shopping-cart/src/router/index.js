import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../Login.vue'
import Addproduct from "@/views/Addproduct";


Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  { path: '/loginwithtoken', name: 'loginwithtoken', component: Login },
  {
    path: '/store',
    name: 'About',
    component: () => import('../views/About.vue')
  },
  {     path: '/contact',
        name: 'Contact',
        component: () => import('../views/Contact.vue')
  },
  { path: '/partners', name: 'Partners', component: () => import('../Partners.vue')},
  { path: '/addproduct', name: 'Addproduct', component: Addproduct}
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router