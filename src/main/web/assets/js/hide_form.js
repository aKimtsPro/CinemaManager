const hideBtn = document.getElementById("hide-btn");
const form = document.getElementById("form-container")

hideBtn.onclick = toggleForm;

function toggleForm(){
    form.hidden = !form.hidden;
}