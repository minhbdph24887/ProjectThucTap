document.querySelector('select[name="color"]').addEventListener('change', function () {
    var selectedColor = this.options[this.selectedIndex].text;
    document.getElementById('detailColorForCombobox').style.backgroundColor = selectedColor;
});
