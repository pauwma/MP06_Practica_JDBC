import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Menu {
	private int option;
	private boolean close;

	public Menu() {
		super();
	}

	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean validOption = false;
		do {
			System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃       MENU PRINCIPAL       ┃");
			System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
			System.out.println("┃  1. Menu database          ┃");
			System.out.println("┃  2. Menu selects           ┃");
			System.out.println("┃  3. Menu updates           ┃");
			System.out.println("┃  0. Salir                  ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if(option >= 0 && option <= 3) {
					validOption = true;
				} else {
					System.out.println("ERROR - Opción no válida, por favor selecciona una opción entre 0 y 6");
				}
			} catch (Exception e) {
				System.out.println("ERROR - Opción no válida, por favor introduce un número entero");
			}

		} while (!validOption);
		return option;
	}

	public int menuDatabase(Connection conn, Statement st) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {
			System.out.println("\n┌────────────────────────────┐");
			System.out.println("│       MENU DATABASE        │");
			System.out.println("├────────────────────────────┤");
			System.out.println("│  1. Crear                  │");
			System.out.println("│  2. Borrar                 │");
			System.out.println("│  2. Rellenar               │");
			System.out.println("│  0. Cerrar                 │");
			System.out.println("└────────────────────────────┘");
			System.out.print("Ingrese opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 3) {
					switch (option){
						case 1:
							Tables.createAllTables(st);
							break;
						case 2:
							Tables.createAllTables(st);
							break;
						case 3:
							Tables.insertAllData(conn);
							break;
						case 0:
							cerrarSubmenu = true;
							break;
					}
				} else {
					System.out.println("Opción inválida, por favor ingrese una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error en el formato, por favor ingrese una opción válida.");
			} catch (IOException e) {
				System.out.println("Error de entrada/salida.");
			}
		}
		return option;
	}
	public int menuSelects(Connection conn) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {
			System.out.println("\n┌────────────────────────────┐");
			System.out.println("│        MENU SELECTS        │");
			System.out.println("├────────────────────────────┤");
			System.out.println("│  1. Por tablas             │");
			System.out.println("│  2. Por contenido          │");
			System.out.println("│  0. Cerrar                 │");
			System.out.println("└────────────────────────────┘");
			System.out.print("Ingrese opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 2) {
					switch (option){
						case 1:
							Tables.selectInTable(conn);
							break;
						case 2:
							Tables.searchByText(conn);
							break;
						case 0:
							cerrarSubmenu = true;
							break;
					}
				} else {
					System.out.println("Opción inválida, por favor ingrese una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error en el formato, por favor ingrese una opción válida.");
			} catch (IOException e) {
				System.out.println("Error de entrada/salida.");
			}
		}
		return option;
	}
	public int menuUpdates(Connection conn) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {

			System.out.println("\n┌────────────────────────────┐");
			System.out.println("│        MENU UPDATES        │");
			System.out.println("├────────────────────────────┤");
			System.out.println("│  1. Por tablas             │");
			System.out.println("│  2. Por contenido          │");
			System.out.println("│  0. Cerrar                 │");
			System.out.println("└────────────────────────────┘");
			System.out.print("Ingrese opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 2) {
					switch (option){
						case 1:
							Tables.selectInTable(conn);
							break;
						case 2:
							Tables.searchByText(conn);
							break;
						case 0:
							cerrarSubmenu = true;
							break;
					}
				} else {
					System.out.println("Opción inválida, por favor ingrese una opción válida.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Error en el formato, por favor ingrese una opción válida.");
			} catch (IOException e) {
				System.out.println("Error de entrada/salida.");
			}
		}
		return option;
	}

	public void close(){
		close = true;
	}
}
