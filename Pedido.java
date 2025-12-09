import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private static int proximoId = 1;

    private int id;
    private Cliente cliente;
    private List<ItemCarrinho> itens;
    private double total;
    private String status; 

    public Pedido(Cliente cliente, Carrinho carrinho) {
        if (cliente == null) {
            throw new IllegalArgumentException("Cliente não pode ser null.");
        }
        if (carrinho == null || carrinho.estaVazio()) {
            throw new IllegalArgumentException("Carrinho inválido ou vazio.");
        }

        this.id = proximoId++;
        this.cliente = cliente;
        this.itens = new ArrayList<>(carrinho.getItens()); 
        this.total = carrinho.calcularTotal();
        this.status = "CRIADO";
    }

    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public String getStatus() {
        return status;
    }

    public void pagar(Pagamento pagamento) {
        if (!status.equals("CRIADO")) {
            throw new IllegalStateException("Pedido não pode ser pago no status atual: " + status);
        }
        if (pagamento == null) {
            throw new IllegalArgumentException("Pagamento não pode ser null.");
        }
        if (pagamento.getValor() != total) {
            throw new IllegalArgumentException("Valor do pagamento diferente do total do pedido.");
        }

        pagamento.pagar();
        this.status = "PAGO";
    }

    public void cancelar() {
        if (status.equals("PAGO")) {
            throw new IllegalStateException("Pedido pago não pode ser cancelado aqui.");
        }
        this.status = "CANCELADO";
    }
}
