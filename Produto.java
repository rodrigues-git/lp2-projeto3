public abstract class Produto implements Descontavel {
    private static int proximoId = 1;

    private int id;
    private String nome;
    private String marca;
    private double preco;
    private int estoque;

    protected Produto(String nome, String marca, double preco, int estoque) {
        this.id = proximoId;
        proximoId++;
        this.nome = nome;
        this.marca = marca;
        setPreco(preco);
        setEstoque(estoque);
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo.");
        }
        this.preco = preco;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        if (estoque < 0) {
            throw new IllegalArgumentException("Estoque não pode ser negativo.");
        }
        this.estoque = estoque;
    }

    public void aumentarEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0.");
        }
        this.estoque += quantidade;
    }

    public void reduzirEstoque(int quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("Quantidade deve ser maior que 0.");
        }
        if (quantidade > estoque) {
            throw new IllegalStateException("Estoque insuficiente.");
        }
        this.estoque -= quantidade;
    }

    @Override
    public void aplicarDescontoPercentual(double percentual) {
        if (percentual < 0 || percentual > 100) {
            throw new IllegalArgumentException("Percentual inválido.");
        }
        this.preco = this.preco * (1.0 - percentual / 100.0);
    }

}

