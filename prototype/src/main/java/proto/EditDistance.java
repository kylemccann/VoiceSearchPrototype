package proto;

public class EditDistance
{
    //* Code from  https://gist.github.com/gabhi/11243437 *//

    private float editDistance;

    public static int minDistance(String expectedWord, String actualWord) {
        int len1 = expectedWord.length();
        int len2 = actualWord.length();

        // two dimensional array the size of expectedWord and actualWord
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            dp[i][0] = i;
        }

        for (int j = 0; j <= len2; j++) {
            dp[0][j] = j;
        }

        //iterate though, and check last char
        for (int i = 0; i < len1; i++) {
            char c1 = expectedWord.charAt(i);
            for (int j = 0; j < len2; j++) {
                char c2 = actualWord.charAt(j);

                //if last two chars equal
                if (c1 == c2) {
                    //update dp value for +1 length
                    dp[i + 1][j + 1] = dp[i][j];
                } else {
                    int replace = dp[i][j] + 1;
                    int insert = dp[i][j + 1] + 1;
                    int delete = dp[i + 1][j] + 1;

                    int min = replace > insert ? insert : replace;
                    min = delete > min ? min : delete;
                    dp[i + 1][j + 1] = min;
                }
            }
        }

        return dp[len1][len2];
    }

    public float getEditDistance() {
        return editDistance;
    }

    public void setEditDistance(float editDistance) {
        this.editDistance = editDistance;
    }
}
