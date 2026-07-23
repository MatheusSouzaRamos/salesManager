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

    data.sort((a, b) => b.id - a.id);

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
                <td style="text-align: center;">R$ ${valorTotal}</td>
                <td style="text-align: center;"><button onclick="pdfPedido(${el.cliente.id})">Imprimir Pedido</button></td>

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

async function buscarPedidoId(){
    const idBuscar = document.getElementById("idbuscarpedido").value;

    if(!idBuscar || idBuscar === null || idBuscar.trim() === ""){
        buscarTodosPedidos();
        return;
    }

    const res = await fetch(`http://localhost:8080/pedidos/${idBuscar}`, {
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

    let tabela = document.getElementById("tabelaPedidos");
    linhas = "";

    let valorTotal = 0;

    for(const val of data.itens){
        valorTotal += (val.quantidade * val.valorUnitario);
    }
    
    linhas += `
        <tr>
            <td>${data.id}</td>
            <td>${data.cliente.id} - ${data.cliente.nome}</td>
            <td style="text-align: center;">R$ ${valorTotal}</td>
            <td style="text-align: center;"><button onclick="pdfPedido(${data.cliente.id})">Imprimir Pedido</button></td>
        </tr>
    `

    tabela.innerHTML = `
    <table>
        <th>ID Pedido</th>
        <th>Cliente</th>
        <th style="text-align: center;">Valor Total (R$)</th>
        <th style="text-align: center;">Nota do Pedido</th>
        ${linhas}
    </table>`
}

async function pdfPedido(id) {
    window.open(`http://localhost:8080/pedidos/pdf/${id}`, "_blank");
}