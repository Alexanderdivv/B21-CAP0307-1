# Follow this steps
<h3>Create a bucket for store model</h3>

```gcloud auth list```

```gsutil mb -p <replace projectid> -c STANDARD -l ASIA-SOUTHEAST2 -b on gs://fatigue-detection```

upload file model.h5

<h3>create function</h3>

//create a main.py and requirement<br>
```gcloud functions deploy fatigue-detection --entry-point fatigue_predict --runtime python38 --trigger-http --allow-unauthenticated```

<h3>test the function</h3>

flefteye, fmouth, frighteye == fatigue
nlefteye, nmouth, nrighteye == no fatigue

  <h2>notes: </h2>
The image must be encoded to base64 format first.

After that, you can send it by put in json format

***
# Flow
  ![flowing](https://user-images.githubusercontent.com/61272505/120897528-e2d18f80-c650-11eb-9bd2-93989b6d6e87.png)
