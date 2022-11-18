
<template>
	<div>
		<el-card class="box-card">
		<el-tabs v-model="activeName" @tab-click="handleClick">
			<!--        第一个页面基础成绩信息-->
			<el-tab-pane label="用户成绩信息" name="first" >
				<!--            成绩table-->
				<el-table ref="multipleTable" 
				:data="tableData" 
				tooltip-effect="dark" 
				style="width: 100%" 
				@selection-change="handleSelectionChange">
					<el-table-column prop="id" label="用户id" >
					</el-table-column>
					<el-table-column prop="userName" label="用户名称" >
					</el-table-column>
					<el-table-column prop="rolename" label="角色" >
					</el-table-column>
					<el-table-column prop="jumpFrequency" label="跳跃高度" >
					</el-table-column>
					<el-table-column prop="itemNumber" label="拾取道具数">
					</el-table-column>


					<el-table-column prop="goodsEdit" label="操作" width="80">
						<template slot-scope="scope">
							<!-- dialogFormVisible = true -->
							<el-button type="primary" icon="el-icon-edit" circle @click="update_search(scope.row)"></el-button>
						</template>
						<!--                    <el-button type="text" @click="dialogFormVisible = true">打开嵌套表单的 Dialog</el-button>-->
					</el-table-column>

					<el-table-column prop="goodsDelete" label="删除" width="80">
						<template slot-scope="scope">
							<el-button type="danger" icon="el-icon-delete" circle @click="delete1(scope)"></el-button>
						</template>

					</el-table-column>
					<!--                分页-->
				</el-table>
				<div class="block">
					<el-pagination 
					@size-change="handleSizeChange" 
					@current-change="handleCurrentChange" 
					:current-page.sync="currentPage"
					:page-size="size" 
					layout="total, prev, pager, next" 
					:total="total"
					style="float: right;margin-top: 2%;">
					</el-pagination>
				</div>

				<el-form>
					<!--                编辑用户成绩按钮-->
					<el-dialog title="编辑用户成绩" :visible.sync="dialogFormVisible">
						<el-form :model="form">
							<el-form-item label="用户名称" :label-width="formLabelWidth" >
								<el-input v-model="form.userName" autocomplete="off" readonly></el-input>
							</el-form-item>
							<el-form-item label="跳跃高度" :label-width="formLabelWidth">
								<el-input v-model="form.jumpFrequency" autocomplete="off"></el-input>
							</el-form-item>
							<el-form-item label="拾取道具数" :label-width="formLabelWidth">
								<el-input v-model="form.itemNumber" autocomplete="off"></el-input>
							</el-form-item>
						</el-form>
						<div slot="footer" class="dialog-footer" >
							<!-- ialogFormVisible = false -->
							<el-button @click="clean()">取 消</el-button>
							<el-button type="primary" @click="update()">确 定</el-button>
						</div>
					</el-dialog>
				</el-form>
			</el-tab-pane>

			<!--        第二个用户成绩添加界面-->
			<el-tab-pane label="添加成绩信息" name="second">
				<div id="add" style="vertical-align: middle">
					<span> 用户昵称：</span>
					<el-input v-model="form.userName" placeholder="请输入用户账号" style="width:50%;margin-left: 66px;"></el-input>
					<br>	
					<span> 角色：  </span>
					<el-select v-model="types" placeholder="请选择" style="width:50%;margin-left: 92px;">
						<el-option v-for="item in lab" :key="item.key" :label="item.display_name" :value="item.key">
						</el-option>
					</el-select>
					<br>
					<span> 跳跃高度：</span>
					<el-input v-model="form.jumpFrequency" placeholder="请输入跳跃高度/米" style="width:50%;margin-left: 66px;"></el-input>
					<br>
					<span> 拾取道具数：</span>
					<el-input v-model="form.itemNumber" placeholder="请输入道具数" style="width:50%;margin-left: 50px;"></el-input>
					<br>

					<el-button type="primary" plain style="margin-left:260px;margin-top: 100px;" @click="add">添加用户成绩
					</el-button>

				</div>
			</el-tab-pane>

		</el-tabs>
		</el-card>
	</div>
