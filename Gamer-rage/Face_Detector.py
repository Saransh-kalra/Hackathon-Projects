# FACE AND EYE DETECTION PROGRAM:
# USES OPEN CV AND HAAR-CASCADE-FRONTAL FACE AND EYES Classifier

# import open-cv use subprocess.call("pip install opencv-python") to install opencv
import cv2
import paralleldots as pd
import time
import subprocess as sp

def checkifmad(tscore):
    if(tscore>=0.3):
        outpf = open('outputfile.txt', 'w')
        outpf.write(str(tscore))
        outpf.close()
        cv2.destroyAllWindows()
        #sp.call('Antifacedetec.py', shell='True')
        sp.call('cat_videos.py',shell='True')




def rage_meter(cur_emo,cur_score,tscore):

    if(cur_emo == "Angry" or cur_emo == "Sad" or cur_emo == "Disgust" or cur_emo == "Fear"):
        tscore = tscore + cur_score

    if(cur_emo == "Happy" or cur_emo == "Surprise"):
        tscore = tscore - cur_score

    if(cur_emo == "Neutral"):
        return tscore

    return tscore


def analyse(tscore):

    pd.set_api_key('p5u0LjqzkyKNM3PEh93MIQhUjgtDMuOFuaZnuybCQoMqq')

    try:
        analysis = pd.facial_emotion('image.jpg')
        print(analysis)

        cur_emo = analysis['facial_emotion'][0]['tag']
        cur_score = analysis['facial_emotion'][0]['score']


    except:
        print('API KEY EXPIRED')
        cur_emo = 'Neutral'
        cur_score = '0'
        analysis = 'Error'

    print(cur_emo + "-- " + str(cur_score))
    print('anger meter --', tscore)
    tscore = rage_meter(cur_emo, cur_score, tscore)
    return tscore


vid = cv2.VideoCapture(0)

cv2.namedWindow('Face-Detector')

try:
    tscore = open('outpfile2.txt','r').read()
except:
    tscore = 0

# infinite loop
while(True):

    # returns ret - bool(camera availability) frame -
    ret,frame = vid.read()

    cv2.imshow('Face-Detector',frame)

    cv2.imwrite('image.jpg', frame)

    tscore = analyse(tscore)
    if(tscore<0):
        tscore == 0

    checkifmad(tscore)



    key = cv2.waitKey(4000)
    if key == ord('q'):
        break


vid.release()

cv2.destroyAllWindows()
