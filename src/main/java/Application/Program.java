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
                    +"(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);
            //Alerando os ? pelos valor
            st.setString(1,"Joao Carlos");
            st.setString(2,"joao.carlos@hotmail.com");
            st.setDate(3,new java.sql.Date(sdf.parse("02/10/1999").getTime()));
            st.setDouble(4,6500.0);
            st.setInt(5,1);

            //Para saber quantas linhas foram alteras
           int rowsAffected = st.executeUpdate();

            //System.out.println("Update! - Foram alterados: " + rowsAffected + " linhas");

            //tratamento para trazer o codigo do registro inserido
            if(rowsAffected > 0){
                ResultSet rs = st.getGeneratedKeys();
                while (rs.next()){
                    int id = rs.getInt(1);
                    System.out.println("UPDATE! Id: " + id);
                }
            }else {
                System.out.println("UPDATE não realizado, não houve linhas acrescentadas.");
            }

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
