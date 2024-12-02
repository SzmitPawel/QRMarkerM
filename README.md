
# QRMarker

QRMarker is a JavaFX application that generates QR codes from user-provided text or links. The generated QR codes can be displayed in the application and copied directly to the system clipboard.

## Features
- Generate QR Codes: Input a link or text and generate a QR code.
- Copy to Clipboard: Copy the generated QR code image directly to your clipboard.
- JavaFX GUI: User-friendly interface built with JavaFX.
- ZXing Integration: Uses the ZXing library for QR code

### Requirements
- Java Development Kit (JDK): Version 11 or later.
- JavaFX SDK: Compatible with your JDK version.
- ZXing Library: Included in the project for QR code generation.

### Usage
- Launch the application.
- Enter a link or text into the input field.
- Click the Generate button to create a QR code.
- Copy the QR code to the clipboard by clicking the Copy button.

### Project Structure
```
src/
├── main/
│   ├── java/
│   │   ├── generator/
│   │   │   ├── qrmarkerm/
│   │   │   │   ├── fx/
│   │   │   │   │   ├── controller/
│   │   │   │   │   │   ├── QRMarkerViewController.java  # JavaFX Controller
│   │   │   │   ├── generator/
│   │   │   │   │   ├── ZxingGenerator.java              # QR Code generation logic
│   │   │   │   ├── ApplicationLauncher.java             # Entry point for launching JavaFX
│   │   │   │   ├── QRMarker.java                        # Core JavaFX application class
│   ├── resources/
│   │   ├── qrmarker-view.fxml                           # JavaFX layout file
```
### Contributing
1. Fork the repository.
2. Create a feature branch:
```bash
git checkout -b feature-name
```
3. Commit your changes:
```bash
git commit -m "Description of changes"
```
4. Push the branch:
```bash
git push origin feature-name
```
5. Submit a pull request.

## License

This project is licensed under the [MIT License.](https://choosealicense.com/licenses/mit/)

