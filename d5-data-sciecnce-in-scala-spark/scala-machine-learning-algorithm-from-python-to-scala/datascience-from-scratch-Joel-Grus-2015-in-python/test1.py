#!/usr/bin/python

# create symbolic link sudo ln -s /home/jzhang/anaconda2/bin/python2.7 /usr/bin/python
# make sure 5/2 = 2.5 instead of 2, we must import division which must happen at file beginning
from __future__ import division
import re as regex
import matplotlib.pyplot as plt

print "Hello World,Python"
word = 'word: hello'
sentence = "This is a sentence."
paragraph = """This is a paragraph. It is
made up of multiple lines and sentences."""

print word 

print sentence

print paragraph

#raw_input("\n\nPress the enter key to exit.")

import sys; x = 'foo'; sys.stdout.write(x + '\n')

#Assigning Values to Variables

counter = 100          # An integer assignment
miles   = 1000.0       # A floating point
name    = "John"       # A string

print counter
print miles
print name

#multiple assignement

a = b = c = 1
# loop
for i in [1,2,3,4,5,6]: 
    print i
# import module re as alias regex

my_regex = regex.compile("[0-9]+", regex.I)



print "5/2=", 5/2
 
