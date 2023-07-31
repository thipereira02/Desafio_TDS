# URL Shortener

## Description

URL Shortener is a simple API that allows users to shorten long URLs into short, easy-to-share links.

## Features

- Shorten long URLs into short links
- Redirect users to the original URL when accessing the short link
- Track and display click statistics for each short link

## Technologies
The following tools and frameworks were used in the construction of this project:<br>

  ![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)&nbsp;
  ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)&nbsp;
  ![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)&nbsp;
  ![MongoDB](https://img.shields.io/badge/MongoDB-%234ea94b.svg?style=for-the-badge&logo=mongodb&logoColor=white)

## Features

- `POST /encurtar`: Shorten a URL
- `GET /{shortUrl}`: Redirect to the original URL
- `GET /estatisticas`: Get URL click statistics
  
## How to Use

1. Clone the repository: `git clone https://github.com/thipereira02/Desafio_TDS`
2. Install dependencies: `mvn install`
3. Configure your MongoDB connection in `application.properties`
4. Run the application: `mvn spring-boot:run`
5. Access the application at: `http://localhost:8080`


