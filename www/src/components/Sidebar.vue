<template>
    <el-menu default-active="/" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose" :collapse="isCollapse" router>
        <el-menu-item index="/">
           <i class="material-icons">format_list_bulleted</i>
            <span slot="title">Tabel</span>
        </el-menu-item>
        <el-menu-item index="map">
           <i class="material-icons">map</i>
            <span slot="title">Kaart</span>
        </el-menu-item>
    </el-menu>
</template>

<style lang="scss">
        .el-menu {
        min-height: 100vh;
        min-width: 73.5px;
            li.el-menu-item{
                background: #fff;
                border-radius:12px;
                margin: 5px 7.5px;
                box-shadow:0 8px 30px 4px rgba(0,0,0,.08);
                .el-tooltip{
                    padding:0px!important;
                }
                &.is-active{
                    background: #409EFF;
                    color:#fff;
                }
            }
    }
        
</style>
<script>
    import ObjTable from '@/components/ObjTable.vue'
    export default {
        name: 'sidebar',
        data() {
            return {
                isCollapse: true
            }
        },
        methods: {
            handleOpen(key, keyPath) {
                console.log(key, keyPath);
            },
            handleClose(key, keyPath) {
                console.log(key, keyPath);
            }
        }
    }
</script>
