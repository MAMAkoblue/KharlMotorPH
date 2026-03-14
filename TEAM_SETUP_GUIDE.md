# Team Setup Guide - KharlMotorPH Project

## 🚨 **Critical Issue: Libraries Not Committed to Git**

The problem is that **JAR libraries are not included in Git** by default, so your team members don't have the required files!

## 📋 **Required Libraries (Missing from Git)**

Your team needs these files in the `lib/` directory:

```
lib/
├── sqlite-jdbc.jar (13.6 MB) ❌ MISSING
├── JCalendar/
│   └── jcalendar-1.4.jar (165 KB) ❌ MISSING  
├── absolutelayout/
│   └── AbsoluteLayout.jar (17 KB) ❌ MISSING
├── opencsv-3.8.jar (64 KB) ❌ MISSING
├── slf4j-api.jar (68 KB) ❌ MISSING
└── slf4j-simple.jar (15 KB) ❌ MISSING
```

## 🔧 **SOLUTIONS**

### **Option 1: Add Libraries to Git (Recommended)**

#### **Step 1: Create .gitignore Update**
Create/update `.gitignore` to include JAR files:
```gitignore
# Keep existing .gitignore content
# Add these lines to ALLOW JAR files in lib/
!lib/*.jar
!lib/**/*.jar
```

#### **Step 2: Add Libraries to Git**
```bash
git add lib/
git commit -m "Add required JAR libraries for team"
git push
```

### **Option 2: Download Script for Team**

Create `setup-libs.bat` for your team:
```batch
@echo off
echo Downloading required libraries...

mkdir lib\JCalendar 2>nul
mkdir lib\absolutelayout 2>nul

echo Downloading SQLite JDBC...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar' -OutFile 'lib/sqlite-jdbc.jar'"

echo Downloading JCalendar...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar' -OutFile 'lib/JCalendar/jcalendar-1.4.jar'"

echo Downloading AbsoluteLayout...
powershell -Command "Invoke-WebRequest -Uri 'https://github.com/netbeans/apichanges/raw/master/javadoc/org-netbeans-lib-awtextra.jar' -OutFile 'lib/absolutelayout/AbsoluteLayout.jar'"

echo Downloading OpenCSV...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/com/opencsv/opencsv/3.8/opencsv-3.8.jar' -OutFile 'lib/opencsv-3.8.jar'"

echo Downloading SLF4J...
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar' -OutFile 'lib/slf4j-api.jar'"
powershell -Command "Invoke-WebRequest -Uri 'https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar' -OutFile 'lib/slf4j-simple.jar'"

echo Setup complete!
pause
```

### **Option 3: Maven Dependencies (Professional Solution)**

Update `nbproject/project.properties` to use Maven:
```properties
# Add Maven repository
javac.classpath=\
    https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar;\
    https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar;\
    https://repo1.maven.org/maven2/com/opencsv/opencsv/3.8/opencsv-3.8.jar;\
    https://repo1.maven.org/maven2/org/slf4j/slf4j-api/1.7.36/slf4j-api-1.7.36.jar;\
    https://repo1.maven.org/maven2/org/slf4j/slf4j-simple/1.7.36/slf4j-simple-1.7.36.jar
```

## 🎯 **Immediate Fix for Your Team**

### **For Each Team Member:**

1. **Download the libraries manually:**
   - **SQLite JDBC:** https://repo1.maven.org/maven2/org/xerial/sqlite-jdbc/3.45.1.0/sqlite-jdbc-3.45.1.0.jar
   - **JCalendar:** https://repo1.maven.org/maven2/com/toedter/jcalendar/1.4/jcalendar-1.4.jar
   - **AbsoluteLayout:** https://github.com/netbeans/apichanges/raw/master/javadoc/org-netbeans-lib-awtextra.jar

2. **Create the directory structure:**
   ```
   project/
   └── lib/
       ├── sqlite-jdbc.jar
       ├── JCalendar/
       │   └── jcalendar-1.4.jar
       └── absolutelayout/
           └── AbsoluteLayout.jar
   ```

3. **In NetBeans:**
   - Right-click project → Properties → Libraries
   - Click "Add JAR/Folder"
   - Add all the downloaded JAR files

## 🔍 **How to Verify the Fix**

After team members have the libraries, they should see:
```bash
# Test this command (should work without errors)
java -cp "build/classes;lib/sqlite-jdbc.jar;lib/JCalendar/jcalendar-1.4.jar;lib/absolutelayout/AbsoluteLayout.jar;..." loginandregister.LoginAndRegister
```

## 📧 **Instructions for Your Team**

**Send this to your team members:**

> "Hi team, the project requires some JAR libraries that aren't in Git. Please:
> 1. Create a `lib/` folder in your project
> 2. Download these JAR files: [provide download links]
> 3. In NetBeans, add them to project libraries
> 4. Clean and build the project
> Let me know if you need help!"

---

**Root Cause:** Libraries are local to your machine only  
**Solution:** Distribute libraries to all team members
