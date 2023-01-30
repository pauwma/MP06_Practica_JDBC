import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
			System.out.println("┃  1. Borrar todo            ┃");
			System.out.println("┃  2. Crear todo             ┃");
			System.out.println("┃  3. Rellenar tablas        ┃");
			System.out.println("┃  4. Consultar              ┃");
			System.out.println("┃  5. Modificar              ┃");
			System.out.println("┃  6.                        ┃");
			System.out.println("┃  0.                        ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

			System.out.print("Elige una opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if(option >= 0 && option <= 6) {
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

	public int menuSelects() {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int option = 0;
		while (option != 5) {
			System.out.println("\n┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃ 1. Agregar nueva misión      ┃");
			System.out.println("┃ 2. Modificar misión existente ┃");
			System.out.println("┃ 3. Eliminar misión           ┃");
			System.out.println("┃ 4. Ver todas las misiones    ┃");
			System.out.println("┃ 5. Salir                     ┃");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("Ingrese opción: ");
			try {
				option = Integer.parseInt(br.readLine());
				if (option >= 1 && option <= 5) {
					return option;
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
