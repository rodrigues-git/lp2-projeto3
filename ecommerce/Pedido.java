package ecommerce;
import java.util.ArrayList;
import java.util.List;

public class Pedido implements Relatorio {
    private static int contador = 1;
    private int id;
    private Cliente cliente;
    private List<Produto> itens;
    private double valorTotal;
    private String status;

    public Pedido(Cliente cliente) {
        this.id = contador++;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.status = "PENDENTE";
    }

    public void adicionarItem(Produto p) {
        itens.add(p);
        valorTotal += p.getPreco();
    }

    public void aplicarDesconto(Cupom cupom) {
        this.valorTotal = cupom.aplicarDesconto(this.valorTotal);
    }

    public void setStatus(String status) { this.status = status; } // Necessário para update
    public int getId() { return id; }
    public double getValorTotal() { return valorTotal; }
    public void finalizar() { this.status = "PAGO"; }

    @Override
    public String gerarDetalhes() {
        return "Pedido #" + id + " | Cliente: " + cliente.getNome() + " | Total: R$ " + valorTotal + " | Status: " + status;
    }
}