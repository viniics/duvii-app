document.addEventListener("DOMContentLoaded", function() {
    document.getElementById("registration-form").addEventListener("submit", function(event) {
        event.preventDefault(); // Prevent default form submission
        
        // Get form data
        const formData = new FormData(event.target);
        const jsonObject = {};
        formData.forEach((value, key) => {
            jsonObject[key] = value;
        });

        // Convert data to JSON
        const jsonData = JSON.stringify(jsonObject);

        // Send JSON data to backend (you can replace this with your backend endpoint)
        fetch("http://localhost:8080/user/post", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: jsonData
        })
        .then(response => {
            if (response.ok) {
                console.log("Data sent successfully!");
                window.location.href = "registration-success.html";
            } else {
                console.error("Failed to send data.");
                // Handle failed response
            }
        })
        .catch(error => {
            console.error("Error:", error);
            // Handle error
        });        
    });
});