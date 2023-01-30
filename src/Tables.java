import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;
import java.util.stream.Collectors;

public interface Tables {

    /**
     * Elimina todas las tablas en una base de datos relacional.
     *
     * @param st Un objeto de la clase {@code java.sql.Statement} que se utiliza para ejecutar una instrucción SQL.
     */
    public static void deleteAllTables(Statement st){
        try {
            st.execute("DROP TABLE location CASCADE;" +
                    "DROP TABLE launch;" +
                    "DROP TABLE agency CASCADE;" +
                    "DROP TABLE mission;" +
                    "DROP TABLE rocket;");
            System.out.println("INFO - Tablas eliminadas.");
        } catch (SQLException e) {
            System.out.println("INFO - Las tablas ya estaban eliminadas.");
        }
    }

    /**
     * Crea todas las tablas en la base de datos.
     *
     * @param st una instancia de {@code Statement} para ejecutar sentencias SQL en la base de datos.
     * @throws IOException si hay un error al leer el archivo "src/schema.sql".
     * @throws SQLException si hay un error al ejecutar la sentencia SQL en la base de datos.
     */
    public static void createAllTables(Statement st) throws IOException, SQLException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/schema.sql"))) {
            st.execute(br.lines().collect(Collectors.joining(" \n")));
            System.out.println("INFO - Tablas creadas.");
        } catch (Exception e){
            System.out.println("INFO - Las tablas ya estaban creadas.");
        }
    }

    /**
     * Inserta todos los registros de los csv en sus respectivas tablas de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertAllData(Connection conn) throws SQLException {
        insertLocations(conn);
        insertAgencys(conn);
        insertRockets(conn);
        insertMissions(conn);
        insertLaunches(conn);
    }

    /**
     * Inserta los registros de localizaciones en la tabla "location" de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertLocations(Connection conn) throws SQLException {
        String csvFile = "src/csv/location.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO location (location_name, launch_location, rockets_launched) VALUES (?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            br.readLine();  // Salta la primera línea
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split(",");
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setString(3,data[2].replace("\"",""));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en location");
        }
    }

    /**
     * Inserta los registros de agencias en la tabla "agency" de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertAgencys(Connection conn) throws SQLException {
        String csvFile = "src/csv/agency.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO agency (agency_name,agency_type,agency_abbreviation,agency_administration,agency_founded,agency_country,agency_spacecraft,agency_launchers,agency_description) VALUES (?,?,?,?,?,?,?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            br.readLine();  // Salta la primera línea
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split("\",\"");
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setString(3,data[2].replace("\"",""));
                pst.setString(4,data[3].replace("\"",""));
                pst.setString(5,data[4].replace("\"",""));
                pst.setString(6,data[5].replace("\"",""));
                pst.setString(7,data[6].replace("\"",""));
                pst.setString(8,data[7].replace("\"",""));
                pst.setString(9,data[8].replace("\"",""));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en agency");
        }
    }

    /**
     * Inserta los registros de cohetes en la tabla "rocket" de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertRockets(Connection conn) throws SQLException {
        String csvFile = "src/csv/rocket.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO rocket (agency_name,rocket_name,rocket_family,rocket_length,rocket_diameter,rocket_launch_mass,rocket_low_earth_orbit_capacity,rocket_description) VALUES (?,?,?,?,?,?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            br.readLine();  // Salta la primera línea
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split(",");
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setString(3,data[2].replace("\"",""));
                pst.setString(4,data[3].replace("\"",""));
                pst.setString(5,data[4].replace("\"",""));
                pst.setString(6,data[5].replace("\"",""));
                pst.setString(7,data[6].replace("\"",""));
                pst.setString(8,data[7].replace("\"",""));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en rocket");
        }
    }

    /**
     * Inserta los registros de misiones en la tabla "mission" de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertMissions(Connection conn) throws SQLException {
        String csvFile = "src/csv/mission.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO mission (mission_name,mission_launch_cost,mission_type,mission_description,rocket_name) VALUES (?,?,?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            br.readLine();  // Salta la primera línea
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split("\",\"");
                pst.setString(1,data[1].replace("\"",""));
                pst.setString(2,data[3].replace("\"",""));
                pst.setString(3,data[2].replace("\"",""));
                pst.setString(4,data[4].replace("\"",""));
                pst.setString(5,data[0].replace("\"",""));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en mission");
        }
    }

    /**
     * Inserta los registros de lanzamientos en la tabla "launch" de la base de datos.
     *
     * @param conn Conexión a la base de datos.
     * @throws SQLException Si ocurre algún error en la ejecución de la consulta SQL.
     */
    public static void insertLaunches(Connection conn) throws SQLException {
        String csvFile = "src/csv/launch.csv";
        String line = "";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO launch (launch_title,launch_status,launch_date,rocket_name,agency_name,location_name,mission_name) VALUES (?,?,?,?,?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            br.readLine();  // Salta la primera línea
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split("\",\"");
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setString(3,data[2].replace("\"",""));
                pst.setString(4,data[3].replace("\"",""));
                pst.setString(5,data[4].replace("\"",""));
                pst.setString(6,data[5].replace("\"",""));
                pst.setString(7,data[6].replace("\"",""));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en launch");
        }
    }

    /**
     * Busca el texto introducido por el usuario en una tabla también selecionada.
     *
     * @param conn La conexión a la base de datos.
     * @throws SQLException Si hay algún problema con la conexión a la base de datos.
     */
    public static void searchByText(Connection conn) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        String searchText = "";
        while (searchText.replace(" ", "").isEmpty()) {
            System.out.print("Ingrese el texto de búsqueda: ");
            searchText = scanner.nextLine();
            searchText = searchText.toLowerCase();
            if (searchText.replace(" ", "").isEmpty()) {
                System.out.println("Por favor ingrese un texto válido.");
            }
        }

        System.out.print("Ingrese en que tabla quiere buscar (1: launch, 2: rocket, 3: agency, 4: location, 5: mission): ");
        int tableChoice = scanner.nextInt();
        String query = "";
        switch(tableChoice){
            case 1:
                query = "SELECT * FROM launch WHERE launch_title ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE launch_status ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE launch_date ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE rocket_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE agency_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE location_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM launch WHERE mission_name ILIKE '%" + searchText + "%'";
                break;
            case 2:
                query = "SELECT * FROM rocket WHERE rocket_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_family ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_length ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_diameter ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_low_earth_orbit_capacity ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_launch_mass ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE rocket_description ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM rocket WHERE agency_name ILIKE '%" + searchText + "%' ";
                break;
            case 3:
                query = "SELECT * FROM agency WHERE agency_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_type ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_abbreviation ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_administration ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_founded ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_country ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_spacecraft ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_launchers ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM agency WHERE agency_description ILIKE '%" + searchText + "%'";
                break;
            case 4:
                query = "SELECT * FROM location WHERE location_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM location WHERE launch_location ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM location WHERE rockets_launched ILIKE '%" + searchText + "%'";

                break;
            case 5:
                query = "SELECT * FROM mission WHERE mission_name ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM mission WHERE mission_launch_cost ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM mission WHERE mission_type ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM mission WHERE mission_description ILIKE '%" + searchText + "%' " +
                        "UNION SELECT * FROM mission WHERE rocket_name ILIKE '%" + searchText + "%'";
                break;
            default:
                System.out.println("Opción inválida, por favor ingrese un número entre 1 y 5.");
                return;
        }

        PreparedStatement statement = conn.prepareStatement(query);
        ResultSet result = statement.executeQuery();
        if (!result.next()) {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━\nNo se ha encontrado \"" + searchText+"\"\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        } else {
            System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
            do {
                ResultSetMetaData rsmd = result.getMetaData();
                int columnCount = rsmd.getColumnCount();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = rsmd.getColumnName(i);
                    String columnValue = result.getString(i);
                    if (columnValue.toLowerCase().contains(searchText.toLowerCase())) {
                        System.out.println(columnName + ": " + columnValue);
                    }
                }
            } while (result.next());
            System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        }
    }

    /**
     * Método para seleccionar datos de una tabla en una base de datos.
     *
     * @param conn La conexión con la base de datos.
     * @throws SQLException Si hay un error en la consulta a la base de datos.
     */
    public static void selectInTable(Connection conn) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese en que tabla quiere buscar (1: launch, 2: rocket, 3: agency, 4: location, 5: mission): ");
        int tableChoice = scanner.nextInt();
        String sql = "SELECT * FROM ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // Establecer conexión con la base de datos
            switch (tableChoice){
                case 1:
                    sql += "launch";
                    break;
                case 2:
                    sql += "rocket";
                    break;
                case 3:
                    sql += "agency";
                    break;
                case 4:
                    sql += "location";
                    break;
                case 5:
                    sql += "mission";
                    break;
                default:
                    System.out.println("Opción no válida");
                    return;
            }

            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();

            int i = 1;
            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            while (rs.next()) {
                ResultSetMetaData metaData = rs.getMetaData();
                int columnCount = metaData.getColumnCount();
                System.out.print(i + " | ");
                for (int j = 1; j <= columnCount; j++) {
                    System.out.print(rs.getString(j) + " | ");
                }
                System.out.println();
                i++;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}