public class Periferico extends Produto {
    private String tipo;     
    private String conexao;  

    public Periferico(String nome, String marca, double preco, int estoque,
    String tipo, String conexao) {
        super(nome, marca, preco, estoque);
        this.tipo = tipo;
        this.conexao = conexao;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConexao() {
        return conexao;
    }

    public void setConexao(String conexao) {
        this.conexao = conexao;
    }
}

