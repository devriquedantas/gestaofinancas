const API_URL = 'http://localhost:8080/api/despesas';


async function carregarDespesas() {
    try {
        const resposta = await fetch(API_URL);
        if (!resposta.ok) throw new Error('Erro ao buscar dados');

        const despesas = await resposta.json();
        renderizarTabela(despesas);
    } catch (erro) {
        console.error("Erro:", erro);
        alert("Não foi possível carregar as despesas.");
    }
}


function renderizarTabela(despesas) {
    const corpoTabela = document.getElementById('tabela-corpo');
    const totalElemento = document.getElementById('valor-total');
    corpoTabela.innerHTML = '';

    let somatorio = 0;

    despesas.forEach(d => {
        somatorio += d.valor;

        const linha = document.createElement('tr');
        linha.innerHTML = `
            <td>${d.descricao}</td>
            <td>R$ ${d.valor.toFixed(2)}</td>
            <td><span class="badge">${d.categoria}</span></td>
            <td>${new Date(d.data).toLocaleDateString('pt-BR')}</td> <td>
                <button onclick="excluirDespesa(${d.id})" class="btn-delete">🗑️</button>
            </td>
        `;
        corpoTabela.appendChild(linha);
    });


    totalElemento.innerText = `R$ ${somatorio.toFixed(2)}`;
}


async function salvarDespesa() {
    const descricao = document.getElementById('descricao').value;
    const valor = document.getElementById('valor').value;
    const categoria = document.getElementById('categoria').value;

    if (!descricao || !valor) {
        alert("Descrição e Valor são obrigatórios!");
        return;
    }

    const novaDespesa = {
        descricao,
        valor: parseFloat(valor),
        data: new Date().toISOString().split('T')[0],
        categoria: categoria || "Geral"
    };

    try {
        const resposta = await fetch(API_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novaDespesa)
        });

        if (resposta.ok) {
            limparCampos();
            await carregarDespesas();
        }
    } catch (erro) {
        alert("Erro ao salvar no servidor.");
    }
}


async function excluirDespesa(id) {
    if (!confirm("Deseja realmente excluir esta despesa?")) return;

    try {
        const resposta = await fetch(`${API_URL}/${id}`, {
            method: 'DELETE'
        });

        if (resposta.ok) {
            await carregarDespesas();
        } else {
            alert("Erro ao excluir do banco de dados.");
        }
    } catch (erro) {
        alert("Erro de conexão ao excluir.");
    }
}

function limparCampos() {
    document.getElementById('descricao').value = '';
    document.getElementById('valor').value = '';
    document.getElementById('categoria').value = '';
}


document.addEventListener('DOMContentLoaded', carregarDespesas);