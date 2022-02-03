# README

Cette application est un prototype de VaxTodo permettant de visualisé les différentes fonctionnalités 

## Fonctionnalités

- Réserver/annuler une période de vaccination
- Gérer les comptes des visiteurs, bénévoles
- Gérer les profiles de vaccination des clients
- Création et impression d'un questionnaire

## Manuel d'utilisation

###########################
## App
###########################

Pour démarrer l’application, il suffit de partir la console de commande et de se rendre dans le 
répertoire du fichier vaxtodo.jar, puis d'exécuter la commande suivante: "java -jar vaxtodo.jar". 
Il est important que le csv soit dans le même répertoire que le fichier jar.

######################################################
## Interface Log in (pour se connecter au système)
######################################################

Selon le code d’identifiant et le mot de passe, vous pouvez accéder au menu employé ou au menu bénévole. 
Exemple de code d’identifiant et mot de passe:
 
Employé: Billy TheGreat
Code d’identifiant:123456789
Mot de passe: Secret?123
 
Employé: Ginnette Desjardins
Code d’identifiant:666666666
Mot de passe: Motdepass123!

Bénévole: Stephan Sparrow
Code d’identifiant: 789456123
Mot de passe: Ben1456!

Bénévole: Romeo Alpha
Code d’identifiant: 470726938
Mot de passe: Alpha12345!

Le message affiche un pop up pour dire si la connexion est réussie ou non.

###########################
## Interface Menu Employé
###########################

À partir de l'interface menu employé, un employé peut accéder à la gestion des visiteurs, la gestion des 
bénévoles, les questionnaires, le profil de vaccination et le calendrier des rendez-vous en cliquant sur 
le bouton respectif. De plus, vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous 
demander si vous voulez vraiment vous déconnecter du système. 

###########################
## Interface Menu Bénévole
###########################

À partir de l’interface menu bénévole, un bénévole peut accéder à la liste des visiteurs et le calendrier 
de rendez-vous. De plus, vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous 
demander si vous voulez vraiment vous déconnecter du système. 

###########################
## Interface Visiteurs
###########################

Cette interface est accessible seulement aux Employés.

Ajouter:

Pour ajouter un visiteur, veuillez remplir les champs. Pour la date de naissance, cliquez sur l'icône du 
calendrier et sélectionnez la date. Cliquez sur le bouton "Ajouter''.

Dans un cas d’erreur, par exemple le numéro de téléphone ne contient pas 10 chiffres, un message pop up 
va s’afficher.

Si un champ est vide, la couleur du text field va changer en rouge pour mentionner qu’il faut remplir ce champ, 
car c'est obligatoire de remplir tous les champs. 

Si tout est correct, l'ajout va être effectué et va être affiché au tableau à droite des text field. Un pop up 
va mentionner que la personne est ajoutée et un courriel est envoyé au visiteur. Le courriel contient le numéro 
de compte.

Modifier:

Pour modifier un visiteur, veuillez cliquer sur la ligne du tableau contenant le visiteur recherché. Les 
informations sauf le numéro compte sont affichées dans les text field. Modifiez le champ désiré et cliquez sur modifier.
De même que ajouter, un message d’erreur s'affiche si le champ est mal écrit dans le text field. 

Supprimer:  

Pour supprimer un visiteur, cliquez sur la ligne du tableau contenant le visiteur recherché. Ensuite, cliquez sur 
supprimer. Un pop up va s’afficher pour confirmer la suppression. Dans le cas où il existe un questionnaire et/ou 
un profil de vaccination connecté au même numéro de compte du visiteur, on va les supprimer de la base de donnée.

Rechercher:

Vous pouvez rechercher un visiteur spécifique en écrivant dans le textfield le numéro de compte, le courriel ou la 
date de naissance du visiteur. Ensuite, cliquez sur le bouton rechercher. Un filtre va être appliqué sur le tableau 
et affichera seulement le visiteur recherché.

Rafraichir:

Ce bouton peut être utilisé pour effacer le texte dans tous les textfield. De plus, mettre à jour le tableau des 
informations des visiteurs. 

Retour:

Bouton retour pour retourner au menu des employés.

Quitter:

Vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous demander si vous voulez vraiment 
vous déconnecter du système. 

######################################################
## Interface Visiteur (pour bénévole)
######################################################

Cette page contient un tableau avec les visiteurs. Il est possible de regarder le tableau et faire une 
recherche seulement.

Il est aussi possible de rafraîchir le tableau ou retourner vers le menu bénévole ou se déconnecter avec 
le bouton “Quitter”.

###########################
## Interface Questionnaire
###########################

Cette interface est accessible seulement aux Employés.

Ajouter:

