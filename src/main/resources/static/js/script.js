console.log("Script loaded");

let currentTheme = getTheme();
changeTheme();

// Function to change the theme
function changeTheme() {
    // Set the initial theme on the webpage
    document.querySelector('html').classList.add(currentTheme);

    // Set up the listener for the change theme button
    const changeThemeButton = document.querySelector("#theme_change_button");
    
    // Set initial button text based on current theme
    changeThemeButton.querySelector("span").textContent = currentTheme === "light" ? "Dark" : "Light";

    changeThemeButton.addEventListener("click", (event) => {
        const oldTheme = currentTheme;
        console.log("Change theme button clicked");

        // Toggle between light and dark theme
        currentTheme = currentTheme === "dark" ? "light" : "dark";

        // Update theme in localStorage
        setTheme(currentTheme);

        // Remove the old theme class and add the new theme class
        document.querySelector("html").classList.remove(oldTheme);
        document.querySelector("html").classList.add(currentTheme);

        // Update the button text based on the new theme
        changeThemeButton.querySelector("span").textContent = currentTheme === "light" ? "Dark" : "Light";
    });
}

// Set theme to localStorage
function setTheme(theme) {
    localStorage.setItem("theme", theme);
}

// Get theme from localStorage
function getTheme() {
    let theme = localStorage.getItem("theme");
    return theme ? theme : "light";
}


