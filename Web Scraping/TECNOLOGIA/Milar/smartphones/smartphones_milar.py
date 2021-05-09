import bs4 as bs
import requests
import csv
from common_functions import *

url_base = "https://www.milar.es"
url = url_base + "/smartphone-telefonos.html"
i = 0
with open(saveCSVpath("milar_smartphones.csv"), 'w') as file:
    writer = csv.writer(file)
    while True:
        page = requests.get(url)
        soup = bs.BeautifulSoup(page.text, 'html5lib')
        products = soup.findAll('section', class_="producto")

        for p in products:
            i += 1
            name = p.find('a')["onclick"].split("'")[1]
            if (p.find("span", class_="precioProducto") != None):
                price = p.find("span", class_="precioProducto").text.strip().split(" ")[0]
                print(i, price, name)
                writer.writerow(['smartphones', 'Milar', name, finalPrice(price)])

        next_page = soup.find("div", class_="holder").findAll('a')[3]
        if next_page:
            url = url_base + next_page["href"]
            print(url)
        else:
            break