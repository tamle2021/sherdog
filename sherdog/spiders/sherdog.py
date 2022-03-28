import sys
import os,re
import random
import scrapy
import logging
from scrapy.spiders import CrawlSpider,Rule
from scrapy.linkextractors import LinkExtractor
from scrapy.utils.log import configure_logging
from ..hf_sherdog import checkEmpty,resetFightCard,loadEventItem,checkHeight,setBirthDate,setDate,setEventDetails,setLocation, \
    createUrl,checkFightResult,loadFightCardItem,setFightCardDetails,setFirstRowFightCard,setFightCard,setAge, \
    setHeight,setWeight,setNationality,setLocality,resetFighterStats,loadFighterItem,setAssociation,setFighterName, \
    setFighter2Result,setEvent,setDateFightHistory,setMethod,setTime
from ..settings import USER_AGENT_LIST
from scrapy_splash import SplashRequest,SplashFormRequest

class EventFightCardSpider(scrapy.Spider):
    name = "event_fight_card"
    allowed_domains = ["www.sherdog.com",'sherdog.com']
    # start_urls = ['https://www.sherdog.com/events/recent']
    # https://www.sherdog.com/events/recent/267-page

    custom_settings = {
        "ITEM_PIPELINES": {
            'sherdog.pipelines.EventFightCardPipeline': 198,
        },
        "CLOSESPIDER_ITEMCOUNT": 1905
    }

    # configure_logging(install_root_handler=False)
    # logging.basicConfig(
    #     filename='log.txt',
    #     format='%(levelname)s: %(message)s',
    #     level=logging.INFO,filemode="w+"
    # )

    def __init__(self,*args,**kwargs):
        super(EventFightCardSpider,self).__init__(*args,**kwargs)
        self.eventUrlList = []
        self.date = ""
        self.eventName = ""
        self.eventTitle = ""
        self.eventUrl = ""
        self.location = ""

        # fight history
        self.dateFightCard = ""
        self.eventNameFightCard = ""
        self.locationFightCard = ""
        self.fighter1Name = ""
        self.fighter2Name = ""
        self.fighter1Url = ""
        self.fighter2Url = ""
        self.fighter1Result = ""
        self.fighter2Result = ""
        self.fightMethodResult = ""
        self.fightRound = ""
        self.time = ""

        self.fighterName = ""
        self.birthDate = ""
        self.age = ""
        self.height = ""
        self.weight = ""
        self.association = ""
        self.fighterClass = ""
        self.win = ""
        self.loss = ""
        self.locality = ""
        self.country = ""

        self.url = "https://www.sherdog.com/events/recent"
        self.script = """
                         function main(splash)
                             local cookies = splash:get_cookies()
                             splash:init_cookies(cookies)

                             assert(splash:go{splash.args.url,headers=splash.args.headers,
                                 http_method=splash.args.http_method,body=splash.args.body})
                             assert(splash:wait(0.5))

                             local entries = splash:history()
                             local last_response = entries[#entries].response
                            
                             return {
                                 url = splash:url(),
                                 headers = last_response.headers,
                                 http_status = last_response.status,
                                 cookies = splash:get_cookies(),
                                 html = splash:html()
                                 -- png = splash:png(),
                                 -- har = splash:har()
                             }
                         end
                     """
        self.script2 = """
                          function main(splash,args)
                              local cookies = splash:get_cookies()
                              splash:init_cookies(cookies)
                              assert(splash:go(splash.args.url))
                              assert(splash:wait(1.5))

                              return {
                                  cookies,
                                  html = splash:html(),
                                  -- png = splash:png(),
                                  -- har = splash:har()
                              }
                          end
                      """

    def start_requests(self):
        yield SplashRequest(url=self.url,callback=self.parseFirstEvent,\
            endpoint="execute",args={"lua_source": self.script2},
            headers={"User-Agent": random.choice(USER_AGENT_LIST)})

    def parseFirstEvent(self,response):
        try:
            # splash renders with body tag
            trTags = checkEmpty(response.xpath("//div[@id='recentfights_tab']/div[contains(@class,'new_table')]/table/tbody/tr[contains(@onclick,'document.location')]"))
            # //*[@id="recentfights_tab"]/div[1]/table
            if (trTags != "None"):
                for i in trTags:
                    setDate(self,i)
                    setEventDetails(self,i,response)

                    location = checkEmpty(i.xpath(".//td[4]/span/text()").get())
                    if (location != "None"):
                        setLocation(self,location)
                    else:
                        self.location = "None"

                    loader = loadEventItem(self,response)
                    yield loader.load_item()
                    yield SplashRequest(url=self.eventUrl,callback=self.parseFightCard,\
                        endpoint="execute",args={"lua_source": self.script2},\
                        headers={"User-Agent": random.choice(USER_AGENT_LIST)})

        except Exception as ex:
            logging.info("error => {0}".format(ex))
            print("error => %s" % ex)

    def parseFightCard(self,response):
        try:
            resetFightCard(self)
            setFightCardDetails(self,response)
            setFirstRowFightCard(self,response)
            loader = loadFightCardItem(self,response)
            yield loader.load_item()

            trTags = checkEmpty(response.xpath("//table/tbody/tr[contains(@itemprop,'subEvent')]"))
            # /html/body/div[4]/div[3]/section[2]/div/table/tbody/tr[2]

            if (trTags != "None"):
                for sel in trTags:
                    resetFightCard(self)
                    setFightCard(self,response,sel)

                    loader = loadFightCardItem(self,response)
                    yield loader.load_item()

        except Exception as ex:
            print("exception: %s" % ex)

