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
const database = getDatabase(app);

// Start PIN Validation on Page Load
document.addEventListener('DOMContentLoaded', () => {
    validatePinAndRedirect();
});

// Login Form Elements
const loginForm = document.getElementById('loginForm');
const emailInput = document.getElementById('email');
const passwordInput = document.getElementById('password');
const errorMessage = document.getElementById('error-message');
const hashedPinsRef = ref(database, 'hpin');

// Login Form Submission
loginForm.addEventListener('submit', async (event) => {
    event.preventDefault();  // Prevent form from submitting until checks are done

    const ipBlocked = await isIpBlocked();  // Await the IP block check
    if (ipBlocked) {
        alert("Access blocked due to multiple failed attempts.");
        return;  // Stop the form submission if the IP is blocked
    }
    
    // Proceed with the rest of the login process
    const email = emailInput.value.trim();
    const password = passwordInput.value.trim();

    if (!email || !password) {
        alert("Please fill in all fields.");
        return;
    }

    try {
        await signInWithEmailAndPassword(auth, email, password);
        // On success, proceed with redirect
        const card = document.querySelector('.card');
        card.style.animation = 'fadeOut 0.3s ease forwards';
        setTimeout(() => {
            window.location.href = "../html's/dashboard.html";
        }, 300);
    } catch (error) {
        handleLoginError(error);  // Refactor error handling into a function
    }
});

function handleLoginError(error) {
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
}



function getHashedPinFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get("pin") || "";
}

async function getStoredHashedPin() {
    try {
        const snapshot = await get(hashedPinsRef);
        return snapshot.exists() ? snapshot.val() : null;
    } catch (error) {
        console.error("Error retrieving hashed PIN:", error);
        return null;
    }
}

// Validate PIN on page load
async function validatePinAndRedirect() {
    const urlHashedPin = getHashedPinFromUrl();
    const storedHashedPin = await getStoredHashedPin();
    if (urlHashedPin && urlHashedPin === storedHashedPin) {
        console.log("Access granted. PIN verified.");
        startCheckingBlockedIp();
    } else {
        console.error("Access Denied: Invalid PIN.");
        alert("Invalid PIN. Redirecting to preindex...");
        setTimeout(() => {
            window.location.href = "../html's/preindex.html";
        }, 1000);
    }
}

async function getUserIp() {
    // craete the url with ip addition now 
    try {
        const response = await fetch("https://api.ipify.org?format=json");
        const data = await response.json();
        return data.ip;
    } catch (error) {
        console.error("Error fetching IP:", error);
        return null;
    }
}

async function isIpBlocked() {
    const userIp = await getUserIp();
    if (!userIp) return false;

    // Check Firebase if this IP is blocked
    const blockedIpRef = ref(database, `ip/blocked/admin/gateway/${sanitizeIpForFirebase(userIp)}`);
    const snapshot = await get(blockedIpRef);
    return snapshot.exists();  // If the IP exists in Firebase, it is blocked
}


function sanitizeIpForFirebase(ip) {
    return ip.replace(/[./]/g, "_");  // Replace all periods and slashes with underscores
}




// Periodically check if the user's IP is blocked
function startCheckingBlockedIp() {
    const intervalId = setInterval(async () => {
        const ipBlocked = await isIpBlocked(); // Check if IP is blocked
        if (ipBlocked === true) {
            console.log("IP is blocked. Stopping further checks.");
            clearInterval(intervalId);  // Stop the interval if IP is blocked

            // Disable the login button
            const loginButton = document.querySelector("button[type='submit'].btn.btn-primary");
            if (loginButton) {
                loginButton.disabled = true;  // Disable the button
                loginButton.style.backgroundColor = "#ccc";  // Optional: change button color to indicate it's disabled
                loginButton.style.cursor = "not-allowed";  // Optional: change cursor style to not-allowed
            }

            alert("Access blocked due to multiple failed attempts.");
        } else {
            console.log("IP is not blocked. Continuing checks...");
        }
    }, 5000);  // Check every 5 seconds
}
