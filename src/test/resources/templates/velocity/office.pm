<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Office Report</title>
</head>
<style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 20px;
        }
        h1, h2 {
            color: #333;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }
        .resources {
            display: inline-block;
            padding: 5px;
            margin: 5px;
            border-radius: 5px;
            color: white;
            font-size: 16px;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f4f4f4;
        }
    </style>
<body>
    <h1>Offices Report</h1>

    #foreach($office in $offices)
        <section>
            <h2>$office.name</h2>
            <p><strong>Location:</strong> $office.location</p>

            #if($office.resources)
                <div>
                    <strong>Resources:</strong>
                    #foreach($resource in $office.resources)
                      #set ($mod = $foreach.index % 3)
                        <p class="resources" style="background-color: $colors[$mod];">
                            $resource
                        </p>
                    #end
                </div>
            #end

            <table>
                <thead>
                    <tr>
                        <th>Personal Table</th>
                        <th>Name</th>
                        <th>Project</th>
                        <th>Position</th>
                        <th>Salary</th>
                        <th>Experience (years)</th>
                    </tr>
                </thead>
                <tbody>
                    #foreach($employee in $office.employees)
                        <tr>
                            <td>$employee.id</td>
                            <td>$employee.name</td>
                            <td>$employee.project.name</td>
                            <td>$employee.position</td>
                            <td>$employee.salary</td>
                            <td>$employee.experience</td>
                        </tr>
                    #end
                </tbody>
            </table>
        </section>
    #end
</body>
</html>