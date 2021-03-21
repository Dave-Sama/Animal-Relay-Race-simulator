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
this class represent Alligator which is water animal, and implementing the IReptile interface.
 */
public class Alligator extends WaterAnimal implements IReptile {
    protected String areaOfLiving;

    // delegator.
    private WaterTerrestrial me;

    /*
        (*) this is the Alligator constructor (*)

        @param: name gives the name of the animal
        @param: valGender gives the Gender of the animal
        @param: weight gives the weight of the animal
        @param: speed gives the speed of the animal
        @param: medal gives an array of references to Medal
        @param: position gives the position of the animal
        @param: diveDepth gives the depth the animal can dive
        @param: areaOfLiving gives the animal's area of living

    */
    public Alligator(int numOfLegs, String areaOfLiving, double diveDept, String name, Gender valGender, double weight, double speed, Medal[] medal, Point position, String animalCategory, String skin,int energy, int energyPerMeter) throws IOException {
        super(0, name, valGender, weight, speed, medal, position,animalCategory,skin,energy,energyPerMeter);
        this.areaOfLiving = areaOfLiving;

        me = new WaterTerrestrial();

        me.setDepth(diveDept);
        me.setNumberOfLegs(numOfLegs);
    }

    public Alligator(Alligator other) throws IOException {
        this(other.me.getNumberOfLegs(),other.areaOfLiving,other.diveDepth,other.name, other.myGender, other.weight, other.speed, other.medal, other.position,other.animalCategory,other.getSkin(),other.getCurrEnergy(),other.energyPerMeter);
    }

    /*
    this function will speed the reptile's movement
    @param: speedToAdd gives us the additional speed
    @return the new speed
     */
    @Override
    public double speedUp(int speedToAdd) {
        if (this.speed + speedToAdd > MAX_SPEED) {
            System.out.println("alligator cannot be faster than 5 km/h.");
            return this.speed;
        } else {
            this.speed += speedToAdd;
            return this.speed;
        }
    }

    public Boolean makeSound () {
        System.out.println("Animal " + this.name + " said Roar.");
        return true;
    }

    /*
    this function will give us all the information regard the animal
    @return: a String of the animal's information
     */
    public String toString() {

        return "*** ALLIGATOR'S INFORMATION ***:\n" + super.toString() +  "\n(*) Dive depth: " + this.me.getDepth() + "." +
                "\n(*) Area of living: " + this.areaOfLiving + ".\n\n";
    }


}
