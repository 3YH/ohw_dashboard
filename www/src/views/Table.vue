<template>
  <div class="table">
    <ObjTable v-if="auth"/>
  </div>
</template>

<script>
  // @ is an alias to /src
  import ObjTable from '@/components/ObjTable.vue'
  export default {
    name: 'home',
    components: {
      ObjTable,
    },
    data() {
      return {
        actives: 1,
      }
    },
     computed: {
      auth : function () {
         return localStorage.getItem("auth") !== null
      }
    }
  }
</script>
