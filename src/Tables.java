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
    }
    public static void insertLocations(Connection conn) throws SQLException {
        String csvFile = "src/csv/location.csv";
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO location (location_name, launch_location, rockets_launched) VALUES (?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split(csvSplitBy);
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setInt(3, Integer.parseInt(data[2]));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en location");
        }
    }
    public static void insert(Connection conn) throws SQLException {
        String csvFile = "src/csv/location.csv";
        String line = "";
        String csvSplitBy = ",";
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            String sql = "INSERT INTO location (location_name, launch_location, rockets_launched) VALUES (?,?,?) ON CONFLICT DO NOTHING;";
            PreparedStatement pst = conn.prepareStatement(sql);
            while ((line = br.readLine()) != null) {
                pst.clearParameters();
                String[] data = line.split(csvSplitBy);
                pst.setString(1,data[0].replace("\"",""));
                pst.setString(2,data[1].replace("\"",""));
                pst.setInt(3, Integer.parseInt(data[2]));
                pst.executeUpdate();
            }
        } catch (IOException e) {
            System.out.println("ERROR - Insertando datos en location");
        }
    }

}