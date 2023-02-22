// Force Range: 0-.02

public class PhysicsPL {
    int currentX;
    int currentY;

    double mass = 1;

    double forceGravity = 0;

    double forceGravityX = 0;
    double forceGravityY = 0;

    double sinTheta = 0;
    double cosTheta = 0;

    double gravitationalConstant = .2;

    double forceX = .02;
    double forceY = .02;

    double accelerationX = forceX/mass;
    double accelerationY = forceY/mass;

    double velocityX = accelerationX*(MainPL.TIME);
    double velocityY = accelerationY*(MainPL.TIME);

    final double TERMINALVELOCITY = 2;

    double displacementX = velocityX*(MainPL.TIME);
    double displacementY = velocityY*(MainPL.TIME);

    int newX;
    int newY;

    CirclesPL currentObj;

    public PhysicsPL(CirclesPL obj){
        currentObj = obj;
        currentX = currentObj.tempCurrentX;
        currentY = currentObj.tempCurrentY;
        //this.refresh();

    }

    public void refresh(){

        forceGravity = gravitationalConstant * ((mass*mass)/(Math.pow(twoDimDistance(currentX,currentY,387,375),1)));


        


        sinTheta = oneDimDistance(currentY, 375)/twoDimDistance(currentX,currentY,387,375);
        cosTheta = oneDimDistance(currentX, 387)/twoDimDistance(currentX,currentY,387,375);
       
       
        forceGravityX = cosTheta*forceGravity;
        forceGravityY = sinTheta*forceGravity;

        
        forceX+=forceGravityX;
        forceY+=forceGravityY;

        

 
        //Below If Statements: If outside edge and headed outside of bounds, bounce off edge.
        if (currentX-MainPL.XBOUND>=0 && forceX>=0){
            forceX = -1*Math.abs(forceX);
        }

        if (currentX<=0 && forceX<=0){
            forceX = Math.abs(forceX);
        }

        if (currentY-MainPL.YBOUND>=-10 && forceY>=0){
            forceY = -1*Math.abs(forceY);
        }

        if (currentY<=0 && forceY<=0){
            forceY = Math.abs(forceY);
        }


        accelerationX = forceX/mass;
        accelerationY = forceY/mass;

        velocityX = accelerationX*MainPL.TIME;
        velocityY = accelerationY*MainPL.TIME;

        if (Math.abs(velocityX) >= TERMINALVELOCITY){
            velocityX = (velocityX/Math.abs(velocityX))*(TERMINALVELOCITY-1);
            forceX = (velocityX*mass)/MainPL.TIME;
        }

        if (Math.abs(velocityY) >= TERMINALVELOCITY){
            velocityY = (velocityY/Math.abs(velocityY))*(TERMINALVELOCITY-1);
            forceY = (velocityY*mass)/MainPL.TIME;
        }



        displacementX = velocityX*MainPL.TIME;
        displacementY = velocityY*MainPL.TIME;




        //Below: Collision with other circle
        if (twoDimDistance(currentX, currentY, 387, 375)<=MainPL.DIAMETER){
            if (displacementX>0 && oneDimDistance(currentX,387)>0){
                displacementX = 0;
            }

            if (displacementY>0 && oneDimDistance(currentY,375)>0){
                displacementY = 0;
            }

            if (displacementX<0 && oneDimDistance(currentX,387)<0){
                displacementX = 0;
            }

            if (displacementY<0 && oneDimDistance(currentY,375)<0){
                displacementY = 0;
            }
        }


        
        newX=currentX+(int)Math.round(displacementX);
        newY=currentY+(int)Math.round(displacementY);

        currentObj.setCoords(newX,newY);
        
        currentX = newX;
        currentY = newY;

        

        
        MainPL.refresh(MainPL.appFrame);



    }

    public static double twoDimDistance(double x1, double y1, double x2, double y2){
        double verDis = y2-y1;
        double horDis = x2-x1;
        double diagDis = Math.sqrt(verDis*verDis+horDis*horDis);
        return diagDis;
    }

    public static double oneDimDistance(double x1, double x2){
        return (x2-x1);
    }
}