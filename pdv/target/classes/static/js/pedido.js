async function buscarTodosPedidos(){
    const res = await fetch("http://localhost:8080/pedidos", {
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
    console.log("DADOS", data)

    let tabela = document.getElementById("tabelaPedidos");

    linhas = "";

    for(const el of data){

        let valorTotal = 0;

        for(const val of el.itens){
            valorTotal += (val.quantidade * val.valorUnitario);
        }
        

        linhas += `
            <tr>
                <td>${el.id}</td>
                <td>${el.cliente.id} - ${el.cliente.nome}</td>
                <td style="text-align: center;">${valorTotal}</td>
                <td style="text-align: center;">{NOTA DO PEDIDO}</td>

            </tr>
        `
    }

    tabela.innerHTML = `
    <table>
        <th>ID Pedido</th>
        <th>Cliente</th>
        <th style="text-align: center;">Valor Total (R$)</th>
        <th style="text-align: center;">Nota do Pedido</th>
        ${linhas}
    </table>`

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