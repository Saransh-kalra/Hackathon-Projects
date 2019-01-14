from credentials import *
import subprocess as sp

##Pakages //Uncomment the next two lines if you dont have the pakages
#sp.call('pip install tweepy')
#sp.call('pip install textblob')

import tweepy
from textblob import TextBlob
import re

#Hard-code for setting up the connection
def setupAPI():

    #Setting up the API for use

    #Developer authentication
    auth = tweepy.OAuthHandler(CONSUMER_KEY,CONSUMER_SECRET)
    auth.set_access_token(ACCESS_TOKEN,ACCESS_SECRET)

    #If successful:
    api = tweepy.API(auth)
    return api

#Parses the tweeet to remove unncessary symbols/emojis
def clean_tweet(tweet):
    '''
    Utility function to clean tweet text by removing links, special characters
    using simple regex statements.
    '''
    return ' '.join(re.sub("(@[A-Za-z0-9]+)|([^0-9A-Za-z \t])| (\w+:\ / \ / \S+)", " ", tweet).split())

#Processes a sentiment of the tweet using textblob library
def get_tweet_sentiment(tweet):
    '''
    Utility function to classify sentiment of passed tweet
    using textblob's sentiment method
    '''
    # create TextBlob object of passed tweet text
    analysis = TextBlob(clean_tweet(tweet))
    print('analysis value-',analysis.sentiment.polarity)
    # set sentiment
    if analysis.sentiment.polarity > 0:
        return 'positive'
    elif analysis.sentiment.polarity == 0:
        return 'neutral'
    else:
        return 'negative'

#Fetching and parsing of tweets
def tweet_ext():
    fetched_tweets = api_obj.search(q=['Product name'], count=100)#Sends a request of 100 tweets with this word

    for tweet in fetched_tweets:

        #parsed_tweet = {}

        # saving text of tweet
        curtex = tweet.text
        #print(tweet.user.location)
        #print('\n\n\n')
        senti = get_tweet_sentiment(tweet.text)
        print('tweet -',curtex)
        print('location- ',tweet.user.location)
        print('sentiment-',senti)
        print('\n\n')

#Function calls:
#MAIN
api_obj= setupAPI()
tweet_ext()


