import glob, os
from vaderSentiment.vaderSentiment import SentimentIntensityAnalyzer
import nltk
nltk.download('punkt')
import csv
import pandas as pd
os.chdir("/home/anisha/Desktop/sample")# changing working directory as required path

for item in glob.glob("*.txt"):  # getting all text files from given directory
	
	with open(item, 'r') as f: # reading text file
            lines = f.read()
            sents = nltk.sent_tokenize(lines)# seperates  each sentences
	sid = SentimentIntensityAnalyzer()
	com = 0
        # sentiment anaysis
	for sentence in sents:
            print(sentence)
            ss = sid.polarity_scores(sentence)
            com = com + ss['compound']
	sentiment_score = (float(com) / len(sents))
	rounded = round(sentiment_score, 2)
	print('sentiment score:', rounded)
	if (sentiment_score < 0.0):
		feedback = "negative"
	elif (sentiment_score > 0.1):
		feedback = "positive"
	else:
		feedback = "neutral"
print(feedback)
