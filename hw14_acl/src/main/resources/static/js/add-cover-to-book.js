function save_image_to_book(curElement) {
    const bookIdElem = document.getElementById("book_id");
    const bookId = bookIdElem.innerText;
    console.log(`Add cover to book ${bookId}`);
    if (bookId === '') {
        console.error('Empty book_id');
        return;
    }

    const coverId = curElement.parentNode.parentNode.id;
    console.log(`cover id ${coverId} for book with id ${bookId}`);
    if (coverId === '') {
        console.error(`Empty cover for book with id ${bookId}`);
        return;
    }

    const url = `/books/cover/${bookId}/${coverId}`;

    let request = new XMLHttpRequest();

    request.open('PUT', url, true);
    request.timeout = 3000;
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            console.log(`Success add cover ${coverId} for book  ${bookId}`);
        }
        else {
            console.error(`Error add cover ${coverId} for book  ${bookId}`);
        }
    }

    request.ontimeout = function () {
        console.error(`Error by timeout add cover ${coverId} for book  ${bookId}`);
    }
    request.send();
}

function return_to_book() {
    const bookId = document.getElementById('book_id').innerText;
    console.log(bookId);
    const url = `add?id=${bookId}`;
    console.log(url);
    window.open(url, '_self');
}