import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;

public class ACBMain {

	public static void main(String[] args) throws IOException, SQLException, ParseException, ClassNotFoundException {
		ACBMenu menu = new ACBMenu();

		ConnectionFactory connectionFactory = ConnectionFactory.getInstance();
		Connection c = connectionFactory.connect();


		Class.forName( "org.postgresql.Driver" );

		//Obrim la connexió amb la base de dades anomenada dbMail
		//utilitzant el driver de postgreSQL
		//Ens connectem amb "usuari" amb contrassenya "usuari"
		String dbURL="jdbc:postgresql://192.168.22.122:5432/jdbc";
		Connection conn = DriverManager.getConnection( dbURL, "sergio","password");
		Statement st = conn.createStatement();

		st.executeUpdate("INSERT INTO Jugador(idjugador, rank , wins, kills, deaths, assists, scoreround,kad,killsround, plants, firstbloods, clutches, flawless, aces) VALUES (1,'DIAMOND','4','4','4','4','4','4','4','4','4','4','4','4')");

		//Tanquem la connexió. No és estríctament necessari, però és un bon hàbit!
		conn.close();


		int option = menu.mainMenu();
		while (option > 0 && option < 12) {
			switch (option) {
			case 1:

				break;

			case 2:
				break;

			case 3:
				break;

			case 4:
				break;

			case 5:
				break;

			case 6:
				break;

			case 7:
				break;

			case 8:
				break;

			case 9:
				break;

			case 10:
				break;

			case 11:
				break;

			default:
				System.out.println("Introdueixi una de les opcions anteriors");
				break;

			}
			option = menu.mainMenu();
		}

	}

}
