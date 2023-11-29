# IWA-candidate-api

API externe candidats

## Installation et utilisation

Premièrement, un fichier de candidats nommé `fake_candidates.json` est requis dans le dossier racine.  
Ce fichier a déjà été généré, mais vous pouvez faire le votre (voir [../generation/README.md](../generation/README.md) pour générer ce fichier.)

### Docker

```bash
# build
docker build . -t iwa-candidates-api

# run
docker run -p 49168:3000 -d iwa-candidates-api

# it is now running on http://localhost:49168
```

### Node

Assurez-vous d’avoir node installé.

```bash
# installation des dépendances
npm install

# mode développement
npm run dev

# mode production
npm start

# it is now running on http://localhost:3000
```

## Format des données

Voir [format.txt](format.txt)
