import java.util.ArrayList;

public class RubiksCube {
    private int LENGTH, type;
    private int[] f, l, r, d, u, b;
    private BiHashMap<int[],int[],int[]> udderly = new BiHashMap<int[],int[],int[]>();

    public static void main(String[] args) {
        /** check the slides for the starting position (current build only has 3x3x3 supported)
         * each number corresponds to each piece of the cube
         * turns are written in string form *all lowercase*
         * for example, if you want to turn R, or rotate side R, then you write "r"
         * if you want to string together turns, such as rotating side U then rotating side L, then you write "ul"
         *
         * currently all this program does is spit back the turns it takes to return to it's starting position
         */
        RubiksCube cube = new RubiksCube(3);

        System.out.println(cube.orderOf("dbf"));
        System.out.println(cube.trackWithFormat("lfr", 8));
    }

    public RubiksCube(int type) {
        if (type <= 2) this.twobytwo(); else this.threebythree();
        initializeBiHashMap();
    }

    private int orderOf(String turns) {
        int[] ovinus_real = new int[LENGTH * 6];

//        screw micro-optimization
        for (int i = 1; i <= LENGTH * 6; i++) {
            ArrayList sheep = track(turns, i);
            ovinus_real[i - 1] = sheep.size();
        }
        return lcm(ovinus_real);
    }

