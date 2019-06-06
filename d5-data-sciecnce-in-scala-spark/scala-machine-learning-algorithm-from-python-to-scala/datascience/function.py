#!/usr/bin/python

# create symbolic link sudo ln -s /home/jzhang/anaconda2/bin/python2.7 /usr/bin/python
# make sure 5/2 = 2.5 instead of 2, we must import division which must happen at file beginning
from __future__ import division

 	 

def double(x):
 	#this is where you put an optional docstring
    #that explains what the function does  
   	return x*2


	
def apply_to_one(f):
	#calls the function f with 1 as its arguments  
	return f(1)

my_double = double
x = apply_to_one(my_double)
print "x=",x
 

#creat function and pass function as argument , riduculous
# create short anonymous functions or lambda
print "y=",apply_to_one(lambda x:x+4)

#Assign lambda to a variable.

#another_double=lambda x: 2*x      # don't do that

def another_double(x): return 2* x  # do this instead

print "another_double=",another_double(4) 

# String use """ create multiple lines

multiline = """this is where you put an optional docstring
            that explains what the function does""" 
print multiline
 
# exception

try:
	print 10/0
except ZeroDivisionError:
	print "cannot divide by zero"

# list and list of list , riduculous
integer_list = [1,2,3,4,5]
heter_list = ["string" , 0.1, True]
string_list = ["python", "scala", "R", "Spark"]

list_of_list=[integer_list, heter_list,string_list,[4,5,6,7]]

print list_of_list, 1 in integer_list, False in heter_list

# extend array
x=[1,2,3]
x.extend([4,5,6])
print x

# x is immutable
y = x+[7,8,9]
print y

# dumn _
_, b,c,d,e=[1,2,3,4,5]
print b,c,d,e

#Tuples using () define immutable array
my_tuple =(1,2,3,4)
try:
	my_tuple[1]=33
except TypeError:
	print "cannot modify a tuple "

# function return 2 more variables , riduculuous
def sum_and_product(x,y):
	return (x+y),(x * y)

sp = sum_and_product(3,5)
s,p = sum_and_product(3,5)

print "sp=",sp
print "s=",s,"p=",p




