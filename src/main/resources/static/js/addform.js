const isEndangeredCheckbox = document.getElementById("isEndangered");
const endangeredOptionsDiv = document.getElementById("endangeredOptions");
const healthSelect = document.getElementById("health");
const ageSelect = document.getElementById("age");
const submitBtn = document.getElementById("submitBtn");

if(isEndangeredCheckbox !== null){
isEndangeredCheckbox.addEventListener("change", function() {
    if (isEndangeredCheckbox.checked) {
        endangeredOptionsDiv.style.display = "block";
        checkDirtyFields();
    } else {
        endangeredOptionsDiv.style.display = "none";
        submitBtn.disabled = false;
    }
});
}
// Function to check if all required fields are dirty
if(healthSelect !== null){
function checkDirtyFields() {
 console.log('checkDirtyFields()');
    if (isEndangeredCheckbox.checked) {
         if (healthSelect.value.trim() !== "" && ageSelect.value.trim() !== "") {
            submitBtn.disabled = false;
        } else {
            submitBtn.disabled = true;
        }
    } else {
        submitBtn.disabled = true; // Enable submit button for non-endangered animals
    }
}

// Add event listeners to health and age selects
healthSelect.addEventListener("change", checkDirtyFields);
ageSelect.addEventListener("change", checkDirtyFields);
}