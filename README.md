# Projeto: Sistema de E-commerce (LP2 - Unidade III)

Este projeto implementa um sistema de E-commerce via console, focado em aplicar os conceitos avançados da Programação Orientada a Objetos (POO), persistência de arquivos e tratamento de exceções, conforme especificado na disciplina do Professor Jerffeson Gomes Dutra.

## 1. Integrantes da Equipe

- Iury Fredson Germano Miranda  
- Ricson Miranda da Nóbrega  
- José Kauã de Lima Souza  
- Fábio Félix Rodrigues  

## 2. Visão Geral do Sistema

O sistema foi desenvolvido em Java e simula as operações de uma loja virtual, permitindo o gerenciamento de vendas, estoque, usuários e fluxo de caixa através de um menu interativo.

A arquitetura do projeto cumpre rigorosamente os requisitos da Unidade 3:

- **CRUD Completo (8 Entidades):** O sistema permite Criar, Ler, Atualizar e Remover registros de 8 entidades distintas:
  1. `Produto`
  2. `Cliente`
  3. `Administrador`
  4. `Categoria`
  5. `Fornecedor`
  6. `Cupom`
  7. `Pedido`
  8. `Cartão de Crédito`

- **Persistência de Dados (Arquivos):** Utiliza a classe `Configuracao` para salvar e carregar preferências do usuário (nome e tema) em um arquivo físico (`config.txt`), garantindo que o sistema tenha "memória" entre execuções.

- **Interfaces (2x):**
  - `Autenticavel`: Define o contrato de login implementado por `Cliente` e `Administrador`.
  - `Relatorio`: Padroniza a exibição de detalhes (`gerarDetalhes()`) em todas as entidades listáveis.

- **Classes Abstratas (2x):**
  - `Pessoa`: Classe base para `Cliente` e `Administrador`.
  - `Pagamento`: Classe base para `CartaoCredito`.

- **Tratamento de Exceções:** Implementa exceções personalizadas (`DadoInvalidoException`, `EstoqueInsuficienteException`) para validar regras de negócio, como preços negativos ou tentativas de compra sem estoque.

- **Encapsulamento e Segurança:** Atributos protegidos, menus segregados (Painel Admin x Área do Cliente) e validação de login.

## 3. Instruções para Compilar o Sistema (Via Terminal)

O projeto utiliza a estrutura de pacotes do Java (`package ecommerce;`). Certifique-se de estar na **pasta raiz** do projeto (a pasta que contém a pasta `ecommerce`).

1. Abra o terminal na raiz do projeto.  
2. Compile todos os arquivos `.java` localizados dentro do pacote:

    ```bash
    javac ecommerce/*.java
    ```
   *(Isso gerará os arquivos `.class` dentro da mesma pasta)*

## 4. Instruções para Executar o Sistema

1. Ainda no diretório raiz, execute o sistema chamando a classe principal através do pacote:

    ```bash
    java ecommerce.Main
    ```
2. O sistema iniciará verificando se existe o arquivo de configuração. Se for a primeira vez, ele pedirá seu nome para configuração inicial.

3. **Logins para Teste Rápido (Seed):**
   - **Administrador:** `admin@ufrn.br` | Senha: `admin123`
   - **Cliente:** `aluno@ufrn.br` | Senha: `123456`
