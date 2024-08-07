<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8">
	<title>信息审核管理</title>
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
	<link rel="stylesheet" href="${ctx }/resources/layui_ext/dtree/dtree.css">
	<link rel="stylesheet" href="${ctx }/resources/layui_ext/dtree/font/dtreefont.css">
</head>
<body class="childrenBody">
	<!-- 搜索条件开始 -->
	<fieldset class="layui-elem-field layui-field-title" style="margin-top: 20px;">
	  <legend>查询条件</legend>
	</fieldset>
	<form class="layui-form" method="post" id="searchFrm">
		<div class="layui-form-item">
		    <div class="layui-inline">
		      <label class="layui-form-label">发布专家:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="eno"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">省份:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="province"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		     <div class="layui-inline">
		      <label class="layui-form-label">城市:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="city"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		      <div class="layui-inline">
		      <label class="layui-form-label">地区:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="district"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		   <div class="layui-inline">
		      <label class="layui-form-label">所属领域:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="area"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">咨询标题:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="title"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">开始时间:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="startTime" id="startTime" readonly="readonly"  autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <label class="layui-form-label">结束时间:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="endTime"  id="endTime" readonly="readonly" autocomplete="off" class="layui-input">
		      </div>
		    </div>
		    <div class="layui-inline">
		      <button type="button" class="layui-btn layui-btn-normal  layui-icon layui-icon-search" id="doSearch">查询</button>
		      <button type="reset" class="layui-btn layui-btn-warm  layui-icon layui-icon-refresh">重置</button>
		    </div>
		 </div>
	</form>
	
	<!-- 搜索条件结束 -->
	
	<!-- 数据表格开始 -->
	<table class="layui-hide" id="newsTable" lay-filter="newsTable"></table>
	<div style="display: none;" id="newsToolBar">
	<!--  <button type="button" class="layui-btn layui-btn-sm" lay-event="add">增加</button>-->
		    <button type="button" class="layui-btn layui-btn-danger layui-btn-sm" lay-event="deleteBatch">批量删除</button>
	</div>
	<div  id="newsBar" style="display: none;">
	  <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
	  <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
	  <a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="viewNews">查看</a>
	</div>
	<!-- 数据表格结束 -->
	
	<!-- 修改的弹出层开始 -->
	<div style="display: none;padding: 20px" id="saveOrUpdateDiv" >
		<form class="layui-form"  lay-filter="dataFrm" id="dataFrm">
			<div class="layui-form-item">
				<label class="layui-form-label">发布专家:</label>
				<div class="layui-input-block">
					<input type="hidden" name="id" id="id">
					<input type="text" name="eno"   autocomplete="off"
						class="layui-input" disabled class="layui-input layui-disabled">
				</div>
			</div>
		
			
			<div class="layui-form-item">
	                <label class="layui-form-label">选择省份</label>
	                		<div class="layui-input-inline">
	                    			<select name="province" id="province" lay-filter="province">
	                        		<option value="">请选择省</option>
	                    			</select>
	                		</div>
	                		
	              <div class="layui-col-md3 layui-col-xs5">
						<div class="layui-upload-list thumbBox mag0 magt3" id="carimgDiv">
							<!-- 显示上传的图片 -->
							<img class="layui-upload-img thumbImg" id="showCarImg">
							<!-- 保存当前显示图片的地址 -->
							<input type="hidden" name="wentiphoto" id="wentiphoto">
						</div>
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
	       
					<div class="layui-form-item">
							<label class="layui-form-label">问题领域:</label>
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
	<div class="layui-form-item">
		<div class="layui-inline">
		      <label class="layui-form-label">公告标题:</label>
		      <div class="layui-input-inline">
		        <input type="text" name="title"  disabled autocomplete="off" class="layui-input">
		      </div>
		    </div>
	</div>
			<div class="layui-form-item">
				<label class="layui-form-label">公告内容:</label>
				<div class="layui-input-block">
				<!-- 富文本操作 -->
					 <textarea class="layui-textarea layui-hide" name="content" lay-verify="content" id="content"></textarea>
				</div>
			</div>
			
	<div class="layui-form-item">
		<div class="layui-inline">
		      <label class="layui-form-label">审核状态:</label>
		      <div class="layui-input-inline">
		        	<input type="radio" name="disable" value="1" title="审核通过" checked="checked">
			    	<input type="radio" name="disable" value="0" title="审核未通过">
		      </div>
		    </div>
	</div>	

			
			<div class="layui-form-item" style="text-align: center;">
			    <div class="layui-input-block">
			      <button type="button" class="layui-btn layui-btn-normal layui-btn-sm layui-icon layui-icon-release" lay-filter="doSubmit" lay-submit="">提交</button>
			    <!-- 设置监控事件 -->
			      <button type="reset" id="dataFrmResetBtn" class="layui-btn layui-btn-warm layui-btn-sm layui-icon layui-icon-refresh" >重置</button>
			    </div>
		  	</div>
		</form>
	
	</div>
	<!-- 添加和修改的弹出层结束 -->
	
	
	<!-- 查看信息的div -->
	<div id="viewNewsDiv" style="padding: 10px;display: none;">
		<h2 id="view_title" align="center"></h2>
		<hr>
		<div style="text-align: right;">
		<!-- inline-block表示不可以独占一行数据 -->
			发布专家:<span id="view_opername"></span>  <span style="display: inline-block;width: 20px" ></span>
			省       份:<span id="view_province"></span>
			城       市:<span id="view_city"></span>
			地       区:<span id="view_district"></span>
			发布时间:<span id="view_createtime"></span>
		</div>
		<hr>
		<div id="view_content"></div>
	</div>
	
	<!-- 查看大图弹出的层 开始 -->
	<div id="viewCarImageDiv" style="display: none;text-align: center;">
		<img alt="信息图片" width="550" height="350" id="view_carimg">
	</div>
	<!-- 查看大图弹出的层 结束 -->
	
	
	<script src="${ctx }/resources/layui/layui.js"></script>
	<script type="text/javascript">
	    var tableIns;
	    layui.use([ 'jquery', 'layer', 'form', 'table','laydate','layedit','upload' ], function() {
			var $ = layui.jquery;
			var layer = layui.layer;
			var form = layui.form;
			var table = layui.table;
			var laydate=layui.laydate;
			var layedit=layui.layedit;
			var upload=layui.upload;
			//渲染时间
			laydate.render({
				elem:'#startTime',
				type:'datetime'
			});
			laydate.render({
				elem:'#endTime',
				type:'datetime'
			});
			//初始化富文本编辑器
			var editIndex;
			
			
			//渲染数据表格
			 tableIns=table.render({
				 elem: '#newsTable'   //渲染的目标对象
			    ,url:'${ctx}/information/loadAllInformation.action' //数据接口 
			    ,title: '信息数据表'//数据导出来的标题
			    ,toolbar:"#newsToolBar"   //表格的工具条
			    ,height:'full-200'
			    ,cellMinWidth:100 //设置列的最小默认宽度
			    ,page: true  //是否启用分页
			    ,cols: [[   //列表数据
			     {type: 'checkbox', fixed: 'left'}
			      ,{field:'id', title:'ID',align:'center'}
			      ,{field:'eno', title:'发布人',align:'center'}
			      ,{field:'province', title:'省份',align:'center',width:'100'}
			      ,{field:'city', title:'城市',align:'center',width:'100'}
			      ,{field:'district', title:'地区',align:'center',width:'100'}
			      ,{field:'area', title:'所属领域',align:'center',width:'100'}
			      ,{field:'title', title:'公告标题',align:'center'}
			       ,{field:'content', title:'公告内容',align:'center'} 
			       ,{field:'wentiphoto',title:'信息缩略图',align:'center',width:'150',templet:function(d){
				    	  return "<img width=40 height=30 src=${ctx}/file/downloadShowFile.action?path="+d.wentiphoto+" />";
				      }}
			      ,{field:'date', title:'发布时间',align:'center'}
			      ,{field:'disable', title:'审核状态',align:'center',templet:function(d){
			    	  return d.disable=='1'?'<font color=green>已通过审核</font>':'<font color=red>审核不通过</font>';
			      }}
			      ,{fixed: 'right', title:'操作', toolbar: '#newsBar', width:220,align:'center'}
			    ]]
			})
			//模糊查询
			$("#doSearch").click(function(){
				var params=$("#searchFrm").serialize();
				tableIns.reload({
					url:"${ctx}/information/loadAllInformation.action?"+params,
							//进行数据标记
					page:{curr:1}
				})
			});
			
			//监听头部工具栏事件
			table.on("toolbar(newsTable)",function(obj){
				 switch(obj.event){
				    case 'add':
				      openAddNews();
				    break;
				    case 'deleteBatch':
				    	deleteBatch();
					break;
				  };
			})
			//监听行工具事件
		   table.on('tool(newsTable)', function(obj){
			   
			   var data = obj.data; //获得当前行数据
			   var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			  if(layEvent === 'del'){ //删除
				  layer.confirm('真的删除【'+data.title+'】这个公告吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/information/deleteInformation.action",{id:data.id},function(res){ 
				    	   layer.msg(res.msg);
				    	    //刷新数据 表格
							tableIns.reload();
				       })
				     }); 
			   } else if(layEvent === 'edit'){ //编辑
				   form.val("dataFrm",data);
			     openUpdateNews(data);
			   }else if(layEvent==='viewNews'){
				   viewNews(data);
			   }
			 });
			
			var url;
			var mainIndex;
			//打开添加页面
			function openAddNews(){
				mainIndex=layer.open({
					type:1,
					title:'添加信息',
					content:$("#saveOrUpdateDiv"),
					area:['800px','550px'],
					success:function(index){
						editIndex=layedit.build('content'); //建立编辑器
			
						//清空表单数据       
						$("#dataFrm")[0].reset();
						url="${ctx}/news/addNews.action";
					}
				});
			}
	
			//将其富文本编辑器中的内容重置
			$("#dataFrmResetBtn").click(function(){
				layedit.setContent(editIndex,"");	
			});
			//打开修改页面
			function openUpdateNews(data){
				mainIndex=layer.open({
					type:1,
					title:'修改咨询信息',
					content:$("#saveOrUpdateDiv"),
					area:['800px','700px'],
					success:function(index){
						editIndex=layedit.build('content'); //建立编辑器
						form.val("dataFrm",data);
						url="${ctx}/information/updateInformation.action";
					}
				});
			}
			//保存
			form.on("submit(doSubmit)",function(obj){
				layedit.sync(editIndex);//把富文本里面的数据同步到自己写的textarea里面
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
			
			//查看
			function viewNews(data){
				mainIndex=layer.open({
					type:1,
					title:'查看公告',
					content:$("#viewNewsDiv"),
					area:['800px','550px'],
					success:function(index){
						$("#view_title").html(data.title);
						$("#view_province").html(data.province);
						$("#view_city").html(data.city);
						$("#view_district").html(data.district);
						$("#view_opername").html(data.eno);
						$("#view_createtime").html(data.date);
						$("#view_content").html(data.content);
					}
				});
			}
			
			//批量删除
			function deleteBatch(){
				//得到选中的数据行
				var checkStatus = table.checkStatus('newsTable');
			    var data = checkStatus.data;
			    var params="";
			    $.each(data,function(i,item){
			    	if(i==0){
			    		params+="ids="+item.id;
			    	}else{
			    		params+="&ids="+item.id;
			    	}
			    });
			    layer.confirm('真的删除选中的这些公告吗', function(index){
				       //向服务端发送删除指令
				       $.post("${ctx}/information/deleteBatchInformation.action",params,function(res){ 
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
		            $('#wentiphoto').val(res.data.src);
		            $('#carimgDiv').css("background","#fff");
		        }
		    });
			
			//查看大图
			function showCarImage(data){
				
				mainIndex=layer.open({
					type:1,
					title:"【"+data.title+'】的咨询信息图片',
					content:$("#viewCarImageDiv"),
					area:['700px','500px'],
					success:function(index){
						$("#view_carimg").attr("src","${ctx}/file/downloadShowFile.action?path="+data.wentiphoto);
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