/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Plot;

/**
 *
 * @author Administrator
 */
public class PlotScreen {
    private static int width, height;
    public static int[] pixels;
    public PlotScreen (int width, int height){
        this.width=width;
        this.height=height;
        pixels= new int[width*height];
    }
    public static void render(){
        for(int y=0; y < height; y++){
            for(int x=0; x < width; x++){
                pixels[x+y*width]=0xFFFFFF;// make every pixel white;
            }
        }
    }
    public static void clear(){
        for(int i=0; i < pixels.length;i++){
            pixels[i]=0;// make every pixel black
        }
    }
}
