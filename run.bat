@echo off
echo Building KharlMotorPH Payroll System...

REM Create build directories
if not exist build\classes mkdir build\classes

REM Compile all Java files with classpath
echo Compiling source files...
javac -cp "lib\*;lib\absolutelayout\AbsoluteLayout.jar;lib\JCalendar\jcalendar-1.4.jar" -d build\classes src\loginandregister\*.java src\data\*.java src\ui\*.java src\org\mindrot\jbcrypt\*.java src\util\*.java src\service\*.java src\kharlmotorph\*.java

if %ERRORLEVEL% NEQ 0 (
    echo Compilation failed!
    pause
    exit /b 1
)

echo Compilation successful!

REM Run the application
echo Starting application...
java -cp "build\classes;lib\*;lib\absolutelayout\AbsoluteLayout.jar;lib\JCalendar\jcalendar-1.4.jar" loginandregister.LoginAndRegister

pause
