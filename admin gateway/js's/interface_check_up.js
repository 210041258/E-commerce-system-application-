// Firebase imports
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-app.js";
import { getDatabase, ref, set, get, onValue } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-database.js";

// Firebase config
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
const database = getDatabase(app);

// Firebase reference for pin and refresh_pin
const pinsRef = ref(database, 'pin'); // Single value at the 'pin' path
const refreshPinRef = ref(database, 'refresh_pin'); // Path for refresh setting

// Function to generate a random PIN (adjust length as needed)
function generateRandomPin(length) {
    let pin = '';
    for (let i = 0; i < length; i++) {
        pin += Math.floor(Math.random() * 10); // Generate a random digit from 0-9
    }
    return pin;
}

// Submit the random PIN to Firebase (as a single value)
function submitPinToFirebase(pin) {
    set(pinsRef, pin)  // Using `set()` to store the PIN as a single value at the 'pin' path
        .then(() => {
            console.log("PIN submitted successfully to Firebase!");
        })
        .catch((error) => {
            console.error("Error submitting PIN to Firebase:", error);
        });
}

// Function to handle refreshing the PIN based on the `refresh_pin` value from Firebase
function refreshPinBasedOnVariable() {
    // Listen for changes in the 'refresh_pin' value in Firebase
    onValue(refreshPinRef, (snapshot) => {
        const refreshPinValue = snapshot.val(); // Get the refresh pin flag or value

        // Check if the refresh_pin value is set to trigger a refresh
        if (refreshPinValue === true) {
            const newPin = generateRandomPin(4);  // Generate a new random PIN
            submitPinToFirebase(newPin);  // Update Firebase with the new PIN
            console.log("PIN refreshed and updated in Firebase:", newPin);
            
            // Optionally reset the value of `refresh_pin` after use to stop further refreshes until it's triggered again
            set(refreshPinRef, false); // Reset the trigger to false after refreshing the pin
        } else {
            console.log("PIN refresh not triggered. Awaiting refresh_pin value change...");
        }
    });
}

// For testing purposes, generate and submit a random PIN on page load
let randomPin = generateRandomPin(4);
submitPinToFirebase(randomPin); // Store the generated random PIN as a single value

// Start checking for refresh trigger based on the `refresh_pin` variable in Firebase
refreshPinBasedOnVariable();


document.getElementById('pinForm').addEventListener('submit', (event) => {
    event.preventDefault(); // Prevent form submission

    checkUserPin(); // Check the user-provided PIN against the stored PIN
});

function checkUserPin() {
    // Get the PIN value from Firebase
    get(pinsRef).then((snapshot) => {
        if (snapshot.exists()) {
            const storedPin = snapshot.val(); // Get the PIN from Firebase

            const pinInput = document.getElementById('pin'); // Get the PIN entered by the user
            const pin = pinInput.value;
            const errorMessage = document.getElementById('error-message');

            // Check if the user-provided PIN matches the stored PIN
            if (pin === storedPin) {
                window.location.href = `../index.html?pin=${storedPin}`;
            } else {
                errorMessage.textContent = "Incorrect PIN. Please try again.";
                errorMessage.style.display = "block";
            }
        } else {
            const errorMessage = document.getElementById('error-message');
            errorMessage.textContent = "No data available at 'pin' path.";
            errorMessage.style.display = "block";
        }
    }).catch((error) => {
        console.error("Error retrieving PIN:", error);
        const errorMessage = document.getElementById('error-message');
        errorMessage.textContent = "Error retrieving data.";
        errorMessage.style.display = "block";
    });
}