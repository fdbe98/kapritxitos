import bs4 as bs
import requests
from selenium import webdriver
import csv
from common_functions import *

# urllib

urls = ["https://www.fnac.es/Monitores/Todos-los-monitores/n126401?PageIndex=9&sl"]
i = 0

with open(saveCSVpath("fnac_monitores.csv"), 'w') as file:
    writer = csv.writer(file)
    for url in urls:
        driver = webdriver.Chrome('../chromedriver')
        driver.get(url)
        page = driver.page_source
        soup = bs.BeautifulSoup(page, 'html5lib')
        products = soup.findAll('article', class_="Article-itemGroup")

        for p in products:
            i += 1
            try:
                name = p.find('a', class_="Article-desc").text.strip()
            except:
                name = p.find('a', class_="Article-title").text.strip()
            try:
                price = p.find("strong", class_="userPrice").text.strip()[:-1]
            except:
                price = p.find("a", class_="userPrice").text[:-4]
            print(i, price, name)
            writer.writerow(['monitores', 'Fnac', name, price])
        driver.close()
