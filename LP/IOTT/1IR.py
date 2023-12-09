import RPi.GPIO as GPIO
import time
GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)
GPIO.setup(33, GPIO.IN)
GPIO.setup(38, GPIO.OUT)
while True:
    i = GPIO.input(33)
    if i==1 :
        print("OBSTACLE")
        GPIO.output(38, GPIO.HIGH)
    else:
        print("NO OBSTACLE")
        GPIO.output(38, GPIO.LOW)
    time.sleep(1)        