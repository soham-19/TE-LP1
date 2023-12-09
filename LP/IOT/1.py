import RPi.GPIO as GPIO
import time
from time import sleep
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(33,GPIO.IN)
GPIO.setup(36, GPIO.OUT)

while True:
    i = GPIO.input(33)
    if i == 1:
        print("OBSTACLE")
        GPIO.output(36,GPIO.HIGH)
    else:
        print("NO OBSTACLE")
        GPIO.output(36,GPIO.LOW)
    time.sleep(1)