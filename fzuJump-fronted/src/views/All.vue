<template>
    <div id="All">
    	<el-container>
			<el-aside width="auto">
				<div line-height="20px" style="margin-top:20px;margin-right: 10px;">
					<i class="el-icon-s-management" style="font-size: 25px; color: white; margin-left: 15px;"></i>
				    <span style=" font-size: 20px;" class="title"><strong>{{isCollapse?'':'FzuJump后台'}}</strong></span>
				</div>
							
				<el-menu class="el-menu-vertical-demo" background-color="#545c64"  text-color="#fff" @open="handleOpen" @close="handleClose" 
				active-text-color="#ffd04b" default-active="1-4-1" :collapse="isCollapse" router >
				<!-- :default-active="this.$route.path" -->
				<el-menu-item-group v-for="(v,i) in data1" :key="i">
					<el-menu-item  router="true"  v-for="(children,n) in v.children"  :index="children.path" :key="n" >
						<i :class="children.icon" ></i>							
						<span style="margin-left:25px">{{children.name}}</span>
					</el-menu-item>
				</el-menu-item-group>
			</el-menu>
			</el-aside>
			<el-container>
				<el-header height="55px">
					<div class="l-content">
						<el-button @click="handleMenu" plain icon="el-icon-menu" size="mini"></el-button>
						<!-- <span style="color:#fff">首页</span> -->
						
					</div>
					

					<div class="r-content">
						<div class="demo-basic--circle" style=" height: 60px; float: right;">
    								<el-avatar :size="50" :src="circleUrl" style="vertical-align: middle;margin-right: 0.625rem;">
    								</el-avatar>
    								<el-dropdown>
    									<el-button type="primary" round>
    										{{userName}}<i class="el-icon-arrow-down el-icon--right"></i>
    									</el-button>
    									<el-dropdown-menu slot="dropdown">
    										<el-dropdown-item @click.native="shutDown">注销</el-dropdown-item>
    									</el-dropdown-menu>
    								</el-dropdown>
    								
    					</div>
					</div>
			</el-header>
			<el-main>
				<router-view></router-view>
			</el-main>
			</el-container>
			
			
    		
    	</el-container>
    </div>
</template>

<script>
    import defaultHeadPicture from "../assets/img/PI@8NE30H5Q(GAGMKB)XY@C.jpg"
	import http from '../util/http.js'
    export default {
        name: "All",
		data() {
			return {
				circleUrl: defaultHeadPicture,
				data1:[],
				userName:"",
				isCollapse:false
			}
		},
		mounted(){
			console.log('111',this.$router.options.routes)
			this.data1=this.$router.options.routes
		},
		methods:{
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			handleMenu(){
				this.isCollapse=!this.isCollapse
			},
			shutDown(){
				http({
					method:'get',
					url:'/toLoginOut'
				}).then((res)=>{
					console.log('side',res)
					if(res.data.code === 200){
						console.log(res)
						this.$router.push("/")
						window.localStorage.removeItem("token")
					}else{
						alert("失败")
					}
				}).catch(()=>{
					console.log("注销失败")
				})
			}
		},
		created() {
			this.userName = localStorage.getItem("userName")
			console.log(this.userName)
		}
    }
</script>

<style scoped>

.el-menu-vertical-demo:not(.el-menu--collapse) {
    /* width: 180px; */
    min-height: 400px;
  }
  header
{
    display: flex;
    height: 100%;
    justify-content: space-between;
    align-items:center;
}
    .name{
		display: flex;
	}
	
     .title{
		font-family:Georgia, 'Times New Roman', Times, serif;
		margin-left: 12px;
		color:white
	}
    .el-header,
	.el-footer {
		background-color: #4e4c4c;
		color: #333;
		text-align: center;
		line-height: 60px;
	}

	.el-aside {
		height: 792px;
		background-color: #545c64;
		color: #333;
		text-align: center;
		/* line-height: 200px; */
		overflow: hidden;
	}

	

	body>.el-container {
		margin-bottom: 40px;
	}

	.el-container:nth-child(4) .el-aside {
		line-height: 320px;
	}
</style>