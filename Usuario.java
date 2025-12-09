public abstract class Usuario implements Autenticavel{

    private static int proximoId = 1;

    private int id;
    private String nome;
    private String email;
    private String senha;

    protected Usuario(String nome, String email, String senha) {
        this.id = proximoId;
        proximoId++;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean autenticar(String senhaTentativa) {
        return senha != null && senha.equals(senhaTentativa);
    }

    public void trocarSenha(String senhaAtual, String senhaNova) {
        if (!autenticar(senhaAtual)) {
            throw new IllegalArgumentException("Senha atual incorreta.");
        }
        this.senha = senhaNova;
    }

}

