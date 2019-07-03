package org.hl.nnagent.bug;

public class BugDraw {


    public static void drawMap(BugMap map) {
        char[][] charmap = new char[map.getWidth()][map.getHeight()];
        clearMap(map, charmap);
        drawBound(map, charmap);
        drawMarks(map, charmap);
        render(map, charmap);
    }

    private static void render(BugMap map, char[][] charmap) {
        int w = map.getWidth();
        int h = map.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                System.out.print(charmap[x][y]);
            }
            System.out.println();
        }
    }


    private static void clearMap(BugMap map, char[][] charmap) {
        int w = map.getWidth();
        int h = map.getHeight();
        for (int y = 0; y < h; y++) {
            for (int x = 0; x < w; x++) {
                charmap[x][y] = ' ';
            }
        }
    }

    /**
     * 画边框
     *
     * @param map
     */
    private static void drawBound(BugMap map, char[][] charmap) {
        int w = map.getWidth();
        int h = map.getHeight();
        // top
        for (int x = 0; x < w; x++) {
            charmap[x][0] = '-';
        }

        // bottom
        for (int x = 0; x < w; x++) {
            charmap[x][h - 1] = '-';
        }

        //left
        for (int y = 0; y < h; y++) {
            charmap[0][y] = '|';
        }

        //right
        for (int y = 0; y < h; y++) {
            charmap[w - 1][y] = '|';
        }

        charmap[0][0] = '\u250c';     //┌
        charmap[w - 1][0] = '\u2510';   //┐
        charmap[0][h - 1] = '\u2514';   //└
        charmap[w - 1][h - 1] = '\u2518'; //┘
    }

    private static void drawMarks(BugMap map, char[][] charmap) {
        for (BugMark mark : map.getMarks()) {
            charmap[mark.x][mark.y] = mark.type.getSymbol();
        }
    }
}
