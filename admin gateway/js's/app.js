import { auth, database, storage } from './firebaseConfig.js';
import { getAuth, onAuthStateChanged, signOut } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-auth.js";
import { ref as dbRef,ref,push, set, get, update, remove } from "https://www.gstatic.com/firebasejs/9.21.0/firebase-database.js";

const contentArea = document.getElementById('content-area');
// Add this to your app.js
onAuthStateChanged(auth, (user) => {
    if (!user) {
        window.location.href = '../index.html';
    }
});


// Add this to your existing code
document.getElementById('logoutBtn').addEventListener('click', handleLogout);

async function handleLogout() {
    try {
        await signOut(auth);
        const card = document.querySelector('.card');
        card.style.animation = 'fadeOut 0.3s ease forwards';
        
        // Redirect to login page after animation
        setTimeout(() => {
            window.location.href = 'index.html';
        }, 300);
    } catch (error) {
        console.error("Error signing out:", error);
        alert("Error signing out. Please try again.");
    }
}

window.addEventListener('DOMContentLoaded', () => {
    document.querySelectorAll('.list-group-item').forEach(item => {
        item.addEventListener('click', () => {
            switch (item.id) {
                case 'bookViewBtn':
                    renderBookManagement();
                    break;
                case 'couponViewBtn':
                    renderCouponManagement();
                    break;
                case 'notificationBtn':
                    renderNotification();
                    break;
                case 'addBalanceBtn':
                    renderAddBalance();
                    break;
                case 'contactbtn':
                    contactbtn1();
                    break;
                default:
                    console.warn('No handler for this button:', item.id);
            }
        });
    });
});



function contactbtn1() {
    // Clear the content area
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = "";

    // Add a title for the page
    const title = document.createElement("h2");
    title.textContent = "Email List Management - Confirmation Page";
    contentArea.appendChild(title);

    loadContacts();
}

function renderContactsTable(listMaps, contentArea) {
    let html = `
        <br>
        <div id="search-container" class="mt-3" style="text-align: center;">
            <input type="text" id="searchBox" class="form-control" placeholder="Search by Username..." 
                   style="max-width: 400px; margin: 0 auto; border-radius: 25px; padding: 10px;" aria-label="Search by Username"/>
           <br> <button id="searchBtn" onclick="filterTable()" class="btn btn-primary" style="border-radius: 25px; padding: 10px; margin-left: 10px;" aria-label="Search">
                Search
            </button>
        </div>
        <br><br>
        <table border="1" cellpadding="10" cellspacing="0" style="width: 100%;">
            <thead>
                <tr>
                    <th>Username</th>
                    <th>List Map ID</th>
                    <th>Order ID</th>
                    <th>Order Price</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody id="tableBody"></br>
    `;

    for (const email in listMaps) {
        const maps = listMaps[email];

        for (const mapId in maps) {
            const map = maps[mapId];

            html += `
                <tr>
                    <td>${email}</td>
                    <td>${mapId}</td>
                    <td>${map.orderid}</td>
                    <td>${map.orderprice}</td>
                    <td>
                        <button onclick="confirmDelete('${email}', '${mapId}')" aria-label="Confirm & Delete">Confirm & Delete</button>
                    </td>
                </tr>
            `;
        }
    }

    html += `
            </tbody>
        </table>
    `;

    contentArea.innerHTML = html;
}
window.filterTable= filterTable;
window.confirmDelete= confirmDelete;

function filterTable() {
    const searchValue = document.getElementById('searchBox').value.toLowerCase();
    const tableRows = document.querySelectorAll('#tableBody tr');

    tableRows.forEach(row => {
        const username = row.cells[0].textContent.toLowerCase();
        row.style.display = username.includes(searchValue) ? '' : 'none';
    });

    // Keep search term active (optional improvement)
    setTimeout(() => {
        loadContacts(searchValue);
    }, 10000);
}


async function loadContacts() {
    const contentArea = document.getElementById('content-area');
    contentArea.innerHTML = '<h3>Loading Email List Maps...</h3>';

    try {
        // Reference to 'contact' node in the database
        const contactsRef = dbRef(database, 'contact');
        const snapshot = await get(contactsRef);

        if (snapshot.exists()) {
            const contacts = snapshot.val();
            renderContactsTable(contacts, contentArea);
        } else {
            contentArea.innerHTML = '<p>No contacts found in the database.</p>';
        }
    } catch (error) {
        console.error("Error loading contacts:", error);
        contentArea.innerHTML = '<p>Error loading contacts. Please try again later.</p>';
        
        // Optional: Provide a button to retry loading contacts
        const retryButton = document.createElement('button');
        retryButton.innerText = 'Retry';
        retryButton.onclick = loadContacts; // Bind the retry function
        contentArea.appendChild(retryButton);
    }
}

