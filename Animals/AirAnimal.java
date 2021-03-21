
/*
package - Animals
 */
package Animals;

/*
imports: Mobility.Point, Olympics.Medal
 */
import Mobility.Point;
import Olympics.Medal;

import java.io.IOException;


/*
this class is an abstract class which is representing the air animals
such as Eagle and Pigeon.
it's a derived class of Animal.
 */
public abstract class AirAnimal extends Animal {
    // first position  - x = 0, y = 100
    protected double wingspan;
    /*
    (*) this is the AirAnimal constructor (*)

    @param: wingspan gives the wing span of the animal
    @param: name gives the name of the animal
    @param: valGender gives the Gender of the animal
    @param: weight gives the weight of the animal
    @param: speed gives the speed of the animal
    @param: medal gives an array of references to Medal
    @param: position gives the position of the animal
     */
    AirAnimal(double wingspan, String name, Gender valGender, Double weight, Double speed, Medal[] medal, Point position, String animalCategory, String skin, int energy, int energyPerMeter) throws IOException {
        super(name, valGender, weight, speed, medal, position, animalCategory, skin, energy, energyPerMeter);

    /*
    double check if the default position of air animals is correct.
     */
        if (position.x == 0 && position.y == 100) {
            super.position.x = position.x;
            super.position.y = position.y;
        } else {
            System.out.println("wrong starting position, atone!");
        }
        /*
        standard check if the value of wingspan in positive.
         */
        if (wingspan > 0) {
            this.wingspan = wingspan;
        }
    }

    public String getType(){return "Air Animal";}
    public int getCurrEnergy(){return this.currEnergy;}
    public void setCurrEnergy(int x)
    {
        if (this.maxEnergy > this.currEnergy+x)
            this.currEnergy = x;
        else {
            throw new RuntimeException("above max energy");
        }
    }

    public void setEnergyPerMeter(int x){
        this.energyPerMeter = x;
    }

}