</template>
<script>
	import http from "../util/http";
	import {
		initGF,
		GFadd
	} from "../util/api";


	export default {
		data() {
			return {
				activeName: 'first',
				types:'',
				index:'',
				lab:[
					{
						key:"组长",display_name:'组长'
					},
					{
						key:"全栈工程师",display_name:'全栈工程师'
					},
					{
						key:"后端工程师",display_name:'后端工程师'
					},
					{
						key:"前端工程师",display_name:'前端工程师'
					},
					{
						key:"测试人员",display_name:'测试人员'
					},
					{
						key:"摄影师",display_name:'摄影师'
					},
					{
						key:"用户",display_name:'用户'
					},
				],
				tableData: [
				],
				currentPage: 1,
				multipleSelection: [],
				dialogFormVisible: false,
				form: {
					userName:'',
					itemNumber:'',
					jumpFrequency:'',
					userRole:''
				},
				formLabelWidth: '120px',
				dialogVisible: false,
				total:0,
				size:1,
				value: '',
				visible: false,
			};
		},
		created() {
			http({
				method: 'post',
				url:'score',
				params: {
					pageNo: 1
				}
			}).then((res) => {
				this.total = res.data.data.total;
				this.size = res.data.data.list.length
				console.log(res);
				if (res.status === 200) {
					this.tableData = res.data.data.list;
				}
			}).catch(() => {
				alert("err")
			})
		},
		methods: {
			update(){
				this.dialogFormVisible=false,

				http({
					method:'get',
					url:'/score/update',
					params:{
						id: this.form.id,
						userName: this.form.userName,
						jumpFrequency: this.form.jumpFrequency,
						itemNumber: this.form.itemNumber
					}
				}).then((res)=>{
					this.form.userName=''
					this.form.itemNumber=''
					this.form.jumpFrequency=''
					this.types=''
					if(res.data.code==200){
						this.$message({
							type: 'success',
							message: '更新成功!'
						});
					}else{
						this.$message({
							type: 'error',
							message: res.data.msg
						});
						}
					http({
						method: 'post',
						url: initGF,
						params: {
							pageNo: 1
						}
					}).then((res) => {
						if (res.status === 200) {
							this.tableData = res.data.data.list;
						}
					}).catch(() => {
						alert("err")
					})
				}).catch(()=>{
					this.$message({
						type: 'info',
						message: '更新失败'
					});
				})
			},
			update_search(scope){
				this.dialogFormVisible = true 
				console.log(scope)
				this.form = JSON.parse(JSON.stringify(scope))
			},
			delete1(scope) {
				this.dialogVisible = false;
				http({
					method:"get",
					url:'/score/delete',
					params: {
						id:this.tableData[scope.$index].id
					}
				}).then((res)=>{
					this.tableData.splice(scope.$index-1,1)
					if(res.data.code==200){
							this.getDataByPage(this.currentPage);
							this.$message({
								type: 'success',
								message: '删除成功!'
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
					http({
						method: 'post',
						url: initGF,
						params: {
							pageNo: 1
						}
					}).then((res) => {
						if (res.data.code == 200) {
							this.tableData = res.data.data.list;
						}
					}).catch(() => {
						 alert("err")
					})
				}).catch(()=>{
				})
			},
			handleClick(tab, event) {
				console.log(tab, event);
			},
			toggleSelection(rows) {
				if (rows) {
					rows.forEach(row => {
						this.$refs.multipleTable.toggleRowSelection(row);
					});
				} else {
					this.$refs.multipleTable.clearSelection();
				}
			},
			handleSelectionChange(val) {
				this.multipleSelection = val;
			},
			handleSizeChange(val) {
				console.log(`每页 ${val} 条`);
			},
			handleCurrentChange(val) {
				console.log(val);
				http({
					method: 'post',
					url: '/score',
					params: {
						pageNo: val
					}
				}).then((res) => {
					if (res.status === 200) {
						this.tableData = res.data.data.list;
					}
				})
			},
			handleClose(done) {
				this.$confirm('确认关闭？')
					.then(_ => {
						done();
					})
					.catch(_ => {});
			},
			add() {
				http({
					method:"get",
					url:'/score/add',
					params:{
						userName:this.form.userName,
						jumpFrequency: this.form.jumpFrequency,
						itemNumber: this.form.itemNumber,
						rolename: this.types,
					}
				}).then((res)=>{
					console.log(res)
					if(res.data.code==200){
							this.$message({
								type: 'success',
								message: '添加成功!'
							});
							this.cancelModify();
						}
						else {
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
			clean(){
				this.dialogFormVisible = false
				this.form.userName=''
				this.form.jumpFrequency=''
				this.form.itemNumber=''
				this.types=''
			}
		}

	};
</script>
