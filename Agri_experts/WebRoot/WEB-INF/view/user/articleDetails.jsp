<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta name="renderer" content="webkit">
    <meta name="viewport" content="width=device-width" />
    <title>文章阅读</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/layui/css/layui.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/master.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/gloable.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/nprogress.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/user/css/blog.css" />
</head>
<body>
    <div class="header">
    </div>
    <header class="gird-header">
        <div class="header-fixed">
            <div class="header-inner">
                <a href="javascript:void(0)" class="header-logo" id="logo">Agricultural Expert Consultation System</a>
                <nav class="nav" id="nav">
                    <ul>
                        <li><a href="${ctx}/userView/Userindex.action">首页</a></li>
                        <li><a href="${ctx}/userView/UserArticle.action">资讯</a></li>
                        <li><a href="${ctx}/userView/UserOnlineMessage.action">在线咨询</a></li>
                        <li><a href="${ctx}/userView/userDiagnose.action">远程问诊</a></li>
                        <li><a href="${ctx}/userView/UserPersonCen.action">个人中心</a></li>
                    </ul>
                </nav>
                  <a href="${pageContext.request.contextPath}/index.jsp" class="blog-user">
                    <i class="fa fa-share">注销</i>
                </a>
                <a class="phone-menu">
                    <i></i>
                    <i></i>
                    <i></i>
                </a>
            </div>
        </div>
    </header>
    <div class="doc-container" id="doc-container">
        <div class="container-fixed">
            <div class="col-content" style="width:100%">
                <div class="inner">
                    <article class="article-list">
                        <input type="hidden" value="${information.id}" id="blogtypeid" />
                        <section class="article-item">
                            <aside class="title" style="line-height:1.5;">
                                <h4>${information.title}</h4>
                                <p class="fc-grey fs-14">
                                    <small>
                                        作者：<a  target="_blank" class="fc-link">${information.eno}</a>
                                    </small>
                                    <small class="ml10">归属领域：<i class="readcount">${information.area}</i></small>
                                    <small class="ml10">更新于 <label>${information.date}</label> </small>
                                </p>
                            </aside>
                            <div class="time mt10" style="padding-bottom:0;">
                                <span class="day">${fn:substring(information.date, 8, 10)}</span>
                                <span class="month fs-18">${fn:substring(information.date, 5, 7)}<small class="fs-14">月</small></span>
                                <span class="year fs-18">${fn:substring(information.date, 0, 4)}</span>
                            </div>
                            <div class="content artiledetail" style="border-bottom: 1px solid #e1e2e0; padding-bottom: 20px;">
                                ${information.content}
                                <div class="copyright mt20">
                                    <p class="f-toe fc-black">
                                        	咨讯省份：${information.province}
                                    </p>
                                     <p class="f-toe fc-black">
                                        	咨讯城市：${information.city}
                                    </p>
                                    <p class="f-toe fc-black">
                                        	咨讯地区：${information.district}
                                    </p>
                                    <p class="f-toe">
                                        本文标题：
                                        <a href="javascript:void(0)" class="r-title">${information.title}</a>
                                    </p>
                          
                                </div>
                                <div id="aplayer" style="margin:5px 0"></div>
                                <h6>文章图片</h6>
                                <ol class="b-relation"></ol>
                            </div>
                            <div class="bdsharebuttonbox share" data-tag="share_1">
                            <img alt="资讯图片" src="${ctx}/file/downloadShowFile.action?path=${information.wentiphoto}"/>  
                            <!-- 
                                <ul>
                                    <li class="f-praise"><span><a class="s-praise"></a></span></li>
                                    <li class="f-weinxi"><a class="s-weinxi" data-cmd="weixin"></a></li>
                                    <li class="f-sina"><a class="s-sina" data-cmd="tsina"></a></li>
                                    <li class="f-qq" href="#"><i class="fa fa-qq"></i></li>
                                    <li class="f-qzone"><a class="s-qzone" data-cmd="qzone"></a></li>
                                </ul>
                              -->
                            </div>
                            <div class="f-cb"></div>
                            <div class="mt20 f-fwn fs-24 fc-grey comment" style="border-top: 1px solid #e1e2e0; padding-top: 20px;">
                            </div>
                            
                            <ul class="blog-comment" id="blog-comment"></ul>
                        </section>
                    </article>
                </div>
            </div>
        </div>
    </div>
    <footer class="grid-footer">
        <div class="footer-fixed">
            <div class="copyright">
                <div class="info">
                    <div class="contact">
                         <a href="https://wpa.qq.com/msgrd?v=3&uin=930054439&site=qq&menu=yes" class="qq" target="_blank" title="930054439"><i class="fa fa-qq"></i></a>
                    </div>
                    <p class="mt05">
                        Copyright &copy; 农业专家咨询系统版权所有
                    </p>
                </div>
            </div>
        </div>
    </footer>
    <script src="${pageContext.request.contextPath}/resources/user/layui/layui.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/yss/gloable.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/plugins/nprogress.js"></script>
    <script src="${pageContext.request.contextPath}/resources/user/js/pagecomment.js"></script>
    <script>NProgress.start();</script>
    <script> 
        window.onload = function () {
            NProgress.done();
        };
    </script>
</body>
</html>
