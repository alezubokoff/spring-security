Comando para subir uma maquina mysql
docker run --name mysql-local -e MYSQL_ROOT_PASSWORD=123456 -e MYSQL_DATABASE=app-mysql -d -p 3306:3306 mysql:8.2.0

* [x] Cadastro de usuário
* [] Solicitação de token
* [] Validação token