import sys
import csv
import pandas as pd
from googleapiclient.discovery import build

CHANNEL_ID_FILE = 'channelIDs.csv'
CHANNEL_ANALYTICS_FILE = 'channelAnalytics.csv'
CHANNEL_DESCRIPTION_FILE = 'channelDescriptions.csv'

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
    return channels

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
    
def main():
    if len(sys.argv) < 2:
        print('Valid command : python3 channelAnalyticsRepository.py API_KEY')
    else:
        API_KEY = str(sys.argv[1])
        server = build('youtube', 'v3', developerKey=API_KEY)
        channels = readFromChannelCSV()
        channelDataAnalytics(channels)
        
        with open(CHANNEL_ANALYTICS_FILE, mode='w') as output_file:
            writer = csv.writer(output_file, delimiter='\t', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writerow(['channel_id','title','tags','country','default_language','videoCount','viewCount',
                             'subscriberCount','thumbnail'])
            for i in range(len(titles)):
                writer.writerow([channels['channel_id'][i + 1], titles[i], tags[i], countries[i],
                                 defaultLanguages[i],videoCount[i],viewCount[i],subscriberCount[i],thumbnails[i]])
                
        with open(CHANNEL_DESCRIPTION_FILE, mode='w') as output_file:
            writer = csv.writer(output_file, delimiter='\t', quotechar='"', quoting=csv.QUOTE_MINIMAL)
            writer.writerow(['channel_id','description'])
            for i in range(len(titles)):
                writer.writerow([channels['channel_id'][i + 1], descriptions[i]])

if __name__ == "__main__":
    main()