package controllers;

import domain.mazes.Cell;
import domain.mazes.Maze;
import org.sikuli.basics.Settings;
import org.sikuli.script.Location;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import java.util.ArrayList;
import java.util.Stack;

public class Mazes {

    public ArrayList<Maze> allMazes = new ArrayList<Maze>();
    Pattern locator = new Pattern("mazeLocator.png").mask().similar(0.90);

    /*

        Uhhh, this is a *very* long string that I will try to explain.

        So there are 9 mazes, with 36 cells each. We know where all of the walls are for each of those cells, i.e. whether or not they have a top, right, bottom, or left wall(s).
        The string below is a 1 bit representation of exactly that. Each four bits represent a single cell within a single maze.
        More specifically, each group of 4 bits states if that cell has a wall in each of the four possible positions.
        For example, a group of "0011" would signal a cell with no top wall, no right wall, a bottom wall, and a left wall.
        This repeats 36 times to fill each cell in the maze, and *that* process is used 9 times to populate every maze.

    */

    public String mazeWalls = "100110101100100110101110010110010110001110101100010100111100100110100100010110110010011010110100000110101100100111100101001111100011011010110110101110001110100110001110100101101001011000111100010110010110100110100100000101101001011011010101010111010101100101100101011100110110001110100110100110101100110110011100011111010101001101100101100101000101100111000101010101010101010101010101010100110110010101010101001110101010011000110110100111001011101010101100010101011001101010100100010100110110100111100101010110111010001010100100000110101010101011000101001110101110101101100111101110101010101010001100100110101010100001100111000111001011011010011100010100111010110001110101010110011010000111100101011100111010101010100110110110011100101110001100010101010101100101100101000101100111010110010110001111001001010001011101100101100111010100110100001110101010011010110110100110101010110010011100010110011110001101100101001101101001111010010110100111000001101001101101010101110011101011000101001110101010101000100110110110011010110010011100000100101110001101100101010110011010101011000101010100111100101100100110010111010011101010101110001100101010101010101110110110011010101010001100010101011001111001010101000100100110100101100101010111011001011010110100010101010101100111000111001101100011011000111110";


    public void createMazes() {

        for (int m = 0; m < 9; m++) {
            allMazes.add(new Maze());
        }

        // Creates both locators for each of the 9 mazes
        allMazes.get(0).locator1 = allMazes.get(0).cells.get(6);
        allMazes.get(0).locator2 = allMazes.get(0).cells.get(17);
        allMazes.get(1).locator1 = allMazes.get(1).cells.get(10);
        allMazes.get(1).locator2 = allMazes.get(1).cells.get(19);
        allMazes.get(2).locator1 = allMazes.get(2).cells.get(21);
        allMazes.get(2).locator2 = allMazes.get(2).cells.get(23);
        allMazes.get(3).locator1 = allMazes.get(3).cells.get(0);
        allMazes.get(3).locator2 = allMazes.get(3).cells.get(18);
        allMazes.get(4).locator1 = allMazes.get(4).cells.get(16);
        allMazes.get(4).locator2 = allMazes.get(4).cells.get(33);
        allMazes.get(5).locator1 = allMazes.get(5).cells.get(4);
        allMazes.get(5).locator2 = allMazes.get(5).cells.get(26);
        allMazes.get(6).locator1 = allMazes.get(6).cells.get(1);
        allMazes.get(6).locator2 = allMazes.get(6).cells.get(31);
        allMazes.get(7).locator1 = allMazes.get(7).cells.get(3);
        allMazes.get(7).locator2 = allMazes.get(7).cells.get(20);
        allMazes.get(8).locator1 = allMazes.get(8).cells.get(8);
        allMazes.get(8).locator2 = allMazes.get(8).cells.get(24);

        // Assigns all of the cells from our omega string above to each of the respective cell objects within each maze.
        for (int m = 0; m < allMazes.size(); m++) {
            for (int c = 0; c < allMazes.get(m).cells.size(); c++) {
                allMazes.get(m).cells.get(c).walls.add(Integer.valueOf(mazeWalls.substring((144*m)+c*4,((144*m)+c*4)+1)));

                allMazes.get(m).cells.get(c).walls.add(Integer.valueOf(mazeWalls.substring((144*m)+c*4+1,((144*m)+c*4)+2)));

                allMazes.get(m).cells.get(c).walls.add(Integer.valueOf(mazeWalls.substring((144*m)+c*4+2,((144*m)+c*4)+3)));

                allMazes.get(m).cells.get(c).walls.add(Integer.valueOf(mazeWalls.substring((144*m)+c*4+3,((144*m)+c*4)+4)));

            }
        }

    }

    public void fullSolution() throws InterruptedException {
    	Modules mod = new Modules();
        Maze maze = identifyMaze();
        maze.startPoint = maze.getStart();
        maze.endPoint = maze.getFinish();
        Stack<Cell> solution = maze.solveMaze();
        System.out.println("End is at : " + maze.endPoint.col+ ", " + maze.endPoint.row);
        System.out.println(solution.size());
        Location up = mod.selected.getCenter().above(115);
        Location right = mod.selected.getCenter().right(120);
        Location down = mod.selected.getCenter().below(115);
        Location left = mod.selected.getCenter().left(120);
        for (int c = 0; c < solution.size()-1; c++) {
            if (solution.get(c+1).row > solution.get(c).row) {
                down.click();
                System.out.println("Move DOWN");
            }
            else if (solution.get(c+1).row < solution.get(c).row) {
                up.click();
                System.out.println("Move UP");
            }else if (solution.get(c+1).col > solution.get(c).col) {
                System.out.println("Move RIGHT");
                right.click();
            }else if (solution.get(c+1).col < solution.get(c).col) {
                System.out.println("Move LEFT");
                left.click();

            }
        }

    }


    public Maze identifyMaze() {

        Modules mod = new Modules();

        Region maze = new Region(mod.selected.getCenter().left(97).x,mod.selected.getCenter().above(76).y,155,154);
        Integer row = 0;
        Integer col = 0;
        maze.setRaster(6, 6);
        Settings.AutoWaitTimeout = 0.2f;
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                if (maze.getCell(r, c).has(locator)) {
                    System.out.println("Locator found at : " + c + ", " + r);
                    row = r;
                    col = c;
                    for (Maze m : allMazes) {
                        if (m.locator1.row == row && m.locator1.col == col) {
                            System.out.println("Maze is number : " + (allMazes.indexOf(m)+1));
                            return m;
                        }
                        else if (m.locator2.row == row && m.locator2.col == col) {
                            System.out.println("Maze is number : " + (allMazes.indexOf(m)+1));
                            return m;
                        }
                    }
                }
            }
        }

        System.out.println("No maze match was found...");
        return null;
    }

}
