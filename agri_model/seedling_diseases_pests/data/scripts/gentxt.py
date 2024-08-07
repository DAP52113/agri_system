import os
import os.path as osp
import numpy as np

image_names = os.listdir(r"F:\new_project\tt100k\data\test")
image_names = [image_name.split(".")[0] for image_name in image_names]
np.savetxt('test.txt', np.array(image_names), fmt="%s", delimiter="\n")