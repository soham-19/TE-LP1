import RPi.GPIO as GPIO
import time
import dht11

GPIO.setwarnings(False)
GPIO.setmode(GPIO.BOARD)

GPIO.setup(33, GPIO.OUT)
dPin = dht11.DHT11(pin=5)

while True:
    t = dPin.read()

    if t.is_valid():
        print("Temperature ", t.temperature) 
        print("Humidity ", t.humidity) 

        if t.temperature >= 32:
            print("Temperature crossed threshold value")
            GPIO.output(33,GPIO.HIGH)
            time.sleep(1)
            GPIO.output(33,GPIO.LOW)