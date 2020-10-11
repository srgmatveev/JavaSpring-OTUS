function delete_image(elem, quest, ready_text, error_text, catch_text) {
    let isDel = confirm(quest);
    if (isDel) {
        let delNode = elem.parentNode.parentNode.parentNode;
        del_fetch(delNode.id, ready_text, error_text, catch_text);
        delNode.style.display = 'none';

    }
}

async function del_fetch(id, ready_text, error_text, catch_text) {
    let url = `/covers/del/${id}`;

    try {
        let responce = await fetch(url, {
            method: 'DELETE',
        });

        if (response.ok) {
            console.log(ready_text);
            return;
        }
        throw new Error(error_tex);
    } catch (err) {
        console.log(`${catch_text} : ${error_text}`);
    }
}

window.onload = function () {
    let perfEntries = performance.getEntriesByType("navigation");

    if (perfEntries[0].type === "back_forward") {
        location.reload();
    }
}