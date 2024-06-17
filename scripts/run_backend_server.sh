#!/bin/sh

# Usage: ./run_backend_server.sh <DB_LOGIN> <DB_PASSWORD> <DB_URL>

if [ $# -ne 3 ]; then
  echo "Usage: $0 <DB_LOGIN> <DB_PASSWORD> <DB_URL>"
  exit 1
fi

# Extract arguments
DB_LOGIN=$1
DB_PASSWORD=$2
DB_URL=$3

# Check and kill any process running on port 9876
PORT=9876

# Stop and remove any Docker container using port 9876
EXISTING_CONTAINER=$(docker ps -q --filter "publish=$PORT")
if [ ! -z "$EXISTING_CONTAINER" ]; then
  docker stop $EXISTING_CONTAINER || { echo "Failed to stop existing Docker container"; exit 1; }
  docker rm $EXISTING_CONTAINER || { echo "Failed to remove existing Docker container"; exit 1; }
  echo "Stopped and removed existing Docker container using port $PORT"
else
  echo "No existing Docker container is using port $PORT"
fi

# Define project paths
PROJECT_PATH="gym-backend"
STARTER_PATH="$PROJECT_PATH/gym-backend-starter"

echo "Building Maven project without running tests..."
mvn package -DskipTests || { echo "Error during Maven build"; exit 1; }

echo "Building Docker image..."
docker build -t gym-backend $STARTER_PATH || { echo "Error during Docker build"; exit 1; }

echo "Running Docker container in the background..."
docker run -d -p 9876:9876 \
  -e DB_SOURCE=$DB_URL \
  -e DB_LOGIN=$DB_LOGIN \
  -e DB_PASSWORD=$2 \
  gym-backend || { echo "Error during Docker container run"; exit 1; }

echo "Container is running on port 9876"
