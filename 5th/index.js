// Base URL of your Spring Boot backend
const BASE_URL = "http://localhost:8080/api/books";

// Fetch and display all books
async function fetchBooks() {
  const response = await fetch(BASE_URL);
  const books = await response.json();

  const booksList = document.getElementById("booksList");
  booksList.innerHTML = "";

  books.forEach((book) => {
    const li = document.createElement("li");
    li.innerHTML = `<span>${book.title} by ${book.author} (${book.publicationYear})</span>`;
    booksList.appendChild(li);
  });
}

// Add a new book
document
  .getElementById("addBookForm")
  .addEventListener("submit", async (event) => {
    event.preventDefault();

    const title = document.getElementById("title").value;
    const author = document.getElementById("author").value;
    const year = document.getElementById("year").value;

    const response = await fetch(BASE_URL, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ title, author, publicationYear: year }),
    });

    if (response.ok) {
      alert("Book added successfully!");
      document.getElementById("addBookForm").reset();
      fetchBooks(); // Refresh the book list
    } else {
      alert("Failed to add book.");
    }
  });

// Search books by title, author, or year
document.getElementById("searchBtn").addEventListener("click", async () => {
  const title = document.getElementById("searchTitle").value.trim();
  const author = document.getElementById("searchAuthor").value.trim();
  const year = document.getElementById("searchYear").value.trim();

  let url = BASE_URL;
  let queryParams = [];

  // Construct the URL based on input
  if (title && author) {
    // Both title and author are filled, use /title-author
    url += `/title-author?title=${title}&author=${author}`;
  } else {
    // Handle each case where only one input is filled
    if (title) {
      queryParams.push(`title/${title}`);
    }
    if (author) {
      queryParams.push(`author/${author}`);
    }
    if (year) {
      queryParams.push(`year/${year}`);
    }

    if (queryParams.length > 0) {
      url += `/${queryParams}`;
    }
  }

  // Fetch data from the backend
  const response = await fetch(url);
  const books = await response.json();

  // Display the books
  const booksList = document.getElementById("booksList");
  booksList.innerHTML = "";

  books.forEach((book) => {
    const li = document.createElement("li");
    li.innerHTML = `<span>${book.title} by ${book.author} (${book.publicationYear})</span>`;
    booksList.appendChild(li);
  });
});

// Initial fetch
fetchBooks();
