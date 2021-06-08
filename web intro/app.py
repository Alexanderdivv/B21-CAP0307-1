# from flask import Flask, render_template, url_for

# app = Flask(__name__)

# @app.route('/')
# def index():
# 	return render_template("index.html")

from flask import Flask, render_template, url_for, request
from werkzeug.utils import secure_filename
import os

app = Flask(__name__)

app.config['UPLOAD_FOLDER'] = 'upload/'

@app.route('/')
def index():
	return render_template("index.html")

@app.route('/', methods = ['GET', 'POST'])
def upload_file():
   if request.method == 'POST':
      f = request.files['file']
      f.save(os.path.join(app.config['UPLOAD_FOLDER'],secure_filename(f.filename)))
      return 'file uploaded successfully'

if __name__ == '__main__':
	app.run(debug = True)
