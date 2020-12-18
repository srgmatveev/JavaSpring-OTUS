function bottom_footer() {
    const footer = document.getElementById("footer");
    const footHeight = footer.offsetHeight;
    footer.style.marginTop = -footHeight - 20 + "px";
    document.getElementById('main').style.paddingBottom = footHeight + 20 + "px";
}

function add_modal_listener() {
    const modal = document.getElementById('about-modal');
    const open = document.getElementById('h1-about-us');
    const close = document.getElementById('close');
    const exit_dialog = document.getElementById('about-modal-exit');
    //const returnSpan = document.getElementById('return-value');

    dialogPolyfill.registerDialog(modal);

    open.addEventListener('click', () => {
        modal.showModal();
    });

    exit_dialog.addEventListener('click', () => {
        modal.close('Exit from it');
    });


    close.addEventListener('click', () => {

        modal.close('cancelled');
    })

    modal.addEventListener('cancel', () => {
        modal.close('cancelled');
    });

    // close when clicking on backdrop
    modal.addEventListener('click', (event) => {
        if (event.target === modal) {
            modal.close('cancelled');
        }
    });

    // display returnValue
    // modal.addEventListener('close', () => {
    //     returnSpan.innerHTML = modal.returnValue;
    // });
}

add_genre_style = () => {
    const doc = document.getElementById("index_genre_select");
    if (typeof doc !== "undefined") {
        const willBlue =  document.getElementById(doc.value);
        willBlue.classList.add('will_blue');
    }
}

window.onload = function () {
    add_genre_style();
    bottom_footer();
    add_modal_listener();
}

