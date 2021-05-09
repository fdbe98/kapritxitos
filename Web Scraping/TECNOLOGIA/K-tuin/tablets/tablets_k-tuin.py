import bs4 as bs
import requests
import csv
from common_functions import *

# urllib

urls = ["https://www.k-tuin.com/comprar-ipad-pro",
        "https://www.k-tuin.com/comprar-un-ipad/ipad-pro",
        "https://www.k-tuin.com/comprar-un-ipad/ipad-10-2",
        "https://www.k-tuin.com/comprar-un-ipad/ipad-air",
        "https://www.k-tuin.com/comprar-un-ipad/ipad-mini"]

i = 0
with open(saveCSVpath("k-tuin_tablets.csv"), 'w') as file:
    writer = csv.writer(file)
    for url in urls:
        page = requests.get(url)
        soup = bs.BeautifulSoup(page.text, 'html5lib')
        products = soup.findAll('div', class_="product-element")

        for p in products:
            i += 1
            name = p.find('h3', class_="product-name").text.strip()
            try:
                price = p.find("span", class_="price")["content"]
            except:
                price = p.find("span", class_="price").text.strip()
            print(i, price, name)
            writer.writerow(['smartphones', 'K-tuin', name, price])
