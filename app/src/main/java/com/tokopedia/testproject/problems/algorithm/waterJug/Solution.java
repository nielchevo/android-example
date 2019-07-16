package com.tokopedia.testproject.problems.algorithm.waterJug;

public class Solution {

    /*public static int minimalPourWaterJug(int jug1, int jug2, int target) {
        // TODO, return the smallest number of POUR action to do the water jug problem
        // below is stub, replace with your implementation!
        return 3;
    }*/

    private static int gcd(int a, int b)
    {
        if(b==0)
        {
            return a;
        }
        return gcd(b, a%b);
    }

    private static int calcStep(int fromCap, int toCap, int target)
    {
        //step start at 1
        int step = 1;

        // init first step, fill jug A with value
        int jugA = fromCap;
        int jugB = 0;

        System.out.format("STEP: %d. -- jugA: %d | jugB: %d \n", step, jugA, jugB);

        while(jugA != target || jugB != target)
        {
            // get diff
            int diff = Math.min(jugA, toCap - jugB);

            // POUR
            jugB += diff;
            jugA -= diff;

            step++;
            System.out.format("STEP: %d. jugA: %d | jugB: %d \n", step, jugA, jugB);

            // if jugs already fit target, stop
            if(jugA == target || jugB == target)
            {
                break;
            }

            // if jugA empty, then fill again
            if(jugA == 0)
            {
                jugA = fromCap;
                step++;
                System.out.format("STEP: %d jugA is EMPTY, do REFILL.\n", step);
            }

            if(jugB == toCap)
            {
                jugB = 0;
                step++;
                System.out.format("STEP: %d jugB is FULL, do EMPTY.\n", step);
            }
        }

        return step;
    }

    public static int minimalPourWaterJug(int jugA, int jugB, int target)
    {
        // check if both input is posible reach target with Great
        if(target % gcd(jugA, jugB) != 0)
        {
            return -1;
        }

        // find the least step with tree
        return Math.min(calcStep(jugA, jugB, target), calcStep(jugB, jugA, target));
    }
}
