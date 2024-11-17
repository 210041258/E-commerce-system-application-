
function checkConnection() {
    if (navigator.onLine) {
        document.body.style.visibility = 'visible';
    } else {
        document.body.style.visibility = 'hidden';
    }
}


setInterval(checkConnection, 500);


window.addEventListener('online', () => {
    document.body.style.visibility = 'visible';
});

window.addEventListener('offline', () => {
    document.body.style.visibility = 'hidden';
});


import { initializeApp } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-app.js";
import { getDatabase, ref, set, get, onValue, remove } from "https://www.gstatic.com/firebasejs/9.19.1/firebase-database.js";



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

let attemptCounter = 0;
const maxAttempts = 3;
const blockedIpsCacheKey = "blocked_ips";

// Firebase reference for pin and refresh_pin
const pinsRef = ref(database, 'pin'); // Single value at the 'pin' path
const refreshPinRef = ref(database, 'refresh_pin'); // Path for refresh setting
const hashedPinsRef = ref(database, 'hpin'); // Path for hashed PIN
/*const _location_Ref = ref(database, 'hpin'); // Path for hashed PIN
const _deatils_Ref = ref(database, 'hpin'); // Path for hashed PIN*/









function hashPin(pin) {
    return CryptoJS.SHA256(pin).toString();
}

function containsCommonSubPattern(pin) {
    // Check for repeating digits (like '11', '22', etc.)
    for (let i = 0; i < pin.length - 1; i++) {
        if (pin[i] === pin[i + 1]) {
            return true;
        }
    }
    
    // Check for sequential increasing or decreasing numbers
    for (let i = 0; i < pin.length - 1; i++) {
        if (parseInt(pin[i]) + 1 === parseInt(pin[i + 1]) ||
            parseInt(pin[i]) - 1 === parseInt(pin[i + 1])) {
            return true;
        }
    }

    return false;
}

// Function to select the keyboard layout
// Function to select the keyboard layout with a weighted preference for STANDARD
function selectKeyboardLayout() {
    // Define available layouts
    const layouts = {
        STANDARD: [
            [7, 8, 9],
            [4, 5, 6],
            [1, 2,3]
        ],
        MIRRORED: [
            [1, 2, 3],
            [4, 5, 6],
            [7, 8, 9]
        ],
        LINEAR: [1, 2, 3, 4, 5, 6, 7, 8, 9]
    };

    // Define weights for each layout selection
    const layoutWeights = {
        STANDARD: 0.5, // 70% chance
        MIRRORED: 0.25, // 15% chance
        LINEAR: 0.70 // 15% chance
    };

    // Create an array for weighted random selection
    const weightedLayouts = [];
    for (const [key, weight] of Object.entries(layoutWeights)) {
        const count = Math.round(weight * 100);
        for (let i = 0; i < count; i++) {
            weightedLayouts.push(key);
        }
    }

    // Randomly select a layout based on the weighted array
    const selectedKey = weightedLayouts[Math.floor(Math.random() * weightedLayouts.length)];

    // Return the selected layout
    return layouts[selectedKey];
}


// Function to check if the digit is too close to the previous one based on the selected layout
function isTooCloseOnSelectedLayout(prevDigit, nextDigit, selectedLayout) {
    // Linear layout case
    if (Array.isArray(selectedLayout) && selectedLayout.length === 9) {
        const prevIndex = selectedLayout.indexOf(prevDigit);
        const nextIndex = selectedLayout.indexOf(nextDigit);
        const distance = Math.abs(prevIndex - nextIndex);
        return distance < 2; // True if digits are too close
    }

    // For 3x3 grid layouts (STANDARD, MIRRORED)
    function getPosition(digit, layout) {
        for (let row = 0; row < 3; row++) {
            for (let col = 0; col < 3; col++) {
                if (layout[row][col] === digit) {
                    return { row, col };
                }
            }
        }
        return null;
    }

    const prevPos = getPosition(prevDigit, selectedLayout);
    const nextPos = getPosition(nextDigit, selectedLayout);
    if (!prevPos || !nextPos) return false;

    const distance = Math.abs(prevPos.row - nextPos.row) + Math.abs(prevPos.col - nextPos.col);
    return distance > 2;
}

