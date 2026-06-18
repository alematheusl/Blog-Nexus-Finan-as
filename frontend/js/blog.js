async function carregarArtigos() {
    const lista = document.getElementById("lista-artigos");
    if (!lista) return;

    try {
        const artigos = await listarArtigos();

        if (artigos.length === 0) {
            lista.innerHTML = "<p>Nenhum artigo publicado ainda.</p>";
            return;
        }

        lista.innerHTML = artigos.map(artigo => {
            const data = artigo.publicadoEm
                ? new Date(artigo.publicadoEm).toLocaleDateString('pt-BR')
                : new Date(artigo.criadoEm).toLocaleDateString('pt-BR');

            return `
                <div class="card-artigo">
                    ${artigo.imagemUrl ? `<img src="${artigo.imagemUrl}" alt="${artigo.titulo}" style="width:100%;height:200px;object-fit:cover;border-radius:8px;margin-bottom:12px;">` : ""}
                    <h3>${artigo.titulo}</h3>
                    <p>${artigo.resumo}</p>
                    <small>Por ${artigo.autor} &mdash; ${data}</small>
                    <br><br>
                    <a href="artigo.html?id=${artigo.id}" class="btn-ler">Ler mais</a>
                </div>
            `;
        }).join("");
    } catch (error) {
        lista.innerHTML = "<p>Erro ao carregar artigos. Verifique se o backend está rodando.</p>";
        console.error("Erro:", error);
    }
}

carregarArtigos();
