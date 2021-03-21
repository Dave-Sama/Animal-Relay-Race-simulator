package graphics;

import Animals.Animal;
import Mobility.Point;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class MoveAnimal {
    private Animal[] myAnimal;
    private AddImage myImage;
    private String selectedType;
    private final java.util.Timer[] t = new Timer[5];
    private int animalSize;
    private JFrame frame;
    private final DefaultTableModel model;
    private String tournamentType;
    private int[] animalGroup;

    private static int counter = 0;
    private static int curr = 1;


    public MoveAnimal(AddImage myImage, Animal[] myAnimal, String selectedType, int animalSize, JFrame frame, DefaultTableModel model, String tournamentType, int[] animalGroup) {
        this.myAnimal = myAnimal;
        this.myImage = myImage;
        this.selectedType = selectedType;
        this.animalSize = animalSize;
        this.frame = frame;
        this.model = model;
        this.tournamentType = tournamentType;
        this.animalGroup = animalGroup;
    }

    /*
    StartMoving method can identify which animal the system need to move
     */
    public void StartMoving() throws IOException {
        if (selectedType.equals("Terrestrial Animal")) {
            if (!myAnimal[animalSize - 1].getClass().getSimpleName().equals("Alligator")) {
                StartMovingTerrestrial();
            } else {
                StartMovingTerrestrialAlligator();
            }
        } else if (selectedType.equals("Water Animal")) {
            if (myAnimal[animalSize - 1].getClass().getSimpleName().equals("Alligator")) {
                StartMovingWaterAlligator();
            } else {
                StartMovingWater();
            }
        } else {
            StartMovingAir();
        }
    }

    /*
StartMovingTerrestrial method is in charge to move the terrestrial animals that are not Alligators
 */
    public void StartMovingTerrestrial() throws IOException {
        if (tournamentType.equals("Regular competition")) {
            myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg2());
            myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg3());
            myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg4());
            int curr = this.myImage.getImgSize() - 4;
            double speed = myAnimal[animalSize - 1].getSpeed();
            TimerTask[] task = new TimerTask[4];
            task[0] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[0]) {
                        double x = myAnimal[animalSize - 1].getLocation().x;
                        double y = myAnimal[animalSize - 1].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalSize - 1].getCurrEnergy();
                        int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalSize - 1].Eat();
                            System.out.println("energy level: " + energy);
                            flag = energy > 0 && energy - energyEater >= 0;
                            if (x <= 1000 && flag) {
                                x = x + speed;
                                myAnimal[animalSize - 1].move(new Point(x, y));
                                myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                            } else if (x > 1000) {
                                myImage.getMyImages()[0].remove(myImage.getMyImages()[curr]);
                                myImage.getMyImages()[0].validate();
                                myImage.getMyImages()[0].repaint();
                                task[0].cancel();
                                t[0].schedule(task[1], 50, 100);
                            }
                        }
                    }
                }
            };

            task[1] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[1]) {
                        double x = myAnimal[animalSize - 1].getLocation().x;
                        double y = myAnimal[animalSize - 1].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalSize - 1].getCurrEnergy();
                        int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalSize - 1].Eat();
                            System.out.println("energy level: " + energy);
                            if (energy > 0 && energy - energyEater >= 0) {
                                flag = true;
                            } else {
                                flag = false;
                                frame.validate();
                                frame.repaint();
                            }
                            if (y < 730 && flag) {
                                y = y + speed;
                                myImage.getMyImages()[curr + 1].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);

                            } else if (y >= 730) {
                                task[1].cancel();
                                myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 1]);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                                t[0].schedule(task[2], 50, 100);
                            }
                        }
                    }
                }
            };

            task[2] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[2]) {
                        double x = myAnimal[animalSize - 1].getLocation().x;
                        double y = myAnimal[animalSize - 1].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalSize - 1].getCurrEnergy();
                        int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalSize - 1].Eat();
                            System.out.println("energy level: " + energy);
                            flag = energy > 0 && energy - energyEater >= 0;
                            frame.validate();
                            frame.repaint();
                            if (x > 0 && flag) {
                                x = x - speed;
                                myImage.getMyImages()[curr + 2].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                            } else if (x <= 0) {
                                task[2].cancel();
                                myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 2]);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                                t[0].schedule(task[3], 50, 100);
                            }
                        }
                    }
                }
            };

            task[3] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[3]) {
                        double x = myAnimal[animalSize - 1].getLocation().x;
                        double y = myAnimal[animalSize - 1].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalSize - 1].getCurrEnergy();
                        int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalSize - 1].Eat();
                            System.out.println("energy level: " + energy);
                            if (energy > 0 && energy - energyEater >= 0) {
                                flag = true;
                            } else {
                                flag = false;
                            }
                            if (y > 0 && flag == true) {
                                y = y - speed;
                                myImage.getMyImages()[curr + 3].setBounds((int) x, (int) y, 100, 100);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                                myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                            } else if (y <= 0) {
                                task[3].cancel();
                                myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 3]);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                            }
                        }
                    }
                }
            };

            t[0] = new java.util.Timer();
            t[0].schedule(task[0], 50, 100);
        } else {
            double speed = myAnimal[animalSize - 1].getSpeed();
            TimerTask[] task = new TimerTask[4];
            task[0] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[0]) {
                        double x = myAnimal[animalGroup[0]].getLocation().x;
                        double y = myAnimal[animalGroup[0]].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalGroup[0]].getCurrEnergy();
                        int energyEater = myAnimal[animalGroup[0]].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[0].Eat();
                            System.out.println("energy level: " + energy);
                            if (energy > 0 && energy - energyEater >= 0) {
                                flag = true;
                            } else {
                                flag = false;
                            }
                            if (x <= 1000 && flag) {
                                System.out.println(Thread.currentThread().getName());
                                x = x + speed;
                                myAnimal[animalGroup[0]].move(new Point(x, y));
                                myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalGroup[0]].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalGroup[0]].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalGroup[0]].getTotalDistance(), 0, 5);
                            } else if (x > 1000) {
                                //myImage.getMyImages()[0].remove(myImage.getMyImages()[curr]);
                                myImage.getMyImages()[0].validate();
                                myImage.getMyImages()[0].repaint();
                                task[0].cancel();
                                ++counter;
                                curr += 1;
                                t[0].schedule(task[1], 50, 100);
                            }
                        }
                    }
                }
            };

            task[1] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[1]) {
                        double x = myAnimal[animalGroup[1]].getLocation().x;
                        double y = myAnimal[animalGroup[1]].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalGroup[1]].getCurrEnergy();
                        int energyEater = myAnimal[animalGroup[1]].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalGroup[1]].Eat();
                            System.out.println("energy level: " + energy);
                            if (energy > 0 && energy - energyEater >= 0) {
                                flag = true;
                            } else {
                                flag = false;
                                frame.validate();
                                frame.repaint();
                            }
                            if (y < 730 && flag) {
                                y = y + speed;
                                myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalGroup[1]].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalGroup[1]].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalGroup[1]].getTotalDistance(), 0, 5);

                            } else if (y >= 730) {
                                task[1].cancel();
                                //myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 1]);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                                ++counter;
                                curr += 1;
                                t[0].schedule(task[2], 50, 100);
                            }
                        }
                    }
                }
            };

            task[2] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[2]) {
                        double x = myAnimal[animalGroup[2]].getLocation().x;
                        double y = myAnimal[animalGroup[2]].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalGroup[2]].getCurrEnergy();
                        int energyEater = myAnimal[animalGroup[2]].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[2].Eat();
                            System.out.println("energy level: " + energy);
                            flag = energy > 0 && energy - energyEater >= 0;
                            frame.validate();
                            frame.repaint();
                            if (x > 0 && flag) {
                                x = x - speed;
                                myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                                frame.validate();
                                frame.repaint();
                                myAnimal[animalGroup[2]].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalGroup[2]].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalGroup[2]].getTotalDistance(), 0, 5);
                            } else if (x <= 0) {
                                task[2].cancel();
                                //myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 2]);
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                                ++counter;
                                curr += 1;
                                t[0].schedule(task[3], 50, 100);
                            }
                        }
                    }
                }
            };

            task[3] = new TimerTask() {
                @Override
                public void run() {
                    synchronized (task[3]) {
                        double x = myAnimal[animalGroup[3]].getLocation().x;
                        double y = myAnimal[animalGroup[3]].getLocation().y;
                        boolean flag = false;
                        int energy = myAnimal[animalGroup[3]].getCurrEnergy();
                        int energyEater = myAnimal[animalGroup[3]].getEnergyPerMeter();
                        if (energy > 0) {
                            energy -= energyEater;
                            myAnimal[animalGroup[3]].Eat();
                            System.out.println("energy level: " + energy);
                            if (energy > 0 && energy - energyEater >= 0) {
                                flag = true;
                            } else {
                                flag = false;
                            }
                            if (y > 0 && flag == true) {
                                y = y - speed;
                                myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                                myImage.getMyImages()[animalGroup[3]].revalidate();
                                myImage.getMyImages()[animalGroup[3]].repaint();
                                myAnimal[animalGroup[3]].setLocation(new Point((int) x, (int) y));
                                model.setValueAt(energy, 0, 4);
                                model.setValueAt(myAnimal[animalGroup[3]].GetAddedEnergy(), 0, 6);
                                model.setValueAt(myAnimal[animalGroup[3]].getTotalDistance(), 0, 5);
                            } else if (y <= 0) {
                                task[3].cancel();
                                //myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 3]);
                                ++counter;
                                curr += 1;
                                myImage.getMyImages()[0].revalidate();
                                myImage.getMyImages()[0].repaint();
                            }
                        }
                    }
                }
            };

            t[0] = new java.util.Timer();
            t[0].schedule(task[0], 50, 100);
        }
    }

    /*
    StartMovingTerrestrialAlligator method is in charge to move the terrestrial animals that are Alligators
     */
    public void StartMovingTerrestrialAlligator() throws IOException {

        myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg2());
        int curr = this.myImage.getImgSize() - 2;
        double speed = myAnimal[animalSize - 1].getSpeed();
        TimerTask[] task = new TimerTask[2];
        task[0] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[0]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x < 1000 && flag == true) {
                            x = x + speed;
                            myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else {
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                            task[0].cancel();
                            t[1].schedule(task[1], 50, 100);
                        }
                    }
                }
            }
        };

        task[1] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[1]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x > 0 && flag) {
                            x = x - speed;
                            myImage.getMyImages()[curr + 1].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else {
                            task[1].cancel();
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 1]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                        }
                    }
                }
            }
        };
        t[1] = new java.util.Timer();
        t[1].schedule(task[0], 50, 100);
    }

    /*
    StartMovingWater method is in charge of movig the water animals that are not alligtors
     */
    public void StartMovingWater() throws IOException {
        myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg2());
        int curr = this.myImage.getImgSize() - 2;
        double speed = myAnimal[animalSize - 1].getSpeed();
        TimerTask[] task = new TimerTask[2];
        task[0] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[0]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x < 1000 && flag) {
                            x = x + speed;
                            myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else if (x >= 1000) {
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                            task[0].cancel();
                            t[2].schedule(task[1], 50, 100);
                        }
                    }
                }
            }
        };

        task[1] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[1]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x > 0 && flag) {
                            x = x - speed;
                            myImage.getMyImages()[curr + 1].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else if (x <= 0) {
                            task[1].cancel();
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 1]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();

                        }
                    }
                }
            }
        };
        t[2] = new java.util.Timer();
        t[2].schedule(task[0], 100, 100);
    }

    /*
    StartMovingWaterAlligator method is in charge of movig the water animals that are alligtors
     */
    public void StartMovingWaterAlligator() throws IOException {
        myImage.AddImg(null, true, myAnimal[animalSize - 1].getImg2());
        int curr = this.myImage.getImgSize() - 2;
        double speed = myAnimal[animalSize - 1].getSpeed();
        TimerTask[] task = new TimerTask[2];
        task[0] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[0]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x < 1000 && flag) {
                            x = x + speed;
                            myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else {
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                            task[0].cancel();
                            t[3].schedule(task[1], 100, 100);
                        }
                    }
                }
            }
        };

        task[1] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[1]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        flag = energy > 0 && energy - energyEater >= 0;
                        if (x > 0 && flag) {
                            x = x - speed;
                            myImage.getMyImages()[curr + 1].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else {
                            task[1].cancel();
                            myImage.getMyImages()[0].remove(myImage.getMyImages()[curr + 1]);
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                        }
                    }
                }
            }
        };
        t[3] = new java.util.Timer();
        t[3].schedule(task[0], 50, 100);
    }

    /*
    StartMovingAir method is in charge of moving the air animals
     */
    public void StartMovingAir() {
        int curr = this.myImage.getImgSize() - 1;
        double speed = myAnimal[animalSize - 1].getSpeed();
        TimerTask[] task = new TimerTask[2];
        task[0] = new TimerTask() {
            @Override
            public void run() {
                synchronized (task[0]) {
                    double x = myAnimal[animalSize - 1].getLocation().x;
                    double y = myAnimal[animalSize - 1].getLocation().y;
                    boolean flag = false;
                    int energy = myAnimal[animalSize - 1].getCurrEnergy();
                    int energyEater = myAnimal[animalSize - 1].getEnergyPerMeter();
                    if (energy > 0) {
                        energy -= energyEater;
                        myAnimal[animalSize - 1].Eat();
                        System.out.println("energy level: " + energy);
                        if (energy > 0 && energy - energyEater >= 0) {
                            flag = true;
                        } else {
                            flag = false;
                        }
                        if (x < 1000) {
                            x = x + speed;
                            myImage.getMyImages()[curr].setBounds((int) x, (int) y, 100, 100);
                            frame.validate();
                            frame.repaint();
                            myAnimal[animalSize - 1].setLocation(new Point((int) x, (int) y));
                            model.setValueAt(energy, 0, 4);
                            model.setValueAt(myAnimal[animalSize - 1].GetAddedEnergy(), 0, 6);
                            model.setValueAt(myAnimal[animalSize - 1].getTotalDistance(), 0, 5);
                        } else {
                            myImage.getMyImages()[0].revalidate();
                            myImage.getMyImages()[0].repaint();
                            task[0].cancel();
                        }
                    }
                }
            }
        };

        t[4] = new Timer();
        t[4].schedule(task[0], 50, 100);
    }

    public void setMyAnimal(Animal[] myAnimal) {
        this.myAnimal = myAnimal;
    }

    public void setmyImage
            (AddImage myImage
            ) {
        this.myImage
                = myImage
        ;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
}
