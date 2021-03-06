//const fetch = require("node-fetch");

//https://pro-api.coinmarketcap.com/v1/cryptocurrency/map?CMC_PRO_API_KEY=f48fff47-d046-4c00-a7b3-9d12d3850233
const api = {
    urlBase: "https://pro-api.coinmarketcap.com/v1/",
    apiCryptoCur: "cryptocurrency/map?CMC_PRO_API_KEY=",
    key: "f48fff47-d046-4c00-a7b3-9d12d3850233"
};

//export
const chamaApiCryptoCur = async () => {
    const dados =
        await
            fetch(api.urlBase + api.apiCryptoCur + api.key)
                .then((response) => {
                    if (!response.ok) throw new Error('Erro ao executar chamada ..' + response.status);
                    return response;
                })
                .then((resp) => resp.json())
                .catch((erro) => {
                    console.error(erro.message);
                });
    return dados;
};

/*
const ready = (callback) => {
    if (document.readyState != "loading") callback();
    else document.addEventListener("DOMContentLoaded", callback);
}

ready(() => {
    chamaApiCryptoCur()
        .then((dados) => console.log(dados));
});
*/