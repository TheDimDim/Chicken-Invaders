# Chicken Invaders 


## Project Description

This project is a desktop game developed in Java using the Swing library.
The game is inspired by Chicken Invaders. 
The player controls a spaceship, shoots chickens, avoids eggs and enemy bullets, collects power-ups, and defeats bosses through multiple levels.


## The project includes:

- Main menu
- Login and register system
- Store system for selecting planes
- High score system
- Sound settings
- Multiple enemy types
- Boss levels
- Power-ups
- SQLite database connection using JDBC
- 8 game levels

  
## Technologies Used
- Java
- Java Swing
- SQLite
- JDBC
- IntelliJ IDEA
- DataGrip
- Git
- GitHub

## How to Compile and Run
To run this project, the following requirements are needed:

- Java JDK 8 or higher
- IntelliJ IDEA or another Java IDE
- SQLite JDBC Driver
- visuals folder containing all image and sound files
- chicken-invaders-db folder containing the SQLite database file

## Running the Project in IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure all Java files are inside the src folder.
3. Make sure the visuals folder is placed in the main project directory.
4. Make sure the chicken-invaders-db folder is placed in the main project directory.
5. Add the SQLite JDBC driver to the project libraries.
6. Make sure the database path in DatabaseManager.java is correct.
7. Run GameMain.java.

## Game Controls

Right Arrow / D:            Move right  
Left Arrow / A:             Move left  
Up Arrow / W:               Move up  
Down Arrow / S:             Move down  
Space:                      Shoot  
P:                          Pause / Resume  
Esc:                        Return to main menu


## Game Rules

The player starts with 3 lives.
The Heavy plane starts with 5 lives.
Lives do not reset between levels.
If the player loses all lives, the game is over.
The game has 8 levels.
Boss enemies appear in level 4 and level 8.
The game is completed after defeating the final boss in level 8.



## Levels

- Level 1: Normal chickens  
- Level 2: Normal and Fast chickens  
- Level 3: Normal and Zigzag chickens  
- Level 4: First boss fight  
- Level 5: Shooter and Fast chickens  
- Level 6: Zigzag and Shooter chickens  
- Level 7: Mixed enemy types  
- Level 8: Final boss fight


## Enemy Types

- Normal Chicken: Basic enemy, worth 10 points.
- Fast Chicken: Faster enemy, worth 15 points.
- Zigzag Chicken: Moves in a zigzag pattern, worth 20 points.
- Shooter Chicken: Shoots bullets downward and sometimes horizontally, worth 25 points.

## Bosses Types

- Boss Level 4: Appears in level 4 and is worth 500 points.
- Final Boss: Appears in level 8 and is worth 1000 points.

## Power-Ups

- Rapid Fire: Increases shooting speed for 8 seconds.
- Freeze: Freezes enemies, eggs, and enemy bullets for 3 seconds.
- Extra Life: Adds one life up to maximum 5 lives.
- Shield: Protects the player for 10 seconds.
- Add Fire: Permanently adds one extra bullet.

The game also shows the active temporary power-up in the HUD using a power indicator.
For example, it displays Rapid, Freeze, Shield, or None based on the current active power-up.


## Store System

The store allows the player to select different planes.
The selected plane is saved in the database and used when the game starts.

Available planes:

- Default: Normal speed, normal shooting, 3 lives.
- Fast: Faster movement.
- Heavy: More lives and starts with 5 lives.
- Sniper: Faster shooting and double damage to bosses.


## Sound Settings

The game includes sound settings for background music, shot sound, crash / explosion sound, and game over / win sound.

The player can enable or disable these sounds from the Settings panel.
The selected sound settings are saved in the database.


## Database Description

### Database Type:
This project uses SQLite as its database.
SQLite is a file-based database and is suitable for this desktop Java project.
The connection to the database is handled using JDBC in the DatabaseManager class.


### Database File Path

The SQLite database file is included inside the project in the chicken-invaders-db folder.

## Database file path: 

chicken-invaders-db/chicken-invaders-db.sqlite

## Database URL used in the code:
private static final String DB_URL = "jdbc:sqlite:chicken-invaders-db/chicken-invaders-db.sqlite";

If the project is moved to another computer, the chicken-invaders-db folder must remain in the main project directory.


### Database Tables

The project uses two main database tables:
-users
-game_records
SQLite may also create internal tables such as sqlite_master and sqlite_sequence. These tables are managed automatically by SQLite.

## users Table : 
- The users table stores user account information and user settings.

This table is used for registering users, logging in users, saving the selected plane, 
saving the user's high score, saving the last reached level, and saving sound settings.

### Main columns used in this table:

- username: Stores the username.
- password: Stores the password.
- selected_plane: Stores the selected plane chosen from the store.
- high_score: Stores the user's highest score.
- last_level: Stores the last reached level.
- background_music: Stores background music setting.
- shot_sound: Stores shot sound setting.
- crash_sound: Stores crash / explosion sound setting.
- game_over_sound: Stores game over / win sound setting.


### game_records Table :
- The game_records table stores records of finished games.

This table is used for saving each played game, saving score history, and showing high scores.

### Main columns used in this table:
- username: Stores the username of the player.
- score: Stores the final score.
- level: Stores the level reached by the player.
- background_music: Stores the background music setting during the game.
- shot_sound: Stores the shot sound setting during the game.
- crash_sound: Stores the crash / explosion sound setting during the game.
- game_over_sound: Stores the game over / win sound setting during the game.
- played_at: Stores the date and time of the game record.


## Database Usage in Classes

- DatabaseManager: Handles all database operations.
- LoginPanel: Checks username and password.
- RegisterPanel: Registers new users.
- StorePanel: Reads high score and saves selected plane.
- SettingsPanel: Saves sound settings.
- HighScorePanel: Shows high scores.
- GamePanel: Saves score and game records.


## Thank you for taking the time to review this project. Hope you enjoy it :)


