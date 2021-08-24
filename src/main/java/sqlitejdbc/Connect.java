package sqlitejdbc;

import java.sql.*;

public class Connect {
    private int ID = 0;

    /**
     * Connect to a sample database
     */
    public Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:C:/Code/sqlite/test.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
            // showMetadata(conn);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    private void showMetadata(Connection conn) throws SQLException {
        DatabaseMetaData md = conn.getMetaData();
        ResultSet rs = md.getTables(null, null, "%", null);
        while (rs.next()) {
            String tableName = rs.getString(3);
            ResultSet columns = md.getColumns(null, null, tableName, null);
            while (columns.next()) {
                String columnName = columns.getString("COLUMN_NAME");
                String datatype = columns.getString("DATA_TYPE");
                String columnsize = columns.getString("COLUMN_SIZE");
                String decimaldigits = columns.getString("DECIMAL_DIGITS");
                String isNullable = columns.getString("IS_NULLABLE");
                String is_autoIncrment = columns.getString("IS_AUTOINCREMENT");
                //Printing results
                System.out.println(columnName + "---" + datatype + "---" + columnsize + "---" + decimaldigits + "---" + isNullable + "---" + is_autoIncrment);
            }
        }
    }

    public void create(String name, String address, String city) {
        String sql = "INSERT INTO Persons(PersonID,LastName,FirstName,Address,City) VALUES(?,?,?,?,?)";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, ID++);
            pstmt.setString(2, name.split(" ")[0]);
            pstmt.setString(3, name.split(" ")[1]);
            pstmt.setString(4, address);
            pstmt.setString(4, city);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void read() {
        String sql = "SELECT * FROM Persons";

        try (Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // loop through the result set
            while (rs.next()) {
                System.out.println(rs.getString("FirstName"));
                System.out.println(rs.getString("LastName"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void update(int id, String name) {
        String sql = "UPDATE Persons SET FirstName = ? , "
                + "LastName = ? "
                + "WHERE PersonId = ?";

        try (Connection conn = this.connect();
             PreparedStatement prepareStatement = conn.prepareStatement(sql)) {

            // set the corresponding param
            prepareStatement.setString(1, name.split(" ")[0]);
            prepareStatement.setString(2, name.split(" ")[1]);
            prepareStatement.setInt(3, id);
            // update
            prepareStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM Persons WHERE PersonId = ?";

        try (Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // set the corresponding param
            pstmt.setInt(1, id);
            // execute the delete statement
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Connect c = new Connect();
        c.create("Cristiano Ronaldo", "Football Garden", "Lisbon");
        c.read();
        c.update(0, "Cristiano Ronaldo");
        c.read();
        c.delete(0);
        c.read();
    }
}
