package dao;

import conexao.ConexaoBanco;
import model.ItemPedido;
import model.Pedido;
import model.Produto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DaoItemPedido {
    public List<ItemPedido> findByOrder(Pedido pedido) {
        List<ItemPedido> itensPedidoList = new ArrayList<>();
        DaoProduto daoProduto = new DaoProduto();
        Connection con = ConexaoBanco.getConnection();
        PreparedStatement pstm;
        ResultSet rs;

        try {
            pstm = con.prepareStatement("SELECT * FROM itemPedido WHERE idPedido = ?");
            pstm.setInt(1,pedido.getId());
            rs = pstm.executeQuery();

            while (rs.next()) {
                Integer iditempedido = rs.getInt("idItemPedido");
                Integer idproduto = rs.getInt("idProduto");
                Integer quantidade = rs.getInt("quantidade");
                Produto produto = daoProduto.findByProduct(idproduto);
                ItemPedido itemPedido = new ItemPedido(iditempedido, produto, pedido, quantidade);
                itensPedidoList.add(itemPedido);
            }
        } catch (SQLException errorSQL) {
            System.out.println("Não foi possível realizar a listagem através do pedido: " + errorSQL.getMessage());
        }
        return itensPedidoList;
    }
}
