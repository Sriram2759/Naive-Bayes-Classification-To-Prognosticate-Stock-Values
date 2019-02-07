import os
import time
import MySQLdb
import sys
from selenium import webdriver
driver = webdriver.Chrome(executable_path=r"C:\Chromedriver\chromedriver.exe")
driver.get("http://in.reuters.com/news/world")
time.sleep(5)
srchid = driver.find_element_by_class_name("search-icon")
srchid.click()
srchfld = driver.find_element_by_id("searchfield")
sym=sys.argv[1]
srchfld.send_keys(sym)
srchfldbtn = driver.find_element_by_id("search-submit-button")
srchfldbtn.click()
time.sleep(5)
relbtn = driver.find_element_by_class_name("sort-selector")
relbtn.click()
relbtn.send_keys("Date")
count=1
srchmore = driver.find_element_by_class_name("search-result-more-txt")
while count<170:
        count=count+1
        srchmore = driver.find_element_by_class_name("search-result-more-txt")
        srchmore.click()
        time.sleep(2)
db = MySQLdb.connect(host='localhost', user='ramu', passwd='ramu1234', db='temp')
db.set_character_set('utf8')
cur = db.cursor()
cur.execute('SET NAMES utf8;')
cur.execute('SET CHARACTER SET utf8;')
cur.execute('SET character_set_connection=utf8;')
headlinegrp = driver.find_elements_by_class_name("search-result-indiv")
source="Reuters"
print("before")
for item in headlinegrp:
        headcontent = item.find_element_by_class_name("search-result-content")
        headtitle = headcontent.find_element_by_class_name("search-result-title")
        headtitlea = headtitle.find_element_by_tag_name("a")
        headline = headtitlea.text
        headdate = headcontent.find_element_by_class_name("search-result-timestamp")
        s=headdate.text
        headtext=headtitlea.text
        headtext=headtext.replace("'","")
        print(headtitlea.text)
        if(headtitlea.text.find(sym)!=-1):
                print(headtitlea.text)
                print(headdate.text)
                s=headdate.text
                s=s.replace(s[len(s)-12 : ],"")
                s = s.replace(s[0:s.find(" ")],s[0:3])
                s=s.replace(",","")
                s = s[0] + s[1:].lower()
                

print("before")
driver.quit()
sys.exit()

