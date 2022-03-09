# define here the models for your scraped items
#
# see documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
import scrapy

class FighterItem(scrapy.Item):
    name = scrapy.Field()
    birthDate = scrapy.Field()
    age = scrapy.Field()
    height = scrapy.Field()
    weight = scrapy.Field()
    association = scrapy.Field()
    fighterClass = scrapy.Field()
    win = scrapy.Field()
    loss = scrapy.Field()
    nationality = scrapy.Field()
    locality = scrapy.Field()
    url = scrapy.Field()

class EventItem(scrapy.Item):
    date = scrapy.Field()
    eventName = scrapy.Field()
    eventTitle = scrapy.Field()
    eventUrl = scrapy.Field()
    location = scrapy.Field()

class FightHistoryItem(scrapy.Item):
    fighter1Name = scrapy.Field()



