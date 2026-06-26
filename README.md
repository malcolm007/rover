# 🚗 Rover - Mars Rover Simulator

Un simulateur de rover Mars développé en Java qui permet de contrôler des rovers sur un plateau donné. Le projet utilise une architecture modulaire avec des commandes, des services et une gestion complète d'état.

## 📋 Contexte du Projet

Ce projet simule le déplacement et la rotation de rovers sur un plateau de Mars. Chaque rover reçoit un ensemble d'instructions (Gauche, Droite, Avancer) et doit les exécuter correctement en respectant les limites du plateau.

Le code est conçu pour être :
- **Testable** : Architecture modulaire avec dépendances injectables
- **Maintenable** : Séparation claire des responsabilités
- **Extensible** : Facile d'ajouter de nouvelles commandes

## 🎯 Fonctionnalités Clés

### 1. **Gestion des Rovers**
- Chaque rover a une position (x, y) et une direction (N, E, S, W)
- Support des quatre directions cardinales : Nord, Est, Sud, Ouest
- Validation automatique des déplacements par rapport aux limites du plateau

### 2. **Système de Commandes**
- **L** : Tourner à gauche (90°)
- **R** : Tourner à droite (90°)
- **M** : Avancer dans la direction actuelle

Système basé sur le pattern Command Registry pour une gestion flexible des commandes.

### 3. **Validation**
- Vérification des limites du plateau
- Validation du format d'entrée
- Détection des commandes invalides

### 4. **Analyse de Fichiers**
- Parsing automatique des fichiers d'entrée
- Support de multiples rovers par mission
- Gestion des erreurs avec messages explicites

## 🚀 Démarrage Rapide

### Prérequis

- **Java** : Version 21 ou supérieure
- **Maven** : Version 3.6+

### Installation

1. Cloner le dépôt :
```bash
git clone https://github.com/votre-nom/rover.git
cd rover
```

2. Compiler le projet :
```bash
mvn clean compile
```

3. Construire le JAR exécutable :
```bash
mvn clean package
```

### Exécution

Exécuter le programme avec un fichier d'entrée :

```bash
java -jar target/rover-1.0-SNAPSHOT.jar input.txt
```

Ou directement avec Maven :

```bash
mvn exec:java@run -Dexec.args="input.txt"
```

## 📝 Format du Fichier d'Entrée

Le fichier d'entrée suit ce format :

```
<MaxX> <MaxY>
<X> <Y> <Direction>
<Instructions>
<X> <Y> <Direction>
<Instructions>
...
```

### Détails :

- **Première ligne** : Les coordonnées maximales du plateau (ex: `5 5`)
- **Lignes paires** : Position initiale du rover - X Y Direction (ex: `1 2 N`)
  - X, Y : Coordonnées (0 à MaxX, 0 à MaxY)
  - Direction : N (Nord), E (Est), S (Sud), ou W (Ouest)
- **Lignes impaires** : Série de commandes - L, R, ou M (ex: `LMLMLMLMM`)

## 📖 Exemple Complet

### Fichier d'entrée (`input.txt`)

```
5 5
1 2 N
LMLMLMLMM
3 3 E
MMRMMRMRRM
```

### Explication

**Plateau** : 5×5 (coins à (0,0) et (5,5))

**Premier Rover** :
- Position initiale : (1, 2) orienté Nord
- Instructions : `LMLMLMLMM`
  - L : Tourner à gauche → facing West
  - M : Avancer → (0, 2)
  - L : Tourner à gauche → facing South
  - M : Avancer → (0, 1)
  - L : Tourner à gauche → facing East
  - M : Avancer → (1, 1)
  - L : Tourner à gauche → facing North
  - M : Avancer → (1, 2)
  - M : Avancer → (1, 3)
- Position finale : `1 3 N`

**Deuxième Rover** :
- Position initiale : (3, 3) orienté Est
- Instructions : `MMRMMRMRRM`
- Position finale : `5 1 E`

### Sortie du programme

```
Rover position: 1 3 N
Rover position: 5 1 E
```

## 🏗️ Architecture du Projet

### Structure des packages

```
com.rover/
├── command/          # Système de commandes
│   ├── Command       # Interface
│   ├── LeftCommand   # Rotation à gauche
│   ├── RightCommand  # Rotation à droite
│   ├── MoveCommand   # Déplacement
│   └── CommandRegistry # Registre des commandes
├── models/          # Modèles de données
│   ├── Direction    # Enum des directions
│   ├── Position     # Record de position
│   ├── Plateau      # Définition du plateau
│   ├── Rover        # État du rover
│   ├── RoverInstruction # Instructions pour un rover
│   └── RoverMission # Mission complète
├── parser/          # Parsing des fichiers
│   └── FileParser   # Lecteur d'entrée
├── services/        # Logique métier
│   └── RoverMissionService # Exécution des missions
└── Main            # Point d'entrée
```

## 🧪 Tests

### Exécuter tous les tests

```bash
mvn test
```

### Exécuter un test spécifique

```bash
mvn test -Dtest=MoveCommandTest
mvn test -Dtest=CommandRegistryTest
```


## ❌ Gestion des Erreurs

Le projet gère les erreurs suivantes :

| Erreur | Cause | Action |
|--------|-------|--------|
| `Out of plateau` | Déplacement hors limites | Exception levée |
| `Unknown command` | Caractère invalide dans les instructions | Exception levée |
| `Invalid rover configuration` | Format du fichier incorrect | Exception levée |
| `Empty input file` | Fichier vide | Exception levée |

---