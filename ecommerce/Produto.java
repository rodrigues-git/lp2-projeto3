package ecommerce;

public class Produto implements Relatorio {
    private static int contadorIds = 1;
    private int id;
    private String nome;
    private double preco;
    private int estoque;

    public Produto(String nome, double preco, int estoque) throws DadoInvalidoException {
        this.id = contadorIds++;
        this.nome = nome;
        setPreco(preco);
        setEstoque(estoque);
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getPreco() { return preco; }

    public void setPreco(double preco) throws DadoInvalidoException {
        if (preco < 0) throw new DadoInvalidoException("Preço negativo inválido.");
        this.preco = preco;
    }

    public int getEstoque() { return estoque; }
    public void setEstoque(int estoque) throws DadoInvalidoException {
        if (estoque < 0) throw new DadoInvalidoException("Estoque negativo inválido.");
        this.estoque = estoque;
    }

    public void reduzirEstoque(int qtd) throws EstoqueInsuficienteException {
        if (qtd > estoque) throw new EstoqueInsuficienteException("Estoque insuficiente.");
        this.estoque -= qtd;
    }

    @Override
    public String gerarDetalhes() {
        return String.format("ID: %d | %s | R$ %.2f | Estoque: %d", id, nome, preco, estoque);
    }
}