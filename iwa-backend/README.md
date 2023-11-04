Tuto YouTube : https://www.youtube.com/watch?v=lS1GwdIfk0c

Télécharger postgresql et configurer pgAdmin 4 : https://www.youtube.com/watch?v=ltvRsnka7Mo

Dossier model :
- Créer un dossier model
- Créer un fichier User.java

Dossier repo :
- Créer un dossier repo
- Créer un fichier UserRepository.java

Dossier controller :
- Créer un dossier controller
- Créer un fichier UserController.java

## SQL import/export

```bash
# dump data from recruiterService database, 
docker exec -i iwa-backend_db_1 pg_dump -U postgres recruiterService > dump.sql

# remove current data (stored in recruiterService database)
docker exec -it iwa-backend_db_1 dropdb -U postgres recruiterService

# import dumped data
cat dump.sql | docker exec -i iwa-backend_db_1 psql -U postgres
```
