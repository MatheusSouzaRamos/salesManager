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
                </tr>
            `
        
        let linhas = "";
        for(let el of data){
            linhas = linhas + `
            <tr>
                <td>${el.produto.nome}</td>
                <td>${el.produto.valor}</td>
                <td>${el.quantidade}</td>
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

function adicionarCarrinho(){
    let id = document.getElementById("idProdutoCarrinho").value;
    let quantidade = document.getElementById("idQuantidadeCarrinho").value;

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