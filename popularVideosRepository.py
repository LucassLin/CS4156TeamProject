import os
import csv
import cv2
import requests
import numpy as np
import pandas as pd
import matplotlib.pyplot as plt 
from PIL import Image
from io import BytesIO
from wordcloud import WordCloud, STOPWORDS 
from nltk import sent_tokenize, word_tokenize, pos_tag
from nltk.corpus import stopwords, wordnet
from nltk.tokenize import RegexpTokenizer
from googleapiclient.discovery import build

CHANNEL_ANALYTICS_FILE = 'channelAnalytics.csv'
CHANNEL_KEYWORDS_FILE = 'channelKeywords.csv'

def readFromChannelCSV():
    channels = pd.read_csv(CHANNEL_ANALYTICS_FILE,sep='\t',engine='python',encoding='latin-1',
                    names=['channel_id','title','tags','country','default_language','videoCount','viewCount',
                             'subscriberCount','thumbnail'])
    return channels

def kMostPopularVideos(server, channelId, videoCount, k):
    popularVideos = []
    k = min(k, videoCount)
    request = server.search().list(part='snippet',type='video',order='viewCount',safeSearch='strict',
                               channelId=channelId, maxResults=k)
    response = request.execute()
    for item in response['items']:
        popularVideos.append(item['id']['videoId'])
    return popularVideos

def avgRatingAndViewCount(videos):
    avg_viewCount = 0
    avg_rating = 0
    comment_count = []
    for videoId in videos:
        request = server.videos().list(part='statistics',id=videoId)
        response = request.execute()
        # Average View Count
        avg_viewCount += int(response['items'][0]['statistics']['viewCount'])
        # Average Rating
        likesCount = int(response['items'][0]['statistics']['likeCount'])
        dislikesCount = int(response['items'][0]['statistics']['dislikeCount'])
        if likesCount == 0 and dislikesCount == 0:
            avg_rating += 0
        else:
            avg_rating += likesCount/(likesCount + dislikesCount)     
        if 'commentCount' in response['items'][0]['statistics']:
            commentCount = response['items'][0]['statistics']['commentCount']
        else:
            commentCount = 0
        comment_count.append(commentCount)
    avg_viewCount /= len(videos)
    avg_rating /= len(videos) 
    return avg_viewCount, avg_rating, comment_count

def commentsWordList(videos, comment_count):
    comments = []
    keywords = []
    tokenizer = RegexpTokenizer(r'\w+')
    for i in range(len(videos)):
        videoId = videos[i]
        commentCount = min(1000, int(comment_count[i]))
        request = server.commentThreads().list(part='snippet',videoId=videoId,order='relevance',
                                       textFormat='plainText',maxResults=1000)
        response = request.execute()
        for item in response['items']:
            reply = item['snippet']['topLevelComment']['snippet']['textOriginal']
            comments += [word.lower() for word in tokenizer.tokenize(reply) if not word.lower() in stopwords.words('english')] 
            
    valid_tags = ['JJ', 'JJR', 'JJS', 'NN', 'NNP', 'NNS', 'RB', 'RNR', 'RNS']
    for word, tag in pos_tag(comments):
        if tag in valid_tags and len(word) > 1:
            keywords.append(word)
    print('Extracted {0} keywords in the combination of all popular reviews'.format(len(keywords)))
    return keywords

def thumbnailsProcessor(channelId, thumbnail_url):
    threshold = 128
    response = requests.get(thumbnail_url)
    img = Image.open(BytesIO(response.content))
    image_name = channelId + '.png'
    img.save(image_name)
    img_grey = cv2.imread(image_name, cv2.IMREAD_GRAYSCALE)
    img_binary = cv2.threshold(img_grey, threshold, 255, cv2.THRESH_BINARY)[1]
    image_name = channelId + '_mask.png'
    cv2.imwrite(image_name,img_binary) 
    
def wordCloudGenerator(channelId, keywords):
    text = " ".join(word for word in keywords)
    image_name = channelId + '_mask.png'
    mask = np.array(Image.open(image_name))
    wordcloud = WordCloud(width = 800, height = 800, 
                mask=mask,contour_width=2, contour_color='black',
                background_color ='white', 
                stopwords = set(STOPWORDS), 
                min_font_size = 10).generate(text) 
    image_name = channelId + '_wordcloud.png'
    wordcloud.to_file(image_name)
    

def main():
    if len(sys.argv) < 2:
        print('Valid command : python3 popularVideosRepository.py API_KEY')
    else:
        API_KEY = str(sys.argv[1])
        server = build('youtube', 'v3', developerKey=API_KEY)
        channels = readFromChannelCSV()
        
        k = 10
        avgViewCount = []
        avgRating = []
        commentKeywords = []
        for i in range(1, len(channels)):
            channelId = channels['channel_id'][i]
            videoCount = channels['videoCount'][i]
            videos = kMostPopularVideos(server, channelId, int(videoCount), k)
            avg_viewCount, avg_rating, comment_count = avgRatingAndViewCount(videos)
            avgViewCount.append(avg_viewCount)
            avgRating.append(avg_rating)
            keywords = commentsWordList(videos, comment_count)
            commentKeywords.append(keywords)
            #thumbnail_url = channels['thumbnail'][i]
            #thumbnailsProcessor(channelId, thumbnail_url)
            #wordCloudGenerator(channelId, keywords)
        
        with open(CHANNEL_ANALYTICS_FILE, mode='w') as output_file:
            writer = csv.writer(output_file, delimiter='\t', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writerow(['channel_id','title','tags','country','default_language','videoCount','viewCount',
                             'subscriberCount','avg_viewCount','avg_Rating','thumbnail'])
            for i in range(len(channels)):
                writer.writerow([channels['channel_id'][i + 1], channels['title'][i + 1], 
                                 channels['tags'][i + 1],channels['country'][i + 1],
                                 channels['default_language'][i + 1],
                                 channels['videoCount'][i + 1], channels['viewCount'][i + 1],
                                 channels['subscriberCount'][i + 1], avgViewCount[i], avgRating[i],
                                 channels['thumbnail'][i + 1]])
                
        with open(CHANNEL_KEYWORDS_FILE, mode='w') as output_file:
            writer = csv.writer(output_file, delimiter='\t', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writerow(['channel_id','keywords'])
            for i in range(len(titles)):
                writer.writerow([channels['channel_id'][i + 1], commentKeywords[i]])

if __name__ == "__main__":
    main()