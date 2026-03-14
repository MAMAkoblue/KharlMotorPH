@echo off
echo ========================================
echo KharlMotorPH - Library Setup Script
echo ========================================
echo.

echo Creating required directories...
if not exist "lib" mkdir lib
if not exist "lib\JCalendar" mkdir lib\JCalendar
if not exist "lib\absolutelayout" mkdir lib\absolutelayout
if not exist "lib\CopyLibs" mkdir lib\CopyLibs

echo.
echo Downloading required libraries...
echo (This may take a few minutes depending on internet speed)
echo.

echo [1/6] Downloading SQLite JDBC Driver...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar' -OutFile 'lib/sqlite-jdbc.jar' -UseBasicParsing; Write-Host 'SQLite JDBC: OK' -ForegroundColor Green } catch { Write-Host 'SQLite JDBC: FAILED - Download manually' -ForegroundColor Red }"

echo [2/6] Downloading JCalendar...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar' -OutFile 'lib/JCalendar/jcalendar-1.4.jar' -UseBasicParsing; Write-Host 'JCalendar: OK' -ForegroundColor Green } catch { Write-Host 'JCalendar: FAILED - Download manually' -ForegroundColor Red }"

echo [3/6] Downloading AbsoluteLayout...
powershell -Command "try { Invoke-WebRequest -Uri 'https://github.com/netbeans/apichanges/raw/master/javadoc/org-netbeans-lib-awtextra.jar' -OutFile 'lib/absolutelayout/AbsoluteLayout.jar' -UseBasicParsing; Write-Host 'AbsoluteLayout: OK' -ForegroundColor Green } catch { Write-Host 'AbsoluteLayout: FAILED - Download manually' -ForegroundColor Red }"

echo [4/6] Downloading OpenCSV...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/opencsv/opencsv/3.8/opencsv-3.8.jar' -OutFile 'lib/opencsv-3.8.jar' -UseBasicParsing; Write-Host 'OpenCSV: OK' -ForegroundColor Green } catch { Write-Host 'OpenCSV: FAILED - Download manually' -ForegroundColor Red }"

echo [5/6] Downloading SLF4J API...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar' -OutFile 'lib/slf4j-api.jar' -UseBasicParsing; Write-Host 'SLF4J API: OK' -ForegroundColor Green } catch { Write-Host 'SLF4J API: FAILED - Download manually' -ForegroundColor Red }"

echo [6/6] Downloading SLF4J Simple...
powershell -Command "try { Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar' -OutFile 'lib/slf4j-simple.jar' -UseBasicParsing; Write-Host 'SLF4J Simple: OK' -ForegroundColor Green } catch { Write-Host 'SLF4J Simple: FAILED - Download manually' -ForegroundColor Red }"

echo.
echo ========================================
echo Setup Complete! Verifying files...
echo ========================================

echo.
echo Checking downloaded files:
if exist "lib/sqlite-jdbc.jar" (echo ✅ sqlite-jdbc.jar) else (echo ❌ sqlite-jdbc.jar - MISSING)
if exist "lib/JCalendar/jcalendar-1.4.jar" (echo ✅ jcalendar-1.4.jar) else (echo ❌ jcalendar-1.4.jar - MISSING)
if exist "lib/absolutelayout/AbsoluteLayout.jar" (echo ✅ AbsoluteLayout.jar) else (echo ❌ AbsoluteLayout.jar - MISSING)
if exist "lib/opencsv-3.8.jar" (echo ✅ opencsv-3.8.jar) else (echo ❌ opencsv-3.8.jar - MISSING)
if exist "lib/slf4j-api.jar" (echo ✅ slf4j-api.jar) else (echo ❌ slf4j-api.jar - MISSING)
if exist "lib/slf4j-simple.jar" (echo ✅ slf4j-simple.jar) else (echo ❌ slf4j-simple.jar - MISSING)

echo.
echo ========================================
echo Next Steps:
echo ========================================
echo 1. Open NetBeans
echo 2. Open the KharlMotorPH project
echo 3. Right-click project → Properties → Libraries
echo 4. Click "Add JAR/Folder"
echo 5. Add all JAR files from the lib folder
echo 6. Clean and Build the project
echo 7. Run the project (F6)
echo.

echo If any downloads failed, please download them manually from:
echo https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar
echo https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar
echo https://github.com/netbeans/apichanges/raw/master/javadoc/org-netbeans-lib-awtextra.jar
echo.

pause
