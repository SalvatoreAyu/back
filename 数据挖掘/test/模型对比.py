# -*- coding: utf-8 -*-
"""
Created on Wed Jan  7 13:27:20 2021

@author: 15702645404
"""

import pandas as pd
from sklearn import svm
from sklearn.linear_model import LogisticRegression
from sklearn.neighbors import KNeighborsClassifier
from sklearn.ensemble import RandomForestRegressor
import sklearn.metrics as ms
data1 = pd.read_csv("TrainingSet.csv")
data2 = pd.read_csv("TestSet.csv")
data3 = data1.copy()#训练集
data4 = data2.copy()#测试集
#数据探索
#print(data3.describe())
#print(data4.describe())


#缺失值填充（预处理）
data3 = data3.fillna(data3.median())
data4 = data4.fillna(data4.median())  

#划分测试集与训练集
x_train = data1.drop(columns = ['QuantitySold','EndDay','SellerName'])
x_test = data2.drop(columns =['QuantitySold','EndDay','SellerName',])
y_train = data1['QuantitySold']
y_test = data2['QuantitySold']
x_train = x_train[:50000] 
y_train = y_train[:50000]
#SVM模型训练
model = svm.SVC(C=1.5)  # (C=1.0, kernel='rbf',  gamma='auto') # 不同核函数的测试
model.fit(x_train, y_train)  # SVM模型训练
print('SVM训练成功')
#逻辑回归训练
logistic_regression_model =LogisticRegression()
logistic_regression_model.fit(x_train, y_train)
print('logistic训练成功')
# KNN训练
knn_model = KNeighborsClassifier(n_neighbors=5)
knn_model.fit(x_train, y_train)
print('KNN训练成功')
# 随机森林训练
rf_model = RandomForestRegressor(n_estimators=100, max_depth=10)
rf_model.fit(x_train, y_train)
print('随机森林训练成功')
# SVM预测结果
y_pred = model.predict(x_test)
# 逻辑回归预测结果
y_pred_logistic =logistic_regression_model.predict(x_test)
# KNN预测结果
y_pred_knn = knn_model.predict(x_test)
# 随机森林预测结果
y_pred_rf = rf_model.predict(x_test)
# 将预测结果的小数换算成整数
y_pred_rf[y_pred_rf >= 0.5] = 1
y_pred_rf[y_pred_rf < 0.5] = 0
y_pred_rf = y_pred_rf.astype(int)
#得到准确率
print("SVM模型的AUC值 ",ms.accuracy_score(y_test, y_pred)) 
print("逻辑回归模型的AUC值 ",ms.accuracy_score(y_test, y_pred_logistic)) 
print("KNN模型的AUC值 ",ms.accuracy_score(y_test, y_pred_knn)) 
print("随机森林的AUC值 ",ms.accuracy_score(y_test, y_pred_rf)) 







