package managers;

import java.sql.*;

public class DatabaseManager {

    //Fields
    private static String currentUsername;
    private static final String DB_URL = "jdbc:sqlite:C:/Users/Asus/DataGripProjects/chicken-invaders-db/chicken-invaders-db.sqlite";

    //----------------------------------------------------------------

    //Methods
    public static boolean registerUser(String username, String password) {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(DB_URL);

            String sql = "INSERT INTO users(username, password) VALUES('" + username + "', '" + password + "')";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

            return true;

        } catch (Exception e) {

            System.out.println("Register error: " + e.getMessage());
            return false;
        }
    }

    public static boolean loginUser(String username, String password) {

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(DB_URL);

            String sql = "SELECT * FROM users WHERE username = '" + username + "' AND password = '" + password + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            boolean found = resultSet.next();

            if (found) {

                currentUsername = username;
            }

            resultSet.close();
            statement.close();
            connection.close();

            return found;

        } catch (Exception e) {

            System.out.println("Login error: " + e.getMessage());
            return false;
        }
    }

    public static String getCurrentUsername() {

        return currentUsername;
    }
    public static void saveScore(int score, int level) {

        if (currentUsername == null) {

            return;
        }

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(DB_URL);

            String sql = "UPDATE users " +
                    "SET high_score = CASE WHEN high_score < " + score + " THEN " + score + " ELSE high_score END, " +
                    "last_level = " + level + " " +
                    "WHERE username = '" + currentUsername + "'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Save score error: " + e.getMessage());
        }
    }
    public static void saveSoundSettings(int backgroundMusic, int shotSound, int crashSound, int gameOverSound) {

        if (currentUsername == null) {

            return;
        }

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(DB_URL);

            String sql = "UPDATE users SET " +
                    "background_music = " + backgroundMusic + ", " +
                    "shot_sound = " + shotSound + ", " +
                    "crash_sound = " + crashSound + ", " +
                    "game_over_sound = " + gameOverSound +
                    " WHERE username = '" + currentUsername + "'";

            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);

            statement.close();
            connection.close();

        } catch (Exception e) {

            System.out.println("Save sound settings error: " + e.getMessage());
        }
    }

    public static int getBackgroundMusic() {

        return getSoundSetting("background_music");
    }

    public static int getShotSound() {

        return getSoundSetting("shot_sound");
    }

    public static int getCrashSound() {

        return getSoundSetting("crash_sound");
    }

    public static int getGameOverSound() {

        return getSoundSetting("game_over_sound");
    }

    private static int getSoundSetting(String columnName) {

        if (currentUsername == null) {

            return 1;
        }

        try {

            Class.forName("org.sqlite.JDBC");

            Connection connection = DriverManager.getConnection(DB_URL);

            String sql = "SELECT " + columnName + " FROM users WHERE username = '" + currentUsername + "'";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            int value = 1;

            if (resultSet.next()) {

                value = resultSet.getInt(columnName);
            }

            resultSet.close();
            statement.close();
            connection.close();

            return value;

        } catch (Exception e) {

            System.out.println("Get sound setting error: " + e.getMessage());
            return 1;
        }
    }



}