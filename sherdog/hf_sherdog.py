import re
from datetime import datetime
from scrapy.loader import ItemLoader
from .items import EventItem,FightCardItem,FighterItem
import logging
from .switch_month import switchMonthThreeLetters

def printTime():
    now = datetime.now()
    currentDate = now.strftime("%m_%d_%y")
    return currentDate

def resetFighterStats(self):
    self.name
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

def setLocation(self,location):
    subComma = re.sub(r"[\,]",";",location)
    self.location = '-' + subComma + '-'

def setLocality(self,locality):
    if (re.search(r"N/A",locality) != None or len(locality) == 0 or locality == "None"):
        self.locality = "None"
    else:
        subQuote = re.sub(r'[\"\,]',"",locality)
        self.locality = '-' + subQuote + '-'

def setNationality(self,nationality):
    if (re.search(r"N/A",nationality) != None or len(nationality) == 0 or nationality == "None"):
        self.nationality = "None"
    else:
        self.nationality = nationality

def setHeight(self,height):
    if (re.search(r"N/A",height) == None and re.search(r"0'0",height) == None):
        subDoubleQuote = re.sub(r"[\"]","",height)
        splitSingleQuote = subDoubleQuote.split("'")
        self.height = str((int(splitSingleQuote[0]) * 12) + int(splitSingleQuote[1]))
    else:
        self.height = "None"

def setWeight(self,weight):
    subLetters = re.sub(r"[^0-9]","",weight)
    if (subLetters != "0" and len(subLetters) != 0):
        self.weight = subLetters
    else:
        self.weight = "None"

def setAge(self,age):
    try:
        subStr = ""
        if (re.search(r"N/A", age) != None or len(age) == 0 or age == "None"):
            self.age = "None"
        else:
            subStr = re.sub(r"AGE:", "",age)
            self.age = subStr.strip()

    except Exception as ex:
        print("exception => error setting age --- {0}".format(ex))

def setBirthDate(self,birthDate):
    month = ""
    day = ""
    year = ""
    if (re.search(r"N/A",birthDate) != None):
        self.birthDate = "None"
    else:
        splitStr = birthDate.split(" ")

        month = splitStr[0]
        if (len(month) != 0):
            month = switchMonthThreeLetters(month)

        day  = splitStr[1].replace(",","")
        year = splitStr[2]

        if (month != "None"):
            self.birthDate = month + "/" + day + "/" + year
        else:
            self.birthDate = "None"

def setAssociation(self,association):
    try:
        self.association = str(association).lower()

    except Exception as ex:
        print("exception => error setting association --- {0}".format(ex))

def setFightCardDetails(self,response):
    try:
        # /html/body/div[4]/div[3]/section[1]/div[2]/div[1]/div/div[2]/span[1]
        dateFightCard = checkEmpty(response.xpath("//div[contains(@class,'event_detail')]/div/div[@class='info']/span/text()").get())

        if (dateFightCard != "None"):
            setDateFightCard(self,dateFightCard)

    except Exception as ex:
        print("exeception => error setting date fight card ---  {0}".format(ex))
        self.dateFightCard = "None"


    try:
        eventNameFightCard = checkEmpty(response.xpath("//div[contains(@class,'event_detail')]/div/h1/span[@itemprop='name']/text()").get())

        if (eventNameFightCard != "None"):
            self.eventNameFightCard = eventNameFightCard.lower()
        else:
            self.eventNameFightCard = "None"

    except Exception as ex:
        print("exeception => error setting event name fight card ---  {0}".format(ex))
        self.eventNameFightCard = "None"

    try:
        locationFightCard = checkEmpty(response.xpath("//div[contains(@class,'event_detail')]/div/div[@class='info']/span/span[@itemprop='location']/text()").get())

        if (locationFightCard != "None"):
            replaceComma = locationFightCard.strip().replace(",",";")
            self.locationFightCard = "-" + replaceComma + "-"
        else:
            self.locationFightCard = "None"

    except Exception as ex:
        print("exeception => error setting location fight card ---  {0}".format(ex))
        self.locationFightCard = "None"


def setDateFightCard(self,dateFightCard):
    splitStr = dateFightCard.split(" ")

    month = switchMonthThreeLetters(splitStr[0])
    day = splitStr[1].replace(",","")
    year = splitStr[2]

    self.dateFightCard = month + "/" + day + "/" + year

