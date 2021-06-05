#create a bucket for store model

gcloud auth list

gsutil mb -p <replace projectid> -c STANDARD -l ASIA-SOUTHEAST2 -b on gs://fatigue-detection

upload file model.h5

//create function

#create a main.py and requirement
gcloud functions deploy fatigue-detection --entry-point fatigue_predict --runtime python38 --trigger-http --allow-unauthenticated

#test the function

flefteye, fmouth, frighteye == fatigue
nlefteye, nmouth, nrighteye == no fatigue

notes: 
The image must be encoded to base64 format first.

After that, you can send it by put in json format