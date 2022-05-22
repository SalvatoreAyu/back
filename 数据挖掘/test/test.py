import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import os
path1 = os.getcwd()+'\\2015.csv'
path2 = os.getcwd()+'\\2016.csv'
path3 = os.getcwd()+'\\2017.csv'
path4 = os.getcwd()+'\\2018.csv'
path5 = os.getcwd()+'\\2019.csv'
f1 = open(path1, encoding='utf-8')
f2 = open(path2, encoding='utf-8')
f3 = open(path3, encoding='utf-8')
f4 = open(path4, encoding='utf-8')
f5 = open(path5, encoding='utf-8')
data_2015 = pd.read_csv(f1)
data_2016 = pd.read_csv(f2)
data_2017 = pd.read_csv(f3)
data_2018 = pd.read_csv(f4)
data_2019 = pd.read_csv(f5)
data = [data_2015, data_2016, data_2017, data_2018, data_2019]
data[0] = data[0].drop(['Standard Error', 'Dystopia Residual'], axis=1)
data[1] = data[1].drop(['Lower Confidence Interval',
                        'Upper Confidence Interval', 'Dystopia Residual'], axis=1)

data[2] = data[2].drop(
    ['Whisker.high', 'Whisker.low', 'Dystopia.Residual'], axis=1)


def rename_columns(df):
    column = []
    for i in df.columns:
        if '(' in str(i):
            index = i.find("(")
            i = i.replace(i[index-1:], '')
        elif '.' in str(i):
            index = i.find('.')
            i = i.replace(i[index-1:], '')
        i = i.replace(" ", "_")
        column.append(i)
    df.columns = column


for i in data:
    rename_columns(i)

for i in data:
    print(data[0].columns)
