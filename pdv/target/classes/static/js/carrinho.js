function buscarTodosProdutosCarrinho(){
    fetch("http://localhost:8080/produtos", {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao consultar dados.");
        return res.json();
    })
    .then(data => {
        console.log("Dados: ", data);
        let tabela = document.getElementById("tabelaProdutos");

        let linhas = "";
        for(const el of data){
            linhas = linhas + `<tr>
                <td>${el.id}</td>
                <td>${el.nome}</td>
                <td>R$ ${el.valor}</td>
                <td><button style="margin: auto; display: block;" onclick="adicionarCarrinho(${el.id})">+</button></td>
                
            </tr>`
        }
        
        tabela.innerHTML = `
        <h2>Produtos</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Produto</th>
                    <th>Valor</th>
                    <th>Adicionar</th>
                </tr>
                ${linhas}
            </table>

        `;

    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

function listarCarrinho(){
    fetch("http://localhost:8080/carrinho", {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao listar carrinho.");
        return res.json();
    })
    .then(data => {
        console.log("Dados: ", data);
        let tabela = document.getElementById("divCarrinho");
        let estrutura = `
            <h2>Carrinho</h2>
            <table>
                <tr>
                    <th>Produto</th>
                    <th>Valor</th>
                    <th>Quantidade</th>
                    <th>Remover</th>
                </tr>
            `
        
        let linhas = "";
        for(let el of data){
            linhas = linhas + `
            <tr>
                <td>${el.produto.nome}</td>
                <td>${el.produto.valor}</td>
                <td>${el.quantidade}</td>
                <td><button style="margin: auto; display: block;" onclick="removerCarrinho(${el.produto.id})">-</button></td>
            </tr>`
        }
        tabela.innerHTML = `
            ${estrutura}
            ${linhas}
            </table>
        `
    })
    .catch(erro => {
        console.log("Erro: ", erro);
    })
}

function adicionarCarrinho(id){
    // let id = document.getElementById("idProdutoCarrinho").value;
    // let quantidade = document.getElementById("idQuantidadeCarrinho").value;
    let quantidade = 1;

    fetch(`http://localhost:8080/carrinho/inserir/${id}/${quantidade}`, {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao inserir no carrinho.");
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

function removerCarrinho(id){
    // let id = document.getElementById("idProdutoCarrinho").value;
    // let quantidade = document.getElementById("idQuantidadeCarrinho").value;
    let quantidade = -1;

    fetch(`http://localhost:8080/carrinho/inserir/${id}/${quantidade}`, {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao inserir no carrinho.");
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}