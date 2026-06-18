const API_URL = 'http://localhost:8080/artigos';
const AUTH_URL = 'http://localhost:8080/auth';

async function listarArtigos() {
    const resposta = await fetch(API_URL);
    return await resposta.json();
}

async function buscarArtigo(id) {
    const resposta = await fetch(`${API_URL}/${id}`);
    return await resposta.json();
}

async function criarArtigo(dados) {
    const resposta = await fetch(API_URL, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dados)
    });
    return await resposta.json();
}

async function atualizarArtigo(id, dados) {
    const resposta = await fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(dados)
    });
    return await resposta.json();
}

async function excluirArtigo(id) {
    await fetch(`${API_URL}/${id}`, {
        method: "DELETE"
    });
}
