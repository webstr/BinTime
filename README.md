# BinTime
For deploy, just open this project in IntelliJ IDEA, setup run configuration "Add new configuration -> JBoss Server -> local"

Used MySQL for saved the informations.
For change property to connect, change it in src/main/resources/hibernate.cfg.xml:
  1. Set connection username in <property name="connection.username">
  2. Set connection password in <property name="connection.pasword">

After run will be created bintime_db database, and needed schema for project.

## URL's
  1. "/" - home page, for added files.
  2. "/first" - first REST endpoint, resived several files and parse it.
  3. "/second" - second REST endpoint, show all data that have been parsed.
All URL's look like "http://localhost:8080/SpringForApp-1.0-SNAPSHOT/", "http://localhost:8080/SpringForApp-1.0-SNAPSHOT/second".

