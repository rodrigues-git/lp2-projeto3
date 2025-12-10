package ecommerce;

public class Fornecedor implements Relatorio {
    private String nomeEmpresa;
    private String cnpj;

    public Fornecedor(String nomeEmpresa, String cnpj) {
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nome) { this.nomeEmpresa = nome; }
    public String getCnpj() { return cnpj; }

    @Override
    public String gerarDetalhes() {
        return "Fornecedor: " + nomeEmpresa + " | CNPJ: " + cnpj;
    }
}