<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Ranking</title>
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
                    <li><a href="exibeTodosTopicos">Tópicos</a></li> 
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
                                    <th>Nome</th>
                                    <th>Login</th>
                                    <th>Pontos</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="item" items="${ranking}" >
                                    <tr>
                                        <td>${item.nome}</td>
                                        <td>${item.login}</td>
                                        <td>${item.pontos}</td>
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

