docker exec -it tst_mongodb9 mongo -u root --eval "rs.initiate()"
docker exec -it tst_mongodb9 mongo -u root /docker-entrypoint-initdb.d/init.js
