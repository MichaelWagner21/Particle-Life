import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Color;


public class CirclesPL extends JLabel{

    int tempCurrentX;
    int tempCurrentY;

    PhysicsPL currentBody;
    
    public CirclesPL(int x, int y, ImageIcon spriteColor){
        this.setIcon(spriteColor);
        this.setBackground(new Color (0,0,0));
        this.setOpaque(true);
        this.setCoords(x, y);
        currentBody = new PhysicsPL(this);

    }

    
    
    public CirclesPL(){
        int x = MainPL.randNum(0,MainPL.xBound());
        int y = MainPL.randNum(0,MainPL.yBound());
        this.setIcon(SpritesPL.randomSprite());
        this.setBackground(new Color (0,0,0));
        this.setOpaque(true);
        this.setCoords(x,y);
        currentBody = new PhysicsPL(this);

    }

    public CirclesPL(int x, int y, boolean isStationary){
        this.setIcon(SpritesPL.randomSprite());
        this.setBackground(new Color (0,0,0));
        this.setOpaque(true);
        this.setCoords(x, y);

    }

    public void setCoords(int x, int y){
        setBounds(x,750-y,8,8);
        tempCurrentX = x;
        tempCurrentY = y;
    }

    

}