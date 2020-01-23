package domain.mazes;

import org.sikuli.script.Location;
import org.sikuli.script.Mouse;
import org.sikuli.script.Pattern;
import org.sikuli.script.Region;

import controllers.Modules;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class Maze {


    public Cell locator1, locator2;
    public Cell startPoint;
    public Cell endPoint;
    public Cell current;
    public ArrayList<Cell> cells = new ArrayList<Cell>();
    public Random rand = new Random();
    public Stack<Cell> currentPath = new Stack<Cell>();
    public Maze() {

        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                cells.add(new Cell(r,c));
            }
        }

    }
    public Cell checkNeighbors() {

        ArrayList<Cell> neighbors = new ArrayList<Cell>();
        ArrayList<Cell> possibleNeighbors = new ArrayList<Cell>();
        if (current.walls.get(0) == 0) {
            possibleNeighbors.add(cells.get(cells.indexOf(current) - 6));
        }
        if (current.walls.get(1) == 0) {
            possibleNeighbors.add(cells.get(cells.indexOf(current)+1));
        }
        if (current.walls.get(2) == 0) {
            possibleNeighbors.add(cells.get(cells.indexOf(current)+6));
        }
        if (current.walls.get(3) == 0) {
            possibleNeighbors.add(cells.get(cells.indexOf(current)-1));
        }

        for (Cell c : possibleNeighbors) {
            if (!c.visited) {
                neighbors.add(c);
            }
        }
        if (neighbors.size() == 0) {
            return null;
        }
        else{
            return neighbors.get(rand.nextInt(neighbors.size()));
        }


    }

    public Stack<Cell> solveMaze() {
        if (current == endPoint) {
            currentPath.push(current);

        }else {
            System.out.println("Current coordinates: " + (current.col) + ", " + (current.row));
            Cell neighbor = checkNeighbors();

            if (neighbor != null) {
                System.out.println((neighbor.col) + ", " + (neighbor.row));
                current.visited = true;
                currentPath.push(current);
                current = neighbor;
            }else if (!currentPath.empty()) {
                current.visited = true;
                current = currentPath.pop();
            }else {
                System.out.println("something is going wrong in the solver?");
            }

            solveMaze();
        }
            return currentPath;




    }

    public Cell getStart() throws InterruptedException {
    	Modules mod = new Modules();
    	Region maze = new Region(mod.selected.getCenter().left(97).x,mod.selected.getCenter().above(76).y,155,154);
        maze.setRaster(6,6);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                Location currentLoc =  maze.getCell(r,c).getCenter();
                if (currentLoc.getColor().getRed() > 200 && currentLoc.getColor().getGreen() > 200 && currentLoc.getColor().getBlue() > 200) {
                    System.out.println("Start is at : " + cells.get(r*6+c).col+", "+cells.get(r*6+c).row);
                    current = cells.get(r*6+c);
                    return cells.get(r*6+c);
                }

                Thread.sleep(100);

            }
        }
        System.out.println("NO START FOUND");
        return null;
    }

    public Cell getFinish() throws InterruptedException {
    	Modules mod = new Modules();
    	Region maze = new Region(mod.selected.getCenter().left(97).x,mod.selected.getCenter().above(76).y,155,154);
        maze.setRaster(6,6);
        for (int r = 0; r < 6; r++) {
            for (int c = 0; c < 6; c++) {
                Location current = maze.getCell(r,c).getCenter();

                if (current.getColor().getRed() > 200 && current.getColor().getGreen() < 50 && current.getColor().getBlue() < 50) {
                    System.out.println("Finish is at : " + cells.get(r*6+c).col+", "+cells.get(r*6+c).row);
                    return cells.get(r*6+c);
                }

                Thread.sleep(100);
            }
        }
        System.out.println("NO FINISH FOUND");
        return null;
    }






}
