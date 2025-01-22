<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Companies Report</title>
</head>
<link rel="stylesheet" type="text/css" href="../style.css">
<body>
<h1>Companies Report</h1>
#foreach($company in $companies)
<div class="company-section">
    <div class="company-info"><h2>$company.name</h2>
        <p><strong>Code:</strong> $company.code</p>
        <p><strong>Director:</strong> $company.director.name</p>
        <p><strong>Main office:</strong> $company.generalOffice.name</p></div>
    <div class="company-logo"><img src="../assets/company/$company.logo" alt="$company.name logo"/></div>
</div>
<h3>Additional Offices</h3>
#foreach($office in $company.additionalOffices)
<section class="office-section">
    <section class="office-info-section">
        <div class="office-info">
            <h4>$office.name</h4>
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
        </div>
        <div class="office-photo">
            <img src="../assets/office/$office.photo" alt="office.name logo"/>
        </div>
    </section>
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
#end
</body>
</html>