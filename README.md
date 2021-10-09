# Lab_08 : OO Design and GSON
# quotes
* Use the file recentquotes.json to show random popular book quotes.
Your program should use GSON to parse the .json file. 
The app needs no functionality other than showing the quote and 
the author when it is run. The app should choose one quote 
each time it is run.

## Features 
* ##### readFromJsonFile() : 
 Reads from file and uses GSON to convert into a Java object

# Lab_09 : Web requests
update lab08 rather than using a hardcoded file of JSON data with quotes,
quotes will be grabbing from an API and display those to the user. and we 
use  https://ron-swanson-quotes.herokuapp.com/v2/quotes 

## Features
* ##### readFromAPI( String backupFile):
create http connection with https://ron-swanson-quotes.herokuapp.com/v2/quotes 
and convert the response from API to list of Object that installation from Quotes class
and write the response as json file on backupFile 
* ##### fileWriter(String filePath, Quotes quote):
 this function write the list of object quote that is created by response of API 
to file that is with filePath parameter to read from it if we have any problem
with internet connection 




