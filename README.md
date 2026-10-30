# Profil développeur Compose

## Description

Cette application Android affiche une fiche « Profil développeur » réalisée avec Jetpack Compose.
La fiche présente un nom, un rôle, une courte description, une liste de compétences,
une information de disponibilité (zone cliquable), un contact et un bouton d'action.
L'écran est découpé en plusieurs composables réutilisables qui reçoivent leurs données en paramètres.
Deux previews montrent deux profils différents : un développeur Android junior disponible
et une développeuse full-stack non disponible.

## Notions utilisées

- Composables (`@Composable`)
- Paramètres
- `Modifier` et chaînage de modifiers
- Padding externe / padding interne
- Callbacks (`onClick`)
- Previews (`@Preview`)
- Accessibilité de base (`semantics`, `Role.Button`, `contentDescription`)

## Explication courte

### 1. Quels composables avez-vous créés ?

- `EcranProfil` : l'écran principal, il fournit les données du profil et place la fiche dans l'écran.
- `FicheProfil` : la fiche complète, elle assemble tous les éléments dans une `Surface` et une `Column`.
- `EnteteProfil` : affiche le nom et le rôle.
- `CompetenceItem` : affiche une seule compétence (appelé une fois par compétence de la liste).
- `InfoSecondaire` : affiche une petite information, ici le contact.
- `ZoneDisponibilite` : zone cliquable personnalisée qui affiche la disponibilité.
- `BoutonAction` : bouton réutilisable dont le texte est un paramètre.

### 2. Quel composable représente l'écran principal ?

`EcranProfil` est l'écran principal : c'est lui qui est appelé dans `setContent`, à l'intérieur du `Scaffold`
de `MainActivity`.

### 3. Où utilisez-vous un padding externe ?

Le padding externe est appliqué par le parent pour placer un composant :
- dans `EcranProfil`, `modifier.padding(16.dp)` est passé à `FicheProfil` pour décaler la fiche par rapport
  aux bords de l'écran ;
- dans `MainActivity`, `innerPadding` du `Scaffold` est passé à `EcranProfil` ;
- dans `FicheProfil`, des `Modifier.padding(top = ...)` sont passés à `ZoneDisponibilite`,
  `InfoSecondaire` et `BoutonAction` pour les espacer les uns des autres.

### 4. Où utilisez-vous un padding interne ?

Le padding interne est appliqué dans un composant pour espacer son contenu :
- dans `FicheProfil`, `Column(modifier = Modifier.padding(16.dp))` espace le contenu par rapport
  au bord de la `Surface` ;
- dans `ZoneDisponibilite`, `Modifier.padding(12.dp)` sur le `Text` espace le texte du bord de la zone cliquable.

### 5. Quel composant reçoit un callback ?

`FicheProfil` reçoit deux callbacks, `onContacterClick` et `onDisponibiliteClick`, qu'elle transmet
à `BoutonAction` et `ZoneDisponibilite` (paramètre `onClick: () -> Unit`). Au clic, ces composants
se contentent de signaler l'événement : les callbacks sont vides, aucune logique applicative
n'est décidée dans les composants.

### 6. Quelle information d'accessibilité avez-vous ajoutée ?

Sur la zone cliquable personnalisée `ZoneDisponibilite`, j'ai ajouté dans `semantics` un
`role = Role.Button` et un `contentDescription` clair (« … Appuyer pour voir les détails »).
Un lecteur d'écran comprend ainsi que la zone est un bouton et ce qu'elle fait.
Pour `BoutonAction`, le composant `Button` est déjà plus explicite qu'une zone cliquable
personnalisée : il porte nativement le rôle de bouton et son texte est lu par le lecteur d'écran.

### 7. Pourquoi vos composants peuvent-ils être considérés comme réutilisables ?

Ils ne sont pas figés : ils reçoivent leurs données en paramètres (nom, rôle, compétence, texte du bouton,
callbacks) et acceptent un paramètre `modifier: Modifier = Modifier` qui est utilisé dans le composable,
ce qui laisse le parent décider du placement externe. Les deux previews le montrent :
la même `FicheProfil` affiche deux profils différents (textes, compétences, disponibilité et bouton différents).

## Capture d'écran

![Preview du profil](screenshots/preview.png)

## Lien du dépôt

https://github.com/Maxime-SALINS/DevProfil
