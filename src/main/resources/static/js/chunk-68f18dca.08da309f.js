(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-68f18dca"],{"0156":function(t,e,n){"use strict";n("44bd")},3530:function(t,e,n){"use strict";n.r(e);var o=function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("el-container",{staticStyle:{border:"1px solid #eee"}},[n("el-aside",{staticStyle:{"background-color":"rgb(238, 241, 246)"},attrs:{width:"200px"}},[n("el-menu",{attrs:{"default-active":t.activeItem},on:{select:t.handleSelect}},[n("el-menu-item",{attrs:{index:"/userManagement"}},[t._v("用户管理")]),n("el-menu-item",{attrs:{index:"/productManagement"}},[t._v("商品管理")]),n("el-menu-item",{attrs:{index:"/hotBookList"}},[t._v("热销榜单")]),n("el-menu-item",{attrs:{index:"/orderManagement"}},[t._v("订单管理")]),n("el-menu-item",{attrs:{index:"/broadcastManagement"}},[t._v("公告管理")])],1)],1),n("el-container",[n("el-header",{staticStyle:{"text-align":"right","font-size":"12px"}},[n("el-dropdown",{on:{command:t.handleCommand}},[n("i",{staticClass:"el-icon-setting",staticStyle:{"margin-right":"15px"}}),n("el-dropdown-menu",{attrs:{slot:"dropdown"},slot:"dropdown"},[n("el-dropdown-item",{attrs:{command:"logout"}},[t._v("退出")])],1)],1),n("span",[t._v(t._s(t.$store.state.username))])],1),n("el-main",[n("router-view")],1)],1)],1)],1)},a=[],i={name:"Admin",data:function(){return{activeItem:"/productManagement"}},methods:{handleCommand:function(t){var e=this;"logout"===t&&axios({method:"get",url:"out"}).then((function(t){e.$store.commit("PUT_LOGIN_STATE",!1),e.$store.commit("PUT_USERNAME",""),e.$router.push("/"),location.reload()}))},handleSelect:function(t){t!==this.$route.fullPath&&this.$router.push(t)}},mounted:function(){this.activeItem=this.$route.fullPath}},r=i,s=(n("0156"),n("2877")),l=Object(s["a"])(r,o,a,!1,null,null,null);e["default"]=l.exports},"44bd":function(t,e,n){}}]);
//# sourceMappingURL=chunk-68f18dca.08da309f.js.map