import RPi.GPIO as GPIO
import time
import datetime
import http.client, urllib.request, urllib.parse;
key = ''
GPIO.setmode(GPIO.BOARD)
GPIO.setwarnings(False)

dPin = dht11.DHT11(pin=5)

while True:    
    t = dPin.read()

    if t.is_valid():
        print('Last input : ' + str(datetime.datetime.now()))
        print("Temperature : ", t.temperature)
        print("Humidity : ", t.humidity)

        params = urllib.parse.urlencode({'feild':t.temperature, 'key': key})

        headers={"Content.type":"application/x-www-from-urlencoded","Accept":"text/plain"}

        con=http.client.HTTPConnection("api.thingspeak.com")

        con.request("POST","/update",params,headers)

        response = con.getresponse()

        print(response.status,response.reason)
        data=response.read()
        
        con.close()
    
    time.sleep(1)