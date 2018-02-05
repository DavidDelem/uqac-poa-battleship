# uqac-poa-battleship

<h2>8INF957 Programmation objet avancée - Hivers 2018 - UQAC</h2>
<h3>Devoir 1</h3>
<p><b>David Delemotte, Rénald Morice</b></p>

Ce qui a été réalisé:

- [x] On peut joueur contre un autre joueur ou contre l'IA (choix au démarage)
- [x] Chaque joueur peut placer ses bateaux un an un. Il y en a 5 au total et on peut facilement en ajouter au besoin.
- [x] A chaque tour le joueur voit deux grilles: la <i>grille de défense</i> (position de ses bateaux, cases des bateaux touchés) et la <i>grille d'attaque</i> (tirs réalisés réussis ou ratés)

- [x] Pour chaque tour on peut tirer dans le champ de tir de ses bateaux (vertical ou horizontal).<br>
Le champ de tir est affiché pour aider le joueur à choisir.
- [x] Si au tour précédent l'adversaire n'a pas touché de bateau, le joueur peut déplacer un de ses bateaux.<br>
Pour cela, il indique le bateau à déplacer, le nombre de cases et la direction.

- [x] Un bateau coule après avoir été touché deux fois, il disparait alors définitivement de la carte
- [x] Le joueur qui n'a plus aucun bateau perd.

- [x] Le programme ne plante pas, des messages d'erreurs s'affichent en cas de saisie invalide.<br>
L'utilisateur peut alors modifier sa saisie.
- [x] Le programme respecte les principes de l'orienté objet, est organisé en package et est commenté.<br>
      Le diagramme des classes de notre projet est le suivant:
<p align="center">
![Diagramme des classes](img/classdiagram.png?raw=true "Diagramme des classes")
</p>
