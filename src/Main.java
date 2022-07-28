import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ParseException {
        String url = "jdbc:mysql://localhost:3306/magazyn";
        String username = "root";
        String password = "";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            Scanner input = new Scanner(System.in);
            // REGISTER

            // imie
            System.out.println("Podaj swoje imię");
            String imie = input.nextLine();
            // Nazwisko
            System.out.println("Podaj swoje nazwisko");
            String nazwisko = input.nextLine();
            // email
            System.out.println("Podaj adres e-mail");
            String email = input.nextLine();

            //czy email jest już w bazie

            ResultSet resultSet = statement.executeQuery("select * from employees where email='"+ email +"'");

            if (resultSet.next()==false)
            {

                if(email.contains("@") && email.contains("."))
                {
                    // Data urodzenia
                    System.out.println("Podaj swoją datę urodzenia (R-M-D)");
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

                    if (check.equals("Tak"))
                    {
                        String insertStr = "INSERT INTO  employees VALUES (?,?,?,?,?,?,?)";
                        try (PreparedStatement pst = connection.prepareStatement(insertStr))
                        {
                            pst.setString(1, null);
                            pst.setString(2,imie);
                            pst.setString(3,nazwisko);
                            pst.setString(4,email);
                            pst.setString(5,dat_uro);
                            pst.setString(6,nr_tel);
                            pst.setString(7,dzial);
                            pst.executeUpdate();
                            System.out.println("Zostałeś zarejestrowany!");
                        }
                        catch (Exception e)
                        {
                            System.out.println("Wystąpił problem podczas dodawania danych do bazy.");
                        }
                    }
                    else // Odpowiedz uzytkownika nieprawilowe dane
                    {
                        System.out.println("Spróbuj ponownie.");
                    }
                }
                else // Adres email nie zawiera (@, .)
                {
                    System.out.println("Podany adres, nie jest adresem email. Spróbuj ponownie");
                }
            }
            else //Email odrzucony - jest już w bazie
            {
                System.out.println("Podany email został już wykorzystany.");
            }
        }
        catch(Exception e ){
            System.out.println("Wystąpił problem w połączeniu z bazą danych." +e);
        }

    }
}