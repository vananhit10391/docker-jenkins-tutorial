FROM mysql/mysql-server:latest
ENV MYSQL_ROOT_PASSWORD='root'
ENV MYSQL_DATABASE='employee_manage'
ENV MYSQL_ROOT_HOST='%'
COPY ./init.sql /docker-entrypoint-initdb.d/init.sql