async function confirmDelete(email, mapId) {
    const confirmation = confirm(`Are you sure you want to delete the list map ${mapId} for ${email}?`);
    if (confirmation) {
        try {
            const mapRef = dbRef(database, `contact/${email}/${mapId}`);
            await remove(mapRef);
            alert(`List map ${mapId} for ${email} deleted successfully.`);
            loadContacts(); // Refresh the list after deletion
        } catch (error) {
            console.error("Error deleting list map:", error);
            alert("Failed to delete the list map. Please try again.");
        }
    }
}


function renderBookManagement() {
    contentArea.innerHTML = `
        <h2>Book Management</h2>
        <button id="showBookListBtn" class="btn btn-primary mb-3">Show Book List</button>
        <button id="addBookBtn" class="btn btn-primary mb-3">Add Book</button>
        <button id="searchBookBtn" class="btn btn-primary mb-3">Search Book</button>
        <button id="historyBookBtn" class="btn btn-primary mb-3">View History</button>
        <div id="searchContainer" class="mb-3" style="display: none;">
            <input type="text" id="searchInput" class="form-control" placeholder="Search by ID, Name, or Author , or Department">
        </div>
        <div id="bookList" style="display: none;">
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Name</th>
                            <th>Description</th>
                            <th>Copies</th>
                            <th>Price</th>
                            <th>Department</th>
                            <th>Semester</th>
                            <th>Cover Image</th>
                            <th>Keywords</th>
                            <th>Author</th>
                            <th>Edition</th>
                            <th>PDF Preview</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="bookTableBody"></tbody>
                </table>
            </div>
        </div>
        <div id="historyContainer" style="display: none;">
            <h3>Action History</h3>
            <ul id="historyList" class="list-group"></ul>
        </div>
    `;
    
    document.getElementById('showBookListBtn').addEventListener('click', showBookList);
    document.getElementById('addBookBtn').addEventListener('click', renderAddBookForm);
    document.getElementById('searchBookBtn').addEventListener('click', handleSearchBook);
    document.getElementById('historyBookBtn').addEventListener('click', toggleHistoryView);

    loadHistory();
}


