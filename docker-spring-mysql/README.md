# docker-spring-mysql module
  Example about build spring, mysql on docker
   1. Github
   2. Docker Hub
   3. Docker compose
   4. MySQL
## Step by step
1. Install and run docker
2. Git
   - Checkout source code
   - At docker-compose.yml file:
     Update content "image: anhtruong10391/docker-spring-mysql" -> "image: ${your_docker_hub_id}/docker-spring-mysql"
3. Setting, build and run docker images
    3.1 Option 1: build and run docker images by command
        Pre:
            - Restart docker
            - Stop and remove all process on docker
              $ docker stop $(docker ps -a -q)
              $ docker rm $(docker ps -a -q)
            - Remove all image on docker
              $ docker rmi $(docker images -a -q)
            - Clean cache
              $ docker builder prune
            - Clean network
              $ docker network prune
        1. Create ‘employee-mysql’ network for mysql
           $ docker network create employee-mysql
        2. Create, build and run ‘mysql’ container for ‘mysql/mysql-server’ image
           $ docker container run --name mysqldb --network employee-mysql -e MYSQL_ROOT_PASSWORD=root -e MYSQL_ROOT_HOST=% -e MYSQL_DATABASE=employee_manage -d mysql/mysql-server
        3. Connect to ‘mysqldb’ container
           $ docker container exec -it mysqldb bash
           $ mysql -uroot -proot
        4. Create ‘employee-service ‘ container
           $ cd <path_of_employee_project> (docker-spring-mysql)
           $ ./gradlew clean
           $ ./gradlew build
           $ docker image build -t employee-service .
        5. Create, build and run ‘employee-service-container’ container (have link to mysqldb:mysql/mysql-server)
           $ docker container run --network employee-mysql --name employee-service-container --link mysqldb:mysql/mysql-server -p 9002:9002 -d employee-service
        6. Check log
           $ docker container logs -t employee-service-container
    3.2 Option 2: Build and run docker images by docker compose
        - $ docker compose up
4. Testing
   curl -H "Content-Type: application/json" -XGET localhost:9002/employee/
## Keyword refer
1. CLI docker compose
2. CLI docker
3. CLI mysql
## Others
* Refer link: https://www.javainuse.com/devOps/docker/docker-mysql
