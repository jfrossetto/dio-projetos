
document.body.onload = adicionaDivsCarrosel();

function adicionaDivsCarrosel() {
    const imagens = ["img/mini1.jpg", "img/mini2.jpg", "img/mini3.jpg", "img/mini4.jpg", "img/mini5.jpg",
        "img/mini6.jpg", "img/mini7.jpg", "img/mini8.jpg", "img/mini9.jpg", "img/mini10.jpg"]

    var divCarrosel = document.getElementById("carrosel");
    imagens.forEach(i => {
        var divImg = document.createElement('div');
        divImg.className = 'item';
        var img = document.createElement('img');
        img.className = 'box-filme';
        img.src = i;
        img.alt = '';
        img.srcset = '';
        divImg.appendChild(img);
        console.log(divImg);
        divCarrosel.appendChild(divImg);
    });
}

$('.owl-carousel').owlCarousel({
    loop: true,
    margin: 10,
    nav: false,
    responsive: {
        0: {
            items: 1
        },
        600: {
            items: 3
        },
        1000: {
            items: 5
        }
    }
})
