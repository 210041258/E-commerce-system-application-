// firebaseConfig.js
import { initializeApp } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-app.js";
import { getAuth } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-auth.js";
import { getDatabase } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-database.js";
import { getStorage } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-storage.js";

const firebaseConfig = {
    apiKey: "AIzaSyBsDin-lyVTkxBNsbVf6FbIH6ye3lBwPIU",
    authDomain: "test453453-bd269.firebaseapp.com",
    databaseURL: "https://test453453-bd269-default-rtdb.firebaseio.com",
    projectId: "test453453-bd269",
    storageBucket: "test453453-bd269.firebasestorage.app",
    messagingSenderId: "504507710871",
    appId: "1:504507710871:web:684dc4bbd054e4cea8d8d2",
    measurementId: "G-0DPH5DQKJ9"
  };

// Initialize Firebase
const app = initializeApp(firebaseConfig);
export const auth = getAuth(app);
export const database = getDatabase(app);
export const storage = getStorage(app);
