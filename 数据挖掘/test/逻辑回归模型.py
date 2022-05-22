# -*- coding: utf-8 -*-
"""
Created on Tue Oct  6 14:28:28 2020

@author: 15702645404
"""
import pandas  as pd
from sklearn.linear_model import LogisticRegression as LR 
from sklearn.linear_model import RandomizedLogisticRegression as RLR 
import sklearn.metrics as ms
#数据读取
data1 = pd.read_csv("TrainingSet.csv")
data2 = pd.read_csv("TestSet.csv")
test_data = data2.drop(columns =['QuantitySold','EndDay','SellerName',])
train_data = data1.drop(columns = ['QuantitySold','EndDay','SellerName'])
train_label = data1['QuantitySold']
test_label = data2['QuantitySold']
rlr = RLR() 
rlr.fit(train_data[:10000], train_label[:10000]) 
x1= train_data[train_data.columns[rlr.get_support()]] 
y1= train_label 
lr = LR() 
lr.fit(x1,y1) 
print("训练集准确率",lr.score(x1, y1)) 

x2= test_data[test_data.columns[rlr.get_support()]] 
y2= test_label 
y_3= lr.predict(x2) 
print("测试集准确率 ",lr.score(x2,y2)) 
print("F1值 ",ms.f1_score(y2, y_3, average='macro'))  