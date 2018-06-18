<template>
  <div id="app">
    <!-- <div class="nav">
      <vs-sidebar vs-static :vs-reduce="reducex" :vs-reduce-expand="!reducex" style="width:400px">
        <router-link to="/">
          <vs-sidebar-item @click="actives=1" :vs-active="actives==1" vs-icon="question_answer">
            Home
          </vs-sidebar-item>
        </router-link>
        <router-link to="/about">
          <vs-sidebar-item @click="actives=2" :vs-active="actives==2" vs-icon="gavel">
            about
          </vs-sidebar-item>
        </router-link>
      </vs-sidebar>
    </div> -->
    <router-view/>

  </div>
</template>

<style lang="scss">
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    background-color: #F5F7FA;
  }
  #nav {
    padding: 30px;
    a {
      font-weight: bold;
      color: #2c3e50;
      &.router-link-exact-active {
        color: #42b983;
      }
    }
    
  }
  .con-darkx{
      display:none!important;
    }
</style>

<script>
  export default {
    components: {
    },
    data() {
      return {
        reducex:true,
        actives: 1,
      }
    }
  }
</script>