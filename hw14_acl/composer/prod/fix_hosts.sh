cont_name=mongodb14
clear

container=`docker ps  | grep -e "\s\{1,\}${cont_name}"`
set $container
container_id=$1
ip=`docker inspect $container_id| grep -e '\"IPAddress\":\s\{1,\}\"[0-9]'`
ip=`echo $ip | sed 's/"IPAddress":[[:space:]]\+"//g'`
ip=`echo $ip | sed 's/",//g'`
#echo $ip
sudo sed -i.bak "/[\t[:space:]]\+$cont_name\$/d" /etc/hosts
sudo sed -i "10i$ip $cont_name" /etc/hosts