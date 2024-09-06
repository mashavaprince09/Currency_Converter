import currencyapicom
import json  # format the result as a JSON string

# Initialize the client with your API key
client = currencyapicom.Client('cur_live_v0DKfMohFVghIulj5F1ev2hfbJWcrkEGjBVGkAyU')
base_currency = 'ZAR'


def change_base_currency(new_base):
    base_currency = new_base

def get_latest_rates():
    try:
        result = client.latest(base_currency)
        with open("latest_data.txt", "w") as file:
            file.write(json.dumps(result, indent=4))
        print("Data saved successfully to latest_data.txt.") 
    except Exception as e:
        print(f"An error occurred: {e}")

#Date to retrieve historical rates from (format: 2021-12-31)
def get_latest_rates(date):
    try:
        result = client.historical(base_currency,date)
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
    pass