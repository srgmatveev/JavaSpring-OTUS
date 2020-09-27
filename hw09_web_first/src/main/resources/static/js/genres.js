function add_genres_delete_listener() {
    const modal = document.getElementById('delete-genre');
    const close = document.getElementById('delete-genre-close');
    const exit = document.getElementById('delete-genre-cancel');
    const del = document.getElementById('delete-genre-delete');

    dialogPolyfill.registerDialog(modal);

    close.addEventListener('click', () => {
        modal.close('cancelled');
    });


    exit.addEventListener('click', () => {
        modal.close('cancelled');
    });

    del.addEventListener('click', () => {
        modal.close('Accepted');

    });

}

function click_delete_genre_button(currentElement) {
    const modal = document.getElementById('delete-genre');
    let parent = currentElement.parentNode.parentNode.parentNode;
    const _id = parent.id;
    const name = parent.querySelector('.genre_row_text').innerText;
    modal.querySelector('#delete_genre_id').value = _id;
    modal.querySelector('#delete_genre_name').value = name;
    modal.showModal();
    if (!modal) {
        return;
    }
    let body_genre_del = get_children(modal, 'delete-genre-body');
    let inputs = get_children(body_genre_del, 'delete-genre-inputs');
    set_width(inputs);
}

function set_width(elem){
    if (typeof elem === 'undefined') return;
    let children = elem.childNodes;
    let wdt = elem.offsetWidth +"px";
    for (let i = 0; i < children.length; i++) {
        if (typeof children[i].classList !== 'undefined' && children[i].tagName.toLowerCase() === 'input') {
            children[i].style.width = wdt;
            console.log(children[i].style.width);
        }
    }
}

function get_children(elem, className) {
    if (typeof elem === 'undefined') return;
    let children = elem.childNodes;
    for (let i = 0; i < children.length; i++) {
        if (typeof children[i].classList !== 'undefined') {
            if (children[i].classList.contains(className))
                return children[i];
        }
    }
}

window.onload = function () {
    add_genres_delete_listener();
}