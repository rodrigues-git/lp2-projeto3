package ecommerce;

public class Administrador extends Pessoa implements Autenticavel, Relatorio {
    private String senha;
    private String setor;

    public Administrador(String nome, String cpf, String email, String senha, String setor) {
        super(nome, cpf, email);
        this.senha = senha;
        this.setor = setor;
    }

    @Override
    public boolean login(String senhaEntrada) { return this.senha.equals(senhaEntrada); }
    public void setSetor(String setor) { this.setor = setor; } // Necessário para update

    @Override
    public String gerarDetalhes() {
        return "ADMIN: " + getNome() + " | Setor: " + setor;
    }
}