// Function to generate a random PIN (adjust length as needed)
function generateRandomPin(length = 4) {
    
    const selectedLayout = selectKeyboardLayout();


    // List of common PIN patterns to avoid
    const commonPatterns = new Set([
        // Repeated Digits
        '0000', '1111', '2222', '3333', '4444', '5555', '6666', '7777', '8888', '9999',
        '0011', '1122', '2233', '3344', '5566', '7788', '8899',

        // Sequential Numbers
        '0123', '1234', '2345', '3456', '4567', '5678', '6789',
        '9876', '8765', '7654', '6543', '5432', '4321', '3210',

        // Palindrome Numbers
        '0110', '1221', '1331', '1441', '2112', '3223', '4334', '5445', '9009',

        // Years
        '1999', '2000', '2010', '2020', '2023', '2024',
        '1980', '1985', '1990', '1995', '2001',

        // Popular Dates
        '0101', '1225', '0407', '0314',

        // Repeating and Alternating Patterns
        '1212', '1313', '1414', '2323', '4343', '5656',
        '1221', '3443', '4554', '7887',

        // Edge Cases
        '0001', '0009', '0010', '0099', '9998', '9999',

        // Keyboard Patterns
        '2580', '1593', '7531',

        // Significant Numbers
        '1234', '1111', '0000', '1212', '6969', '2580',
        '9110', '1122',

        // Double Digit Combos
        '1122', '3344', '5566', '7788', '9900',
        '1212', '3434', '5656', '7878', '9090'
    ]);

    let pin;

    do {
        pin = '';
        let prevDigit = null;

        for (let i = 0; i < length; i++) {
            let newDigit;
            do {
                newDigit = Math.floor(Math.random() * 9) + 1; // Generate digit from 1 to 9
            } while (newDigit === prevDigit || isTooCloseOnSelectedLayout(prevDigit, newDigit, selectedLayout));

            pin += newDigit;
            prevDigit = newDigit;
        }
    } while (commonPatterns.has(pin) || containsCommonSubPattern(pin));
    
    
   return pin;
}


// Submit the random PIN to Firebase (as a single value)
function submitPinToFirebase(pin) {
    const hashedPin = hashPin(pin);
    set(pinsRef, pin)
        .then(() => console.log("Plain PIN submitted successfully to Firebase"))
        .catch((error) => console.error("Error submitting plain PIN:", error));

    set(hashedPinsRef, hashedPin)
        .then(() => console.log("Hashed PIN submitted successfully to Firebase"))
        .catch((error) => console.error("Error submitting hashed PIN:", error));
}

// Refresh PIN based on the refresh variable
function refreshPinBasedOnVariable() {
    onValue(refreshPinRef, (snapshot) => {
        const refreshTrigger = snapshot.val();
        if (refreshTrigger === true) {
            const newPin = generateRandomPin();
            submitPinToFirebase(newPin); // Store new PIN and its hash
            console.log("PIN refreshed and updated in Firebase:", newPin);
            set(refreshPinRef, false); // Reset the trigger
        }
    });
}

// Sanitize IP to be stored in Firebase
function sanitizeIpForFirebase(ip) {
    return ip;  // Replace all periods and slashes with underscores
}

// Function to store blocked IP in Firebase
async function storeBlockedIpInFirebase(ip) {
    if (!ip) return;

    const sanitizedIp = sanitizeIpForFirebase(ip);  // Sanitize the IP
    const blockedIpRef = ref(database, `ip/blocked/admin/gateway/${sanitizedIp}`);
    try {
        await set(blockedIpRef, true);  // Store the sanitized IP as blocked
        console.log("IP successfully stored as blocked in Firebase:", sanitizedIp);
    } catch (error) {
        console.error("Error storing IP in Firebase:", error);
    }
}

// Check if the IP is blocked in Firebase
async function isIpBlocked_db() {
    const userIp = await getUserIp();
    if (!userIp) return false;

    // Check Firebase if this IP is blocked
    const blockedIpRef = ref(database, `ip/blocked/admin/gateway/${sanitizeIpForFirebase(userIp)}`);
    const snapshot = await get(blockedIpRef);
    return snapshot.exists();  // If the IP exists in Firebase, it is blocked
}

// Remove blocked IP from Firebase
async function removeBlockedIpFromFirebase(ip) {
    if (!ip) return;

    const sanitizedIp = sanitizeIpForFirebase(ip);  // Sanitize the IP
    const blockedIpRef = ref(database, `ip/blocked/admin/gateway/${sanitizedIp}`);
    
    try {
        await remove(blockedIpRef);  // Remove the sanitized IP from Firebase
        console.log("IP successfully removed from blocked list in Firebase:", sanitizedIp);
    } catch (error) {
        console.error("Error removing IP from Firebase:", error);
    }
}

