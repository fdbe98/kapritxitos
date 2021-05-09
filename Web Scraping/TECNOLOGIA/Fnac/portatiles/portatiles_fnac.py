import bs4 as bs
from selenium import webdriver
import csv
from TECNOLOGIA.Fnac.monitores.common_functions import *

# urllib

urls = [
    "https://www.fnac.es/Portatiles-Apple/Mas-vendidos/n126453#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126453|NonApplicable|BL1|NonApplicable",
    "https://www.fnac.es/Portatiles-MSI/n126468#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126468|NonApplicable|BL2|NonApplicable",
    "https://www.fnac.es/Portatiles-Lenovo/Portatiles-Lenovo-Legion/n126456#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Lenovo|126456|NonApplicable|BL1|NonApplicable",
    "https://www.fnac.es/Portatiles-Lenovo/Portatiles-lenovo-i7/n1https://www.fnac.es/Portatiles-Lenovo/Portatiles-lenovo-i5/n126458#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Lenovo|126458|NonApplicable|BL3|NonApplicable26457#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Lenovo|126457|NonApplicable|BL2|NonApplicable",
    "https://www.fnac.es/Portatiles-Lenovo/Portatiles-lenovo-i5/n126458#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Lenovo|126458|NonApplicable|BL3|NonApplicable",
    "https://www.fnac.es/Portatiles-Lenovo/Portatiles-lenovo-i3/n126459#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Lenovo|126459|NonApplicable|BL4|NonApplicable",
    "https://www.fnac.es/Portatiles-HP/Portatiles-HP-OMEN/n126464#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20HP|126464|NonApplicable|BL1|NonApplicable",
    "https://www.fnac.es/Portatiles-HP/Portatiles-hp-i7/n126465#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20HP|126465|NonApplicable|BL2|NonApplicable",
    "https://www.fnac.es/Ordenadores-Intel/n126523#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126523|NonApplicable|BL3|NonApplicable",
    "https://www.fnac.es/Ordenadores-AMD/n126522#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126522|NonApplicable|BL4|NonApplicable",
    "https://www.fnac.es/Portatiles-Acer/n126469#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126469|NonApplicable|BL8|NonApplicable",
    "https://www.fnac.es/Portatiles-LG/n126470#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|126470|NonApplicable|BL9|NonApplicable",
    "https://www.fnac.es/Portatiles-Asus/Portatiles-asus-i7/n126461#int=S:Por%20categor%C3%ADa|Port%C3%A1tiles%20Asus|126461|NonApplicable|BL2|NonApplicable",
    "https://www.fnac.es/Portatiles-Huawei-Matebook/n127583#int=S:Por%20categor%C3%ADa|PAG_10301d03-1d54-45e6-b7e6-f043c797f022|127583|NonApplicable|BL11|NonApplicable"
]
i = 0

with open(saveCSVpath("fnac_portatiles.csv"), 'w') as file:
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
                writer.writerow(['monitores', 'Fnac', name, price])
        driver.close()
