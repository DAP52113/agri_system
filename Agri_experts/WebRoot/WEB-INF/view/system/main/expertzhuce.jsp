<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>专家注册页面</title>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css_zhuce/public.css"/>
    <link rel="stylesheet" type="text/css" href="${ctx }/resources/css_zhuce/login.css"/>
    
    <script src="${ctx }/resources/js/jquery-1.12.4.min.js"></script>
    <script src="${ctx }/resources/js/function.js"></script>
    
    	


</head>
<script type="text/javascript">
		function checkForm(){
			var userName = document.getElementById("userName").value;
			var name = document.getElementById("name").value;
			var password = document.getElementById("password").value;
			var repassword = document.getElementById("repassword").value;
			var eprof = document.getElementById("eprof").value;
			var area = document.getElementById("area").value;
			var photo = document.getElementById("photo").value;
			
			$("#error").html("");
		
			if(userName=="" || password==""|| repassword=="" ||name =="" ||eprof == ""||area =="" ||photo ==""){
				alert("信息填写不完整！");
				
			}else if(password != repassword){
				alert("确认密码与原密码不一致！");
				return false;
			}
			if(userName !="" && password != "" && repassword != "" && password == repassword && name != "" && eprof != "" && area != "" && photo != ""){
				alert("注册成功！请登录");
				return true;
			}
			
		}
		
		var data = {
				"province": [{
						"name": "广东省",
						"citylist": [{
								"name": "天河区",
								"arealist": [{
										"name": "岗顶"
									},
									{
										"name": "体育西路"
									},
									{
										"name": "东圃"
									}
								]
							},
							{
								"name": "白云区",
								"arealist": [{
										"name": "人和"
									},
									{
										"name": "嘉禾望岗"
									},
									{
										"name": "三元里"
									}
								]
							},
							{
								"name": "海珠区",
								"arealist": [{
										"name": "中大"
									},
									{
										"name": "广州塔"
									},
									{
										"name": "大塘"
									}
								]
							}
						]
					},
					{
						"name": "广西省",
						"citylist": [{
								"name": "桂林市",
								"arealist": [{
										"name": "七星"
									},
									{
										"name": "崖山"
									},
									{
										"name": "燕山"
									}
								]
							},
							{
								"name": "南宁市",
								"arealist": [{
										"name": "兴宁区"
									},
									{
										"name": "青秀区",
									},
									{
										"name": "西乡塘区",
									}
								]
							},
							{
								"name":"河池市",
								"arealist":[{
									"name":"大化县瑶族自治县"
								},
								{
									"name":"都安瑶族自治县"
								}]
							}
						]
					}
				]
			}

			window.onload = function() {
				// 填充初始数据
				// 获取json对象的province属性：返回的是一个数组对象
				// 获取页面加载时的初始省份信息  pros={'广东省','广西省'}
				var pros = data.province;
				// 获取页面加载时的初始城市信息	pros[0].citylist：{'天河区','白云区','海珠区'}
				var cities = pros[0].citylist;
				// 获取页面加载时的初始地区信息	pros[0].citylist[0].arealist：{'岗顶','体育西路','东圃'}
				var areas = cities[0].arealist;
				// 填充省份
				// 获取省份下拉框
				var province = document.getElementById('province');
				// 遍历省份数组，添加初始数据
				for (var i = 0; i < pros.length; i++) {
					// 创建option标签
					var option = document.createElement('option');
					// 设置option标签的文本内容
					option.innerText = pros[i].name;
					// 将option追加到select中
					province.appendChild(option);
				}
				// 填充城市
				// 获取城市下拉框
				var city = document.getElementById('city');
				// 遍历城市数组，添加数据
				for (var i = 0; i < cities.length; i++) {
					var option = document.createElement('option');
					option.innerText = cities[i].name;
					city.appendChild(option);
				}
				// 填充地区
				// 获取地区下拉框
				var area = document.getElementById('county');
				// 遍历地区数组，添加数据
				for (var i = 0; i < areas.length; i++) {
					var option = document.createElement('option');
					option.innerText = areas[i].name;
					area.appendChild(option);
				}
			}

			// 省份下拉框发生改变时的操作
			function changeProvince() {
				// 获取省份下拉框
				var province = document.getElementById('province');
				// 获取选中省份的下标
				var index = province.selectedIndex;
				// 获取省份
				var pros = data.province[index];
				// 所选省份对应的城市
				var cities = pros.citylist;
				// 对应城市的地区
				var areas = cities[0].arealist;
				// 获取城市下拉框
				var city = document.getElementById('city');
				// 清除生词下拉框原先的数据
				// 获取原先的option数组
				var childs = city.childNodes;
				var length = childs.length;
				// 每次删除，数组的长度都是动态变化减少，所以需要定义一个变量用于接受数组原始长度，
				// 按照原始的长度来确定循环次数，因为循环删除，元素逐渐减少，所以每次删除的都是下标为0的元素
				for (var i = 0; i < length; i++) {
					// 删除的都是第一个元素，直至没有
					city.removeChild(childs[0]);
				}
				// 填充对应的城市
				for (var i = 0; i < cities.length; i++) {
					var option = document.createElement('option');
					option.innerText = cities[i].name;
					city.appendChild(option);
				}
				// 与城市的操作一样
				var area = document.getElementById('county');
				// 获取地区的子节点数组（option）
				var childNodes = area.childNodes;
				// 子节点数组的长度
				var cength = childNodes.length;
				// 遍历childNodes，循环删除
				for (var i = 0; i < cength; i++) {
					area.removeChild(childNodes[0]);
				}
				for (var i = 0; i < areas.length; i++) {
					var option = document.createElement('option');
					option.innerText = areas[i].name;
					area.appendChild(option);
				}
			}
			// 城市下拉框发生改变时的操作
			function changeCity() {
				// 获取选中省份的下标
				var pindex = document.getElementById('province').selectedIndex;
				// 获取选中城市的下标
				var cindex = document.getElementById('city').selectedIndex;
				// 获取地区数组
				var areas = data.province[pindex].citylist[cindex].arealist;
				// 获取地区下拉框
				var area = document.getElementById('county');
				// 获取原先的option数组
				var childs = area.childNodes;
				var length = childs.length;
				// 删除原先的option元素
				for (var i = 0; i < length; i++) {
					area.removeChild(childs[0]);
				}
				// 向area填充新的内容
				for (var i = 0; i < areas.length; i++) {
					var option = document.createElement('option');
					option.innerText = areas[i].name;
					area.appendChild(option);
				}
			}


		
		

