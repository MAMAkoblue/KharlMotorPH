package util;

public class StringUtils {
    public static String nullToEmpty(String str) {
        return str == null ? "" : str;
    }

    public static String nullToEmpty(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }
}
