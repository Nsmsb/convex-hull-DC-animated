import Dessin.*;
import java.awt.Color;
// import java.awt.Point;
import java.util.*;


public class ConvexHullDivCon {


    
    // visualisation

    static void drawHull(Fenetre f, List<Point> hull) {
        int j = 1;
        Point p1,p2;
        for(int i = 0; i < hull.size(); i++) {
            p1 = hull.get(i);
            p2 = hull.get(j);
            j = (j+1)%hull.size();
            f.tracerSansDelai(new Segment(p1.x, p1.y, p2.x, p2.y));
        }
    }

    static void eraseHull(Fenetre f, List<Point> hull) {
        int j = 1;
        Point p1,p2;
        for(int i = 0; i < hull.size(); i++) {
            p1 = hull.get(i);
            p2 = hull.get(j);
            j = (j+1)%hull.size();
            f.effacerSansDelai(new Segment(p1.x, p1.y, p2.x, p2.y));
        }
    }


    // algorithm implimentation

    // sorting code

    

    static Point getCenterPoint(List<Point> hull) {
		double sumX = 0.0;
		double sumY = 0.0;
		
		for (Point point : hull)
		{
			sumX+= point.x;
			sumY+= point.y;
		}
		
		sumX = sumX / hull.size();
		sumY = sumY / hull.size();
		
		Point center = new Point((int)sumX, (int)sumY);
		
		return center;
    }
    

    static void sortCounerClock(List<Point> hull) {
        Point center = getCenterPoint(hull);
        Collections.sort(hull, new CounterClockComparator(center));
    }

    
    static boolean turnsLeft(Point p1, Point p2, Point p3) {
        Point s1 = new Point(p2.x-p1.x, p2.y-p1.y);
        Point s2 = new Point(p3.x-p2.x, p3.y-p2.y);

        return s1.x*s2.y - s1.y*s2.x > 0;
    }

    static boolean turnsRight(Point p1, Point p2, Point p3) {
        Point s1 = new Point(p2.x-p1.x, p2.y-p1.y);
        Point s2 = new Point(p3.x-p2.x, p3.y-p2.y);

        return s1.x*s2.y - s1.y*s2.x < 0;

    }

    static int getRightMostPointIndex(List<Point> hull) {
        int rightMost = 0;
        		
		for (int i = 1; i < hull.size(); i++)
			if(hull.get(i).x > hull.get(rightMost).x)
				rightMost = i;
		
		return rightMost;
    }
    
    static int getLeftMostPointIndex(List<Point> hull) {
        int leftMost = 0;
        		
		for (int i = 1; i < hull.size(); i++)
			if(hull.get(i).x < hull.get(leftMost).x)
				leftMost = i;
		
		return leftMost;
    }

    static List<Integer> getUpperTan(List<Point> leftHull, List<Point> rightHull) {

        List<Integer> upperTan = new LinkedList<>();
        
        int leftSize = leftHull.size();
        int rightSize = rightHull.size();

        int mostRightIndex = getRightMostPointIndex(leftHull);
        int mostLeftIndex = getLeftMostPointIndex(rightHull);

        Point mostRight = leftHull.get(mostRightIndex);
        Point mostLeft = rightHull.get(mostLeftIndex);

        int pRightIndex = (mostLeftIndex+rightSize-1)%rightSize;
        Point pRight = rightHull.get(pRightIndex);

        int pLeftIndex = (mostRightIndex+1)%leftSize;
        Point pLeft = leftHull.get(pLeftIndex);

        while(turnsLeft(mostRight, mostLeft, pRight) && turnsRight(mostLeft, mostRight, pLeft)) {
            while(turnsLeft(mostRight, mostLeft, pRight)) {
                mostLeft = pRight;
                pRightIndex = (pRightIndex+rightSize-1)%rightSize;
                pRight = rightHull.get(pRightIndex);             
            }

            while(turnsRight(mostLeft, mostRight, pLeft)) {
                mostRight = pLeft;
                pLeftIndex = (pLeftIndex+1)%leftSize;
                pLeft = leftHull.get(pLeftIndex);
            }

        }

        upperTan.add((pLeftIndex+leftSize-1)%leftSize);
        upperTan.add((pRightIndex+1)%rightSize);

        return upperTan;
    }

    static List<Integer> getLowerTan(List<Point> leftHull, List<Point> rightHull) {

        List<Integer> lowerTan = new LinkedList<>();
        
        int leftSize = leftHull.size();
        int rightSize = rightHull.size();
        
        int mostRightIndex = getRightMostPointIndex(leftHull);
        int mostLeftIndex = getLeftMostPointIndex(rightHull);

        Point mostRight = leftHull.get(mostRightIndex);
        Point mostLeft = rightHull.get(mostLeftIndex);


        
        int pRightIndex = (mostLeftIndex+1)%rightSize;
        Point pRight = rightHull.get(pRightIndex);

        int pLeftIndex = (mostRightIndex+leftSize-1)%leftSize;
        Point pLeft = leftHull.get(pLeftIndex);

        while(turnsRight(mostRight, mostLeft, pRight) && turnsLeft(mostLeft, mostRight, pLeft)) {
            while(turnsRight(mostRight, mostLeft, pRight)) {
                mostLeft = pRight;
                pRightIndex = (pRightIndex+1)%rightSize;
                pRight = rightHull.get(pRightIndex);             
            }

            while(turnsLeft(mostLeft, mostRight, pLeft)) {
                mostRight = pLeft;
                pLeftIndex = (pLeftIndex+leftSize-1)%leftSize;
                pLeft = leftHull.get(pLeftIndex);
            }

        }

        lowerTan.add((pLeftIndex+1)%leftSize);
        lowerTan.add((pRightIndex+rightSize-1)%rightSize);

        return lowerTan;
    }


