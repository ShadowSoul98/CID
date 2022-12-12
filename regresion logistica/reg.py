from sklearn import datasets #para la importacion de datos
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import roc_auc_score



### TRATAMIENTO DE DATOS
dataset = datasets.load_breast_cancer()

print('Características del dataset:')
print(dataset.DESCR)

X = dataset.data

y = dataset.target

###REGRESION LOGISTICA
X_train, X_test, y_train, y_test = train_test_split(X, y, test_size=0.2)

escalar = StandardScaler()

X_train = escalar.fit_transform(X_train)
X_test = escalar.transform(X_test)

algoritmo = LogisticRegression()
algoritmo.fit(X_train, y_train)

y_pred = algoritmo.predict(X_test)

roc_auc = roc_auc_score(y_test, y_pred)
print('Curva ROC - AUC del modelo:')
print(roc_auc)