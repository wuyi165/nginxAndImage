package com.wuyi.jhlabs;


import com.jhlabs.composite.AverageComposite;
import com.jhlabs.image.ApplyMaskFilter;
import com.jhlabs.image.AverageFilter;
import com.jhlabs.image.BlockFilter;
import com.jhlabs.image.BlurFilter;
import com.jhlabs.image.BorderFilter;
import com.jhlabs.image.BoxBlurFilter;
import com.jhlabs.image.ChromeFilter;
import com.jhlabs.image.GrayscaleFilter;
import com.jhlabs.image.PointFilter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * @author  jcqwu
 */
public class Filters {



    /************************************************************Grayscale Filter*****************************************************************/

    public static BufferedImage greyScaleFilter(BufferedImage image) throws IOException{

        GrayscaleFilter filter = new GrayscaleFilter();
        BufferedImage grayscaleImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.filter(image, grayscaleImage);



        return grayscaleImage;
    }

    /************************************************************Blur Filter*******************************************/

    public static BufferedImage blurFilter(BufferedImage image) throws IOException{

        BlurFilter filter = new BlurFilter();
        BufferedImage blurImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.setEdgeAction(5);
        System.out.println(" " + filter.getEdgeAction());
        filter.filter(image, blurImage);

        return blurImage;
    }


    /************************************************************Box Blur Filter Parameters and Default Values*******************************************
     H Radius = 1 to 100, default = 0
     V Radius = 1 to 100, default = 0
     Iterations = 1 to 10, default = 1
     */
    public static BufferedImage boxBlurFilter(BufferedImage image, int hRadius, int vRadius, int iterations) throws IOException{

        BoxBlurFilter filter = new BoxBlurFilter(hRadius, vRadius, iterations);
        BufferedImage boxBlurImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        System.out.println(filter.getHRadius() + " " + filter.getVRadius() + " " + filter.getIterations());
        filter.filter(image, boxBlurImage);


        return boxBlurImage;
    }


    /************************************************************Mask Filter*******************************************************************/

    public static BufferedImage applyMaskFilter(BufferedImage image) throws IOException{

        ApplyMaskFilter filter = new ApplyMaskFilter();
        BufferedImage maskImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.filter(image, maskImage);

        return maskImage;
    }

    /************************************************************Average Filter*******************************************************************/

    public static BufferedImage averageFilter(BufferedImage image) throws IOException{

        AverageFilter filter = new AverageFilter();
        BufferedImage averageImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.filter(image, averageImage);

        return averageImage;
    }

    /************************************************************Block Filter******************************************************************
     Block size = 1 to 100, default = 0*/

    public static BufferedImage blockFilter(BufferedImage image, int blockSize) throws IOException{

        BlockFilter filter = new BlockFilter();
        BufferedImage blockImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.setBlockSize(blockSize);
        filter.filter(image, blockImage);


        return blockImage;
    }

    /************************************************************Chrome Filter******************************************************************
     Amount = 0 to 100, default 0.5
     Exposure = 0 to 5, default = 1*/

    public static BufferedImage chromeFilter(BufferedImage image, float amount, float exposure) throws IOException{

        ChromeFilter filter = new ChromeFilter();
        BufferedImage chromeImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        filter.setAmount(amount);
        filter.setExposure(exposure);
        filter.filter(image, chromeImage);

        return chromeImage;
    }
}
