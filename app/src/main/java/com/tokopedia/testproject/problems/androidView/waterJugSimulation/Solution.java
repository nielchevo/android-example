package com.tokopedia.testproject.problems.androidView.waterJugSimulation;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    final static String TAG = "JUG_SIMULATION";

    private static int gcd(int a, int b) {
        if(b==0)
        {
            return a;
        }
        return gcd(b, a%b);
    }

    private static List<WaterJugAction> doCalclulateListStep(int jug1, int jug2, int target, List<WaterJugAction> list){
        // init first step, by fill jug A with water
        int jugA = jug1;
        int jugB = 0;

        list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));

        int step = 1;

        Log.d(TAG,"Step: "+step+" JugA: "+ jugA +" JugB: "+ jugB);

        while(jugA != target || jugB != target)
        {
            // get diff
            int diff = Math.min(jugA, jug2 - jugB);

            // POUR Jug A to Jug B. always centered on Jug B.
            jugB += diff;
            jugA -= diff;
            list.add(new WaterJugAction(WaterJugActionEnum.POUR, 2));

            step++;
            Log.d(TAG,"Step: "+step+" JugA: "+ jugA +" JugB: "+ jugB);

            // if jugs already fit target, stop
            if(jugA == target || jugB == target)
            {
                break;
            }

            // if jugA empty, then FILL again
            if(jugA == 0)
            {
                jugA = jug1;
                list.add(new WaterJugAction(WaterJugActionEnum.FILL, 1));

                step++;
                Log.d(TAG, "STEP: "+ step +" -> jugA is EMPTY, do REFILL.");
            }

            if(jugB == jug2)
            {
                jugB = 0;
                list.add(new WaterJugAction(WaterJugActionEnum.EMPTY, 2));

                step++;
                Log.d(TAG,"STEP: "+ step +" -> jugB is FULL, do EMPTY");
            }
        }

        return list;
    }

    public static List<WaterJugAction> simulateWaterJug(int jug1, int jug2, int target) {
        // TODO, simulate the smallest number of action to do the water jug problem
        // below is stub, replace with your implementation!


        // make sure Jug 2 always the Bigger limit.
        if(jug1 > jug2){
            int temp = jug1;
            jug1 = jug2;
            jug2 = temp;
        }

        // check if both value could be solveable
        if(target % gcd(jug1, jug2) != 0)
        {
            return new ArrayList<>();
        }

        List<WaterJugAction> A = new ArrayList<>();
        List<WaterJugAction> B = new ArrayList<>();

        int A_size = doCalclulateListStep(jug1, jug2, target, A).size();
        int B_size = doCalclulateListStep(jug2, jug1, target, B).size();

        Log.d(TAG,"Step Size Compare A: "+ A +" vs  B: "+B);

        return (A_size < B_size) ? A : B;
    }
}
