<template>
  <div class="wrapper">
    <div class="actions">
      <vs-input vs-icon="search" vs-placeholder="Zoeken.." class="search" v-model="filterText" @keyup.native="filterTable" />
      <vs-button @click="addDialogVisible = true" vs-color="#fff" vs-type="border" vs-icon="add" class="addbtn"><span class="hide-sm">Object toevoegen</span></vs-button>
    </div>
    <el-table :data="tableData" ref="tableRef" row-class-name="styled-row" class="objtable" header-row-class-name="head" max-height="800">
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
        <div class="oh-tab">
          <label>Onderhoud nodig?
           <vs-switch vs-type="warning" vs-icon="build" v-model="editform.onderhoud"/></label><br>
            <label>Acties</label><br>
           <el-input type="textarea" :rows="2" placeholder="e.g. corrosie/graffiti" v-model="editform.acties">
           </el-input>
           <br>
          <label>Controleur</label><br>
          <el-select v-model="editform.controleur" placeholder="Controleur:">
    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" ></el-option>
    </el-select>
        </div>
      </vs-tab>
      <vs-tab vs-label="Locatie">
        <div class="loc-tab">
        <vs-input vs-label="Plaats" vs-placeholder="Plaats" v-model="editform.plaats"/>
        <vs-input vs-label="Straatnaam" vs-placeholder="Straatnaam" v-model="editform.straatnaam"/>
        <vs-input vs-label="Gemeente" vs-placeholder="Gemeente" v-model="editform.gemeente"/>
        <vs-input vs-label="Route" vs-placeholder="Route" v-model="editform.route"/>
        <vs-input vs-label="X-coördinaat" vs-placeholder="" vs-type="number" v-model="editform.xcord"/>
        <vs-input vs-label="Y-coördinaat" vs-placeholder="" vs-type="number" v-model="editform.ycord"/>
        </div>
      </vs-tab>
      <vs-tab vs-label="Inhoud">
        <div class="inh-tab">
           <vs-input vs-label="Locatienummer" vs-placeholder="e.g. NW01" v-model="editform.locatienummer"/>
           <label>Soort:</label>
           <div class="soort">
       <vs-radio v-model="editform.soort" vs-value="Bord">Bord</vs-radio>
       <vs-radio v-model="editform.soort" vs-value="Brug">Brug</vs-radio>
       <vs-radio v-model="editform.soort" vs-value="Stap">Stap</vs-radio>
       </div>
         <label>Grootte</label><br>
        <el-select v-model="editform.grootte" placeholder="Grootte:">
    <el-option v-for="item in grootte" :key="item.value" :label="item.label" :value="item.value" ></el-option>
    </el-select><br>
    <label>Biestekst</label><br>
           <el-input type="textarea" :rows="2" placeholder="Biestekst" v-model="editform.biestekst">
           </el-input>
        </div>
      </vs-tab>
    </vs-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <vs-button vs-color="primary" vs-icon="save" @click="updateForm(editform)">Wijzigingen opslaan</vs-button> 
        </div>
    </el-dialog>


   <el-dialog title="Object toevoegen" :visible.sync="addDialogVisible" custom-class="popup">
      <el-form :model="addform">
         <vs-tabs>
      <vs-tab vs-label="Locatie">
        <div class="loc-tab">
        <vs-input vs-label="Plaats" vs-placeholder="Plaats" v-model="addform.plaats"/>
        <vs-input vs-label="Straatnaam" vs-placeholder="Straatnaam" v-model="addform.straatnaam"/>
        <vs-input vs-label="Gemeente" vs-placeholder="Gemeente" v-model="addform.gemeente"/>
        <vs-input vs-label="Route" vs-placeholder="Route" v-model="addform.route"/>
        <vs-input vs-label="X-coördinaat"  v-model="addform.xcord"/>
        <vs-input vs-label="Y-coördinaat" v-model="addform.ycord"/>
        </div>
      </vs-tab>
      <vs-tab vs-label="Inhoud">
        <div class="inh-tab">
           <vs-input vs-label="Locatienummer(verplicht)" vs-placeholder="e.g. NW01" v-model="addform.locatienummer"/>
           <label>Soort:</label>
           <div class="soort">
       <vs-radio v-model="addform.soort" vs-value="Bord">Bord</vs-radio>
       <vs-radio v-model="addform.soort" vs-value="Brug">Brug</vs-radio>
       <vs-radio v-model="addform.soort" vs-value="Stap">Stap</vs-radio>
       </div>
         <label>Grootte</label><br>
        <el-select v-model="addform.grootte" placeholder="Grootte:">
    <el-option v-for="item in grootte" :key="item.value" :label="item.label" :value="item.value" ></el-option>
    </el-select><br>
    <label>Biestekst</label><br>
           <el-input type="textarea" :rows="2" placeholder="Biestekst" v-model="addform.biestekst">
           </el-input>
        </div>
      </vs-tab>
         <vs-tab vs-label="Onderhoud">
        <div class="oh-tab">
          <label>Onderhoud nodig?
           <vs-switch vs-type="warning" vs-icon="build" v-model="addform.onderhoud" /></label><br>
            <label>Acties</label><br>
           <el-input type="textarea" :rows="2" placeholder="e.g. corrosie/graffiti" v-model="addform.acties">
           </el-input>
           <br>
          <label>Controleur</label><br>
          <el-select v-model="addform.controleur" placeholder="Controleur:">
    <el-option v-for="item in options" :key="item.value" :label="item.label" :value="item.value" ></el-option>
    </el-select>
        </div>
      </vs-tab>
    </vs-tabs>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <vs-button vs-color="primary" vs-icon="save" @click="newObjForm(addform)">Object opslaan</vs-button> 
        </div>
    </el-dialog>

    
