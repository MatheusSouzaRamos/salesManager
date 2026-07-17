async function buscarTodosProdutosCarrinho(){
    const res = await fetch("http://localhost:8080/produtos", {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })

    if(!res.ok){
        throw new Error("Erro ao consultar dados.");
    }
        
    const data = await res.json();
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
}

async function listarCarrinho(){
    const res = await fetch("http://localhost:8080/carrinho", {
        method: "GET",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        }
    })

    if(!res.ok) {
        throw new Error("Erro ao listar carrinho.");
    }

    const data = await res.json();
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

    totaisCarrinho();
}

async function adicionarCarrinho(id){
    // let id = document.getElementById("idProdutoCarrinho").value;
    // let quantidade = document.getElementById("idQuantidadeCarrinho").value;
    let quantidade = 1;

    const res = await fetch(`http://localhost:8080/carrinho/inserir/${id}/${quantidade}`, {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })
    
    if(!res.ok){
        throw new Error("Erro ao inserir no carrinho.");
    }

    listarCarrinho();
    totaisCarrinho();
}

async function removerCarrinho(id){
    // let id = document.getElementById("idProdutoCarrinho").value;
    // let quantidade = document.getElementById("idQuantidadeCarrinho").value;
    let quantidade = -1;

    const res = await fetch(`http://localhost:8080/carrinho/inserir/${id}/${quantidade}`, {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })

    if(!res.ok) {
        throw new Error("Erro ao inserir no carrinho.");
    }

    listarCarrinho();
    totaisCarrinho();
}

async function totaisCarrinho(){
    const res = await fetch("http://localhost:8080/carrinho/totais", {
        method: "GET",
        headers : {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })

    if(!res.ok){
        throw new Error("Erro ao buscar totais do carrinho");
    }

    data = await res.json();
    console.log(data);

    let totais = document.getElementById("totaisCarrinho");
    totais.innerHTML = `
        <p>Total R$ ${data[0]}   Total de Itens: ${data[1]}   Produtos: ${data[2]}</p>
        
        ` 
    
}