</script>


<style>
	.reg p .error{
		width:310px;
		display:inline-block;
		border :1px solid #ff855a;
		background-color:#ffe8e0;
	}
	.reg form {
    width: 400px;
    height: 800px;
    background: #fff;
}

</style>
<body>
<div class="reg">
    <form action="${ctx}/userRegister/expertRegisterToLogin.action" method="post" onsubmit="return checkForm()">
    <h1><a href="#"><img src="${ctx }/resources/images/市级专家.jpg" width="30px" height="30px"></a></h1>
        <p><h2>专家注册</h2></p>
      
        <p><input style="width:300px;" type="text" id ="userName" name="userName" onfocus ="FocusItemExpert(this)" onBlur="CheckItemExpert(this)"  value="" placeholder="请输入登录手机号"><span></span></p>
       
         <p><input style="width:300px;" type="text" id="name" name="name" onfocus ="FocusItemExpert(this)" onBlur="CheckItemExpert(this)" value="" placeholder="请输入专家姓名"><span></span></p>
        <p><input style="width:300px;" type="password"  id = "password" name="password" onfocus ="FocusItemExpert(this)" onBlur="CheckItemExpert(this)" value="" placeholder="请输入密码"><span></span></p>
        <p><input style="width:300px;" type="password"  id = "repassword" name="repassword" onfocus ="FocusItemExpert(this)" onBlur="CheckItemExpert(this)"  value="" placeholder="请确认密码"><span></span></p>
     
         <input  id="man" type="radio" name="userType" value="男" checked><strong>男</strong>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
         <input style="margin-left:60px;" id="woman" type="radio" name="userType" value="女"><strong>女</strong>
      	 <p><input style="width:300px;" type="text"  id = "eprof" name="eprof" onfocus ="FocusItemExpert(this)" onBlur="CheckItemExpert(this)" value="" placeholder="请输入专家职称"><span></span></p>
        <p>
        <select class="form-control" id="area"  name="area" style="width:312px;">
        <option>请选择注册专家领域</option>
		<option value="种植">种植</option>
		<option value="养殖">养殖</option>
		<option value="园艺">园艺</option>
		<option value="植保">植保</option>
		<option value="农业工程">农业工程</option>
		<option value="农业经济">农业经济</option>
		<option value="政策法规">政策法规</option>
		<option value="其他">其他</option>
   </select></p>
   
       <p>
        <select class="form-control" id="province"  name="province" style="width:312px;"  onclick="changeProvince()">
        <option>请选择注册专家省份</option>
   </select></p>
   <p>
        <select class="form-control" id="city"  name="city" style="width:312px;" onclick="changeCity()">
       <option>请选择注册专家城市</option>
   </select></p>
   <p>
        <select class="form-control" id="county"  name="county" style="width:312px;">
        <option>请选择注册专家地区</option>
   </select></p>
   
       <!--  
       <p style=" border: 1px solid #dbdbdb;font-size: 20px;padding-left: 10px;padding-top:3px;vertical-align: middle;width:300px;margin-left:45px;font-family: inherit;">上传头像:
           <input type="file" id="photo" name="photo" style="padding-top:3px;">
           </p>
       
        <p style="margin-left:44px;margin-top:20px;" class="txtL txt"><input style="width: 100px;" type="text" id="veryCode" name="veryCode" onfocus ="FocusItem(this)" onBlur="CheckItem(this)" value="" placeholder="验证码"><img
                src="getcode"  alt="如看不清楚，请换一张" onclick="change(this)"><span align="center"></span></p>
          -->
          <p id ="errorRegisterExpert">${errorRegisterExpert }</p>
       	<input type="submit" name="" value="注册" style="margin-top:14px;margin-right:50px; margin-left:65px; width: 280px;background: #C10000;font-size: 20px;border: none; color: #fff;">
        </form>
</div>
</body>

</html>