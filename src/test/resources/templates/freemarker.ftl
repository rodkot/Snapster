<#-- Шаблон офисов и их сотрудников -->
<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Offices Report</title>
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
            font-family: Arial, sans-serif;
        }
        .departament-list {
        display: flex;
            gap: 10px;
            margin-top: 10px;
        }
        .departament-list img {
            width: 50px;
            height: 50px;
            border-radius: 50%;
            border: 1px solid #ccc;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border: 1px solid #ccc;
        }
        th {
            background-color: #f4f4f4;
        }
        .no-employees {
            color: #999;
            font-style: italic;
        }
    </style>
</head>
<body>
<h1>Offices and Employees</h1>

<#-- Проверяем, есть ли офисы -->
<#--<#if offices?size > 0>-->
<#-- Перебираем офисы -->
    <#list offices as office>
        <section>
            <h2>${office.name}</h2>
            <p><strong>Location:</strong> ${office.location}</p>

            <#if office.resources??>
                <div>
                    <strong>Resources:</strong>
                <#list office.resources as resource>
                    <p class="resources" style="background-color: ${['#ff5733', '#33ff57', '#3357ff'][resource?index % 3]};">
                        ${resource}
                    </p>
                </#list>
                </div>
            </#if>


            <#list office.employees as employee>
<#--                <div class="departament-list">-->
<#--                    <#list employee.departments as department>-->
<#--                        <#macro departmentImage departmentName>-->
<#--                            <img class="departament-list"-->
<#--                                 src="-->
<#--                               <#switch departmentName>-->
<#--                               <#case 'IT'>/images/it.png-->
<#--                               <#case 'HR'>/images/hr.png-->
<#--                               <#case 'Finance'>/images/finance.png-->
<#--                               <#case 'Marketing'>/images/marketing.png-->
<#--                               <#default>/images/default.png-->
<#--                               </#switch>" alt="${departmentName} Department" />-->
<#--                        </#macro>-->

<#--                        <@departmentImage departmentName=department.name />-->
<#--                    </#list>-->
<#--                </div>-->
            </#list>
                <table>
                    <thead>
                    <tr>
                        <th>Personal Table</th>
                        <th>Name</th>
                        <th>Position</th>
                        <th>Salary</th>
                        <th>Experience (years)</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list office.employees as employee>
                        <tr>
                            <td>${employee.id}</td>
                            <td>${employee.name}</td>
                            <td>${employee.position}</td>
                            <td>${employee.salary?string.currency}</td>
                            <td>${employee.experience}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
        </section>
    </#list>
<#--<#else>-->
<#--    <p>No offices available.</p>-->
<#--</#if>-->
</body>
</html>