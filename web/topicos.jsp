<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Topicos</title>
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
                <ul class="nav navbar-nav">
                    <li><a href="insereTopico.jsp">Novo Tópico</a></li>  
                        <c:choose>
                            <c:when test = "${exibeTopicos == 'todos'}">
                                <li><a href="exibeTodosTopicos">Todos Topicos</a></li> 
                            </c:when>

                            <c:when test = "${exibeTopicos == 'usuario'}">
                                <li><a href="exibeTopicosUsuario">Meus Topicos</a></li>
                            </c:when>

                            <c:otherwise>
                                <li><a href="exibeTopicosUsuario">Meus Topicos</a></li>
                            </c:otherwise>
                        </c:choose>
                    <li><a href="ranking">Ranking</a></li> 
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="#"><span class="glyphicon glyphicon-user"></span> ${usuario.login}</a></li>
                </ul>
            </div>
        </nav>

        <div class="container">
            <div class="row">
                <div class="col-sm-2">

                </div>
                <div class="col-sm-8">
                    <div class="table-responsive">   
                        <table class="table table-striped">
                            <thead>
                                <tr>
                                    <th>Tópico</th>
                                    <th>Usuário</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${topicos}" >
                                    <tr>
                                        <td><a href="exibeTopico?idTopico=${item.id}">${item.titulo}</a> </td>
                                        <td>${item.usuario.nome}</td>
                                    </tr>
                                </c:forEach>
                            </tbody>        
                        </table>
                    </div>  	
                </div>
                <div class="col-sm-2">

                </div>
            </div>
        </div>


    </body>
</html>

