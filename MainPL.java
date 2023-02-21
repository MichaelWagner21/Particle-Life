import javax.swing.SwingUtilities;


public class MainPL {
    static WindowPL appFrame = new WindowPL();
    final static double time = 10;
    final static int DIAMETER = 8;
    final static int CIRCLEAMOUNT = 2;
    public static void main(String[] args){

        System.out.print("\033[H\033[2J");

        CirclesPL[] circles = new CirclesPL[CIRCLEAMOUNT];

        for (int c = 0; c<CIRCLEAMOUNT; c++){
            circles[c] = new CirclesPL();
            appFrame.add(circles[c]);
        }

        //CirclesPL testCircle = new CirclesPL(387,175,SpritesPL.yellowCircleSprite());
        CirclesPL stationaryCircle = new CirclesPL(387,375,true);
        //CirclesPL testCircle3 = new CirclesPL(100,0,SpritesPL.orangeCircleSprite());

        //appFrame.add(testCircle);
        appFrame.add(stationaryCircle);
        //appFrame.add(testCircle3);

        while (true){
            //testCircle.currentBody.refresh();
            //testCircle3.currentBody.refresh();
            refreshAllObjsIn(circles);
            wait((int)time);
        }
        


        

    }

    public static int xBound(){
        return 775;
    }
    public static int yBound(){
        return 750;
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