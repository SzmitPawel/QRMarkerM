
# QRMarker

### Project Overview

QR Marker Generator is a JavaFX-based application for generating customizable QR codes. Users can modify the QR code's colors, styles, resolution, and even add a logo. The project follows a modular design and implements design patterns for code maintainability.



### Features
- Custom QR Code Generation: Users can create QR codes with different styles and resolutions.

-  Logo Support: The generated QR codes can include an embedded logo.

- Finder Pattern Styling: Rounded QR code styles are available.

- Flexible Resolution: Different QR code resolutions can be selected from a dropdown menu.

- Copy QR Code: A feature to copy the generated QR code to the clipboard.

### Requirements
- Java Development Kit (JDK): Version 11 or later.
- JavaFX SDK: Compatible with your JDK version.
- ZXing Library: Included in the project for QR code generation.

### Usage
- Enter a link in the text field.

- Click the Generate QR Code button.

- Customize your QR code:

- Choose foreground and background colors.

- Select QR code resolution.

- Apply a QR code style (e.g., rounded patterns).

- Add a logo if needed.

- Click Copy QR Code to copy the generated code.



### Project Structure
```
QRMarkerGenerator/
│── dto/
│   └── ResolutionDto.java
│
│── fx/controller/button/
│   ├── CopyButtonHandle.java
│   ├── GenerateButtonHandle.java
│   ├── LoadLogoButtonHandle.java
│
│── combobox/
│   ├── CodeResolutionsComboBox.java
│   ├── CodeStylesComboBox.java
│
│── controller/
│   └── QRMarkerViewController.java
│
│── pattern/
│   ├── Generator.java
│   ├── GeneratorFactory.java
│
│── generator/
│   ├── ZxingGenerator.java
│   ├── ZxingGeneratorWithLogo.java
│
│── styles/
│   └── RoundedQrCodeStyle.java
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

6. ## License

This project is licensed under the [MIT License.](https://choosealicense.com/licenses/mit/)

