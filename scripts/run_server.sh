#!/bin/sh

get_local_ip() {
  local_ip=$(ifconfig | grep 'inet ' | grep -v '127.0.0.1' | awk '{print $2}' | head -n 1)
  echo "$local_ip"
}
HOST_IP=$(get_local_ip)
echo "Local IP address detected: $HOST_IP"


./scripts/run_db.sh
./scripts/run_backend_server.sh postgres mysecretpassword jdbc:postgresql://${HOST_IP}:5432/gym_database