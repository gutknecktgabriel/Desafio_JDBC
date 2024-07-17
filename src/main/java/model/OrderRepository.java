package model;

import dao.DaoPedido;
import java.util.List;

public class OrderRepository {
    DaoPedido dao = new DaoPedido();

    public List<Pedido> findAll() {
        return dao.findAll();
    }
}
