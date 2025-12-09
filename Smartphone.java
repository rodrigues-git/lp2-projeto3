public class Smartphone extends Produto {
    private int armazenamentoGb;
    private int ramGb;
    private double telaPolegadas;

    public Smartphone(String nome, String marca, double preco, int estoque,
    int armazenamentoGb, int ramGb, double telaPolegadas) {
        super(nome, marca, preco, estoque);
        setArmazenamentoGb(armazenamentoGb);
        setRamGb(ramGb);
        setTelaPolegadas(telaPolegadas);
    }

    public int getArmazenamentoGb() {
        return armazenamentoGb;
    }

    public void setArmazenamentoGb(int armazenamentoGb) {
        if (armazenamentoGb < 0) {
            throw new IllegalArgumentException("Armazenamento não pode ser negativo.");
        }
        this.armazenamentoGb = armazenamentoGb;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        if (ramGb < 0) {
            throw new IllegalArgumentException("RAM não pode ser negativa.");
        }
        this.ramGb = ramGb;
    }

    public double getTelaPolegadas() {
        return telaPolegadas;
    }

    public void setTelaPolegadas(double telaPolegadas) {
        if (telaPolegadas < 0) {
            throw new IllegalArgumentException("Tamanho da tela não pode ser negativo.");
        }
        this.telaPolegadas = telaPolegadas;
    }
}

