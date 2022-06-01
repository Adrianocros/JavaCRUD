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
        Statement st =null;

        try {
            conn = DB.getConnection();

            //Não confirmando as operações automaticamente, aguardando o programador cofirmar
            conn.setAutoCommit(false);

            st = conn.createStatement();

            int rows1 = st.executeUpdate("UPDATE seller SET BaseSalary = 2090 WHERE DepartmentId = 1");

            //Interrompendo o update e criando erro executando somente o comando 1
//            int x = 1;
//            if(x < 2){
//                throw new SQLException("Erro falso!");
//            }

            int rows2 = st.executeUpdate("UPDATE seller SET BaseSalary = 3090 WHERE DepartmentId = 2");

            //confirma a transação
            conn.commit();

            System.out.println("Linha 1" + rows1);
            System.out.println("Linha 2" + rows2);

        }catch (SQLException e){
            //Volta a transação caso tenha ocorrido erro
            try {
                conn.rollback();
                throw  new DBException("Transação não foi concluida! " + e.getMessage());
            } catch (SQLException ex) {
                //Tentou voltar e ocorreu erro
                throw new DBException("Erro ao voltar o banco! " + e.getMessage());
            }
        }
        finally {
            DB.closeStatement(st);
            DB.closeConnection();

        }
    }
}
