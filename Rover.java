import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Rover extends Actor
{
    private Display anzeige;

    public void act() 
    {

    } 

   /**
     * Dieser Algorithmus funktioniert nur mit der exakten Abbildung aus dem Buch! (siehe S.50)
     */
    
    public void aufgabe3() { // Seite 50, Aufgabe 3b,c; a im Heft
        int x=0;
        
        while(!markeVorhanden()) { // Teilaufgabe B
                fahre();
            if(gesteinVorhanden()) {
                if((huegelVorhanden("links")) || (huegelVorhanden("rechts"))) {                
                    analysiereGestein();
                }    
            }
        x++;    
        }
        
        if(markeVorhanden()) { // Teilaufgabe C
            setRotation(getRotation()+180);
            for(int y=x;y>0;y--) {
                fahre();
            }
            setRotation(getRotation()+180);
        }                   
    }
    
   /**
     * Fahre eine Spirale
     */   
    
     public void Spirale() {
        for(int y=0;y<40;y++) {
            for(int x=0;x<y;x++) {
                setzeMarke();
                fahre();
                nachricht("IMPEACH TRUMP!");
            }
        drehe("rechts");
        }
     }
     
    /**
     * Der Rover bewegt sich ein Feld in Fahrtrichtung weiter.
     * Sollte sich in Fahrtrichtung ein Objekt der Klasse Huegel befinden oder er sich an der Grenze der Welt befinden,
     * dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void fahre()
    {
        int posX = getX();
        int posY = getY();

        if(huegelVorhanden("vorne"))
        {
            nachricht("Zu steil!");
        }
        else if(getRotation()==270 && getY()==1)
        {
            nachricht("Ich kann mich nicht bewegen");
        }
        else
        {
            move(1);
            Greenfoot.delay(1);
        }

        if(posX==getX()&&posY==getY()&&!huegelVorhanden("vorne"))
        {
            nachricht("Ich kann mich nicht bewegen");
        }
    }

    /**
     * Der Rover dreht sich um 90 Grad in die Richtung, die mit richtung (â€žlinksâ€œ oder â€žrechtsâ€œ) Ã¼bergeben wurde.
     * Sollte ein anderer Text (String) als "rechts" oder "links" Ã¼bergeben werden, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void drehe(String richtung)
    {
        if(richtung=="rechts")
        {
            setRotation(getRotation()+90);
        }
        else if (richtung=="links")
        {
            setRotation(getRotation()-90);
        }
        else
        {
            nachricht("Befehl nicht korrekt!");
        }
    }

    /** 
     * Fahre endlos lange im Quadrat, bis das Programm geschlossen wird.
     */
    public void quadratTour() {
        int x; 
        x=1;
        while (x <= 100) {
        
        move(4);
        Greenfoot.delay(1);
        drehe("rechts");
        move(4);
        Greenfoot.delay(1);
        drehe("rechts");
        move(4);
        Greenfoot.delay(1);
        drehe("rechts");
        move(4);
        Greenfoot.delay(1);
        drehe("rechts");
    }
}
    /**
     * Umfahre einen Hügel, wenn vorhanden.
     */
    public void umfahreHügel() {   
    
    if(huegelVorhanden("vorne"))
    { 
        drehe("rechts");
        Greenfoot.delay(1);
        move(1);
        Greenfoot.delay(1);
        drehe("links");
        Greenfoot.delay(1);
        move(2);
        Greenfoot.delay(1);
        drehe("links");
        Greenfoot.delay(1);
        move(1);
        drehe("rechts");
    }
    else
        nachricht("Hier ist kein Hügel!");
    }

    /**
     * Dieses Programm umfährt automatisch jedes bliebige Ding.
     */
    public void unkreiseObjekt() {
        int z;
        z=5;
        while(z <= 100) {
  
        while(huegelVorhanden("rechts"))
       
        move(1);
        Greenfoot.delay(1);
        drehe("rechts");
        move(1);
        }
    }
    
    /**
     * Fahre vorwärts, wenn ein Hügel vorhanden ist.
     */
    public void quadratHügelTour() {

        int loop;
        loop=1;
        while(loop <= 100) {
        
        // Fahrtrichtung Links
        if (huegelVorhanden("links")) 
            do {
                fahre();
                nachricht("IMPEACH TRUMP!");
            } while(huegelVorhanden("links"));
        else {
            drehe("rechts"); // Hat Greenfoot eine Rechts/Links Schwäche?
            fahre();
        }
        
        // Fahrtrichtung Rechts
        if (huegelVorhanden("rechts"))
            do {
                fahre();
                nachricht("IMPEACH TRUMP AND MERKEL!");
            } while(huegelVorhanden("rechts"));
        else {
            drehe("links"); // Hat Greenfoot eine Rechts/Links Schwäche?
            fahre();            
        }
    } 
}
    
    /**
     * Der Rover gibt durch einen Wahrheitswert (true oder false )zurÃ¼ck, ob sich auf seiner Position ein Objekt der Klasse Gestein befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean gesteinVorhanden()
    {
        if(getOneIntersectingObject(Gestein.class)!=null)
        {
            nachricht("Gestein gefunden!");
            return true;

        }

        return false;
    }

    /**
     * Der Rover Ã¼berprÃ¼ft, ob sich in richtung ("rechts", "links", oder "vorne") ein Objekt der Klasse Huegel befindet.
     * Das Ergebnis wird auf dem Display angezeigt.
     * Sollte ein anderer Text (String) als "rechts", "links" oder "vorne" Ã¼bergeben werden, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public boolean huegelVorhanden(String richtung)
    {
        int rot = getRotation();

        if (richtung=="vorne" && rot==0 || richtung=="rechts" && rot==270 || richtung=="links" && rot==90)
        {
            if(getOneObjectAtOffset(1,0,Huegel.class)!=null && ((Huegel)getOneObjectAtOffset(1,0,Huegel.class)).getSteigung() >30)
            {
                return true;
            }
        }

        if (richtung=="vorne" && rot==180 || richtung=="rechts" && rot==90 || richtung=="links" && rot==270)
        {
            if(getOneObjectAtOffset(-1,0,Huegel.class)!=null && ((Huegel)getOneObjectAtOffset(-1,0,Huegel.class)).getSteigung() >30)
            {
                return true;
            }
        }

        if (richtung=="vorne" && rot==90 || richtung=="rechts" && rot==0 || richtung=="links" && rot==180)
        {
            if(getOneObjectAtOffset(0,1,Huegel.class)!=null && ((Huegel)getOneObjectAtOffset(0,1,Huegel.class)).getSteigung() >30)
            {
                return true;
            }

        }

        if (richtung=="vorne" && rot==270 || richtung=="rechts" && rot==180 || richtung=="links" && rot==0)
        {
            if(getOneObjectAtOffset(0,-1,Huegel.class)!=null && ((Huegel)getOneObjectAtOffset(0,-1,Huegel.class)).getSteigung() >30)
            {
                return true;
            }

        }

        if(richtung!="vorne" && richtung!="links" && richtung!="rechts")
        {
            nachricht("Befehl nicht korrekt!");
        }

        return false;
    }

    /**
     * Der Rover ermittelt den Wassergehalt des Gesteins auf seiner Position und gibt diesen auf dem Display aus.
     * Sollte kein Objekt der Klasse Gestein vorhanden sein, dann erscheint eine entsprechende Meldung auf dem Display.
     */
    public void analysiereGestein()
    {
        if(gesteinVorhanden())
        {
            nachricht("Gestein untersucht! Wassergehalt ist " + ((Gestein)getOneIntersectingObject(Gestein.class)).getWassergehalt()+"%.");
            Greenfoot.delay(1);
            removeTouching(Gestein.class);
        }
        else 
        {
            nachricht("Hier ist kein Gestein");
        }
    }

    /**
     * Der Rover erzeugt ein Objekt der Klasse â€žMarkierungâ€œ auf seiner Position.
     */
    public void setzeMarke()
    {
        getWorld().addObject(new Marke(), getX(), getY());
    }

    /**
     * *Der Rover gibt durch einen Wahrheitswert (true oder false )zurÃ¼ck, ob sich auf seiner Position ein Objekt der Marke befindet.
     * Eine entsprechende Meldung erscheint auch auf dem Display.
     */
    public boolean markeVorhanden()
    {
        if(getOneIntersectingObject(Marke.class)!=null)
        {
            return true;
        }

        return false;
    }

    public void entferneMarke()
    {
        if(markeVorhanden())
        {
            removeTouching(Marke.class);
        }
    }

    private void nachricht(String pText)
    {
        if(anzeige!=null)
        {
            anzeige.anzeigen(pText);
            Greenfoot.delay(1);
            anzeige.loeschen();
        }
    }

    private void displayAusschalten()
    {
        getWorld().removeObject(anzeige);

    }

    protected void addedToWorld(World world)
    {

        setImage("images/rover.png");
        world = getWorld();
        anzeige = new Display();
        anzeige.setImage("images/nachricht.png");
        world.addObject(anzeige, 7, 0);
        if(getY()==0)
        {
            setLocation(getX(),1);
        }
        anzeige.anzeigen("Ich bin bereit");

    }

    class Display extends Actor
    {
        GreenfootImage bild; 

        public Display()
        {
          bild = getImage();
        }

        public void act() 
        {

        }  

        public void anzeigen(String pText)
        {
           loeschen();
           getImage().drawImage(new GreenfootImage(pText, 25, Color.BLACK, new Color(0, 0, 0, 0)),10,10);

        }

        public void loeschen()
        {
            getImage().clear();
            setImage("images/nachricht.png");
        }

    }
}
