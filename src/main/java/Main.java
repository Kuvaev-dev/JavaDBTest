import java.sql.*;

public class Main {
    public static String connectionUrl = "jdbc:sqlserver://SQL5105.site4now.net;databaseName=db_a7f0d3_testdb;user=a7f0d3_testdb;password=qwerty009";

    public static void main(String[] args) {

        try {
            // Load SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to SQL Server ... ");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                System.out.println("Done.");
                String sql = "SELECT Id, Name FROM Test;";
                try (Statement statement = connection.createStatement();
                    ResultSet resultSet = statement.executeQuery(sql)) {
                    while (resultSet.next()) {
                        System.out.println(
                                resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
                } catch (Exception e) {
                    System.out.println();
                    e.printStackTrace();
                }
                // Create a Table and insert some sample data
                System.out.print("Creating sample table with data, press ENTER to continue...");
                System.in.read();
                sql = new StringBuilder().append("USE SampleDB; ").append("CREATE TABLE Test ( ")
                        .append(" Id INT IDENTITY(1,1) NOT NULL PRIMARY KEY, ").append(" Name NVARCHAR(50), ")
                        .append(" Location NVARCHAR(50) ").append("); ")
                        .append("INSERT INTO Employees (Name, Location) VALUES ").append("(N'Jared', N'Australia'), ")
                        .append("(N'Nikita', N'India'), ").append("(N'Tom', N'Germany'); ").toString();
                try (Statement statement = connection.createStatement()) {
                    statement.executeUpdate(sql);
                    System.out.println("Done.");
                } catch (Exception e) {
                    System.out.println();
                    e.printStackTrace();
                }

                Insert(4,"Vasya");
                Update("Vasya");
                Select();
                Delete("Vasya");

                connection.close();
                System.out.println("All done.");
            } catch (Exception e) {
                System.out.println();
                e.printStackTrace();
            }
        } catch (Exception e) {
                System.out.println();
                e.printStackTrace();
        }
    }

    public static void Select() {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            // READ demo
            System.out.print("Reading data from table, press ENTER to continue...");
            System.in.read();
            String sql = "SELECT Id, Name, Location FROM Test;";
            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    System.out.println(
                            resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                }
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void Update(String userToUpdate) {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            // UPDATE demo
            System.out.print("Updating 'Location' for user '" + userToUpdate + "', press ENTER to continue...");
            System.in.read();
            String sql = "UPDATE Test SET Location = N'United States' WHERE Name = ?";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userToUpdate);
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " row(s) updated");
            } catch (Exception e) {
                System.out.println();
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void Delete(String userToDelete) {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            // DELETE demo
            System.out.print("Deleting user '" + userToDelete + "', press ENTER to continue...");
            System.in.read();
            String sql = "DELETE FROM Test WHERE Name = ?;";
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(1, userToDelete);
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " row(s) deleted");
            } catch (Exception e) {
                System.out.println();
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }

    public static void Insert(Integer index, String data) {
        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            // INSERT demo
            System.out.print("Inserting a new row into table, press ENTER to continue...");
            System.in.read();
            String sql = new StringBuilder().append("INSERT Test (Name, Location) ").append("VALUES (?, ?);")
                    .toString();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setString(index, data);
                statement.setString(index, data);
                int rowsAffected = statement.executeUpdate();
                System.out.println(rowsAffected + " row(s) inserted");
            } catch (Exception e) {
                System.out.println();
                e.printStackTrace();
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
    }
}