def setFirstRowFightCard(self,response):
    try:
        # check for meta tag
        hasMeta = checkEmpty(response.xpath("//div[@class='left-module-pad-line']/meta[@itemprop]").get())

        if (hasMeta != "None"):
            fighter1Name = checkEmpty(response.xpath("//div[@class='fighter left_side']/h3/a/span/text()").get())
            if (fighter1Name != "None"):
                self.fighter1Name = fighter1Name.lower()
            else:
                self.fighter1Name = "None"

            fighter1Result = checkEmpty(response.xpath("//div[@class='fighter left_side']/span[contains(@class,'final_result')]/text()").get())
            if (fighter1Result != "None"):
                self.fighter1Result = checkFightResult(self, fighter1Result.lower())
            else:
                self.fighter1Result = "None"

            fighter2Name = checkEmpty(response.xpath("//div[@class='fighter right_side']/h3/a/span/text()").get())
            if (fighter2Name != "None"):
                self.fighter2Name = fighter2Name.lower()
            else:
                self.fighter2Name = "None"

            fighter2Result = checkEmpty(response.xpath("//div[@class='fighter right_side']/span[contains(@class,'final_result')]/text()").get())
            if (fighter2Result != "None"):
                self.fighter2Result = checkFightResult(self, fighter2Result.lower())
            else:
                self.fighter2Result = "None"

            fightMethodResult = checkEmpty(response.xpath("//div/table[contains(@class,'fight_card_resume')]/tbody/tr/td[2]/text()").get())
            if (fightMethodResult != "None"):
                self.fightMethodResult = fightMethodResult.strip().lower()
            else:
                self.fightMethodResult = "None"

            fightRound = checkEmpty(response.xpath("//div/table[contains(@class,'fight_card_resume')]/tbody/tr/td[4]/text()").get())
            if (fightRound != "None"):
                self.fightRound = fightRound.strip().lower()
            else:
                self.fightRound = "None"

            time = checkEmpty(response.xpath("//div/table[contains(@class,'fight_card_resume')]/tbody/tr/td[5]/text()").get())
            setTime(self,time)

            print("")



    except Exception as ex:
        print("exception => error setting first row fight card --- {0}".format(ex))

def setTime(self,time):
    try:
        if (time != "None"):
            splitColon = time.split(":")
            min = splitColon[0]
            seconds = splitColon[1]

            if (min == "0"):
                convertMin = 0
            else:
                convertMin = int(min) * 60

            self.time = str(convertMin + int(seconds))

        else:
            self.time = "None"

    except Exception as ex:
        print("exception => error setting time --- {0}".format(ex))
        self.time = "None"

def setFightCard(self,response,sel):
    try:
        fighter1Name = checkEmpty(sel.xpath(".//td[contains(@class,'text_right')]/div[@class='fighter_list left']/div/a/span/text()").getall())
        setFighterName(self,fighter1Name,"f1")

        fighter1Url = checkEmpty(sel.xpath(".//td[contains(@class,'text_right')]/div[@class='fighter_list left']/div/a/@href").get())
        if (fighter1Url != "None"):
            self.fighter1Url = response.urljoin(fighter1Url)
        else:
            self.fighter1Url = "None"

        fighter1Result = checkEmpty(sel.xpath(".//td[contains(@class,'text_right')]/div[@class='fighter_list left']/div/span[contains(@class,'final_result')]/text()").get())
        if (fighter1Result != "None"):
            self.fighter1Result = fighter1Result.lower()
        else:
            self.fighter1Result = "None"

        fighter2Name = checkEmpty(sel.xpath(".//td[contains(@class,'text_left')]/div[@class='fighter_list right']/div/a/span/text()").getall())
        setFighterName(self,fighter2Name,"f2")

        fighter2Url = checkEmpty(sel.xpath(".//td[contains(@class,'text_left')]/div[@class='fighter_list right']/div/a/@href").get())
        if (fighter2Url != "None"):
            self.fighter2Url = response.urljoin(fighter2Url)
        else:
            self.fighter2Url = "None"

        fighter2Result = checkEmpty(
            sel.xpath(".//td[@class='text_left col_fc_upcoming']/div[@class='fighter_result_data']/span/text()").get())
        if (fighter2Result != "None"):
            self.fighter2Result = checkFightResult(self, fighter2Result.lower())
        else:
            self.fighter2Result = "None"

        fightMethodResult = checkEmpty(sel.xpath(".//td[@class='winby']/text()").get())
        if (fightMethodResult != "None"):
            self.fightMethodResult = fightMethodResult.strip().lower()
        else:
            self.fightMethodResult = "None"

        fightRound = checkEmpty(sel.xpath(".//td[@class='winby']/td[1]/text()").get())
        if (fightRound != "None"):
            self.fightRound = fightRound.strip().lower()
        else:
            self.fightRound = "None"

        time = checkEmpty(sel.xpath(".//td[@class='winby']/td[2]/text()").get())
        setTime(self,time)

    except Exception as ex:
        print("exception => error setting fight card --- {0}".format(ex))

