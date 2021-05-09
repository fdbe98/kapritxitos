from io import open

import bs4 as bs
import requests
import csv
from common_functions import *

# urllib

url = "https://coquette-bilbao.com/categoria/abrigos-y-chaquetas/"

i = 0

page = requests.get(url)

soup = bs.BeautifulSoup(page.text, 'html5lib')

products = soup.findAll("div", class_="box-text box-text-products")

with open(saveCSVpath("coquette_muj_chaq.csv"), 'a') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('p', class_="name product-title").find('a').text
        price = p.find("span", class_="woocommerce-Price-amount amount").text
        print(i, price, name)
        writer.writerow([i, 'mujer', 'chaquetas', 'Coquette', name, finalPrice(price)])

