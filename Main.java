public class Main {
    public static void main(String[] args) {
        
        Administrador admin = new Administrador("Admin", "admin@loja.com", "123");

        Notebook note = new Notebook("Notebook Gamer", "Dell", 4500.00, 5,"Intel i7", 16, 512);

        Smartphone phone = new Smartphone("Smartphone Top", "Samsung", 2500.00, 10,256, 8, 6.7);

        Periferico mouse = new Periferico("Mouse Sem Fio", "Logitech", 150.00, 20,"Mouse", "Wireless");

        admin.alterarPreco(mouse, 139.90);
        admin.adicionarEstoque(note, 2);

        System.out.println("=== Produtos criados ===");
        System.out.println("Notebook: id=" + note.getId() + " | " + note.getNome() + " | R$ " + note.getPreco() + " | estoque=" + note.getEstoque());
        System.out.println("Phone:   id=" + phone.getId() + " | " + phone.getNome() + " | R$ " + phone.getPreco() + " | estoque=" + phone.getEstoque());
        System.out.println("Mouse:   id=" + mouse.getId() + " | " + mouse.getNome() + " | R$ " + mouse.getPreco() + " | estoque=" + mouse.getEstoque());

        
        Cliente cliente = new Cliente("João", "joao@email.com", "senha", "123.456.789-00");

        System.out.println("\n=== Autenticação ===");
        System.out.println("Senha correta? " + cliente.autenticar("senha"));
        System.out.println("Senha errada?  " + cliente.autenticar("x"));

        
        System.out.println("\n=== Carrinho ===");
        cliente.adicionarAoCarrinho(note, 1);
        cliente.adicionarAoCarrinho(phone, 1);
        cliente.adicionarAoCarrinho(mouse, 2);

        Carrinho carrinho = cliente.getCarrinho();
        System.out.println("Itens no carrinho: " + carrinho.getItens().size());
        System.out.println("Total do carrinho: R$ " + carrinho.calcularTotal());

        for (ItemCarrinho item : carrinho.getItens()) {
            System.out.println("- " + item.getProduto().getNome()
                    + " | qtd=" + item.getQuantidade()
                    + " | unit=R$ " + item.getPrecoUnitario()
                    + " | subtotal=R$ " + item.getSubtotal());
        }
        
        System.out.println("\n=== Pedido ===");
        Pedido pedido = cliente.finalizarPedido();
        System.out.println("Pedido id=" + pedido.getId() + " | status=" + pedido.getStatus() + " | total=R$ " + pedido.getTotal());
        System.out.println("Carrinho após finalizar está vazio? " + cliente.getCarrinho().estaVazio());

        
        System.out.println("\n=== Pagamento ===");
        Pagamento pagamento = new Pagamento(pedido.getTotal(), "PIX");
        pedido.pagar(pagamento);

        System.out.println("Pagamento status=" + pagamento.getStatus());
        System.out.println("Pedido status=" + pedido.getStatus());
    }
}
