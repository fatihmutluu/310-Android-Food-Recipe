## Cookr - Recipe Recommendation Android Application

<img src="https://media.discordapp.net/attachments/238684365930889216/1114702780347711648/Cookr-removebg-preview.png?width=488&height=488" alt="Cookr Banner" align="left" width="200">

Cookr is a user-friendly Android application designed to provide recipe recommendations based on user-selected filters. Developed using the Spring framework for backend services and built in Android Studio, Cookr aims to simplify the process of finding delicious recipes for users of all cooking skill levels.

## Features
- **Recipe Recommendations:** Cookr offers a diverse collection of recipe recommendations based on user-selected filters such as cuisine, dietary restrictions, ingredients, and cooking time.
- **User-Friendly Interface:** The application provides an intuitive and visually appealing interface, making it easy for users to browse, search, and save their favorite recipes.
- **Filtering and Sorting:** Users can apply filters to refine their recipe search based on specific preferences, such as vegetarian, gluten-free, quick meals, and more. Sorting options are also available for personalized recipe exploration.
- **Recipe Details:** Each recipe comes with detailed instructions, ingredient lists, nutritional information, and user reviews to help users make informed cooking decisions.
- **User-Created Recipes:** Users can also submit their own recipes, contributing to the community and expanding the recipe collection.
- **Backend Services with Spring:** Cookr utilizes the Spring framework to handle backend services, including recipe data storage, retrieval, and filtering.
- **Android Studio Development:** The Android application is developed using Android Studio, leveraging the robust set of tools and features provided by the platform.

## Installation
1. Clone this repository: `git clone https://github.com/your-username/your-repo.git`
2. Backend Setup:
   - Navigate to the backend directory: `cd your-repo/backend`
   - Install backend dependencies: `mvn install`
   - Configure the necessary environment variables in the `application.properties` file, such as database connection details.
   - Start the backend server: `mvn spring-boot:run`
3. Android Studio Setup:
   - Open Android Studio and select "Open an Existing Project."
   - Navigate to the frontend directory: `your-repo/frontend`
   - Import the project into Android Studio.
   - Build the project and resolve any necessary dependencies.
   - Update the API endpoint in the codebase to match your backend server URL.
   - Run the application on an emulator or connected Android device.

## Postman Collection
1. Import the provided Postman collection file (`your-repo/postman_collection.json`) into your Postman application.
2. Update the API endpoints in the collection to match your backend server URL.

Make sure to set up and configure the necessary environment variables in the `application.properties` file, including the database connection details and any other required properties specific to your environment.

## Contributing
Contributions are welcome! If you find any issues or have suggestions for improvements, please open an issue or submit a pull request.

Feel free to customize the descriptions according to your project's specific details and additional features.
