(() => {
    function getRandomInt(max) {
        return Math.floor(Math.random() * max);
    }

    function processar(jogos) {
        let html = "";
        for (const jogo of jogos) {
            html +=
                `<div class="col">
                    <div class="card">
                        <img src="${jogo.display}" class="card-img">
                        <div class="card-body">
                            <h5 class="card-title">${jogo.titulo}</h5>
                            <p class="card-text">${jogo.descricao}</p>
                            <a href="${jogo.url}" class="btn btn-sm btn-primary">Ver na Steam</a>
                        </div>
                    </div>
                </div>`;
        }
        document.querySelector("#game-cards").innerHTML = html;
    }

    function main() {
        let id = 0;
        let i = 3;
        const proms = [];
        while (i-- > 0) {
            id = 1 + getRandomInt(6);
            proms.push(
                fetch("/jogo", { method: "POST", body: JSON.stringify({ idJogo: id }) })
                    .then(resp => resp.json())
                    .then(json => json.valor));
        }
        Promise.all(proms).then(jogos => processar(jogos.values()));
    }

    function onready(callback) {
        if (document.readyState != "loading")
            callback();
        else
            document.addEventListener("DOMContentLoaded", callback);
    }

    onready(main);
})()