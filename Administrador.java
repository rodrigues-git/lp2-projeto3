public class Administrador extends Usuario {

    public Administrador(String nome, String email, String senha) {
        super(nome, email, senha);
    }

    public void alterarPreco(Produto produto, double novoPreco) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser null.");
        }
        produto.setPreco(novoPreco);
    }

    public void adicionarEstoque(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser null.");
        }
        produto.aumentarEstoque(quantidade);
    }

    public void removerEstoque(Produto produto, int quantidade) {
        if (produto == null) {
            throw new IllegalArgumentException("Produto não pode ser null.");
        }
        produto.reduzirEstoque(quantidade);
    }
}
