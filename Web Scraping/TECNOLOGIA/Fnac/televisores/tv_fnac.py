import bs4 as bs
import requests
from selenium import webdriver
import csv
from common_functions import *

# urllib

urls = ["https://www.fnac.es/Televisores/Todas-las-TV/n127682#bl=MMTv"]

i = 0

with open(saveCSVpath("fnac_tv.csv"), 'w') as file:
    writer = csv.writer(file)
    for url in urls:
        driver = webdriver.Chrome('../chromedriver')
        driver.get(url)
        page = driver.page_source
        soup = bs.BeautifulSoup(page, 'html5lib')
        products = soup.findAll('article', class_="Article-itemGroup")

        for p in products:
            i += 1
            name = p.find('a', class_="Article-title").text.strip()
            if (p.find("a", class_="userPrice") != None):
                price = p.find("a", class_="userPrice").text[:-4]
                print(i, price, name)
                writer.writerow(['tv', 'Fnac', name, price])
        driver.close()
