# Microservice Recruiter

Ecoute sur le port 8302

permet les CRUD :
  - recruiter
  - address
  - company
  - establishment

## Sécurité

### Roles

Les rôles existants sont :
  - ADMIN
  - FREE
  - SILVER
  - GOLD
  - PLATINUM

### Configuration

La configuration de la sécurité se fait dans le package security:

1. Ajouter les classes CustomAuthentication, RolesFromHeaderFilter, SecurityConfig
2. Créer deux classes controller PublicController et ProtectedController sous le format suivant :

```java
Routes publiques :
  - '/api/public/**'

Routes protégées :
  - '/api/protected/**'
```

3. Ajouter les rôles dans la classe ProtectedController:
    
```java
Routes protégées accessible par les rôles :
  - 'ADMIN'
  - 'USER'
 @PreAuthorize("hasAnyRole('ADMIN','USER')")

Routes protégées accessible par les rôles :
  - 'ADMIN'
@PreAuthorize("hasRole('ADMIN')")
```

### Initialisation

2 utilisateurs sont créés au démarrage de l'application :
  - ADMIN / email : admin@gmail.com  / password : admin
  - USER / email : JohnDoe@gmail.com / password : password

