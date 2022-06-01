package Application;

import db.DB;
import db.DBException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement st =null;

        try {
            conn = DB.getConnection();
            //Atualizar registro do banco
            st = conn.prepareStatement(
                    "UPDATE seller "
                    + "SET BaseSalary = BaseSalary + ? "
                    + "WHERE "
                    + "(DepartmentID = ?)");

            st.setDouble(1,200.0);
            st.setInt(2,1);

            int rowsAffected = st.executeUpdate();
            System.out.println("UPDATE realizado, " + rowsAffected + " linhas");

        }catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();

        }


    }
}
