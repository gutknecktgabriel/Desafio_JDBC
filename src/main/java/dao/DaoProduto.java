package dao;

import conexao.ConexaoBanco;
import model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DaoProduto {
    public Produto findByProduct(Integer idProduto) {
        Produto produto = null;
        Connection con = ConexaoBanco.getConnection();
        PreparedStatement pstm;
        ResultSet rs;

        try {
            pstm = con.prepareStatement("SELECT * FROM produto WHERE idProduto = ?");
            pstm.setInt(1, idProduto);
            rs = pstm.executeQuery();

            while (rs.next()){
                Integer idproduto = rs.getInt("idProduto");
                String nome = rs.getString("nome");
                Double valor = rs.getDouble("valor");
                produto = new Produto(idproduto, nome, valor);
            }
        } catch (SQLException errorSQL) {
            System.out.println("Não foi possível buscar as informações do produto, error: " + errorSQL.getMessage());
        }
        return produto;
    }
}
