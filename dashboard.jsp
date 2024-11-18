<%@ page import="java.util.*" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>BlueBird - Admin</title>
    <link rel="stylesheet" href="./css/dashboard.css">

    <!-- Chart.js -->
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>

    <!-- Morris.js -->
    <link rel="stylesheet" href="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.css">
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.0/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/morris.js/0.5.1/morris.min.js"></script>
</head>
<body>
    <div class="databox">
        <div class="box roombookbox">
            <h2>Total Booked Room</h2>
            <h1>${roombookRow} / ${roomRow}</h1>
        </div>
        <div class="box guestbox">
            <h2>Total Staff</h2>
            <h1>${staffRow}</h1>
        </div>
        <div class="box profitbox">
            <h2>Profit</h2>
            <h1>${totalProfit} <span>&#8377</span></h1>
        </div>
    </div>

    <div class="chartbox">
        <div class="bookroomchart">
            <canvas id="bookroomchart"></canvas>
            <h3 style="text-align: center;margin:10px 0;">Booked Room</h3>
        </div>
        <div class="profitchart">
            <div id="profitchart"></div>
            <h3 style="text-align: center;margin:10px 0;">Profit</h3>
        </div>
    </div>

    <script>
        const labels = ['Superior Room', 'Deluxe Room', 'Guest House', 'Single Room'];
        const data = {
            labels: labels,
            datasets: [{
                label: 'Room Booking',
                backgroundColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(255, 159, 64, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(153, 102, 255, 1)'
                ],
                //data: <%= Arrays.toString((int[]) request.getAttribute("roomTypeCounts")) %>
            }]
        };
        const doughnutchart = {
            type: 'doughnut',
            data: data,
            options: {}
        };
        new Chart(document.getElementById('bookroomchart'), doughnutchart);
    </script>

    <script>
        Morris.Bar({
            element: 'profitchart',
            xkey: 'date',
            ykeys: ['profit'],
            labels: ['Profit'],
            hideHover: 'auto',
            stacked: true,
            barColors: ['rgba(153, 102, 255, 1)']
        });
    </script>
</body>
</html>
