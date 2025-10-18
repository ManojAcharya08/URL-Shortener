URL Shortener
A simple and lightweight URL Shortener application built using Spring Boot for the backend and ReactJS for the frontend.
This project converts long URLs into short, shareable links and allows the user to retrieve or redirect to the original URL quickly.

Features :
Convert any long URL into a unique short link.

Retrieve and redirect to the original URL.

Copy short URLs instantly to the clipboard.

Responsive and clean ReactJS user interface.

RESTful API built with Spring Boot.

CORS enabled for frontend-backend communication.

Tech Stack :
Backend: Spring Boot, Java, JPA, H2/MySQL
Frontend: ReactJS, Vite, JavaScript, CSS
Build Tools: Maven, npm
Version Control: Git & GitHub

How to Run the Project :
1. Backend (Spring Boot)
Open the folder in your IDE (like IntelliJ or Eclipse).

Run the following command in the terminal:

bash
mvn spring-boot:run
The backend will start on:
http://localhost:8080

2. Frontend (ReactJS)
Navigate to the React frontend folder:

bash
cd url-shortener-frontend
Install dependencies:

bash
npm install
Start the development server:

bash
npm run dev
The frontend will start on:
http://localhost:5173

Usage :
Enter a long URL in the input box.

Click “Shorten URL” to generate a short link.

Copy the link using the “Copy” button.

Open the short link in a browser to get redirected to the original URL.

Folder Structure :
text
project-root/
│
├── backend/ (Spring Boot)
│   ├── src/
│   ├── pom.xml
│   └──...
│
├── frontend/ (React App)
│   ├── src/
│   ├── package.json
│   └── vite.config.js
│
└── README.md

Clone :
git clone https://github.com/ManojAcharya08/URL-Shortener.git

License :
This project is open-source and available under the MIT License.

Author:
Manoj Acharya
GitHub Profile :https://github.com/ManojAcharya08

