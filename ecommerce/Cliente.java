package ecommerce;

public class Cliente extends Pessoa implements Autenticavel, Relatorio {
    private String senha;
    private String endereco;

    public Cliente(String nome, String cpf, String email, String senha, String endereco) {
        super(nome, cpf, email);
        this.senha = senha;
        this.endereco = endereco;
    }

    @Override
    public boolean login(String senhaEntrada) { return this.senha.equals(senhaEntrada); }
    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    @Override
    public String gerarDetalhes() {
        return "CLIENTE: " + getNome() + " | CPF: " + getCpf() + " | Endereço: " + endereco;
    }
}