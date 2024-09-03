package insertData;

import db.DB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class InsertData {
    public static void main(String[] args) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        Connection conn = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            /* Exemplo 1
            conn = DB.getConnection();
            st = conn.prepareStatement("INSERT INTO seller "
                    + "(Name, Email, BirthDate, BaseSalary, DepartmentId)"
                    + "VALUES"
                    + "(?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            st.setString(1, "Peter Black");
            st.setString(2, "peter@gmail.com");
            st.setDate(3, new java.sql.Date(sdf.parse("25/07/1988").getTime()));
            st.setDouble(4, 5000);
            st.setInt(5, 4);
             */

            conn = DB.getConnection();

            st = conn.prepareStatement(
                    "insert into department (Name) values ('D1'), ('D2')",
                    Statement.RETURN_GENERATED_KEYS);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                rs = st.getGeneratedKeys();
                while (rs.next()) {
                    int id = rs.getInt(1);
                    System.out.println("Done! Id = " + id);
                }
            } else {
                System.out.println("No rows affected!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }
    }
}
