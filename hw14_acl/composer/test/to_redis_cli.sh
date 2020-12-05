read pswd <./.redis-passw
#let pswd='12345'
#echo $pswd
docker exec -it redis_tst redis-cli -a $pswd


