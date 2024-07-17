package app;

import conexao.ConexaoBanco;
import dao.DaoCliente;
import dao.DaoItemPedido;
import dao.DaoPedido;
import dao.DaoProduto;
import model.*;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppView {

    private OrderRepository orderRepository = new OrderRepository();


    public void showMenu(Scanner terminal) {
        int option = -1;
        while (option != 3) {
            showOptions();
            option = Integer.parseInt(terminal.next());
            processOption(option, terminal);
        }
    }

    private void showOptions() {
        System.out.println("1 - Listar Pedidos");
        System.out.println("2 - Cadastrar Pedido");
        System.out.println("3 - Sair");
    }

    private void processOption(int option, Scanner terminal) {
        switch (option) {
            case 1:
                optionListOrders();
                break;
            case 2:
                optionCreateOrder(terminal);
                break;
            case 3:
                optionExit();
                break;
            default:
                System.out.println("Opção inválida");
        }
    }

    private void optionListOrders() {
        List<Pedido> orders = orderRepository.findAll();
        System.out.println("LISTAGEM DE PEDIDOS");
        if (orders.size() > 0) {
            System.out.println("A Lista de  Pedidos é ");
            for (Pedido order : orders) {
                System.out.println(order);
            }
        } else {
            System.out.println("Não foram encontrados pedidos cadastrados");
        }
    }

    private void optionCreateOrder(Scanner terminal) {
        Pedido novoPedido = new Pedido();
        DaoPedido daoPedido = new DaoPedido();
        DaoCliente daoCliente = new DaoCliente();
        DaoItemPedido daoItemPedido = new DaoItemPedido();
        DaoProduto daoProduto = new DaoProduto();
        List<ItemPedido> itensNovoPedido = new ArrayList<>();

        System.out.println("NOVO PEDIDO");
        System.out.println("---------------------------------------------------------");

        try {
            System.out.print("ID do cliente: ");
            Cliente cliente = daoCliente.findById(terminal.nextInt());
            novoPedido.setCliente(cliente);

            boolean adicionarItens = true;
            while (adicionarItens) {
                System.out.print("ID do produto: ");
                int idProduto = terminal.nextInt();
                Produto produto = daoProduto.findByProduct(idProduto);

                System.out.print("Quantidade: ");
                int quantidade = terminal.nextInt();

                ItemPedido itemPedido = new ItemPedido(null , produto, novoPedido, quantidade);
                itensNovoPedido.add(itemPedido);

                System.out.print("Deseja adicionar mais itens? (S/N): ");
                String continuar = terminal.next();
                if (!continuar.equalsIgnoreCase("S")) {
                    adicionarItens = false;
                }
            }

            novoPedido.setItens(itensNovoPedido);
            daoPedido.adicionar(novoPedido);

            System.out.println("Pedido adicionado com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro ao criar pedido: " + e.getMessage());
        }
    }


    private void optionExit() {
        System.out.println("Bye bye!");
    }
}