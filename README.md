QRMarker
QRMarker is a JavaFX application that generates QR codes from user-provided text or links. The generated QR codes can be displayed in the application and copied directly to the system clipboard.

Features
Generate QR Codes: Input a link or text and generate a QR code.
Copy to Clipboard: Copy the generated QR code image directly to your clipboard.
JavaFX GUI: User-friendly interface built with JavaFX.
ZXing Integration: Uses the ZXing library for QR code generation.
Requirements
Java Development Kit (JDK): Version 11 or later.
JavaFX SDK: Compatible with your JDK version.
ZXing Library: Included in the project for QR code generation.
Setup
Clone the Repository
git clone <repository-url>
cd qrmarker
Maven Configuration (Optional)
If using Maven, ensure the following dependencies are included in your pom.xml:

<dependencies>
    <!-- JavaFX Dependencies -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21.0.0</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21.0.0</version>
    </dependency>

    <!-- ZXing Dependency -->
    <dependency>
        <groupId>com.google.zxing</groupId>
        <artifactId>core</artifactId>
        <version>3.5.2</version>
    </dependency>
    <dependency>
        <groupId>com.google.zxing</groupId>
        <artifactId>javase</artifactId>
        <version>3.5.2</version>
    </dependency>
</dependencies>
Running the Application
Using IntelliJ IDEA
Open the project in IntelliJ IDEA.
Ensure the JavaFX SDK path is configured:
Go to File > Project Structure > Libraries and add the JavaFX SDK.
Run the QRMarker class.
Using Command Line
Compile the application:
javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -d out src/main/java/generator/qrmarker/*.java
Run the application:
java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls,javafx.fxml -cp out generator.qrmarker.QRMarker
Creating a Fat JAR (Maven)
Package the application into a standalone JAR:
mvn clean package
Run the generated JAR:
java -jar target/qrmarker.jar
Usage
Launch the application.
Enter a link or text into the input field.
Click the Generate button to create a QR code.
Copy the QR code to the clipboard by clicking the Copy button.
