#!/bin/bash
#downloads zipped archive
wget http://download1519.mediafire.com/yhep929vfu1g/0ljx9s382dmrmyt/archive.zip
unzip archive.zip
#removes zip file since it is no longer needed
rm archive.zip
#Debugging Options:
#head card_data.csv
#wc -l card_data.csv
#Extracts the 1st column of the card_data.csv to a new file named names.txt
awk -F "\"*,\"*" '{print $1}' card_data.csv > names.txt
#Removes the 1st row of names. It is the table header, it is no longer needed since the file name is the same as it was called...
tail -n +2 names.txt > names.txt.tmp && mv names.txt.tmp names.txt
#sorts the names.txt file alphabetically and stores it into the names.txt file
sort names.txt > names.txt.tmp && mv names.txt.tmp names.txt
