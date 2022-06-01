package Application;

import db.DB;
import db.DBException;
import db.DBIntegrityException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st =null;

        try {
            conn = DB.getConnection();
            //Deletando registro do banco
            st = conn.prepareStatement(
                "DELETE FROM department "
                + "WHERE "
                + "Id = ?");

            st.setInt(1,6);


            int rowsAffected = st.executeUpdate();
            System.out.println("DELET realizado, " + rowsAffected + " linhas");

        }catch (SQLException e){
            throw new DBIntegrityException(e.getMessage());
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}
