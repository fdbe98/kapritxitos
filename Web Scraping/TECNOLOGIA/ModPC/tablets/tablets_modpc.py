import bs4 as bs
import requests
import csv
from common_functions import *

# urllib

url = "https://www.modpc.com/categorias/QT/tablets-android"
i = 0

page = requests.get(url)
soup = bs.BeautifulSoup(page.text, 'html5lib')
products = soup.findAll('div', class_="panel-body thumbnail-articulo")

with open(saveCSVpath("modpc_tablets.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('a').find('h2', class_="titulo-small").text
        price = p.find("div", class_="precio-actual").text
        print(i, finalPrice(price), name)
        writer.writerow(['smartphones', 'Modpc', name, finalPrice(price)])
