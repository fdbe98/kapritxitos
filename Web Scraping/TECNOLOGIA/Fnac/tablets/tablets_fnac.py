import bs4 as bs
import requests
from selenium import webdriver
import csv
from common_functions import *

# urllib

urls = ["https://www.fnac.es/Tablet-Lenovo/n126498#int=S:Por%20categor%C3%ADa|PAG_f150b423-dbc8-4b1e-9764-320f7813c649|126498|NonApplicable|BL5|NonApplicable",
        "https://www.fnac.es/Tablets-Samsung/n126493#int=S:Por%20categor%C3%ADa|PAG_f150b423-dbc8-4b1e-9764-320f7813c649|126493|NonApplicable|BL2|NonApplicable",
        "https://www.fnac.es/Tablet-Sunstech/n126497#int=S:Por%20categor%C3%ADa|PAG_f150b423-dbc8-4b1e-9764-320f7813c649|126497|NonApplicable|BL4|NonApplicable",
        "https://www.fnac.es/Apple-iPad/iPad-Pro/n124696#int=S:Por%20categor%C3%ADa|Apple%20iPad|124696|NonApplicable|BL1|NonApplicable",
        "https://www.fnac.es/Apple-iPad/iPad-Air/n124793#int=S:Por%20categor%C3%ADa|Apple%20iPad|124793|NonApplicable|BL2|NonApplicable",
        "https://www.fnac.es/Apple-iPad/iPad/n124699#int=S:Por%20categor%C3%ADa|Apple%20iPad|124699|NonApplicable|BL3|NonApplicable",
        "https://www.fnac.es/Apple-iPad/iPad-mini/n124730#int=S:Por%20categor%C3%ADa|Apple%20iPad|124730|NonApplicable|BL4|NonApplicable"
]
i = 0

with open(saveCSVpath("fnac_tablets.csv"), 'w') as file:
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
                writer.writerow(['tablets', 'Fnac', name, price])
        driver.close()
