<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>用户远程问诊</title>
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
	  <legend>查询专家</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
	
		    <div class="layui-inline">
		      	<label class="layui-form-label">专家姓名:</label>
				<div class="layui-input-inline">
				<input type="text" name="ename"  autocomplete="off" class="layui-input">
				</div>
		    </div>
		    <div class="layui-inline">
		      	<label class="layui-form-label">职称:</label>
				<div class="layui-input-inline">
				<input type="text" name="eprof"  autocomplete="off" class="layui-input">
				</div>
		    </div>
		   <div class="layui-inline">
		      	<label class="layui-form-label">所在地区:</label>
				<div class="layui-input-inline">
				<input type="text" name="district"  autocomplete="off" class="layui-input">
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
  				 <div  class="layui-form-item" style="text-align: center;">
		  <div class="layui-input-block" >
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		    </div>
		 </div>
		 </div>
	</form>
	<table class="layui-hide" id="userTable" lay-filter="userTable"></table>
	<div style="display: none;" id="userToolBar">
	</div>
	<div  id="userBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="add">图文咨询</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">电话咨询</a>
	  <a class="layui-btn layui-btn-xs" lay-event="viewImage">查看专家头像</a>
	</div>
 <div style="display:none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form layui-row layui-col-space10"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-col-md12 layui-col-xs12">
				<div class="layui-row layui-col-space10">
					<div class="layui-col-md9 layui-col-xs7">
						<div class="layui-form-item magt3">
							<label class="layui-form-label">咨询标题:</label>
							<div class="layui-input-block">
								<input type="hidden" name="dno">
								<input type="text" name="title"  class="layui-input" lay-verify="required" placeholder="请输入咨询标题">
							</div>
						</div>
						<div class="layui-form-item">
							<label class="layui-form-label">咨询用户:</label>
							<div class="layui-input-block">
								<input type="text" name="counselor" value="${user.unname}" disabled class="layui-input" lay-verify="required" placeholder="请输入用户姓名">
							</div>
						</div>	
					<div class="layui-form-item layui-form-text">
					    <label class="layui-form-label">咨询内容:</label>
					    <div class="layui-input-block">
					      <textarea name="content" placeholder="请输入内容" required class="layui-textarea"></textarea>
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
	<div id="viewCarImageDiv" style="display: none;text-align: center;">
		<img alt="专家图片" width="550" height="350" id="view_carimg">
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
			    ,url:'${ctx}/userRestController/loadAllExpert.action' //数据接口
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
					url:"${ctx}/userRestController/loadAllExpert.action?"+params
				})
			});
		   table.on('tool(userTable)', function(obj){
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('【'+data.ename+'】专家电话为'+data.eno); 
			   }else if(layEvent === 'viewImage'){
				  showCarImage(data);
			   }else if(layEvent === 'add'){
				   openAddUser();
			   }
			 });
			var url;
			var mainIndex;
			//打开图文咨询页面
			function openAddUser(){
				mainIndex=layer.open({
					type:1,
					title:'图文咨询',
					content:$("#saveOrUpdateDiv"),
					area:['1000px','500px'],
					success:function(index){
						//清空表单数据       
						$("#dataFrm")[0].reset();
						//设置默认图片
						$("#photo").val("images/寿光.jpg")
						
						url="${ctx}/userRestController/addDiagnose.action";  
						$("#eno").removeAttr("readonly");
					}
				});
			}
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