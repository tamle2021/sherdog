# define here the models for your scraped items
#
# see documentation in:
# https://docs.scrapy.org/en/latest/topics/items.html
import scrapy

class EventItem(scrapy.Item):
    date = scrapy.Field()
    eventName = scrapy.Field()
    eventTitle = scrapy.Field()
    location = scrapy.Field()

class FightCardItem(scrapy.Item):
    fighter1Name = scrapy.Field()
    fighter2Name = scrapy.Field()
    fighter1Result = scrapy.Field()
    fighter2Result = scrapy.Field()
    fighterMethodResult = scrapy.Field()



