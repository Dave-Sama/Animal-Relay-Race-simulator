package graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;


public class AddImage {
    private int imgSize = 0;
    private DrawingComponent[] myImages = new DrawingComponent[1];

  /*
    AddImg method is in charge of adding new DrawingComponent that contains an image, into the myImages array, so from there
    the system could get the relevant image and display it on the screen
    @param : String path
    @param : boolean flag
    @param : BufferedImage buff
    @return : new DrawingComponent instance
     */
    public DrawingComponent AddImg(String path, boolean flag, BufferedImage buff) throws IOException {

        if (this.imgSize == 0) {
            this.imgSize += 1;
            this.myImages = new DrawingComponent[1];
            this.myImages[this.imgSize - 1] = new DrawingComponent(path, flag);
        } else if (this.imgSize > 0) {

            DrawingComponent[] temp = new DrawingComponent[this.imgSize];

            for (int i = 0; i < this.imgSize; ++i) {
                temp[i] = this.myImages[i];
            }

            this.imgSize += 1;
            this.myImages = new DrawingComponent[this.imgSize];

            for (int i = 0; i < temp.length; ++i) {
                this.myImages[i] = temp[i];
            }
            if (buff == null) {
                this.myImages[this.imgSize - 1] = new DrawingComponent(path, flag);
                this.myImages[0].add(this.myImages[imgSize - 1]);
                return this.myImages[imgSize - 1];
            } else {
                this.myImages[this.imgSize - 1] = new DrawingComponent(buff, flag);
                this.myImages[0].add(this.myImages[imgSize - 1]);
                return this.myImages[imgSize - 1];
            }

        }
        return null;
    }

    public int getImgSize() {
        return imgSize;
    }

    public DrawingComponent[] getMyImages() {
        return myImages;
    }

    public void setImgSize(int imgSize) {
        this.imgSize = imgSize;
    }

    public void setMyImages(DrawingComponent[] myImages) {
        this.myImages = myImages;
    }
}
