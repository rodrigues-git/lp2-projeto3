package ecommerce;
import java.util.ArrayList;
import java.util.List;

public class SistemaEcommerce {
    private static List<Produto> produtos = new ArrayList<>();
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Administrador> admins = new ArrayList<>();
    private static List<Categoria> categorias = new ArrayList<>();
    private static List<Fornecedor> fornecedores = new ArrayList<>();
    private static List<Cupom> cupons = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();
    private static List<CartaoCredito> cartoes = new ArrayList<>();

    public static void inicializarDados() {
        try {
            admins.add(new Administrador("Admin Jefferson", "000", "admin@ufrn.br", "admin123", "TI"));
            clientes.add(new Cliente("Aluno Teste", "111", "aluno@ufrn.br", "123456", "Rua A"));
            categorias.add(new Categoria("Eletrônicos", "Gadgets"));
            fornecedores.add(new Fornecedor("TechZone", "12345678000199"));
            produtos.add(new Produto("Notebook", 4000.0, 5));
            cupons.add(new Cupom("DESC10", 10.0));
        } catch (Exception e) { e.printStackTrace(); }
    }

    // --- 1. PRODUTO ---
    public static void cadastrarProduto(Produto p) { produtos.add(p); }
    public static List<Produto> listarProdutos() { return produtos; }
    public static Produto buscarProduto(int id) throws DadoInvalidoException {
        return produtos.stream().filter(p -> p.getId() == id).findFirst()
                .orElseThrow(() -> new DadoInvalidoException("Produto não encontrado"));
    }
    public static void atualizarProduto(int id, String nome, double preco) throws DadoInvalidoException {
        Produto p = buscarProduto(id);
        if (!nome.isEmpty()) p.setNome(nome);
        if (preco >= 0) p.setPreco(preco);
    }
    public static void removerProduto(int id) { produtos.removeIf(p -> p.getId() == id); }

    // --- 2. CLIENTE ---
    public static void cadastrarCliente(Cliente c) { clientes.add(c); }
    public static List<Cliente> listarClientes() { return clientes; }
    public static void atualizarCliente(Cliente c, String endereco) { c.setEndereco(endereco); }
    public static void removerCliente(String cpf) { clientes.removeIf(c -> c.getCpf().equals(cpf)); }

    // --- 3. ADMINISTRADOR ---
    public static void cadastrarAdmin(Administrador a) { admins.add(a); }
    public static List<Administrador> listarAdmins() { return admins; }
    public static void atualizarAdmin(String cpf, String setor) {
        admins.stream().filter(a -> a.getCpf().equals(cpf)).findFirst()
                .ifPresent(a -> a.setSetor(setor));
    }
    public static void removerAdmin(String cpf) { admins.removeIf(a -> a.getCpf().equals(cpf)); }

    // --- 4. CATEGORIA ---
    public static void cadastrarCategoria(Categoria c) { categorias.add(c); }
    public static List<Categoria> listarCategorias() { return categorias; }
    public static void atualizarCategoria(int id, String nome) {
        categorias.stream().filter(c -> c.getId() == id).findFirst().ifPresent(c -> c.setNome(nome));
    }
    public static void removerCategoria(int id) { categorias.removeIf(c -> c.getId() == id); }

    // --- 5. FORNECEDOR ---
    public static void cadastrarFornecedor(Fornecedor f) { fornecedores.add(f); }
    public static List<Fornecedor> listarFornecedores() { return fornecedores; }
    public static void atualizarFornecedor(String cnpj, String nome) {
        fornecedores.stream().filter(f -> f.getCnpj().equals(cnpj)).findFirst().ifPresent(f -> f.setNomeEmpresa(nome));
    }
    public static void removerFornecedor(String cnpj) { fornecedores.removeIf(f -> f.getCnpj().equals(cnpj)); }

    // --- 6. CUPOM ---
    public static void cadastrarCupom(Cupom c) { cupons.add(c); }
    public static List<Cupom> listarCupons() { return cupons; }
    public static Cupom buscarCupom(String codigo) {
        return cupons.stream().filter(c -> c.getCodigo().equalsIgnoreCase(codigo)).findFirst().orElse(null);
    }
    public static void atualizarCupom(String codigo, double porcentagem) {
        Cupom c = buscarCupom(codigo);
        if (c != null) c.setPorcentagemDesconto(porcentagem);
    }
    public static void removerCupom(String codigo) { cupons.removeIf(c -> c.getCodigo().equalsIgnoreCase(codigo)); }

    // --- 7. PEDIDO ---
    public static void registrarPedido(Pedido p) { pedidos.add(p); p.finalizar(); }
    public static List<Pedido> listarPedidos() { return pedidos; }
    public static void atualizarStatusPedido(int id, String status) {
        pedidos.stream().filter(p -> p.getId() == id).findFirst().ifPresent(p -> p.setStatus(status));
    }
    public static void removerPedido(int id) { pedidos.removeIf(p -> p.getId() == id); }

    // --- 8. CARTÃO ---
    public static void salvarCartao(CartaoCredito cc) { cartoes.add(cc); }
    public static List<CartaoCredito> listarCartoes() { return cartoes; }
    public static void atualizarCartao(String num, String titular) {
        cartoes.stream().filter(c -> c.getNumero().equals(num)).findFirst().ifPresent(c -> c.setTitular(titular));
    }
    public static void removerCartao(String num) { cartoes.removeIf(c -> c.getNumero().equals(num)); }

    // --- LOGIN ---
    public static Pessoa fazerLogin(String email, String senha) throws DadoInvalidoException {
        for (Administrador a : admins) if (a.getEmail().equals(email) && a.login(senha)) return a;
        for (Cliente c : clientes) if (c.getEmail().equals(email) && c.login(senha)) return c;
        throw new DadoInvalidoException("Credenciais inválidas.");
    }
}