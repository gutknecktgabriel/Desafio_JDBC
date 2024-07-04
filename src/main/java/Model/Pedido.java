package Model;

public class Pedido {
    private Integer idPedido;
    private Integer idCliente;
    private double total;

    public Pedido(Integer idPedido, Integer idCliente, double total) {
        this.idPedido = idPedido;
        this.idCliente = idCliente;
        this.total = total;
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(Integer idPedido) {
        this.idPedido = idPedido;
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Pedido{" +
                "idPedido=" + idPedido +
                ", idCliente=" + idCliente +
                ", total=" + total +
                '}';
    }
}
