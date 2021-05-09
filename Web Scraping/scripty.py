import bs4 as bs
import requests
#urllib

url_base = "https://thermometer.co.uk"
url = url_base + "/79-thermocouple-probes"

i = 0

while True:
    #print(url)
    page = requests.get(url)
    
    soup = bs.BeautifulSoup(page.text,'html5lib')
    
    products = soup.findAll("div",class_="product-container") 
    for p in products:
        i+=1
        name = p.find('a',class_="product-name")["title"]
        #price = p.find("span",class_="price").text
        price = p.find("span",itemprop="price").string
        print(i, price, name)

    next_page = soup.find(id="pagination_next").find("a")
    #print(next_page)
    if next_page:
        url = url_base + next_page["href"]
    else: 
        break

    
    