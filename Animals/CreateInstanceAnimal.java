package Animals;

import Mobility.Point;
import Olympics.Medal;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class CreateInstanceAnimal extends JDialog {

    //                                                       [0]                                                [1]                              [2]                  [3]                                [4]                                [5]                             [6]                                [7]               [8]
    private final String[][] names = {{"Terrestrial Animal", "Air Animal", "Water Animal"}, {"dog1", "dog2", "dog3", "dog4", "dog5", "dog6"}, {"cat1", "cat2"}, {"snake1", "snake2", "snake3"}, {"alligator1", "alligator2", "alligator3"}, {"whale1", "whale2"}, {"dolphin1", "dolphin2", "dolphin3"}, {"eagle1", "eagle2", "eagle3"}, {"pigeon1"}};
    private int medalSize = 0;
    public String name;
    protected Gender myGender;
    protected Double weight;
    protected Double speed;
    protected Medal[] medal;
    protected Point position;
    protected boolean castrated;
    protected Snake.Poisonous poison;
    protected String isPoison;
    private String selectedType;
    private String animalType1;
    private String selectedSkin;
    public String path;
    public Animal a;
    private JButton acceptButton;
    private JTextField nameTextField,speedTextField,weightTextField;
    private boolean flag = false;


    public CreateInstanceAnimal(JFrame daddy,String selected) {
        super(daddy,ModalityType.DOCUMENT_MODAL);
        this.selectedType = selected;
        setTitle("Add Animal Dialog");

        this.setResizable(false);
        this.setSize(365, 330);
        this.setLayout(null);

        // NEED TO CHECK IF THE ANIMAL IS FIT TO THE COMPETITION TYPE - SHOULD BE AN EXCPETION
        JLabel animalCat = new JLabel("Choose Animal Category:");
        animalCat.setBounds(15, 7, 150, 20);
        this.add(animalCat);


        JLabel animalType = new JLabel("Choose Animal: ");
        animalType.setBounds(15, 37, 150, 20);
        this.add(animalType);

        JLabel animalDesign = new JLabel("Choose Animal Design: ");
        animalDesign.setBounds(15, 67, 150, 20);
        this.add(animalDesign);

        JLabel ilustration = new JLabel("Illustration: ");
        ilustration.setBounds(15, 97, 150, 20);
        this.add(ilustration);


        JComboBox<String> designCombo = new JComboBox<>();
        designCombo.setBounds(180, 70, 150, 20);
        designCombo.setEnabled(false);
        this.add(designCombo);


        JComboBox<String> myUpdatedCombo = new JComboBox<>();
        myUpdatedCombo.setBounds(180, 40, 150, 20);
        myUpdatedCombo.setEnabled(false);
        this.add(myUpdatedCombo);

        JComboBox<String> myCombo = new JComboBox<>(names[0]);
        myCombo.setBounds(180, 10, 150, 20);
        this.add(myCombo);


        myCombo.addActionListener(e -> {
            myUpdatedCombo.removeAllItems();

            if (!selectedType.equals(Objects.requireNonNull(myCombo.getSelectedItem()))) {
                try {
                    JOptionPane.showMessageDialog(this, "Cannot continue:\n Bad match up.");
                    throw new Exception("bad fit");
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

            } else if (selectedType.equals(Objects.requireNonNull(myCombo.getSelectedItem()))) {
                //CreateAnimal newAnimal= new CreateAnimal();
                int k = 0;
                int m = 0;
                int sum = 0;
                int[] key = InttypesOfAnimalsMenu((String) Objects.requireNonNull(myCombo.getSelectedItem()));
                String[] stringKey = StringtypesOfAnimalsMenu(key);
                assert key != null;
                for (int value : key) {
                    sum += names[value].length;
                }
                String[] animalsTypes = new String[sum];
                while (m < key.length) {
                    for (int j = 0; j < names[key[m]].length; ++j) {
                        animalsTypes[k] = names[key[m]][j];
                        k += 1;
                    }
                    m += 1;
                }
                for (String s : stringKey) {
                    myUpdatedCombo.addItem(s);
                }
                myUpdatedCombo.setEnabled(true);
                myUpdatedCombo.addActionListener(e13 -> {
                    designCombo.removeAllItems();
                    designCombo.setEnabled(true);
                    String[] userPick = DesignAnimals((String) Objects.requireNonNull(myUpdatedCombo.getSelectedItem()));
                    animalType1 = (String) myUpdatedCombo.getSelectedItem();
                    for (String s : userPick) {
                        designCombo.addItem(s);
                    }
                    designCombo.addActionListener(e12 -> {
                        selectedSkin = (String) designCombo.getSelectedItem();
                        //path = "graphicsAnimals/" + selectedSkin + "S.png";
                        path = "C:\\Users\\david\\IdeaProjects\\assignment2 gui\\src\\graphicsAnimals\\" + selectedSkin + "E.png";
                        BufferedImage img = null;
                        assert img != null;
                        try {
                            img = ImageIO.read(new File(path));
                        } catch (IOException k1) {
                            System.out.println("Cannot load image");
                        }

                        ImageIcon iconLogo = new ImageIcon(path);
                        Image image = iconLogo.getImage();
                        Image modImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                        iconLogo = new ImageIcon(modImage);


                        JLabel animalLabel = new JLabel();
                        this.remove(animalLabel);
                        this.revalidate();
                        this.repaint();

                        animalLabel.setIcon(iconLogo);
                        animalLabel.setBounds(15, 105, 200, 200);

                        this.add(animalLabel);


                        JLabel info = new JLabel("Animal's personal info:");
                        info.setBounds(100, 300, 200, 20);
                        this.add(info);

                        JLabel name1 = new JLabel("Name:");
                        name1.setBounds(15, 330, 50, 20);
                        this.add(name1);

                        nameTextField = new JTextField();
                        nameTextField.setBounds(180, 330, 150, 20);
                        nameTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                name = nameTextField.getText();
                            }
                        });
                        this.add(nameTextField);


                        JLabel gender = new JLabel("Gender:");
                        gender.setBounds(15, 360, 50, 20);
                        this.add(gender);

                        String[] genderOptions = new String[]{"Male", "Female", "Hermaphrodite"};

                        JComboBox<String> genderCombo = new JComboBox<>(genderOptions);
                        genderCombo.setBounds(180, 360, 150, 20);
                        genderCombo.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                myGender = Gender.valueOf((String) genderCombo.getSelectedItem());
                            }
                        });
                        this.add(genderCombo);
                        JLabel speed1 = new JLabel("Speed: ");
                        speed1.setBounds(15, 390, 50, 20);
                        this.add(speed1);

                        speedTextField = new JTextField();
                        speedTextField.setBounds(180, 390, 150, 20);

                        speedTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                speed = Double.parseDouble(speedTextField.getText());
                            }
                        });

                        this.add(speedTextField);

                        JLabel weight1 = new JLabel("Weight: ");
                        weight1.setBounds(15, 420, 150, 20);
                        this.add(weight1);

                        weightTextField = new JTextField();
                        weightTextField.setBounds(180, 420, 150, 20);

                        weightTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                weight = Double.parseDouble(speedTextField.getText());
                            }
                        });

                        this.add(weightTextField);

                        JLabel medal1 = new JLabel("Medals: ");
                        medal1.setBounds(15, 450, 150, 20);
                        this.add(medal1);
                        JLabel medal2 = new JLabel();
                        medal2.setBounds(100, 480, 150, 20);
                        this.add(medal2);
                        String[] medalsOptions = new String[]{"no medal", "1", "2", "3", "4", "5"};

                        JComboBox<String> medalsCombo = new JComboBox<>(medalsOptions);
                        medalsCombo.setBounds(180, 450, 150, 20);

                        this.add(medalsCombo);

                        JLabel medal3 = new JLabel("Medal: ");
                        medal3.setBounds(15, 510, 150, 20);
                        medal3.setEnabled(false);
                        this.add(medal3);


                        String[] medalOptions = new String[]{"gold", "silver", "bronze"};

                        JComboBox<String> medalCombo = new JComboBox<>(medalOptions);
                        medalCombo.setBounds(180, 510, 150, 20);
                        medalCombo.setEnabled(false);

                        this.add(medalCombo);


                        JLabel tournament = new JLabel("Tournament: ");
                        tournament.setBounds(15, 540, 150, 20);
                        tournament.setEnabled(false);
                        this.add(tournament);

                        JTextField tournamentTextField = new JTextField();
                        tournamentTextField.setBounds(180, 540, 150, 20);
                        this.add(tournamentTextField);
                        tournamentTextField.setEnabled(false);

                        JLabel year = new JLabel("Year: ");
                        year.setBounds(15, 570, 150, 20);
                        year.setEnabled(false);
                        this.add(year);

                        JTextField yearTextField = new JTextField();
                        yearTextField.setBounds(180, 570, 70, 20);
                        yearTextField.setText("Enter");
                        this.add(yearTextField);

                        medalCombo.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                tournamentTextField.setText("");
                                yearTextField.setText("");
                            }
                        });

                        medalsCombo.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!"no medal".equals((String) medalsCombo.getSelectedItem())) {
                                    medal = new Medal[Integer.parseInt((String) medalsCombo.getSelectedItem())];
                                    medal2.setText("Enter " + medalsCombo.getSelectedItem() + " medals:");

                                    medal3.setEnabled(true);
                                    tournament.setEnabled(true);
                                    year.setEnabled(true);

                                    tournamentTextField.setEnabled(true);
                                    yearTextField.setEnabled(true);
                                    medalCombo.setEnabled(true);
                                    medal2.setVisible(true);

                                }
                            }
                        });

                        JButton acceptMedal = new JButton("Accept");
                        acceptMedal.setBounds(255, 570, 75, 20);
                        yearTextField.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                if (!"".equals(yearTextField.getText()) || !"".equals(tournamentTextField.getText())) {
                                    acceptMedal.setEnabled(true);
                                    int i = Integer.parseInt((String) medalsCombo.getSelectedItem());
                                    if (i > 0) {
                                        --i;
                                    }
                                    medal2.setText("Enter " + i + " medals:");
                                }

                            }
                        });
                        acceptMedal.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                String animalMedal = (String) medalCombo.getSelectedItem();
                                String tournament = (String) tournamentTextField.getText();
                                int year = Integer.parseInt(yearTextField.getText());
                                //cleaning
                                medalCombo.setSelectedIndex(0);
                                tournamentTextField.setText("");
                                yearTextField.setText("");
                                medal[medalSize] = new Medal(Medal.myMedal.valueOf(animalMedal), tournament, year);
                                ++medalSize;
                            }
                        });
                        acceptMedal.setEnabled(false);
                        this.add(acceptMedal);

                        yearTextField.setEnabled(false);

                        acceptButton = new JButton("Accept");
                        // Additional info
                        String animalkey = (String) designCombo.getSelectedItem();
                        animalkey = animalkey.substring(0, animalkey.length() - 1);

                        switch (animalkey) {
                            case "dog" -> {
                                CreateDog();
                            }
                            case "cat" -> {
                                CreateCat();
                            }
                            case "snake" -> {
                                CreateSnake();
                            }
                            case "alligator" -> {
                                CreateAlligaotr();
                            }
                            case "whale" -> {
                                CreateWhale();
                            }
                            case "dolphin" -> {
                                CreateDolphin();
                            }
                            case "eagle" -> {
                                CreateEagle();
                            }
                            case "pigeon" -> {
                                CreatePigeon();
                            }
                            default -> throw new IllegalStateException("Unexpected value: " + name);
                        }

                    });
                });
            }
        });
        this.setVisible(true);
    }

    private int[] InttypesOfAnimalsMenu(String key) {
        switch (key) {
            case "Terrestrial Animal" -> {
                return new int[]{1, 2, 3, 4};
            }
            case "Air Animal" -> {
                return new int[]{7, 8};
            }
            case "Water Animal" -> {
                return new int[]{4, 5, 6};
            }
        }
        return null;
    }

    /*
  StringtypesOfAnimalsMenu method is a helper method that can convert s specific int array to a string array
  @param : int[] key
  @return : new String[]
   */
    private String[] StringtypesOfAnimalsMenu(int[] key) {

        if (Arrays.equals(key, new int[]{1, 2, 3, 4})) {
            return new String[]{"Dog", "Cat", "Snake", "Alligator"};
        } else if (Arrays.equals(key, new int[]{4, 5, 6})) {
            return new String[]{"Whale", "Alligator", "Dolphin"};
        } else {
            return new String[]{"Eagle", "Pigeon"};
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

    private void CreateDog() {
        JLabel breed1 = new JLabel("Breed: ");
        breed1.setBounds(15, 600, 150, 20);
        this.add(breed1);

        JTextField breedTextField = new JTextField();
        breedTextField.setBounds(180, 600, 150, 20);

        this.add(breedTextField);

        JLabel numOfLegs = new JLabel("Number of legs: ");
        numOfLegs.setBounds(15, 630, 150, 20);
        this.add(numOfLegs);

        JTextField numOfLegsTextField = new JTextField();
        numOfLegsTextField.setBounds(180, 630, 150, 20);
        this.add(numOfLegsTextField);

        this.setSize(365, 760);
        acceptButton.setBounds(120, 660, 100, 35);

        this.add(acceptButton);
        acceptButton.setEnabled(true);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String breed = breedTextField.getText();
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                int legs = Integer.parseInt(numOfLegsTextField.getText());
                try {
                    a = new Dog(breed, legs, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 1500, 5);
                    setVisible(false);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });

    }


    /*
 CreateCat is a method that can add the relevant fields to create a cat instance
 @param: String path
 @param: JDialog this
 @param: JTextField nameTextField
 @param: JTextField speedTextField
 @param: JTextField weightTextField
 @param: JButton acceptButton
  */
    private void CreateCat() {
        JLabel castrated1 = new JLabel("Castrated: ");
        castrated1.setBounds(15, 600, 150, 20);
        this.add(castrated1);

        String[] castratedOptions = new String[]{"Yes", "No"};
        JComboBox<String> castratedCombo = new JComboBox<>(castratedOptions);
        castratedCombo.setBounds(180, 600, 150, 20);
        castratedCombo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                castrated = Boolean.parseBoolean((String) castratedCombo.getSelectedItem());
            }
        });
        this.add(castratedCombo);

        JLabel numOfLegs1 = new JLabel("Number of legs: ");
        numOfLegs1.setBounds(15, 630, 150, 20);
        this.add(numOfLegs1);

        JTextField numOfLegsTextField = new JTextField();
        numOfLegsTextField.setBounds(180, 630, 150, 20);
        this.add(numOfLegsTextField);

        acceptButton.setBounds(120, 670, 100, 35);
        this.setSize(365, 770);
        this.add(acceptButton);
        acceptButton.setEnabled(true);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                int legs = Integer.parseInt((String) numOfLegsTextField.getText());
                try {
                    a = new Cat(castrated, legs, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });

    }

    /*
CreateSnake is a method that can add the relevant fields to create a snake instance
@param: String path
@param: JDialog this
@param: JTextField nameTextField
@param: JTextField speedTextField
@param: JTextField weightTextField
@param: JButton acceptButton
*/
    private void CreateSnake() {
        {
            JLabel poisonous = new JLabel("Poisonous: ");
            poisonous.setBounds(15, 600, 150, 20);
            this.add(poisonous);

            String[] poisonousOptions = new String[]{"Yes", "No"};
            JComboBox<String> poisonousCombo = new JComboBox<>(poisonousOptions);
            poisonousCombo.setBounds(180, 600, 150, 20);
            this.add(poisonousCombo);

            JLabel poisonousDegree = new JLabel("Poisonous Degree: ");
            poisonousDegree.setBounds(15, 630, 150, 20);
            poisonousDegree.setEnabled(false);
            this.add(poisonousDegree);

            String[] poisonousDegreeOptions = new String[]{"Low", "Medium", "High"};
            JComboBox<String> poisonousDegreeCombo = new JComboBox<>(poisonousDegreeOptions);
            poisonousDegreeCombo.setBounds(180, 630, 150, 20);
            poisonousDegreeCombo.setEnabled(false);
            poisonousDegreeCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    poison = Snake.Poisonous.valueOf((String) poisonousDegreeCombo.getSelectedItem());
                }
            });
            poisonousCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                    isPoison = (String) poisonousCombo.getSelectedItem();
                    if (isPoison.equals("Yes")) {
                        poisonousDegree.setEnabled(true);
                        poisonousDegreeCombo.setEnabled(true);
                    }
                }
            });

            poisonousDegreeCombo.setEnabled(false);
            this.add(poisonousDegreeCombo);

            poisonousCombo.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("Yes".equals(poisonousCombo.getSelectedItem())) {
                        poisonousDegreeCombo.setEnabled(true);
                    }
                }
            });

            JLabel length = new JLabel("Length: ");
            length.setBounds(15, 660, 150, 20);
            this.add(length);

            JTextField lengthTextField = new JTextField();
            lengthTextField.setBounds(180, 660, 150, 20);
            this.add(lengthTextField);

            acceptButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    name = nameTextField.getText();
                    speed = Double.parseDouble(speedTextField.getText());
                    weight = Double.parseDouble(speedTextField.getText());
                    double length = Double.parseDouble((String) lengthTextField.getText());
                    try {
                        a = new Snake(poison, length, 0, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    setVisible(false);
                    flag = true;

                }
            });

            this.setSize(365, 800);
            acceptButton.setBounds(120, 700, 100, 35);
            this.add(acceptButton);
            acceptButton.setEnabled(true);
        }

    }

    /*
  CreateAlligator is a method that can add the relevant fields to create a Alligator instance
  @param: String path
  @param: JDialog this
  @param: JTextField nameTextField
  @param: JTextField speedTextField
  @param: JTextField weightTextField
  @param: JButton acceptButton
   */
    private void CreateAlligaotr() {
        JLabel areaOfLiving1 = new JLabel("Area of living: ");
        areaOfLiving1.setBounds(15, 600, 150, 20);
        this.add(areaOfLiving1);

        JTextField areaOfLivingTextField = new JTextField();
        areaOfLivingTextField.setBounds(180, 600, 150, 20);

        this.add(areaOfLivingTextField);

        JLabel depth1 = new JLabel("Depth: ");
        depth1.setBounds(15, 630, 150, 20);
        this.add(depth1);

        JTextField depthTextField = new JTextField();
        depthTextField.setBounds(180, 630, 150, 20);
        this.add(depthTextField);

        JLabel numOfLegs1 = new JLabel("Number of legs: ");
        numOfLegs1.setBounds(15, 660, 150, 20);
        this.add(numOfLegs1);

        JTextField numOfLegsTextField = new JTextField();
        numOfLegsTextField.setBounds(180, 660, 150, 20);
        this.add(numOfLegsTextField);

        this.setSize(365, 800);
        acceptButton.setBounds(120, 700, 100, 35);
        this.add(acceptButton);
        acceptButton.setEnabled(true);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                int legs = Integer.parseInt(numOfLegsTextField.getText());
                String areaOfLiving = areaOfLivingTextField.getText();
                double depth = Double.parseDouble(depthTextField.getText());
                try {
                    a = new Alligator(legs, areaOfLiving, depth, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });

    }

    /*
  CreateWhale is a method that can add the relevant fields to create a whale instance
  @param: String path
  @param: JDialog this
  @param: JTextField nameTextField
  @param: JTextField speedTextField
  @param: JTextField weightTextField
  @param: JButton acceptButton
   */
    private void CreateWhale() {

        JLabel depth1 = new JLabel("Depth: ");
        depth1.setBounds(15, 600, 150, 20);
        this.add(depth1);

        JTextField depthTextField = new JTextField();
        depthTextField.setBounds(180, 600, 150, 20);

        this.add(depthTextField);

        JLabel foodType1 = new JLabel("Food type: ");
        foodType1.setBounds(15, 630, 150, 20);
        this.add(foodType1);

        JTextField foodTypeTextField = new JTextField();
        foodTypeTextField.setBounds(180, 630, 150, 20);
        this.add(foodTypeTextField);

        this.setSize(365, 760);
        acceptButton.setBounds(120, 660, 100, 35);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                double depth = Double.parseDouble(depthTextField.getText());
                String foodType = foodTypeTextField.getText();
                try {
                    a = new Whale(foodType, depth, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });
        this.add(acceptButton);
        acceptButton.setEnabled(true);

    }

    /*
CreateDolphin is a method that can add the relevant fields to create a dolphin instance
@param: String path
@param: JDialog this
@param: JTextField nameTextField
@param: JTextField speedTextField
@param: JTextField weightTextField
@param: JButton acceptButton
*/
    private void CreateDolphin() {


        JLabel depth1 = new JLabel("Depth:");
        depth1.setBounds(15, 600, 150, 20);
        this.add(depth1);

        JTextField depthTextField = new JTextField();
        depthTextField.setBounds(180, 600, 150, 20);
        this.add(depthTextField);

        JLabel waterType1 = new JLabel("Water type: ");
        waterType1.setBounds(15, 630, 150, 20);
        this.add(waterType1);

        String[] waterTypeOptions = new String[]{"Sea", "Sweet"};
        JComboBox<String> waterTypeCombo = new JComboBox<>(waterTypeOptions);
        waterTypeCombo.setBounds(180, 630, 150, 20);
        this.add(waterTypeCombo);

        this.setSize(365, 770);
        acceptButton.setBounds(120, 670, 100, 35);
        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                double depth = Double.parseDouble(depthTextField.getText());
                Dolphin.WaterType waterType = Dolphin.WaterType.valueOf((String) waterTypeCombo.getSelectedItem());
                try {
                    a = new Dolphin(waterType, depth, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });
        this.add(acceptButton);
        acceptButton.setEnabled(true);

    }

    /*
CreateEagle is a method that can add the relevant fields to create a eagle instance
@param: String path
@param: JDialog this
@param: JTextField nameTextField
@param: JTextField speedTextField
@param: JTextField weightTextField
@param: JButton acceptButton
*/
    private void CreateEagle() {
        JLabel altitudeOfFlight1 = new JLabel("Altitude of flight:");
        altitudeOfFlight1.setBounds(15, 600, 150, 20);
        this.add(altitudeOfFlight1);

        JTextField altitudeOfFlightTextField = new JTextField();
        altitudeOfFlightTextField.setBounds(180, 600, 150, 20);
        this.add(altitudeOfFlightTextField);

        JLabel wingSpan1 = new JLabel("Wing span:");
        wingSpan1.setBounds(15, 630, 150, 20);
        this.add(wingSpan1);

        JTextField wingSpanTextField = new JTextField();
        wingSpanTextField.setBounds(180, 630, 150, 20);
        this.add(wingSpanTextField);

        this.setSize(365, 770);
        acceptButton.setBounds(120, 670, 100, 35);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                double altitudeOfFlight = Double.parseDouble(altitudeOfFlightTextField.getText());
                double wingSpan = Double.parseDouble(wingSpanTextField.getText());
                try {
                    a = new Eagle(altitudeOfFlight, wingSpan, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });

        this.add(acceptButton);
        acceptButton.setEnabled(true);

    }

    /*
CreatePigeon is a method that can add the relevant fields to create a pigeon instance
@param: String path
@param: JDialog this
@param: JTextField nameTextField
@param: JTextField speedTextField
@param: JTextField weightTextField
@param: JButton acceptButton
*/
    private void CreatePigeon() {
        JLabel family1 = new JLabel("Family:");
        family1.setBounds(15, 600, 150, 20);
        this.add(family1);

        JTextField familyTextField = new JTextField();
        familyTextField.setBounds(180, 600, 150, 20);
        this.add(familyTextField);

        JLabel wingSpan1 = new JLabel("Wing span:");
        wingSpan1.setBounds(15, 630, 150, 20);
        this.add(wingSpan1);

        JTextField wingSpanTextField = new JTextField();
        wingSpanTextField.setBounds(180, 630, 150, 20);

        this.add(wingSpanTextField);

        acceptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String family = familyTextField.getText();
                name = nameTextField.getText();
                speed = Double.parseDouble(speedTextField.getText());
                weight = Double.parseDouble(speedTextField.getText());
                double wingSpan = Double.parseDouble(wingSpanTextField.getText());
                try {
                    a = new Pigeon(family, wingSpan, name, myGender, weight, speed, medal, new Point(0, 0), selectedType, selectedSkin, 500, 5);
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                setVisible(false);
                flag = true;

            }
        });

        this.setSize(365, 760);
        acceptButton.setBounds(120, 660, 100, 35);
        this.add(acceptButton);
        acceptButton.setEnabled(true);

    }

    public Animal getAnimal(){return this.a;}

    public boolean isFlag() {
        return flag;
    }

    public String[][] getNames() {
        return names;
    }

    public int getMedalSize() {
        return medalSize;
    }

    @Override
    public String getName() {
        return name;
    }

    public Gender getMyGender() {
        return myGender;
    }

    public Double getWeight() {
        return weight;
    }

    public Double getSpeed() {
        return speed;
    }

    public Medal[] getMedal() {
        return medal;
    }

    public Point getPosition() {
        return position;
    }

    public boolean isCastrated() {
        return castrated;
    }

    public Snake.Poisonous getPoison() {
        return poison;
    }

    public String getIsPoison() {
        return isPoison;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public String getAnimalType1() {
        return animalType1;
    }

    public String getSelectedSkin() {
        return selectedSkin;
    }

    public String getPath() {
        return path;
    }

    public Animal getA() {
        return a;
    }

    public JButton getAcceptButton() {
        return acceptButton;
    }

    public JTextField getNameTextField() {
        return nameTextField;
    }

    public JTextField getSpeedTextField() {
        return speedTextField;
    }

    public JTextField getWeightTextField() {
        return weightTextField;
    }
}
