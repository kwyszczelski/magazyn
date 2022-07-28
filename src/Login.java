import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class Login {
    String url = "jdbc:mysql://localhost:3306/magazyn";
    String username = "root";
    String password = "";

    public void simpleLogin() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();


            Scanner input = new Scanner(System.in);

            // email
            System.out.println("Podaj email: ");
            String email = input.nextLine();

            // haslo
            System.out.println("Podaj swoje haslo");
            String haslo = input.nextLine();

            ResultSet resultQuery = statement.executeQuery("select * from employees where email='" + email +"' && passwd='" + haslo + "'");

            if (resultQuery.next() == true)
            {
                System.out.println("Zalogowano pomyślnie!");
            }
            else
            {
                System.out.println("Nieprawidłowe hasło lub email spróbuj ponownie.");
            }


        }
        catch (Exception e )
        {
            System.out.println("Wystąpił problem połączenia z bazą danych. "+e);
        }

    }
}
