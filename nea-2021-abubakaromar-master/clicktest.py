from selenium import webdriver
from selenium.common.exceptions import StaleElementReferenceException,NoSuchElementException
import requests
from bs4 import BeautifulSoup as bs
import time
path=r"C:\Users\abu\Documents\actual coursework\chromedriver.exe"
options = webdriver.ChromeOptions()
prefs = {"profile.default_content_setting_values.notifications" : 2}
options.add_experimental_option("prefs",prefs)
driver = webdriver.Chrome(options=options)
headers={"User-Agent":"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36"}




def button_clicker(class_name):
    try:
        button=driver.find_element_by_class_name(class_name)
    except NoSuchElementException:
        return
    while True:
        try:
            button.click()
            time.sleep(1.5)
        except StaleElementReferenceException:
            break

url=requests.get("https://www.erai-raws.info/anime-list/",headers=headers).text
soup = bs(url, "lxml")
animes = soup.find_all("div", class_="ind-show button button5")
for anime in animes:
    animelink=anime.find("a").get("href")
    animetitle=anime.find("a").span.string
    driver.get("https://www.erai-raws.info/anime-list/" + animelink)
    button_clicker("load_more_0")
    button_clicker("load_more_2")
    button_clicker("load_more_3")
    html=driver.page_source
    soup=bs(html,"lxml")
        
    episode_container=soup.find("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 posmain h-episodes show-episodes")
    if episode_container is not None:
        episodes=episode_container.find_all("article", class_="era_center col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain border_radius_22")
        for episode in episodes:
            episode_number=episode.find("h1",class_="name post-title entry-title titlehh").find("font",class_="aa_ss_ops",style=None).contents[0].strip()
            torrent_container=episode.find_all("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain release-table button last_block")
            for torrent in torrent_container:
                quality=torrent.find("i", class_="sp_p_q").string
                quality=quality[1:len(quality)-1]
                torrent_link=torrent.a['href']
                print(animetitle + ' - ' + episode_number + ' - ' + quality + ' - ' + torrent_link)
    else:
        pass
    
    batch_container=soup.find("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 posmain h-batch show-batch")
    if batch_container is not None:
        
        batches=batch_container.find_all("article", class_="era_center col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain border_radius_22")
        for batch in batches:
            batch_range=batch.find("h1",class_="name post-title entry-title titlehh").find("font",class_="aa_ss_ops",style=None).contents[0].strip()                                                                                                                  
            torrent_container=batch.find_all("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain release-table button last_block")
            for torrent in torrent_container:
                quality=torrent.find("i", class_="sp_p_q").string
                quality=quality[1:len(quality)-1]
                torrent_link=torrent.a['href']
                print(animetitle + ' - ' + batch_range + ' - ' + quality + ' - ' + torrent_link)
    else:
        pass
    
    movie_container=soup.find("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 posmain h-movies show-movies")
    if movie_container is not None:
        movies=movie_container.find_all("article", class_="era_center col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain border_radius_22")
        for movie in movies:
            movie_name=movie.find("h1",class_="name post-title entry-title titlehh").find("font",class_="aa_ss_ops",style=None).contents[0].strip()
            if movie_name=='00':
                movie_name=movie.find("h1",class_="name post-title entry-title titlehh").find("font",class_="aa_ss_ops",style="margin: 0px 0px -4px 0px;").contents[0].strip()
            torrent_container=movie.find_all("div", class_="col-12 col-sm-12 col-md-12 col-lg-12 col-xl-12 nonmain release-table button last_block")
            for torrent in torrent_container:
                quality=torrent.find("i", class_="sp_p_q").string
                quality=quality[1:len(quality)-1]
                torrent_link=torrent.a['href']
                print(animetitle + ' - ' + movie_name + ' - ' + quality + ' - ' + torrent_link)
    else:
        pass




"""def button_clicker(link,class_name):
    driver.get(link)
    button=driver.find_element_by_class_name(class_name)
    while True:
        try:
            button.click()
            time.sleep(1)
        except StaleElementReferenceException:
            break"""
