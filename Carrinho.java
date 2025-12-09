import java.util.ArrayList;
import java.util.List;

public class Carrinho {
    private Cliente cliente;
    private List<ItemCarrinho> itens;

    public Carrinho(Cliente cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemCarrinho> getItens() {
        return itens;
    }

    public boolean estaVazio() {
        return itens.isEmpty();
    }

    public void adicionarProduto(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser null.");
        }
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0.");
        }
        if (produto.getEstoque() < quantidade) {
            throw new IllegalStateException("Estoque insuficiente.");
        }

        for (ItemCarrinho item : itens) {
            if (item.getProduto().getId() == produto.getId()) {
                item.setQuantidade(item.getQuantidade() + quantidade);
                return;
            }
        }

        itens.add(new ItemCarrinho(produto, quantidade));
    }

    public boolean removerProduto(int idProduto) {
        return itens.removeIf(item -> item.getProduto().getId() == idProduto);
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemCarrinho item : itens) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void limpar() {
        itens.clear();
    }
}
