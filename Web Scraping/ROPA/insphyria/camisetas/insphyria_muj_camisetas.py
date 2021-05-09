import bs4 as bs
import requests
import csv
from common_functions import *

# urllib

url = "https://www.insphyria.es/categoria-producto/partes-de-arriba/"

i = 0

page = requests.get(url)

soup = bs.BeautifulSoup(page.text, 'html5lib')

products = soup.findAll('a', class_="woocommerce-LoopProduct-link woocommerce-loop-product__link")
with open(saveCSVpath("insphyria_muj_camisetas.csv"), 'a') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('h2', class_="woocommerce-loop-product__title").text
        price = p.find('bdi').text
        print(i, price, name)
        writer.writerow([i, 'mujer', 'camisetas', 'Insphyria', name, finalPrice(price)])
