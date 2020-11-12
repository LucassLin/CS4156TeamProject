import sys
import csv
import requests
import pandas as pd
from PIL import Image
from io import BytesIO
from googleapiclient.discovery import build

CHANNEL_ID_FILE = 'channelIDs.csv'

channels = []
titles = []
descriptions = []
thumbnails = []
countries = []
defaultLanguages = []
tags = []
videoCount = []
viewCount = []
subscriberCount = []

def readFromChannelCSV():
    channels = pd.read_csv(CHANNEL_ID_FILE,sep=',',engine='python',encoding='latin-1',
                    names=['channel_id','subcategory','category'])
    print("Loaded {0} channels of {1} topics".format(len(channels['channel_id'][1:]), 
                                                     len(set(channels['subcategory'][1:]))))

def channelDataAnalytics(channels):
    for i in range(1, len(channels)):
        channelId = channels['channel_id'][i]
        
        request = server.channels().list(part='snippet', id=channelId)
        response = request.execute()
        # Title
        titles.append(response['items'][0]['snippet']['title'])
        # Description
        if 'description' in response['items'][0]['snippet']:
            descriptions.append(response['items'][0]['snippet']['description'])
        else:
            descriptions.append('')
        # Rhumbnails
        thumbnails.append(response['items'][0]['snippet']['thumbnails']['high']['url'])
      
        request = server.channels().list(part='brandingSettings', id=channelId)
        response = request.execute()
        # Country
        if 'country' in response['items'][0]['brandingSettings']['channel']:
            countries.append(response['items'][0]['brandingSettings']['channel']['country'])
        else:
            countries.append('NA')
        # Default Language
        if 'defaultLanguage' in response['items'][0]['brandingSettings']['channel']:
            defaultLanguages.append(response['items'][0]['brandingSettings']['channel']['defaultLanguage'])
        else:
            defaultLanguages.append('NA')
        
        request = server.channels().list(part='topicDetails', id=channelId)
        response = request.execute()
        # Tags
        channel_tags = []
        topicCategories = response['items'][0]['topicDetails']['topicCategories']
        for category in topicCategories:
            idx = category.rfind('/')
            channel_tags.append(category[idx + 1:].replace('_', ' '))
        tags.append(channel_tags)
        
        request = server.channels().list(part='statistics', id=channelId)
        response = request.execute()
        videoCount.append(response['items'][0]['statistics']['videoCount'])
        viewCount.append(response['items'][0]['statistics']['viewCount'])
        if response['items'][0]['statistics']['hiddenSubscriberCount'] == False:
            subscriberCount.append(response['items'][0]['statistics']['subscriberCount'])
        else:
            subscriberCount.append('NA')
            
def mostKpopularVideos(channels, k):
    popularVideos = []
    for i in range(1, len(channels)):
        channelId = channels['channel_id'][i]
        k = min(k, videoCount[i])
        request = server.search().list(part='snippet',type='video',order='viewCount',safeSearch='strict',
                               channelId=channelId,maxResults=k)
        response = request.execute()
        videos = []
        for item in response['items']:
            videos.append(item['id']['videoId'])
        popularVideos.append(videos)
    return popularVideos

def channelVideosAnalytics(video_id):
    avg_viewCount = 0
    avg_rating = 0
    for videoId in video_id:
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
        
    avg_viewCount /= len(video_id)
    avg_rating /= len(video_id) 
    return avg_viewCount, avg_rating

def thumbnailsProcessor(channels, i):
    threshold = 128
    response = requests.get(thumbnails[i])
    img = Image.open(BytesIO(response.content))
    image_name = channels['channel_id'][i + 1] + '.png'
    img.save(image_name)
    img_grey = cv2.imread(image_name, cv2.IMREAD_GRAYSCALE)
    img_binary = cv2.threshold(img_grey, threshold, 255, cv2.THRESH_BINARY)[1]
    image_name = channels['channel_id'][i + 1] + '_mask.png'
    cv2.imwrite(image_name,img_binary)   
    
    
def main():
    if len(sys.argv) < 3:
        print('Valid command : python3 popularChannelsRepository.py API_KEY NUM_CHANNELS_PER_TOPIC')
    else:
        API_KEY = str(sys.argv[1])
        k = int(sys.argv[2])
        server = build('youtube', 'v3', developerKey=API_KEY)
        topics = readTopicIdFromCSV()
        channel_id, channel_subcategory, channel_category = generateKMostPopularChannels(server, topics, k)
        with open(CHANNEL_ID_FILE, mode='w') as output_file:
            writer = csv.writer(output_file, delimiter=',', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writerow(['channel_id','subcategory','category'])
            for i in range(len(channel_id)):
                writer.writerow([channel_id[i], channel_subcategory[i], channel_category[i]])

if __name__ == "__main__":
    main()