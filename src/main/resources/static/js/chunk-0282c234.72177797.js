(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-0282c234"],{1221:function(t,e,r){},"2d32":function(t,e,r){"use strict";r("1221")},cf2a:function(t,e,r){"use strict";r.r(e);var n=function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",[r("el-row",[r("el-col",{staticStyle:{"margin-top":"10px"},attrs:{span:22,offset:1}},[r("el-tabs",{attrs:{type:"border-card"},on:{"tab-click":t.handleClick},model:{value:t.activeName,callback:function(e){t.activeName=e},expression:"activeName"}},[r("el-tab-pane",{attrs:{label:"未付款",name:"0"}},[t._l(t.orderInfo,(function(t){return r("div",{key:t.order.id},[r("order-item",{attrs:{"order-info":t,type:0}})],1)})),0===t.orderInfo.length?r("div",[r("el-empty",{attrs:{description:"暂时还没有订单"}})],1):t._e()],2),r("el-tab-pane",{attrs:{label:"待发货",name:"1"}},[t._l(t.orderInfo,(function(t){return r("div",{key:t.order.id},[r("order-item",{attrs:{"order-info":t,type:1}})],1)})),0===t.orderInfo.length?r("div",[r("el-empty",{attrs:{description:"暂时还没有订单"}})],1):t._e()],2),r("el-tab-pane",{attrs:{label:"已完成",name:"2"}},[t._l(t.orderInfo,(function(t){return r("div",{key:t.order.id},[r("order-item",{attrs:{"order-info":t,type:2}})],1)})),0===t.orderInfo.length?r("div",[r("el-empty",{attrs:{description:"暂时还没有订单"}})],1):t._e()],2)],1)],1)],1)],1)},a=[],o=(r("77ed"),function(){var t=this,e=t.$createElement,r=t._self._c||e;return r("div",{directives:[{name:"show",rawName:"v-show",value:t.isShow,expression:"isShow"}]},[r("transition",{attrs:{appear:!0,name:"animate__animated animate__bounce","enter-active-class":"animate__fadeIn"}},[r("el-row",{staticStyle:{margin:"10px"}},[r("el-col",{attrs:{span:20,offset:1}},[r("el-card",{staticClass:"box-card",attrs:{shadow:"hover"}},[r("div",{staticStyle:{"text-align":"left"}},[r("table",{attrs:{width:"100%"}},[r("tr",[r("td",[r("div",[r("span",[t._v("订单编号："+t._s(t.orderInfo.order.id))])]),t._l(t.orderInfo.orderItems,(function(e){return r("div",{key:e.product_id},[r("div",{staticStyle:{"margin-top":"10px","margin-left":"10px",display:"inline-flex"}},[r("el-image",{staticStyle:{width:"50px",height:"50px",margin:"5px"},attrs:{src:t.$store.state.url+e.book.imgurl,fit:"fill"}}),r("span",{staticStyle:{margin:"auto 30px"}},[t._v(t._s(e.book.name))]),r("span",{staticStyle:{margin:"auto 5px"}},[t._v("X "+t._s(e.buyNum))])],1)])}))],2),r("td",[r("div",{staticStyle:{"text-align":"right"}},[0===t.type?r("el-button",{attrs:{type:"primary",round:""},on:{click:t.onCancel}},[t._v("取消订单")]):t._e(),1===t.type?r("el-button",{attrs:{type:"primary",round:""},on:{click:t.onCancel}},[t._v("退款")]):t._e(),2===t.type?r("el-button",{attrs:{type:"primary",round:""},on:{click:t.reputation}},[t._v("评价")]):t._e(),0===t.type?r("el-button",{attrs:{type:"danger",round:""},on:{click:t.payment}},[t._v("付款")]):t._e()],1)])])])])])],1)],1)],1)],1)}),i=[],s={name:"OrderItem",props:["orderInfo","type"],data:function(){return{isShow:!0}},methods:{reputation:function(){},onCancel:function(){var t=this;axios({url:"order/"+this.orderInfo.order.id,method:"delete"}).then((function(e){200===e.data.code&&(t.$notify({title:"退款成功",message:"",type:"success"}),t.isShow=!1)}))},payment:function(){var t=this;axios({url:"order",method:"put",data:this.orderInfo.order}).then((function(e){200===e.data.code&&(t.$notify({title:"支付成功",message:"",type:"success"}),t.isShow=!1)}))}}},d=s,c=r("2877"),l=Object(c["a"])(d,o,i,!1,null,"857c64e0",null),u=l.exports,p=function(t,e){axios({url:"orderInfo/"+t,method:"get"}).then(e)},f={name:"Order",components:{OrderItem:u},data:function(){return{activeName:"1",orderInfo:[]}},mounted:function(){var t=this;p(this.activeName,(function(e){t.orderInfo=e.data.data}))},methods:{handleClick:function(t,e){var r=this;p(this.activeName,(function(t){r.orderInfo=t.data.data}))}}},m=f,h=(r("2d32"),Object(c["a"])(m,n,a,!1,null,"500006ac",null));e["default"]=h.exports}}]);
//# sourceMappingURL=chunk-0282c234.72177797.js.map