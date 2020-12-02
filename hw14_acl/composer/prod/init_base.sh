docker exec -it mongodb14 mongo -u root --eval "rs.initiate()"
docker exec -it mongodb14 mongo -u root /docker-entrypoint-initdb.d/init.js
