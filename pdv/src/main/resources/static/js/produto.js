function listarProdutos(){
    fetch("http://localhost:8080/produtos", {
        method: "GET",
        headers: {
            "Accept": "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao buscar produtos.")
        return res.json();
    })
    .then(data => {
        console.log("Produtos: ", data);
    })
    .catch(erro => {
        console.log("Erro: ", erro);
    })
}

function cadastrarProduto(){
    // let idInserir = document.getElementById("idCadastrarProduto").value;
    let nomeInserir = document.getElementById("nomeCadastrarProduto").value;
    let valorInserir = document.getElementById("valorCadastrarProduto").value;

    fetch("http://localhost:8080/produtos", {
        method: "POST",
        headers: {
            "Accept": "application/json",
            "Content-type": "application/json"
        },
        body: JSON.stringify({
            // id: idInserir,
            nome: nomeInserir,
            valor: valorInserir
        })
    })
    .then(res =>{
        if(!res.ok) throw new Error("Erro ao inserir produto.");
        return res.json();
    })
    .then(data => {
        console.log(data);
    })
    .catch(erro => {
        console.log("Erro: ", erro);
    })
}