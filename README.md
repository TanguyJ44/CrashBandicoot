# CrashBandicoot
Projet Supinfo (1PROJ)

Réalisation du jeu Crash Bandicoot en 2D (développement + design) sous la forme d'un demake

# Présentation
Crash Bandicoot est un jeu vidéo de plates-formes développé par Naughty Dog et édité par Sony Computer Entertainment en 1996 sur PlayStation. Il constitue le premier épisode de la série Crash Bandicoot.

Crash Bandicoot relate la création de l'anthropomorphe Crash Bandicoot, le héros éponyme du jeu, par le Docteur Neo Cortex et le Docteur Nitrus Brio, les deux antagonistes principaux du jeu. L'histoire du jeu suit ensuite les aventures de Crash qui a pour mission d'arrêter ses deux créateurs dans leur quête de domination du monde, nettoyer toute la pollution qu'ils ont causée, et sauver sa petite amie, Tawna, une femelle bandicoot également créée par les Docteurs Cortex et Nitrus Brio.

# Langage et librairies
Langage : Java 8 (SE)
- Dev Kit : JDK 8
- LWJGL / GLFW / OpenGL / OpenCL / OpenAL / Slick

# Dev' Logs
- Création du projet
- Implémentation de la librairie LWJGL
- Création de la classe Components
- Création de la fenêtre principale
- Initialisation du moteur de jeu
- Initialisation du moteur de rendu
- Implémentation des méthodes de gestion graphique
- Rendu des images
- Mise à l'échelle 
- Prise en charge du redimensionnement
- Correction bug d'affichage
- Implémentation du système d'entité
- Ajout du joueur
- Couleur & Alpha
- Amélioration qualité images
- Correction de bug "Sprite Sheet"
- Ajout du déplacement personnage
- Alternance des "Sprite Sheet"
- Ajout des colisions (v1)
- Implémentation de la gravitée
- Ajout du saut
- Implémentation des animations
- Ajout des fruits
- Ajout des colisions (v2)
- Fonction reload
- Ajout des caisses
- Animation des objets
- Ajout de texte
- Ajout de Akuaku
- Ajout du sprite sheet "saut du personnage'
- Mise à jour du design des fruits
- Correction de bugs mineurs
- Ajout du header
- Animation de mouvement Akuaku
- Ajout écran de chargement
- Ajout des touches de debugs
- Amélioration des pièges (fonction random)
- Ajout de nouvelles textures
- Ajout de l'ennemie crabe
- Déplacement et détection du crabe
- Ajout de l'ennemie plante
- Détection et annimation (v1) de la plante
- Implémentation de 'Box' de détection
- Prise en charge de l'audio (v1)
- Amélioration des animations
- Audio ramassage des fruits
- Gestion des délais d'exécution
- Amélioration mort du personnage
- Détection du poisson
- Prise en charge des manettes de jeu
- Changement de niveau
- Message & animation chengement de niveau
- Gestion du Game-Over
- Message & animation game-over
- Convertion des piques en objets
- Amélioration du changement de niveau
- Image Game-Over
- Ajout de l'effet Tornade (V1)
- Action gamepad sur la tornade
- Gestion de la longueur des niveaux
- Système de caisses (V2)
- Optimisation sur le système de rendu des caisses
- Animation des caisses (v1)
- Ajout des sons caisses TnT/Nitro
- Ajout des designs de toutes les caisses
- Ajout des sons de toutes les caisses
- Intération et action des caisses
- Action de l'attaque tornade sur les caisses
- Construction du niveau 01
- Ajout du son de démarrage (initialisation)
- Construction du niveau 02
- Ajout des CheckPoints dans les niveaux
- Configuration du respawn sur le checkpoint
- Implémentation des son sur les checkpoints
- Modification des certaines textures
- Attaque tornade (V2)
- Gestion du respawn et de la vie (V2)
- Meilleur gestion d'AkuAku
- Animation de la plante (V2)
- Correction bug ordre de rendu
- Reload des checkpoints
- Correction bug caisse AkuAku
- Reload caisse TnT / Nitro
- Répartition des évenements des caisses
- Création du level 3
- Ajout des nuages dynamiques
- Animation des nuages
- Fermeture du jeu en fin de niveau 3
- Optimisation des sons level
- Finalisation collisions des caisses
- Lecture des arguments launcher
- Finalisation du jeu 


# Patchs
#### PA01-2020-02-20 
- Correction de bugs mineurs
- Optimisation de la boucle d'affichage
- Traitement des calculs différé
- Amélioration des méthodes de rendu

#### PA02-2020-03-04 
- Correction de bugs mineurs
- Amélioration des détections
- Optimisation des collisions
- Stabilité au lancement

#### PA03-2020-04-16
- Correction de bugs mineurs
- Optimisation des collisions
- Changement version JDK

#### PA04-2020-06-04
- Correction de bugs majeurs
- Stabilité sur les diffèrents OS
- Refonte des tiles & collides


# Pistes de réflexion
- Ajout de son / ambiance musicale [FAIT]
- Prise en charge des contrôleurs de jeux [FAIT]
- Mise en place d'un driver [FAIT]
- Dialogue launcheur / jeu [FAIT]
- Système de MaJ dynamique [EN RÉFLEXION]