class FighterSpider(CrawlSpider):
    name = "fighter"
    allowed_domains = ["www.sherdog.com","sherdog.com"]
    start_urls = ["https://www.sherdog.com"]
    custom_settings = {
        "ITEM_PIPELINES": {
            'sherdog.pipelines.FighterPipeline': 245,
        },
        "CLOSESPIDER_ITEMCOUNT": 65
    }
    handle_httpstatus_list = [403]

    # configure_logging(install_root_handler=False)
    # logging.basicConfig(filename='log.txt',format='%(levelname)s: %(message)s', \
    #     level=logging.INFO,filemode="w+"
    # )

    rules = (
        # extract links matching fighter and parse with spider's method
        Rule(LinkExtractor(allow=('fighter/'),deny=()),
             process_request="parseFromRule"),
    )

    def __init__(self,*args,**kwargs):
        super(FighterSpider,self).__init__(*args,**kwargs)
        self.name = ""
        self.birthDate = ""
        self.age = ""
        self.height = ""
        self.weight = ""
        self.association = ""
        self.fighterClass = ""
        self.win = ""
        self.loss = ""
        self.nationality = ""
        self.locality = ""
        self.url = ""

        self.urlList = []

        self.count = 0
        # self.url = "https://www.sherdog.com"
        self.script = """
                         function main(splash,args)
                             local cookies = splash:get_cookies()
                             splash:init_cookies(cookies)
                             assert(splash:go(splash.args.url))
                             assert(splash:wait(1.5))

                             return {
                                 cookies,
                                 html = splash:html(),
                                 -- png = splash:png(),
                                 -- har = splash:har()
                             }
                         end
                      """

    def parseFromRule(self,request,htmlResponse):
        # error: spider must return request, item, or None, got 'generator'
        # return instead of yield generator
        return SplashRequest(url=request.url,callback=self.parseFighter,
            endpoint="execute",args={"lua_source": self.script},
            headers={"User-Agent": random.choice(USER_AGENT_LIST)})

    def parseFighter(self,response):
        try:
            name = checkEmpty(response.xpath("//div[@class='fighter-right']/div[contains(@class,'fighter-title')]/div/h1/span[contains(@class,'fn')]/text()").get())
            if (name != "None"):
                self.name = name.lower()
            else:
                self.name = "None"

            trTagsAgeWeightHeight = checkEmpty(response.xpath("//div[@class='fighter-data']/div/table/tbody/tr"))

            age = checkEmpty(trTagsAgeWeightHeight[0].xpath(".//td[2]/b/text()").get())
            setAge(self,age)

            birthDate = checkEmpty(trTagsAgeWeightHeight[0].xpath(".//td[2]/span/text()").get())
            if (birthDate != "None"):
                setBirthDate(self, birthDate)
            else:
                self.birthDate = "None"

            height = checkEmpty(trTagsAgeWeightHeight[1].xpath(".//td[2]/b/text()").get())
            if (height != "None"):
                setHeight(self, height)
            else:
                self.height = "None"

            weight = checkEmpty(trTagsAgeWeightHeight[2].xpath(".//td[2]/b/text()").get())
            if (weight != "None"):
                setWeight(self,weight)
            else:
                self.weight = "None"

            association = checkEmpty(response.xpath("//div[@class='association-class']/span[contains(@itemprop,'memberOf')]/a/span/text()").get())
            if (association != "None"):
                setAssociation(self,association)
            else:
                self.association = ""

            fighterClass = checkEmpty(response.xpath("//div[@class ='association-class']/a[contains(@href,'stats')]/text()").get())
            if (fighterClass != "None"):
                self.fighterClass = str(fighterClass).lower()
            else:
                self.fighterClass = ""

            win = checkEmpty(response.xpath("//div[@class='fighter-data']/div[contains(@class,'winsloses-holder')]/div[@class='wins']/div/span[2]/text()").get())
            if (win != "None"):
                self.win = win
            else:
                self.win = ""

            loss = checkEmpty(response.xpath("//div[@class='fighter-data']/div[contains(@class,'winsloses-holder')]/div[@class='loses']/div/span[2]/text()").get())
            if (loss != "None"):
                self.loss = loss
            else:
                self.loss = ""

            nationality = checkEmpty(response.xpath("//div[@class='fighter-nationality']/span[contains(@class,'item birthplace')]/strong/text()").get())
            setNationality(self,nationality)

            locality = checkEmpty(response.xpath("//div[@class='fighter-nationality']/span[contains(@class,'item birthplace')]/span[@itemprop='address']/span/text()").get())
            setLocality(self,locality)

            url = checkEmpty(response.url)
            if (url != "None"):
                self.url = url
            else:
                self.url = "None"

            urlTrTags = checkEmpty(response.xpath("//div[@class='new_table_holder']/table[contains(@class,'fighter')]/tbody/tr[not(@class)]"))

            loader = loadFighterItem(self,response)
            yield loader.load_item()

            if (len(urlTrTags) != 0):
                for sel in urlTrTags:
                    partialUrl = sel.xpath(".//td/a[contains(@href,'fighter')]/@href").get()
                    fighterUrl = response.urljoin(partialUrl)

                    yield SplashRequest(url=fighterUrl,callback=self.parseFighter,endpoint="execute",args={"lua_source": self.script}, \
                        headers={"User-Agent": random.choice(USER_AGENT_LIST)})

        except Exception as ex:
            print("exception => error in parse fighter --- {0}".format(ex))

