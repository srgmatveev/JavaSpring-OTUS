function clear_events(elem, eventsStr) {
    events = eventsStr.split(/\s+/);
    events.forEach(el => {
        elem.addEventListener(el, () => {
            return false;
        });
    });
}




function events_init(elem) {

    clear_events(elem, 'drag dragstart dragend dragover dragenter dragleave drop')
    elem.addEventListener('dragover', (event) => {
        elem.classList.add('dragover');
        event.preventDefault();
        event.stopPropagation()
    });
    elem.addEventListener('dragenter', (event) => {
        elem.classList.add('dragover');
        event.preventDefault();
        event.stopPropagation();
    });

    elem.addEventListener('dragleave', (e) => {
        let dx = e.pageX - elem.offsetLeft;
        let dy = e.pageY - elem.offsetTop;

        if ((dx < 0) || (dx >= elem.offsetWidth) || (dy < 0) || (dy >= elem.offsetHeight)) {
            elem.classList.remove('dragover');
        }
    });

    elem.addEventListener('drop', (event) => {
        console.log('Drop it');
        event.preventDefault();
        event.stopPropagation();
        elem.classList.remove('dragover');
        let files = event.dataTransfer.files;
        sendFiles(files);
    });
}


function sendFiles(files) {
    ([...files]).forEach(uploadFile)
}


function uploadFile(file) {
    let url = '/covers/add'
    let formData = new FormData()
    formData.append('file', file)
    fetch(url, {
        method: 'POST',
        body: formData
    })
        .then((response) => {
            if (response.ok)
                console.log("Готово");
            else
                throw new Error("Names could not be loaded!")
        })
        .catch(() => { console.log("Ошибка."); })
}

window.onload = function () {
    let dropZone = document.getElementById('cover-upload-container');
    let fileInput = document.getElementById('cover-file-input');
    events_init(dropZone)
    fileInput.addEventListener('change', () => {
        console.log('Change it');
        let files = fileInput.files;
        sendFiles(files);
    });


    /*
        dropZone.on('dragover dragenter', function () {
            dropZone.addClass('dragover');
        });
    
        dropZone.on('dragleave', function (e) {
            let dx = e.pageX - dropZone.offset().left;
            let dy = e.pageY - dropZone.offset().top;
            if ((dx < 0) || (dx > dropZone.width()) || (dy < 0) || (dy > dropZone.height())) {
                dropZone.removeClass('dragover');
            }
        });
    
        dropZone.on('drop', function (e) {
            dropZone.removeClass('dragover');
            let files = e.originalEvent.dataTransfer.files;
            sendFiles(files);
        });
    
        $('#file-input').change(function () {
            let files = this.files;
            sendFiles(files);
        });
    
    
        function sendFiles(files) {
            let maxFileSize = 5242880;
            let Data = new FormData();
            $(files).each(function (index, file) {
                if ((file.size <= maxFileSize) && ((file.type == 'image/png') || (file.type == 'image/jpeg'))) {
                    Data.append('images[]', file);
                };
            });
    
            $.ajax({
                url: dropZone.attr('action'),
                type: dropZone.attr('method'),
                data: Data,
                contentType: false,
                processData: false,
                success: function (data) {
                    alert('Файлы были успешно загружены!');
                }
            });
        }
        */
}