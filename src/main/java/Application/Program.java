package Application;

import db.DB;
import db.DBException;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Program {
    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        //Criando a  conexao
        Connection conn = null;
        PreparedStatement st = null;

        try {
            conn = DB.getConnection();
            //Comando SQL
            st = conn.prepareStatement(
                    "INSERT INTO seller "
                    + "(Name,Email,BirthDate,BaseSalary, DepartmentID)"
                    + "VALUES "
                    +"(?,?,?,?,?)");
            //Alerando os ? pelos valor
            st.setString(1,"Maria Julia");
            st.setString(2,"maria@gmail.com");
            st.setDate(3,new java.sql.Date(sdf.parse("22/04/1985").getTime()));
            st.setDouble(4,3500.0);
            st.setInt(5,4);

            //Para saber quantas linhas foram alteras
           int rowsAffected = st.executeUpdate();

            System.out.println("Update! - Foram alterados: " + rowsAffected + " linhas");

        }catch (SQLException e){
            e.printStackTrace();

        }catch (ParseException e){
            e.printStackTrace();
        }
        //Fechando a conexao
        finally {
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
