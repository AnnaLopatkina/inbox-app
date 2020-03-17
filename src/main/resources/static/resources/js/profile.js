// Get DOM Elements
const modal = document.querySelector('#my-modal');
const modalBtn = document.querySelector('#modal-btn');
const closeBtn = document.querySelector('.close');


$("#upload_btn").click(function(e){
	e.preventDefault();
    $("#upload").trigger('click');;
});

$("#submit_btn").click(function(e){
	e.preventDefault();
    $("#submit").trigger('click');;
});

$("#save_btn").click(function(e){
    e.preventDefault();
    $("#save").trigger('click');;
});

$("#sav").click(function(e){
    e.preventDefault();
    $("#save_it").trigger('click');;
});



// Events
modalBtn.addEventListener('click', openModal);
closeBtn.addEventListener('click', closeModal);
window.addEventListener('click', outsideClick);

// Open
function openModal() {
    modal.style.display = 'block';
}

// Close
function closeModal() {
    modal.style.display = 'none';
}

// Close If Outside Click
function outsideClick(e) {
    if (e.target === modal) {
        modal.style.display = 'none';
    }
}

var expanded = false;



