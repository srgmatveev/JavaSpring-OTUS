
function previewFile(file) {
    let reader = new FileReader()
    reader.readAsDataURL(file)
    reader.onloadend = function () {
        let img = document.createElement('img')
        img.src = reader.result
        document.getElementById('gallery').appendChild(img)
    }
}

function preventDefaults(e) {
    e.preventDefault()
    e.stopPropagation()
}

function highlight(e, elem) {
    elem.classList.add('dragover')
}

function unhighlight(e, elem) {
    if (e.type === "dragleave") {
        let dx = e.pageX - elem.offsetLeft;
        let dy = e.pageY - elem.offsetTop;

        if ((dx < 0) || (dx >= elem.offsetWidth) || (dy < 0) || (dy >= elem.offsetHeight)) {
            elem.classList.remove('dragover');
        }
    } else
        elem.classList.remove('dragover')
}

function handleDrop(e) {
    let dt = e.dataTransfer
    let files = dt.files

    handleFiles(files)
}

function dropZone_init(dropArea) {

    ['dragenter', 'dragover', 'dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, preventDefaults, false)
    });

    ['dragenter', 'dragover'].forEach(eventName => {
        dropArea.addEventListener(eventName, (e) => { highlight(e, dropArea); }, false)
    });

    ;['dragleave', 'drop'].forEach(eventName => {
        dropArea.addEventListener(eventName, (e) => { unhighlight(e, dropArea); }, false)
    })

    dropArea.addEventListener('drop', handleDrop, false);

}


function handleFiles(files) {
    files = [...files];
    files.forEach(uploadFile);
    //files.forEach(previewFile);
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
                {console.log("Готово");
                previewFile(file);
            }
            else
                throw new Error("Names could not be loaded!")
        })
        .catch(() => { console.log("Ошибка."); })
    }

window.onload = function () {
    let dropZone = document.getElementById('cover-upload-container');
    let fileInput = document.getElementById('cover-file-input');
    dropZone_init(dropZone)
    fileInput.addEventListener('change', () => {
        console.log('Change it');
        let files = fileInput.files;
        handleFiles(files);
    }, false);



}