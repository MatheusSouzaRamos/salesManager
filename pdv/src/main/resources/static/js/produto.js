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
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

function buscarProdutoId(){
    const idBuscar = document.getElementById("idbuscarproduto").value;

    // if idBuscar vazio {......}

    fetch(`http://localhost:8080/produtos/${idBuscar}`, {
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
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

function buscarProdutoNome(){
    const nomeBuscar = document.getElementById("nomebuscarproduto").value;

    // if idBuscar vazio {......}

    fetch(`http://localhost:8080/produtos/buscar/${nomeBuscar}`, {
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
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
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