import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.stream.Collectors;

public interface Tables {

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
    public static void createAllTables(Statement st) throws IOException, SQLException {
        try(BufferedReader br = new BufferedReader(new FileReader("src/schema.sql"))) {
            st.execute(br.lines().collect(Collectors.joining(" \n")));
            System.out.println("INFO - Tablas creadas.");
        } catch (Exception e){
            System.out.println("INFO - Las tablas ya estaban creadas.");
        }
    }
    public static void insertAllData(Connection conn) throws SQLException {
        insertLocations(conn);
        insertAgencys(conn);
        insertRockets(conn);
        insertMissions(conn);
        insertLaunches(conn);
    }
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

}