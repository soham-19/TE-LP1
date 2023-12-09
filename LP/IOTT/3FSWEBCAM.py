import RPi.GPIO as GPIO
import time
import os
import sys

os.system('clear')

GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)

GPIO.setup(33, GPIO.IN)

while True:
    i = GPIO.input(33)
    if i==1:
        print("OBSTACLE")
        os.system('fswebcam / home/kkwieer/desktop/abc.jpg')
    else:
        print("No OBSTACLE")