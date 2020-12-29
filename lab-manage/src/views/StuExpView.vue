<template>
    <div>
        <Header></Header>
        <el-container style="height: 600px; border: 1px solid #eee">
            <el-aside width="200px" style="background-color: rgb(238, 241, 246)">
                <el-menu>
                    <el-menu-item-group>
                        <el-menu-item index="1">实验</el-menu-item>
                        <el-menu-item index="2">机房</el-menu-item>
                    </el-menu-item-group>
                </el-menu>
            </el-aside>

            <el-container>
                <el-header style="text-align: right; font-size: 12px">
                    <span>王小虎</span>
                </el-header>

                <el-main>
                    <el-table :data="tableData">

                        <el-table-column type="index" :index="indexMethod" label="序号" min-width="60" align='left'>
                            <template slot-scope="scope">
                                <a @click="toDetail(scope.row.id)" style="cursor:pointer">{{scope.row.id}}</a>
                            </template>
                        </el-table-column>

                        <el-table-column prop="expName" label="实验名称" width="300">
                        </el-table-column>
                        <el-table-column prop="expSite" label="实验地点" width="300">
                        </el-table-column>
                        <el-table-column prop="doTime" label="实验时间" width="300">
                        </el-table-column>
                        <el-table-column prop="expTch" label="实验老师" width="135">
                        </el-table-column>
                        <el-table-column prop="expTch" label="是否完成" width="120">
                        </el-table-column>
                    </el-table>
                    <el-pagination
                            class="page-shift"
                            background
                            layout="prev, pager, next"
                            :total=total>
                    </el-pagination>
                </el-main>
            </el-container>
        </el-container>
    </div>
</template>

<script>
    import Header from "../components/Header";
    export default {
        name: "StuExpView",
        components: {Header},
        data() {
            return{
                tableData: [],
                currentPage: 1,
                total: 0,
                pageSize: 9,
            }
        },
        methods:{
            page(currentPage){
                const _this = this;
                _this.$axios.get("/experiments/?currentPage="+currentPage).then( res => {
                    const temp = [];
                    for (let i in res.data.data.records){
                        temp.push(res.data.data.records[i]);
                    }
                    _this.tableData = temp;
                    _this.currentPage = res.data.data.current;
                    _this.total = res.data.data.total;
                    _this.pageSize = res.data.data.size;
                })
            },
            indexMethod(index) {
                return index + 1;
            },
            toDetail(index){
                console.log(index);
            }
        },
        created(){
            this.page(1);
        }
    }
</script>

<style scoped>
    .el-header {
        background-color: #B3C0D1;
        color: #333;
        line-height: 60px;
    }

    .el-aside {
        color: #333;
    }

    .page-shift{
        margin: 10px auto;
    }
</style>