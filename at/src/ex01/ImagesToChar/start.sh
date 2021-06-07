#!/bin/bash

mkdir target
javac -d target ./src/java/edu/school21/printer/app/Program.java ./src/java/edu/school21/printer/logic/ASCII_convert.java
jar cvmf ./src/manifest.txt ./target/images-to-chars-printer.jar -C target ./edu/school21/printer
java -jar ./target/images-to-chars-printer.jar ./src/resources/it.bmp 0 .
