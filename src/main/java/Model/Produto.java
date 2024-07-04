package Model;

public class Produto {
    private Integer idProduto;
    private String nomeProduto;
    private double valor;

    public Produto(Integer idProduto, String nomeProduto, double valor) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.valor = valor;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Produto{" +
                "idProduto=" + idProduto +
                ", nomeProduto='" + nomeProduto + '\'' +
                ", valor=" + valor +
                '}';
    }
}
