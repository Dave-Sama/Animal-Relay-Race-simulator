package graphics;//package graphics;

import Animals.*;
import Mobility.Point;
import threads.AnimalThread;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Objects;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
this class represent the race
 */
public class CompetitionFrame extends JFrame implements ActionListener {

    private CreateInstanceAnimal newAnimal;
    private final JTable table;
    private final ZooPanel myZoo;
    //                                                       [0]                                                [1]                              [2]                  [3]                                [4]                                [5]                             [6]                                [7]               [8]
    private final String[][] names = {{"Terrestrial Animal", "Air Animal", "Water Animal"}, {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6"}, {"cat1", "cat2"}, {"snake1", "snake2", "snake3"}, {"alligator1", "alligator2", "alligator3"}, {"whale1", "whale2"}, {"dolphin1", "dolphin2", "dolphin3"}, {"eagle1", "eagle2", "eagle3"}, {"pigeon1"}};
    private final Container pane;
    private String selectedType;

    private final DefaultTableModel model;
    private final JScrollPane tablepPane;

    private Vector<AnimalThread> myThreadVector = new Vector<AnimalThread>();
    private Animal[] myAnimal;
    private int animalSize = 0;
    private AddImage myImage = new AddImage();
    private DisplayAnimal myDisplay = new DisplayAnimal();
    private Vector<CreateInstanceAnimal> ciaVector;
    private Vector<Thread> myRegularThread;
    private Vector<Thread> myCuriorThread;
    private Thread[] t = new Thread[5];
    private MoveAnimal myMovement;
    private String tournamentType;
    static int counter = 0;
    static int threadIndex = 0;
    private int[] animalGroup = {0, 1, 2, 3};

    /*
    CompetitionFrame default Constructor
    here we're setting up the backgroud, relevant panels and menu bars + initializing stuffs for certain operations.
     */
    public CompetitionFrame() throws IOException {

        setSize(1118, 950);
        setTitle("Competition");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(true);
        pane = this.getContentPane();
        ZooMenuPanel myMenu = new ZooMenuPanel(this);
        myZoo = new ZooPanel(this);
        ciaVector = new Vector<CreateInstanceAnimal>();
        myRegularThread = new Vector<Thread>();
        myCuriorThread = new Vector<Thread>();

        String backgroundImage = "./src/graphicsAnimals/competitionBackground.png";
        myImage.AddImg(backgroundImage, false, null);
        this.myImage.getMyImages()[0].setLayout(null);
        pane.add(this.myImage.getMyImages()[0], BorderLayout.CENTER);

        table = new JTable();
        table.setRowHeight(20);
        model = new DefaultTableModel();
        String[] columns = {"Animal", "Category", "Type", "Speed", "Energy Amount", "Distance", "Energy consumption"};
        model.setColumnIdentifiers(columns);
        table.setModel(model);
        tablepPane = new JScrollPane(table);


        setVisible(true);

    }


    /*
    action listener that works for the buttons, each button leads to unique funtionality.
     */
    @Override
    public void actionPerformed(ActionEvent e) {

        String keyWord = e.getActionCommand();

        switch (keyWord) {
            case "Exit" -> {
                System.exit(0);
            }
            case "Help" -> {
                JOptionPane.showMessageDialog(this, "Home Work 2 \nGUI");
            }
            case "Competition" -> {
                createCompetition();
            }
            case "Add Animal" -> {

                try {
                    AddAnimal();
                } catch (IOException | InterruptedException ioException) {
                    ioException.printStackTrace();
                }
            }
            case "Clear" -> {
                ClearAnimal();
            }
            case "Eat" -> {
                try {
                    Eat();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
            }
            case "Info" -> CreateInfo();

        }

    }

    /*
    AddCopetitior is a method that in charge of the field "myAnimal" -> each time the user choose to create a new animal,
    this method is adding this animal to an array of animal, so i can use it later on.
     */
    private void AddCompetitor(Animal animal) {

        model.addRow(new Object[]{animal.getName(), animal.getType(), newAnimal.getAnimalType1(), animal.getSpeed(), animal.getCurrEnergy(), animal.getTotalDistance(), animal.GetAddedEnergy()});

        System.out.println(animal.toString());
        Animal[] temp;
        if (this.animalSize > 0) {
            temp = new Animal[this.myAnimal.length];
            for (int i = 0; i < this.myAnimal.length; ++i) {
                temp[i] = this.myAnimal[i];
            }
            this.animalSize += 1;
            this.myAnimal = new Animal[this.animalSize];
            for (int i = 0; i < temp.length; ++i) {
                this.myAnimal[i] = temp[i];
            }
            this.myAnimal[this.animalSize - 1] = animal;

        } else {
            this.myAnimal = new Animal[1];
            myAnimal[0] = animal;
            this.animalSize += 1;
        }


    }

    /*
    Eat() method can open a new JDialog window, and in this window, the user can input the amount of energy he whats to give
    to his animal.
    +
    if the animal is already displayed on the screen and stopped, we need to feed it so it continue to move.
     */
    private void Eat() throws Exception {
        if (this.myImage.getImgSize() > 1) {
            JDialog myDialog = new JDialog(this, "Feed Animal");
            myDialog.setSize(250, 250);
            myDialog.setLayout(null);
            JLabel label12 = new JLabel("Choose animal:");
            label12.setBounds(10, 15, 100, 20);
            myDialog.add(label12);

            String[] option = new String[animalSize];
            for (int i = 0; i < animalSize; ++i) {
                option[i] = myAnimal[i].getSkin() + " (" + (i + 1) + ")";
            }
            JComboBox<String> myPick = new JComboBox<>(option);
            myPick.setBounds(110, 15, 90, 20);
            myDialog.add(myPick);

            JLabel label21 = new JLabel("Feed energy:");
            label21.setBounds(10, 45, 100, 20);
            myDialog.add(label21);

            JTextField f1 = new JTextField();
            f1.setBounds(110, 45, 50, 20);
            myDialog.add(f1);
            f1.setEnabled(false);

            JButton myButton = new JButton("Accept");
            myButton.setBounds(53, 80, 80, 25);
            myDialog.add(myButton);
            myButton.setEnabled(false);

            myPick.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    f1.setEnabled(true);
                    myButton.setEnabled(true);
                    String selectedSkin;
                    int j = 0;
//                    for (int i = 0; i < animalSize; ++i) {
//                            String key = (String) myPick.getSelectedItem();
//                            key = key.substring(0,key.length()-4);
//                        if (myAnimal[i].getSkin().equals(key)) {
                    j = myPick.getSelectedIndex();
//                            selectedSkin = myAnimal[i].getSkin();
////                            j = i;
//                        }
//                    }
                    ImageIcon iconLogo = new ImageIcon(myAnimal[j].getPath1());
                    Image image = iconLogo.getImage();
                    Image modImage = image.getScaledInstance(250, 250, Image.SCALE_SMOOTH);
                    iconLogo = new ImageIcon(modImage);


                    JLabel animalLabel = new JLabel();
                    myDialog.revalidate();
                    myDialog.repaint();

                    animalLabel.setIcon(iconLogo);
                    animalLabel.setBounds(0, 0, 250, 250);

                    myDialog.add(animalLabel);

//                    DrawingComponent bg = null;
//                    try {
//                        bg = new DrawingComponent(myAnimal[j].getPath1(), true);
//                    } catch (IOException ioException) {
//                        ioException.printStackTrace();
//                    }
//                    myDialog.add(bg);
//                    bg.setBounds(15, 5, 250, 250);
//                    myDialog.validate();
//                    myDialog.repaint();

                    int finalJ = j;
                    myButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            try {
                                myAnimal[finalJ].AddEnergy(Integer.parseInt(f1.getText()));
                                myDialog.dispose();
                            } catch (Exception ex) {
                                System.out.println("wrong input");
                            }

                        }
                    });
                }
            });
            myDialog.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "there is not Animal to feed!", "Warning",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /*
    CreateInfo() method is a method that dynamically updated a Jtable each time the animal move.
     */
    private void CreateInfo() {
        JDialog infoDialog = new JDialog(this, "Competitors Information Dialog");
        infoDialog.setLayout(null);
        if (this.animalSize > 0) {
            tablepPane.setLocation(0, 0);
            tablepPane.setBounds(0, 0, 700, 300);
            infoDialog.add(tablepPane);
            infoDialog.setResizable(true);
            table.getColumnModel().getColumn(0).setPreferredWidth(36);
            infoDialog.setSize(tablepPane.getWidth() + 5, tablepPane.getHeight());

            infoDialog.setVisible(true);

        } else {
            System.out.println("no information available");
        }
    }

    /*
    createCompetition() is a method that allow the user to choose which kind of "Competition" he wants, "air, water etc.. "
     */
    private void createCompetition() {

        final String[] names = {"Terrestrial Animal", "Air Animal", "Water Animal"};
        JComboBox<String> myCombo = new JComboBox<>(names);
        JLabel label;


        JDialog myDialog = new JDialog(this, "Add Competition Dialog");

        myDialog.setVisible(true);
        myDialog.setResizable(true);
        myDialog.setSize(400, 150);
        //myDialog.setDefaultCloseOperation(EXIT_ON_CLOSE);
        myDialog.setLayout(null);

        label = new JLabel("Please choose your competition:");
        label.setBounds(15, 5, 300, 40);
        myDialog.add(label);

        myCombo.setBounds(220, 13, 150, 25);

        JLabel label1 = new JLabel("Choose tournament:");
        label1.setBounds(15, 38, 300, 40);
        myDialog.add(label1);
        String[] names1 = {"Regular competition", "Courier competition"};
        JComboBox<String> myCombo1 = new JComboBox<>(names1);
        myCombo1.setBounds(220, 46, 150, 25);
        myDialog.add(myCombo1);

        myCombo1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tournamentType = (String) myCombo1.getSelectedItem();
                myZoo.getAddAnimal().setEnabled(true);
                myDialog.dispose();
            }
        });

        // combo action listener
        myCombo.addActionListener(e -> {
            String key = (String) myCombo.getSelectedItem();
            switch (Objects.requireNonNull(key)) {
                case "Terrestrial Animal" -> {
                    selectedType = (String) myCombo.getSelectedItem();
                }
                case "Water Animal" -> {
                    selectedType = (String) myCombo.getSelectedItem();
                }
                case "Air Animal" -> {
                    selectedType = (String) myCombo.getSelectedItem();
                }
            }
        });


        myDialog.add(myCombo);
    }

    /*
    AddAnimal() is a method that create an instance of the required animal, by filling certain details about a specific animal
    in short about how it does it:
    1. identify the animalType
    2. identify the animalSkin
    3. dynamically display the relevant fields
     */
    // we're in a class that extends from JFrame
    private void AddAnimal() throws IOException, InterruptedException {
        if (tournamentType.equals("Regular competition")) {
            newAnimal = new CreateInstanceAnimal(this, selectedType);
            AddCompetitor(newAnimal.getAnimal());
            myDisplay.DisplayAnimal(newAnimal.path, true, myImage, selectedType, myAnimal, animalSize, tournamentType, newAnimal);
            validate();
            repaint();

            AnimalThread myThread = new AnimalThread(myImage, myAnimal, selectedType, animalSize, this, model, tournamentType, animalGroup);
            //myThreadVector.add(myThread);
            //myThread = null;
            t[threadIndex] = new Thread(myThread);
            t[threadIndex].start();
            ++threadIndex;
            //myRegularThread.add(t);

            //t = null;
            //myRegularThread.get(animalSize-1).start();
//            if(animalSize>1) {
//                for (int i = 0; i < animalSize; ++i) {
//                    t[i].start();
//                }
//            }
//
//            }

        } else {
            newAnimal = new CreateInstanceAnimal(this, selectedType);
            switch (newAnimal.getAnimal().getClass().getSimpleName()) {
                case "Dog" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Dog((Dog) newAnimal.getAnimal()));
                    }
                }
                case "Cat" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Cat((Cat) newAnimal.getAnimal()));
                    }
                }
                case "Snake" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Snake((Snake) newAnimal.getAnimal()));
                    }
                }
                case "Alligator" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Alligator((Alligator) newAnimal.getAnimal()));
                    }
                }
                case "Whale" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Whale((Whale) newAnimal.getAnimal()));
                    }
                }
                case "Dolphin" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Dolphin((Dolphin) newAnimal.getAnimal()));
                    }
                }
                case "Eagle" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Eagle((Eagle) newAnimal.getAnimal()));
                    }
                }
                case "Pigeon" -> {
                    for (int i = 0; i < 4; ++i) {
                        AddCompetitor(new Pigeon((Pigeon) newAnimal.getAnimal()));
                    }
                }


            }
            ciaVector.add(newAnimal);
            myDisplay.DisplayAnimal(newAnimal.path, true, myImage, selectedType, myAnimal, animalSize, tournamentType, newAnimal);
            validate();
            repaint();

            if (animalSize > 4) {
                for (int i = 0; i < animalGroup.length; ++i) {
                    animalGroup[i] += 4;
                }
            }

            AnimalThread myThread = new AnimalThread(myImage, myAnimal, selectedType, animalSize, this, model, tournamentType, animalGroup);
            ExecutorService pool = Executors.newFixedThreadPool(2);

            pool.submit(myThread);
            pool.shutdown();



