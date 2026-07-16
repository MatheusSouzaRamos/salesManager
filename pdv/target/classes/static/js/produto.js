function buscarTodosProdutos(){
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
            linhas + linhas + "<tr>"
            linhas = linhas + `<td>${el.id}</td>`
            linhas = linhas + `<td>${el.nome}</td>`
            linhas = linhas + `<td>R$ ${el.valor}</td>`
            linhas = linhas + "</tr>"
        }
        
        tabela.innerHTML = `
        <h2>Produtos</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Produto</th>
                    <th>Valor</th>
                </tr>
                ${linhas}
            </table>

        `;

    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

async function buscarProdutoId(){
    const idBuscar = document.getElementById("idbuscarproduto").value;

    if (isNaN(Number(idBuscar)) || idBuscar.trim() === "") {
        return "";
    }

    const res = await fetch(`http://localhost:8080/produtos/${idBuscar}`, {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    });

    if(!res.ok){
        throw new Error("Erro ao consultar dados.");
    }

    const data = await res.json();
    return `
        <tr>
            <td>${data.id}</td>
            <td>${data.nome}</td>
            <td>R$ ${data.valor}</td>
        </tr>
    `
}

async function buscarProdutoNome(){
    const nomeBuscar = document.getElementById("idbuscarproduto").value;

    if(!nomeBuscar || nomeBuscar.trim() === ""){
        return "";
    }


    const res = await fetch(`http://localhost:8080/produtos/buscar/${nomeBuscar}`, {
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

    let tabela = "";

    let linhas = "";
    for(const el of data){
        linhas + linhas + "<tr>"
        linhas = linhas + `<td>${el.id}</td>`
        linhas = linhas + `<td>${el.nome}</td>`
        linhas = linhas + `<td>R$ ${el.valor}</td>`
        linhas = linhas + "</tr>"
    }

    return linhas;

}

async function buscarProduto(){
    const campo = document.getElementById("idbuscarproduto").value;

    if(!campo || campo.trim() === ""){
        buscarTodosProdutos();
        return;
    }

    let linhas = await buscarProdutoId() + await buscarProdutoNome();
    let tabela = document.getElementById("tabelaProdutos");

    tabela.innerHTML = `
    <h2>Produtos</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Produto</th>
            <th>Valor</th>
        </tr>
        ${linhas}
    </table>
    `
}

function inserirProduto(){
    const nomeInserir = document.getElementById("nomeinserirproduto").value;
    const valorInserir = document.getElementById("valorinserirproduto").value;

    fetch("http://localhost:8080/produtos", {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        },
        body: JSON.stringify({
            nome: nomeInserir,
            valor: valorInserir
        })
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao inserir dados.");
        return res.json();
    })
    .then(data => {
        console.log("Dados: ", data);
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

function atualizarProduto(){
    const idAtualizar = document.getElementById("ideditarproduto").value;
    const nomeAtualizar = document.getElementById("nomeeditarproduto").value;
    const valorAtualizar = document.getElementById("valoreditarproduto").value;

    fetch(`http://localhost:8080/produtos/${idAtualizar}`, {
        method: "PUT",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        },
        body: JSON.stringify({
            nome: nomeAtualizar,
            valor: valorAtualizar
        })
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao inserir dados.");
        return res.json();
    })
    .then(data => {
        console.log("Dados: ", data);
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}