'''
Extract fighter urls into file

'''
import os,re

class ExtractFighterUrl():
    def __init__(self):
        self.fightCardDir = "csv_files/fight_card"
        self.fighterDir = "csv_files/fighter"
        self.fighterUrlDir = "text_files"

        self.fighterUrlFileName = "fighter_url.txt"
        self.urlList = []

    def start(self):
        try:
            # print(os.getcwd())
            # iterate through fight card directory
            for file in os.listdir(self.fightCardDir):
                fileReader = open(os.path.join(self.fightCardDir,file), "r")
                next(fileReader)
                text = fileReader.readlines()

                for line in text:
                    splitStr = line.split(",")

                    # search for fighter
                    for i in splitStr:
                        if (re.search(r"fighter", i) != None):
                            self.urlList.append(i)

                fileReader.close()

            # iterate through fighter directory
            for file in os.listdir(self.fighterDir):
                fileReader = open(os.path.join(self.fighterDir,file), "r")
                next(fileReader)
                text = fileReader.readlines()

                for line in text:
                    splitStr = line.split(",")

                    # search for fighter
                    for i in splitStr:
                        if (re.search(r"fighter", i) != None):
                            self.urlList.append(i)

                fileReader.close()

            filterList = set(self.urlList)
            fileWriter = open(os.path.join(self.fighterUrlDir,self.fighterUrlFileName),"w+")
            for line in filterList:
                fileWriter.write(line.strip() + "\n")

            fileWriter.close()

        except Exception as ex:
            print("exception => error in opening/parsing file --- {0}".format(ex))

if (__name__ == "__main__"):
    e = ExtractFighterUrl()
    e.start()
