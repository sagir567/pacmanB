import org.junit.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

public class MapTest {

    /**
     * This test verifies the correctness of the floodFill method. It creates two maps (map1 and map2),
     * performs the flood fill operation on map1, and then asserts that map1 is equal to map2.
     */
    @Test
    public void TestFill() {
        int[][] map1 = {
                {0, -1, -1, 0},
                {-1, 0, 0, -1},
                {-1, 0, 0, -1}
        };
        int[][] map2 = {
                {0, -1, -1, 0},
                {-1, 3, 3, -1},
                {-1, 3, 3, -1}
        };
       Map map = new Map(map1);
       map.floodFill(2,2,0,3);
        assertArrayEquals(map1, map2);

    }


    /**
     * This test checks the functionality of the isValidMove method.
     * It creates a map (map1) and asserts that the move to position (2, 2) is valid.
     */
    @Test
    public void TestIsValidMove() {
        int[][] map1 = {
                {0, -1, -1, 0},
                {-1, 0, 0, -1},
                {-1, 0, 0, -1}
        };
        Map map =new Map(map1);
        assertTrue(map.isValidMove(map1, 2, 2));
    }


/**
// This test validates the correctness of the fill method. It creates two maps (map1 and map2), creates a new Map object (map3), performs the flood fill operation on map1,
// and then asserts that the result of fill method on position (2, 2) is equal to map2.
// */
    @Test
    public void TestFloodFill() {
        int[][] map1 = {
                {0, -1, -1,0},
                {-1, 0, 0,-1},
                {-1, 0, 0,-1}
        };
        int[][] map2 = {
                {0, -1, -1,0},
                {-1, 3, 3,-1},
                {-1, 3, 3,-1}
        };
        Map map3 = new Map(map1);
        map3.floodFill(2,2,0,3);
        Pixel2D s1= new Index2D(2,2);

        assertEquals(printArr(map2) ,printArr(map3.getMap()));

    }

    /**
     * Test if a pixel that is inside the map asserts true.
     */

    @Test
    public void isInside() {
        int[][] map = {
                {0, 0, 0},
                {0, 0, 0},
                {0, 0, 0}
        };
        Map2D map2 = new Map(map);


        Pixel2D p1 = new Index2D(1, 1);
        assertTrue(map2.isInside(p1));
    }



    @Test
    public void isNeighborsNotCyclicTest() {//checking 2 vertically neighbored points.
        Map a1 = new Map(3, 3, -1);
        a1.setPixel(1,1,0);
        a1.setPixel(2,1,0);
        Pixel2D p1 = new Index2D(1, 1);
        Pixel2D p2 = new Index2D(2, 1);
        assertTrue(a1.isNeighbors(p1, p2));


    }


    @Test
    public void isNeighborsNotCyclic2Test() {//checking 2 horizontally neighbored points.
        Map a1 = new Map(4, 4, 0);
        a1.setCyclic(false);
        a1.setPixel(0,1,0);
        a1.setPixel(0,2,0);
        Pixel2D p1 = new Index2D(0, 1);
        Pixel2D p2 = new Index2D(0, 2);
        assertTrue(a1.isNeighbors(p1, p2));

        Pixel2D p3 = new Index2D(0, 1);
        Pixel2D p4 = new Index2D(3,1 );
         assertFalse(a1.isNeighbors(p3,p4));


    }

    @Test
    public void isNeighborsCyclic1Test() {//checking on a cyclic map
        Map a1 = new Map(3, 3, 0);
        Pixel2D p1 = new Index2D(0, 1);
        Pixel2D p2 = new Index2D(2, 1);
        assertTrue(a1.isNeighbors(p1, p2));
    }


    @Test
    public void isNeighborsCyclic2Test() {//checking neighbors on a cyclic map

        Map a1 = new Map(3, 3, 0);
        Pixel2D p1 = new Index2D(1, 0);
        Pixel2D p2 = new Index2D(1, 2);
        assertTrue(a1.isNeighbors(p1, p2));
    }

    /**
     * t creates a map (a1) and performs the allDistance operation with a pixel (a) and obstacle color -1.
     * It then compares the obtained distances with the expected distances from a pre-defined map (a2).
     */
    @Test
    public void allDistanceTest() {
        Pixel2D a = new Index2D(2, 2);
        Map a1 = new Map(4, 4, 0);
        a1.setPixel(0, 1, -1);
        a1.setPixel(1, 0, -1);
        a1.setPixel(2, 0, -1);
        a1.setPixel(2, 1, -1);
        a1.setPixel(2, 3, -1);
        a1.setPixel(2, 2, -2);
        Map2D a3 = a1.allDistance(a, -1);
        Map a2 = new Map(4, 4, 0);
        a2.setPixel(0, 0, -1);
        a2.setPixel(0, 1, -1);
        a2.setPixel(0, 2, 2);
        a2.setPixel(0, 3, 3);
        a2.setPixel(1, 0, -1);
        a2.setPixel(1, 1, 2);
        a2.setPixel(1, 2, 1);
        a2.setPixel(1, 3, 2);
        a2.setPixel(2, 0, -1);
        a2.setPixel(2, 1, -1);
        a2.setPixel(2, 2, 0);
        a2.setPixel(2, 3, -1);
        a2.setPixel(3, 0, 3);
        a2.setPixel(3, 1, 2);
        a2.setPixel(3, 2, 1);
        a2.setPixel(3, 3, 2);


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                assertEquals(a3.getPixel(i, j), a2.getPixel(i, j));


            }

        }


    }

    public String printArr(int [][] arr){
        String res="";
        for (int i = 0; i <arr.length ; i++) {

            res += Arrays.toString(arr[i]);

        }
        System.out.println(res);
        return res;
    }

    @Test
    public void shortestPathTest() {
        Map a2 = new Map(4, 4, 0);
        a2.setPixel(1, 0, -1);
        a2.setPixel(0, 2, 0);
        a2.setPixel(2, 0, -1);
        a2.setPixel(0, 1, -1);
        a2.setPixel(2, 1, -1);
        a2.setPixel(2, 2, 0);
        a2.setPixel(2, 3, -1);
        Pixel2D p1 = new Index2D(2, 2);
        Pixel2D p2 = new Index2D(0, 3);

        Map2D a3 = a2.allDistance(p1, -1);
        System.out.println("a3 is:\n");
        printArr(a3.getMap());
        System.out.println("-------");


        Pixel2D[] shortestP = a3.shortestPath(p1, p2, -1);






        assertTrue(shortestP.length ==4);


    }
}

