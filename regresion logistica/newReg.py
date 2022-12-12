from sklearn.model_selection import train_test_split
import numpy as np
import pandas as pd

dataset = pd.read_csv("diabetes.csv")
dataset.head()