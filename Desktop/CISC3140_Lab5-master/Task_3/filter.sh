#!/bin/bash
#Provides a variable where you can enter a URL, if not an error will occur in the Terminal
URL=${1?Error: No URL of an Image Provided!}
#Applies a type of grayscale filter or turns the picture into a black & white picture
convert -charcoal 30 $URL screenshot.png 
