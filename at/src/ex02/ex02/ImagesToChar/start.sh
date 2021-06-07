#!/bin/bash

rm -rf target
mkdir target
cp -r src/resources/ target
jar xvf lib/jcommander-1.69.jar && jar xvf lib/JCDP-4.0.2.jar && rm -rf META-INF
javac -d target ./src/java/edu/school21/printer/app/Program.java ./src/java/edu/school21/printer/logic/Arguments.java ./src/java/edu/school21/printer/logic/ASCII_convert.java && mv com/ target/
jar cvmf src/manifest.txt target/images-to-chars-printer.jar -C target ./
java -jar target/images-to-chars-printer.jar -w RED --black GREEN