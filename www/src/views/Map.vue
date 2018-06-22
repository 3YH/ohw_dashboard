<template>
  <div id="map">
      <Map v-if="auth"></Map>
  </div>
</template>
<script>
  import Map from '@/components/Map.vue'
  export default {
    components: {
      Map
    },
    computed: {
      auth : function () {
         return localStorage.getItem("auth") !== null
      }
    }
  }
</script>