Pour ajouter un questionnaire, veuillez remplir les champs. Pour la date de naissance et la date de visite, 
cliquez sur l'icône du calendrier et sélectionnez la date. Pour la date de visite, c’est impossible de cliquer 
sur les journées de fin de semaine, car le centre de vaccination est ouvert seulement du lundi au vendredi. 
Pour ce qui est du bouton radio, vous pouvez choisir oui ou non, et pas les deux. Pour la liste déroulante, 
le nom du vaccin par défaut c’est NONE et vous pouvez sélectionner un des quatre vaccins disponibles. 

Pour ce qui est des informations post-vaccination, on ne va pas les ajouter lorsqu’on remplit le questionnaire, 
car ces informations vont être ajoutées après avoir rencontré le professionnel de santé. Donc, pas besoin de 
cocher Info post-Vaccination pour afficher les informations à remplir.

Attention de bien écrire le numéro de compte de client, sinon un message d'erreur s'affiche pour dire que le 
compte n’existe pas.

Dans un cas d’erreur, par exemple l’assurance maladie ne contient pas 10 chiffres, un message pop up va s’afficher.

Si un champ est vide, la couleur du text field va changer en rouge pour mentionner qu’il faut remplir ce champ, 
car c'est obligatoire de remplir tous les champs. 

Si tout est correct, l'ajout va être effectué et va être affiché au tableau à droite des text field. Pour les 
champs de post-vaccination, pour la colonne “Vaccin fait”, on affiche “Non”, code du vaccin on affiche “vide” et 
pour le “Nom du vaccin” on affiche NONE. De plus, lors de l’ajout on affiche le message suivant: "Questionnaire 
ajouté dans le système, voulez-vous imprimer le Questionnaire?" et vous pouvez sélectionner oui. Un autre message 
qui va apparaître pour dire "Document en cours d'impression".   

Vous pouvez cliquer sur cancel si vous voulez changer un champ avant l’ajout du questionnaire.  

Modifier:

Pour modifier un questionnaire, veuillez cliquer sur la ligne du tableau contenant le questionnaire recherché. 
Vous pouvez aussi utiliser la barre de recherche et écrire le numéro de compte pour trouver le questionnaire. 
Les informations sont affichées dans les text field. Cliquez sur le checkbox post-vaccination pour ajouter le 
reste des informations et cliquez sur modifier.

Rechercher:

Vous pouvez rechercher un questionnaire spécifique en écrivant dans le textfield le numéro de compte du visiteur. 
Ensuite, cliquez sur le bouton rechercher. Un filtre va être appliqué sur le tableau et affichera seulement le 
questionnaire recherché.

Rafraichir:

Ce bouton peut être utilisé pour effacer le texte dans tous les textfield. De plus, mettre à jour le tableau des 
informations sur les questionnaires. 

Retour:

Bouton retour pour retourner au menu des employés.

Quitter:

Vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous demander si vous voulez vraiment 
vous déconnecter du système. 

###########################
## Interface bénévoles
###########################

Ajouter:

Pour ajouter un visiteur, veuillez remplir les champs. Pour la date de naissance, cliquez sur l'icône du calendrier 
et sélectionnez la date. Cliquez sur le bouton "Ajouter''. 

Dans un cas d’erreur, par exemple le numéro de téléphone ne contient pas 10 chiffres, un message pop up va s’afficher.

Si un champ est vide, la couleur du text field va changer en rouge pour mentionner qu’il faut remplir ce champ, 
car c'est obligatoire de remplir tous les champs. 

Si tout est correct, l'ajout va être effectué et va être affiché au tableau à droite des text field. Le mot de passe 
ne s’affiche pas dans le tableau. Un pop up affichera que le compte est créé avec succès.

Modifier:

Pour modifier un bénévole, veuillez cliquer sur la ligne du tableau contenant le bénévole recherché. Les informations
sauf le numéro de compte et le mot de passe sont affichées dans les text field. Modifiez le champ désiré et cliquez sur 
modifier. De même que ajouter, un message d’erreur s'affiche si le champ est mal écrit dans le text field. 

Supprimer:

Pour supprimer un bénévole, cliquez sur la ligne du tableau contenant le visiteur recherché. Ensuite, cliquez sur supprimer. 
Un pop up va s’afficher pour confirmer la suppression. 

Rechercher:

Vous pouvez rechercher un bénévole spécifique en écrivant dans le textfield le numéro de compte du bénévole. Ensuite, 
cliquez sur le bouton rechercher. Un filtre va être appliqué sur le tableau et affichera seulement le bénévole recherché.

Rafraichir:

Ce bouton peut être utilisé pour effacer le texte dans tous les textfield. De plus, mettre à jour le tableau des 
informations des bénévoles. 

