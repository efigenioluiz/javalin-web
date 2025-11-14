<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>


    <h1>Exemplo SELECT</h1>

    <!-- <select multiple name="pessoas" id="pessoas"> -->
    <#list pessoas?keys as pessoa>

        <input type="checkbox"> ${pessoas[pessoa].nome}  </input>

    </#list>

    <select >
        <#list pessoas?keys as pessoa>
            <option value="${pessoa}">${pessoas[pessoa].nome} - ${pessoas[pessoa].idade} anos</option>
        </#list>

    </select>


</body>
</html>