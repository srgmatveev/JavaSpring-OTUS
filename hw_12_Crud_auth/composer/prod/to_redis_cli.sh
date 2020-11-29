read pswd <./.redis-passw
#let pswd='12345'
#echo $pswd
docker exec -it redis redis-cli -a $pswd


