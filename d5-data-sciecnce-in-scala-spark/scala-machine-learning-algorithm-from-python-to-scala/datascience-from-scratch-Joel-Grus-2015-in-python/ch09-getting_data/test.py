#!/usr/bin/python
from __future__ import division
from collections import Counter
import math, random, csv, json

from bs4 import BeautifulSoup
import requests

######
#
# BOOKS ABOUT DATA
#
######

def is_video(td):
    """it's a video if it has exactly one pricelabel, and if
    the stripped text inside that pricelabel starts with 'Video'"""
    pricelabels = td('span', 'pricelabel')
    return (len(pricelabels) == 1 and
            pricelabels[0].text.strip().startswith("Video"))

def book_info(td):
    """given a BeautifulSoup <td> Tag representing a book,
    extract the book's details and return a dict"""
    
    title = td.find("div", "thumbheader").a.text
    by_author = td.find('div', 'AuthorName').text
    authors = [x.strip() for x in re.sub("^By ", "", by_author).split(",")]
    isbn_link = td.find("div", "thumbheader").a.get("href")
    isbn = re.match("/product/(.*)\.do", isbn_link).groups()[0]
    date = td.find("span", "directorydate").text.strip()
    
    return {
        "title" : title,
        "authors" : authors,
        "isbn" : isbn,
        "date" : date
    }

from time import sleep

def scrape(num_pages=31):
    base_url = "http://shop.oreilly.com/category/browse-subjects/" + \
           "data.do?sortby=publicationDate&page="

    books = []

    for page_num in range(1, num_pages + 1):
        print "souping page", page_num
        url = base_url + str(page_num)
        soup = BeautifulSoup(requests.get(url).text, 'html5lib')
            
        for td in soup('td', 'thumbtext'):
            if not is_video(td):
                books.append(book_info(td))

        # now be a good citizen and respect the robots.txt!
        sleep(30)

    return books

def get_year(book):
    """book["date"] looks like 'November 2014' so we need to 
    split on the space and then take the second piece"""
    return int(book["date"].split()[1])

def plot_years(plt, books):
    # 2014 is the last complete year of data (when I ran this)
    year_counts = Counter(get_year(book) for book in books
                          if get_year(book) <= 2014)

    years = sorted(year_counts)
    book_counts = [year_counts[year] for year in x]
    plt.bar([x - 0.5 for x in years], book_counts)
    plt.xlabel("year")
    plt.ylabel("# of data books")
    plt.title("Data is Big!")
    plt.show()

##
# 
# APIs
#
##

endpoint = "https://api.github.com/users/joelgrus/repos"

repos = json.loads(requests.get(endpoint).text)

from dateutil.parser import parse

dates = [parse(repo["created_at"]) for repo in repos]
month_counts = Counter(date.month for date in dates)
weekday_counts = Counter(date.weekday() for date in dates)

####
#
# Twitter
#
####

from twython import Twython

# fill these in if you want to use the code
CONSUMER_KEY = "PjEDphhZ8Igv4IJO0nP6fkGrC"
CONSUMER_SECRET = "sbfDg8doSQ9nyWhhhr3FJ8A0Jgnv1tKyvGh30Uy2PrqAqY7UOi"
ACCESS_TOKEN = "187280067-nvFtJIKbShfcWnI8EcmS7OGTup4kHghlkYBReqIK"
ACCESS_TOKEN_SECRET = "3Snfc2TAgerzGZf7CMsFTAdOiJLwV7biT4z6q4Pa3qFVA"

def call_twitter_search_api():
    print "call_twitter_search_api() begin"
    twitter = Twython(CONSUMER_KEY, CONSUMER_SECRET)
    print "call_twitter_search_api() twitter=",twitter

    # search for tweets containing the phrase "data science"
    for status in twitter.search(q='"data science"')["statuses"]:
        user = status["user"]["screen_name"].encode('utf-8')
        text = status["text"].encode('utf-8')
        print user, ":", text
        print "call_twitter_search_api() end"

from twython import TwythonStreamer

# appending data to a global variable is pretty poor form
# but it makes the example much simpler
tweets = [] 

class MyStreamer(TwythonStreamer):
    """our own subclass of TwythonStreamer that specifies
    how to interact with the stream"""

    def on_success(self, data):
        """what do we do when twitter sends us data?
        here data will be a Python object representing a tweet"""

        # only want to collect English-language tweets
        if data['lang'] == 'en':
            tweets.append(data)

        # stop when we've collected enough
        if len(tweets) >= 1000:
            self.disconnect()

    def on_error(self, status_code, data):
        print status_code, data
        self.disconnect()

def call_twitter_streaming_api():
    stream = MyStreamer(CONSUMER_KEY, CONSUMER_SECRET, 
                        ACCESS_TOKEN, ACCESS_TOKEN_SECRET)

    # starts consuming public statuses that contain the keyword 'data'
    stream.statuses.filter(track='data')
    


#call_twitter_search_api()

call_twitter_streaming_api()

print "finished something"


    
