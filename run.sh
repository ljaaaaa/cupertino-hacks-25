#!/bin/sh
#Confirm the compilation was sucessful
if javac -d compiled *.java
then
    echo "Running Scraps To Crafts Program"
    cd compiled
    java Run
fi