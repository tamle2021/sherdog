## Scraper for sherdog website
### Installation instructions
#### 1) Create virtual environment.  Install virtualenv with pip install virtualenv.  Then install into directory.
    ***
    virtualenv --python pythonVersion folderName
    ***
#### 2) Activate virtual environment with source activate in bin directory (linux machine).
#### 3) Install required packages or other missing.
    ***
    pip install scrapy-splash
    pip install scrapy
    ***
#### 4) Install splash docker image: https://hub.docker.com/r/scrapinghub/splash/#!
#### 5) Run splash with sudo docker run -it -p 8050:8050 --rm scrapinghub/splash
#### 6) In command line, run 
    ***
    crawl sd_event_fight_card
    crawl fighter
    crawl fight_history
    ***
    
