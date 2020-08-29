read -p "Are you sure(Y/n)? " -n 1 -r
echo # (optional) move to a new line
if [[ $REPLY =~ ^[YyДд]$ ]]; then
    docker-compose down
fi
