FROM mariadb:10.3.6

ENV MYSQL_ROOT_PASSWORD secretadmin
ENV MYSQL_DATABASE testDB
ENV MYSQL_USER user
ENV MYSQL_PASSWORD secretuser

ADD sql-files/ /docker-entrypoint-initdb.d/