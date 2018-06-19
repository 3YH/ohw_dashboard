<template>
  <div id="app">
    <el-container>
     
        <Sidebar></Sidebar>
   
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<style lang="scss">
  body {
    font-family: 'Roboto', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    background-color: #F5F7FA;
    .el-main{
      padding:40px;
    }
  }
</style>

<script>
  import Sidebar from '@/components/Sidebar.vue'
  export default {
    components: {
      Sidebar
    },
    data() {
      return {
        
      }
    }
  }
</script>