#!/usr/bin/python
from __future__ import division
import math, random
from collections import defaultdict, Counter
from linear_algebra import dot
import recommender_systems as rem
def matrix_print(matrix):
    print
    for row in matrix:
        print row        

# -----------------User based recomemdation ------------------------    
print "\nusers_interests=",matrix_print(rem.users_interests)

print "\npopular interest=", matrix_print(rem.popular_interests)

print "\nrecommend user3 most popular interests=", matrix_print(rem.most_popular_new_interests(rem.users_interests[3],5))

print "\nunique interests=", matrix_print(rem.unique_interests)

print "\n user[0] interests=",rem.users_interests[0] 

print "\nmake user[0] interests vector=",rem.make_user_interest_vector(rem.users_interests[0])

print "\nuser interests matrix=\n",matrix_print(rem.user_interest_matrix)

print "\n users interests similiarity =\n", matrix_print(rem.user_similarities)

print "\n print most_similar_interests_to user[0]=", matrix_print(rem.most_similar_users_to(0))

print "\n print user based suggestion=",matrix_print(rem.user_based_suggestions(0))

# ----------------Items based recomendation-------------------

print "\n interest is row index (item number is total rows) and user is column matrix=", matrix_print(rem.interest_user_matrix)
 
print "\n interest similarity=", matrix_print(rem.interest_similarities)

print "\n most_similar_interests_to(0)(Big Data)=", matrix_print(rem.most_similar_interests_to(0))

print "\n Item based recommendation for user 0 =", matrix_print(rem.item_based_suggestions(0,True))