function renderBooks(books, tableBody) {
    tableBody.innerHTML = '';
    books.forEach(book => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>${book.id}</td>
            <td>${book.name}</td>
            <td>${book.description}</td>
            <td>${book.copies}</td>
            <td>${book.price}</td>
            <td>${book.department}</td>
            <td>${book.semester}</td>
            <td><img src="${book.url}" alt="${book.name}" style="width: 50px; height: auto;"></td>
            <td>${book.keywords.join(', ')}</td>
            <td>${book.author}</td>
            <td>${book.edition}</td>
            <td><a href="${book.copy_preview}" target="_blank">Preview</a></td>
            <td>
                <button class="btn btn-sm btn-danger" onclick="deleteBook('${book.id}')">Delete</button>
                <button class="btn btn-sm btn-warning" onclick="handleEditBook('${book.id}')">Edit</button>
            </td>
        `;
        tableBody.appendChild(row);
    });
}

window.handleEditBook= handleEditBook;
window.deleteBook =deleteBook;


function showBookList() {
    const bookList = document.getElementById('bookList');
    if(bookList.style.display == 'block'){
        bookList.style.display = 'none';
        searchContainer.style.display= 'none';
    }
    bookList.style.display = 'block';
    searchContainer.style.display = 'block';
    loadBooks();
    
}

async function loadBooks() {
    const bookTableBody = document.getElementById('bookTableBody');
    bookTableBody.innerHTML = '<tr><td colspan="9">Loading books...</td></tr>';

    try {
        const booksRef = dbRef(database, 'book');
        const snapshot = await get(booksRef);

        if (snapshot.exists()) {
            const books = Object.values(snapshot.val());
            renderBooks(books, bookTableBody);
        } else {
            bookTableBody.innerHTML = '<tr><td colspan="9">No books found in the database.</td></tr>';
        }
    } catch (error) {
        console.error("Error loading books:", error);
        bookTableBody.innerHTML = '<tr><td colspan="9">Error loading books. Please try again later.</td></tr>';
    }
}




function renderAddBookForm() {
    contentArea.innerHTML = `
        <h2>Add New Book</h2>
        <form id="addBookForm">
            <!-- Other existing input fields -->
            <div class="mb-3">
                <label for="bookName" class="form-label">Name</label>
                <input type="text" class="form-control" id="bookName" required>
            </div>
            <div class="mb-3">
                <label for="bookDescription" class="form-label">Description</label>
                <textarea class="form-control" id="bookDescription" required></textarea>
            </div>
            <div class="mb-3">
                <label for="bookCopies" class="form-label">Copies</label>
                <input type="number" class="form-control" id="bookCopies" required>
            </div>
            <div class="mb-3">
                <label for="bookPrice" class="form-label">Price</label>
                <input type="number" class="form-control" id="bookPrice" step="0.01" required>
            </div>
            <div class="mb-3">
                <label for="bookDepartment" class="form-label">Department</label>
                <select class="form-control" id="bookDepartment" required>
                    <option value="">Select Department</option>
                    <option value="TVE">TVE</option>
                    <option value="CEE">CEE</option>
                    <option value="CSE">CSE</option>
                    <option value="EEE">EEE</option>
                    <option value="MPE">MPE</option>
                    <option value="BTM">BTM</option>
                </select>
            </div>
            <div class="mb-3">
                <label for="bookSemester" class="form-label">Semester</label>
                <input type="number" class="form-control" id="bookSemester" min="1" max="8" required>
            </div>
            <div class="mb-3">
                <label for="bookUrl" class="form-label">Cover Image URL</label>
                <input type="text" class="form-control" id="bookUrl" required>
            </div>
            <!-- New fields for keywords, author, edition, and PDF preview link -->
            <div class="mb-3">
                <label for="bookKeywords" class="form-label">Keywords (comma-separated)</label>
                <input type="text" class="form-control" id="bookKeywords" required>
            </div>
            <div class="mb-3">
                <label for="bookAuthor" class="form-label">Author</label>
                <input type="text" class="form-control" id="bookAuthor" required>
            </div>
            <div class="mb-3">
                <label for="bookEdition" class="form-label">Edition</label>
                <input type="text" class="form-control" id="bookEdition" required>
            </div>
            <div class="mb-3">
                <label for="copy_preview" class="form-label">PDF Preview Link</label>
                <input type="text" class="form-control" id="copy_preview" required>
            </div>
            <button type="submit" class="btn btn-primary">Add Book</button>
            <button type="button" class="btn btn-secondary" id="cancelAddBookBtn">Cancel</button>
        </form>
    `;
    
    document.getElementById('addBookForm').addEventListener('submit', handleAddBook);
    document.getElementById('cancelAddBookBtn').addEventListener('click', renderBookManagement);
}

    



async function handleAddBook(event) {
    event.preventDefault();
    
    const bookName = document.getElementById('bookName').value;
    const bookDescription = document.getElementById('bookDescription').value;
    const bookCopies = document.getElementById('bookCopies').value;
    const bookPrice = document.getElementById('bookPrice').value;
    const bookDepartment = document.getElementById('bookDepartment').value;
    const bookSemester = document.getElementById('bookSemester').value;
    const bookUrl = document.getElementById('bookUrl').value;
    const bookKeywords = document.getElementById('bookKeywords').value.split(',').map(keyword => keyword.trim());
    const bookAuthor = document.getElementById('bookAuthor').value;
    const bookEdition = document.getElementById('bookEdition').value;
    const copy_preview = document.getElementById('copy_preview').value;


    try {
        // Get the current book counter to determine the new book ID
        const bookCounterRef = dbRef(database, 'bookCounter');
        const counterSnapshot = await get(bookCounterRef);
        
        let newBookId = 1;
        if (counterSnapshot.exists()) {
            newBookId = counterSnapshot.val() + 1;  // Increment the counter
        }

        // Update the counter in the database for the next book
        await set(bookCounterRef, newBookId);

           const newBook = {
            id: String(newBookId),
            name: bookName,
            description: bookDescription,
            copies: bookCopies,
            price: bookPrice,
            department: bookDepartment,
            semester: bookSemester,
            url: bookUrl,
            keywords: bookKeywords,
            author: bookAuthor,
            edition: bookEdition,
            copy_preview: copy_preview
        };

        // Add the new book to Firebase
        const booksRef = dbRef(database, `book/${newBookId}`);
        await set(booksRef, newBook);

        // Log the history
        await logHistory('Added Book', String(newBookId), null, newBook);
        
        alert('Book added successfully.');
        renderBookManagement();

    } catch (error) {
        console.error("Error adding book:", error.message);
        alert(`Error adding book: ${error.message}. Please try again later.`);
    }
}

async function loadHistory() {
    const historyList = document.getElementById('historyList');
    if (!historyList) return;

    try {
        const historyRef = dbRef(database, 'history');
        const snapshot = await get(historyRef);

        historyList.innerHTML = '';

        if (snapshot.exists()) {
            const historyItems = Object.entries(snapshot.val())
                .map(([key, value]) => ({...value, key}))
                .sort((a, b) => b.timestamp - a.timestamp);

            historyItems.forEach(item => {
                const historyItem = document.createElement('div');
                historyItem.className = 'list-group-item d-flex justify-content-between align-items-center';
                historyItem.innerHTML = `
                    <div>
                        <strong>${new Date(item.timestamp).toLocaleString()}</strong>: 
                        ${item.action} - Book ID: ${item.bookId}
                    </div>
                    <button class="btn btn-sm btn-warning undo-button" data-history-id="${item.key}">
                        Undo
                    </button>
                `;
                historyList.appendChild(historyItem);
            });

            document.querySelectorAll('.undo-button').forEach(button => {
                button.addEventListener('click', handleUndo);
            });
        }
    } catch (error) {
        console.error("Error loading history:", error);
    }
}

async function handleUndo(event) {
    const historyId = event.target.dataset.historyId;
    try {
        const historyRef = dbRef(database, `history/${historyId}`);
        const snapshot = await get(historyRef);

        if (snapshot.exists()) {
            const historyItem = snapshot.val();
            const bookId = historyItem.bookId;
            const bookRef = dbRef(database, `book/${bookId}`);

            switch (historyItem.action) {
                case 'Added Book':
                    await remove(bookRef);
                    break;
                case 'Deleted Book':
                    if (historyItem.oldData) {
                        await set(bookRef, JSON.parse(historyItem.oldData));
                    }
                    break;
                case 'Updated Book':
                    if (historyItem.oldData) {
                        await set(bookRef, JSON.parse(historyItem.oldData));
                    }
                    break;
            }

            // Remove this history item
            await remove(historyRef);

            // Refresh book list and history
            loadBooks();
            loadHistory();

            alert('Action undone successfully.');
        }
    } catch (error) {
        console.error("Error undoing action:", error);
        alert('Error undoing action. Please try again.');
    }
}

async function logHistory(action, bookId, oldData = null, newData = null) {
    try {
        const historyRef = push(dbRef(database, 'history'));
        await set(historyRef, {
            timestamp: Date.now(),
            action: action,
            bookId: bookId,
            oldData: oldData ? JSON.stringify(oldData) : null,
            newData: newData ? JSON.stringify(newData) : null
        });
        loadHistory();
    } catch (error) {
        console.error("Error logging history:", error);
    }
}


function toggleHistoryView() {
    const historyContainer = document.getElementById('historyContainer');
    if (historyContainer.style.display === 'none') {
        historyContainer.style.display = 'block';
    } else {
        historyContainer.style.display = 'none';
    }
}

function handleSearchBook() {
    const searchValue = document.getElementById('searchInput').value.toLowerCase().trim();
    const bookTableBody = document.getElementById('bookTableBody');
    searchContainer.style.display = 'block';
    bookList.style.display = 'block';
    if (!searchValue) { // Checks for null, undefined, or empty string
        alert("Please enter a search term.");
        return; // Exit the function early if searchValue is invalid
      }
    bookTableBody.innerHTML = ''; // Clear existing content
    const loadingMessage = document.createElement('tr');
    loadingMessage.innerHTML = '<td colspan="9" class="text-center">Searching...</td>';
    bookTableBody.appendChild(loadingMessage); 

    const booksRef = dbRef(database, 'book');
    get(booksRef).then(snapshot => {
        if (snapshot.exists()) {
            const booksArray = snapshot.val();
            setTimeout(() => {
                document.getElementById('searchInput').value = "";
            
                bookTableBody.appendChild(noBooksMessage);
            }, 10000);
            const filteredBooks = Object.values(booksArray).filter(book => 
                book.name.toLowerCase().includes(searchValue) || 
                book.id.toLowerCase().includes(searchValue)||book.department.toLowerCase().includes(searchValue)
            );

            bookTableBody.innerHTML = ''; // Clear the previous search results
            if (filteredBooks.length > 0) {
                renderBooks(filteredBooks, bookTableBody);
            } else {
                const noResultsMessage = document.createElement('tr');
                noResultsMessage.innerHTML = '<td colspan="9" class="text-center">No matching books found.</td>';
                bookTableBody.appendChild(noResultsMessage);
            }
        } else {
            const noBooksMessage = document.createElement('tr');
            noBooksMessage.innerHTML = '<td colspan="9" class="text-center">No books found in the database.</td>';
            bookTableBody.appendChild(noBooksMessage);
        }
    }).catch(error => {
        console.error("Error fetching books for search:", error);
        bookTableBody.innerHTML = '<tr><td colspan="9" class="text-center text-danger"> Error searching for books. Please try again later.</td></tr>';
    });
}

async function handleEditBook(bookId) {
    const booksRef = dbRef(database, `book/${bookId}`);
    const snapshot = await get(booksRef);

    if (snapshot.exists()) {
        const book = snapshot.val();
        
        // Render the form with existing data for editing
        contentArea.innerHTML = `
        <h2>Edit Book</h2>
        <form id="editBookForm">
            <div class="mb-3">
                <label for="bookId" class="form-label">Book ID</label>
                <input type="text" class="form-control" id="bookId" value="${book.id}" readonly>
            </div>
            <div class="mb-3">
                <label for="bookName" class="form-label">Name</label>
                <input type="text" class="form-control" id="bookName" value="${book.name}">
            </div>
            <div class="mb-3">
                <label for="bookDescription" class="form-label">Description</label>
                <textarea class="form-control" id="bookDescription">${book.description}</textarea>
            </div>
            <div class="mb-3">
                <label for="bookCopies" class="form-label">Copies</label>
                <input type="number" class="form-control" id="bookCopies" value="${book.copies}">
            </div>
            <div class="mb-3">
                <label for="bookPrice" class="form-label">Price</label>
                <input type="number" class="form-control" id="bookPrice" value="${book.price}">
            </div>
            <div class="mb-3">
                <label for="bookDepartment" class="form-label">Department</label>
                <input type="text" class="form-control" id="bookDepartment" value="${book.department}">
            </div>
            <div class="mb-3">
                <label for="bookSemester" class="form-label">Semester</label>
                <input type="text" class="form-control" id="bookSemester" value="${book.semester}">
            </div>
            <div class="mb-3">
                <label for="bookUrl" class="form-label">Cover Image URL</label>
                <input type="text" class="form-control" id="bookUrl" value="${book.url}">
            </div>
            <div class="mb-3">
                <label for="bookKeywords" class="form-label">Keywords (comma-separated)</label>
                <input type="text" class="form-control" id="bookKeywords" value="${book.keywords.join(', ')}">
            </div>
            <div class="mb-3">
                <label for="bookAuthor" class="form-label">Author</label>
                <input type="text" class="form-control" id="bookAuthor" value="${book.author}">
            </div>
            <div class="mb-3">
                <label for="bookEdition" class="form-label">Edition</label>
                <input type="text" class="form-control" id="bookEdition" value="${book.edition}">
            </div>
            <div class="mb-3">
                <label for="copy_preview" class="form-label">PDF Preview Link</label>
                <input type="text" class="form-control" id="copy_preview" value="${book.copy_preview}">
            </div>
            <button type="submit" class="btn btn-primary">Save Changes</button>
            <button type="button" class="btn btn-secondary" onclick="renderBookManagement()">Cancel</button>
        </form>
    `;


        document.getElementById('editBookForm').addEventListener('submit', (event) => handleUpdateBook(event, bookId));
    }
}

window.renderBookManagement = renderAddBookForm;

// Delete book functionality
async function deleteBook(bookId) {
    const bookRef = dbRef(database, `book/${bookId}`);
    
    try {
        const snapshot = await get(bookRef);
        if (snapshot.exists()) {
            const oldData = snapshot.val();
            await remove(bookRef);
            logHistory('Deleted Book', bookId, oldData, null);
            alert(`Book ID ${bookId} deleted successfully.`);
            loadBooks();
        }
    } catch (error) {
        console.error("Error deleting book:", error);
        alert(`Error deleting Book ID ${bookId}. Please try again later.`);
    }
}

async function handleUpdateBook(event, bookId) {
    event.preventDefault();

    const bookName = document.getElementById('bookName').value;
    const bookDescription = document.getElementById('bookDescription').value;
    const bookCopies = document.getElementById('bookCopies').value;
    const bookPrice = document.getElementById('bookPrice').value;
    const bookDepartment = document.getElementById('bookDepartment').value;
    const bookSemester = document.getElementById('bookSemester').value;
    const bookUrl = document.getElementById('bookUrl').value;
    const bookKeywords = document.getElementById('bookKeywords').value.split(',').map(keyword => keyword.trim());
    const bookAuthor = document.getElementById('bookAuthor').value;
    const bookEdition = document.getElementById('bookEdition').value;
    const copy_preview = document.getElementById('copy_preview').value;
    
    try {
        const bookRef = dbRef(database, `book/${bookId}`);
        const snapshot = await get(bookRef);
        const oldData = snapshot.exists() ? snapshot.val() : null;

        const updatedBook = {
            id: bookId,
            name: bookName,
            description: bookDescription,
            copies: bookCopies,
            price: bookPrice,
            department: bookDepartment,
            semester: bookSemester,
            url: bookUrl,
            keywords: bookKeywords,
            author: bookAuthor,
            edition: bookEdition,
            copy_preview: copy_preview
        };

        await set(bookRef, updatedBook);

        logHistory('Updated Book', bookId, oldData, updatedBook);
        alert('Book updated successfully.');
        renderBookManagement();
    } catch (error) {
        console.error("Error updating book:", error);
        alert(`Error updating book: ${error.message}. Please try again later.`);
    }
}



// Define renderCouponManagement, renderNotification, and renderAddBalance
    function renderCouponManagement() {
        contentArea.innerHTML = `
            <h2>Coupon Management</h2>
            <button id="addCouponBtn" class="btn btn-primary mb-3">Add Coupon</button>
            <div id="couponList">
                <table class="table table-striped">
                    <thead>
                        <tr>
                            <th>Coupon Code</th>
                            <th>Discount (%)</th>
                            <th>Expiry Date</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody id="couponTableBody"></tbody>
                </table>
            </div>
        `;
        document.getElementById('addCouponBtn').addEventListener('click', renderAddCouponForm);
        loadCoupons();
      
}

async function loadCoupons() {
    const couponTableBody = document.getElementById('couponTableBody');
    couponTableBody.innerHTML = '<tr><td colspan="4">Loading coupons...</td></tr>';

    try {
        const couponsRef = dbRef(database, 'coupons');
        const snapshot = await get(couponsRef);

        if (snapshot.exists()) {
            couponTableBody.innerHTML = '';
            snapshot.forEach((childSnapshot) => {
                const coupon = childSnapshot.val();
                const row = document.createElement('tr');
                row.innerHTML = `
                    <td>${coupon.code}</td>
                    <td>${coupon.discount}%</td>
                    <td>${coupon.expiryDate}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" onclick="editCoupon('${childSnapshot.key}')">Edit</button>
                        <button class="btn btn-sm btn-danger" onclick="deleteCoupon('${childSnapshot.key}')">Delete</button>
                    </td>
                `;
                couponTableBody.appendChild(row);
            });
        } else {
            couponTableBody.innerHTML = '<tr><td colspan="4">No coupons found.</td></tr>';
        }
    } catch (error) {
        console.error("Error loading coupons:", error);
        couponTableBody.innerHTML = '<tr><td colspan="4">Error loading coupons. Please try again.</td></tr>';
    }
}
// Add this at the beginning of your JavaScript file
window.editCoupon = async function(couponId) {
    try {
        const couponRef = dbRef(database, `coupons/${couponId}`);
        const snapshot = await get(couponRef);
        
        if (snapshot.exists()) {
            const coupon = snapshot.val();
            
            // Render the edit form with the existing coupon data
            contentArea.innerHTML = `
                <h2>Edit Coupon</h2>
                <form id="editCouponForm">
                    <div class="mb-3">
                        <label for="couponCode" class="form-label">Coupon Code</label>
                        <input type="text" class="form-control" id="couponCode" value="${coupon.code}" required>
                    </div>
                    <div class="mb-3">
                        <label for="discount" class="form-label">Discount (%)</label>
                        <input type="number" class="form-control" id="discount" value="${coupon.discount}" required>
                    </div>
                    <div class="mb-3">
                        <label for="expiryDate" class="form-label">Expiry Date</label>
                        <input type="date" class="form-control" id="expiryDate" value="${coupon.expiryDate}" required>
                    </div>
                    <button type="submit" class="btn btn-primary">Save Changes</button>
                    <button type="button" class="btn btn-secondary" onclick="renderCouponManagement()">Cancel</button>
                </form>
            `;

            // Add event listener for the edit form submission
            document.getElementById('editCouponForm').addEventListener('submit', (event) => 
                handleEditCoupon(event, couponId)
            );
        } else {
            alert('Coupon not found');
        }
    } catch (error) {
        console.error("Error loading coupon for edit:", error);
        alert('Error loading coupon details. Please try again.');
    }
};

// Also make deleteCoupon globally accessible
window.deleteCoupon = async function(couponId) {
    if (confirm("Are you sure you want to delete this coupon?")) {
        try {
            const couponRef = dbRef(database, `coupons/${couponId}`);
            await remove(couponRef);
            alert("Coupon deleted successfully.");
            loadCoupons();
        } catch (error) {
            console.error("Error deleting coupon:", error);
            alert("Error deleting coupon. Please try again.");
        }
    }
};

// Add the function to handle the edit form submission
async function handleEditCoupon(event, couponId) {
    event.preventDefault();
    
    const couponCode = document.getElementById('couponCode').value;
    const discount = document.getElementById('discount').value;
    const expiryDate = document.getElementById('expiryDate').value;

    // Validation
    if (!couponCode || !discount || !expiryDate) {
        alert('Please fill in all fields.');
        return;
    }

    if (isNaN(discount) || discount <= 0 || discount >= 100) {
        alert('Please enter a valid discount percentage between 1 and 99.');
        return;
    }

    try {
        const couponRef = dbRef(database, `coupons/${couponId}`);
        await set(couponRef, {
            code: couponCode,
            discount: parseInt(discount),
            expiryDate: expiryDate
        });

        alert('Coupon updated successfully.');
        renderCouponManagement();
    } catch (error) {
        console.error("Error updating coupon:", error);
        alert('Error updating coupon. Please try again.');
    }
}

function renderNotification() {
    contentArea.innerHTML = `
        <h2>Notification Center</h2>
        <button id="addNotificationBtn" class="btn btn-primary mb-3">Add Notification</button>
        <div id="notificationList">
            <ul class="list-group" id="notificationListItems"></ul>
        </div>
    `;
    document.getElementById('addNotificationBtn').addEventListener('click', renderAddNotificationForm);
}


function renderAddBalance() {
    contentArea.innerHTML = `
        <h2>Add Balance</h2>
        <form id="addBalanceForm">
            <div class="mb-3">
                <label for="user_email" class="form-label">User Email</label>
                <input type="email" class="form-control" id="user_email" required>
            </div>
            <div class="mb-3">
                <label for="amount" class="form-label">Amount to Add</label>
                <input type="number" class="form-control" id="amount" required min="0.01" step="0.01">
            </div>
            <button type="submit" class="btn btn-primary">Add Balance</button>
        </form>
        <div id="userSearchResult" class="mb-3"></div> 
    `;
    document.getElementById('addBalanceForm').addEventListener('submit', handleAddBalance);
}




// Functions to handle Coupons
function renderAddCouponForm() {
    contentArea.innerHTML = `
       <h2>Add Coupon</h2>
<form id="addCouponForm">
  <div class="mb-3">
    <label for="couponCode" class="form-label">Coupon Code</label>
    <input type="text" class="form-control" id="couponCode" required>
  </div>
  <div class="mb-3">
    <label for="discount" class="form-label">Discount (%)</label>
    <input type="number" class="form-control" id="discount" required>
  </div>
  <div class="mb-3">
    <label for="expiryDate" class="form-label">Expiry Date</label>
    <input type="date" class="form-control" id="expiryDate" required>
  </div>
  <button type="submit" class="btn btn-primary">Add Coupon</button>
</form>
    `;
    document.getElementById('addCouponForm').addEventListener('submit', handleAddCoupon);
}
async function handleAddCoupon(event) {
    event.preventDefault();
    const couponCode = document.getElementById('couponCode').value;
    const discount = document.getElementById('discount').value;
    const expiryDate = document.getElementById('expiryDate').value;
  
    // Input validation
    if (!couponCode || !discount || !expiryDate) {
      alert('Please fill in all fields.');
      return; 
    }

    if (isNaN(discount) || discount <= 0 || discount >= 100) {
      alert('Please enter a valid discount percentage between 1 and 99.');
      return;
    }

    // Check if expiry date is in the future (optional but recommended)
    const today = new Date();
    const expiryDateObj = new Date(expiryDate);
    if (expiryDateObj <= today) {
      alert('Expiry date must be in the future.');
      return;
    }

    try {
        // Check for duplicate coupon code before adding
        const couponsRef = dbRef(database, 'coupons');
        const couponSnapshot = await get(couponsRef);
    
        if (couponSnapshot.exists()) {
          const coupons = couponSnapshot.val();
    
          // Check if a coupon with the entered code already exists
          for (const key in coupons) {
            if (coupons[key].code === couponCode) {
              alert('A coupon with this code already exists.');
              return; // Exit the function if a duplicate is found
            }
          }
        }
    
        // If no duplicate is found, proceed with adding the coupon
        const newCouponRef = push(couponsRef);
        await set(newCouponRef, {
          code: couponCode,
          discount: discount,
          expiryDate: expiryDate
        });
    
        alert('Coupon added successfully.');
        renderCouponManagement();
    } catch (error) {
      console.error("Error adding coupon:", error);
    }
  }

// Functions to handle Notifications
function renderAddNotificationForm() {
    contentArea.innerHTML = `
    <h2>Add Notification</h2>
    <form id="addNotificationForm">
        <div class="mb-3">
            <label for="notificationTitle" class="form-label">Title</label>
            <input type="text" class="form-control" id="notificationTitle" required>
        </div>
        <div class="mb-3">
            <label for="notificationMessage" class="form-label">Message</label>
            <textarea class="form-control" id="notificationMessage" required></textarea>
        </div>
        <div class="mb-3">
            <label for="notificationEmail" class="form-label">User Email</label> <input type="email" class="form-control" id="notificationEmail">
        </div>
        <button type="submit" class="btn btn-primary">Send Notification</button>
    </form>
`;
    document.getElementById('addNotificationForm').addEventListener('submit', handleAddNotification);

}


  
async function handleAddNotification(event) {
    event.preventDefault(); // Prevent the default form submission behavior

    // Gather values from form fields
    const notificationTitle = document.getElementById('notificationTitle').value.trim();
    const notificationMessage = document.getElementById('notificationMessage').value.trim();
    const notificationEmail = document.getElementById('notificationEmail').value.trim();

    // Basic validation to ensure required fields are filled
    if (!notificationTitle || !notificationMessage) {
        alert("Title and message are required.");
        return;
    }

    try {
        // Create the notification object
        const notificationData = {
            title: notificationTitle,
            message: notificationMessage,
            email: notificationEmail || null, // Optional email
            timestamp: new Date().toISOString(), // Add a timestamp for tracking
        };

        // Reference to the notifications list in the database
        const sanitizedEmail = notificationEmail.replace(".", "_").replace("@", "_");

        // Reference to the notifications list in the database
        const notificationsRef = dbRef(database, `inter_user/${sanitizedEmail}/data/notifications`);
    
        // Push the new notification to the list
        const newNotificationRef = push(notificationsRef); // Use push to generate a unique key for each notification
        await set(newNotificationRef, notificationData); // Store the new notification object


        // Success feedback to the user
        alert('Notification sent successfully.');

        // Optionally, clear the form after submission
        document.getElementById('addNotificationForm').reset();

    } catch (error) {
        console.error("Error sending notification:", error.message);
        alert(`Error sending notification: ${error.message}. Please try again later.`);
    }
}





async function handleAddBalance(event) {
    event.preventDefault();
    const userEmail = document.getElementById('user_email').value;
    const amount = parseFloat(document.getElementById('amount').value);
    const resultDiv = document.getElementById('userSearchResult'); 

    if (isNaN(amount) || amount <= 0) {
        alert("Please enter a valid positive amount.");
        return;
    }

    try {
        const username = userEmail.split('@')[0]; 
        const userRef = dbRef(database, `information/${username}`);
        const snapshot = await get(userRef);

        let currentBalance = 0;
        if (snapshot.exists()) {
            currentBalance = parseFloat(snapshot.val().balance || '0'); 
        }

        const newBalance = (currentBalance + amount).toFixed(1);

        await set(userRef, {
            email: userEmail,
            balance: newBalance
        });

        alert('Balance added successfully.');
        
        // Update the result display 
        resultDiv.innerHTML = `
            <br><div class="alert alert-success">
                Balance updated successfully!
                <br>
                User: ${userEmail}
                <br>
                New Balance: ${newBalance}
            </div>
        `;

    } catch (error) {
        console.error("Error adding balance:", error);
        alert('Error adding balance. Please try again.');
    }
}

// Attach the renderAddBalance function to the window object
window.renderAddBalance = renderAddBalance;