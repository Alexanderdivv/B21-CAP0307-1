from flask import Flask, redirect, url_for, render_template, request
from werkzeug.utils import secure_filename
import base64
import requests
import urllib.parse
import urllib.request
from PIL import Image
import json
from base64 import b64encode

app = Flask (__name__)

# @app.route("/")
# def  home():
# 	return render_template("index.html")

@app.route("/", methods=["POST", "GET"])
def login():
	if request.method == "POST":
		foto = request.files["foto"]
		# data = io.BytesIO()
		# foto.save(data, "JPEG")
		# encoded_img_data = base64.b64encode(data.getvalue())
		image_string = base64.b64encode(foto.read())
		ENCODING = 'utf-8'
		base64_string = image_string.decode(ENCODING)
		# foto.save(secure_filename(foto.filename))
		# encoded_string = base64.b64encode(foto.read())

		# send to GCG
		data = requests.post("https://asia-southeast2-face-to-face-fatigue.cloudfunctions.net/fatigue_detection?key=AIzaSyBBwSxFg5ENmFHTbIt_ytbwBEgSjuegLX8", json={
			"gambar": base64_string
			})

		data_real = data.text


		# uploaded_file = request.files['file']
		# imagefile = flask.request.files.get('foto', '')
		return redirect(url_for("user", usr=data_real))
	else:
		return render_template("login.html")

@app.route("/<usr>")
def user(usr):
	if usr == "flefteye" or usr == "frighteye" or usr == "fmouth":
		hasil = "Fatigue"
	else:
		hasil = "Not Fatigue"
	return render_template("login.html", results = usr, hasil = hasil)
	# return f"<h1>{usr}</h1>"

if __name__ == "__main__":
	app.run(debug=True)