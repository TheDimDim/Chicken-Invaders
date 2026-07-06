CREATE TABLE IF NOT EXISTS users (
    username TEXT PRIMARY KEY,
    password TEXT NOT NULL,
    high_score INTEGER DEFAULT 0,
    last_level INTEGER DEFAULT 1
);
SELECT * FROM users;

ALTER TABLE users ADD COLUMN background_music INTEGER DEFAULT 1;
ALTER TABLE users ADD COLUMN shot_sound INTEGER DEFAULT 1;
ALTER TABLE users ADD COLUMN crash_sound INTEGER DEFAULT 1;
ALTER TABLE users ADD COLUMN game_over_sound INTEGER DEFAULT 1;

CREATE TABLE IF NOT EXISTS game_records (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    username TEXT NOT NULL,
    score INTEGER NOT NULL,
    level INTEGER NOT NULL,
    background_music INTEGER NOT NULL,
    shot_sound INTEGER NOT NULL,
    crash_sound INTEGER NOT NULL,
    game_over_sound INTEGER NOT NULL,
    played_at TEXT DEFAULT CURRENT_TIMESTAMP
);

SELECT * FROM game_records;

ALTER TABLE users ADD COLUMN selected_plane TEXT DEFAULT 'Default';