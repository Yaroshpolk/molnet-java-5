<%@ page import="java.util.Map" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<% Map<Integer, Integer> ages = (Map<Integer, Integer>) request.getAttribute("ages"); %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Графики</title>
    <link rel="stylesheet" href="../styles/main.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
</head>
<body>
<div class="content">
    <div class="charts">
        <div class="charts__age">
            <canvas id="ageChart">

            </canvas>
        </div>
    </div>
</div>
<script>
    let ctx = document.getElementById('ageChart');

    new Chart(ctx, {
        type: 'bar',
        data: {
            labels: [
                <% for (Integer key : ages.keySet()) { %>
                <%=key%>,
                <% } %>
            ],
            datasets: [{
                label: 'Сотрудники',
                data: [
                    <% for (Integer value : ages.values()) { %>
                    <%=value%>,
                    <% } %>
                ],
                borderWidth: 1
            }]
        },
        options: {
            scales: {
                y: {
                    beginAtZero: true
                }
            }
        }
    });
</script>
</body>
</html>
