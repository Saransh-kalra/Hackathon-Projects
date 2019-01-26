# FACE AND EYE DETECTION PROGRAM:
# USES OPEN CV AND HAAR-CASCADE-FRONTAL FACE AND EYES Classifier

# import open-cv use subprocess.call("pip install opencv-python") to install opencv
import cv2
import paralleldots as pd


def analyse():
    pd.set_api_key('MkSMsOuyDoriA9qVpcRMCYImB9lfHAkuccRq8flyTqA')
    outpf = open('outputfile.txt', 'a')

    #print(pd.get_api_key())

    try:
        analysis = pd.facial_emotion('image.jpg')
        print(analysis['facial_emotion'][0]['tag'])
        print(analysis['facial_emotion'][0]['score'])
        print('\n\n')
    except:
        print('Some Error occured\n')
        #print('error')
        analysis = 'Error'

    return analysis

# loading required classifiers
# Trained classifier to detect faces:

#ace_classify = cv2.CascadeClassifier('haarcascade_frontalface_default.xml')

# Trained classifier to detect eyes:

#eyes_classify = cv2.CascadeClassifier('haarcascade_eye.xml')

# Capture video from camera

vid = cv2.VideoCapture(0)

cv2.namedWindow('Face-Detector')



# infinite loop
while(True):

    # returns ret - bool(camera availability) frame -
    ret,frame = vid.read()

    cv2.imshow('Face-Detector',frame)

    cv2.imwrite('image.jpg', frame)

    answer = analyse()



    key = cv2.waitKey(1)
    if key == ord('q'):
        break


vid.release()

cv2.destroyAllWindows()
