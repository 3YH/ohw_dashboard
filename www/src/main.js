import Vue from 'vue'
import App from './App.vue'
import router from './router'
import {Scrollbar, Button, Table, TableColumn} from 'element-ui'
import Vuesax from 'vuesax'
import axios from 'axios'
import lang from 'element-ui/lib/locale/lang/nl'
import locale from 'element-ui/lib/locale'

//Styles
import 'element-ui/lib/theme-chalk/index.css';
import 'vuesax/dist/vuesax.css' //vuesax styles

locale.use(lang)
Vue.use(Vuesax)
Vue.component(Scrollbar.name, Scrollbar);
Vue.component(Button.name, Button);
Vue.component(Table.name, Table);
Vue.component(TableColumn.name, TableColumn);

Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
