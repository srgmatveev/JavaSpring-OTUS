docker exec -it mongodb111 mongo -u root --eval "rs.initiate()"
docker exec -it mongodb111 mongo -u root /docker-entrypoint-initdb.d/init.js
