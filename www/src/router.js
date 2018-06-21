import Vue from 'vue'
import Router from 'vue-router'
import Table from './views/Table.vue'
import Map from './views/Map.vue'
import Login from './views/Login.vue'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'table',
      component: Table
    },
    {
      path: '/map',
      name: 'map',
      component: Map
    },
    {
      path: '/login',
      name: 'login',
      component: Login
    },
  ]
})