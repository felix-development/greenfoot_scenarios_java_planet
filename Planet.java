import greenfoot.*; // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Die einzigen aktiven Akteure in der Roboterwelt sind die Roboter.
 * Die Welt besteht aus 14 * 10 Feldern.
 */

public class Planet extends World
{
    private static int zellenGroesse = 50;

    /**
     * Erschaffe eine Welt mit 15 * 12 Zellen.
     */
    public Planet()
    {
        super(20, 20, zellenGroesse);
        setBackground("images/boden.png");
        setPaintOrder(String.class, Rover.class, Marke.class, Gestein.class, Huegel.class);
        Greenfoot.setSpeed(20); 
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
        Huegel huegel = new Huegel();
        addObject(huegel,9,5);
        Huegel huegel2 = new Huegel();
        addObject(huegel2,10,8);
        Huegel huegel3 = new Huegel();
        addObject(huegel3,7,8);
        Huegel huegel4 = new Huegel();
        addObject(huegel4,6,11);
        Huegel huegel5 = new Huegel();
        addObject(huegel5,8,12);
    }
}