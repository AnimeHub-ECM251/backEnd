FROM mysql:latest

EXPOSE 3306

ENV MYSQL_ROOT_PASSWORD password

COPY animeHubMock.sql /docker-entrypoint-initdb.d/script.sql
RUN chmod -R 775 /docker-entrypoint-initdb.d


