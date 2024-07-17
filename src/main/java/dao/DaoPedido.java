package dao;

import conexao.ConexaoBanco;
import model.Cliente;
import model.ItemPedido;
import model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoPedido {
    public List<Pedido> findAll() {
        List<Pedido> list = new ArrayList<>();
        Connection con = ConexaoBanco.getConnection();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = con.prepareStatement("SELECT * FROM pedido");
            rs = pstm.executeQuery();

            DaoCliente daoCliente = new DaoCliente();
            DaoItemPedido daoItemPedido = new DaoItemPedido();

            while (rs.next()) {
                Integer idPedido = rs.getInt("idPedido");
                Integer idCliente = rs.getInt("idCliente");
                Cliente cliente = daoCliente.findById(idCliente);
                Pedido pedido = new Pedido(idPedido, cliente, null);
                List<ItemPedido> itens = daoItemPedido.findByOrder(pedido);
                pedido.setItens(itens);
                list.add(pedido);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    public Pedido adicionar(Pedido pedido) {
        Connection con = ConexaoBanco.getConnection();
        PreparedStatement pstm = null;

        try {
            pstm = con.prepareStatement("INSERT INTO pedido (idCliente) VALUES (?)", Statement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, pedido.getCliente().getId());
            pstm.executeUpdate();

            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                int idPedido = rs.getInt(1);
                pedido.setId(idPedido);
            }
            for (ItemPedido item : pedido.getItens()) {
                pstm = con.prepareStatement("INSERT INTO itemPedido (idPedido, idProduto, quantidade) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                pstm.setInt(1, pedido.getId());
                pstm.setInt(2, item.getProduto().getId());
                pstm.setInt(3, item.getQuantidade());
                pstm.executeUpdate();

                ResultSet resultSet = pstm.getGeneratedKeys();
                if (resultSet.next()) {
                    int idItemPedido = rs.getInt(1);
                    item.setId(idItemPedido);
                }
            }

        } catch (SQLException errorSQL) {
            System.out.println("Não foi possível realizar a criação do pedido: " + errorSQL.getMessage());
        }
            return pedido;
        }
    }