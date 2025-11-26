import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(reader);


        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while (T--> 0) {
            Cube cube = new Cube();
            int cmd = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (cmd--> 0) {
                String s = st.nextToken();

                switch (s) {
                    case "L+":
                        cube.leftClockwise();
                        break;
                    case "L-":
                        cube.leftCounterClockwise();
                        break;
                    case "R+":
                        cube.rightClockwise();
                        break;
                    case "R-":
                        cube.rightCounterClockwise();
                        break;
                    case "U+":
                        cube.upClockwise();
                        break;
                    case "U-":
                        cube.upCounterClockwise();
                        break;
                    case "D+":
                        cube.downClockwise();
                        break;
                    case "D-":
                        cube.downCounterClockwise();
                        break;
                    case "F+":
                        cube.frontClockwise();
                        break;
                    case "F-":
                        cube.frontCounterClockwise();
                        break;
                    case "B+":
                        cube.backClockwise();
                        break;
                    case "B-":
                        cube.backCounterClockwise();
                        break;
                }
            }
//            cube.front.printFace();
//            System.out.println();
//            cube.left.printFace();
//            System.out.println();
//            cube.back.printFace();
//            System.out.println();
//            cube.right.printFace();
//            System.out.println();
//            cube.down.printFace();

            sb.append(cube.up.getFace());
        }
        System.out.print(sb);
