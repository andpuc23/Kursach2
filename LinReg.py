import sys
import pandas as pd
import numpy as np
from sklearn.linear_model import LinearRegression
from sklearn.metrics import mean_squared_error

number = int(sys.argv[1])

y = []
def f(X):
    return X * model.coef_[0] + model.intercept_

for i in range(0, number):
    data = pd.read_csv('data/positions' + str(i) + '.csv', header=None)
    model = LinearRegression().fit(np.array(data[0]).reshape(-1, 1), data[1])
    if (i % 5 == 0):
        print(f'{i}: y = x * {model.coef_[0]} + {model.intercept_}')

    y.append(mean_squared_error(data[1], f(data[0])))

# data_m = pd.read_csv('data/mechs.csv', header=None)

# model_m = LinearRegression().fit(data_m, y)

mses = pd.DataFrame(y)
mses.to_csv(path_or_buf='data/mses.csv', header=False, index=False)

print('written mses')
# print(f'y = a * {model_m.coef_[0]} + b * {model_m.coef_[1]} + c * {model_m.coef_[2]} + d * {model_m.coef_[3]} + {model_m.intercept_}')

# print(model_m.coef_.size)