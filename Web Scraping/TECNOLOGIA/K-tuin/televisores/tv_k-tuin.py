import bs4 as bs
import requests
import csv
from common_functions import *

# urllib

urls = ["https://www.k-tuin.com/comprar-un-apple-tv/apple-tv-4k","https://www.k-tuin.com/apple-tv-hd-32gb"]

i = 0
with open(saveCSVpath("k-tuin_tv.csv"), 'w') as file:
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
            writer.writerow(['tv', 'K-tuin', name, price])
