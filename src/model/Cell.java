package model.enemy;

public class Cell {

    private Enemy enemy;
    private int hitCount;

    public Cell(Enemy enemy, int hitCount) {

        this.enemy = enemy;
        this.hitCount = hitCount;
    }

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