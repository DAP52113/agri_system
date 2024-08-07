<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>农业专家管理</title>
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
		      <label class="layui-form-label">专家用户名:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="eno"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      	<label class="layui-form-label">专家姓名:</label>
				<div class="layui-input-inline">
				<input type="text" name="ename"  autocomplete="off" class="layui-input">
				</div>
		    </div>
		    
	
		    
		    <div class="layui-inline">
		      <label class="layui-form-label">性别:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="esex"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    
		    <div class="layui-inline">
		      	<label class="layui-form-label">职称:</label>
				<div class="layui-input-inline">
				<input type="text" name="eprof"  autocomplete="off" class="layui-input">
				</div>
		    </div>
		    

		 </div>
		
		 <div class="layui-form-item">
		    <div class="layui-inline">
		      	<label class="layui-form-label">所在省份:</label>
				<div class="layui-input-inline">
				<input type="text" name="province"  autocomplete="off" class="layui-input">
				</div>
		    </div>

			<div class="layui-inline">
		      	<label class="layui-form-label">所在城市:</label>
				<div class="layui-input-inline">
				<input type="text" name="city"  autocomplete="off" class="layui-input">
				</div>
		    </div>
		   <div class="layui-inline">
		      	<label class="layui-form-label">所在地区:</label>
				<div class="layui-input-inline">
				<input type="text" name="district"  autocomplete="off" class="layui-input">
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
	
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
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
	
	</div>
	<!-- 数据表格结束 -->
	
 
 <div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md9 layui-col-xs7">
						<div class="layui-form-item magt3">
							<label class="layui-form-label">专家用户名:</label>
							<div class="layui-input-block">
								<input type="hidden" name="eid">
								<input type="text" name="eno"  class="layui-input" lay-verify="required" placeholder="请输入用户名">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">专家姓名:</label>
							<div class="layui-input-block">
								<input type="text" name="ename" class="layui-input" lay-verify="required" placeholder="请输入用户姓名">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">个人密码:</label>
							<div class="layui-input-block">
								<input type="password" name="epsw" class="layui-input" lay-verify="required" placeholder="请输入个人密码">
							</div>
						</div>
						
						<div class="layui-form-item">
							<label class="layui-form-label">性别:</label>
							<div class="layui-input-block">
									 <input type="radio" name="esex" value="男" checked="checked" title="男">
						 			<input type="radio" name="esex" value="女" title="女">
							</div>
						</div>
					<div class="layui-form-item">
							<label class="layui-form-label">专家职称:</label>
							<div class="layui-input-block">
								<input type="text" name="eprof" class="layui-input" lay-verify="required" placeholder="请输入专家职称">
							</div>
						</div>
						
						<div class="layui-form-item">
                				<label class="layui-form-label">选择省份</label>
                		<div class="layui-input-inline">
                    			<select name="province" id="province" lay-filter="province">
                        		<option value="">请选择省</option>
                    			</select>
                		</div>
                		</div>
                <div class="layui-form-item">
                		<label class="layui-form-label">选择城市</label>
                		<div class="layui-input-inline">
                    			<select name="city" id="city" lay-filter="city">
                       		 	<option value="">请选择市</option>
                    			</select>
                		</div>
                </div>
                 <div class="layui-form-item">
                 	<label class="layui-form-label">选择地区</label>
                	<div class="layui-input-inline">
                   				 <select name="district" id="district" lay-filter="district">
                        		<option value="">请选择县/区</option>
                   				 </select>
                		</div>
            		</div>
					<!--  	
					<div class="layui-form-item">
							<label class="layui-form-label">省份:</label>
							<div class="layui-input-block">
								<input type="text" name="province" class="layui-input" lay-verify="required" placeholder="请输入专家省份">
							</div>
						</div>
					<div class="layui-form-item">
							<label class="layui-form-label">城市:</label>
							<div class="layui-input-block">
								<input type="text" name="city" class="layui-input" lay-verify="required" placeholder="请输入专家城市">
							</div>
						</div>	
						
						<div class="layui-form-item">
							<label class="layui-form-label">地区:</label>
							<div class="layui-input-block">
								<input type="text" name="district" class="layui-input" lay-verify="required" placeholder="请输入专家地区">
							</div>
						</div>	
						-->

					<div class="layui-form-item">
							<label class="layui-form-label">专家领域:</label>
							<div class="layui-input-block">
								<select name="area" lay-verify="required" >
  										<option value ="种植">种植</option>
  										<option value ="养殖">养殖</option>
  										<option value="园艺">园艺</option>
  										<option value="植保">植保</option>
  										<option value ="农业工程">农业工程</option>
  										<option value ="农业经济">农业经济</option>
  										<option value="政策法规">政策法规</option>
  										<option value="其他">其他</option>
								</select>
							</div>
						</div>	
						
					</div>
					<div class="layui-col-md3 layui-col-xs5">
						<div class="layui-upload-list thumbBox mag0 magt3" id="carimgDiv">
							<!-- 显示上传的图片 -->
							<img class="layui-upload-img thumbImg" id="showCarImg">
							<!-- 保存当前显示图片的地址 -->
							<input type="hidden" name="photo" id="photo">
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
 
 
	
	<!-- 查看大图弹出的层 开始 -->
	<div id="viewCarImageDiv" style="display: none;text-align: center;">
		<img alt="专家图片" width="550" height="350" id="view_carimg">
	</div>
	<!-- 查看大图弹出的层 结束 -->
	
	
	<!-- 用户分配角色的弹出层开始 -->
	<div style="display: none;padding: 10px" id="selectUserRole">
		<table class="layui-hide" id="roleTable" lay-filter="roleTable"></table>
	</div>
	<!-- 用户分配角色的弹出层结束 -->
	
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
			    ,url:'${ctx}/expert/loadAllExpert.action' //数据接口
			    ,title: '专家数据表'//数据导出来的标题
			    ,toolbar:"#userToolBar"   //表格的工具条
			    ,height:'full-200'
			    ,cellMinWidth:100 //设置列的最小默认宽度
			    ,page: true  //是否启用分页
			    ,cols: [[   //列表数据
			     {type: 'checkbox', fixed: 'left'}
			      ,{field:'eid', title:'ID',align:'center',width:'80'}
			      ,{field:'eno', title:'专家用户名',align:'center',width:'100'}
			      ,{field:'ename', title:'姓名',align:'center',width:'100'}
			      ,{field:'esex', title:'性别',align:'center',width:'100'}
			      ,{field:'eprof', title:'职称',align:'center',width:'100'}
			      ,{field:'province', title:'省份',align:'center',width:'100'}
			      ,{field:'city', title:'城市',align:'center',width:'100'}
			      ,{field:'district', title:'地区',align:'center',width:'100'}
			      ,{field:'area', title:'所属领域',align:'center',width:'100'}
			      ,{field:'photo',title:'个人头像',align:'center',width:'150',templet:function(d){
			    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile.action?path="+d.photo+" />";
			      }}
			      ,{fixed: 'right', title:'操作', toolbar: '#userBar', width:260,align:'center'}
			    ]]
			})
			//模糊查询
			$("#doSearch").click(function(){
				var params=$("#searchFrm").serialize();
				tableIns.reload({
					url:"${ctx}/expert/loadAllExpert.action?"+params
				})
			});
			
			//监听头部工具栏事件
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
				  layer.confirm('真的删除【'+data.eno+'】这个用户吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/expert/deleteExpert.action",{eid:data.eid},function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
			     openUpdateUser(data);
			   }else if(layEvent==='resetUserPwd'){
				   layer.confirm('真的重置【'+data.eno+'】这个用户的密码吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/expert/resetExpertPwd.action",{eid:data.eid},function(res){
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
					title:'添加专家',
					content:$("#saveOrUpdateDiv"),
					area:['1000px','500px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm")[0].reset();
						//设置默认图片
						$("#photo").val("images/寿光.jpg")
						url="${ctx}/expert/addExpert.action";  
						$("#eno").removeAttr("readonly");
					}
				});
			}
			//打开修改页面
			function openUpdateUser(data){
				mainIndex=layer.open({
					type:1,
					title:'修改专家',
					content:$("#saveOrUpdateDiv"),
					area:['1000px','500px'],
					success:function(index){
						form.val("dataFrm",data);
						$("#showCarImg").attr("src","${ctx}/file/downloadShowFile.action?path="+data.photo);
						url="${ctx}/expert/updateExpert.action";
						$("#eno").attr("readonly","readonly");
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
			
			//批量删除
			function deleteBatch(){
				//得到选中的数据行
				var checkStatus = table.checkStatus('userTable');
			    var data = checkStatus.data;
			    var params="";
			    $.each(data,function(i,item){
			    	if(i==0){
			    		params+="eids="+item.eid;
			    	}else{
			    		params+="&eids="+item.eid;
			    	}
			    });
			    layer.confirm('真的删除选中的这些用户吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/expert/deleteBatchExpert.action",params,function(res){
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			}
			
			//上传图片
			//上传缩略图
		    upload.render({
		        elem: '#carimgDiv',
		        url: '${ctx}/file/uploadFile.action',
		        method : "post",  //此处是为了演示之用，实际使用中请将此删除，默认用post方式提交
		        acceptMime:'images/*',
		        field:"mf",
		        done: function(res, index, upload){
		            $('#showCarImg').attr('src',"${ctx}/file/downloadShowFile.action?path="+res.data.src);
		            $('#photo').val(res.data.src);
		            $('#carimgDiv').css("background","#fff");
		        }
		    });
	
			//查看大图
			function showCarImage(data){
				
				mainIndex=layer.open({
					type:1,
					title:"【"+data.eno+'】的专家图片',
					content:$("#viewCarImageDiv"),
					area:['700px','500px'],
					success:function(index){
						$("#view_carimg").attr("src","${ctx}/file/downloadShowFile.action?path="+data.photo);
					}
				});
			}
			


			
			//打开分配角色的弹出层
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

<script type="text/javascript">
        var defaults = {
            s1: 'province',
            s2: 'city',
            s3: 'district',
            v1: null,
            v2: null,
            v3: null
        };
 
</script>
<script src="${ctx}/resources/assets/jquery-1.12.4.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/data.js"></script>
	<script type="text/javascript" src="${ctx}/resources/assets/province.js"></script>
</body>
</html>