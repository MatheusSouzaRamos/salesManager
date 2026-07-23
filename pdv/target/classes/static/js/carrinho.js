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
            <td>R$ ${el.produto.valor}</td>
            <td  style="text-align: center;">${el.quantidade}</td>
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

async function limparCarrinho(){
    const res = await fetch("http://localhost:8080/carrinho/limpar", {
        method: "DELETE",
        headers : {
            "Accept": "application/json",
            "Content-type": "application/json"
        }
    })
    
    if(!res.ok){
        throw new Error("Erro ao limpar carrinho.");
    }

    listarCarrinho();

}

async function listarClientes(){
    const res = await fetch("http://localhost:8080/clientes", {
        method: "GET",
        headers : {
            "Accept":"application/json",
            "Content-type": "application/json"
        }
    })

    if(!res.ok){
        throw new Error("Erro ao buscar Clientes.");
    }

    const data = await res.json();
    const spam = document.getElementById("selecaoCliente");


    if(data.length === 0){
        spam.innerHTML = `<p>Não há clientes cadastrados.</p>`
    }else{
        let linhas = "";

        for(let el of data){
            linhas += `<option value = "${el.id}">${el.id} - ${el.nome}</option>`
        }

        spam.innerHTML = `
            <select id="selectCliente">
                ${linhas}
            </select>
            <button onclick="fecharPedido()">Fechar Pedido</button>
        `
    }
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

    const data = await res.json();
    console.log(data);

    
    let totais = document.getElementById("totaisCarrinho");
    totais.innerHTML = `
    <p>Total R$ ${data[0]}   Total de Itens: ${data[1]}   Produtos: ${data[2]} <button onclick="limparCarrinho()">Limpar Carrinho</button></p>
    ` 
    
    listarClientes();
    
}

async function fecharPedido(){
    const select = document.getElementById("selectCliente");
    const id = select.value;
    console.log(id);

    const res = await fetch(`http://localhost:8080/carrinho/fechar/${id}`, {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Contenty-type": "application/json"
        }
    });

    if(!res.ok){
        throw new Error("Erro ao gravar pedido.");
    }

    limparCarrinho();
    buscarTodosPedidos();
}