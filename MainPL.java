import javax.swing.SwingUtilities;


public class MainPL {
    static WindowPL appFrame = new WindowPL();
    static double time = 10;
    public static void main(String[] args){

        System.out.print("\033[H\033[2J");

        CirclesPL testCircle = new CirclesPL(387,175,SpritesPL.yellowCircleSprite());
        CirclesPL testCircle2 = new CirclesPL(387,375,true);
        CirclesPL testCircle3 = new CirclesPL(100,475,SpritesPL.orangeCircleSprite());

        appFrame.add(testCircle);
        appFrame.add(testCircle2);
        appFrame.add(testCircle3);

        while (true){
            testCircle.currentBody.refresh();
            testCircle3.currentBody.refresh();
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
}