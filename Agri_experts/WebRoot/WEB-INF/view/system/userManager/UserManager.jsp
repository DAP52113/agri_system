<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户管理</title>
	<meta name="renderer" content="webkit">
	<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<meta http-equiv="Access-Control-Allow-Origin" content="*">
	<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<meta name="apple-mobile-web-app-status-bar-style" content="black">
	<meta name="apple-mobile-web-app-capable" content="yes">
	<meta name="format-detection" content="telephone=no">
	<link rel="icon" href="favicon.ico">
	<link rel="stylesheet" href="${ctx }/resources/layui/css/layui.css" media="all" />
	<link rel="stylesheet" href="${ctx }/resources/css/public.css" media="all" />
</head>
<body class="childrenBody">
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">用户名:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="unname"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
				<div class="layui-input-inline">
				</div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">个人简介:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="introduce"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		 </div>
		 <div class="layui-form-item">
		    <div class="layui-inline">
		      <div class="layui-input-inline">
		      </div>
		    </div>
		 </div>
		 <div  class="layui-form-item" style="text-align: center;">
		  <div class="layui-input-block" >
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		    </div>
		 </div>
	</form>
	<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
	<div style="display: none;" id="userToolBar">
	   <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>
       <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
	</div>
	<div  id="userBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="resetUserPwd">重置密码</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	  <a class="layui-btn layui-btn-xs" lay-event="viewImage">查看大图</a>
	 <!-- <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="selectUserRole">分配角色</a>-->
	</div>
 <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md9 layui-col-xs7">
						<div class="layui-form-item magt3">
							<label class="layui-form-label">用户名:</label>
							<div class="layui-input-block">
								<input type="hidden" name="id">
								<input type="text" name="unname"  class="layui-input" lay-verify="required" placeholder="请输入用户名">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">个人密码:</label>
							<div class="layui-input-block">
								<input type="password" name="upsw" class="layui-input" lay-verify="required" placeholder="请输入用户密码">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">个人介绍:</label>
							<div class="layui-input-block">
								<input type="text" name="introduce" class="layui-input" lay-verify="required" placeholder="请输入个人介绍">
							</div>
						</div>
					</div>
					<div class="layui-col-md3 layui-col-xs5">
						<div class="layui-upload-list thumbBox mag0 magt3" id="carimgDiv">
							<!-- 显示上传的图片 -->
							<img class="layui-upload-img thumbImg" id="showCarImg">
							<!-- 保存当前显示图片的地址 -->
							<input type="hidden" name="uphoto" id="uphoto">
						</div>
					</div>
				</div>
				<div class="layui-form-item magb0" style="text-align: center;">
					    <div class="layui-input-block">
					    &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					      <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
					      &nbsp;&nbsp;&nbsp;&nbsp;
					      <button type="reset" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
					    </div>
				  	</div>
			</div>
		</form>
	</div>
	<div id="viewCarImageDiv" style="display: none;text-align: center;">
		<img alt="用户图片" width="550" height="350" id="view_carimg">
	</div>
	<div style="display: none;padding: 10px" id="selectUserRole">
		<table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
	</div>
	<script src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    var tableIns;
	    layui.extend({
			dtree:'${ctx}/resources/layui_ext/dist/dtree'
		}).use([ 'jquery', 'layer', 'form', 'table','dtree' ,'upload' ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var dtree=layui.dtree;
			var upload=layui.upload;
			//渲染数据表格
			 tableIns=table.render({
				 elem: '#userTable'   //渲染的目标对象
			    ,url:'${ctx}/user/loadAllUser.action' //数据接口
			    ,title: '用户数据表'//数据导出来的标题
			    ,toolbar:"#userToolBar"   //表格的工具条
			    ,height:'full-200'
			    ,cellMinWidth:100 //设置列的最小默认宽度
			    ,page: true  //是否启用分页
			    ,cols: [[   //列表数据
			     {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID',align:'center',width:'80'}
			      ,{field:'unname', title:'用户名',align:'center',width:'100'}
			      ,{field:'introduce', title:'个人介绍',align:'center',width:'100'}
			      ,{field:'uphoto',title:'个人头像',align:'center',width:'150',templet:function(d){
			    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile.action?path="+d.uphoto+" />";
			      }}
			      ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:260,align:'center'}
			    ]]
			})
			//模糊查询
			$("#doSearch").click(function(){
				var params=$("#searchFrm").serialize();
				tableIns.reload({
					url:"${ctx}/user/loadAllUser.action?"+params
				})
			});
			table.on("toolbar(userTable)",function(obj){
				 switch(obj.event){
				    case 'add':
				      openAddUser();
				    break;
				    case 'deleteBatch':
				      deleteBatch();
					break;
				  };
			})
			//监听行工具事件
		   table.on('tool(userTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的删除【'+data.unname+'】这个用户吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/user/deleteUser.action",{id:data.id},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
			     openUpdateUser(data);
			   }else if(layEvent==='resetUserPwd'){
				   layer.confirm('真的重置【'+data.unname+'】这个用户的密码吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/user/resetUserPwd.action",{id:data.id},function(res){
				    	   layer.msg(res.msg);
				       })
				     }); 
			   }else if(layEvent==='selectUserRole'){
				 openselectUserRole(data);
			   }else if(layEvent === 'viewImage'){
				  showCarImage(data);
			   }
			 });
			var url;
			var mainIndex;
			//打开添加页面
			function openAddUser(){
				mainIndex=layer.open({
					type:1,
					title:'添加用户',
					content:$("#saveOrUpdateDiv"),
					area:['1000px','400px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm")[0].reset();
						//设置默认图片
						
						$("#uphoto").val("images/寿光.jpg")
						url="${ctx}/user/addUser.action";
						$("#unname").removeAttr("readonly");
					}
				});
			}
			function openUpdateUser(data){
				mainIndex=layer.open({
					type:1,
					title:'修改用户',
					content:$("#saveOrUpdateDiv"),
					area:['800px','400px'],
					success:function(index){
						form.val("dataFrm",data);
						$("#showCarImg").attr("src","${ctx}/file/downloadShowFile.action?path="+data.uphoto);
						url="${ctx}/user/updateUser.action";
						$("#unname").attr("readonly","readonly");
					}
				});
			}
			//保存
			form.on("submit(doSubmit)",function(obj){
				//序列化表单数据
				var params=$("#dataFrm").serialize();
				$.post(url,params,function(obj){
					layer.msg(obj.msg);
					//关闭弹出层
					layer.close(mainIndex)
					//刷新数据 表格
					tableIns.reload();
				})
			});
			function deleteBatch(){
				//得到选中的数据行
				var checkStatus = table.checkStatus('userTable');
			    var data = checkStatus.data;
			    var params="";
			    $.each(data,function(i,item){
			    	if(i==0){
			    		params+="ids="+item.id;
			    	}else{
			    		params+="&ids="+item.id;
			    	}
			    });
			    layer.confirm('真的删除选中的这些用户吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/user/deleteBatchUser.action",params,function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			}
		    upload.render({
		        elem: '#carimgDiv',
		        url: '${ctx}/file/uploadFile.action',
		        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
		        acceptMime:'images/*',
		        field:"mf",
		        done: function(res, index, upload){
		            $('#showCarImg').attr('src',"${ctx}/file/downloadShowFile.action?path="+res.data.src);
		            $('#uphoto').val(res.data.src);
		            $('#carimgDiv').css("background","#fff");
		        }
		    });
			function showCarImage(data){
				mainIndex=layer.open({
					type:1,
					title:"【"+data.unname+'】的用户图片',
					content:$("#viewCarImageDiv"),
					area:['700px','500px'],
					success:function(index){
						$("#view_carimg").attr("src","${ctx}/file/downloadShowFile.action?path="+data.uphoto);
					}
				});
			}
			function openselectUserRole(data){
				mainIndex=layer.open({
					type:1,
					title:'分配【'+data.unname+'】的角色',
					content:$("#selectUserRole"),
					area:['800px','400px'],
					btnAlign:'c',
					height:'full',
					btn:['<div class="layui-icon layui-icon-release">确认分配</div>','<div class="layui-icon layui-icon-close">取消分配</div>'],
					yes:function(index, layero){
						//得到选中的数据行
						var checkStatus = table.checkStatus('roleTable');
						var roleData = checkStatus.data;
						var params="userid="+data.userid;
					    $.each(roleData,function(i,item){
					    	params+="&ids="+item.roleid;
						});
					    //保存
					    $.post("${ctx}/user/saveUserRole.action",params,function(obj){
					    	layer.msg(obj.msg);
					    })
					},
					success:function(index){
						//渲染数据表格
						 var roleTableIns=table.render({
							 elem: '#roleTable'   //渲染的目标对象
						    ,url:'${ctx}/user/initUserRole.action?userid='+data.userid //数据接口
						    ,title: '角色列表'//数据导出来的标题
						    ,cellMinWidth:100 //设置列的最小默认宽度
						    ,cols: [[   //列表数据
						     {type: 'checkbox', fixed: 'left'}
						      ,{field:'roleid', title:'ID',align:'center'}
						      ,{field:'rolename', title:'角色名称',align:'center'}
						      ,{field:'roledesc', title:'角色备注',align:'center'}
						    ]]
						})
					}
				});
			}
		});
	</script>
</body>
</html>