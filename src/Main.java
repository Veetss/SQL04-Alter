import java.sql.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        Connection con = null;

        try {
            String db_url = "jdbc:mysql://localhost:3306/newdb";
            String user = "developer";
            String password = "Password!";

            con = DriverManager.getConnection(db_url, user, password);

            Statement s = con.createStatement();

            String sql = "CREATE TABLE IF NOT EXISTS student " +
                    "(student_id INTEGER(10) NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    " last_name VARCHAR(30), " +
                    " first_name VARCHAR(30))";

            ArrayList<String> surnames = new ArrayList<>();

            s.executeUpdate(sql);

            System.out.println("Table created.");

            s.execute("insert into student (last_name,first_name) value (\"Boukoti\", \"Ismail\");");
            s.execute("insert into student (last_name,first_name) value (\"Vassarotti\", \"Vittoria\");");
            s.execute("insert into student (last_name,first_name) value (\"De Fenzo\", \"Marco\");");
            s.execute("insert into student (last_name,first_name) value (\"Mustata\", \"Elena Alina\");");

            System.out.println("Students added to table.");

            ResultSet rs = s.executeQuery("SELECT first_name, last_name FROM newdb.student");

            while(rs.next())
            {
                surnames.add(rs.getString("last_name"));
                System.out.println(rs.getString("first_name"));
            }

            System.out.println(surnames);

            String sql1 = "ALTER TABLE student ADD country VARCHAR(30);";

            s.executeUpdate(sql1);

            s.executeUpdate("UPDATE student SET country = \"Italy\" where student_id = 1;");
            s.executeUpdate("UPDATE student SET country = \"Italy\" where student_id = 2;");
            s.executeUpdate("UPDATE student SET country = \"Germany\" where student_id = 3;");
            s.executeUpdate("UPDATE student SET country = \"Germany\" where student_id = 4;");

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try{
                if(con != null)
                    con.close();
            }catch(SQLException ex){
                System.out.println(ex.getMessage());
            }
        }
    }
}
