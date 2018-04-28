Nous avons complété toutes les étapes demandées de 1 à 7

Pour se faire, nous avons créé d'abord plusieurs classes: 
Dans le package weather :

une classe RecupDonneesMetaWeather: traiter le json correspondant de l'API metaweather qui récupère toutes les données pour 5 jours.

une classe RecupDonneesPrev: traiter le json correspondant de l'API PrevisionMeteo qui récupère toutes les données pour 5 jours.

une classe RecupDonneesYahoo: traiter le json correspondant de l'API Yahoo qui récupère toutes les données pour 5 jours. ( Nous ne pouvons pas rÈcupÈrer certaines valeurs qui ne se trouvent pas dans le json ).

une classe DonnéesMétéo: cette classe représente notre wrapper, chaque API remplie son tableau DonnéesMétéo sur 5 jours

dans le package gestion :
une classe GestionAffichage qui gere l'affiche

une classe GestionArg qui gère les arguments de la ligne de commande.

Les fichiers d'historique de requêtes et de sauvegarde de résultats sont de le dossier DonneeApp à la racine du projet