//            Thread t = new Thread(myThread);
//            myCuriorThread.add(t);
//            t.start();
        }

    }


    /*
    DesignAnimals is a helper method that can decide which element from names need to be taken
    @param : String key
    @return String[] from names;
     */
    private String[] DesignAnimals(String key) {
        switch (key) {
            case "Dog" -> {
                return names[1];
            }
            case "Cat" -> {
                return names[2];
            }
            case "Snake" -> {
                return names[3];
            }
            case "Alligator" -> {
                return names[4];
            }
            case "Whale" -> {
                return names[5];
            }
            case "Dolphin" -> {
                return names[6];
            }
            case "Eagle" -> {
                return names[7];
            }
            case "Pigeon" -> {
                return names[8];
            }
        }
        return new String[]{"kisses"};
    }

    /*
    ClearAnimal is a method can can clear an animal from the screen
     */
    private void ClearAnimal() {
        this.myImage.getMyImages()[0].remove(this.myImage.getMyImages()[this.myImage.getImgSize() - 1]);
        this.myImage.setImgSize(this.myImage.getImgSize() - 1);
        this.myImage.getMyImages()[0].revalidate();
        this.myImage.getMyImages()[0].repaint();
    }

    public static void main(String[] args) throws IOException {
        new CompetitionFrame();
    }
}