def checkFightResult(self,fightResult):
    if (fightResult == "win"):
        return "W"
    elif (fightResult == "loss"):
        return "L"

def createUrl(self):
    # 1-202
    for i,x in enumerate(range(2,99,1)):
        url = "https://www.sherdog.com/events/recent/{0}-page".format(x)
        self.eventUrlList.append(url)

def setEventDetails(self,xp,response):
    try:
        eventName = checkEmpty(xp.xpath(".//td/a[contains(@itemprop,'url') and contains(@href,'/events')]/text()").get())

        if (eventName != "None"):
            self.eventName = eventName
        else:
            self.eventName = "None"

    except Exception as ex:
        print("exception => error setting name --- {0}".format(ex))
        self.eventName = "None"

    try:
        eventTitle = checkEmpty(xp.xpath(".//td/a[contains(@href,'/events') and not(@itemprop)]/text()").get())
        if (eventTitle != "None"):
            self.eventTitle = eventTitle
        else:
            self.eventTitle = "None"

    except Exception as ex:
        print("exception => error setting title --- {0}".format(ex))
        self.eventTitle = "None"

    try:
        eventUrl = checkEmpty(xp.xpath(".//td/a[contains(@href,'/events') and not(@itemprop)]/@href").get())
        if (eventUrl != "None"):
            urlJoin = checkEmpty(response.urljoin(eventUrl))
            if (urlJoin != "None"):
                self.eventUrl = urlJoin
            else:
                self.eventUrl = "None"

    except Exception as ex:
        print("exception => error setting url --- {0}".format(ex))
        self.eventUrl = "None"

    try:
        location = checkEmpty(xp.xpath(".//td/span[contains(@itemprop,'location')]/text()").get())
        if (location != "None"):
            setLocation(self,location)
        else:
            self.location = "None"

    except Exception as ex:
        print("exception => error setting location --- {0}".format(ex))
        self.location = "None"


def setFighterName(self,fighterName,type):

    try:
        firstName = fighterName[0]
        lastName = fighterName[1]

        if (type == "f1"):
            self.fighter1Name = firstName.lower() + " " + lastName.lower()
        else:
            self.fighter2Name = firstName.lower() + " " + lastName.lower()


    except Exception as ex:
        print("exception => error setting {0} name --- {1}".format(type,ex))
        if (type == "f1"):
            self.fighter1Name = "None"
        else:
            self.fighter2Name = "None"


def setDate(self,sel):
    try:
        monthNumber = ""
        eventDate = checkEmpty(sel.xpath(".//td/div[contains(@class,'calendar-date')]/div/text()").getall())

        if (eventDate != "None"):
            month = eventDate[0].strip()
            day = eventDate[1].strip()
            year = eventDate[2].strip()

            monthNumber = switchMonthThreeLetters(month)

            # if (monthNumber != "None" and len(day) != 0 and len(year) != 0):
            self.date = monthNumber + "/" + day + "/" + year
            # else:
            # self.date = "None"
        else:
            self.date = "None"

    except Exception as ex:
        print("exception => error setting date --- {0}".format(ex))
        self.date = "None"

def loadEventItem(self,response):
    self.date = self.date if (self.date != "") else "None"
    self.eventName = self.eventName if (self.eventName != "") else "None"
    self.eventTitle = self.eventTitle if (self.eventTitle != "") else "None"
    self.eventUrl = self.eventUrl if (self.eventUrl != "") else "None"
    self.location = self.location if (self.location != "") else "None"

    loader = ItemLoader(item=EventItem(),response=response)
    loader.add_value("date",self.date)
    loader.add_value("eventName",self.eventName)
    loader.add_value("eventTitle",self.eventTitle)
    loader.add_value("eventUrl",self.eventUrl)
    loader.add_value("location",self.location)
    return loader

