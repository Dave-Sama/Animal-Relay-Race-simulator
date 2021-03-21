package threads;

import Animals.Animal;
import graphics.AddImage;
import graphics.CompetitionFrame;
import graphics.MoveAnimal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Timer;

public class AnimalThread implements Runnable {
    private Animal participant;
    private double neededDistance;
    private Boolean startFlag = true;
    private Boolean finishFlag = false;

    private Animal[] myAnimal;
    private AddImage myImage;
    private String selectedType;
    private int animalSize;
    private JFrame frame;
    private final DefaultTableModel model;
    private MoveAnimal myMove;
    private String tournamentType;
    private int[] animalGroup;


    public AnimalThread(AddImage myImage, Animal[] myAnimal, String selectedType, int animalSize, JFrame frame, DefaultTableModel model, String tournamentType,int[] animalGroup) {
        this.myAnimal = myAnimal;
        this.myImage = myImage;
        this.selectedType = selectedType;
        this.animalSize = animalSize;
        this.frame = frame;
        this.model = model;
        this.tournamentType = tournamentType;
        this.animalGroup = animalGroup;
    }

    @Override
    public void run() {
        synchronized (startFlag) {
            if (!startFlag)
                try {
                    Thread.currentThread().wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupt exception in AnimalThread class");
                }
            if (Thread.interrupted())
                System.out.println(Thread.currentThread().getName() + " interrupted blat");
        }
        synchronized (myAnimal) {
            if (tournamentType.equals("Regular competition")) {
                System.out.println("Thread's name -- " + Thread.currentThread().getName());
                myMove = new MoveAnimal(myImage, myAnimal, selectedType, animalSize, frame, model, tournamentType, animalGroup);
                try {
                    myMove.StartMoving();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else{
                System.out.println("Thread's name -- " + Thread.currentThread().getName());
                myMove = new MoveAnimal(myImage, myAnimal, selectedType, animalSize, frame, model, tournamentType, animalGroup);
                try {
                    myMove.StartMoving();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}