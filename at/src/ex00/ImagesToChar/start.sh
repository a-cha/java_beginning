#!/bin/bash
set -e
mkdir target
javac -d target ./src/java/edu/school21/printer/app/Program.java ./src/java/edu/school21/printer/logic/ASCII_convert.java
java -cp target edu.school21.printer.app.Program ./resources/it.bmp 0 .
