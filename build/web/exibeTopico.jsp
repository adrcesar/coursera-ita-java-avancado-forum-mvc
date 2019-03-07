<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Topico</title>
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
                    <h6> <span class="glyphicon glyphicon-user"></span> ${topico.usuario.nome}</h6>
                    <h4> ${topico.titulo}</h4>   
                    <h5> ${topico.conteudo}</h5>
                    <hr/>

                    <c:forEach var="item" items="${comentarios}" >
                        <h6><span class="glyphicon glyphicon-user"></span> ${item.usuario.nome}</h6>
                        <h5>${item.comentario}</h5>
                        <hr/>
                    </c:forEach>   

                    <form name="cadastroResposta" action="insereComentario">
                        
                        <input type="hidden" name="idTopico" value="${topico.id}">
                        
                        <div class="form-group">
                            <label for="resposta"><b>Resposta</b></label>
                            <textarea rows="5" cols="100" name="comentario" required></textarea>
                        </div>    

                        <button type="submit" class="btn btn-primary" >Enviar</button>
                        <a href="exibeTodosTopicos" class="btn btn-primary" role="button" aria-pressed="true" required>Voltar</a>
                    
                    </form>    

                </div>
                <div class="col-sm-2">

                </div>
            </div>
        </div>
    </body>
</html>



