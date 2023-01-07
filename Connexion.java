package base;
import java.sql.*;

public class Connexion{
    String username;
    String password;

    public String getPassword() {
        return password;
    }
    public String getUsername() {
        return username;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public Connexion(){}

    public Connexion(String a, String b){
        setPassword(b);
        setUsername(a);
    }

    public static Connection getConnection() throws Exception{
        Connection con = null;
        Class.forName("oracle.jdbc.driver.OracleDriver");  
        con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","star","star");  
        return con;
    }


}