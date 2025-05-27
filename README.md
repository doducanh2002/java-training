# airport-management-system

Provide a brief description of what your project does and its purpose here.

## 1. Prerequisites

Before you begin, ensure you have met the following requirements:
* Java 17 installed on your system.
* IntelliJ IDEA for development (optional, but recommended for ease of use).

## Configuration

### Environment Variables

This project requires setting up environment variables to manage sensitive data and configurations. Follow these steps to create an `.env` file that will store your environment settings:

1. In the root directory of your project, create a file named `.env`.
2. Open the `.env` file and add the following variables:

    ```plaintext
    SPRING_EMAIL_USERNAME=your_email_username
    SPRING_EMAIL_PASSWORD=your_email_password
    REDIS_HOST=your_redis_host
    REDIS_PORT=your_redis_port
    DATASOURCE_PASSWORD=your_database_password
    DATASOURCE_USERNAME=your_database_username
    DATASOURCE_URL=jdbc:your_database_url
    ```

   Replace the placeholders with your actual data.

### Adding `.env` File to IntelliJ Configuration

To make IntelliJ IDEA recognize and use your `.env` file during development, follow these steps:

1. Open your project in IntelliJ IDEA.
2. Go to `Run` > `Edit Configurations...`.
3. In the run/debug configurations dialog, select your application under 'Application'.
4. In the configuration settings, find the `Environment variables` field.
5. Click the browse button (looks like three dots: `...`) next to the field.
6. Click `+` to add each environment variable and value as specified in your `.env` file.
7. Click `OK` to save the configurations.

## Running the Project

You can run this project either using the command line or through IntelliJ IDEA.
You can run it using the main function in the main module

### Using Command Line

Navigate to the root directory of your project in your terminal and run the following commands:

```bash
./mvnw clean install  # To build the project
./mvnw spring-boot:run  # To run the project
```
## 2. Code Formatting with Google Java Format

To maintain a consistent code style across the development team, this project uses Google Java Format. Follow these steps to set up Google Java Format in IntelliJ IDEA:

### Step 1: Download the Google Java Format File

Before configuring IntelliJ, you'll need the Google Java Format style file. You can download the latest version from the following link:

[Google Java Format XML](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml)

Save this file to a known location on your computer as you will need to import it into IntelliJ IDEA.

### Step 2: Install the Google Java Format Plugin

The Google Java Format Plugin automatically formats your Java code according to the Google Style guidelines. To install the plugin, follow these steps:

1. Open IntelliJ IDEA.
2. Go to `File` > `Settings` (on Windows/Linux) or `IntelliJ IDEA` > `Preferences` (on macOS).
3. Navigate to `Plugins`.
4. Click the `Marketplace` tab and search for "Google Java Format".
5. Find the plugin in the search results and click `Install`.
6. Restart IntelliJ IDEA to activate the plugin.

### Step 3: Configure Code Style in IntelliJ

After installing the plugin and downloading the style file, you need to set it as the current code style for your project:

1. Open IntelliJ IDEA.
2. Go back to `File` > `Settings` or `Preferences`.
3. Navigate to `Editor` > `Code Style`.
4. Click on `Java`, then click on the gear icon next to `Scheme` and select `Import Scheme`.
5. Choose `IntelliJ IDEA code style XML`.
6. Navigate to where you downloaded the `intellij-java-google-style.xml` file, select it, and click `OK`.
7. Confirm that you want to use the new style if prompted and apply the changes.

### Step 4: Using the Google Java Format Plugin

To format an individual file:
- Open the file you want to format.
- Right-click in the file and select `Reformat Code with Google Style`.

To format the whole project:
- Go to `Code` in the menu bar.
- Select `Reformat Code`.
- In the dialog that appears, select `Whole Project` and ensure `Optimize imports` is checked to organize your imports according to the Google style.
- Click `Run`.

Follow these steps to ensure your codebase remains clean and consistent with Google's Java coding standards. This setup will help all contributors automatically adhere to the project's coding style guidelines.
