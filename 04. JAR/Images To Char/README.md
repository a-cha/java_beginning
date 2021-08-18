# ASCII Converter

This application converts images to ASCII format. Works only with black & white files bmp format, 16x16 pixels resolution (examples in src/main/resources).

## Usage
To build the app you have to install Maven. Then run `mvn package` in the root of the repo (the current directory) for compilation.

After compiling, you can run the app using the command:

`java -cp target/ImagesToChar-1.0-SNAPSHOT.jar edu.school21.printer.app.Main src/main/resources/it.bmp 0 .`

Details:

- `-cp target/ImagesToChar-1.0-SNAPSHOT.jar` - path to compiled library
- `edu.school21.printer.app.Main` - main class
- `src/main/resources/it.bmp` - image to convert
- `0` - symbol for white colour
- `.` - symbol for black colour
