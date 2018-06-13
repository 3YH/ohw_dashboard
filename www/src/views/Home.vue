<template>
  <div class="home">
    <img src="../assets/logo.png">
    <HelloWorld msg="Welcome to Your Vue.js App"/>
    <vs-button vs-type="primary-filled">Primary</vs-button>
      <el-table
    :data="tableData"
    style="width: 100%">
    <el-table-column
      prop="id"
      label="id"
      width="150">
    </el-table-column>
    <el-table-column
      prop="locatienummer"
      label="Locatienummer"
      width="120">
    </el-table-column>
    <el-table-column
      prop="soort"
      label="Soort"
      width="120">
    </el-table-column>
    <el-table-column
      fixed="right"
      label="Operations"
      width="120">
      <template slot-scope="scope">
        <el-button @click="handleClick" type="text" size="small">Detail</el-button>
        <el-button type="text" size="small">Edit</el-button>
      </template>
    </el-table-column>
  </el-table>

  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'home',
  components: {
    HelloWorld
  },
  data() {
      return {
       tableData: [],
       apiUrl: 'http://localhost:8090/api/objecten/',
      }
  },
  mounted (){
              this.getObjecten()
              },
    methods: {
      handleClick() {
        console.log('click');
      },
      getObjecten: function () {
                this.$axios.get(this.apiUrl, {
                }).then((response) => {
                    this.tableData = response.data
                    console.log(response.data);
                })
                 .catch(e => {
                   this.errors.push(e)
                   })
            }
    }
}
</script>