    static List<Point> mergeHulls(Fenetre f, List<Point> leftHull, List<Point> rightHull, List<Integer> upperTan, List<Integer> lowerTan) {

        List<Point> result = new LinkedList<>();
        
        Point leftUp, leftLp, rightUp, rightLp;
        int leftSize, rightSize, i;

        leftUp = leftHull.get(upperTan.get(0));
        leftLp = leftHull.get(lowerTan.get(0));
        rightUp = rightHull.get(upperTan.get(1));
        rightLp = rightHull.get(lowerTan.get(1));

        f.tracer(new Segment(leftUp.x, leftUp.y, rightUp.x, rightUp.y));
        f.tracer(new Segment(leftLp.x, leftLp.y, rightLp.x, rightLp.y));


        leftSize = leftHull.size();
        rightSize = rightHull.size();

        i = (upperTan.get(0)+1)%leftSize;
        for(; !leftUp.equals(leftLp); i = (i+1)%leftSize) {
            result.add(leftUp);
            leftUp = leftHull.get(i);
            System.out.println("here");
        }
        result.add(leftLp);
        
        i = (lowerTan.get(1)+1)%rightSize;
        for(; !rightLp.equals(rightUp); i = (i+1)%rightSize) {
            result.add(rightLp);
            rightLp = rightHull.get(i);
        }
        result.add(rightUp);

        return result;
    }


    static List<Point> getConvexHull(Fenetre f, List<Point> hull) {
        if(hull.size() <= 3)
            return hull;
        
        List<Point> leftHalfHull = getConvexHull(f, new LinkedList(hull.subList(0, hull.size()/2)));
        List<Point> rightHalfHull = getConvexHull(f, new LinkedList(hull.subList(hull.size()/2, hull.size())));

        // drawHull(f, leftHalfHull);
        // drawHull(f, rightHalfHull);

        sortCounerClock(leftHalfHull);
        sortCounerClock(leftHalfHull);
        List<Point> result = mergeHulls(f, leftHalfHull, rightHalfHull, getUpperTan(leftHalfHull, rightHalfHull), getLowerTan(leftHalfHull, rightHalfHull));

        // drawHull(f, result);
        return result;
    }


    


    
    
    
    public static void main(Fenetre f, String [] args) {

        Random r = new Random();
        List<Point> l = new LinkedList<Point>();
        int nbhull;

        // Reccuperation du nombre de hull en argument (ou valeur par defaut)
        if (args.length > 0) {
            nbhull = Integer.parseInt(args[0]);
        } else {
            nbhull = 5;
        }

        // Generation du nuage avec une petite marge pour ne pas avoir de
        // hull contre le bord de la fenetre
        for (int i=0; i<6; i++) {
            int x = r.nextInt(f.largeur()-20)+10;
            int y = r.nextInt(f.hauteur()-20)+10;
            Point p = new Point(x, y);
            l.add(p);
            f.tracerSansDelai(p);
        }

        // l.add(new Point(100,100));
        // l.add(new Point(105,100));
        // l.add(new Point(103,110));

        List<Point> left, right;
        left = new LinkedList<>();
        // left.add(new Point(100,101));
        // left.add(new Point(101,100));
        // left.add(new Point(100,-102));
        // left.add(new Point(-101,100));

        left.add(new Point(-1, 1));
        left.add(new Point(-2, 2));
        left.add(new Point(-4, 1));
        left.add(new Point(-3, -1));


        right = new LinkedList<>();
        // right.add(new Point(102,102));
        // right.add(new Point(103,101));
        // right.add(new Point(103,103));
        // right.add(new Point(105,102));
        // right.add(new Point(104,100));

        right.add(new Point(1,1));
        right.add(new Point(2,-3));
        right.add(new Point(4,0));
        right.add(new Point(3,4));

        
        Collections.sort(l, new Comparator<Point>() {
            public int compare(Point p1, Point p2) {
                return Integer.compare(p1.x, p2.x);
            }
            });
        
            left = l.subList(0, 3);
            right = l.subList(3, 6);


        // sortCounerClock(left);
        // sortCounerClock(right);

        System.out.println(left.toString());
        System.out.println(right.toString());
        
        // List<Integer> up = getUpperTan(left, right);
        // List<Integer> lp = getLowerTan(left, right);

        // List<Point> result = mergeHulls(left, right, up, lp);


        List<Point> result = getConvexHull(f, l);

        // drawHull(f, l);

        // drawHull(f, result);
        System.out.println(result.toString());

        


        System.out.println(l);

        // eraseHull(f, l);
        




    }
}