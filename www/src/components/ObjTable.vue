<template>
  <el-table :data="tableData" row-class-name="styled-row" stripe style="width: 70%; margin:20px;">
    <el-table-column label="#" type="index" width="40px">
    </el-table-column>
    <el-table-column label="Locatie" type="expand" width="180px">
      <template slot-scope="props">
                            <p>Plaats: {{ props.row.plaats }}</p>
                            <p>Gemeente: {{ props.row.gemeente }}</p>
                            <p>Straatnaam: {{ props.row.straatnaam }}</p>
                            <p>X-Coördinaat: {{ props.row.xcord }}</p>
                             <p>Y-Coördinaat: {{ props.row.ycord }}</p>
</template>
      </el-table-column>
      <el-table-column prop="locatienummer" label="Nummer" aling="left">
      </el-table-column>
      <el-table-column prop="soort" label="Soort">
      </el-table-column>
      <el-table-column prop="grootte" label="Grootte">
      </el-table-column>
      <el-table-column prop="biestekst" label="Biestekst">
      </el-table-column>
      <el-table-column prop="route" label="Route">
      </el-table-column>
      <el-table-column prop="onderhoud" label="Onderhoud?" :filters="[{ text: 'Ja', value: 'Ja' }, { text: 'Nee', value: 'Nee' }]"
      :filter-method="filterTag">
      </el-table-column>
      <el-table-column prop="controleur" label="Controleur">
      </el-table-column>
      <el-table-column prop="acties" label="Acties">
      </el-table-column>
      <el-table-column fixed="right" label="Operations">
<template slot-scope="scope">
  <el-button @click="handleClick" type="text" size="small">
    Detail</el-button>
  <el-button type="text" size="small">Edit</el-button>
</template>
    </el-table-column>
  </el-table>
</template>
<style lang="scss">
.el-table td, .el-table th.is-leaf{
  border-bottom:none!important;
}

  .el-table__body {
    border-spacing: 0 1rem;
    border-collapse: separate;
    .styled-row {
      border:0px solid #fff;
      td:first-child {
        border:0px;
        border-top-left-radius: 5px;
        border-bottom-left-radius: 5px;
      }
      td:last-child {
        border:0px;
        border-top-right-radius: 5px;
        border-bottom-right-radius: 5px;
      }
      .cell {
        text-align: left;
      }
    }
  }
</style>
<script>
  // @ is an alias to /src
  export default {
    name: 'ObjTable',
    data() {
      return {
        active: false,
        actives: 2,
        reducex: false,
        tableData: [],
        apiUrl: 'http://localhost:8090/api/objecten/',
      }
    },
    mounted() {
      this.getObjecten()
    },
    methods: {
      handleClick() {
        console.log('click');
      },
      filterTag(value, row) {
        return row.tag === value;
      },
      filterHandler(value, row, column) {
        const property = column['property'];
        return row[property] === value;
      },
      getObjecten: function() {
        this.$axios.get(this.apiUrl, {}).then((response) => {
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
