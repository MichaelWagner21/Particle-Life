// Force Range: 0-.1

public class PhysicsPL {
    int currentX;
    int currentY;

    double mass = 1;

    double forceGravity = 0;

    double forceGravityX = 0;
    double forceGravityY = 0;

    double sinTheta = 0;
    double cosTheta = 0;

    double gravitationalConstant = .1;

    double forceX = MainPL.randNumDouble(0,0.1);
    double forceY = MainPL.randNumDouble(0,0.1);

    double accelerationX = forceX/mass;
    double accelerationY = forceY/mass;

    double velocityX = accelerationX*(MainPL.TIME);
    double velocityY = accelerationY*(MainPL.TIME);

    final double TERMINALVELOCITY = 1;

    double displacementX = velocityX*(MainPL.TIME);
    double displacementY = velocityY*(MainPL.TIME);

    int newX;
    int newY;

    CirclesPL currentObj;

    public PhysicsPL(CirclesPL obj){
        currentObj = obj;
        currentX = currentObj.tempCurrentX;
        currentY = currentObj.tempCurrentY;
    }

    public void refresh(){

        for (int c = 0; c<MainPL.circles.length; c++){
            if (twoDimDistance(currentX, currentY, MainPL.circles[c].currentBody.currentX, MainPL.circles[c].currentBody.currentY) > MainPL.DIAMETER){
                forceGravity = gravitationalConstant * ((mass*mass)/(Math.pow(twoDimDistance(currentX,currentY,MainPL.circles[c].currentBody.currentX,MainPL.circles[c].currentBody.currentY),1)));

                sinTheta = oneDimDistance(currentY, MainPL.circles[c].currentBody.currentY)/twoDimDistance(currentX,currentY,MainPL.circles[c].currentBody.currentX,MainPL.circles[c].currentBody.currentY);
                cosTheta = oneDimDistance(currentX, MainPL.circles[c].currentBody.currentX)/twoDimDistance(currentX,currentY,MainPL.circles[c].currentBody.currentX,MainPL.circles[c].currentBody.currentY);
                
                
                forceGravityX = cosTheta*forceGravity;
                forceGravityY = sinTheta*forceGravity;

                
                forceX+=forceGravityX;
                forceY+=forceGravityY;
            } 
        }

        


        //Below: Collision with other circle
        for (int c = 0; c<MainPL.circles.length; c++){
            if (twoDimDistance(currentX, currentY, MainPL.circles[c].currentBody.currentX, MainPL.circles[c].currentBody.currentY) != 0){
                if (twoDimDistance(currentX, currentY, MainPL.circles[c].currentBody.currentX, MainPL.circles[c].currentBody.currentY)<=MainPL.DIAMETER){
                    if (displacementX>0 && oneDimDistance(currentX,MainPL.circles[c].currentBody.currentX)>0){
                        forceX = 0;
                    }

                    if (displacementY>0 && oneDimDistance(currentY,MainPL.circles[c].currentBody.currentY)>0){
                        forceY = 0;
                    }

                    if (displacementX<0 && oneDimDistance(currentX,MainPL.circles[c].currentBody.currentX)<0){
                        forceX = 0;
                    }

                    if (displacementY<0 && oneDimDistance(currentY,MainPL.circles[c].currentBody.currentY)<0){
                        forceY = 0;
                    }
                }
            }
        }

        

 
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