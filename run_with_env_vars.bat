@echo off
echo ===========================================
echo HappyTravell Database Setup
echo ===========================================
echo.
echo This script sets up environment variables for database connection.
echo Each developer should edit this file with their own MySQL credentials.
echo.

REM ===========================================
REM EDIT THESE LINES WITH YOUR MYSQL CREDENTIALS
REM ===========================================
set DB_USERNAME=root
set DB_PASSWORD=your_mysql_password_here
REM ===========================================

echo Setting environment variables...
echo Username: %DB_USERNAME%
echo Password: [HIDDEN]
echo Database: happytravel
echo.

echo Starting HappyTravell application...
echo.

java -cp "lib/mysql-connector-j-9.2.0.jar;lib/mail.jar;lib/org;src" happytravell.HappyTravell

echo.
echo Application finished.
pause 