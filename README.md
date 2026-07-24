# salesManager - Sistema de Vendas (PDV)

## Sobre o Projeto

Este projeto consiste em um sistema de vendas (PDV) desenvolvido para ambiente web, permitindo o gerenciamento de clientes, produtos e pedidos de forma simples e intuitiva.

A aplicação possibilita o cadastro de clientes e produtos, a montagem de carrinhos de compras, o fechamento de pedidos e a geração automática de uma nota fiscal em PDF.

O projeto foi desenvolvido com o objetivo de aplicar conceitos de desenvolvimento Full Stack utilizando Java com Spring Boot no backend e tecnologias web no frontend.

---

## Funcionalidades

### Clientes

* Cadastro de clientes;
* Listagem de clientes;
* Pesquisa de clientes.

### Produtos

* Cadastro de produtos;
* Listagem de produtos;
* Pesquisa de produtos.

### Carrinho de Compras

* Adição de produtos ao carrinho;
* Remoção de itens do carrinho;
* Limpeza completa do carrinho;
* Associação do carrinho a um cliente;
* Cálculo automático dos valores da compra.

### Pedidos

* Fechamento do pedido;
* Exibição do resumo da compra;
* Registro do pedido no banco de dados;
* Geração automática da nota fiscal em PDF.

---

## Tecnologias Utilizadas

### Backend

* Java
* Spring Boot
* Spring Data JPA
* Maven

### Front-end

* HTML5
* CSS3
* JavaScript

### Banco de Dados

* PostgreSQL

### Bibliotecas

* iText PDF

---

## Estrutura do Projeto

```text
src
├── controller
├── service
├── repository
├── entity
├── dto
├── config
└── resources
```

---

## Banco de Dados

O sistema utiliza o PostgreSQL para armazenamento das informações.

Principais entidades:

* Clientes
* Produtos
* Pedidos
* Itens do Pedido

---

## Fluxo de Utilização

1. Cadastrar clientes;
2. Cadastrar produtos;
3. Selecionar um cliente;
4. Adicionar produtos ao carrinho;
5. Alterar ou limpar o carrinho, se necessário;
6. Fechar o pedido;
7. Visualizar os totais da compra;
8. Gerar automaticamente a nota fiscal em PDF.

---

## Objetivos do Projeto

Este projeto teve como finalidade colocar em prática conhecimentos relacionados a:

* Desenvolvimento de aplicações web com Spring Boot;
* Arquitetura em camadas;
* APIs REST;
* Persistência de dados com JPA/Hibernate;
* Integração entre frontend e backend;
* Banco de dados PostgreSQL;
* Geração de documentos PDF.

---

## Demonstração

Vídeo demonstrativo no diretório principal do projeto.
