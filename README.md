# uqac-poa-battleship

<h2>8INF957 Programmation objet avancée - Hivers 2018 - UQAC</h2>
<h3>Devoir 1</h3>
<p><b>David Delemotte, Rénald Morice</b></p>

Ce qui a été réalisé:

- [x] On peut joueur contre un autre joueur ou contre l'IA (choix au démarage)
- [x] Chaque joueur peut placer ses bateaux un an un. Il y en a 5 au total et on peut facilement en ajouter au besoin.
- [x] A chaque tour on voit deux grilles:
      - la grille de défense (position de ses bateaux, cases des bateaux touchés)
      - la grille d'attaque (tirs réalisés réussis ou ratés)
- [x] A chaque tour on peut tirer dans le champ de tir de ses bateaux. Certains ont un champ de tir vertical, d'autres horizontal. Le champ de tir est affiché pour aider le joueur à choisir.
- [x] Si au tour précédent l'adversaire n'a pas touché de bateau, j'ai le droit de déplacer un de mes bateaux. Pour cela, j'indique le bateau à déplacer, le nombre de cases et la direction.
- [x] Un bateau coule après avoir été touché deux fois, il disparait alors définitivement de la carte
- [x] Le joueur qui n'a plus aucun bateau perd.
- [x] Le programme ne plante pas: des messages d'erreurs précis s'affichent en cas de saisie invalide pour expliquer le problème. L'utilisateur peut alors modifier sa saisie.
- [x] Le programme respecte les principes de l'orienté objet et est commenté.

Le diagramme des classes de notre projet est le suivant:

![Diagramme des classes](img/classdiagram.png?raw=true "Diagramme des classes")
