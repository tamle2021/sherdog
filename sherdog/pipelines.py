# define your item pipelines here
# don't forget to add your pipeline to the ITEM_PIPELINES setting
# see: https://docs.scrapy.org/en/latest/topics/item-pipeline.html

import os,re
from scrapy import signals
from scrapy.exporters import CsvItemExporter
from .items import EventItem,FightCardItem,FighterItem
from datetime import datetime

class SherdogStatsPipeline:
    def __init__(self):
        self.outputEventDir = "sherdog/csv_files/event"
        self.outputSpecificEventDir = "sherdog/csv_files/specific_event"
        self.outputFighterDir = "sherdog/csv_files/fighter"
        self.eventList = ["date","eventName","eventTitle","location"]
        self.specificEventList = ["fighter1Name","fighter1Result","fighter2Name","fighter2Result", \
            "fighterMethodResult"]
        self.fighterList = ["fighterName","birthDate","age","height","weight","fighterClass","win","loss", \
            "locality","country"]

        self.eventWriter = ""
        self.specificEventWriter = ""
        self.fighterWriter = ""
        # ----------------------------------
        self.eventFileName = ""
        self.specificEventFileName = ""
        self.fighterFileName = ""
        # ----------------------------------
        self.eventExporter = ""
        self.specificEventExporter = ""
        self.fighterExporter = ""

    @classmethod
    def from_crawler(cls,crawler):
        pipeline = cls()
        crawler.signals.connect(pipeline.spider_opened,signals.spider_opened)
        crawler.signals.connect(pipeline.spider_closed,signals.spider_closed)
        return pipeline

    def spider_opened(self,spider):
        today = datetime.today()
        dt = datetime(today.year,today.month,today.day)
        self.eventFileName = "event_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_"\
            + str(dt.year) + "_.csv"
        self.specificEventFileName = "specific_event_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_"\
            + str(dt.year) + "_.csv"

        self.fighterFileName = "fighter_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_" + str(dt.year) + "_.csv"

        absolutePathEvent = os.path.join(os.getcwd(),self.outputEventDir)
        absolutePathSpecificEvent = os.path.join(os.getcwd(),self.outputSpecificEventDir)
        absolutePathFighter = os.path.join(os.getcwd(),self.outputFighterDir)

        self.eventWriter = open(os.path.join(absolutePathEvent,self.eventFileName),'wb+')
        self.specificEventWriter = open(os.path.join(absolutePathSpecificEvent,self.specificEventFileName),'wb+')
        self.fighterWriter = open(os.path.join(absolutePathFighter,self.fighterFileName),"wb+")

        self.eventExporter = CsvItemExporter(self.eventWriter)
        self.specificEventExporter = CsvItemExporter(self.specificEventWriter)
        self.fighterExporter = CsvItemExporter(self.fighterWriter)

        self.eventExporter.fields_to_export = self.eventList
        self.specificEventExporter.fields_to_export = self.specificEventList
        self.fighterExporter.fields_to_export = self.fighterList

        self.eventExporter.start_exporting()
        self.specificEventExporter.start_exporting()
        self.fighterExporter.start_exporting()

    def spider_closed(self,spider):
        self.eventExporter.finish_exporting()
        self.specificEventExporter.finish_exporting()
        self.fighterExporter.finish_exporting()

        self.eventWriter.close()
        self.specificEventWriter.close()
        self.fighterWriter.close()

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
                self.specificEventExporter.export_item(item)
                return item
        elif (isinstance(item,FighterItem)):
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


class SherdogFighterPipeline:
    def __init__(self):
        self.outputFighterDir = "sherdog/csv_files/fighter"
        self.fighterList = ["fighterName","birthDate","age","height","weight","association","fighterClass", \
            "win","loss","locality","country"]

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
        today = datetime.today()
        dt = datetime(today.year,today.month,today.day)

        self.fighterFileName = "fighter_" + self.checkMonthDay(dt.month) + "_" + self.checkMonthDay(dt.day) + "_" + str(dt.year) + "_.csv"
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