<el-dialog title="Object verwijderen" :visible.sync="deleteDialogVisible" custom-class="popup del">
  <p>Wilt u het object met locatienummer {{deleteobj.locatienummer}} echt verwijderen?</p>
  <span slot="footer" class="dialog-footer">
    <vs-button vs-color="primary" vs-type="border" @click="deleteDialogVisible = false">Annuleren</vs-button>
    <vs-button vs-color="danger" vs-icon="delete_forever" @click="delObj(deleteobj)">Verwijderen</vs-button>
  </span>
</el-dialog>
</div>
</template>
<style lang="scss">
  @import "../../node_modules/sass-mq/_mq.scss";
  .wrapper {
    padding: 40px;
    .actions {
      display: flex;
      align-items: stretch;
      margin-bottom: 40px;
      @include mq($until: tablet) {
        margin-bottom: 10px;
      }
      .search {
        flex: 1 0 0;
        margin: 0 20px 0 0 !important;
        font-size: 16px;
        @include mq($until: tablet) {
          margin: 0 10px 0 0 !important;
        }
        input {
          border: none !important;
          height: 44px;
          font-size: 16px;
        }
        .iconx {
          border: none !important;
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
            color: #409eff;
          }
          .hide-sm {
            @include mq($until: tablet) {
              display: none;
            }
          }
        }
      }
    }
    .el-table td,
    .el-table th.is-leaf {
      border-bottom: none !important;
    }
    .objtable {
      background-color: transparent !important;
      width: 100% !important;
      @include mq($until: tablet) {
        max-height: 85vh !important;
      }
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
                box-shadow: 0 9px 28px -9px #eb5757;
                span.text {
                  color: #eb5757;
                  opacity: 1;
                }
                &:first-child {
                  box-shadow: 0 9px 28px -9px #409eff;
                  span.text {
                    opacity: 1;
                    color: #409eff;
                  }
                }
              }
              span.text {
                color: #eb5757;
                @include mq($until: tablet) {
                  padding: 0 !important;
                }
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
              @include mq($until: tablet) {
                margin: 0 3px 0 0!important;
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
      max-width: 40%;
      border-radius: 5px;
      text-align: left;
      @include mq($until: tablet) {
        width: 95%;
        max-width: 98%;
      }
      .el-dialog__title {
        font-size: 27px;
        font-weight: 300;
      }
      .el-dialog__body {
        .ul-tabs {
          li {
            list-style: none;
          }
        }
        .con-tab {
          label {
            font-size: 14px;
          }
          input {
            font-size: 14px;
          }
          .oh-tab {
            .el-textarea {
              max-width: 350px;
              margin-bottom: 20px;
              .el-textarea__inner {
                padding: 5px 5px;
                font-family: inherit;
              }
              &:focus {
                border-color: #1f30ff;
              }
            }
            label {
              justify-content: left;
            }
          }
          .loc-tab {
            .con-input {
              max-width: 350px;
              margin: 30px 0px;
              label {
                padding-left: 0px;
              }
              &:nth-child(5) {
                margin-bottom: 0px;
              }
            }
          }
          .inh-tab {
            .con-input {
              max-width: 350px;
              margin: 20px 0px;
              label {
                padding-left: 0px;
              }
            }
            .soort {
              display: flex;
              justify-content: flex-start;
              margin-bottom: 20px;
              .con-vs-radio {
                padding: 3px 18px 0 0;
                justify-content: flex-start;
              }
            }
            .el-select {
              margin-bottom: 20px;
            }
            .el-textarea {
              max-width: 350px;
              .el-textarea__inner {
                padding: 5px 5px;
                font-family: inherit;
              }
            }
          }
        }
      }
      .dialog-footer {
        .vs-btn {
          font-family: inherit;
          .text {
            font-size: 14px;
          }
        }
      }
    }
    .el-dialog.popup.del {
      width: 30%;
      text-align: center;
      padding-top: 10px;
      @include mq($until: tablet) {
        width: 95%;
        max-width: 98%;
      }
      p {
        font-size: 18px;
      }
      .el-dialog__footer {
        text-align: center;
        padding: 0px 0 20px 0;
        .vs-btn.border {
          background-color: transparent !important;
          border: 1px solid #d8d8d8;
          min-width: 121px;
          transform: translateY(-18%);
          margin-right: 10px;
        }
        .vs-btn.filled {
          margin-left: 10px;
          &:hover {
            box-shadow: 0 9px 28px -9px #ff4757;
          }
        }
      }
    }
  }
