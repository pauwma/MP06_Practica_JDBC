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
			} catch (Exception e) {
				System.out.println("ERROR - Opción no válida");
				e.printStackTrace();
			}

		} while (close);

		return option;
	}

	public void close(){
		close = true;
	}
}
