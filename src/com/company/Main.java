package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {270, 280, 240,250,400,250,150,200};
    public static int[] heroesDamage = {20, 15, 25,0,0,15,25,15};
    public static String[] heroesAttackType = {"Physical",
            "Magical", "Kinetic","Medic","Golem","Lucky","Berserk","Thor"};
    public static int round_number = 0;

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(
                heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss chose " + bossDefence);
    }

    public static void round() {
        round_number++;
        chooseBossDefence();
        if (bossHealth > 0) { // на всякий случай
            bossHits();
            medic();
            golem();
            lucky();
            berserk();
            thor();
        }
        heroesHit();
        printStatistics();
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
       /* if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0
                && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefence) {
                    Random random = new Random();
                    int coeff = random.nextInt(11); //0,1,2,3,4,5,6,7,8,9
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }
    public static void medic () {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] == heroesHealth[3]) {
                continue;
            }
            if (heroesHealth[i] < 100 && heroesHealth[3] > 0)  {
                heroesHealth[i] += 200;
                break;
            }
        }
    }
    public static void golem() {
        heroesHealth[17] = 17 - 72;
        System.out.println("Golem использовал супер способность");
*/
        int partDamage = bossDamage / 5; //1 / 5 урона от босса
        int aliveHeroes = 0;

        if (heroesHealth[4] > 0) {
            for (int i = 0; i < heroesHealth.length; i++) {
                if (i == 4) {
                    continue;
                } else if (heroesHealth[i] > 0) {
                    aliveHeroes++; //2
                    heroesHealth[i] = heroesHealth[i] + partDamage;
                }
            }
            heroesHealth[4] = heroesHealth[4] - (partDamage * aliveHeroes);
            System.out.println("Golem take " + (partDamage * aliveHeroes));
        }
    }

    public static void lucky() {
        Random random = new Random();
        boolean slope = random.nextBoolean();
        if (heroesHealth[5] > 0) {
            if (slope ) {
                heroesHealth[5] += bossDamage - 10;

                System.out.println("lucky is sloped ");

            }
        }
    }
    public static void berserk(){
        Random random = new Random();
        int blocked = random.nextInt(80 - 10) + 10;
        if (heroesHealth[6] > 0) {
            heroesHealth[6] += blocked;
            bossHealth -= blocked;
            System.out.println(heroesAttackType[6] + " заблокировал " + blocked + "xp и нанес урон Босу " + blocked + "xp");
        }
    }

    public static void thor (){
        Random random = new Random();
        int thor = random.nextInt(2);
        if (heroesHealth[6] > 0){
            for (int i = 0; i < bossDamage; i++) {
                if (thor == 1 ){
                    System.out.println("Thor is hit");
                    bossDamage = 0;
                    break;
                }else  if (thor == 2){
                    System.out.println("Thor don't hit");
                    break;
                }
            }
        }
    }


    public static void printStatistics () {
        System.out.println(round_number + " ROUND ______________");
        System.out.println("Boss health: " + bossHealth
                + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i]
                    + " health: " + heroesHealth[i]
                    + " (" + heroesDamage[i] + ")");
        }

        System.out.println("____________________");
    }
}
