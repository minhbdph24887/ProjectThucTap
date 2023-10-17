document.getElementById('imageInput').addEventListener('change', function(e) {
    var reader = new FileReader();
    reader.onload = function(e) {
        document.getElementById('previewImage').src = e.target.result;
    }
    reader.readAsDataURL(this.files[0]);
});