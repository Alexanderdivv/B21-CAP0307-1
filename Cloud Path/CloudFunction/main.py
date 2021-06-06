# receive image and process
# download model
# process image with model

import numpy as np
import os
import tensorflow as tf
import base64

from google.cloud import storage
from tensorflow.keras.layers import Dense, Flatten, Conv2D
from tensorflow.keras.models import Sequential
from tensorflow.keras import Model
from PIL import Image
from io import BytesIO
from tensorflow.keras.preprocessing.image import img_to_array
from tensorflow.keras.models import load_model
from keras.preprocessing import image

# We keep model as global variable so we don't have to reload it in case of warm invocations
model = None

# Download model file from cloud storage bucket
def download_model_file():

    from google.cloud import storage

    # Model Bucket details
    BUCKET_NAME        = "fatigue-detection"
    PROJECT_ID         = "face-to-face-fatigue"
    GCS_MODEL_FILE     = "fatigue.h5"

    # Initialise a client
    client   = storage.Client(PROJECT_ID)
    
    # Create a bucket object for our bucket
    bucket   = client.get_bucket(BUCKET_NAME)
    
    # Create a blob object from the filepath
    blob     = bucket.blob(GCS_MODEL_FILE)
    
    folder = '/tmp/'
    if not os.path.exists(folder):
      os.makedirs(folder)
    # Download the file to a destination
    blob.download_to_filename(folder + "fatigue.h5")


# Main entry point for the cloud function
def fatigue_predict(request):

    # Use the global model variable 
    global model
    if not model:

        download_model_file()
        # model = load_model(open("/tmp/fatigue.h5", 'rb'))
        model = load_model("/tmp/fatigue.h5")
    
    folder = '/tmp/'
    # Get the features sent for prediction
    params = request.get_json()
    gambar=params['gambar'].replace(' ', '+')
    im = Image.open(BytesIO(base64.b64decode(gambar)))
    im.save(folder + 'image1.jpg', 'JPEG')

    if (params is not None):
        # Run a test prediction
        # predicting images
        img = image.load_img(folder + 'image1.jpg', target_size=(150, 150))
        x = image.img_to_array(img)
        x = np.expand_dims(x, axis=0)
        images = np.vstack([x])
        classes = model.predict_classes(images, batch_size=10)
        hasil = ['flefteye', 'fmouth', 'frighteye', 'nlefteye', 'nmouth', 'nrighteye']
        return hasil[np.argmax(classes)]
      # return pred_species[0]
        
    else:
        return "No data to predict"
