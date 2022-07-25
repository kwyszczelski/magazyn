import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
            String url="jdbc:mysql://localhost:3306/magazyn";
            String username="root";
            String password="";

            Scanner input = new Scanner(System.in);
            // imie
            System.out.println("Podaj swoje imię");
            String imie = input.nextLine();
            // Nazwisko
            System.out.println("Podaj swoje nazwisko");
            String nazwisko = input.nextLine();
            // Data urodzenia
            System.out.println("Podaj swoją datę urodzin");
            String dat_uro = input.nextLine();
            // Nr tel
            System.out.println("Podaj nr tel");
            String nr_tel = input.nextLine();
            // Dzial
            System.out.println("Podaj dział w którym pracujesz");
            String dzial = input.nextLine();

            System.out.println( "Imie: "+ imie + " Nazwisko: " + nazwisko+ " Data Urodzenia:  "+ dat_uro  + " Nr Telefonu: " + nr_tel+ " Dział: " + dzial);
            System.out.println("Czy podane przez ciebie dane są prawidłowe?: (Tak/Nie) ");
            String check = input.nextLine();

            //if czy sie zgadza
        if (check.equals("Tak"))
        {
            try
            {
                Class.forName("com.mysql.cj.jdbc.Driver");

                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                String insertStr = "INSERT INTO  employees VALUES (?, ?,?,?,?,?)";
                try (PreparedStatement pst = connection.prepareStatement(insertStr))
                {
                    pst.setString(1, null);
                    pst.setString(2,imie);
                    pst.setString(3,nazwisko);
                    pst.setString(4,null);
                    pst.setString(5,nr_tel);
                    pst.setString(6,dzial);
                    pst.executeUpdate();
                }
                catch (Exception e)
                {
                    System.out.println("Error");
                }



                //statement.executeUpdate("Insert INTO employees VALUES (null, imie, nazwisko, null, nr_tel, dzial )");

                /*System.out.println("Wszystkie dane z bazy: ");
                ResultSet resultSet=statement.executeQuery("select * from employees");
                while(resultSet.next())
                {
                    System.out.println(resultSet.getInt(1) + " "+ resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getDate(4) + " " + resultSet.getString(5) + " " + resultSet.getString(6));
                }

                 */


                connection.close();

            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        else if (check.equals("Nie"))
        {
            System.out.println("Spróbuj jeszcze raz");
        }
        else
        {
            System.out.println("Komenda nie została rozpoznana, spróbuj ponownie");
        }


    }
}
