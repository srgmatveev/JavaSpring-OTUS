function click_add_author_to_book_button(curElement) {
    const bookIdElem = document.getElementById("book_id");
    const bookId = bookIdElem.innerText;
    console.log(`Add author to book ${bookId}`);
    if (bookId === '') {
        console.error('Empty book_id');
        return;
    }

    const authorId = curElement.parentNode.parentNode.parentNode.id;

    if (authorId === '') {
        console.error(`Empty author for book with id ${bookId}`);
        return;
    }

    const url = `/books/${bookId}/${authorId}`;

    let request = new XMLHttpRequest();

    request.open('PUT', url, true);
    request.timeout = 3000;
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            // hide_genre_on_delete(_id);
            console.log(`Success add author ${authorId} for book  ${bookId}`);
        }
        else {
            console.error(`Error add author ${authorId} for book  ${bookId}`);
        }
    }

    request.ontimeout = function () {
        console.error(`Error by timeout add author ${authorId} for book  ${bookId}`);
    }
    request.send();
}