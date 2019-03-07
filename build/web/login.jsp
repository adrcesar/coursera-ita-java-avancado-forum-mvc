
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Login</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-sm-4">

                </div>
                <div class="col-sm-4">
                    <div class="container col-sm-12">
                        <div class="container col-sm-6 center-block">
                            <img src="coursera.png" class="img-rounded img-responsive center-block" alt="coursera" >
                        </div>	

                        <div class="container col-sm-6 center-block">
                            <img src="ita.png" class="img-rounded img-responsive center-block" alt="ita" >
                        </div>	

                        <h1 class="text-center">Login</h1>

                        <img src="img_avatar1.jpg" class="img-circle img-responsive center-block" alt="avatar" >

                        <form name="myForm" method="post" action="login">
                            <div class="form-group">
                                <label for="uname"><b>Usuário</b></label>
                                <input type="text" class="form-control" placeholder="login" name="login" required value="">
                            </div>

                            <div class="form-group">
                                <label for="psw"><b>Senha</b></label>
                                <input type="password" class="form-control" placeholder="Senha" name="senha" required>
                            </div>

                            <button type="submit" class="btn btn-primary btn-block" >Login</button>
                        </form>
                        <br>    
                        <c:if test = "${erro == 'S'}">
                            <p class="text-danger">${erroMSG}.</p>
                        </c:if>
                        <a href="cadastro.jsp"><span class="glyphicon glyphicon-user"></span>Cadastre-se</a>    
                    </div>
                </div>
                <div class="col-sm-4">

                </div>
            </div>
        </div>
    </body>
</html>
