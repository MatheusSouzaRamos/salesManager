function buscarTodosPedidos(){
    fetch("http://localhost:8080/pedidos", {
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

function buscarPedidoId(){
    const idBuscar = document.getElementById("idbuscarpedido").value;

    fetch(`http://localhost:8080/pedidos/${idBuscar}`, {
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