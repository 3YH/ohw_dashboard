import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui';
import Vuesax from 'vuesax'
import axios from 'axios'
import lang from 'element-ui/lib/locale/lang/nl'
import locale from 'element-ui/lib/locale'
import moment from 'moment'
import * as VueGoogleMaps from 'vue2-google-maps';
import GmapCluster from 'vue2-google-maps/dist/components/cluster'
   
//Styles
import 'element-ui/lib/theme-chalk/index.css';
import 'vuesax/dist/vuesax.css' //vuesax styles
 

Vue.use(VueGoogleMaps, {
  load: {
      key: 'AIzaSyBW9wbX31PbIhBciON03InLQmXVt33KFI0',
  }
});
Vue.component('GmapCluster', GmapCluster)
locale.use(lang)
Vue.use(Vuesax)
Vue.use(ElementUI);


Object.defineProperty(Vue.prototype, '$moment', { value: moment });
Object.defineProperty(Vue.prototype, '$axios', { value: axios });
Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