//        Cube cube = new Cube();
//        cube.backClockwise();
//        cube.back.printFace();
//        cube.up.printFace();
//        cube.left.printFace();
//        cube.down.printFace();
//        cube.right.printFace();



    }
    static class Cube {
        static class Face {
            char[][] val;
            static final char[] values = {'w', 'y', 'r', 'o', 'g', 'b'};
            Face(int type) {
                this.val = new char[3][3];
                for (int i=0; i<3; i++) {
                    Arrays.fill(this.val[i], values[type]);
                }
            }
            String getFace() {
                StringBuilder sb = new StringBuilder();
                for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                        sb.append(val[i][j]);
                    }
                    sb.append('\n');
                }
                return sb.toString();
            }
            void printFace() {
                for (int i=0; i<3; i++) {
                    for (int j=0; j<3; j++) {
                        System.out.print(val[i][j]);
                    }
                    System.out.println();
                }
            }
        }
        Face up, down, front, back, left, right;
        Cube() {
            this.up = new Face(0);
            this.down = new Face(1);
            this.front = new Face(2);
            this.back = new Face(3);
            this.left = new Face(4);
            this.right = new Face(5);
        }
        void upClockwise() {
            /*
            * front left back right
            * front:
            * */
            Face[] inventory = {front, left, back, right};
            char[] temp = new char[12];
            int p = 0;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    temp[p++] = f.val[0][c];
                }
            }
            //
            p = 9;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    f.val[0][c] = temp[p++ % 12];
                }
            }
            rotateFaceClockwise(up);
        }
        void upCounterClockwise() {
            // front right back left
            Face[] inventory = {front, right, back, left};
            char[] temp = new char[12];
            int p = 0;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    temp[p++] = f.val[0][c];
                }
            }
            p = 9;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    f.val[0][c] = temp[p++ % 12];
                }
            }
            rotateFaceCounterClockwise(up);
        }
        void downClockwise() {
            // back left front right
            Face[] inventory = {back, left, front, right};
            char[] temp = new char[12];
            int p = 0;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    temp[p++] = f.val[2][c];
                }
            }
            p = 9;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    f.val[2][c] = temp[p++ % 12];
                }
            }
            rotateFaceClockwise(down);
        }
        void downCounterClockwise() {
            // back right front left
            Face[] inventory = {back, right, front, left};
            char[] temp = new char[12];
            int p = 0;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    temp[p++] = f.val[2][c];
                }
            }
            p = 9;
            for (Face f: inventory) {
                for (int c=0; c<3; c++) {
                    f.val[2][c] = temp[p++ % 12];
                }
            }
            rotateFaceCounterClockwise(down);
        }
        void leftClockwise() {
            // up front down back
            // up: 1 4 7
            // front: 7 4 1
            // down: 3 6 9
            // back: 9 6 3

            char[] temp = new char[12];
            int p = 0;
            for (int r=0; r<3; r++) {
                temp[p++] = up.val[r][0];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = front.val[r][0];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = down.val[r][2];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = back.val[r][2];
            }
            p = 9;
            for (int r=0; r<3; r++) {
                up.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                front.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                down.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                back.val[r][2] = temp[p++ % 12];
            }
            rotateFaceClockwise(left);
        }
        void leftCounterClockwise() {
            // front up back down
            // front: 1 4 7
            // up: 7 4 1
            // back: 3 6 9
            // down: 9 6 3

            char[] temp = new char[12];
            int p = 0;
            for (int r=0; r<3; r++) {
                temp[p++] = front.val[r][0];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = up.val[r][0];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = back.val[r][2];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = down.val[r][2];
            }
            p = 9;
            for (int r=0; r<3; r++) {
                front.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                up.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                back.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                down.val[r][2] = temp[p++ % 12];
            }
            rotateFaceCounterClockwise(left);
        }
        void rightClockwise() {
            // front up back down
            // front: 3 6 9
            // up: 9 6 3
            // back: 1 4 7
            // down: 7 4 1
            char[] temp = new char[12];
            int p = 0;
            for (int r=0; r<3; r++) {
                temp[p++] = front.val[r][2];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = up.val[r][2];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = back.val[r][0];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = down.val[r][0];
            }
            p = 9;
            for (int r=0; r<3; r++) {
                front.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                up.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                back.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                down.val[r][0] = temp[p++ % 12];
            }
            rotateFaceClockwise(right);
        }
        void rightCounterClockwise() {
            // up front down back
            // up: 3 6 9
            // front: 9 6 3
            // down: 1 4 7
            // back: 7 4 1
            char[] temp = new char[12];
            int p = 0;
            for (int r=0; r<3; r++) {
                temp[p++] = up.val[r][2];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = front.val[r][2];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = down.val[r][0];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = back.val[r][0];
            }
            p = 9;
            for (int r=0; r<3; r++) {
                up.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                front.val[r][2] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                down.val[r][0] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                back.val[r][0] = temp[p++ % 12];
            }
            rotateFaceCounterClockwise(right);
        }
        void frontClockwise() {
            /*
            * up right down left
            * up: 7 8 9
            * right: 1 4 7
            * down: 9 8 7
            * left: 9 6 3
            * */
            char[] temp = new char[12];
            int p = 0;
            for (int c=0; c<3; c++) {
                temp[p++] = up.val[2][c];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = right.val[r][0];
            }
            for (int c=2; c>=0; c--) {
                temp[p++] = down.val[2][c];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = left.val[r][2];
            }
            p = 9;
            for (int c=0; c<3; c++) {
                up.val[2][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                right.val[r][0] = temp[p++ % 12];
            }
            for (int c=0; c<3; c++) {
                down.val[2][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                left.val[r][2] = temp[p++ % 12];
            }
            rotateFaceClockwise(front);
        }
        void frontCounterClockwise() {
            /*
             * up left down right
             * up: 9 8 7
             * left: 9 6 3
             * down: 7 8 9
             * right: 1 4 7
             * */
            char[] temp = new char[12];
            int p = 0;
            for (int c=2; c>=0; c--) {
                temp[p++] = up.val[2][c];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = left.val[r][2];
            }
            for (int c=0; c<3; c++) {
                temp[p++] = down.val[2][c];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = right.val[r][0];
            }
            p = 9;
            for (int c=0; c<3; c++) {
                up.val[2][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                left.val[r][2] = temp[p++ % 12];
            }
            for (int c=0; c<3; c++) {
                down.val[2][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                right.val[r][0] = temp[p++ % 12];
            }
            rotateFaceCounterClockwise(front);
        }
        void backClockwise() {
            /*
             * up left down right
             * up: 3 2 1
             * left: 7 4 1
             * down: 1 2 3
             * right: 3 6 9
             * */
            char[] temp = new char[12];
            int p = 0;
            for (int c=2; c>=0; c--) {
                temp[p++] = up.val[0][c];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = left.val[r][0];
            }
            for (int c=0; c<3; c++) {
                temp[p++] = down.val[0][c];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = right.val[r][2];
            }
            p = 9;
            for (int c=0; c<3; c++) {
                up.val[0][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                left.val[r][0] = temp[p++ % 12];
            }
            for (int c=0; c<3; c++) {
                down.val[0][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                right.val[r][2] = temp[p++ % 12];
            }
            rotateFaceClockwise(back);
        }
        void backCounterClockwise() {
            /*
             * up right down left
             * up: 1 2 3
             * right: 3 6 9
             * down: 3 2 1
             * left: 7 4 1
             * */
            char[] temp = new char[12];
            int p = 0;
            for (int c=0; c<3; c++) {
                temp[p++] = up.val[0][c];
            }
            for (int r=0; r<3; r++) {
                temp[p++] = right.val[r][2];
            }
            for (int c=2; c>=0; c--) {
                temp[p++] = down.val[0][c];
            }
            for (int r=2; r>=0; r--) {
                temp[p++] = left.val[r][0];
            }
            p = 9;
            for (int c=0; c<3; c++) {
                up.val[0][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                right.val[r][2] = temp[p++ % 12];
            }
            for (int c=0; c<3; c++) {
                down.val[0][c] = temp[p++ % 12];
            }
            for (int r=0; r<3; r++) {
                left.val[r][0] = temp[p++ % 12];
            }
            rotateFaceCounterClockwise(back);
        }
        void rotateFaceCounterClockwise(Face face) {
            char[] temp = new char[9];
            int p = 0;
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    temp[p++] = face.val[i][j];
                }
            }
            p = 0;
            for (int c=0; c<3; c++) {
                for (int r=2; r>=0; r--) {
                    face.val[r][c] = temp[p++];
                }
            }
        }
        void rotateFaceClockwise(Face face) {
            char[] temp = new char[9];
            int p = 0;
            for (int i=0; i<3; i++) {
                for (int j=0; j<3; j++) {
                    temp[p++] = face.val[i][j];
                }
            }
            p = 0;
            for (int c=2; c>=0; c--) {
                for (int r=0; r<3; r++) {
                    face.val[r][c] = temp[p++];
                }
            }
        }
    }

}
