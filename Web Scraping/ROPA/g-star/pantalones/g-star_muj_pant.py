import bs4 as bs
import requests
import csv
from common_functions import *

import pathlib

# urllib

url = "https://www.g-star.com/es_es/shop/mujeres/pantalones"

page = requests.get(url)
soup = bs.BeautifulSoup(page.text, 'html5lib')
showAll = soup.findAll('a', class_="countSwitch")
ulr_extra = showAll[1]["href"]
if ulr_extra != "#":
    url = "https://www.g-star.com" + ulr_extra
    print(url)
    page = requests.get(url)
    soup = bs.BeautifulSoup(page.text, 'html5lib')
i = 0

products = soup.findAll("a", class_="productTile")

with open(saveCSVpath("g-star_muj_pant.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('p', class_="productTile-name").text.strip()
        price = p.find("span", class_="productPrice-value").text.strip()
        print(i, price, name)
        writer.writerow([i, 'mujer','pantalones','G-STAR',name, finalPrice(price)])
