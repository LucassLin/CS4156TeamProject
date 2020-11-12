import sys
import csv
import pandas as pd
from googleapiclient.discovery import build

TOPIC_ID_FILE = 'topicIDs.csv'
CHANNEL_ID_FILE = 'channelIDs.csv'

def readTopicIdFromCSV():
    topics = pd.read_csv(TOPIC_ID_FILE,
                    sep=',',
                    engine='python',
                    encoding='latin-1',
                    names=['topic_id', 'subcategory', 'category'])
    return topics 

def generateKMostPopularChannels(server, topics, k):
    channel_id = []
    channel_subcategory = []
    channel_category = []
    for i in range(1, len(topics)):
        request = server.search().list(part='snippet',type='channel',order='viewCount',safeSearch='strict',
                                    topicId=topics['topic_id'][i],maxResults=k)
        response = request.execute()
        for item in response['items']:
            channelId = item['id']['channelId']
            request = server.channels().list(part='status', id=channelId)
            response = request.execute()
            if 'isLinked' in response['items'][0]['status']:
                isLinked = response['items'][0]['status']['isLinked']
            else:
                isLinked = False
            if 'privacyStatus' in response['items'][0]['status'] and response['items'][0]['status']['privacyStatus'] == 'public':
                isPublic = True
            else:
                isPublic = False
            if isLinked == True and isPublic == True:
                channel_id.append(channelId)
                channel_subcategory.append(topics['subcategory'][i])
                channel_category.append(topics['category'][i])
    print("Generated at total {0} most popular channels for {1} topics".format(len(channel_id),len(topics) - 1))
    return channel_id, channel_subcategory, channel_category

def printPopularChannelsRepository(channel_id, channel_subcategory, channel_category, n):
    data = {'channel_id':channel_id,'subcategory':channel_subcategory,'category':channel_category}
    df = pd.DataFrame(data)
    df.head(n)
    
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