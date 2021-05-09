import re
import pathlib

# DO NOT MOVE FILE --> FUNCTION SUCH AS "saveCSVpath" WILL BE AFFECTED !

#price = "125,966€"           #  125.966€ --> 125,96 €
def finalPrice(price):
    result = re.findall('\d{1,4}(?:[.,])*\d{1,2}',price)
    final_price = result[0].replace(',','.')
    return final_price


def saveCSVpath(nomFile):
    return str(pathlib.Path(__file__).parent)+'/FicherosCSV/'+nomFile