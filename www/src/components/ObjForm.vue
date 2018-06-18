<template>
        <el-form :model="form">
          <p>testst</p>
            <el-form-item label="item_id" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.id" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="username" :label-width="formLabelWidth">
                <el-input v-model="form.username" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="email" :label-width="formLabelWidth">
                <el-input :disabled="true" v-model="form.email" auto-complete="off"></el-input>
            </el-form-item>
        </el-form>
</template>
<style lang="scss">
</style>
<script>

  export default {
    name: 'ObjForm',
    data() {
      return {
                formLabelWidth: '120px',
            }
    },
    props: ['dialogVisible', 'form'],
    methods: {
            updateForm: function (formName) {
                this.$axios.put('http://127.0.0.1:8000/api/persons/detail/' + itemId, {
                    username: username,
                    phone: phone,
                    zone: zone
                })
                    .then(function (response) {
                        console.log(response);
                        this.form = response.data;

                    })
                    .catch(function (error) {
                        console.log(error);
                    });
                location.reload();
            }
        }
  }
</script>
