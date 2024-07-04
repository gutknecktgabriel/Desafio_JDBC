package Conexao;

import javax.management.RuntimeErrorException;
import java.sql.*;

public class ConexaoBanco {
    private static final String drive = "org.postgresql.Driver";
    private static final String url = "jdbc:postgresql://localhost:5432/DesafioOlv";
    private static final String usuario = "postgresql";
    private static final String senha = "1234";


    public static Connection getConnection() {
        try {
            Class.forName(drive);
            return DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException | SQLException exceptionSQL) {
            throw new RuntimeException("Não foi possível conectar: " + exceptionSQL);
        }
    }

    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException exceptionSQL) {
            throw new RuntimeException("Erro! não foi possível fechar a conexão: " + exceptionSQL);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstm) {
        closeConnection(con);
        try {
            if (pstm != null) {
                pstm.close();
            }
        } catch (SQLException exceptionSQL) {
            throw new RuntimeException("Erro! não foi possível fechar o PreparedStatment: " + exceptionSQL);
        }
    }

    public static void closeConnection(Connection con, PreparedStatement pstm, ResultSet rs) {
        closeConnection(con, pstm);
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException exceptionSQL) {
            throw new RuntimeException("Erro! não foi possível fechar o Result set: " + exceptionSQL);
        }
    }
}
