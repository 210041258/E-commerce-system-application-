import { initializeApp } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-app.js";
import { getAuth, signInWithEmailAndPassword } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-auth.js";
import { getDatabase, ref, get } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-database.js";

// Primary Firebase Config
const firebaseConfig = {
    apiKey: "AIzaSyCzT77IureChCmwMkYq45oMVpmNTJgXYUw",
    authDomain: "iutianbookshop.firebaseapp.com",
    databaseURL: "https://iutianbookshop-default-rtdb.asia-southeast1.firebasedatabase.app",
    projectId: "iutianbookshop",
    storageBucket: "iutianbookshop.appspot.com",
    messagingSenderId: "300755004711",
    appId: "1:300755004711:web:fb9a9033b13b016cc78d02",
    measurementId: "G-64VGT3VT75"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const auth = getAuth(app);
const db = getDatabase(app);

// Start PIN Validation on Page Load
document.addEventListener('DOMContentLoaded', () => {
    validatePinAndRedirect();
});

// Login Form Elements
const loginForm = document.getElementById('loginForm');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const errorMessage = document.getElementById('error-message');

// Login Form Submission
loginForm.addEventListener('submit', (event) => {
    event.preventDefault();

    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    if (!email || !password) {
        alert("Please fill in all fields.");
        return;
    }

    signInWithEmailAndPassword(auth, email, password)
        .then(() => {
            const card = document.querySelector('.card');
            card.style.animation = 'fadeOut 0.3s ease forwards';
            setTimeout(() => {
                window.location.href = "../html's/dashboard.html";
            }, 300);
        })
        .catch((error) => {
            const errorCode = error.code;
            let errorMessageText;

            switch (errorCode) {
                case 'auth/wrong-password':
                    errorMessageText = "Incorrect password.";
                    break;
                case 'auth/user-not-found':
                    errorMessageText = "Account not found. Please check your email or sign up.";
                    break;
                case 'auth/too-many-requests':
                    errorMessageText = "Account temporarily blocked due to too many login attempts. Try again later.";
                    break;
                case 'auth/user-disabled':
                    errorMessageText = "This account has been disabled. Please contact support.";
                    break;
                case 'auth/invalid-email':
                    errorMessageText = "Invalid email format.";
                    break;
                default:
                    errorMessageText = "Login failed. Please try again.";
            }

            alert(errorMessageText);
        });
});

// Function to Get PIN from Firebase Database
async function getPinFromFirebase() {
    const pinRef = ref(db, 'pin');
    try {
        const snapshot = await get(pinRef);
        if (snapshot.exists()) {
            const pin = snapshot.val();
            return pin;
        } else {
            return null;
        }
    } catch (error) {
        alert(`Error retrieving PIN: ${error.message}`);
        return null;
    }
}

// Function to Get PIN from URL
function getPinFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    const urlPin = urlParams.get("pin") || "";
    return urlPin;
}

// Validate PIN and Redirect if Mismatch
async function validatePinAndRedirect() {
    const urlPin = getPinFromUrl();
    const storedPin = await getPinFromFirebase();

    if (!urlPin || !storedPin) {
        alert("Access Denied: Invalid or missing PIN. Redirecting...");
        setTimeout(() => {
            window.location.href = "../html's/preindex.html";
        }, 3000);
        return;
    }

    if (urlPin === storedPin) {
        alert("PIN matched. Access granted.");
    } else {
        alert("PIN mismatch detected. Redirecting to preindex.html.");
        setTimeout(() => {
            window.location.href = "../html's/preindex.html";
        }, 3000);
    }
}
