// Helper function to display response messages
function showResponse(message, success = true) {
    const responseDiv = document.getElementById("response");
    responseDiv.textContent = message;
    responseDiv.style.display = "block";
    responseDiv.style.color = success ? "green" : "red";
}

// Fetch and display employees
async function fetchEmployees() {
    const tableBody = document.querySelector("#employeeTable tbody");
    tableBody.innerHTML = ""; // Clear existing rows

    try {
        const response = await fetch("http://localhost:8080/employees/all");
        if (response.ok) {
            const employees = await response.json();
            employees.forEach(employee => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${employee.id}</td>
                    <td>${employee.name}</td>
                    <td>${employee.department}</td>
                `;
                tableBody.appendChild(row);
            });
        } else {
            showResponse("Failed to fetch employees.", false);
        }
    } catch (error) {
        showResponse("Error: Unable to connect to the server.", false);
    }
}

// Handle Add Employee
document.getElementById("addEmployeeForm").addEventListener("submit", async (event) => {
    event.preventDefault();
    const name = document.getElementById("employeeName").value;
    const department = document.getElementById("employeeDepartment").value;

    try {
        const response = await fetch("http://localhost:8080/employees/add", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ name, department }),
        });
        if (response.ok) {
            showResponse("Employee added successfully!");
            document.getElementById("addEmployeeForm").reset();
            fetchEmployees(); // Refresh employee list
        } else {
            showResponse("Failed to add employee.", false);
        }
    } catch (error) {
        showResponse("Error: Unable to connect to the server.", false);
    }
});

// Handle Remove Employee
document.getElementById("removeEmployeeForm").addEventListener("submit", async (event) => {
    event.preventDefault();
    const id = document.getElementById("employeeId").value;

    try {
        const response = await fetch(`http://localhost:8080/employees/remove/${id}`, {
            method: "DELETE",
        });
        if (response.ok) {
            showResponse("Employee removed successfully!");
            document.getElementById("removeEmployeeForm").reset();
            fetchEmployees(); // Refresh employee list
        } else {
            showResponse("Failed to remove employee. Check the ID.", false);
        }
    } catch (error) {
        showResponse("Error: Unable to connect to the server.", false);
    }
});

// Refresh employee list when "Refresh List" button is clicked
document.getElementById("refreshEmployees").addEventListener("click", fetchEmployees);

// Load employee list on page load
window.onload = fetchEmployees;
