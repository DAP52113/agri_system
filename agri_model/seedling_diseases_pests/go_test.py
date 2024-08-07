
import cv2
import numpy as np

image = cv2.imread(r"D:\PythonProjects\yolov5-wheat\wheat_yolo_format\images\test\00008.jpg")  # uint8 (372,419,3) [[[252 246 233][252 246 233].........]]
cv2.imshow("input", image)
# 原图像素值
print(image)
result = np.zeros(image.shape, dtype=np.float32)
# cv2.normalize(image, result, alpha=0, beta=1, norm_type=cv2.NORM_MINMAX, dtype=cv2.CV_32F)
# result = image / 255.0  # 与cv2.normalize效果一样
result = image/127.5 - 1 #也是对图像进行归一化，范围为[-1, 1]
# 归一化像素值
print(result)  # float64 (372,419,3) [[[0.98823529 0.96470588 0.91372549] [0.98823529 0.96470588 0.91372549].....]]

img = np.uint8(result * 255.0)  #
# print((image == img).all())  # true

cv2.imshow("norm", result)  # result 和 img的显示效果一样，灰度图像除以255就是进行以归一化处理，你可视化的时候会映射到图空间的。
cv2.waitKey(0)
cv2.destroyAllWindows()
