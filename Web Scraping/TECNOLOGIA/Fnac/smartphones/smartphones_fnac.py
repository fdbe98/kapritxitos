import bs4 as bs
import requests
from selenium import webdriver
import csv
from common_functions import *

# urllib

urls = ["https://www.fnac.es/Apple-iPhone/iPhone-12-Pro-Pro-Max/n127105#int=S:NonApplicable|Apple%20iPhone|127105|NonApplicable|BL1|NonApplicable",
        "https://www.fnac.es/Apple-iPhone/iPhone-12/n127103#int=S:NonApplicable|Apple%20iPhone|127103|NonApplicable|BL2|NonApplicable",
        "https://www.fnac.es/Apple-iPhone/iPhone-12-mini/n127104#int=S:NonApplicable|Apple%20iPhone|127104|NonApplicable|BL3|NonApplicable",
        "https://www.fnac.es/Apple-iPhone/iPhone-11-Pro-Pro-Max/n124064#int=S:NonApplicable|Apple%20iPhone|124064|NonApplicable|BL4|NonApplicable",
        "https://www.fnac.es/Smartphones-Xiaomi/Xiaomi-Mi/n108432",
        "https://www.fnac.es/Smartphones-Xiaomi/Xiaomi-Redmi/n108433",
        "https://www.fnac.es/Smartphones-Realme/n126621#int=S:Por%20categor%C3%ADa|PAG_350bb737-62fb-43cd-a940-fed0fd7713f2|126621|NonApplicable|BL7|NonApplicable",
        "https://www.fnac.es/Smartphones-Motorola/n126620#int=S:Por%20categor%C3%ADa|PAG_350bb737-62fb-43cd-a940-fed0fd7713f2|126620|NonApplicable|BL6|NonApplicable",
        "https://www.fnac.es/Smartphones-Huawei/Huawei-P40/n126563#int=S:NonApplicable|Smartphones%20Huawei|126563|NonApplicable|BL1|NonApplicable"
        ]


i = 0

with open(saveCSVpath("fnac_smartphones.csv"), 'w') as file:
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
                price = p.find("a", class_="userPrice").text[:-1]
                print(i, price, name)
                writer.writerow(['smartphones', 'Fnac', name, price])
        driver.close()