</style>
<script>
  export default {
    name: "ObjTable",
    data() {
      return {
        apiUrl: "http://localhost:8090/api/objecten/",
        active: false,
        actives: 2,
        reducex: false,
        tableData: [],
        addDialogVisible: false,
        editDialogVisible: false,
        deleteDialogVisible: false,
        addform: {
          onderhoud: false
        },
        editform: {},
        deleteobj: {},
        filterText: null,
        options: [{
            value: "John Doe",
            label: "John Doe"
          },
          {
            value: "Jan van Eerd",
            label: "Jan van Eerd"
          },
          {
            value: "Piet Post",
            label: "Piet Post"
          }
        ],
        grootte: [{
            value: "Klein",
            label: "Klein"
          },
          {
            value: "Groot",
            label: "Groot"
          }
        ],
        value: ""
      };
    },
    mounted() {
      this.getObjecten();
    },
    methods: {
      isAuthenticated() {
        return localStorage.getItem("auth") !== null;
      },
      cellValueRenderer(row, column, cellValue) {
        return cellValue ? "Ja" : "Nee";
      },
      dateFormatter(row, column) {
        let data = this.$moment(row.updatedAt, this.$moment.ISO_8601);
        return data.format("DD-MM-YYYY, h:mm");
      },
      getObjecten: function() {
        this.$axios
          .get(this.apiUrl, {})
          .then(response => {
            this.tableData = response.data;
            console.log(response.data);
          })
          .catch(e => {
            this.errors.push(e);
          });
      },
      newObjForm: function(addObjForm) {
        let objId = addObjForm.id;
        let locatienummer = addObjForm.locatienummer;
        let soort = addObjForm.soort;
        let grootte = addObjForm.grootte;
        let biestekst = addObjForm.biestekst;
        let plaats = addObjForm.plaats;
        let gemeente = addObjForm.gemeente;
        let straatnaam = addObjForm.straatnaam;
        let route = addObjForm.route;
        let xcord = addObjForm.xcord;
        let ycord = addObjForm.ycord;
        let controleur = addObjForm.controleur;
        let acties = addObjForm.acties;
        let onderhoud = addObjForm.onderhoud;
        this.$axios
          .post(this.apiUrl, {
            locatienummer: locatienummer,
            soort: soort,
            grootte: grootte,
            biestekst: biestekst,
            plaats: plaats,
            gemeente: gemeente,
            straatnaam: straatnaam,
            route: route,
            xcord: xcord,
            ycord: ycord,
            controleur: controleur,
            onderhoud: onderhoud,
            acties: acties
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        this.$vs.notify({
          text: "Object opgeslagen",
          color: "success",
          icon: "done"
        });
        setTimeout(window.location.reload.bind(window.location), 400);
      },
      editObject: function(index, rows) {
        this.editDialogVisible = true;
        const objId = rows[index].id;
        const idurl = this.apiUrl + objId;
        this.$axios
          .get(idurl)
          .then(response => {
            this.editform = response.data;
          })
          .catch(function(response) {
            console.log(response);
          });
      },
      updateForm: function(editObjForm) {
        let objId = editObjForm.id;
        let locatienummer = editObjForm.locatienummer;
        let soort = editObjForm.soort;
        let grootte = editObjForm.grootte;
        let biestekst = editObjForm.biestekst;
        let plaats = editObjForm.plaats;
        let gemeente = editObjForm.gemeente;
        let straatnaam = editObjForm.straatnaam;
        let route = editObjForm.route;
        let xcord = editObjForm.xcord;
        let ycord = editObjForm.ycord;
        let controleur = editObjForm.controleur;
        let acties = editObjForm.acties;
        let onderhoud = editObjForm.onderhoud;
        this.$axios
          .put(this.apiUrl + objId, {
            locatienummer: locatienummer,
            soort: soort,
            grootte: grootte,
            biestekst: biestekst,
            plaats: plaats,
            gemeente: gemeente,
            straatnaam: straatnaam,
            route: route,
            xcord: xcord,
            ycord: ycord,
            controleur: controleur,
            onderhoud: onderhoud,
            acties: acties
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        this.$vs.notify({
          text: "Object opgeslagen",
          color: "success",
          icon: "done"
        });
        setTimeout(window.location.reload.bind(window.location), 400);
      },
      getDelObject: function(index, rows) {
        this.deleteDialogVisible = true;
        const objId = rows[index].id;
        const idurl = this.apiUrl + objId;
        this.$axios
          .get(idurl)
          .then(response => {
            this.deleteobj = response.data;
          })
          .catch(function(response) {
            console.log(response);
          });
      },
      delObj: function(deleteObj) {
        let objId = deleteObj.id;
        this.$axios
          .delete(this.apiUrl + objId, {
            objId: objId
          })
          .then(response => {})
          .catch(function(error) {
            console.log(error);
          });
        this.$vs.notify({
          text: "Object verwijderd",
          color: "danger",
          icon: "done"
        });
        setTimeout(window.location.reload.bind(window.location), 400);
      },
      filterTable() {
        const rows = this.$refs.tableRef.$refs.bodyWrapper.getElementsByClassName(
          "el-table__row"
        );
        for (let row of rows) {
          let cells = row.getElementsByTagName("td");
          for (let cell of cells) {
            let innerText = cell.innerText.toLowerCase();
            let filterText = this.filterText.toLowerCase();
            if (innerText.indexOf(filterText) > -1) {
              row.style.display = "";
              break;
            } else {
              row.style.display = "none";
            }
          }
        }
      }
    }
  };
</script>