def loadFightCardItem(self,response):
    self.dateFightCard = self.dateFightCard if (self.dateFightCard != "") else "None"
    self.eventNameFightCard = self.eventNameFightCard if (self.eventNameFightCard != "") else "None"
    self.locationFightCard = self.locationFightCard if (self.locationFightCard != "") else "None"
    self.fighter1Name = self.fighter1Name if (self.fighter1Name != "") else "None"
    self.fighter2Name = self.fighter2Name if (self.fighter2Name != "") else "None"
    self.fighter1Url = self.fighter1Url if (self.fighter1Url != "") else "None"
    self.fighter2Url = self.fighter2Url if (self.fighter2Url != "") else "None"
    self.fighter1Result = self.fighter1Result if (self.fighter1Result != "") else "None"
    self.fighter2Result = self.fighter2Result if (self.fighter2Result != "") else "None"
    self.fightMethodResult = self.fightMethodResult if (self.fightMethodResult != "") else "None"
    self.fightRound = self.fightRound if (self.fightRound != "") else "None"
    self.time = self.time if (self.time != "") else "None"

    loader = ItemLoader(item=FightCardItem(),response=response)
    loader.add_value("dateFightCard", self.dateFightCard)
    loader.add_value("eventNameFightCard", self.eventNameFightCard)
    loader.add_value("locationFightCard", self.locationFightCard)
    loader.add_value("fighter1Name",self.fighter1Name)
    loader.add_value("fighter2Name",self.fighter2Name)
    loader.add_value("fighter1Url", self.fighter1Url)
    loader.add_value("fighter2Url", self.fighter2Url)
    loader.add_value("fighter1Result",self.fighter1Result)
    loader.add_value("fighter2Result",self.fighter2Result)
    loader.add_value("fightMethodResult",self.fightMethodResult)
    loader.add_value("fightRound",self.fightRound)
    loader.add_value("time",self.time)
    return loader

def loadFighterItem(self,response):
    self.name = self.name if (self.name != "") else "None"
    self.birthDate = self.birthDate if (self.birthDate != "") else "None"
    self.age = self.age if (self.age != "") else "None"
    self.height = self.height if (self.height != "") else "None"
    self.weight = self.weight if (self.weight != "") else "None"
    self.association = self.association if (self.association != "") else "None"
    self.fighterClass = self.fighterClass if (self.fighterClass != "") else "None"
    self.win = self.win if (self.win != "") else "None"
    self.loss = self.loss if (self.loss != "") else "None"
    self.nationality = self.nationality if (self.nationality != "") else "None"
    self.locality = self.locality if (self.locality != "") else "None"
    self.url = self.url if (self.url != "") else "None"

    loader = ItemLoader(item=FighterItem(),response=response)
    loader.add_value("name",self.name)
    loader.add_value("birthDate",self.birthDate)
    loader.add_value("age",self.age)
    loader.add_value("height",self.height)
    loader.add_value("weight",self.weight)
    loader.add_value("association",self.association)
    loader.add_value("fighterClass",self.fighterClass)
    loader.add_value("win",self.win)
    loader.add_value("loss",self.loss)
    loader.add_value("nationality",self.nationality)
    loader.add_value("locality",self.locality)
    loader.add_value("url",self.url)
    return loader

def resetFightCard(self):
    self.fighter1Name = ""
    self.fighter2Name = ""
    self.fighter1Result = ""
    self.fighter2Result = ""
    self.fightMethodResult = ""

def checkHeight(data):
    subDoubleQuote = re.sub(r"[\"\\]",'',data)
    if (subDoubleQuote == "0'0" or subDoubleQuote == None):
        subDoubleQuote = "None"
        return subDoubleQuote
    else:
        splitSingleQuote = subDoubleQuote.split("'")
        convInches = (int(splitSingleQuote[0])) * 12 + int(splitSingleQuote[1])
        return str(convInches)

def checkEmpty(data):
    if (data == None or len(data) == 0):
        data = "None"
        return data
    else:
        return data