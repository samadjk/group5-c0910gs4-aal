/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package entity;

/**
 *
 * @author Pe Yeu
 */
public class Benefit {
    private int id;
    private float minCost;
    private float maxCost;
    private float perCent;

    public float getMaxCost() {
        return maxCost;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public void setMaxCost(float maxCost) {
        this.maxCost = maxCost;
    }

    public float getMinCost() {
        return minCost;
    }

    public void setMinCost(float minCost) {
        this.minCost = minCost;
    }

    public float getPerCent() {
        return perCent;
    }

    public void setPerCent(float perCent) {
        this.perCent = perCent;
    }

    public Benefit() {
    }

    public Benefit(int id, float minCost, float maxCost, float perCent) {
        this.id = id;
        this.minCost = minCost;
        this.maxCost = maxCost;
        this.perCent = perCent;
    }

  
}
