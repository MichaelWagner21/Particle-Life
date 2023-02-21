public class PhysicsPL {
    int currentX;
    int currentY;

    double mass = 1;

    double forceGravity = 0;

    double forceGravityX = 0;
    double forceGravityY = 0;

    double sinTheta = 0;
    double cosTheta = 0;

    double gravitationalConstant = 50;

    double forceX = 50;
    double forceY = 0;

    double accelerationX = forceX/mass;
    double accelerationY = forceY/mass;

    double velocityX = accelerationX*(MainPL.time);
    double velocityY = accelerationY*(MainPL.time);

    double terminalVelocity = 100;

    double displacementX = velocityX*(MainPL.time);
    double displacementY = velocityY*(MainPL.time);

    int newX;
    int newY;

    CirclesPL currentObj;

    public PhysicsPL(CirclesPL obj){
        currentObj = obj;
        currentX = currentObj.tempCurrentX;
        currentY = currentObj.tempCurrentY;
        this.refresh();

    }

    public void refresh(){

       // forceGravity = gravitationalConstant * ((mass*mass)/(Math.pow(twoDimDistance(currentX,currentY,387,375),2)));
        forceGravity = gravitationalConstant * ((mass*mass)/(Math.pow(twoDimDistance(currentX,currentY,387,375),1)));


        


        sinTheta = oneDimDistance(currentY, 375)/twoDimDistance(currentX,currentY,387,375);
        cosTheta = oneDimDistance(currentX, 387)/twoDimDistance(currentX,currentY,387,375);
       
       
        forceGravityX = cosTheta*forceGravity;
        forceGravityY = sinTheta*forceGravity;

        
        forceX+=forceGravityX;
        forceY+=forceGravityY;

        

 
        //Below If Statements: If outside edge and headed outside of bounds, bounce off edge.
        if (currentX-MainPL.xBound()>=0 && forceX>=0){
            forceX = -1*Math.abs(forceX);
        }

        if (currentX<=0 && forceX<=0){
            forceX = Math.abs(forceX);
        }

        if (currentY-MainPL.yBound()>=-10 && forceY>=0){
            forceY = -1*Math.abs(forceY);
        }

        if (currentY<=0 && forceY<=0){
            forceY = Math.abs(forceY);
        }


        accelerationX = forceX/mass;
        accelerationY = forceY/mass;

        velocityX = accelerationX*MainPL.time;
        velocityY = accelerationY*MainPL.time;

        if (Math.abs(velocityX) >= terminalVelocity){
            velocityX = (velocityX/Math.abs(velocityX))*(terminalVelocity-1);
            forceX = (velocityX*mass)/MainPL.time;
        }

        if (Math.abs(velocityY) >= terminalVelocity){
            velocityY = (velocityY/Math.abs(velocityY))*(terminalVelocity-1);
            forceY = (velocityY*mass)/MainPL.time;
        }



        displacementX = velocityX*0.1;
        displacementY = velocityY*0.1;



        
        newX=currentX+(int)displacementX;
        newY=currentY+(int)displacementY;

        currentObj.setCoords(newX,newY);
        
        currentX = newX;
        currentY = newY;

        System.out.print(forceX);
        System.out.print(", ");
        System.out.println(forceY);
        
        MainPL.refresh(MainPL.appFrame);



    }

    public static double twoDimDistance(double x1, double y1, double x2, double y2){
        double verDis = y2-y1;
        double horDis = x2-x1;
        double diagDis = Math.sqrt(verDis*verDis+horDis*horDis);
        return diagDis;
    }

    public static double oneDimDistance(double x1, double x2){
        return x2-x1;
    }
}