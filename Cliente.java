public class Cliente extends Usuario {
    private String cpf;
    private Carrinho carrinho;

    public Cliente(String nome, String email, String senha, String cpf) {
        super(nome, email, senha);
        this.cpf = cpf;
        this.carrinho = new Carrinho(this);
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    
    public void adicionarAoCarrinho(Produto produto, int quantidade) {
        carrinho.adicionarProduto(produto, quantidade);
    }

    
    public Pedido finalizarPedido() {
        Pedido pedido = new Pedido(this, carrinho);
        carrinho.limpar();
        return pedido;
    }
}

