import javax.swing.SwingUtilities;


public class MainPL {
    static WindowPL appFrame = new WindowPL();
    
    
    final static int XBOUND = 775;
    final static int YBOUND = 750;
    
    
    final static double TIME = 10;
    final static int DIAMETER = 8;
    private final static int CIRCLEAMOUNT = 30;
    
    
    
    public static void main(String[] args){

        System.out.print("\033[H\033[2J");

        CirclesPL[] circles = new CirclesPL[CIRCLEAMOUNT];

        for (int c = 0; c<CIRCLEAMOUNT; c++){
            circles[c] = new CirclesPL();
            appFrame.add(circles[c]);
        }

        CirclesPL stationaryCircle = new CirclesPL(387,375,true);

        appFrame.add(stationaryCircle);

        while (true){
            refreshAllObjsIn(circles);
            wait((int)TIME);
        }
        


        

    }

    public static void wait(int t){
        try {
            Thread.sleep(t);
          } 
        catch (InterruptedException ex) {
    
            Thread.currentThread().interrupt();
          }
    }

    public static void refresh(WindowPL w){
        SwingUtilities.updateComponentTreeUI(w);
    }

    public static int randNum(double min, double max){
        return (int)Math.floor(Math.random() * (max - min + 1) + min);
    }

    public static void refreshAllObjsIn(CirclesPL[] circlesList){
        for (int i = 0; i<circlesList.length; i++){
            circlesList[i].currentBody.refresh();
        }
    }
}