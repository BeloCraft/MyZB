/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.belocraft.gameobjects;

/**
 *
 * @author Eugene
 */
public class Grass extends Scrollable {
    
    // Когда констуктор Grass вызван – вызовите конструтор родителя (Scrollable)
    public Grass(float x, float y, int width, int height, float scrollSpeed) {
        super(x, y, width, height, scrollSpeed);

    }

    void stop() {
        velocity.x = 0;
    }

    void onRestart(float x, int scrollSpeed) {
        position.x = x;
        velocity.x = scrollSpeed;
    }
}
