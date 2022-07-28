import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args)  {

        Scanner input = new Scanner(System.in);

        // email
        System.out.println("Czy posiadasz już konto w serwisie ?: (Tak/Nie) ");
        String xPytanie = input.nextLine();

        if (xPytanie.equalsIgnoreCase("Tak"))
        {
            System.out.println("Zaloguj się: ");
            Login loginObject = new Login();
            loginObject.simpleLogin();
        }
        else if (xPytanie.equalsIgnoreCase("Nie"))
        {
            System.out.println("Zarejestruj się: ");
            Register registerObject = new Register();
            registerObject.simpleRegister();
        }
        else
        {
            System.out.println("Nieznana odpowiedz, spróbuj jeszcze raz.");
        }


    }

}