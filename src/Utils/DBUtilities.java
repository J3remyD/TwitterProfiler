package Utils;

import engine.AnalyzeTrend;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;

public final class DBUtilities {

    protected static final String TREND_TABLE = "Trends";
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/test";

    private static final String USER = "root";
    private static final String PASS = "toto";

    private DBUtilities(){}

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection(DB_URL,USER,PASS);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String insertTrend(AnalyzeTrend trend) {
        return QueryUtilities.insert(TREND_TABLE, Arrays.asList("T_" + trend.getId(), trend.getCountryName(), trend.getCity()));
    }

    public static String selectTrends() {
        return "Select * from " + TREND_TABLE;
    }
}
