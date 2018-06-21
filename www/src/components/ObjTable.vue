<template>
  <div class="wrapper">
    <div class="actions">
      <vs-input vs-icon="search" vs-placeholder="Zoeken.." class="search" v-model="filterText" @keyup.native="filterTable" />
      <vs-button @click="addDialogVisible = true" vs-color="#fff" vs-type="border" vs-icon="add" class="addbtn">Object toevoegen</vs-button>
    </div>
    <el-table :data="tableData" ref="tableRef" row-class-name="styled-row" class="objtable" style="background-color:transparent; width:100%;" header-row-class-name="head" max-height="800">
      <el-table-column label="#" type="index" width="40px">
      </el-table-column>
      <el-table-column label="Inhoud" type="expand" width="180px">
        <template slot-scope="props">
                                   <p>Soort: {{ props.row.soort }}</p>
                                   <p>Grootte: {{ props.row.grootte }}</p>
                                   <p>Biestekst: {{ props.row.biestekst }}</p>
                                   <p>Coördinaten:</p>
                                   <p>X-coördinaat: {{ props.row.xcord }}</p>
                                   <p>Y-coördinaat: {{ props.row.ycord }}</p>
</template>
      </el-table-column>
      <el-table-column sortable prop="locatienummer" label="Nummer" aling="left">
      </el-table-column>
      <el-table-column sortable prop="plaats" label="Plaats">
      </el-table-column>
       <el-table-column sortable prop="straatnaam" label="Straatnaam">
      </el-table-column>
      <el-table-column sortable prop="gemeente" label="Gemeente">
      </el-table-column>
      <el-table-column sortable prop="route" label="Route">
      </el-table-column>
      <el-table-column sortable :formatter="cellValueRenderer" prop="onderhoud" label="Onderhoud?">
      </el-table-column>
      <el-table-column sortable prop="acties" label="Acties">
      </el-table-column>
      <el-table-column sortable prop="controleur" label="Controleur">
      </el-table-column>
      <el-table-column sortable prop="updatedAt" label="Laatste wijziging" :formatter="dateFormatter">
      </el-table-column>
      <el-table-column label="Wijzigingen">
<template scope="scope">
  <vs-button @click="editObject(scope.$index, tableData)" vs-icon="edit">
  </vs-button>
  <vs-button @click="getDelObject(scope.$index, tableData)" vs-icon="delete_forever"></vs-button>
</template>
    </el-table-column>
  </el-table>
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
    <el-button @click="deleteDialogVisible = false">Cancel</el-button>
    <el-button type="primary" @click="delObj(deleteobj)">Confirm</el-button>
  </span>
</el-dialog>
</div>
</template>
<style lang="scss">
  @import '../../node_modules/sass-mq/_mq.scss';
  .actions {
    display: flex;
    align-items: stretch;
    margin-bottom: 40px;
    .search {
      flex: 1 0 0;
      margin: 0 20px 0 0!important;
      font-size: 16px;
      input {
        border: none!important;
        height: 44px;
        font-size: 16px;
      }
      .iconx {
        border: none!important;
        i {
          font-size: 20px;
          padding-left: 10px;
        }
      }
      .placeholder {
        padding-bottom: 2px;
        font-size: 16px;
        padding-left: 8px;
      }
    }
    .addbtn {
      right: 0;
      font-size: 16px;
      span.text {
        color: #858585;
        .icon-btn {
          opacity: 1;
          color: #409EFF;
        }
      }
    }
  }
  .el-table td,
  .el-table th.is-leaf {
    border-bottom: none!important;
  }
  .objtable {
    .head {
      background-color: transparent;
      th {
        background-color: transparent;
        padding: 0;
        margin: 0;
      }
    }
    .el-table__expanded-cell {
      background-color: transparent;
    }
    .el-table__body {
      border-spacing: 0 1rem;
      border-collapse: separate;
      .styled-row {
        border: 0px solid #fff;
        td {
          padding: 0px;
        }
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
              box-shadow: 0 9px 28px -9px #EB5757;
              span.text {
                color: #EB5757;
                opacity: 1;
              }
              &:first-child {
                box-shadow: 0 9px 28px -9px #409EFF;
                span.text {
                  opacity: 1;
                  color: #409EFF;
                }
              }
            }
            span.text {
              color: #EB5757;
            }
            &:first-child {
              margin-top: 11px;
              margin-right: 7px;
              margin-bottom: 11px;
              span.text {
                color: #000;
                opacity: 0.58;
              }
            }
          }
        }
        &:hover {
          td {
            background-color: #edf3fc;
          }
        }
      }
      .current-row {
        td {
          background-color: #d9e8ff;
        }
        &:hover {
          td {
            background-color: #d9e8ff;
          }
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
        filterText: null,
      }
    },
    mounted() {
      this.getObjecten()
    },
    methods: {
      isAuthenticated() {
        return localStorage.getItem("auth") !== null
      },
      cellValueRenderer(row, column, cellValue) {
        return cellValue ? 'Ja' : 'Nee';
      },
      dateFormatter(row, column) {
        let data = this.$moment(row.updatedAt, this.$moment.ISO_8601);
        return data.format('DD-MM-YYYY, h:mm')
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
        this.$vs.notify({
          text: 'Object opgeslagen',
          color: 'success',
          icon: 'done'
        })
        setTimeout(window.location.reload.bind(window.location), 400);
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
        let locatienummer = editObjForm.locatienummer;
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
        this.$vs.notify({
          text: 'Object opgeslagen',
          color: 'success',
          icon: 'done'
        })
        setTimeout(window.location.reload.bind(window.location), 400);
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
        this.$vs.notify({
          text: 'Object verwijderd',
          color: 'danger',
          icon: 'done'
        })
        setTimeout(window.location.reload.bind(window.location), 400);
      },
      filterTable() {
        const rows = this.$refs.tableRef.$refs.bodyWrapper.getElementsByClassName(
          'el-table__row'
        )
        for (let row of rows) {
          let cells = row.getElementsByTagName('td')
          for (let cell of cells) {
            let innerText = cell.innerText.toLowerCase()
            let filterText = this.filterText.toLowerCase()
            if (innerText.indexOf(filterText) > -1) {
              row.style.display = ''
              break
            } else {
              row.style.display = 'none'
            }
          }
        }
      }
    }
  }
</script>
