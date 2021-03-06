
//import {chamaApiCryptoCur} from './apicripto.js';

const ready = (callback) => {
    if (document.readyState != "loading") callback();
    else document.addEventListener("DOMContentLoaded", callback);
}

const renderData = (dados) => {
    console.log(dados);
    const criptoContent = document.querySelector('.cripto-content');
    const template = document.getElementById('cripto-template'); // as HTMLTemplateElement;
    dados.data.forEach( data => {
        const criptoCard = document.importNode(template.content, true);
        criptoCard.querySelector('.cripto-name').textContent = data.name;
        criptoCard.querySelector('.cripto-hist').textContent = data.first_historical_data;
        criptoContent.appendChild(criptoCard);
    });
}

ready(() => {
    chamaApiCryptoCur()
        .then((dados) => renderData(dados));
});
