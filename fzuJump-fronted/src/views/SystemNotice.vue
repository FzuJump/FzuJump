<template>

	<div>
		<el-card class="box-card">
		<el-tabs v-model="activeName" type="card">
			<el-tab-pane label="日志信息" name="first">
				<el-table :data="myData1" style="width: 100%" :header-cell-style="{'text-align':'center'}">
					
					<el-table-column prop="id" label="日志编号" width="150px" align="center">
					</el-table-column>
					<el-table-column prop="message" label="日志消息"  width="700px" align = "center">
					</el-table-column>
					<el-table-column prop="time" label="发布时间" align="center">
					</el-table-column>
					<el-table-column prop="goodsDelete" label="删除日志" width="80" align = "center">
						<template slot-scope="scope">
							<el-button type="danger" icon="el-icon-delete" circle @click="deleteOneLog(scope.row)" ></el-button>
						</template>
					</el-table-column>
				</el-table>
				<div class="block">
					<span class="demonstration"></span>
					<el-pagination 
					:current-page="currentPage1" 
					:page-size="10"
					layout="total, prev, pager, next, jumper"
					:total="total1" 
					style="float: right;margin-top: 2%;">
					</el-pagination>
				</div>
			</el-tab-pane>

			<el-tab-pane label="添加日志" name="second" >
				<div id="add" style="vertical-align: middle">
					<span> 日志内容</span>
					<el-input v-model="form.message1" placeholder="请输入内容" style="width:80%;margin-left: 66px;"></el-input>
					<el-button type="primary" plain style="margin-left:130px;margin-top: 10px;" @click="add">添加日志
					</el-button>

				</div>
			</el-tab-pane>

		</el-tabs>
		</el-card>
	</div>

</template>
<script>
	import {
		systemNotice,
		delLog
	} from '../util/api.js'
	import http from '../util/http.js'
	
	export default {
		data() {
			return {
				total1:0,
				total2:0,
				myData1:[],
				activeName: 'first',
				currentPage1: 1,
				form: {
					message1:''
				},
				err:'',
			};
		},
		mounted() {
			var _this= this;
			http({
				method:'get',
				url:systemNotice.notify,
				params:{
					pageNo:1
				}
			}).then((res)=>{
				this.total1=res.data.total;
				this.myData1=res.data.list;
			}).catch(()=>{
				this.$messege({
					type:'error',
					messege:'服务器错误'
				})
			})
		}, 
		methods:{
			add() {
				http({
					method:"get",
					url:'/log/add',
					params:{
						message: this.form.message1,
					}	

				}).then((res)=>{
					if(res.data.code==200){
							this.total1=res.data.data.total;
							this.myData1=res.data.data.list;
							this.$message({
								type: 'success',
								message: '添加成功!'
							});
							this.cancelModify();
						}else{
							this.$message({
								type: 'error',
								message: res.data.msg
							});
						}
					}).catch(function() {
						this.$message({
							type: 'error',
							message: '服务器错误'
						});
				})
			},
			deleteOneLog(val) {
				// 删除的员工的id
				let delLogId = val.id;
				this.$confirm('此操作将永久删除该数据, 是否继续?', '提示', {
					confirmButtonText: '确定',
					cancelButtonText: '取消',
					type: 'warning'
				}).then(() => {
					console.log(delLogId);

					http({
						method: 'post',
						url: delLog,
						params: {
							userId: delLogId,
							pageNo: 1
						}
					}).then((res) => {
						console.log(res);
						if (res.data.code == 200) {
							 this.total1=res.data.data.total;
							 this.myData1=res.data.data.list;
							 this.$message({
								type: 'success',
								message: '删除成功!'
							});
						}else{
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

				})
			},
		}
	};
</script>

