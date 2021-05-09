import bs4 as bs
import requests
import csv
from common_functions import *

url = "https://modaphuket.com/183-camisas-camisetas-tops?page=1"

i = 0

page = requests.get(url)
soup = bs.BeautifulSoup(page.text, 'html5lib')
products = soup.findAll("div", class_="product-description")

with open(saveCSVpath("phuket_muj_camisetas.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('a').text
        price = p.find("span", class_="price").text
        print(i, price, name)
        writer.writerow([i, 'mujer', 'camisetas', 'Phuket', name, finalPrice(price)])

