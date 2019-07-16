package com.tokopedia.testproject.problems.algorithm.continousarea;

/**
 * Created by hendry on 18/01/19.
 */
public class Solution {
    /*public static int maxContinuousArea(int[][] matrix) {
        // TODO, return the largest continuous area containing the same integer, given the 2D array with integers
        // below is stub
        return 0;
    }*/

    private static boolean[][] visited;
    private static int m_row, m_col;
    private static int m_maxArea, m_currentArea;

    private static boolean CheckValue(int[][] mArr, int mRow, int mCol, boolean[][] visited, int flag)
    {
        try
        {
            if((mRow >= 0) && (mRow < m_row) && (mCol >= 0) && (mCol < m_col)   //check boundary
                    && !visited[mRow][mCol]                     // check is visited
                    && mArr[mRow][mCol] == flag)                // check if value is same
            {
                return true;
            }

            System.out.printf("Check FAILED! row: %s col: %s visited: %s | arr: %d != flag: %d \n",
                    mRow, mCol, visited[mRow][mCol], mArr[mRow][mCol], flag );

            return false;
        }
        catch(Exception ex)
        {
            System.out.println(ex.getMessage());
            return false;
        }
    }

    private static void DeepFirstSearch(int[][] mArr, int mRow, int mCol, boolean[][] visited, int find)
    {
        if(CheckValue(mArr, mRow, mCol, visited, find))
        {
            visited[mRow][mCol] = true;

            System.out.println("check ok");
            m_currentArea++;

            DeepFirstSearch(mArr, mRow, mCol-1, visited, find); // north
            DeepFirstSearch(mArr, mRow+1, mCol, visited, find); // east
            DeepFirstSearch(mArr, mRow, mCol+1, visited, find); // south
            DeepFirstSearch(mArr, mRow-1, mCol, visited, find); // west

        }
    }

    public static int maxContinuousArea(int[][] mArr)
    {
        m_row = mArr[0].length;
        m_col = mArr.length;

        // To track visited cell.
        visited = new boolean[m_row][m_col];

        for(int i=0; i < m_row; i++)
        {
            for (int j=0; j< m_col; j++)
            {
                if(visited[i][j] == false)
                {
                    m_currentArea = 0;
                    DeepFirstSearch(mArr, i, j, visited, mArr[i][j]);

                    m_maxArea = Math.max(m_maxArea, m_currentArea);
                }
            }
        }

        return m_maxArea;
    }
}
