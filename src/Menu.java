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

	/**
	 * Muestra el menú principal y permite elegir una opción.
	 *
	 * @return La opción elegida por el usuario. Valor entero entre 0 y 3.
	 */
	public int mainMenu() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		boolean validOption = false;
		do {
			System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃       MENU PRINCIPAL      ┃");
			System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
			System.out.println("┃  1 -  Base de datos  - 1  ┃");
			System.out.println("┃  2 -     Selects     - 2  ┃");
			System.out.println("┃  3 -     Updates     - 3  ┃");
			System.out.println("┃  4 -     Deletes     - 4  ┃");
			System.out.println("┃  0 -      Salir      - 0  ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if(option >= 0 && option <= 4) {
					validOption = true;
				} else {
					System.out.println("ERROR - Opción no válida, por favor selecciona una opción entre 0 y 4");
				}
			} catch (Exception e) {
				System.out.println("ERROR - Opción no válida, por favor introduce un número entero");
			}

		} while (!validOption);
		return option;
	}

	/**
	 * Muestra el menú del control de la base de datos y permite elegir una opción.
	 *
	 * @return La opción elegida por el usuario.
	 */
	public int menuDatabase(Connection conn, Statement st) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {
			System.out.println("\n┌───────────────────────────┐");
			System.out.println("│       MENU DATABASE       │");
			System.out.println("├───────────────────────────┤");
			System.out.println("│  1 -      Crear      - 1  │");
			System.out.println("│  2 -      Borrar     - 2  │");
			System.out.println("│  3 -     Rellenar    - 3  │");
			System.out.println("│  0 -      Cerrar     - 0  │");
			System.out.println("└───────────────────────────┘");
			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 3) {
					switch (option){
						case 1:
							Tables.createAllTables(st);
							break;
						case 2:
							Tables.deleteAllTables(st);
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

	/**
	 * Muestra el menú del control de las funciones de selects y permite elegir una opción.
	 *
	 * @return La opción elegida por el usuario.
	 */
	public int menuSelects(Connection conn) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {
			System.out.println("\n┌───────────────────────────┐");
			System.out.println("│       MENU  SELECTS       │");
			System.out.println("├───────────────────────────┤");
			System.out.println("│  1 -      Tablas     - 1  │");
			System.out.println("│  2 -    Por texto    - 2  │");
			System.out.println("│  3 -   Lanzamiento   - 3  │");
			System.out.println("│  0 -      Cerrar     - 0  │");
			System.out.println("└───────────────────────────┘");
			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 3) {
					switch (option){
						case 1:
							Tables.selectInTable(conn,0);
							break;
						case 2:
							Tables.searchByText(conn);
							break;
						case 3:
							Tables.searchAllOf(conn);
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

	/**
	 * Muestra el menú del control de las funciones de updates y permite elegir una opción.
	 *
	 * @return La opción elegida por el usuario.
	 */
	public int menuUpdates(Connection conn) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {

			System.out.println("\n┌───────────────────────────┐");
			System.out.println("│       MENU  UPDATES       │");
			System.out.println("├───────────────────────────┤");
			System.out.println("│  1 -      Tablas     - 1  │");
			System.out.println("│  2 -   Condiciones   - 2  │");
			System.out.println("│  0 -      Cerrar     - 0  │");
			System.out.println("└───────────────────────────┘");
			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 2) {
					switch (option){
						case 1:
							Tables.updateInTable(conn);
							break;
						case 2:
							Tables.updateLaunchBy(conn);
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

	/**
	 * Muestra el menú del control de las funciones de deletes y permite elegir una opción.
	 *
	 * @return La opción elegida por el usuario.
	 */
	public int menuDeletes(Connection conn) throws SQLException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		boolean cerrarSubmenu = false;
		while (!cerrarSubmenu) {

			System.out.println("\n┌───────────────────────────┐");
			System.out.println("│       MENU  DELETES       │");
			System.out.println("├───────────────────────────┤");
			System.out.println("│  1 -      Tablas     - 1  │");
			System.out.println("│  2 -   Condiciones   - 2  │");
			System.out.println("│  0 -      Cerrar     - 0  │");
			System.out.println("└───────────────────────────┘");
			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 0 && option <= 2) {
					switch (option){
						case 1:
							Tables.deleteInTable(conn);
							break;
						case 2:
							Tables.deleteLaunchBy(conn);
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

	/**
	 * Función para cerrar el menú principal
	 */
	public void close(){
		close = true;
	}
}