class FightHistorySpider(scrapy.Spider):
    name = "fight_history"
    allowed_domains = ["www.sherdog.com",'sherdog.com']
    # start_urls = ['https://www.sherdog.com/events/recent']

    custom_settings = {
        "ITEM_PIPELINES": {
            'sherdog.pipelines.FightHistoryPipeline': 198,
        },
        "CLOSESPIDER_ITEMCOUNT": 37
    }

    # configure_logging(install_root_handler=False)
    # logging.basicConfig(
    #     filename='log.txt',
    #     format='%(levelname)s: %(message)s',
    #     level=logging.INFO,filemode="w+"
    # )

    def __init__(self, *args, **kwargs):
        super(FightHistorySpider,self).__init__(*args,**kwargs)
        self.fighterUrlDir = "text_files"
        self.fighterUrlFileName = "fighter_url1.txt"
        self.eventUrlList = []

        self.fighter1Name = ""
        self.fighter2Name = ""
        self.fighter1Result = ""
        self.fighter2Result = ""
        self.event = ""
        self.dateFightHistory = ""
        self.method = ""
        self.round = ""
        self.time = ""

        self.url = ""
        self.script = """
                             function main(splash)
                                 local cookies = splash:get_cookies()
                                 splash:init_cookies(cookies)

                                 assert(splash:go{splash.args.url,headers=splash.args.headers,
                                     http_method=splash.args.http_method,body=splash.args.body})
                                 assert(splash:wait(0.5))

                                 local entries = splash:history()
                                 local last_response = entries[#entries].response

                                 return {
                                     url = splash:url(),
                                     headers = last_response.headers,
                                     http_status = last_response.status,
                                     cookies = splash:get_cookies(),
                                     html = splash:html()
                                     -- png = splash:png(),
                                     -- har = splash:har()
                                 }
                             end
                         """
        self.script2 = """
                              function main(splash,args)
                                  local cookies = splash:get_cookies()
                                  splash:init_cookies(cookies)
                                  assert(splash:go(splash.args.url))
                                  assert(splash:wait(1.5))

                                  return {
                                      cookies,
                                      html = splash:html(),
                                      -- png = splash:png(),
                                      -- har = splash:har()
                                  }
                              end
                          """

    def start_requests(self):
        try:
            print(os.getcwd())
            fileReader = open(os.path.join(self.fighterUrlDir,self.fighterUrlFileName),"r")
            text = fileReader.readlines()

            for url in text:
                splitFighter = url.split("fighter/")[1]
                subStr = re.sub(r"[0-9\-]"," ",splitFighter)
                fighter1Name = subStr.strip()

                yield SplashRequest(url=url,callback=self.parseFightHistory,endpoint="execute", \
                    args={"lua_source": self.script2},headers={"User-Agent": random.choice(USER_AGENT_LIST)}, \
                    cb_kwargs={"fighter1Name": fighter1Name})

        except Exception as ex:
            print("exception => error opening file --- {0}".format(ex))

    def parseFightHistory(self,response,**kwargs):
        try:
            trTags = checkEmpty(response.xpath(".//table[contains(@class,'new_table')]/tbody/tr[not(@class)]"))

            for sel in trTags:
                self.fighter1Result = checkEmpty(sel.xpath(".//td[1]/span/text()").get())
                setFighter2Result(self)
                self.fighter1Name = kwargs["fighter1Name"]
                self.fighter2Name = checkEmpty(sel.xpath(".//td[2]/a/text()").get())

                event = checkEmpty(sel.xpath(".//td[3]/a/text()").get())
                setEvent(self,event)

                dateFightHistory = checkEmpty(sel.xpath(".//td[3]/span/text()").get())
                setDateFightHistory(self,dateFightHistory)

                method = checkEmpty(sel.xpath("./td[contains(@class,'winby')]/b/text()").get())
                setMethod(self,method)

                self.round = checkEmpty(sel.xpath("./td[5]/text()").get())

                time = checkEmpty(sel.xpath("./td[6]/text()").get())
                setTime(self,time)

                print("")

            name = checkEmpty(response.xpath("//div[@class='fighter-right']/div[contains(@class,'fighter-title')]/h1/span[contains(@class,'fn')]/text()").get())
            if (name != "None"):
                self.name = name.lower()
            else:
                self.name = "None"


            # loader = loadFighterItem(self,response)
            # yield loader.load_item()

            # yield SplashRequest(url=fighterUrl, callback=self.parseFighter, endpoint="execute",
            #     args={"lua_source": self.script}, \
            #                     headers={"User-Agent": random.choice(USER_AGENT_LIST)})

        except Exception as ex:
            print("exception => error in parse fighter history --- {0}".format(ex))




