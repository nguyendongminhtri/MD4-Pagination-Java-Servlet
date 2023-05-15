package rikkei.academy.config;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class ConnectMySQL {
    private static Connection connection;
    private static final String URL = System.getenv("URL");
    private static final String USER = System.getenv("USER");
    private static final String PASS = System.getenv("PASS");
    public static Connection getConnection(){
        try {
            System.out.println("URL --->"+URL);
            System.out.println("USER --->"+USER);
            System.out.println("PASS -->"+PASS);
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(URL,USER,PASS);
            System.out.println("KET NOI THANH CONG");
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("KET NOI THAT BAI");
            throw new RuntimeException(e);
        }
        return connection;
    }

}
