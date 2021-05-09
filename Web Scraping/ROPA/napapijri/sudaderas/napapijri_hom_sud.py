import bs4 as bs
import requests
import csv
from common_functions import *

url = "https://www.napapijri.es/shop/es/npj-es/hombre-vestimenta-prendas-de-felpa-y-sudaderas"

i = 0
page = requests.get(url)
soup = bs.BeautifulSoup(page.text,'html5lib')
products = soup.findAll("div",class_="product-block")

with open(saveCSVpath("napapijri_hom_sud.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i+=1
        name = p.find('span',class_="product-block-name-wrapper").text
        price = p.find("span",class_="product-block-price")["data-amount"]
        print(i, price, name)
        writer.writerow([i, 'hombre', 'sudaderas', 'Napapijri', name, finalPrice(price)])