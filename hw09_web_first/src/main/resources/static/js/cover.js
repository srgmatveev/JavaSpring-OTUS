function delete_image(elem, quest, ready_text, error_text, catch_text) {
    let isDel = confirm(quest);
    if (isDel) {
        let delNode = elem.parentNode.parentNode.parentNode;
        del_fetch(delNode.id, ready_text, error_text, catch_text);
        delNode.style.display = 'none';

    }
}

async function del_fetch(id,  ready_text, error_text, catch_text) {
    let url = `/covers/del/${id}`;
    fetch(url, {
        method: 'DELETE',
    })
        .then((response) => {
            if (response.ok) {
                console.log(ready_text);
            }
            else
                throw new Error(error_tex)
        })
        .catch(() => { console.log(catch_text); })
}