Retour:

Bouton retour pour retourner au menu des bénévoles.

Quitter:

Vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous demander si vous voulez vraiment vous 
déconnecter du système. 

####################################
## Interface prise de rendez-vous
####################################

Cette interface est accessible par les bénévoles et les employés. Elle servira à enregistrer les dates de rendez-vous 
pris par le client et les disponibilités pour les prochains. 

Ajouter:

Pour ajouter un nouveau rendez-vous, la contrainte de rendez-vous à respecter est de minimum 72 heures à l'avance 
sauf si c’est une visite spontanée.
 
Les conditions nécessaires pour pouvoir avoir une prise de rendez-vous complète:

- Remplir tous les champs et sélectionner une dose. Sans quoi le texte sera en rouge indiquant à l’utilisateur le 
champ à compléter.
 
- Choisir une date et une plage horaire disponible. S’il y a 15 clients sur la plage horaire choisie, il sera 
impossible de prendre un rendez-vous . Le système avisera l’utilisateur s’il essaye d’ajouter un client de trop 
à une plage horaire pleine. Il devra en choisir une autre plage horaire.

Accéder: 

Ce bouton permet d'accéder à tous les rendez-vous présents dans à une date et une plage horaire. Ici, il suffit 
simplement de choisir la date en question ainsi que la plage horaire qu’on désire regarder. Si aucune réservation 
n’a été faite, le tableau sera vide. Dans le cas contraire, une liste sera affichée avec tous les clients censés être 
présent à cette date et heure. 

Annuler:

Pour annuler une réservation, il faut sélectionner le jour du rendez-vous et la plage horaire. Il faut sélectionner 
la ligne dans le tableau et annuler le rendez-vous en cliquant sur annuler.

Un Pop up s'affiche, donnant la possibilité à l’employé de confirmer le choix du client de supprimer sa réservation.
 
Ps: Une réservation n’est pas modifiable. On peut juste l’annuler et en prendre une nouvelle au besoin.

Quitter :

Vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous demander si vous voulez vraiment 
vous déconnecter du système. 

Retour:

Bouton retour pour retourner au menu des employés ou bénévoles selon l’utilisateur connecter.

######################################
## Interface Profil de Vaccination
######################################

Cette interface est accessible seulement aux Employés.

Ajouter:

Pour ajouter un profil de vaccination, veuillez remplir les champs. Pour la date de vaccination, cliquez sur 
l'icône du calendrier et sélectionnez la date. Pour la date de vaccination, c’est impossible de cliquer sur 
les journées de fin de semaine, car le centre de vaccination est ouvert seulement du lundi au vendredi. Pour 
ce qui est du bouton radio, vous pouvez choisir oui ou non, et pas les deux. Pour la liste déroulante, le nom 
par défaut c’est NONE et vous pouvez sélectionner un des quatre vaccins disponibles.

Attention de bien écrire le numéro de compte du visiteur, sinon un message d'erreur s'affiche pour dire que le 
numéro de compte n’existe pas.

Dans un cas d’erreur, par exemple le code du vaccin qui est supérieur à 24 chiffres, un message pop up va s’afficher.

Si un champ est vide, la couleur du text field va changer en rouge pour mentionner qu’il faut remplir ce champ, 
car c'est obligatoire de remplir tous les champs. 

Si tout est correct, l'ajout va être effectué et va être affiché au tableau à droite des text field. Un pop up 
va montrer le rapport de Vaccination qui est produit sous la forme de pdf et aussi le code QR. Ensuite, vous
allez voir un autre pop up pour dire que le profil de vaccination est envoyé au client par courriel.

Modifier:

Pour modifier un profil de vaccination, veuillez cliquer sur la ligne du tableau contenant le profil recherché. 
Les informations sont affichées dans les text field. Modifiez le champ désiré et cliquez sur modifier. 
Ensuite, vous allez voir un autre pop up pour dire que le profil de vaccination est envoyé au client par courriel. 
De même que ajouter, un message d’erreur s'affiche si le champ est mal écrit dans le text field.

Rechercher:

Vous pouvez rechercher un profil de vaccination spécifique en écrivant dans le textfield le numéro de compte du visiteur. 
Ensuite, cliquez sur le bouton rechercher. Un filtre va être appliqué sur le tableau et affichera seulement le profil de 
vaccination recherché.

Rafraichir:

Ce bouton peut être utilisé pour effacer le texte dans tous les textfield. De plus, mettre à jour le tableau des 
informations sur les profils de vaccination. 

Retour:

Bouton retour pour retourner au menu des employés.

Quitter:

Vous pouvez quitter à partir de cette page. Un Pop up s’affichera pour vous demander si vous voulez vraiment vous 
déconnecter du système.







