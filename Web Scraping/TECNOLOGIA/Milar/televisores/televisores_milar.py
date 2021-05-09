import bs4 as bs
import requests
import csv
from common_functions import *

url_base = "https://www.milar.es"
url_path = "/televisores.html"
url = url_base + url_path
num = 1
url_pag = "/pagina-"
i = 0
with open(saveCSVpath("milar_tv.csv"), 'w') as file:
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
                if (price != ""):
                    print(i, price, name)
                    writer.writerow(['tv', 'Milar', name, price])

        next_page = str(num)
        if (num < 4):
            url = url_base + url_path + url_pag + next_page
            num = num + 1
            print(url)
        else:
            break
