#!/bin/sh

# Define variables
DB_CONTAINER_NAME="some-postgres"
DB_NETWORK="gym-network"
DB_PASSWORD="mysecretpassword"
DB_USER="postgres"
DB_NAME="gym_database"
SCHEMA_FILE="gym-database/schema.sql"
DB_PORT="5432"

echo "Stopping and removing existing PostgreSQL container (if any)..."
docker rm -f $DB_CONTAINER_NAME > /dev/null 2>&1 || true

docker network ls | grep $DB_NETWORK > /dev/null || \
  docker network create $DB_NETWORK

echo "Running new PostgreSQL container..."
docker run --name $DB_CONTAINER_NAME --network $DB_NETWORK \
  -e POSTGRES_PASSWORD=$DB_PASSWORD -p $DB_PORT:$DB_PORT -d postgres || \
  { echo "Error running PostgreSQL container"; exit 1; }

echo "Waiting for PostgreSQL to start..."
sleep 10

echo "Creating database $DB_NAME..."
docker exec -u $DB_USER $DB_CONTAINER_NAME psql -U $DB_USER -c "DROP DATABASE IF EXISTS $DB_NAME;" || \
  { echo "Error dropping existing database $DB_NAME"; exit 1; }
docker exec -u $DB_USER $DB_CONTAINER_NAME psql -U $DB_USER -c "CREATE DATABASE $DB_NAME;" || \
  { echo "Error creating database $DB_NAME"; exit 1; }

echo "Executing schema.sql..."
cd ..
docker cp $SCHEMA_FILE $DB_CONTAINER_NAME:/schema.sql || \
  { echo "Error copying schema.sql to PostgreSQL container"; exit 1; }

docker exec -u $DB_USER $DB_CONTAINER_NAME psql -U $DB_USER -d $DB_NAME -f /schema.sql || \
  { echo "Error executing schema.sql"; exit 1; }

echo "PostgreSQL container is running and schema.sql executed successfully."
echo "PostgreSQL is accessible at:"
echo "Host: localhost"
echo "Port: $DB_PORT"
echo "Database name: $DB_NAME"
echo "Username: $DB_USER"
echo "Password: $DB_PASSWORD"
