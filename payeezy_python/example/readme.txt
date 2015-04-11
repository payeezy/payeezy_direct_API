
# This is version payeezy 1.0 with which you could use transactions into your project using following steps
# 
# Download and install Requests 2.3 from the link below which is a dependancy for Payeezy.
# https://github.com/kennethreitz/requests
# 
# 1. unzip the folder and open cmd / terminal
# 2. use 'cd' command to payeezy folder
#
# 3. run 'python setup.py build'
# 4. run 'python setup.py install'
# 5. Now you should be able to run example.py using ' python example.py'
#
# Using payeezy with Django or any other python script support platform
# 
# use statement 'import payeezy' to be able to call the methods in transactions#
#
# Setting attributes:
#
# payeezy.apikey= ""
# payeezy.apisecret= ""
# payeezy.token = ""
# payeezy.url = ""




This is the python module for payeezy-client.

Allows following transactions:

- authorize
- purchase
- capture
- void
- refund

Requires following attributes to be set before calling any methods:

apikey	  #developer credentials for first api
apisecret #developer credentials for first api

# For HTTPBasicAuth 
 
userID 
password 