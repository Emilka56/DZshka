import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.util.Scanner;

public class Main {

    private static final String DB_USERNAME = "postgres";
    private static final String DB_PASSWORD = "5432";
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static void main(String[] args) throws Exception {
        Connection connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        Statement statement1 = connection.createStatement();
        ResultSet result = statement.executeQuery("select * from driver");
        ResultSet result1 = statement1.executeQuery("select * from driver where age < 21");
        while (result.next()) {
            System.out.println(result.getInt("id") + " " + result.getString("name"));
        }
        System.out.println();
        System.out.println("select users where age < 21");
        while (result1.next()) {
            System.out.println(result1.getInt("id") + " " + result1.getString("name")
                    + " " + result1.getInt("age"));
        }

        Scanner scanner = new Scanner(System.in);
        int affectedRows = 0;
        String sqlInsertUser = "insert into driver(name, surname, age) " +
                "values (?, ?, ?), (?, ?, ?) , (?, ?, ?) , (?, ?, ?) , (?, ?, ?) , (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sqlInsertUser);
        try {
            for (int i = 0; i < 6; i++) {
                String firstName = "ivanS " + i;
                String secondName = "ivanovichS " + i;
                int age = 19 + i;

                preparedStatement.setString(1 + i * 3, firstName);
                preparedStatement.setString(2 + i * 3, secondName);
                preparedStatement.setInt(3 + i * 3, age);
                affectedRows++;
            }
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            System.out.println("такой пользователь уже создан");
        }


    }
}