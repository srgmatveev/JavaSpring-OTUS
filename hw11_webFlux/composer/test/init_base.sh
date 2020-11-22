docker exec -it tst_mongodb11 mongo -u root --eval "rs.initiate()"
docker exec -it tst_mongodb11 mongo -u root /docker-entrypoint-initdb.d/init.js
