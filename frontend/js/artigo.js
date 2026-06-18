async function carregarArtigo() {
    const params = new URLSearchParams(window.location.search);
    const id = params.get("id");

    const conteudo = document.getElementById("conteudo-artigo");

    if (!id) {
        conteudo.innerHTML = "<p>Artigo não encontrado.</p>";
        return;
    }

    try {
        const artigo = await buscarArtigo(id);

        const data = artigo.publicadoEm
            ? new Date(artigo.publicadoEm).toLocaleDateString('pt-BR')
            : new Date(artigo.criadoEm).toLocaleDateString('pt-BR');

        conteudo.innerHTML = `
            ${artigo.imagemUrl ? `<img src="${artigo.imagemUrl}" alt="${artigo.titulo}" style="width:100%;max-height:400px;object-fit:cover;border-radius:12px;margin-bottom:24px;">` : ""}
            <h1>${artigo.titulo}</h1>
            <p class="resumo">${artigo.resumo}</p>
            <p>${artigo.conteudo}</p>
            <span class="autor">Por ${artigo.autor} &mdash; ${data}</span>
        `;
    } catch (error) {
        conteudo.innerHTML = "<p>Erro ao carregar o artigo.</p>";
        console.error("Erro na busca:", error);
    }
}

carregarArtigo();
