function click_add_genre_to_book_button(curElement) {
    const bookIdElem = document.getElementById("book_id");
    const bookId = bookIdElem.innerText;
    console.log(`Add genre to book ${bookId}`);
    if (bookId === '') {
        console.error('Empty book_id');
        return;
    }

    const genreId = curElement.parentNode.parentNode.id;
    console.log(`genre id ${genreId} for book with id ${bookId}`);
    if (genreId === '') {
        console.error(`Empty genre for book with id ${bookId}`);
        return;
    }

    const url = `/books/genre/${bookId}/${genreId}`;

    let request = new XMLHttpRequest();

    request.open('PUT', url, true);
    request.timeout = 3000;
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            // hide_genre_on_delete(_id);
            console.log(`Success add genre ${genreId} for book  ${bookId}`);
        }
        else {
            console.error(`Error add genre ${genreId} for book  ${bookId}`);
        }
    }

    request.ontimeout = function () {
        console.error(`Error by timeout add genre ${genreId} for book  ${bookId}`);
    }
    request.send();
}

function return_to_book(){
    const bookId = document.getElementById('book_id').innerText;
    console.log(bookId);
    const url = `add?id=${bookId}`;
    console.log(url);
    window.open(url,'_self');
}