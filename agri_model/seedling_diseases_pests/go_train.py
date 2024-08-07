import os
os.system("python train.py --data my_data.yaml --cfg yolov5s.yaml --weights pretrained/yolov5s.pt --epoch 100 --batch-size 16 --device 0")
os.system("python train.py --data my_data.yaml --cfg yolov5m.yaml --weights pretrained/yolov5m.pt --epoch 100 --batch-size 16")
os.system("python train.py --data my_data.yaml --cfg yolov5l.yaml --weights pretrained/yolov5l.pt --epoch 100 --batch-size 16")
os.system("python train.py --data my_data.yaml --cfg yolov5n.yaml --weights pretrained/yolov5n.pt --epoch 100 --batch-size 16")

