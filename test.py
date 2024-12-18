import currencyapicom

client = currencyapicom.Client('cur_live_v0DKfMohFVghIulj5F1ev2hfbJWcrkEGjBVGkAyU')
result = client.latest()
print(result)
