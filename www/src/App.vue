<template>
  <div id="app">
    <el-container >
      <Sidebar :class="{ 'noauth': $route.path==='/login'}"></Sidebar>
      <el-main>
        <router-view/>
      </el-main>
    </el-container>
  </div>
</template>

<style lang="scss">
  @import '../node_modules/sass-mq/_mq.scss';
  body {
    font-family: 'Roboto', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    color: #2c3e50;
    background-color: #F5F7FA;
    .noauth{
      display:none;
    }
    .el-main {
          min-height: 100vh;
      padding: 40px;
      @include mq($until: tablet) {
        padding:10px;
      }
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
    },
    computed: {
      auth : function () {
         return localStorage.getItem("auth") !== null
      }
    }
  }
</script>