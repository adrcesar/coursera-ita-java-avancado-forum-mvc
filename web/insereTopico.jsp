<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Incluir Topico</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    </head>
    <body>



        <nav class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <img src="ita.png" class="img-rounded img-responsive center-block" alt="ita" width="152" height="118">
                </div>
                
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${usuario.login}</a></li>
                </ul>

            </div>
        </nav>

        <div class="container">
            <div class="row">
                <div class="col-sm-2">

                </div>
                <div class="col-sm-8 container">
                    <form name="cadastroForm" method="POST" action="insereTopico">
                        <div class="form-group">	
                            <label for="titulo"><b>Título</b></label>
                            <input type="text" class="form-control" placeholder="Título" name="titulo" required>
                        </div>	

                        <div class="form-group">
                            <label for="conteudo"><b>Conteúdo</b></label>
                            <textarea rows="10" class="form-control" cols="100" name="conteudo" required></textarea>
                        </div>	

                        <button type="submit" class="btn btn-primary " >Confirmar</button>
                        <a href="exibeTodosTopicos" class="btn btn-primary" role="button" aria-pressed="true" required>Cancelar</a>
                        


                    </form>
                </div>
                <div class="col-sm-2">

                </div>
            </div>
        </div>


    </body>
</html>