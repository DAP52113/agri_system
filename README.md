# 项目简介
## 1.项目技术栈

|    服务端     | Java、SpringBoot、LayUI、Bootstrap、Redis、MySQL、WebSocket、MyBatis |
| :-----------: | :----------------------------------------------------------: |
| **Android端** |              **EaseUI、Fragment、OKHttp、JSON**              |
|  **模型端**   |             **CNN、YOLO、Python、PyTorch、CUDA**             |

## 2.项目概述

农业专家系统的设计分为模型端，Android客户端和服务端设计工作。对于模型端，为了解决基线网络模型特征融合部分在跨尺度提取特征中存在信息丢失问题。首先在基线网络YOLOv7模型特征融合部分，构建信息聚合分发(Gather-and-Distribute)模块，利用构建模块全局融合不同层次的特征并将融合后的信息基于信息分发模块注入到各个层级中，在没有显著增加延迟的前提下实现更高效的信息交互和融合，尽可能保证提取的特征信息不丢失。本次设计需要完成Android端部署检测工作，因此对于计算机视觉所训练得到的最好权重，即best.pt来讲，需要对其进行转化为移动端可以部署的格式来实现部署操作。其具体的部署流程为：首先基于模型的train文件进行GPU环境训练，得到的最佳的best.pt权重文件，经过export文件的执行操作操作，实现对权重文件的导出。

Android客户端中App页面以原生Android渲染为主，通过Activity进行相关请求的响应处理。Android客户端采用OKHttp协议与服务端建立通信，通过JSON数据格式完成客户端与服务端之间的数据传输工作。服务端采用B/S架构设计，前端以Layui框架为主进行前端网页的编写工作，在数据统计方面以eCharts图表API接口调用进行实现。后端以Spring框架作为前端请求处理技术栈，以MyBatis框架完成与MySQL数据库的数据交互工作，通过WebSocket实现网络即时通讯功能，为保证专家系统在Linux服务器上线后针对QPS访问量的性能提高，采用Redis进行数据缓存存储的工作，明显提高系统性能。

### 3.相关技术实现图



<img src="picture\model_structure.png" alt="model_structure" style="zoom:50%;" />

<img src="picture\yolo.png" alt="yolo" style="zoom: 50%;" />

<img src="picture\detect.png" alt="detect" style="zoom:50%;" />

### 4.相关功能

<img src="picture\function.png" alt="function" style="zoom:50%;" />

## 5.相关时序图

<img src="picture\图片3.png" alt="图片3" style="zoom:50%;" />

<img src="picture\图片1.png" alt="图片1" style="zoom:50%;" />

<img src="picture\图片2.png" alt="图片2" style="zoom:50%;" />

## 6.效果图

#### Android端

| <img src="picture\图片4.png" alt="图片4" style="zoom: 33%;" /> | <img src="picture\图片5.png" alt="图片5" style="zoom: 33%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="picture\图片7.png" alt="图片7" style="zoom:33%;" /> | <img src="picture\图片9.png" alt="图片9" style="zoom:33%;" /> |
| <img src="picture\图片13.png" alt="图片13" style="zoom:33%;" /> | <img src="picture\图片6.png" alt="图片6" style="zoom:33%;" /> |

#### 服务端

| <img src="picture\图片8.png" alt="图片8" style="zoom:33%;" /> | <img src="picture\图片10.png" alt="图片10" style="zoom:33%;" /> |
| :----------------------------------------------------------: | :----------------------------------------------------------: |
| <img src="picture\图片11.png" alt="图片11" style="zoom:33%;" /> | <img src="picture\图片12.png" alt="图片12" style="zoom:33%;" /> |
| <img src="picture\图片15.png" alt="图片15" style="zoom:33%;" /> | <img src="picture\图片16.png" alt="图片16" style="zoom:33%;" /> |
| <img src="picture\图片17.png" alt="图片17" style="zoom:33%;" /> | <img src="picture\图片14.png" alt="图片14" style="zoom:33%;" /> |

