(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-52068312"],{8705:function(e,t,a){},9870:function(e,t,a){"use strict";a.d(t,"f",(function(){return r})),a.d(t,"a",(function(){return n})),a.d(t,"j",(function(){return o})),a.d(t,"k",(function(){return l})),a.d(t,"g",(function(){return s})),a.d(t,"d",(function(){return c})),a.d(t,"b",(function(){return u})),a.d(t,"h",(function(){return d})),a.d(t,"c",(function(){return i})),a.d(t,"e",(function(){return p})),a.d(t,"i",(function(){return m}));var r="/user/show",n="/user/add",o="/user/search",l={notify:"/log"},s="/user/update",c="/email",u="/log/delete",d="/user/beachDelete",i="/user/delete",p="/score",m={showRole:"/role/showRole",addRole:"/role/add",showAllRoot:"/permission/show",roleUpdate:"/role/update",deleteOneRole:"/role/delete"}},b033:function(e,t,a){"use strict";var r=a("8705"),n=a.n(r);n.a},f78e:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",[a("el-card",{staticClass:"box-card"},[a("h1",[e._v("总排行榜")]),a("div",{attrs:{id:"table"}},[a("el-table",{staticStyle:{width:"100%"},attrs:{data:e.tableData,"header-cell-style":{"text-align":"center"}}},[a("el-table-column",{attrs:{label:"排名",type:"index",align:"center"}}),a("el-table-column",{attrs:{label:"用户id",prop:"id",align:"center"}}),a("el-table-column",{attrs:{label:"用户昵称",prop:"userName",align:"center"}}),a("el-table-column",{attrs:{label:"角色",prop:"rolename",align:"center"}}),a("el-table-column",{attrs:{label:"跳跃高度",prop:"jumpFrequency",sortable:"",align:"center"}}),a("el-table-column",{attrs:{label:"拾取道具数",prop:"itemNumber",sortable:"",align:"center"}}),a("el-table-column",[a("template",{slot:"header"},[a("div",{staticStyle:{display:"flex"},attrs:{id:"block111"}},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.search,expression:"search"}],attrs:{type:"表单类型",placeholder:"请输入用户昵称"},domProps:{value:e.search},on:{input:function(t){t.target.composing||(e.search=t.target.value)}}}),a("el-button",{attrs:{icon:"el-icon-search",circle:""},on:{click:function(t){return e.searchMes(e.search)}}})],1)])],2)],1)],1),a("div",{attrs:{id:"block"}},[a("el-pagination",{attrs:{"current-page":e.currentPage,"page-size":10,layout:"total,  prev, pager, next, jumper",total:e.total},on:{"current-change":e.handleCurrentChange}})],1)])],1)},n=[],o=(a("9870"),a("adb5")),l={name:"StockAll",data:function(){return{tableData:[],search:"",currentPage:1,total:0}},methods:{handleCurrentChange:function(e){var t=this;Object(o["a"])({method:"post",url:"/rank",params:{pageNo:e}}).then((function(e){consolo.log(e),200==e.data.code?(t.tableData=e.data.data.list,t.total=e.data.data.total,console.log(t.total)):t.$message({type:"error",message:e.data.msg})})).catch((function(){t.$message({type:"error",message:"服务器错误"})}))},searchMes:function(e){var t=this;Object(o["a"])({method:"get",url:"/score/search",params:{userName:e}}).then((function(e){200==e.data.code?(console.log(e.data),console.log(e.data.data),t.tableData=e.data.data,t.total=1):t.$message({type:"error",message:e.data.msg})})).catch((function(){t.$message({type:"error",message:"服务器错误"})}))}},mounted:function(){var e=this;Object(o["a"])({method:"post",url:"/rank",params:{pageNo:1}}).then((function(t){200==t.data.code?(e.tableData=t.data.data.list,e.total=t.data.data.total):e.$message({type:"error",message:t.data.msg})})).catch((function(){e.$message({type:"error",message:"服务器错误"})}))}},s=l,c=(a("b033"),a("2877")),u=Object(c["a"])(s,r,n,!1,null,"c083c578",null);t["default"]=u.exports}}]);
//# sourceMappingURL=chunk-52068312.2bf771aa.js.map