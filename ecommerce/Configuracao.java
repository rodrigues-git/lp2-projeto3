package ecommerce;
import java.io.*;

public class Configuracao {
    private String nomeUser;
    private String tema;
    private final String ARQUIVO = "config.txt";

    public boolean carregar() {
        try (BufferedReader br = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha = br.readLine();
            if (linha != null) {
                String[] dados = linha.split(";");
                this.nomeUser = dados[0];
                this.tema = dados[1];
                return true;
            }
        } catch (IOException e) { }
        return false;
    }

    public void salvar(String nome, String tema) {
        this.nomeUser = nome;
        this.tema = tema;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARQUIVO))) {
            bw.write(nome + ";" + tema);
        } catch (IOException e) { System.out.println("Erro ao salvar config."); }
    }

    public String getNomeUser() { return nomeUser; }
    public String getTema() { return tema; }
}