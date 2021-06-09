import requests
import urllib.parse
import urllib.request
import json


data = requests.post("https://asia-southeast2-face-to-face-fatigue.cloudfunctions.net/fatigue_detection", json={
	"gambar": "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"
})

print(data)
print(data.text)