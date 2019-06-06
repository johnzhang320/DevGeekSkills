#!/usr/bin/python

# create symbolic link sudo ln -s /home/jzhang/anaconda2/bin/python2.7 /usr/bin/python
# make sure 5/2 = 2.5 instead of 2, we must import division which must happen at file beginning
from __future__ import division
from collections import Counter
 	 
#control flow

i=2
message=""
if i>2:
	message ="if i >2"
elif i>3:
    message="i>3"
else: 
	message="i<2"

print "message=",message

#while

x=0
while x<10:
	print x," is less than 10"
	x+=1

for x in range(10):
	print x," is less tban ten"

tweet = {
	"user":"Johnzhang",
	"text":"Data Science ia Awesome",
	"tweet_count":100,
	"hashtag":["#data","#science","#datascience","#awesome","johnzhang"]
} 

for v in tweet.values():
	print v

#Trueiness
#python return None if variable is not defined , same as other language
z=None
y={}
print "z=",z,"y=",y

#Sorted
x = [4,1,2,3]
y = sorted(x)
print("y=",y)

reverse=True

d = sorted(x,key=abs,reverse=True)

print("d=",d)

#iterationg and enumerate
integer_list = [1,2,3,4,5,6]
heter_list = ["string" , 0.1, True]
string_list = ["python", "scala", "R", "Spark","kafka","flume"]

for index, element in enumerate(string_list):   # pythonic
	print "index=",index, "element=",element

for elem in string_list:     #not pythonic
	print "elem=",elem

p =[i + 0.1 for i, _ in enumerate(string_list)]
print "index+0.1=",p


#zip two lists to one, two lists are same size

# end of previous line is ':', next line must be added a '\t' that is tab
for _,elem in enumerate(zip(string_list,integer_list)):
	print "elem=",elem

movies = ["Annie Hall", "Ben-Hur", "Casablanca", "Gandhi", "West Side Story"]
num_oscars = [5, 11, 3, 8, 10]

# bars are by default width 0.8, so we'll add 0.1 to the left coordinates
# so that each bar is centered
xs = [i + 0.1 for i, _ in enumerate(movies)]
print 'xs=',xs	

grades = [83,95,91,87,70,0,85,82,100,67,73,77,0]
decile = lambda grade: grade // 10 * 10 
histogram = Counter(decile(grade) for grade in grades) 
print "histogram=",histogram

variance     = [1,2,4,8,16,32,64,128,256]
bias_squared = [256,128,64,32,16,8,4,2,1]
total_error  = [x + y for x, y in zip(variance, bias_squared)]

