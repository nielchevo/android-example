package com.tokopedia.testproject.problems.algorithm.continousarea;

import java.util.HashMap;

/**
 * Created by hendry on 18/01/19.
 */
public class Solution {
    /*public static int maxContinuousArea(int[][] matrix) {
        // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        return 0;
    }*/

    private static boolean[][] isVisited;
    private static int m_row, m_col;
    private static int maxArea, m_currentArea;
    private static int currentRegion = 0;

    private static HashMap<Integer, Integer> mListMaxRegion = new HashMap<Integer, Integer>();

    private static boolean CheckValue(int curPos, int[][] mArr, int mRow, int mCol, boolean[][] isVisited)
    {
        try
        {
            if(!isVisited[mRow][mCol]) // check if next arr are already visited
            {
                if(mArr[mRow][mCol] == curPos) // check if next array is same value as current position
                {
                    return true;
                }
            }

            return false;
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("[CheckValue() Func] Out of Bound! at: iRow: "+ mRow +" jCol: "+ mCol);
            return false;
        }
    }

    private static void DeepFirstSearch(int curArr, int[][] mArr, int mRow, int mCol, boolean[][] visited)
    {
        try
        {
            if(CheckValue(curArr, mArr, mRow, mCol, visited))
            {
                // markVisit this array
                visited[mRow][mCol] = true;
                m_currentArea++;

                // Check neighboor array
                DeepFirstSearch(curArr, mArr, mRow, mCol-1, visited); // north
                DeepFirstSearch(curArr, mArr, mRow+1, mCol, visited); // east
                DeepFirstSearch(curArr, mArr, mRow, mCol+1, visited); // south
                DeepFirstSearch(curArr, mArr, mRow-1, mCol, visited); // west
            }
        }
        catch(ArrayIndexOutOfBoundsException ex)
        {
            System.out.println("[DFS() Func] Out of Bound Exception!");
        }
    }

    public static int maxContinuousArea(int[][] mArr)
    {
        m_row = mArr[0].length;
        m_col = mArr.length;

        // To track visited array.
        isVisited = new boolean[m_row][m_col];

        for(int i=0; i < m_row; i++)
        {
            for (int j=0; j< m_col; j++)
            {
                if(!isVisited[i][j])
                {
                    // Mark new region
                    currentRegion += 1;

                    // Reset current area
                    m_currentArea = 0;

                    try
                    {
                        DeepFirstSearch(mArr[i][j], mArr, i, j, isVisited);
                    }
                    catch(ArrayIndexOutOfBoundsException ex)
                    {
                        /**catch array out of bound due to col and row is not match */
                        System.out.println("[CheckLargerstRegion() func] Out of Bound in row "+ i +" col: "+ j);
                    }

                    System.out.println("--- END of DFS search with result: currentRegion= "+ currentRegion +" currentArea= "+ m_currentArea + " ---");

                    // Save and track result inside stack
                    //mListMaxRegion.put(currentRegion, m_currentArea); *lambda issue

                    // Track the highest region
                    if(m_currentArea > maxArea)
                    {
                        maxArea = m_currentArea;
                    }
                }
            }
        }

        // Traverse throught stack to get highest region.
//        mListMaxRegion.entrySet().forEach(entry -> {  // *lambda issue
//            if(entry.getValue() > maxArea) {
//                maxArea = entry.getValue();
//            }
//        });

        return maxArea;
    }
}
