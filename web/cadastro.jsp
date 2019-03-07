<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Cadastro de Usuario</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <script>
            function validateForm() {
                var senha = document.forms["cadastroForm"]["senha"].value;
                var repeteSenha = document.forms["cadastroForm"]["repeteSenha"].value;
                if (senha != repeteSenha) {
                    alert("Senhas não conferem!!!");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <img src="ita.png" class="img-rounded img-responsive center-block" alt="ita" width="152" height="118">
                </div>
            </div>
        </nav>

        <div class="container">
            <div class="row">
                <div class="col-sm-3">

                </div>
                <div class="col-sm-6 container">
                    <form name="cadastroForm" method="POST" action="cadastro" onsubmit="return validateForm()">    
                        <div class="form-group">	
                            <label for="login"><b>login</b></label>
                            <input type="text" class="form-control" placeholder="login" name="login" required>
                        </div>	

                        <div class="form-group">
                            <label for="email"><b>Email</b></label>
                            <input type="email" class="form-control" placeholder="Email" name="email" required>
                        </div>	

                        <div class="form-group">
                            <label for="nome"><b>Nome</b></label>
                            <input type="text" class="form-control" placeholder="Nome" name="nome" required>
                        </div>

                        <div class="form-group">
                            <label for="psw"><b>Senha</b></label>
                            <input type="password" class="form-control" placeholder="Senha"  name="senha" required>
                        </div>

                        <div class="form-group">
                            <label for="psw-repeat"><b>Repita a senha</b></label>
                            <input type="password" class="form-control" placeholder="Repita a senha" name="repeteSenha" required>
                        </div>

                        <button type="submit" class="btn btn-primary " >Confirmar</button>
                        <a href="login.jsp" class="btn btn-primary" role="button" aria-pressed="true" required>Cancelar</a>
                    </form>
                </div>
                <div class="col-sm-3">

                </div>
            </div>
        </div>
    </body>
</html>





