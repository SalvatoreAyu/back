import matplotlib.pyplot as plt
import pandas as pd
import os
path1 = os.getcwd()+'\\TrainingSet.csv'
path2 = os.getcwd() + '\\TestSet.csv'
f1 = open(path1, encoding='utf-8')
f2 = open(path2, encoding='utf-8')
data1 = pd.read_csv(f1)
data2 = pd.read_csv(f2)
data3 = data2.copy()
data4 = data1.copy()
df1 = data3[data3['EndDay'] == 'Monday']
df2 = data3[data3['EndDay'] == 'Tuesday']
df3 = data3[data3['EndDay'] == 'Wednesday']
df4 = data3[data3['EndDay'] == 'Thursday']
df5 = data3[data3['EndDay'] == 'Friday']
df6 = data3[data3['EndDay'] == 'Saturday']
df7 = data3[data3['EndDay'] == 'Sunday']
d1 = df1['QuantitySold'].value_counts()
d2 = df2['QuantitySold'].value_counts()
d3 = df3['QuantitySold'].value_counts()
d4 = df4['QuantitySold'].value_counts()
d5 = df5['QuantitySold'].value_counts()
d6 = df6['QuantitySold'].value_counts()
d7 = df7['QuantitySold'].value_counts()
a = [['Monday', d1[1]], ['Tuesday', d2[1]], ['Wednesday', d3[1]], [
    'Thursday', d4[1]], ['Friday', d5[1]], ['Saturday', d6[1]], ['Sunday', d7[1]]]
f1 = pd.DataFrame(a, columns=['day', 'number'])
print(f1)
plt.figure
plt.xlabel('time')
plt.ylabel('number')
plt.bar(f1['day'], f1['number'])
plt.title("testset sale number")
plt.show()

a = [['Monday', d1[1]/(d1[1]+d1[0])], ['Tuesday', d2[1]/(d2[0]+d2[1])], ['Wednesday', d3[1]/(d3[0]+d3[1])], ['Thursday',
                                                                                                             d4[1]/(d4[0]+d4[1])], ['Friday', d5[1]/(d5[0]+d5[1])], ['Saturday', d6[1]/(d6[0]+d6[1])], ['Sunday', d7[1]/(d7[0]+d7[1])]]
f3 = pd.DataFrame(a, columns=['day', 'number'])
print(f3)
plt.figure
plt.xlabel('time')
plt.ylabel('rate')
plt.bar(f3['day'], f3['number'])
plt.title("testset sale rate")
plt.show()


df_1 = data4[data4['EndDay'] == 'Monday']
df_2 = data4[data4['EndDay'] == 'Tuesday']
df_3 = data4[data4['EndDay'] == 'Wednesday']
df_4 = data4[data4['EndDay'] == 'Thursday']
df_5 = data4[data4['EndDay'] == 'Friday']
df_6 = data4[data4['EndDay'] == 'Saturday']
df_7 = data4[data4['EndDay'] == 'Sunday']
d1 = df_1['QuantitySold'].value_counts()
d2 = df_2['QuantitySold'].value_counts()
d3 = df_3['QuantitySold'].value_counts()
d4 = df_4['QuantitySold'].value_counts()
d5 = df_5['QuantitySold'].value_counts()
d6 = df_6['QuantitySold'].value_counts()
d7 = df_7['QuantitySold'].value_counts()
a = [['Monday', d1[1]], ['Tudsday', d2[1]], ['Wednesday', d3[1]], [
    'Thursday', d4[1]], ['Friday', d5[1]], ['Saturday', d6[1]], ['Sunday', d7[1]]]
f2 = pd.DataFrame(a, columns=['day', 'number'])
print(f2)
plt.figure
plt.xlabel('time')
plt.ylabel('number')
plt.bar(f2['day'], f2['number'])
plt.title("trainingset sale number")
plt.show()

a = [['Monday', d1[1]/(d1[1]+d1[0])], ['Tuesday', d2[1]/(d2[0]+d2[1])], ['Wednesday', d3[1]/(d3[0]+d3[1])], ['Thursday',
                                                                                                             d4[1]/(d4[0]+d4[1])], ['Friday', d5[1]/(d5[0]+d5[1])], ['Saturday', d6[1]/(d6[0]+d6[1])], ['Sunday', d7[1]/(d7[0]+d7[1])]]
f4 = pd.DataFrame(a, columns=['day', 'number'])
print(f4)
plt.figure
plt.xlabel('time')
plt.ylabel('rate')
plt.bar(f4['day'], f4['number'])
plt.title("trainingset sale rate")
plt.show()
