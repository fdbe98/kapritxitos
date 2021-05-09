import bs4 as bs
from selenium import webdriver
import time
import csv
from common_functions import *

url = "https://www.browniespain.com/es/sudaderas/"

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
products = soup.findAll("div", class_="product-description")
with open(saveCSVpath("brownie_muj_sud.csv"), 'w') as file:
    writer = csv.writer(file)
    for p in products:
        i += 1
        name = p.find('h3', class_="h3 product-title").find('a').text
        price = p.find("span", class_="price").text.strip()
        print(i, price, name)
        writer.writerow([i, 'mujer', 'sudaderas', 'Brownie', name, finalPrice(price)])
driver.close()