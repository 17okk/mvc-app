const wrapper = document.querySelector('.wrapper');
const loginLink = document.querySelector('.login-link');
const registerLink = document.querySelector('.register-link');
const btnPopup = document.querySelector('.btnLogin-popup');
const iconClose = document.querySelector('.icon-close');

registerLink.addEventListener('click', ()=> {
    wrapper.classList.add('active');
});

loginLink.addEventListener('click', ()=> {
    wrapper.classList.remove('active');
});

btnPopup.addEventListener('click', ()=> {
    wrapper.classList.add('active-popup');
});

iconClose.addEventListener('click', ()=> {
    wrapper.classList.remove('active-popup');
});

function validateLogin() {
    let valid = true;

    // Clear previous error messages
    document.getElementById('email-error').innerText = '';
    document.getElementById('password-error').innerText = '';

    const email = document.getElementById('email');
    const password = document.getElementById('password');

    // Validate email
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email.value.match(emailRegex)) {
        document.getElementById('email-error').innerText = 'Please enter a valid email address.';
        valid = false;
    }

    // Validate password (must be at least 8 characters)
    if (password.value.length < 8) {
        document.getElementById('password-error').innerText = 'Password must be at least 8 characters long.';
        valid = false;
    }

    return valid;
}

function validateRegister() {
    let valid = true;

    // Clear previous error messages
    document.getElementById('username-error').innerText = '';
    document.getElementById('email-register-error').innerText = '';
    document.getElementById('password-register-error').innerText = '';

    const username = document.getElementById('username');
    const email = document.getElementById('email-register');
    const password = document.getElementById('password-register');

    // Validate username (must be at least 3 characters)
    if (username.value.length < 3) {
        document.getElementById('username-error').innerText = 'Username must be at least 3 characters long.';
        valid = false;
    }

    // Validate email
    const emailRegex = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
    if (!email.value.match(emailRegex)) {
        document.getElementById('email-register-error').innerText = 'Please enter a valid email address.';
        valid = false;
    }

    // Validate password (must be at least 8 characters)
    if (password.value.length < 8) {
        document.getElementById('password-register-error').innerText = 'Password must be at least 8 characters long.';
        valid = false;
    }

    return valid;
}
