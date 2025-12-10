package ecommerce;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static Pessoa usuarioLogado = null;
    private static Configuracao config = new Configuracao();

    public static void main(String[] args) {
        // 1. Carregar Configurações
        if (config.carregar()) {
            System.out.println("Bem-vindo de volta, " + config.getNomeUser() + "! (Tema: " + config.getTema() + ")");
        } else {
            System.out.println(">>> Configuração Inicial <<<");
            System.out.print("Seu nome: ");
            String nome = scanner.nextLine();
            config.salvar(nome, "CLARO");
        }

        // 2. Inicializar Banco de Dados
        SistemaEcommerce.inicializarDados();

        // 3. Loop Principal
        while (true) {
            if (usuarioLogado == null) {
                menuInicial();
            } else if (usuarioLogado instanceof Administrador) {
                menuAdmin();
            } else if (usuarioLogado instanceof Cliente) {
                menuCliente();
            }
        }
    }

    // --- MENUS DE NAVEGAÇÃO ---

    private static void menuInicial() {
        System.out.println("\n=== LOGIN E-COMMERCE ===");
        System.out.println("1. Entrar (Login)");
        System.out.println("2. Criar Nova Conta (Cadastrar)");
        System.out.println("0. Sair");
        System.out.print("Opção: ");

        String op = scanner.nextLine();
        if (op.equals("1")) fazerLogin();
        else if (op.equals("2")) cadastrarNovoCliente();
        else if (op.equals("0")) System.exit(0);
    }

    private static void fazerLogin() {
        System.out.print("Email: "); String email = scanner.nextLine();
        System.out.print("Senha: "); String senha = scanner.nextLine();
        try {
            usuarioLogado = SistemaEcommerce.fazerLogin(email, senha);
            System.out.println("Login realizado com sucesso! Olá, " + usuarioLogado.getNome());
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    // --- PAINEL DO ADMINISTRADOR ---
    private static void menuAdmin() {
        System.out.println("\n=== PAINEL ADMIN (Gerenciar Entidades) ===");
        System.out.println("1. Gerenciar Produtos");
        System.out.println("2. Gerenciar Categorias");
        System.out.println("3. Gerenciar Fornecedores");
        System.out.println("4. Gerenciar Cupons");
        System.out.println("5. Gerenciar Pedidos");
        System.out.println("6. Gerenciar Cartões");
        System.out.println("7. Gerenciar Usuários (Admins e Clientes)");
        System.out.println("0. Logout");
        System.out.print("Opção: ");

        switch (scanner.nextLine()) {
            case "1": crudProduto(); break;
            case "2": crudCategoria(); break;
            case "3": crudFornecedor(); break;
            case "4": crudCupom(); break;
            case "5": crudPedido(); break;
            case "6": crudCartao(); break;
            case "7": crudUsuario(); break;
            case "0": usuarioLogado = null; break;
            default: System.out.println("Opção inválida.");
        }
    }

    // --- CRUDs ESPECÍFICOS ---

    private static void crudProduto() {
        System.out.println("\n--- PRODUTOS ---");
        System.out.println("1.Listar 2.Criar 3.Atualizar 4.Remover");
        String op = scanner.nextLine();
        try {
            if (op.equals("1")) SistemaEcommerce.listarProdutos().forEach(p -> System.out.println(p.gerarDetalhes()));
            if (op.equals("2")) {
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Preço: "); double preco = Double.parseDouble(scanner.nextLine());
                System.out.print("Estoque: "); int est = Integer.parseInt(scanner.nextLine());
                SistemaEcommerce.cadastrarProduto(new Produto(nome, preco, est));
                System.out.println("Produto cadastrado!");
            }
            if (op.equals("3")) {
                System.out.print("ID do Produto: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                SistemaEcommerce.atualizarProduto(id, nome, -1);
            }
            if (op.equals("4")) {
                System.out.print("ID do Produto: ");
                SistemaEcommerce.removerProduto(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudCategoria() {
        System.out.println("\n--- CATEGORIAS ---");
        System.out.println("1.Listar 2.Criar 3.Atualizar 4.Remover");
        String op = scanner.nextLine();
        try {
            if (op.equals("1")) SistemaEcommerce.listarCategorias().forEach(c -> System.out.println(c.gerarDetalhes()));
            if (op.equals("2")) {
                System.out.print("Nome: "); String nome = scanner.nextLine();
                System.out.print("Descrição: "); String desc = scanner.nextLine();
                SistemaEcommerce.cadastrarCategoria(new Categoria(nome, desc));
            }
            if (op.equals("3")) {
                System.out.print("ID: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                SistemaEcommerce.atualizarCategoria(id, nome);
            }
            if (op.equals("4")) {
                System.out.print("ID: "); SistemaEcommerce.removerCategoria(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudFornecedor() {
        System.out.println("\n--- FORNECEDORES ---");
        System.out.println("1.Listar 2.Criar 3.Atualizar 4.Remover");
        String op = scanner.nextLine();
        try {
            if (op.equals("1")) SistemaEcommerce.listarFornecedores().forEach(f -> System.out.println(f.gerarDetalhes()));
            if (op.equals("2")) {
                System.out.print("Empresa: "); String nome = scanner.nextLine();
                System.out.print("CNPJ: "); String cnpj = scanner.nextLine();
                SistemaEcommerce.cadastrarFornecedor(new Fornecedor(nome, cnpj));
            }
            if (op.equals("3")) {
                System.out.print("CNPJ Atual: "); String cnpj = scanner.nextLine();
                System.out.print("Novo Nome: "); String nome = scanner.nextLine();
                SistemaEcommerce.atualizarFornecedor(cnpj, nome);
            }
            if (op.equals("4")) {
                System.out.print("CNPJ: "); SistemaEcommerce.removerFornecedor(scanner.nextLine());
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudCupom() {
        System.out.println("\n--- CUPONS ---");
        System.out.println("1.Listar 2.Criar 3.Atualizar 4.Remover");
        String op = scanner.nextLine();
        try {
            if (op.equals("1")) SistemaEcommerce.listarCupons().forEach(c -> System.out.println(c.gerarDetalhes()));
            if (op.equals("2")) {
                System.out.print("Código: "); String cod = scanner.nextLine();
                System.out.print("Desconto (%): "); double desc = Double.parseDouble(scanner.nextLine());
                SistemaEcommerce.cadastrarCupom(new Cupom(cod, desc));
            }
            if (op.equals("3")) {
                System.out.print("Código: "); String cod = scanner.nextLine();
                System.out.print("Nova %: "); double desc = Double.parseDouble(scanner.nextLine());
                SistemaEcommerce.atualizarCupom(cod, desc);
            }
            if (op.equals("4")) {
                System.out.print("Código: "); SistemaEcommerce.removerCupom(scanner.nextLine());
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudPedido() {
        System.out.println("\n--- PEDIDOS ---");
        System.out.println("1.Listar Todos 2.Atualizar Status 3.Remover/Cancelar");
        String op = scanner.nextLine();
        try {
            if(op.equals("1")) SistemaEcommerce.listarPedidos().forEach(p -> System.out.println(p.gerarDetalhes()));
            if(op.equals("2")) {
                System.out.print("ID Pedido: "); int id = Integer.parseInt(scanner.nextLine());
                System.out.print("Novo Status: "); String status = scanner.nextLine();
                SistemaEcommerce.atualizarStatusPedido(id, status);
            }
            if(op.equals("3")) {
                System.out.print("ID Pedido: "); SistemaEcommerce.removerPedido(Integer.parseInt(scanner.nextLine()));
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudCartao() {
        System.out.println("\n--- CARTÕES ---");
        System.out.println("1.Listar 2.Criar 3.Atualizar 4.Remover");
        String op = scanner.nextLine();
        try {
            if(op.equals("1")) SistemaEcommerce.listarCartoes().forEach(c -> System.out.println(c.gerarDetalhes()));

            // CREATE (Adicionado)
            if(op.equals("2")) {
                System.out.print("Num Cartão: "); String num = scanner.nextLine();
                System.out.print("Titular: "); String tit = scanner.nextLine();
                SistemaEcommerce.salvarCartao(new CartaoCredito(0, num, tit));
                System.out.println("Cartão salvo!");
            }

            if(op.equals("3")) {
                System.out.print("Num Cartão: "); String num = scanner.nextLine();
                System.out.print("Novo Titular: "); String tit = scanner.nextLine();
                SistemaEcommerce.atualizarCartao(num, tit);
            }
            if(op.equals("4")) {
                System.out.print("Num Cartão: "); SistemaEcommerce.removerCartao(scanner.nextLine());
            }
        } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
    }

    private static void crudUsuario() {
        System.out.println("\n--- GERENCIAR USUÁRIOS ---");
        System.out.println("1. Listar Admins");
        System.out.println("2. Listar Clientes");
        System.out.println("3. Cadastrar Novo Admin");
        System.out.println("4. Atualizar Admin (Setor)");
        System.out.println("5. Remover Admin");
        System.out.println("6. Remover Cliente");
        System.out.print("Opção: ");

        String op = scanner.nextLine();

        // 1. Listar
        if(op.equals("1")) SistemaEcommerce.listarAdmins().forEach(a -> System.out.println(a.gerarDetalhes()));
        if(op.equals("2")) SistemaEcommerce.listarClientes().forEach(c -> System.out.println(c.gerarDetalhes()));

        // 3. Cadastrar Admin
        if(op.equals("3")) {
            try {
                System.out.print("Nome/CPF/Email/Senha/Setor (ENTER entre cada): ");
                SistemaEcommerce.cadastrarAdmin(new Administrador(scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine(), scanner.nextLine()));
                System.out.println("Admin cadastrado!");
            } catch (Exception e) { System.out.println("Erro: " + e.getMessage()); }
        }

        // 4. Atualizar Admin
        if(op.equals("4")) {
            System.out.print("CPF do Admin: "); String cpf = scanner.nextLine();
            System.out.print("Novo Setor: "); String setor = scanner.nextLine();
            SistemaEcommerce.atualizarAdmin(cpf, setor);
        }

        // 5. Remover Admin
        if(op.equals("5")) {
            System.out.print("CPF do Admin: "); SistemaEcommerce.removerAdmin(scanner.nextLine());
        }

        // 6. Remover Cliente
        if(op.equals("6")) {
            System.out.print("CPF do Cliente: "); SistemaEcommerce.removerCliente(scanner.nextLine());
            System.out.println("Cliente removido.");
        }
    }

    // --- PAINEL DO CLIENTE ---
    private static void menuCliente() {
        System.out.println("\n--- ÁREA DO CLIENTE ---");
        System.out.println("1. Comprar Produtos");
        System.out.println("2. Meus Dados (Atualizar Endereço)");
        System.out.println("0. Sair");
        System.out.print("Opção: ");

        String op = scanner.nextLine();
        if (op.equals("1")) realizarCompra();
        if (op.equals("2")) {
            System.out.println(((Cliente)usuarioLogado).gerarDetalhes());
            System.out.print("Deseja atualizar endereço? (S/N): ");
            if (scanner.nextLine().equalsIgnoreCase("S")) {
                System.out.print("Novo Endereço: ");
                SistemaEcommerce.atualizarCliente((Cliente)usuarioLogado, scanner.nextLine());
            }
        }
        if (op.equals("0")) usuarioLogado = null;
    }

    private static void realizarCompra() {
        try {
            Pedido pedido = new Pedido((Cliente) usuarioLogado);
            System.out.println("--- CARRINHO DE COMPRAS ---");
            while(true) {
                System.out.print("Digite o ID do Produto (ou 0 para finalizar): ");
                int id = Integer.parseInt(scanner.nextLine());
                if (id == 0) break;

                Produto p = SistemaEcommerce.buscarProduto(id);
                p.reduzirEstoque(1);
                pedido.adicionarItem(p);
                System.out.println(p.getNome() + " adicionado!");
            }

            if(pedido.getValorTotal() > 0) {
                System.out.println("Subtotal: R$ " + pedido.getValorTotal());
                System.out.print("Possui Cupom? Digite o código (ou Enter para pular): ");
                String cod = scanner.nextLine();
                if(!cod.isEmpty()) {
                    Cupom c = SistemaEcommerce.buscarCupom(cod);
                    if(c != null) {
                        pedido.aplicarDesconto(c);
                        System.out.println("Cupom aplicado!");
                    } else {
                        System.out.println("Cupom inválido.");
                    }
                }
                SistemaEcommerce.registrarPedido(pedido);
                System.out.println("Compra Finalizada! Total Pago: R$ " + pedido.getValorTotal());
            } else {
                System.out.println("Carrinho vazio.");
            }
        } catch (Exception e) { System.out.println("Erro na compra: " + e.getMessage()); }
    }

    private static void cadastrarNovoCliente() {
        System.out.println("\n--- NOVO CADASTRO ---");
        try {
            System.out.print("Nome Completo: "); String nome = scanner.nextLine();
            System.out.print("CPF: "); String cpf = scanner.nextLine();
            System.out.print("Email: "); String email = scanner.nextLine();
            System.out.print("Senha: "); String senha = scanner.nextLine();
            System.out.print("Endereço: "); String end = scanner.nextLine();

            SistemaEcommerce.cadastrarCliente(new Cliente(nome, cpf, email, senha, end));
            System.out.println("Cadastro realizado! Faça login para continuar.");
        } catch (Exception e) { System.out.println("Erro ao cadastrar: " + e.getMessage()); }
    }
}