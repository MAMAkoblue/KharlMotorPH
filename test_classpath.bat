@echo off
echo Testing NetBeans Library Configuration...
echo.

echo === Testing SQLite Driver ===
java -cp "lib/sqlite-jdbc.jar" -c "import java.sql.Connection; import java.sql.DriverManager; Connection conn = DriverManager.getConnection('jdbc:sqlite:KharlMotorPH.db'); System.out.println('SQLite Driver: OK'); conn.close();" 2>nul
if %errorlevel% equ 0 (
    echo ✅ SQLite Driver: OK
) else (
    echo ❌ SQLite Driver: FAILED
)

echo.
echo === Testing AbsoluteLayout ===
java -cp "lib/absolutelayout/AbsoluteLayout.jar" -c "import org.netbeans.lib.awtextra.AbsoluteLayout; System.out.println('AbsoluteLayout: OK');" 2>nul
if %errorlevel% equ 0 (
    echo ✅ AbsoluteLayout: OK
) else (
    echo ❌ AbsoluteLayout: FAILED
)

echo.
echo === Testing Full Application ===
java -cp "build/classes;lib/sqlite-jdbc.jar;lib/JCalendar/jcalendar-1.4.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/opencsv-3.8.jar;lib/slf4j-api.jar;lib/slf4j-simple.jar" loginandregister.LoginAndRegister
if %errorlevel% equ 0 (
    echo ✅ Full Application: OK
) else (
    echo ❌ Full Application: FAILED
)

echo.
echo Test complete!
pause
