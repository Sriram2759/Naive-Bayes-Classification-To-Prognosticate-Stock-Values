import os
import time
import MySQLdb
import sys
from selenium.common.exceptions import NoSuchElementException
from selenium import webdriver
from selenium.webdriver.common.keys import Keys
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
driver = webdriver.Chrome(executable_path=r"C:\Chromedriver\chromedriver.exe")
time.sleep(3)
driver.get("http://www.marketwatch.com/")
time.sleep(20)
input_field = driver.find_element_by_class_name("icon--search")
input_field.click()
input_text = driver.find_element_by_class_name("search__input")
input_text.send_keys(sys.argv[1])
input_text.send_keys(Keys.RETURN)
time.sleep(15)
newswrapper = driver.find_element_by_css_selector("div.collection__list.j-scrollElement")
newslist = newswrapper.find_elements_by_css_selector("div.element.element--article.no-image.j-scrollByElement")
scrollnews = driver.find_element_by_css_selector("mw-scrollable-news.collection.j-tabPane.is-active.j-scrollViewport")
flag=1
while flag<=70 :
        driver.execute_script('arguments[0].scrollTop = arguments[0].scrollHeight',scrollnews)
        time.sleep(1)
        flag = flag + 1
       
driver.quit()
sys.exit()


