# IWA-Backend

Backend du projet IWA ([lien vers le front-end](https://github.com/ach-ber/IWA)).

Chaque microservice est stocké dans un dossier à la racine.

## préparation

Entrer les bonnes valeurs dans les fichiers `application.properties` pour les micro-services `service-review` et `service-job`.

Pour cela, voir les READMEs respectifs : [service-review README](./service-review/README.md), [service-job README](./service-job/README.md)

## build

1. Avec IntelliJ, aller dans maven (logo m à droite), `backend`, `Lifecycle` -> install
2. `docker-compose build`

Pour build un module en particulier (ex: si `service-job` a été modifié) :
1. Avec IntelliJ, aller dans maven (logo m à droite) : `backend` -> `service-job` -> `install`
2. `docker-compose build service-job`.

## run

`docker-compose up`

## drop tables
psql -U admin -d <nom_de_la_bd>
\d
DROP TABLE <nom_de_la_table>;
