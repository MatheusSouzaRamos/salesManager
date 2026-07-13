function listarCarrinho(){
    fetch("http://localhost:8080/carrinho", {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })
    .then(res => {
        if(!res.ok) throw new Error("Erro ao buscar dados do carrinho.");
        return res.json;
    })
    .then(data => {
        console.log("Dados:", data);
    })
    .catch(erro => {
        console.log("Erro: ", erro);
    })
}
