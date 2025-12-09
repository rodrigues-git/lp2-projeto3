public class Pagamento {
    private static int proximoId = 1;

    private int id;
    private double valor;
    private String metodo; 
    private String status;

    public Pagamento(double valor, String metodo) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Valor deve ser maior que 0.");
        }
        this.id = proximoId++;
        this.valor = valor;
        this.metodo = metodo;
        this.status = "PENDENTE";
    }

    public int getId() {
        return id;
    }

    public double getValor() {
        return valor;
    }

    public String getMetodo() {
        return metodo;
    }

    public String getStatus() {
        return status;
    }

    public void pagar() {
        if (!status.equals("PENDENTE")) {
            throw new IllegalStateException("Pagamento não está pendente.");
        }
        this.status = "APROVADO";
    }
}
