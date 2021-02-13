package com.example.designpatterns.model.state;

public class Swordman implements State {

    int agility = 7;
    int attack = 13;
    int defense = 5;

    @Override
    public void increaseDefense(int increment) {
        defense += increment;
        System.out.println("Defense Increased to: " + defense);
    }

    @Override
    public void speedUp(int increment) {
        attack = attack + 2 * increment;
        agility = agility + increment;
        System.out.println("Attack Increased to: " + attack);
        System.out.println("Agility Increased to: " + agility);
    }

    @Override
    public void increaseAttack(int increment) {
        attack += increment;
        defense = (int) (defense - 0.3 * increment);
        System.out.println("Attack Increased to: " + attack);
        System.out.println("Defense Increased to " + defense);
    }

    @Override
    public void printStates() {
        System.out.println("Agility-Attack-Defense: " + agility + "-" + attack + "-" + defense + "\n");
    }
}
