Запуск программы:

Способ номер 1:
chmod 777 ./start.sh
./start.sh

Способ номер 2:
Создайте папку target
mkdir target
Компиляция:
javac -d target ./src/java/edu/school21/printer/app/Program.java ./src/java/edu/school21/printer/logic/ASCII_convert.java

Создание JAR архива:
jar cvmf ./src/manifest.txt ./target/images-to-chars-printer.jar -C target ./edu/school21/printer

Запуск:
java -jar ./target/images-to-chars-printer.jar ./src/resources/it.bmp 0 .

При необходимости замены аргументов, замените их либо в файле start.sh, либо введите вручную команды компиляции и запуска.

Аргументы принимаемые программой:
1) Полный путь к картинке на вашем компьютере.
2) Символ, который закрасит темную часть картинки.
3) Символ, который закрасит светлую часть картинки.
Программа поддерживает черно-белые картинки расширением только BMP.

* В папке resources находятся файлы, которые можно подать в качестве аргументов.
* Поддерживаются только черно-белые файлы формата bmp, 16x16 Pixels.