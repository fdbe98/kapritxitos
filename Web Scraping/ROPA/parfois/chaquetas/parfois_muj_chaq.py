import bs4 as bs
from selenium import webdriver
import time
import csv
from common_functions import *

url = "https://www.parfois.com/es/es/ropa/abrigos-y-chaquetas/"

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
products = soup.findAll("div",class_="product-tile")
with open(saveCSVpath("parfois_muj_chaq.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('a')["title"]
        price = p.find("span",class_="product-sales-price").text
        print(i, price, name)
        writer.writerow([i, 'mujer', 'chaquetas', 'Parfois', name, finalPrice(price)])
driver.close()