    private static int gcd(int a, int b) {
        while (b > 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    private static int lcm(int a, int b) {
        return a * (b / gcd(a, b));
    }
    private static int lcm(int[] input) {
        int result = input[0];
        for (int i = 1; i < input.length; i++) result = lcm(result, input[i]);
        return result;
    }

    private ArrayList track(String turns, int start) {
        ArrayList result = new ArrayList();
        int pos = start, n = turns.length(), i = 0;
        while (true) {
            String turnString = Character.toString(turns.charAt(i));
            pos = turn(turnString, pos);
            if (i == 0) result.add(pos); // old standard boring format but is necessary for finding order
            i = (i + 1) % n;
            if (pos == start && i == 0) break;
        }
        return result;
    }

    private ArrayList trackWithFormat(String turns, int start) {
        /** THIS IS FOR VIEWING PURPOSES WHEN DISPLAYING WHAT THE track FUNCTION DOES ONLY
         * THIS IS NOT FOR IMPLEMENTATION WITHIN THE orderOf FUNCTION
         * THIS IS ONLY FOR IMPROVING READABILITY OF EACH ALTERATION OF WHAT EACH SEPARATE MOVE DOES UPON A SPECIFIC PIECE
         */
        ArrayList result = new ArrayList();
        int pos = start, old_pos = start, n = turns.length(), i = 0;
        turns.toLowerCase();
        while (true) {
            String turnString = Character.toString(turns.charAt(i));
            pos = turn(turnString, pos);
            result.add(turnString.toUpperCase() + " : " + old_pos + " → " + pos);
            old_pos = pos;
            i = (i + 1) % n;
            if (pos == start && i == 0) break;
        }
        return result;
    }

    private int turn(String turn, int start) {
        int side_int = (int)((start - 1) / (double) LENGTH);
        int[][] all_side_arr = {f, l, r, d, u, b};
        int[] side_arr = all_side_arr[side_int]; // they're bad names yes
        int loc = (type == 2) ? (start - 1) % 4 : (start - 1) % 9;

        int[] turn_arr = all_side_arr[indexOfString("flrdub", turn.charAt(0))];

        return udderly.get(side_arr, turn_arr)[loc];
    }

    private int indexOfString(String string, char character) {
        for (int i = 0, n = string.length(); i < n; i++) if (character == string.charAt(i)) return i;
        return -1;
    }

    private void initializeBiHashMap() {
            /*
            while hardcoding is the least efficient, it's ironically the most readable
             */
        if (type == 2) {
            udderly.put(f, f, new int[]{2, 4, 1, 3});
            udderly.put(f, l, new int[]{13, 2, 15, 4});
            udderly.put(f, r, new int[]{1, 18, 3, 20});
            udderly.put(f, d, new int[]{1, 2, 11, 12});
            udderly.put(f, u, new int[]{5, 6, 3, 4});
            udderly.put(f, b, new int[]{1, 2, 3, 4});

            udderly.put(l, f, new int[]{5, 20, 7, 19});
            udderly.put(l, l, new int[]{6, 8, 5, 7});
            udderly.put(l, r, new int[]{5, 6, 7, 8});
            udderly.put(l, d, new int[]{5, 6, 3, 4});
            udderly.put(l, u, new int[]{21, 22, 7, 8});
            udderly.put(l, b, new int[]{15, 6, 16, 8});

            udderly.put(r, f, new int[]{14, 10, 13, 12});
            udderly.put(r, l, new int[]{9, 10, 11, 12});
            udderly.put(r, r, new int[]{10, 12, 9, 11});
            udderly.put(r, d, new int[]{9, 10, 23, 24});
            udderly.put(r, u, new int[]{1, 2, 11, 12});
            udderly.put(r, b, new int[]{9, 17, 11, 18});

            udderly.put(d, f, new int[]{6, 8, 15, 16});
            udderly.put(d, l, new int[]{24, 14, 22, 16});
            udderly.put(d, r, new int[]{13, 2, 15, 4});
            udderly.put(d, d, new int[]{14, 16, 13, 15});
            udderly.put(d, u, new int[]{13, 14, 15, 16});
            udderly.put(d, b, new int[]{13, 14, 12, 10});

            udderly.put(u, f, new int[]{17, 18, 9, 11});
            udderly.put(u, l, new int[]{1, 18, 3, 20});
            udderly.put(u, r, new int[]{17, 23, 19, 21});
            udderly.put(u, d, new int[]{17, 18, 19, 20});
            udderly.put(u, u, new int[]{18, 20, 17, 19});
            udderly.put(u, b, new int[]{7, 5, 19, 20});

            udderly.put(b, f, new int[]{21, 22, 23, 24});
            udderly.put(b, l, new int[]{21, 19, 23, 17});
            udderly.put(b, r, new int[]{16, 22, 14, 24});
            udderly.put(b, d, new int[]{21, 22, 7, 8});
            udderly.put(b, u, new int[]{9, 10, 23, 24});
            udderly.put(b, b, new int[]{22, 24, 21, 23});
        } else {
            udderly.put(l, f, new int[]{10, 11, 45, 13, 14, 44, 16, 17, 43});
            udderly.put(l, l, new int[]{12, 15, 18, 11, 14, 17, 10, 13, 16});
            udderly.put(l, r, new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18});
            udderly.put(l, d, new int[]{10, 11, 12, 13, 14, 15, 7, 8, 9});
            udderly.put(l, u, new int[]{46, 47, 48, 13, 14, 15, 16, 17, 18});
            udderly.put(l, b, new int[]{34, 11, 12, 35, 14, 15, 36, 17, 18});

            udderly.put(f, f, new int[]{3, 6, 9, 2, 5, 8, 1, 4, 7});
            udderly.put(f, l, new int[]{28, 2, 3, 31, 5, 6, 34, 8, 9});
            udderly.put(f, r, new int[]{1, 2, 39, 4, 5, 42, 7, 8, 45});
            udderly.put(f, d, new int[]{1, 2, 3, 4, 5, 6, 25, 26, 27});
            udderly.put(f, u, new int[]{10, 11, 12, 4, 5, 6, 7, 8, 9});
            udderly.put(f, b, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});

            udderly.put(r, f, new int[]{30, 20, 21, 29, 23, 24, 28, 26, 27});
            udderly.put(r, l, new int[]{19, 20, 21, 22, 23, 24, 25, 26, 27});
            udderly.put(r, r, new int[]{21, 24, 27, 20, 23, 26, 19, 22, 25});
            udderly.put(r, d, new int[]{19, 20, 21, 22, 23, 24, 52, 53, 54});
            udderly.put(r, u, new int[]{1, 2, 3, 22, 23, 24, 25, 26, 27});
            udderly.put(r, b, new int[]{19, 20, 37, 22, 23, 38, 25, 26, 39});

            udderly.put(d, f, new int[]{12, 15, 18, 31, 32, 33, 34, 35, 36});
            udderly.put(d, l, new int[]{54, 29, 30, 51, 32, 33, 48, 35, 36});
            udderly.put(d, r, new int[]{28, 29, 3, 31, 32, 6, 34, 35, 9});
            udderly.put(d, d, new int[]{30, 33, 36, 29, 32, 35, 28, 31, 34});
            udderly.put(d, u, new int[]{28, 29, 30, 31, 32, 33, 34, 35, 36});
            udderly.put(d, b, new int[]{28, 29, 30, 31, 32, 33, 27, 24, 21});

            udderly.put(u, f, new int[]{37, 38, 39, 40, 41, 42, 19, 22, 25});
            udderly.put(u, l, new int[]{1, 38, 39, 4, 41, 42, 7, 44, 45});
            udderly.put(u, r, new int[]{37, 38, 52, 40, 41, 49, 43, 44, 46});
            udderly.put(u, d, new int[]{37, 38, 39, 40, 41, 42, 43, 44, 45});
            udderly.put(u, u, new int[]{39, 42, 45, 38, 41, 44, 37, 40, 43});
            udderly.put(u, b, new int[]{16, 13, 10, 40, 41, 42, 43, 44, 45});

            udderly.put(b, f, new int[]{46, 47, 48, 49, 50, 51, 52, 53, 54});
            udderly.put(b, l, new int[]{46, 47, 43, 49, 50, 40, 52, 53, 37});
            udderly.put(b, r, new int[]{36, 47, 48, 33, 50, 51, 30, 53, 54});
            udderly.put(b, d, new int[]{46, 47, 48, 49, 50, 51, 16, 17, 18});
            udderly.put(b, u, new int[]{19, 20, 21, 49, 50, 51, 52, 53, 54});
            udderly.put(b, b, new int[]{48, 51, 54, 47, 50, 53, 46, 49, 52});
        }
    }
    private void twobytwo() {
        LENGTH = 4;
        type = 2;
        f = new int[]{1, 2, 3, 4};
        l = new int[]{5, 6, 7, 8};
        r = new int[]{9, 10, 11, 12};
        d = new int[]{13, 14, 15, 16};
        u = new int[]{17, 18, 19, 20};
        b = new int[]{21, 22, 23, 24};
    }

    private void threebythree() {
        LENGTH = 9;
        type = 3;
        f = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        l = new int[]{10, 11, 12, 13, 14, 15, 16, 17, 18};
        r = new int[]{19, 20, 21, 22, 23, 24, 25, 26, 27};
        d = new int[]{28, 29, 30, 31, 32, 33, 34, 35, 36};
        u = new int[]{37, 38, 39, 40, 41, 42, 43, 44, 45};
        b = new int[]{46, 47, 48, 49, 50, 51, 52, 53, 54};
    }
}