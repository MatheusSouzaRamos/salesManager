function buscarTodosClientes(){
    fetch("http://localhost:8080/clientes", {
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
        let tabela = document.getElementById("tabelaClientes");

        let linhas = "";
        for(const el of data){
            linhas + linhas + "<tr>"
            linhas = linhas + `<td>${el.id}</td>`
            linhas = linhas + `<td>${el.nome}</td>`
            linhas = linhas + `<td>${el.telefone}</td>`
            linhas = linhas + `<td>${el.cpf}</td>`
            linhas = linhas + "</tr>"
        }
        
        tabela.innerHTML = `
        <h2>Clientes</h2>
            <table>
                <tr>
                    <th>ID</th>
                    <th>Nome</th>
                    <th>Telefone</th>
                    <th>CPF</th>
                </tr>
                ${linhas}
            </table>

        `;
    })
    .catch(erro => {
        console.log("Erro: ", erro)
    })
}

async function buscarClienteId(){
    const idBuscar = document.getElementById("idbuscarcliente").value;
   
    if (isNaN(Number(idBuscar)) || idBuscar.trim() === "") {
        return "";
    }

    const res = await fetch(`http://localhost:8080/clientes/${idBuscar}`, {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })

    if(!res.ok) {
        throw new Error("Erro ao consultar dados.");
    }

    const data = await res.json();
    console.log(data);


    linhas = `
        <tr>
            <td>${data.id}</td>
            <td>${data.nome}</td>
            <td>${data.telefone}</td>
            <td>${data.cpf}</td>
        </tr>
    `
    return linhas;

}

async function buscarClientesNome(){
    const nomeBuscar = document.getElementById("idbuscarcliente").value;

    if(!nomeBuscar || nomeBuscar.trim() === ""){
        return "";
    }

    const res = await fetch(`http://localhost:8080/clientes/buscar/${nomeBuscar}`, {
        method: "GET",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        }
    })

    if(!res.ok) {
        throw new Error("Erro ao consultar dados.");
    }

    const data = await res.json();
    console.log(data);
    linhas = "";

    for(const el of data){
        linhas = linhas + `
            <tr>
                <td>${el.id}</td>
                <td>${el.nome}</td>
                <td>${el.telefone}</td>
                <td>${el.cpf}</td>
            </tr>
        `
    }
    // console.log("linhas", linhas)
    return linhas; 
}

async function buscarClientes(){
    const campo = document.getElementById("idbuscarcliente").value;

    if(!campo || campo.trim() === ""){
        buscarTodosClientes();
        return;
    }

    let linhas = await buscarClienteId() + await buscarClientesNome();
    let tabela = document.getElementById("tabelaClientes");

    tabela.innerHTML = `
        <h2>Clientes</h2>
        <table>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Telefone</th>
                <th>CPF</th>
            </tr>
            ${linhas}
        </table>
    `
}

function inserirCliente(){
    const nomeInserir = document.getElementById("nomeinserircliente").value;
    const telefoneInserir = document.getElementById("telefoneinserircliente").value;
    const cpfInserir = document.getElementById("cpfinserircliente").value;

    fetch("http://localhost:8080/clientes", {
        method: "POST",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        },
        body: JSON.stringify({
            nome: nomeInserir,
            telefone: telefoneInserir,
            cpf: cpfInserir
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

function atualizarCliente(){
    const idAtualizar = document.getElementById("ideditarcliente").value;
    const nomeAtualizar = document.getElementById("nomeeditarcliente").value;
    const telefoneAtualizar = document.getElementById("telefoneeditarcliente").value;
    const cpfAtualizar = document.getElementById("cpfeditarcliente").value;

    fetch(`http://localhost:8080/clientes/${idAtualizar}`, {
        method: "PUT",
        headers: {
            "Accept" : "application/json",
            "Content-type" : "application/json"
        },
        body: JSON.stringify({
            nome: nomeAtualizar,
            telefone: telefoneAtualizar,
            cpf: cpfAtualizar
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