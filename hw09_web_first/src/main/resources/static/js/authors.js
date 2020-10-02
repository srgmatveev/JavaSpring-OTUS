function add_authors_delete_listener() {
    const modal = document.getElementById('delete-author');
    const close = document.getElementById('delete-author-close');
    const exit = document.getElementById('delete-author-cancel');
    const del = document.getElementById('delete-author-delete');

    dialogPolyfill.registerDialog(modal);

    close.addEventListener('click', () => {
        modal.close('cancelled');
    });


    exit.addEventListener('click', () => {
        modal.close('cancelled');
    });

    del.addEventListener('click', () => {
        modal.close('Accepted');
        delete_author();
    });

}

function click_delete_author_button(currentElement) {
    const modal = document.getElementById('delete-author');
    let parent = currentElement.parentNode.parentNode.parentNode;
    const _id = parent.id;
    const name = parent.querySelector('.author_row_text').innerText;
    modal.querySelector('#delete_author_id').value = _id;
    modal.querySelector('#delete_author_name').value = name;
    modal.showModal();
    if (!modal) {
        return;
    }
    let body_author_del = get_children(modal, 'delete-author-body');
    let inputs = get_children(body_author_del, 'delete-author-inputs');
    set_width(inputs);
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


function delete_author() {
    const _id = document.getElementById('delete_author_id').value
    let url = '/authors/del/' + _id
    let request = new XMLHttpRequest()
    console.log(url)
    request.open('DELETE', url, true)
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            hide_author_on_delete(_id);
            console.log(`Success delete for ${_id}`);
        }
        else {
            console.log(`Error delete for ${_id}`);
        }
    }

    request.send()
}

function hide_author_on_delete(id){
    const doc = document.getElementById(id);
    doc.style.display = 'none';
}

window.onload = function () {
    add_authors_delete_listener();
}