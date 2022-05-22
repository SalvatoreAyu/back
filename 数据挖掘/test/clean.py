import numpy as np
import pandas as pd
import seaborn as sns
import matplotlib.pyplot as plt
import os
from sklearn.model_selection import train_test_split
import statsmodels as sm
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

# 数据整理到数组之中
data = [data_2015, data_2016, data_2017, data_2018, data_2019]
data[0] = data[0].drop(['Standard Error', 'Dystopia Residual'], axis=1)
data[1] = data[1].drop(['Lower Confidence Interval',
                        'Upper Confidence Interval', 'Dystopia Residual'], axis=1)
data[2] = data[2].drop(
    ['Whisker.high', 'Whisker.low', 'Dystopia.Residual'], axis=1)


# 重命名函数
data[0].rename(columns={'Economy (GDP per Capita)': 'Economy', 'Health (Life Expectancy)': 'Life Expectancy',
                        'Trust (Government Corruption)': 'Government Trust', 'Family': 'Social Support'}, inplace=True)

data[1].rename(columns={'Economy (GDP per Capita)': 'Economy', 'Health (Life Expectancy)': 'Life Expectancy',
                        'Trust (Government Corruption)': 'Government Trust', 'Family': 'Social Support'}, inplace=True)

data[2].rename(columns={'Happiness.Rank': 'Happiness Rank', 'Happiness.Score': 'Happiness Score',
                        'Economy..GDP.per.Capita.': 'Economy', 'Health..Life.Expectancy.': 'Life Expectancy',
                        'Trust..Government.Corruption.': 'Government Trust', 'Family': 'Social Support'}, inplace=True)

data[3].rename(columns={'Overall rank': 'Happiness Rank', 'Country or region': 'Country', 'Score': 'Happiness Score',
                        'GDP per capita': 'Economy', 'Social support': 'Social Support',
                        'Healthy life expectancy': 'Life Expectancy', 'Freedom to make life choices': 'Freedom',
                        'Perceptions of corruption': 'Government Trust'}, inplace=True)

data[4].rename(columns={'Overall rank': 'Happiness Rank', 'Country or region': 'Country', 'Score': 'Happiness Score',
                        'GDP per capita': 'Economy', 'Social support': 'Social Support',
                        'Healthy life expectancy': 'Life Expectancy', 'Freedom to make life choices': 'Freedom',
                        'Perceptions of corruption': 'Government Trust'}, inplace=True)

plt.figure(figsize=(15, 8))
sns.boxplot(x="Region", y="Happiness Score", data=data[1])
plt.xticks(rotation=30)
plt.show()
