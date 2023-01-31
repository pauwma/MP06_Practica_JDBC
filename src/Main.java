import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class Main {

	public static void main(String[] args) throws IOException, SQLException, ParseException, ClassNotFoundException {
		Menu menu = new Menu();

		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();

		Class.forName( "org.postgresql.Driver" );

		//Obrim la connexió amb la base de dades anomenada dbMail
		//utilitzant el driver de postgreSQL
		//Ens connectem amb "usuari" amb contrassenya "usuari"
		String dbURL="jdbc:postgresql://127.0.0.1:5432/jdbc";
		Connection conn = DriverManager.getConnection( dbURL, "pauwma","P2506");
		Statement st = conn.createStatement();

		//Tanquem la connexió. No és estríctament necessari, però és un bon hàbit!

		int option = menu.mainMenu();
		while (option > 0 && option <= 3) {
			switch (option) {
			case 1:
				menu.menuDatabase(conn, st);
				break;
			case 2:
				menu.menuSelects(conn);
				break;
			case 3:
				menu.menuUpdates(conn);
				break;
			case 0:
				menu.close();
				break;

			}
			option = menu.mainMenu();
		}

	}

}
