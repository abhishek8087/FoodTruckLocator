# FoodTruckLocator

The code implements a command line application to list the Truck applicant name and address depending on the current time.
It basically validates current time to be between start and end time for a particular truck.
The output is sorted by applicant name.

To build the project just go to pom.xml directorty and run mven clean install.

It generates a FoodTruckLocator.jar in target directory.

You can download latest jar from https://github.com/abhishek8087/FoodTruckLocator/blob/master/target/FoodTruckLocator.jar

Sample run :

C:\AA\Code\FoodTruckLocator\target>java -jar FoodTruckLocator.jar
***** Food Trucks Available now ******
Name || Address
San Francisco's Hometown Creamery || 281 GEARY ST
San Francisco's Hometown Creamery || 281 GEARY ST
San Francisco's Hometown Creamery || 281 GEARY ST
Treats by the Bay LLC || 1 MONTGOMERY ST
Treats by the Bay LLC || 1 MONTGOMERY ST
Treats by the Bay LLC || 1 MONTGOMERY ST
Treats by the Bay LLC || 120 ELLIS ST
Treats by the Bay LLC || 120 ELLIS ST
Treats by the Bay LLC || 120 ELLIS ST
Treats by the Bay LLC || 1550 04TH ST
 ***** 10 of 13 *****
--------------------Please choose from below options---------------------------
1. next  3. refresh 4. exit
-------------------------------------------------------------------------------
####################################################################
Your choice (Should be one of above mentioned options) :
1
Name || Address
Treats by the Bay LLC || 1550 04TH ST
Treats by the Bay LLC || 1550 04TH ST
Wu Wei LLC dba MoBowl || 370 DRUMM ST
 ***** 13 of 13 *****
--------------------Please choose from below options---------------------------
2. previous 3. refresh 4. exit
-------------------------------------------------------------------------------
####################################################################
Your choice (Should be one of above mentioned options) :
4
 
