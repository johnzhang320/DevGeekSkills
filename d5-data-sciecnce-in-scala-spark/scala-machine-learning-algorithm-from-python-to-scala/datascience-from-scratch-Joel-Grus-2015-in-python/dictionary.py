#!/usr/bin/python

# create symbolic link sudo ln -s /home/jzhang/anaconda2/bin/python2.7 /usr/bin/python
# make sure 5/2 = 2.5 instead of 2, we must import division which must happen at file beginning
from __future__ import division

 	 
#dictionory is about key and value pair in JSON format, retrieve value by pointing key

empty_dict={}   # empty  dictionary

grades ={"Joel":80, "Tim":95}  # dictionary literal (JSON)

print grades["Joel"]   # 80

try:
	grades["Katie"]
except KeyError:
	print "no grade for Katie"


print "Joel" in grades  # True


# add Katie

grades["Katie"]=100

print "length of grades=",len(grades)

tweet = {
	"user":"Johnzhang",
	"text":"Data Science ia Awesome",
	"tweet_count":100,
	"hashtag":["#data","#science","#datascience","#awesome","johnzhang"]
} 

print tweet.keys()
print tweet.values()
print tweet.items()

