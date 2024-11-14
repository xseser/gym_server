cd gym-bdd-tests
mvn clean install
cd target
allure generate --clean
allure serve -h localhost
