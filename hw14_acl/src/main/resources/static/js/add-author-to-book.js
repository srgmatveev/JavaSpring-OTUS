function add_book_authors(url_path, alert_message) {
    add_book(url_path, alert_message);
}


function add_book_genres(url_path, alert_message){
    add_book(url_path, alert_message);
}

function add_book(url_path, alert_message) {
    const id = document.getElementById("id");
    if (id == undefined) {
        console.error("Not defined element with id == 'id'");
        return;
    }
    if (!id.value) {
          alert(`${alert_message}`);
        return;
    }
    window.open(url_path,"_self");
}