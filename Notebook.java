public class Notebook extends Produto {
    private String processador;
    private int ramGb;
    private int ssdGb;

    public Notebook(String nome, String marca, double preco, int estoque,
    String processador, int ramGb, int ssdGb){
        super(nome, marca, preco, estoque);
        this.processador = processador;
        this.ramGb = ramGb;
        this.ssdGb = ssdGb;
    }

    public String getProcessador(){
        return processador;
    }

    public void setProcessador(String processador) {
        this.processador = processador;
    }

    public int getRamGb() {
        return ramGb;
    }

    public void setRamGb(int ramGb) {
        if (ramGb < 0) throw new IllegalArgumentException("RAM não pode ser negativa.");
        this.ramGb = ramGb;
    }

    public int getSsdGb() {
        return ssdGb;
    }

    public void setSsdGb(int ssdGb) {
        if (ssdGb < 0) throw new IllegalArgumentException("SSD não pode ser negativo.");
        this.ssdGb = ssdGb;
    }

}
