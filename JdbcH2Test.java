import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * @author Le Van Tuan Long
 */

public class JdbcH2Test {
  static final String DB_URL = "jdbc:h2:./test2;IGNORECASE=TRUE;MODE=MySQL;AUTO_SERVER=TRUE";
  static final String USER = "sa";
  static final String PASS = "";
  static final String JDBC_DRIVER = "org.h2.Driver";
  public static void main(String[] args) {
    try (
        Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
        Statement stmt = conn.createStatement();
    ){
      Class.forName(JDBC_DRIVER);
      
      // Test queries
      String sql;
      sql = "DROP TABLE IF EXISTS user_previous_bookmark";
      stmt.executeUpdate(sql);

      sql = "CREATE TABLE user_previous_bookmark ( username VARCHAR(255) NOT NULL, type VARCHAR(255) NOT NULL, last_bookmark VARCHAR(255),entries_count INT)";
      stmt.executeUpdate(sql);

      sql = "ALTER TABLE user_previous_bookmark ADD PRIMARY KEY (username, type)";
      stmt.executeUpdate(sql);

      sql = "INSERT INTO user_previous_bookmark " + "VALUES ('test username', 'test type', 'test bookmark', 1234)";
      stmt.executeUpdate(sql);

      sql = "SELECT * FROM user_previous_bookmark";
      ResultSet rs = stmt.executeQuery(sql);

      while (rs.next()) {
        System.out.println(rs.getString("username"));
        System.out.println(rs.getString("type"));
        System.out.println(rs.getString("last_bookmark"));
        System.out.println(rs.getInt("entries_count"));
      }
    }
    catch (Exception ex) {
      ex.printStackTrace();
    }
  }
}
