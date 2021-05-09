import bs4 as bs
from selenium import webdriver
import time
import csv
from common_functions import *

url = "https://www.guess.eu/es-es/guess/hombres/prendas-de-vestir/t-shirts"
#SELENIUM --->
driver = webdriver.Chrome('../../chromedriver')
driver.get(url)
lenOfPage = driver.execute_script(
    "window.scrollTo(0, document.body.scrollHeight);var lenOfPage=document.body.scrollHeight;return lenOfPage;")
match = False
while (match == False):
    lastCount = lenOfPage
    time.sleep(1)
    lenOfPage = driver.execute_script(
        "window.scrollTo(0, document.body.scrollHeight);var lenOfPage=document.body.scrollHeight;return lenOfPage;")
    if lastCount == lenOfPage:
        match = True

page = driver.page_source
soup = bs.BeautifulSoup(page, 'html5lib')
i = 0
products = soup.findAll("article", class_="product__tile js-tile")
with open(saveCSVpath("guess_hom_camisetas.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('a')["title"]
        if None != p.find("span", class_="value price__value"):
            price = p.find("span", class_="value price__value").text
        print(i, price, name)
        writer.writerow([i, 'hombre', 'camisetas', 'Guess', name, finalPrice(price)])
driver.close()
