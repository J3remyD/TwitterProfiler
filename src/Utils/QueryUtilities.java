package Utils;

import java.util.List;
import java.util.StringJoiner;

public class QueryUtilities {

    public static String withSingleQuotes(String str) {
        return "'" + str + "'";
    }

    public static String withQuotes(String str) {
        return "\"" + str + "\"";
    }

    public static String insert(String table, List<String> col) {
        StringJoiner joiner = new StringJoiner(",");
        for(String str : col) {
            joiner.add(withSingleQuotes(str));
        }
        return "INSERT INTO " + table + " values (" + joiner.toString() + "); ";
    }
}
