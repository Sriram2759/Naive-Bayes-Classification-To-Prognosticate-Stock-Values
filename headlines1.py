import os
import time
import sys
from selenium.common.exceptions import NoSuchElementException
from datetime import date, datetime, timedelta
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.common.keys import Keys
sym=sys.argv[1]
source="marketwatch"
driver = webdriver.Chrome(executable_path=r"C:\Chromedriver\chromedriver.exe")
driver.get("http://www.marketwatch.com/")
input_field = driver.find_element_by_class_name("icon--search")
input_field.click()
input_text = driver.find_element_by_class_name("search__input")
input_text.send_keys(sym)
input_text.send_keys(Keys.RETURN)
count=1
newswrapper = driver.find_element_by_class_name("headlinewrapper")
while count <= 100:
	driver.execute_script('arguments[0].scrollTop = arguments[0].scrollHeight',newswrapper)
	time.sleep(1)
	count = count + 1
driver.quit()
sys.exit()


