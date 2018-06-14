<template>
  <div class="table">
    <ObjTable/>
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
    }
  }
</script>
