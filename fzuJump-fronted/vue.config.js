//http://47.94.160.46:8080/swagger-ui.html
// module.exports = {
//     devServer: {
//         proxy: {
//             '/api/': {
//                 target: 'http://47.94.160.46:8080/',
//                 changeOrigin: true, //是否选择性改变跨域
//                 pathRewrite:{
//                     '^/api':''  //做替换
//                 }
//             }
//         }
//     }
// };
module.exports = {
     devServer: {
         port: 8081,
         proxy: {
             '/api/': {
                 //target:"http://49.234.32.149:8082/",
                 target:"http://localhost:8082/",
                 changeOrigin: true, //是否选择性改变跨域
                 pathRewrite:{
                     '^/api':''  //做替换
                 }
             }
         }
     }
 };







