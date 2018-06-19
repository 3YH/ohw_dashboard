<template>
  <div class="table">
    <el-table :data="tableData" row-class-name="styled-row" class="objtable" style="background-color:transparent">
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
      <el-table-column :formatter="cellValueRenderer" prop="onderhoud" label="Onderhoud?" :filters="[{ text: 'Ja', value: 'Ja' }, { text: 'Nee', value: 'Nee' }]"
      :filter-method="filterTag">
      </el-table-column>
      <el-table-column prop="controleur" label="Controleur">
      </el-table-column>
      <el-table-column prop="acties" label="Acties">
      </el-table-column>
      <el-table-column fixed="right" label="Operations">
<template scope="scope">
  <vs-button @click="editObject(scope.$index, tableData)" vs-icon="edit"></vs-button>
  <vs-button @click="getDelObject(scope.$index, tableData)" vs-icon="delete"></vs-button>
  
</template>
    </el-table-column>
  </el-table>
  <vs-button @click="addDialogVisible = true" vs-color="primary" vs-type="filled">Primary</vs-button>
   <el-dialog title="Object wijzigen" :visible.sync="editDialogVisible" custom-class="popup">
      <el-form :model="editform">
         <vs-tabs>
      <vs-tab vs-label="Onderhoud">
        <div class="con-tab-ejemplo">
           <vs-input vs-label="Locatienummer" vs-placeholder="Placeholder" v-model="editform.locatienummer"/>
          <label>Onderhoud nodig?
           <vs-switch vs-type="warning" vs-icon="build" v-model="editform.onderhoud"/></label>
        </div>
      </vs-tab>
      <vs-tab vs-label="Locatie">
        <div class="con-tab-ejemplo">
        <vs-input vs-label="Label" vs-placeholder="Placeholder" v-model="editform.plaats"/>
        </div>
      </vs-tab>
      <vs-tab vs-label="Inhoud">
        <div class="con-tab-ejemplo">
        <vs-input vs-label="Label" vs-placeholder="Placeholder" v-model="editform.plaats"/>
        </div>
      </vs-tab>
    </vs-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" @click="editDialogVisible = false">Cancel</el-button>
            <el-button :plain="true" @click="updateForm(editform)">Save</el-button>
        </div>
    </el-dialog>
    <el-dialog
  title="Object toevoegen"
  :visible.sync="addDialogVisible" custom-class="popup">
  <el-form :model="addform">
         <vs-tabs>
      <vs-tab vs-label="Onderhoud">
        <div class="con-tab-ejemplo">
           <vs-input vs-label="Locatienummer" vs-placeholder="Placeholder" v-model="addform.locatienummer"/>
        </div>
      </vs-tab>
      <vs-tab vs-label="Locatie">
        <div class="con-tab-ejemplo">
        <vs-input vs-label="Label" vs-placeholder="Placeholder" v-model="addform.plaats"/>
        </div>
      </vs-tab>
      <vs-tab vs-label="Inhoud">
        <div class="con-tab-ejemplo">
      
        </div>
      </vs-tab>
    </vs-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
            <el-button :plain="true" type="danger" @click="addDialogVisible = false">Cancel</el-button>
            <el-button :plain="true" @click="newObjForm(addform)">Save</el-button>
        </div>
</el-dialog>
<el-dialog
  title="Tips"
  :visible.sync="deleteDialogVisible"
  width="30%">
  <span>{{deleteobj.locatienummer}}</span>
  <span slot="footer" class="dialog-footer">
    <el-button @click="dialogVisible = false">Cancel</el-button>
    <el-button type="primary" @click="delObj(deleteobj)">Confirm</el-button>
  </span>
</el-dialog>


</div>
</template>
<style lang="scss">
  .el-table td,
  .el-table th.is-leaf {
    border-bottom: none!important;
  }
  .objtable {
    width: 75%!important;
    margin: 20px;
    .el-table__expanded-cell {
      background-color: transparent;
    }
    .el-table__body {
      border-spacing: 0 1rem;
      border-collapse: separate;
      .styled-row {
        border: 0px solid #fff;
        td:first-child {
          border: 0px;
          border-top-left-radius: 5px;
          border-bottom-left-radius: 5px;
        }
        td:last-child {
          border: 0px;
          border-top-right-radius: 5px;
          border-bottom-right-radius: 5px;
        }
        .cell {
          text-align: left;
          button.vs-btn.vs-button-primary-filled.filled.vs-button-icon {
            background-color: #fff;
            &:hover {
              box-shadow: none;
              span.text {
                color: #409EFF;
                opacity: 1;
              }
            }
            span.text {
              color: #000;
              opacity: 0.58;
            }
          }
        }
      }
      .hover-row {
        td {
          background-color: rgba(64, 160, 255, 0.212)!important;
        }
      }
    }
  }
  .el-dialog.popup {
    border-radius: 5px;
    text-align: left;
    .ul-tabs {
      li {
        list-style: none;
      }
    }
    .con-tab {
      .con-tab-ejemplo {
        label {
          justify-content: left;
        }
      }
    }
  }
</style>
<script>
  export default {
    name: 'ObjTable',
    data() {
      return {
        apiUrl: 'http://localhost:8090/api/objecten/',
        active: false,
        actives: 2,
        reducex: false,
        tableData: [],
        addDialogVisible: false,
        editDialogVisible: false,
        deleteDialogVisible: false,
        addform: {},
        editform: {},
        deleteobj: {},
      }
    },
    mounted() {
      this.getObjecten()
    },
    methods: {
      cellValueRenderer(row, column, cellValue) {
        return cellValue ? 'Ja' : 'Nee';
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
      },
      newObjForm: function(addObjForm) {
        let objId = addObjForm.id;
        let locatienummer = addObjForm.locatienummer;
        let plaats = addObjForm.plaats;
        let onderhoud = addObjForm.onderhoud;
        this.$axios.post('http://localhost:8090/api/objecten/', {
            locatienummer: locatienummer,
            plaats: plaats,
            onderhoud: onderhoud,
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        location.reload();
      },
      editObject: function(index, rows) {
        this.editDialogVisible = true;
        const objId = rows[index].id;
        const idurl = 'http://localhost:8090/api/objecten/' + objId;
        this.$axios.get(idurl).then((response) => {
          this.editform = response.data;
        }).catch(function(response) {
          console.log(response)
        });
      },
      updateForm: function(editObjForm) {
        let objId = editObjForm.id;
        let locatienummerr = editObjForm.locatienummer;
        let plaats = editObjForm.plaats;
        let onderhoud = editObjForm.onderhoud;
        this.$axios.put('http://localhost:8090/api/objecten/' + objId, {
            locatienummer: locatienummer,
            plaats: plaats,
            onderhoud: onderhoud,
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        location.reload();
      },
       getDelObject: function(index, rows) {
        this.deleteDialogVisible = true;
        const objId = rows[index].id;
        const idurl = 'http://localhost:8090/api/objecten/' + objId;
        this.$axios.get(idurl).then((response) => {
          this.deleteobj = response.data;
        }).catch(function(response) {
          console.log(response)
        });
      },
      delObj: function(deleteObj) {
        let objId = deleteObj.id;
        this.$axios.delete('http://localhost:8090/api/objecten/' + objId, {
            objId: objId,
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        location.reload();
      }
    }
  }
</script>
