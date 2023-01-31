import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
     * @param nTabla Número de la tabla que desea mostrar, 0 para preguntar.
     * @throws SQLException Si hay un error en la consulta a la base de datos.
     */
    public static void selectInTable(Connection conn, int nTabla) throws SQLException {
        int tableChoice = nTabla;
        if (nTabla == 0){
            Scanner scanner = new Scanner(System.in);
            System.out.print("Ingrese en que tabla quiere buscar (1: launch, 2: rocket, 3: agency, 4: location, 5: mission): ");
            tableChoice = scanner.nextInt();
        }
        String sql = "SELECT * FROM ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
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

    public static void updateInTable(Connection conn) throws SQLException{
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese en que tabla quiere buscar (1: launch, 2: rocket, 3: agency, 4: location, 5: mission): ");
        int tableChoice = scanner.nextInt();
        String sql = "SELECT * FROM ";
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int nRows = 0;
        try {
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

            pstmt = conn.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = pstmt.executeQuery();

            int i = 1;
            ResultSetMetaData rsmd = rs.getMetaData();
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
            nRows = i-1;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        int rowChoice = scannerInt("\nInserta el número de la fila que quieres editar ( 1-" + nRows +" / 0-Salir): ",0,nRows);
        rs.absolute(rowChoice);
        if (rowChoice > 0 && rowChoice <= nRows ){
            switch (tableChoice){
                case 1:
                    String newLaunchTitle = scannerString("Introduce el título del lanzamiento (" + rs.getString(1)+"): ");
                    String newLaunchStatus = scannerString("Introduce la estado del lanzamiento (" + rs.getString(2)+"): ");
                    String newLaunchDate = scannerString("Introduce la fecha del lanzamiento (" + rs.getString(3)+"): ");

                    System.out.println("\nLista de cohetes:");
                    String sqlLaunchRocket = "SELECT rocket_name FROM rocket;";
                    conn.prepareStatement(sqlLaunchRocket);
                    ResultSet rsLaunchRockets = pstmt.executeQuery();
                    List<String> rocketLaunchNames = new ArrayList<>();
                    int nLaunchRocket = 0;
                    while (rsLaunchRockets.next()) {
                        nLaunchRocket++;
                        System.out.println(nLaunchRocket + " | " + rsLaunchRockets.getString("rocket_name"));
                        rocketLaunchNames.add(rsLaunchRockets.getString("rocket_name"));
                    }
                    String newLaunchRocket = rocketLaunchNames.get(scannerInt("Introduce el número correspondiente al cohete de la misión: ",1,nLaunchRocket)-1);

                    System.out.println("\nLista de agencias:");
                    String sqlLaunchAgency = "SELECT agency_name FROM agency;";
                    conn.prepareStatement(sqlLaunchAgency);
                    ResultSet rsLaunchAgency = pstmt.executeQuery();
                    List<String> agencyLaunchNames = new ArrayList<>();
                    int nLaunchAgency = 0;
                    while (rsLaunchAgency.next()) {
                        nLaunchAgency++;
                        System.out.println(nLaunchAgency + " | " + rsLaunchAgency.getString("agency_name"));
                        agencyLaunchNames.add(rsLaunchAgency.getString("agency_name"));
                    }
                    String newLaunchAgency = agencyLaunchNames.get(scannerInt("Introduce el número correspondiente a la agencia del cohete: ",1,nLaunchAgency)-1);

                    System.out.println("\nLista de localizaciones:");
                    String sqlLaunchLocation = "SELECT location_name FROM location;";
                    conn.prepareStatement(sqlLaunchLocation);
                    ResultSet rsLaunchLocation = pstmt.executeQuery();
                    List<String> agencyLaunchLocations = new ArrayList<>();
                    int nLaunchLocation = 0;
                    while (rsLaunchLocation.next()) {
                        nLaunchLocation++;
                        System.out.println(nLaunchLocation + " | " + rsLaunchLocation.getString("location_name"));
                        agencyLaunchLocations.add(rsLaunchLocation.getString("location_name"));
                    }
                    String newLaunchLocation = agencyLaunchLocations.get(scannerInt("Introduce el número correspondiente a la agencia del cohete: ",1,nLaunchLocation)-1);

                    System.out.println("\nLista de misiones:");
                    String sqlLaunchMission = "SELECT mission_name FROM mission;";
                    conn.prepareStatement(sqlLaunchMission);
                    ResultSet rsLaunchMission = pstmt.executeQuery();
                    List<String> agencyLaunchMissions = new ArrayList<>();
                    int nLaunchMission = 0;
                    while (rsLaunchMission.next()) {
                        nLaunchMission++;
                        System.out.println(nLaunchMission + " | " + rsLaunchMission.getString("mission_name"));
                        agencyLaunchMissions.add(rsLaunchMission.getString("mission_name"));
                    }
                    String newLaunchMission = agencyLaunchMissions.get(scannerInt("Introduce el número correspondiente a la agencia del cohete: ",1,nLaunchMission)-1);

                    sql = "UPDATE launch\n" +
                            "SET launch_title = '"+newLaunchTitle+"', launch_status = '"+newLaunchStatus+"', launch_date = '"+newLaunchDate+"', rocket_name = '"+newLaunchRocket+"', agency_name = '"+newLaunchAgency+"', location_name = '"+newLaunchLocation+"', mission_name = '"+newLaunchMission+"'"+
                            "WHERE launch_title = (SELECT launch_title FROM launch LIMIT 1 OFFSET "+(rowChoice-1)+");";
                    break;
                case 2:
                    String newRocketName = scannerString("Introduce el nombre del cohete (" + rs.getString(1)+"): ");
                    String newRocketFamily = scannerString("Introduce la família del cohete (" + rs.getString(2)+"): ");
                    String newRocketLength = scannerString("Introduce la longitud del cohete (" + rs.getString(3)+"): ");
                    String newRocketDiameter = scannerString("Introduce el diámetro del cohete (" + rs.getString(4)+"): ");
                    String newRocketOrbitCapacity = scannerString("Introduce la capacidad de órbita baja del cohete (" + rs.getString(5)+"): ");
                    String newRocketLaunchMass = scannerString("Introduce la masa de lanzamiento del cohete (" + rs.getString(6)+"): ");
                    String newRocketDescription = scannerString("Introduce la descripción del cohete (" + rs.getString(7)+")\n: ");

                    System.out.println("Lista de agencias:");
                    String sql2 = "SELECT agency_name FROM agency;";
                    conn.prepareStatement(sql2);
                    ResultSet rsRcoket = pstmt.executeQuery();
                    List<String> agencyNames = new ArrayList<>();
                    int nRocket = 0;
                    while (rsRcoket.next()) {
                        nRocket++;
                        System.out.println(nRocket + " | " + rsRcoket.getString("agency_name"));
                        agencyNames.add(rsRcoket.getString("agency_name"));
                    }
                    String newAgencyNameRocket = agencyNames.get(scannerInt("Introduce el número correspondiente a la agencia del cohete: ",1,nRocket)-1);

                    sql = "UPDATE rocket\n" +
                            "SET rocket_name = '"+newRocketName+"', rocket_family = '"+newRocketFamily+"', rocket_length = '"+newRocketLength+"',\n" +
                            "rocket_diameter = '"+newRocketDiameter+"', rocket_low_earth_orbit_capacity = '"+newRocketOrbitCapacity+"', rocket_launch_mass = '"+newRocketLaunchMass+"',\n" +
                            "rocket_description = '"+newRocketDescription+"', agency_name = '"+newAgencyNameRocket+"'\n" +
                            "WHERE rocket_name = (SELECT rocket_name FROM rocket LIMIT 1 OFFSET "+(rowChoice-1)+");";
                    break;
                case 3:
                    String newAgencyName = scannerString("Introduce el nombre de la agencia (" + rs.getString(1)+"): ");
                    String newAgencyType = scannerString("Introduce el tipo de la agencia (" + rs.getString(2)+"): ");
                    String newAgencyAbbreviation = scannerString("Introduce la abreviación de la agencia (" + rs.getString(3)+"): ");
                    String newAgencyAdministration = scannerString("Introduce la administración de la agencia (" + rs.getString(4)+"): ");
                    String newAgencyFounded = scannerString("Introduce el año de fundación de la agencia (" + rs.getString(5)+"): ");
                    String newAgencyCountry = scannerString("Introduce el país de la agencia (" + rs.getString(6)+"): ");
                    String newAgencySpacecraft = scannerString("Introduce la/s nave/s de la agencia (" + rs.getString(7)+"): ");
                    String newAgencyLaunchers = scannerString("Introduce el/los cohete/s de la agencia (" + rs.getString(8)+"): ");
                    String newAgencyDescription = scannerString("Introduce la descripción de la agencia (" + rs.getString(9)+"): \n");

                    sql = "UPDATE agency\n" +
                            "SET agency_name = '"+newAgencyName+"', agency_type = '"+newAgencyType+"', agency_abbreviation = '"+newAgencyAbbreviation+"',\n" +
                            "agency_administration = '"+newAgencyAdministration+"', agency_founded = '"+newAgencyFounded+"', agency_country = '"+newAgencyCountry+"',\n" +
                            "agency_spacecraft = '"+newAgencySpacecraft+"', agency_launchers = '"+newAgencyLaunchers+"', agency_description = '"+newAgencyDescription+"'\n" +
                            "WHERE agency_name = (SELECT agency_name FROM agency LIMIT 1 OFFSET "+(rowChoice-1)+");";
                    break;
                case 4:
                    String newLocationName = scannerString("Introduce el nuevo nombre de la ubicación (" + rs.getString(1) +"): ");
                    String newLocationLocation = scannerString("Introduce el nuevo lugar de lanzamiento (" + rs.getString(2) +"): ");
                    String newRocketsLaunched = scannerString("Introduce el nuevo número de cohetes lanzados (" + rs.getString(3) +"): ");
                    sql = "UPDATE location\n" +
                            "SET location_name = '"+newLocationName+"', launch_location = '"+newLocationLocation+"', rockets_launched = '"+newRocketsLaunched+"' " +
                            "WHERE location_name = (SELECT location_name FROM location LIMIT 1 OFFSET "+(rowChoice-1)+");";
                    break;
                case 5:
                    String newMissionName = scannerString("Introduce el nombre de la misión (" + rs.getString(1) +"): ");
                    String newMissionLaunchCost = scannerString("Introduce el coste de la misión (" + rs.getString(2) +"): ");
                    String newMissionType = scannerString("Introduce el tipo de la misión (" + rs.getString(3) +"): ");
                    String newMissionDescription = scannerString("Introduce la descripción de la misión (" + rs.getString(4) +") \n:");

                    System.out.println("Lista de cohetes:");
                    String sqlMissionRocket = "SELECT rocket_name FROM rocket;";
                    conn.prepareStatement(sqlMissionRocket);
                    ResultSet rsMission = pstmt.executeQuery();
                    List<String> rocketNames = new ArrayList<>();
                    int nMissionRocket = 0;
                    while (rsMission.next()) {
                        nMissionRocket++;
                        System.out.println(nMissionRocket + " | " + rsMission.getString("rocket_name"));
                        rocketNames.add(rsMission.getString("rocket_name"));
                    }
                    String newMissionRocket = rocketNames.get(scannerInt("Introduce el número correspondiente al cohete de la misión: ",1,nMissionRocket)-1);

                    sql = "UPDATE mission\n" +
                            "SET mission_name = '"+newMissionName+"', mission_launch_cost = '"+newMissionLaunchCost+"', mission_type = '"+newMissionType+"',\n" +
                            "mission_description = '"+newMissionDescription+"', rocket_name = '"+newMissionRocket+"'\n" +
                            "WHERE mission_name = (SELECT mission_name FROM mission LIMIT 1 OFFSET "+(rowChoice-1)+");";
                    break;
            }
            PreparedStatement preparedStatement = conn.prepareStatement(sql);
            preparedStatement.executeUpdate();

        } else if (rowChoice == 0){
            System.out.println("No se ha editado ninguna fila");
        } else {
            System.out.println("Opción no válida");
        }

    }



    public static String scannerString(String pregunta) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(pregunta);
                userInput = sc.nextLine();
                if (userInput.trim().isEmpty()){
                    System.out.println("No puedes introducir una cadena vacía.");
                    isValid = false;
                } else {
                    isValid = true;
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al leer la entrada. Por favor, inténtalo de nuevo.");
                sc.next();
            }
        }

        return userInput;
    }
    public static int scannerInt(String pregunta, int min, int max) {
        Scanner sc = new Scanner(System.in);
        int userInput = 0;
        boolean isValid = false;
        while (!isValid) {
            try {
                System.out.print(pregunta);
                userInput = sc.nextInt();
                if (userInput >= min && userInput <= max) {
                    isValid = true;
                } else {
                    System.out.println("El número debe estar entre " + min + " y " + max + ".");
                }
            } catch (Exception e) {
                System.out.println("Ocurrió un error al leer la entrada. Por favor, inténtalo de nuevo con un número entero.");
                sc.next();
            }
        }
        return userInput;
    }

}