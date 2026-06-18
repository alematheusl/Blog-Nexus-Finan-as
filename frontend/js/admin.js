if (localStorage.getItem("logado") !== "true") {
    alert("Você precisa estar logado para acessar esta página.");
    window.location.href = "login.html";
}

let editandoId = null;

async function carregarLista() {
    const lista = document.getElementById("lista-admin");
    if (!lista) return;

    try {
        const artigos = await listarArtigos();

        if (artigos.length === 0) {
            lista.innerHTML = "<p>Nenhum artigo cadastrado ainda.</p>";
            return;
        }

        lista.innerHTML = artigos.map(artigo => `
            <div class="card-admin">
                <div>
                    <strong>${artigo.titulo}</strong>
                    <span>Por ${artigo.autor}</span>
                </div>
                <div class="card-admin-acoes">
                    <button onclick="prepararEdicao(${artigo.id})">Editar</button>
                    <button class="btn-excluir" onclick="removerArtigo(${artigo.id})">Excluir</button>
                </div>
            </div>
        `).join("");
    } catch (error) {
        lista.innerHTML = "<p>Erro ao carregar artigos.</p>";
        console.error(error);
    }
}

document.getElementById("form-artigo").addEventListener("submit", async (e) => {
    e.preventDefault();

    let imagemUrl = null;
    const imagemInput = document.getElementById("imagem");

    if (imagemInput.files.length > 0) {
        const formData = new FormData();
        formData.append("arquivo", imagemInput.files[0]);

        const respostaUpload = await fetch("http://localhost:8080/upload", {
            method: "POST",
            body: formData
        });

        if (respostaUpload.ok) {
            imagemUrl = await respostaUpload.text();
        } else {
            alert("Erro ao fazer upload da imagem.");
            return;
        }
    }

    const dados = {
        titulo: document.getElementById("titulo").value,
        resumo: document.getElementById("resumo").value,
        conteudo: document.getElementById("conteudo").value,
        autor: document.getElementById("autor").value,
        publicado: true,
        imagemUrl: imagemUrl
    };

    try {
        if (editandoId) {
            await atualizarArtigo(editandoId, dados);
            editandoId = null;
        } else {
            await criarArtigo(dados);
        }

        e.target.reset();
        document.getElementById("btn-cancelar").hidden = true;
        carregarLista();
    } catch (error) {
        alert("Erro ao salvar artigo. Verifique o backend.");
        console.error(error);
    }
});

async function prepararEdicao(id) {
    try {
        const artigo = await buscarArtigo(id);
        editandoId = id;
        document.getElementById("titulo").value = artigo.titulo;
        document.getElementById("resumo").value = artigo.resumo;
        document.getElementById("conteudo").value = artigo.conteudo;
        document.getElementById("autor").value = artigo.autor;
        document.getElementById("btn-cancelar").hidden = false;
        window.scrollTo({ top: 0, behavior: 'smooth' });
    } catch (error) {
        alert("Erro ao carregar artigo para edição.");
        console.error(error);
    }
}

async function removerArtigo(id) {
    if (!confirm("Tem certeza que deseja excluir este artigo?")) return;

    try {
        await excluirArtigo(id);
        carregarLista();
    } catch (error) {
        alert("Erro ao excluir artigo.");
        console.error(error);
    }
}

document.getElementById("btn-cancelar").addEventListener("click", () => {
    editandoId = null;
    document.getElementById("form-artigo").reset();
    document.getElementById("btn-cancelar").hidden = true;
});

carregarLista();
