package ecommerce;

public class Cupom implements Relatorio {
    private String codigo;
    private double porcentagemDesconto;
    private boolean ativo;

    public Cupom(String codigo, double porcentagemDesconto) {
        this.codigo = codigo;
        this.porcentagemDesconto = porcentagemDesconto;
        this.ativo = true;
    }

    public double aplicarDesconto(double valorTotal) {
        if (ativo) return valorTotal - (valorTotal * (porcentagemDesconto / 100));
        return valorTotal;
    }

    public String getCodigo() { return codigo; }
    public void setPorcentagemDesconto(double p) { this.porcentagemDesconto = p; }

    @Override
    public String gerarDetalhes() {
        return "Cupom: " + codigo + " | " + porcentagemDesconto + "% OFF";
    }
}