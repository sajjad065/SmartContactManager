


/* JavaScript for serching the contact based on keyword. search() function will trigger as soon as user typed something in search field */


const search = () => {
    let query = $("#search-input").val();
    console.log("hellow sajjad");
    if (query === "") {
        $(".search-result").hide();
    } else {
        console.log("Search query:", query);  // Log the search query
        $(".search-result").show();
        
        // Fetching search results from the server
        fetch(`http://localhost:9099/searchContact/${query}`)
            .then(response => {
                console.log("Response status:", response.status);  // Log status of the response
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();  // Parse the JSON response
            })
            .then(data => {
                console.log("Data from backend:", data);  // Log the data received from the backend
                console.log("Length of data array:", data.length);  // Log the length of the data array
                
                // Check if data is an array and log its length
                if (Array.isArray(data) && data.length > 0) {
                    // Building the HTML for search results
                    let text = `<div class='list-group'>`;
                    data.forEach(contact => {
                        text += `<a href="/user/viewContactDetail/${contact.cid}" class='list-group-item list-group-item-action'>${contact.name}</a>`;
                    });
                    text += `</div>`;
                    
                    // Injecting the HTML into the search result container
                    $(".search-result").html(text);
                } else {
                    console.log("No contacts found for the search query.");
                    $(".search-result").html("<p>No contacts found.</p>"); // Display a message if no contacts found
                }
                
                $(".search-result").show();
            })
            .catch(error => {
                console.error("Error fetching search results:", error);  // Log any errors
            });
    }
}; 
