import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class JDBCDemo {
    public static void main(String[] args) {
        // 1. Define the JDBC URL, username, and password
        String jdbcURL = "jdbc:mysql://localhost:3306/mydatabase";  // Replace "mydatabase" with your database name
        String username = "root"; // Replace with your database username
        String password = "password"; // Replace with your database password

        // Initialize the connection, statement, and resultSet objects
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            // 2. Load the JDBC driver (optional in newer versions of Java)
            // Class.forName("com.mysql.cj.jdbc.Driver"); // Uncomment if needed for older versions

            // 3. Establish a connection to the database
            connection = DriverManager.getConnection(jdbcURL, username, password);
            System.out.println("Connection established successfully!");

            // 4. Create a statement object to send SQL queries to the database
            statement = connection.createStatement();

            // 5. Execute a query and get the results in a ResultSet
            String sql = "SELECT * FROM mytable";  // Replace "mytable" with your table name
            resultSet = statement.executeQuery(sql);

            // 6. Process the ResultSet
            while (resultSet.next()) {
                // Assuming the table has columns named "id" and "name"
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            // Handle SQL exception
            e.printStackTrace();
        } finally {
            // 7. Close all resources to avoid memory leaks
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
