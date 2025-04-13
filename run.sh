#!/bin/sh
#Confirm the compilation was sucessful
if javac -d compiled *.java
then
    cd compiled
    java Run
fi