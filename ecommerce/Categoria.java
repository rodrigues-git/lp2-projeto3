package ecommerce;

public class Categoria implements Relatorio {
    private static int contador = 1;
    private int id;
    private String nome;
    private String descricao;

    public Categoria(String nome, String descricao) {
        this.id = contador++;
        this.nome = nome;
        this.descricao = descricao;
    }

    public int getId() { return id; }
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    @Override
    public String gerarDetalhes() {
        return "ID: " + id + " | Categoria: " + nome + " (" + descricao + ")";
    }
}