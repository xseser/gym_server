#!/bin/sh

DB_CONTAINER_NAME="some-postgres"

docker-compose down --volumes
./scripts/run_db.sh init.sql
mvn clean package -DskipTests
echo "Stopping and removing existing PostgreSQL container (if any exists)..."
docker rm -f $DB_CONTAINER_NAME > /dev/null 2>&1 || true

MAIL_PROFILE=mock docker-compose up -d --build
