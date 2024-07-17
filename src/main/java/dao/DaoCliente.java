package dao;

import conexao.ConexaoBanco;
import model.Cliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoCliente {

    public Cliente findById(Integer idCliente){
        Cliente cliente = null;
        Connection con = ConexaoBanco.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT * FROM cliente WHERE idCliente = ?");
            pstm.setInt(1, idCliente);
            rs = pstm.executeQuery();

            if(rs.next()){
                Integer id = rs.getInt("idCliente");
                String nome = rs.getString("nomeCliente");
                String cpf = rs.getString("cpf");
                cliente = new Cliente(id, nome, cpf);
            }

        } catch (SQLException errorSQL) {
            System.out.println("Não foi possível buscar o cliente por seu ID: " + errorSQL.getMessage());
        } finally {
            ConexaoBanco.closeConnection(con, pstm, rs);
        }

        return cliente;
    }
}