// Function to block the user IP
async function blockUserIp() {
    const userIp = await getUserIp();
    if (!userIp) return;

    let blockedIps = JSON.parse(localStorage.getItem(blockedIpsCacheKey)) || [];
    if (!blockedIps.includes(userIp)) {
        blockedIps.push(userIp);
        localStorage.setItem(blockedIpsCacheKey, JSON.stringify(blockedIps));
        console.log("User IP blocked:", userIp);
    }

    // Store IP in Firebase
    await storeBlockedIpInFirebase(userIp);
}

function hashIp(ip) {
    return CryptoJS.SHA256(ip).toString();
}


// Get the user's IP address
async function getUserIp() {
    try {
        const response = await fetch("https://api.ipify.org?format=json");
        const data = await response.json();
        return hashIp(data.ip);
    } catch (error) {
        console.error("Error fetching IP:", error);
        return null;
    }
}

// Check if the IP is blocked locally
async function isIpBlocked() {
    const userIp = await getUserIp();
    if (!userIp) return false;

    const blockedIps = JSON.parse(localStorage.getItem(blockedIpsCacheKey)) || [];
    return blockedIps.includes(userIp);
}

// Periodically check if the user's IP is blocked
function startCheckingBlockedIp() {
    const intervalId = setInterval(async () => {
        const ipBlocked = await isIpBlocked_db(); // Check if IP is blocked
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
    }, 10000);  // Check every 5 seconds
}

function toggleSubmitButtonTemporarily(submitButton) {
    submitButton.disabled = true; // Disable the button
    setTimeout(() => {
        submitButton.disabled = false; // Re-enable the button after 2 seconds
    }, 1500); // 2000ms (2 seconds) delay
}

// Form submission to check user input against stored PIN
document.getElementById('pinForm').addEventListener('submit', async (event) => {
    event.preventDefault();
    const submitButton = document.getElementById('pinForm').querySelector('button');
    toggleSubmitButtonTemporarily(submitButton);
    const userPin = document.getElementById('pin').value.trim();

    if (userPin === "1111") {
        const userIp = await getUserIp();
        removeBlockedIpFromLocalStorage(userIp);  // Remove IP from localStorage
        removeBlockedIpFromFirebase(userIp);     // Remove IP from Firebase
    }

    if (await isIpBlocked()) {
        alert("Access blocked due to multiple failed attempts.");
        document.getElementById('pin').disabled = true;
        document.getElementById('pinForm').querySelector('button').disabled = true;
        return;
    }

    checkUserPin();
});


// Remove blocked IP from localStorage
function removeBlockedIpFromLocalStorage(ip) {
    let blockedIps = JSON.parse(localStorage.getItem(blockedIpsCacheKey)) || [];
    blockedIps = blockedIps.filter((blockedIp) => blockedIp !== ip);  // Filter out the IP to unblock
    localStorage.setItem(blockedIpsCacheKey, JSON.stringify(blockedIps));  // Update localStorage
    console.log("IP removed from localStorage:", ip);
}

async function checkUserPin() {
    startCheckingBlockedIp();
    const userPin = document.getElementById('pin').value.trim();
    const hashedUserPin = hashPin(userPin);

    try {
        const hashed_ip = await getUserIp(); // Await the IP hash retrieval separately
        const snapshot = await get(hashedPinsRef);
        const storedHashedPin = snapshot.val();

        if (hashedUserPin === storedHashedPin) {
            console.log("PIN matched, redirecting...");
            // Form the URL after both hashedUserPin and hashed_ip are resolved
            const redirectUrl = `../index.html?pin=${hashedUserPin}&ip=${hashed_ip}`;
            window.location.href = redirectUrl;
        } else {
            attemptCounter++;
            alert(`Incorrect PIN. You have ${maxAttempts - attemptCounter} attempts left.`);

            if (attemptCounter >= maxAttempts) {
                alert("Maximum attempts reached. Access blocked.");
                await blockUserIp();
                document.getElementById('pin').disabled = true;
                document.getElementById('pinForm').querySelector('button').disabled = true;
            }
        }
    } catch (error) {
        console.error("Error during PIN check:", error);
        alert("Error checking PIN. Please try again later.");
    }
}



// Initial setup for random PIN
const randomPin = generateRandomPin(4);
submitPinToFirebase(randomPin);
setInterval(refreshPinBasedOnVariable,5000);
