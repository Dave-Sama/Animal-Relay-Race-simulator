package graphics;

import Animals.Animal;
import Animals.CreateInstanceAnimal;
import Mobility.Point;

import java.io.IOException;
import java.util.Random;

public class DisplayAnimal {
    /*
 DisplayAnimal this method is in charge of displaying specific image on the screen
 @param : String path
 @param : boolean flag
  */
    private static int counter = 0;
    public void DisplayAnimal(String path, boolean flag, AddImage obj, String selectedType, Animal[] myAnimal, int animalSize, String tournamentType, CreateInstanceAnimal newAnimal) throws IOException {
        if (tournamentType.equals("Regular competition")) {
            DrawingComponent animalImg = null;
            int[] result = new int[6];
            result[0] = 0;

            try {

                animalImg = obj.AddImg(path, flag, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            int resSize = 0;
            int x = 0;
            int y = 0;
            Random r = new Random();

            switch (selectedType) {
                case "Terrestrial Animal" -> {

                    y = 0;
                }
                case "Air Animal" -> {
                    int low = 1;
                    int high = 5;
                    int temp = 0;

                    while (contains(result, temp)) {
                        temp = r.nextInt(high - low) + low;
                    }
                    result[resSize] = temp;
                    switch (result[resSize]) {
                        case 1 -> {
                            y = 0;
                        }
                        case 2 -> {
                            y = 185;
                        }
                        case 3 -> {
                            y = 360;
                        }
                        case 4 -> {
                            y = 555;
                        }
                        case 5 -> {
                            y = 730;
                        }
                    }
                }
                case "Water Animal" -> {
                    if (!myAnimal[animalSize - 1].getClass().getSimpleName().equals("Alligator")) {
                        int low = 1;
                        int high = 4;
                        int temp = 0;
                        while (contains(result, temp)) {
                            temp = r.nextInt(high - low) + low;
                        }
                        result[resSize] = temp;

                        switch (result[resSize]) {
                            case 1 -> {
                                y = 0;
                            }
                            case 2 -> {
                                y = 185;
                            }
                            case 3 -> {
                                y = 360;
                            }
                            case 4 -> {
                                y = 555;
                            }
                        }
                    } else {
                        y = 0;
                    }
                }

            }

            resSize += 1;
            myAnimal[animalSize - 1].setLocation(new Point(x, y));
            assert animalImg != null;
            animalImg.setBounds(x, y, 100, 100);
            obj.getMyImages()[0].add(animalImg);
        }
        else{
            DrawingComponent animalImg1 = null;
            DrawingComponent animalImg2 = null;
            DrawingComponent animalImg3 = null;
            DrawingComponent animalImg4 = null;
            int[] result = new int[6];
            result[0] = 0;
            int x1 = 0;
            int x2 = 0;
            int y1 = 0;
            int y2 = 0;

            try {
                animalImg1 = obj.AddImg(newAnimal.getA().getPath1(), flag, null);
                animalImg2 = obj.AddImg(newAnimal.getA().getPath2(), flag, null);
                animalImg3 = obj.AddImg(newAnimal.getA().getPath3(), flag, null);
                animalImg4 = obj.AddImg(newAnimal.getA().getPath4(), flag, null);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }

            int resSize = 0;
            int x = 0;
            int y = 0;
            Random r = new Random();

            switch (selectedType) {
                case "Terrestrial Animal" -> {
                    x1= 0;
                    x2= 1000;
                    y1 =0;
                    y2= 730;
                }
                case "Air Animal" -> {
                    int low = 1;
                    int high = 5;
                    int temp = 0;

                    while (contains(result, temp)) {
                        temp = r.nextInt(high - low) + low;
                    }
                    result[resSize] = temp;
                    switch (result[resSize]) {
                        case 1 -> {
                            y = 0;
                        }
                        case 2 -> {
                            y = 185;
                        }
                        case 3 -> {
                            y = 360;
                        }
                        case 4 -> {
                            y = 555;
                        }
                        case 5 -> {
                            y = 730;
                        }
                    }
                }
                case "Water Animal" -> {
                    if (!myAnimal[animalSize - 1].getClass().getSimpleName().equals("Alligator")) {
                        int low = 1;
                        int high = 4;
                        int temp = 0;
                        while (contains(result, temp)) {
                            temp = r.nextInt(high - low) + low;
                        }
                        result[resSize] = temp;

                        switch (result[resSize]) {
                            case 1 -> {
                                y = 0;
                            }
                            case 2 -> {
                                y = 185;
                            }
                            case 3 -> {
                                y = 360;
                            }
                            case 4 -> {
                                y = 555;
                            }
                        }
                    } else {
                        y = 0;
                    }
                }

            }

            resSize += 1;
            myAnimal[counter].setLocation(new Point(0, 0));
            ++counter;
            myAnimal[counter].setLocation(new Point(1000, 0));
            ++counter;
            myAnimal[counter].setLocation(new Point(1000, 730));
            ++counter;
            myAnimal[counter].setLocation(new Point(0, 730));
            ++counter;
            assert animalImg1 != null;
            assert animalImg2 != null;
            assert animalImg3 != null;
            assert animalImg4 != null;
            animalImg1.setBounds(x1, y1, 100, 100);
            animalImg2.setBounds(x2, y1, 100, 100);
            animalImg3.setBounds(x2, y2, 100, 100);
            animalImg4.setBounds(x1, y2, 100, 100);
            obj.getMyImages()[0].add(animalImg1);
            obj.getMyImages()[0].add(animalImg2);
            obj.getMyImages()[0].add(animalImg3);
            obj.getMyImages()[0].add(animalImg4);
        }
    }

    private boolean contains(int[] arr, int x) {
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] == x) {
                return true;
            }
        }
        return false;
    }
}
