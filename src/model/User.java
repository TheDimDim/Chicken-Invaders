package model;

public class User {

    //Fields
    private String username;
    private String password;
    private int highScore;
    private int lastLevel;

    //----------------------------------------------------------------

    //Constructor
    public User(String username, String password) {

        this.username = username;
        this.password = password;
        this.highScore = 0;
        this.lastLevel = 1;
    }

    //----------------------------------------------------------------

    //Methods
    public String getUsername() {

        return username;
    }

    public String getPassword() {

        return password;
    }

    public int getHighScore() {

        return highScore;
    }

    public void setHighScore(int highScore) {

        this.highScore = highScore;
    }

    public int getLastLevel() {

        return lastLevel;
    }

    public void setLastLevel(int lastLevel) {

        this.lastLevel = lastLevel;
    }

    public boolean checkPassword(String password) {

        return this.password.equals(password);
    }
}