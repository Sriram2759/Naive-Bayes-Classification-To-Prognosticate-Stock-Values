import os
import time
import sys
from selenium.common.exceptions import NoSuchElementException
from selenium import webdriver
from selenium.webdriver.common.desired_capabilities import DesiredCapabilities
from selenium.webdriver.common.keys import Keys
driver = webdriver.Chrome(executable_path=r"C:\Chromedriver\chromedriver.exe")
time.sleep(5)
driver.get("http://www.seekingalpha.com/")
time.sleep(20)
sym=sys.argv[1]
input_field = driver.find_element_by_id("hd-auto")
input_field.clear()
input_field.send_keys(sys.argv[1])
btn_field = driver.find_element_by_class_name("btn")
btn_field.click()
time.sleep(10)
tab_element = driver.find_element_by_id("overview")
tab_element.click()
time.sleep(20)
historical_quotes_tab = driver.find_element_by_id("sub-menu-historical-quotes")
time.sleep(5)
historical_quotes_tab.click()
time.sleep(5)
date_range = driver.find_element_by_id("historical-quotes-date-range")
date_range.clear()
date_range.send_keys(sys.argv[2])
date_range_btn = driver.find_element_by_id("historical-quotes-apply")
date_range_btn.click()
time.sleep(10)
export_btn = driver.find_element_by_id("historical-quotes-export")
export_btn.click()
time.sleep(5)
driver.quit()
sys.exit()
 
