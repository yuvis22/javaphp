<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Blue Bird</title>
    <link rel="stylesheet" href="./css/home.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
    <nav>
        <div class="logo">
            <img class="bluebirdlogo" src="./image/bluebirdlogo.png" alt="logo">
            <p>BLUEBIRD</p>
        </div>
        <ul>
            <li><a href="#firstsection">Home</a></li>
            <li><a href="#secondsection">Rooms</a></li>
            <li><a href="#thirdsection">Facilities</a></li>
            <li><a href="#contactus">Contact us</a></li>
        </ul>
    </nav>

    <section id="firstsection">
        <!-- Carousel Section -->
        <div class="carousel">
            <div>Welcome to Heaven on Earth</div>
        </div>

        <!-- Form -->
        <form action="ReservationServlet" method="post" class="reservation-form">
            <h3>Reservation</h3>
            <input type="text" name="Name" placeholder="Enter Full name" required>
            <input type="email" name="Email" placeholder="Enter Email" required>
            <select name="Country">
                <option value="" selected>Select your country</option>
                <option value="India">India</option>
                <option value="USA">USA</option>
                <option value="Canada">Canada</option>
                <!-- Add more countries -->
            </select>
            <input type="text" name="Phone" placeholder="Enter Phone" required>
            <select name="RoomType">
                <option value="" selected>Type of Room</option>
                <option value="Superior Room">Superior Room</option>
                <option value="Deluxe Room">Deluxe Room</option>
            </select>
            <select name="Bed">
                <option value="" selected>Bedding Type</option>
                <option value="Single">Single</option>
                <option value="Double">Double</option>
            </select>
            <input type="date" name="cin" required>
            <input type="date" name="cout" required>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </section>
</body>
</html>
