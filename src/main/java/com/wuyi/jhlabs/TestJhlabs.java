package com.wuyi.jhlabs;

import  com.jhlabs.image.ImageUtils ;
import com.jhlabs.image.SharpenFilter;

import  java.awt.image.BufferedImage ;
import  java.io.File ;
import  java.io.IOException ;
import  java.util.Scanner ;
import  javax.imageio.ImageIO ;

public class TestJhlabs {

    /**
     * @param args the command line arguments
     */

    private static int filterCode;
    private static BufferedImage image, filteredImage;
    private static String imageFileName;
    private static File imageFile;
    private static Scanner sc;

    private static File filteredImageFile;

    public static void main(String[] args) throws IOException {
        // TODO code application logic here
//        sc = new Scanner(System.in);
//        System.out.println("Enter Image file name::");
//        imageFileName = sc.nextLine();
//        System.out.println("Enter Filter code::");
//        filterCode = Integer.parseInt(sc.next());

        imageFile = new File("C:\\1.png");

        image = ImageIO.read(imageFile);
        //image = ImageUtils.convertImageToARGB(image);
        System.out.println("====="+image.getType()+"=========");

        BufferedImage averageImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        SharpenFilter  sharpenFilter = new SharpenFilter();

        sharpenFilter.filter(image,averageImage);
        image = averageImage;
        sharpenFilter.filter(image,averageImage);


        filteredImageFile = new File("C://"+System.currentTimeMillis()+".jpg");
        ImageIO.write(averageImage, "png", filteredImageFile);

    }

    public static BufferedImage applyFilter(int filterCode) throws IOException{

        switch(filterCode){
            case Constants.GrayscaleFilterCode:
                return Filters.greyScaleFilter(image);

            case Constants.BlurFilterCode:
                return Filters.blurFilter(image);

            case Constants.BoxBlurFilterCode:
                System.out.println("Enter values for HRadius (0 to 100), VRadius (0 to 100) and Iterations (0 to 10)");
                int hRadius = Integer.parseInt(sc.next());
                int vRadius = Integer.parseInt(sc.next());
                int iterations = Integer.parseInt(sc.next());
                return  Filters.boxBlurFilter(image, hRadius, vRadius, iterations);


            case Constants.MaskFilterCode:
                return Filters.applyMaskFilter(image);

            case Constants.AverageFilterCode:
                return Filters.averageFilter(image);


            case Constants.BlockFilteredImage:
                System.out.println("Enter the Block Size::");
                int blockSize = Integer.parseInt(sc.next());
                return  Filters.blockFilter(image, blockSize);

            case Constants.ChromeFilterCode:
                System.out.println("Enter the values for Amount (0 to 100) and Exposure (0 to 5)::");
                float amount = Float.parseFloat(sc.next());
                float exposure = Float.parseFloat(sc.next());
                return Filters.chromeFilter(image, amount, exposure);

                default:
                    return null;
        }
    }
}
