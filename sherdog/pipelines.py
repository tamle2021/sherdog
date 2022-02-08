# define your item pipelines here
# don't forget to add your pipeline to the ITEM_PIPELINES setting
# see: https://docs.scrapy.org/en/latest/topics/item-pipeline.html
import os,re
from sys import platform
from scrapy import signals
from scrapy.exporters import CsvItemExporter
from .items import EventItem,FightCardItem,FighterItem
from datetime import datetime

class SherdogEventFightCardPipeline:
    def __init__(self):
        self.outputEventDir = "csv_files/event"
        self.outputSpecificEventDir = "csv_files/specific_event"
        self.outputFighterDir = "csv_files/fighter"
        self.eventList = ["date","eventName","eventTitle","eventUrl","location"]
        self.fightCardList = ["dateFightCard","eventNameFightCard","locationFightCard","fighter1Name","fighter1Url","fighter1Result", \
            "fighter2Name","fighter2Url","fighter2Result","fightMethodResult"]

        self.eventWriter = ""
        self.fightCardWriter = ""
        # ----------------------------------
        self.eventFileName = ""
        self.fightCardFileName = ""
        # ----------------------------------
        self.eventExporter = ""
        self.fightCardExporter = ""

    @classmethod
    def from_crawler(cls,crawler):
        pipeline = cls()
        crawler.signals.connect(pipeline.spider_opened,signals.spider_opened)
        crawler.signals.connect(pipeline.spider_closed,signals.spider_closed)
        return pipeline

    def spider_opened(self,spider):
        # check system; change if on windows
        if (platform != "linux"):
            self.outputEventDir = "csv_files\\event"
            self.outputFightCardDir = "csv_files\\fight_card"

        today = datetime.today()
        dt = datetime(today.year,today.month,today.day)
        self.eventFileName = "event_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_"\
            + str(dt.year) + "_.csv"
        self.fightCardFileName = "fight_card_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_"\
            + str(dt.year) + "_.csv"

        absolutePathEvent = os.path.join(os.getcwd(),self.outputEventDir)
        absolutePathFightCard = os.path.join(os.getcwd(),self.outputFightCardDir)

        self.eventWriter = open(os.path.join(absolutePathEvent,self.eventFileName),'wb+')
        self.fightCardWriter = open(os.path.join(absolutePathFightCard,self.fightCardFileName),'wb+')

        self.eventExporter = CsvItemExporter(self.eventWriter)
        self.fightCardExporter = CsvItemExporter(self.fightCardWriter)

        self.eventExporter.fields_to_export = self.eventList
        self.fightCardExporter.fields_to_export = self.fightCardList

        self.eventExporter.start_exporting()
        self.fightCardExporter.start_exporting()

    def spider_closed(self,spider):
        self.eventExporter.finish_exporting()
        self.fightCardExporter.finish_exporting()

        self.eventWriter.close()
        self.fightCardWriter.close()

    def process_item(self,item,spider):
        if (isinstance(item,EventItem)):
            if (len(item) == 0):
                return item
            else:
                self.eventExporter.export_item(item)
                return item
        elif (isinstance(item,FightCardItem)):
            if (len(item) == 0):
                return item
            else:
                self.fightCardExporter.export_item(item)
                return item

    def checkMonthDay(self,dayOrMonth):
        if (int(dayOrMonth) <= 9):
            concatStr = "0" + str(dayOrMonth)
            return concatStr
        else:
            return str(dayOrMonth)

class SherdogFighterPipeline:
    def __init__(self):
        self.outputFighterDir = "csv_files/fighter"
        self.fighterList = ["name","birthDate","age","height","weight","association","fighterClass", \
            "win","loss","nationality","locality","url"]

        self.fighterWriter = ""
        self.fighterFileName = ""
        self.fighterExporter = ""

    @classmethod
    def from_crawler(cls,crawler):
        pipeline = cls()
        crawler.signals.connect(pipeline.spider_opened,signals.spider_opened)
        crawler.signals.connect(pipeline.spider_closed,signals.spider_closed)
        return pipeline

    def spider_opened(self,spider):
        # check system; change if on windows
        if (platform != "linux"):
            self.outputFighterDir = "csv_files\\fighter"

        today = datetime.today()
        dt = datetime(today.year,today.month,today.day)

        self.fighterFileName = "fighter_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_" + str(dt.year) + \
            "_.csv"

        absolutePathFighter = os.path.join(os.getcwd(),self.outputFighterDir)

        self.fighterWriter = open(os.path.join(absolutePathFighter,self.fighterFileName),"wb+")
        self.fighterExporter = CsvItemExporter(self.fighterWriter)
        self.fighterExporter.fields_to_export = self.fighterList
        self.fighterExporter.start_exporting()

    def spider_closed(self,spider):
        self.fighterExporter.finish_exporting()
        self.fighterWriter.close()

    def process_item(self,item,spider):
        if (isinstance(item,FighterItem)):
            if (len(item) == 0):
                return item
            else:
                self.fighterExporter.export_item(item)
                return item

    def checkMonthDay(self,dayOrMonth):
        if (int(dayOrMonth) <= 9):
            concatStr = "0" + str(dayOrMonth)
            return concatStr
        else:
            return str(dayOrMonth)
