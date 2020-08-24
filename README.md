# maiia-back
## BACK
Pour lancer le back, se placer dans le module maiia-back-parent et lancer l'application :
>cd maiia-back-parent

>mvn spring-boot:run

Le back est visible sur http://localhost:8080/posts

## FRONT
Pour lancer le front, se placer dans le module maiia-front-react et lancer le front :
>cd maiia-front-react

>npm install

>npm start

Le front est visible sur http://localhost:3000

## PACKAGE
Le module maiia-package permet de construire un unique jar encapsulant l'entièreté du projet :
>cd maiia-package

>mvn package

Un jar prêt se trouve également dans le dossier resources. L'application complète se lance avec la comande suivante :
>java -jar maiia-package-1.0-SNAPSHOT.jar

L'application est visible sur http://localhost:8080