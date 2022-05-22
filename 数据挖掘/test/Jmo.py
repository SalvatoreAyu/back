import numpy as np 
import pandas as pd 
import seaborn as sns
import matplotlib.pyplot as plt
import os
path1 = os.getcwd()+'\\2015.csv'
f1= open(path1, encoding='utf-8')
data_2015= pd.read_csv(f1)
def rename_columns(df):
    column=[]
    for i in df.columns:
        if '(' in str(i):
            index=i.find("(")
            i=i.replace(i[index-1:],'')
        elif '.' in str(i):
            index=i.find('.')
            i=i.replace(i[index-1:],'')
        i=i.replace(" ","_")
        column.append(i)
    df.columns=column
data=[data_2015]
for i in data:
    rename_columns(i)
print(data_2015.columns)
