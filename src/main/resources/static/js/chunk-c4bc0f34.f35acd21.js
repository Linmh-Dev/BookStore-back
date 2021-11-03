(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-c4bc0f34"],{"73cf":function(e,r,t){"use strict";t.r(r);var l=function(){var e=this,r=e.$createElement,t=e._self._c||r;return t("div",{attrs:{id:"register"}},[t("el-row",[t("el-col",{attrs:{span:6,offset:9}},[t("h1",{staticStyle:{color:"#606266"}},[t("i",{staticClass:"el-icon-user-solid"}),e._v(" 新用户注册")])])],1),t("el-row",[t("el-col",{attrs:{span:8,offset:8}},[t("el-form",{ref:"ruleForm",staticClass:"demo-ruleForm",attrs:{"status-icon":"",rules:e.rules,model:e.ruleForm,"label-width":"100px"}},[t("el-form-item",{attrs:{label:"用户名",prop:"name"}},[t("el-input",{attrs:{type:"text"},model:{value:e.ruleForm.name,callback:function(r){e.$set(e.ruleForm,"name",r)},expression:"ruleForm.name"}})],1),t("el-form-item",{attrs:{label:"密码",prop:"pass"}},[t("el-input",{attrs:{type:"password","show-password":""},model:{value:e.ruleForm.pass,callback:function(r){e.$set(e.ruleForm,"pass",r)},expression:"ruleForm.pass"}})],1),t("el-form-item",{attrs:{label:"重复密码",prop:"verifyPass"}},[t("el-input",{attrs:{type:"password","show-password":""},model:{value:e.ruleForm.verifyPass,callback:function(r){e.$set(e.ruleForm,"verifyPass",r)},expression:"ruleForm.verifyPass"}})],1),t("el-row",[t("el-col",{attrs:{span:10}},[t("el-form-item",{attrs:{label:"性别"}},[t("el-radio-group",{model:{value:e.ruleForm.gender,callback:function(r){e.$set(e.ruleForm,"gender",r)},expression:"ruleForm.gender"}},[t("el-radio",{attrs:{label:"男"}}),t("el-radio",{attrs:{label:"女"}})],1)],1)],1)],1),t("el-form-item",{attrs:{label:"手机号码",prop:"telphone"}},[t("el-input",{attrs:{type:"tel"},model:{value:e.ruleForm.telephone,callback:function(r){e.$set(e.ruleForm,"telephone",r)},expression:"ruleForm.telephone"}})],1),t("el-form-item",{attrs:{label:"邮箱",prop:"email"}},[t("el-input",{attrs:{type:"email",autocomplete:"off"},model:{value:e.ruleForm.email,callback:function(r){e.$set(e.ruleForm,"email",r)},expression:"ruleForm.email"}})],1),t("el-form-item",{attrs:{label:"个人介绍",prop:"introduce"}},[t("el-input",{attrs:{type:"textarea",maxlength:"100"},model:{value:e.ruleForm.introduce,callback:function(r){e.$set(e.ruleForm,"introduce",r)},expression:"ruleForm.introduce"}})],1),t("el-form-item",{attrs:{label:"验证码",prop:"verifyCode"}},[t("el-row",[t("el-col",{attrs:{span:6}},[t("el-input",{attrs:{type:"tel"},model:{value:e.ruleForm.validateCode,callback:function(r){e.$set(e.ruleForm,"validateCode",r)},expression:"ruleForm.validateCode"}})],1),t("el-col",{attrs:{span:6,offset:1}},[e.showValidate?t("el-image",{attrs:{src:e.validateUrl}}):e._e()],1),t("el-col",{attrs:{span:10}},[t("el-link",{attrs:{underline:!1,type:"primary"},on:{click:e.switchValidate}},[e._v("看不清？换一张")])],1)],1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:function(r){return e.submitForm("ruleForm")}}},[e._v("注册 ")]),t("el-button",{on:{click:function(r){return e.resetForm("ruleForm")}}},[e._v("重填")])],1)],1)],1)],1)],1)},o=[],a=/^[\w-]+@([\w-]+\.)+[a-zA-Z]{2,4}$/,s=/^[a-zA-Z]\w{0,9}$/,i=/^.{6,16}$/,n=/^1(3[0-9]|5[0-3,5-9]|7[1-3,5-8]|8[0-9])\d{8}$/,u={name:"Register",data:function(){var e=this,r=function(r,t,l){""===t?l(new Error("请输入邮箱")):(console.log(e.ruleForm.email),a.test(t)||l(new Error("请输入有效的邮箱地址")),l())},t=function(e,r,t){""===r?t(new Error("请输入会员名")):(s.test(r)||t(new Error("字母数字下划线1到10位，不能是数字开头")),t())},l=function(e,r,t){""===r?t(new Error("请输入密码")):(i.test(r)||t(new Error("密码不合法")),t())},o=function(r,t,l){""===t?l(new Error("请再次输入密码")):t!==e.ruleForm.pass?l(new Error("两次输入密码不一致!")):l()},u=function(e,r,t){""===r?t(new Error("请输入手机号码")):(n.test(r)||t(new Error("请输入有效的手机号码")),t())};return{showValidate:!0,validateUrl:this.$store.state.url+"/validateCode",ruleForm:{name:"",introduce:"",pass:"",gender:"男",email:"",telephone:"",verifyPass:"",validateCode:""},rules:{pass:[{validator:l,trigger:"blur"}],verifyPass:[{validator:o,trigger:"blur"}],email:[{validator:r,trigger:"blur"}],name:[{validator:t,trigger:"blur"}],telephone:[{validator:u,trigger:"blur"}]}}},methods:{switchValidate:function(){this.validateUrl=this.$store.state.url+"/validateCode?"+Math.random()},submitForm:function(e){var r=this;this.$refs[e].validate((function(e){if(!e)return console.log("error"),!1;axios({method:"post",headers:{"Content-Type":"application/json"},url:"user",data:r.ruleForm}).then((function(e){console.log(e),200===e.data.code?(r.$message({message:"注册成功",type:"success"}),r.$router.push("/login")):r.$message({message:e.data.data,type:"error"})}))}))},resetForm:function(e){this.ruleForm={name:"",introduce:"",pass:"",gender:"男",email:"",telephone:"",verifyPass:""},this.$refs[e].resetFields()}}},m=u,c=(t("e30a"),t("2877")),p=Object(c["a"])(m,l,o,!1,null,"2c1a0576",null);r["default"]=p.exports},dd61:function(e,r,t){},e30a:function(e,r,t){"use strict";t("dd61")}}]);
//# sourceMappingURL=chunk-c4bc0f34.f35acd21.js.map