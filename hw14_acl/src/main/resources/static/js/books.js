add_books_delete_listener = () => {
    const modal = document.getElementById('delete-book');
    const close = document.getElementById('delete-book-close');
    const exit = document.getElementById('delete-book-cancel');
    const del = document.getElementById('delete-book-delete');

    dialogPolyfill.registerDialog(modal);

    close.addEventListener('click', () => {
        modal.close('cancelled');
    });


    exit.addEventListener('click', () => {
        modal.close('cancelled');
    });

    del.addEventListener('click', () => {
        modal.close('Accepted');
        delete_book();
    });

}


function click_delete_book_button(currentElement) {
    const modal = document.getElementById('delete-book');
    let parent = currentElement.parentNode.parentNode.parentNode;
    const _id = parent.id;
    const name = parent.querySelector('.book_row_text').innerText;
    modal.querySelector('#delete_book_id').value = _id;
    modal.querySelector('#delete_book_name').value = name;
    modal.showModal();
    if (!modal) {
        return;
    }


    let body_genre_del = get_children(modal, 'delete-genre-body');
    let inputs = get_children(body_genre_del, 'delete-genre-inputs');
    set_width(inputs);
}

delete_book = () => {
    const _id = document.getElementById('delete_book_id').value
    let url = '/books/del/' + _id
    let request = new XMLHttpRequest()
    console.log(url)
    request.timeout = 3000;
    request.open('DELETE', url, true)
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            hide_book_on_delete(_id);
            console.log(`Success delete book for ${_id}`);
        }
        else {
            console.log(`Error delete book for ${_id}`);
        }
    }

    request.ontimeout = function () {
        console.error(`Error by timeout delete book ${_id}`);
    }
    request.send()
}

function hide_book_on_delete(id) {
    const doc = document.getElementById(id);
    doc.style.display = 'none';
}


function set_width(elem) {
    if (typeof elem === 'undefined') return;
    let children = elem.childNodes;
    let wdt = elem.offsetWidth + "px";
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
    add_books_delete_listener();
    let perfEntries = performance.getEntriesByType("navigation");

    if (perfEntries[0].type === "back_forward") {
        location.reload();
    }
}