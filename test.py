import json
import currencyapicom

client = currencyapicom.Client('cur_live_v0DKfMohFVghIulj5F1ev2hfbJWcrkEGjBVGkAyU')
try:
    result = client.historical("2024-01-01","ZAR")
    with open("historical_data.txt", "w") as file:
        file.write(json.dumps(result, indent=4))
    print("Data saved successfully to historical_data.txt.") 
except Exception as e:
    print(f"An error occurred: {e}")
