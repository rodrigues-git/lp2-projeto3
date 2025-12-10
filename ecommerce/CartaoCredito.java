package ecommerce;

public class CartaoCredito extends Pagamento implements Relatorio {
    private String numero;
    private String titular;

    public CartaoCredito(double valor, String numero, String titular) {
        super(valor);
        this.numero = numero;
        this.titular = titular;
    }

    @Override
    public void processarPagamento() {
        System.out.println("Pagamento de R$ " + getValor() + " autorizado no cartão " + numero);
    }

    public String getNumero() { return numero; }
    public void setTitular(String titular) { this.titular = titular; }

    @Override
    public String gerarDetalhes() {
        return "Cartão Final " + numero.substring(Math.max(0, numero.length()-4)) + " | Titular: " + titular;
    }
}