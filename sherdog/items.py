# define here the models for your scraped items
#
# see documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
import scrapy

class FighterItem(scrapy.Item):
    fighterName = scrapy.Field()
    birthDate = scrapy.Field()
    age = scrapy.Field()
    height = scrapy.Field()
    weight = scrapy.Field()
    association = scrapy.Field()
    fighterClass = scrapy.Field()
    win = scrapy.Field()
    loss = scrapy.Field()
    locality = scrapy.Field()
    country = scrapy.Field()

class EventItem(scrapy.Item):
    date = scrapy.Field()
    eventName = scrapy.Field()
    eventTitle = scrapy.Field()
    eventUrl = scrapy.Field()
    location = scrapy.Field()

class FightCardItem(scrapy.Item):
    fighter1Name = scrapy.Field()
    fighter2Name = scrapy.Field()
    fighter1Result = scrapy.Field()
    fighter2Result = scrapy.Field()
    fighter1Url = scrapy.Field()
    fighter2Url = scrapy.Field()
    fightMethodResult = scrapy.Field()



