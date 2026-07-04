package model.enemy;

public class Cell {

    //Fields
    private Enemy enemy;
    private int hitCount;
    //----------------------------------------------------------------

    //Constructor
    public Cell(Enemy enemy, int hitCount) {

        this.enemy = enemy;
        this.hitCount = hitCount;
    }

    //----------------------------------------------------------------

    //Methods

    public Enemy getEnemy() {
        return enemy;
    }

    public int getHitCount() {
        return hitCount;
    }

    public void hit() {
        hitCount--;
    }

    public boolean isDestroyed() {
        return hitCount <= 0;
    }
}