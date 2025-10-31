<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Agenda INFO24</title>
</head>
<body>

    <form action="/contatos" enctype="multipart/form-data" method="post" >

        <label for="file">Filename:</label>
        <input type="file" name="file" id="file"  />

        <label for="nome">NOME:</label>
        <input type="text" name="nome">

        <label for="email">Email:</label>
        <input type="email" name="email">

        <label for="telefone">Telefone:</label>
        <input type="text" name="telefone">

        <button  type="submit">Cadastrar Contato</button>
    </form>

</body>
</html>