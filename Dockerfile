# Utiliser l'image officielle de PostgreSQL comme base
FROM postgres:13

# Définir les variables d'environnement pour PostgreSQL
ENV POSTGRES_USER=bwissal
ENV POSTGRES_PASSWORD=bwissal
ENV POSTGRES_DB=batiCuisine

# Exposer le port 5432
EXPOSE 5432

# Commande par défaut pour lancer PostgreSQL
CMD ["postgres"]
