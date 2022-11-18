<template>

    <div>
		<el-card class="box-card">
        <h1>总排行榜</h1>
        <div id="table">
            <el-table
                    :data="tableData"
                    style="width: 100%"
                    :header-cell-style="{'text-align':'center'}">
                    <el-table-column
                        label="排名"
                        type="index"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="用户id"
                        prop="id"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="用户昵称"
                        prop="userName"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="角色"
                        prop="rolename"
                        align="center">
                </el-table-column>
                <el-table-column
                        label="跳跃高度"
                        prop="jumpFrequency"
                        sortable
                        align="center">
                </el-table-column>
                <el-table-column
                        label="拾取道具数"
                        prop="itemNumber"
                        sortable
                        align="center">
                </el-table-column>
                <el-table-column>
                    <template slot="header" >
                        <div style="display:flex" id="block111">
                            <input type="表单类型" v-model="search" placeholder="请输入用户昵称"/>
                            <el-button icon="el-icon-search" circle @click="searchMes(search)"></el-button>
                        </div>
                        
                    </template>
                </el-table-column>
            </el-table>
        </div>
        <div id="block">
            <el-pagination
                    @current-change="handleCurrentChange"
                    :current-page="currentPage"
                    :page-size="10"
                    layout="total,  prev, pager, next, jumper"
                    :total="total">
            </el-pagination>
        </div>
		</el-card>
    </div>

</template>

<script>
    import {
        stockAll
    } from "../util/api.js";
    import http from '../util/http.js'

    export default {
        name: 'StockAll',
        data() {
            return {
                tableData: [],
                search:'',
                currentPage: 1,
                total: 0,
            }
        },
        methods: {
            handleCurrentChange(val) {
                http({
                    method:'post',
                    url:'/rank',
                    params:{
                        pageNo:val
                    }
                }).then((res)=>{
                    consolo.log(res)
                    if (res.data.code == 200){
                        this.tableData = res.data.data.list;
                        this.total = res.data.data.total;
                        console.log(this.total)
                    }else {
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					}).catch(() => {
						this.$message({
							type: 'error',
							message: '服务器错误'
						});
                })
            },
            searchMes(mes){
                http({
                    method:'get',
                    url:'/score/search',
                    params:{
                        userName:mes
                    }
                }).then((res)=>{
                    if(res.data.code==200){
                        console.log(res.data)
                        console.log(res.data.data)
                        this.tableData=res.data.data
                        this.total = 1;
                    }else {
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					}).catch(() => {
						this.$message({
							type: 'error',
							message: '服务器错误'
						});
                })
            }
        },
        mounted() {
            http({
                method: 'post',
                url: '/rank',
                params: {
                    pageNo: 1
                }
            }).then((res) => {
                if (res.data.code == 200){
                        this.tableData = res.data.data.list;
                        this.total = res.data.data.total;
                    }else {
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					}).catch(() => {
						this.$message({
							type: 'error',
							message: '服务器错误'
						});
            })
        }


    }
</script>

<style scoped>
    #table {
        margin-top: 2%;
        margin-left: 0%;
    }
    #block {
        float: right;
        margin-top: 2%;
    }
</style>
