# Fix NetBeans Runtime Classpath Issues

## 🚨 Problem Identified
NetBeans shows "BUILD SUCCESSFUL" but fails at runtime with:
- `No suitable driver found for jdbc:sqlite:KharlMotorPH.db`
- `NoClassDefFoundError: org/netbeans/lib/awtextra/AbsoluteLayout`

## 🔧 Solution Steps

### **Step 1: Add Libraries in NetBeans IDE**
1. **Open NetBeans**
2. **Right-click project** → **Properties**
3. **Go to Libraries** category
4. **Click "Add JAR/Folder"** and add:
   - `lib/sqlite-jdbc.jar`
   - `lib/JCalendar/jcalendar-1.4.jar`
   - `lib/absolutelayout/AbsoluteLayout.jar`
   - `lib/opencsv-3.8.jar`
   - `lib/slf4j-api.jar`
   - `lib/slf4j-simple.jar`

### **Step 2: Verify Runtime Classpath**
1. In **Properties** → **Run**
2. Check that **VM Options** includes:
   ```
   -cp "lib/sqlite-jdbc.jar;lib/JCalendar/jcalendar-1.4.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/opencsv-3.8.jar;lib/slf4j-api.jar;lib/slf4j-simple.jar;build/classes"
   ```

### **Step 3: Alternative - Manual Classpath Fix**
If above doesn't work, **edit nbproject/project.properties**:

#### **Add these lines at the end:**
```properties
# Manual runtime classpath fix
run.classpath=\
    ${file.reference.sqlite-jdbc.jar};\
    ${file.reference.jcalendar-1.4.jar};\
    ${file.reference.opencsv-3.8.jar};\
    ${file.reference.slf4j-api.jar};\
    ${file.reference.slf4j-simple.jar};\
    ${file.reference.AbsoluteLayout.jar};\
    ${build.classes.dir}

# Debug classpath fix
debug.classpath=\
    ${run.classpath}
```

### **Step 4: Clean and Rebuild**
1. **Right-click project** → **Clean and Build**
2. **Run project** (F6)

## 🎯 Quick Test Command
To verify libraries work outside NetBeans:
```bash
java -cp "build/classes;lib/sqlite-jdbc.jar;lib/JCalendar/jcalendar-1.4.jar;lib/absolutelayout/AbsoluteLayout.jar;lib/opencsv-3.8.jar;lib/slf4j-api.jar;lib/slf4j-simple.jar" loginandregister.LoginAndRegister
```

## ✅ Expected Result
After applying these fixes:
- ✅ No SQLite driver errors
- ✅ No AbsoluteLayout errors
- ✅ Application starts successfully
- ✅ Database connects properly
- ✅ GUI displays correctly

## 🔍 Troubleshooting
If issues persist:
1. **Check NetBeans version** (should support Java 22)
2. **Verify JAR files aren't corrupted**
3. **Restart NetBeans** after adding libraries
4. **Delete NetBeans cache**: `Help → About → Clear Cache`
