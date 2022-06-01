package Application;

import db.DB;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Program {
    public static void main(String[] args) {
        //Criando a  conexao
        Connection conn = null;
        //Preparar consulta SQL
        Statement st = null;
        //Traz o resultado
        ResultSet rs = null;

        try{
            //conectando ao banco
            conn = DB.getConnection();

            st = conn.createStatement();
            //Consuta ao banco
            rs = st.executeQuery("select * from department");

            //Percorrendo a tabela e trazendo o ID e Name
            while (rs.next()){
                System.out.println(rs.getInt("Id") + " - " + rs.getString("Name"));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        //Fechando as conexões
        finally {
            DB.closeResultSet(rs);
            DB.closeStatement(st);
            DB.closeConnection();
        }

    }
}
