import sys
import currencyapicom
import json  # format the result as a JSON string

# Initialize the client with your API key
client = currencyapicom.Client('cur_live_v0DKfMohFVghIulj5F1ev2hfbJWcrkEGjBVGkAyU')

with open("base_currency.txt", "r") as file:
    base_currency = file.read()  

def change_base_currency(new_base):
    base_currency = new_base

def get_latest_rates():
    try:
        result = client.latest(base_currency)
        with open("latest_data.txt", "w") as file:
            file.write(json.dumps(result, indent=4))
        print("Data successfully update in latest_data.txt.") 
    except Exception as e:
        print(f"An error occurred: {e}")

#Date to retrieve historical rates from (format: 2021-12-31)
def get_historical_rates(date):
    try:
        result = client.historical(date,base_currency)
        with open("historical_data.txt", "w") as file:
            file.write(json.dumps(result, indent=4))
        print("Data saved successfully to historical_data.txt.") 
    except Exception as e:
        print(f"An error occurred: {e}")

#Datetime for the start of your requested range (format: 2022-12-01T00:00:00Z)        
#The accuracy you want to receive. Possible Values: day, hour, quarter_hour, minute Default: da
def get_range_of_rates(start_date,end_date,accuracy):
    try:
        result = client.range(start_date,end_date,accuracy,base_currency,)
        with open("range_of_rates.txt", "w") as file:
            file.write(json.dumps(result, indent=4))
        print("Data saved successfully to range_of_rates.txt.") 
    except Exception as e:
        print(f"An error occurred: {e}")      
        
        
if __name__ == "__main__":
    function = sys.argv[1]
    if function=="1":
        new_base_curr = sys.argv[2]
        change_base_currency(new_base_curr)
        with open("base_currency.txt", "w") as file:
            file.write(new_base_curr)
        print("The new base currency is ",new_base_curr) 
    elif function=="2":
        get_latest_rates()
    elif function=="3":
        rate_for_specified_date = sys.agrv[2]
        get_historical_rates(rate_for_specified_date)      
    else:
        print("Something went wrong")

