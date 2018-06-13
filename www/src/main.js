import Vue from 'vue'
import App from './App.vue'
import router from './router'
import {Button, Table, TableColumn} from 'element-ui';
import Vuesax from 'vuesax'
import axios from 'axios'

//Styles
import 'element-ui/lib/theme-chalk/index.css';
import 'vuesax/dist/vuesax.css' //vuesax styles

Vue.use(Vuesax)
Vue.component(Button.name, Button);
Vue.component(Table.name, Table);
Vue.component(TableColumn.name, TableColumn);

Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
