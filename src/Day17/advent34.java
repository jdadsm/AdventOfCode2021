package Day17;
import ExtraClasses.*;
import java.util.*;
import java.io.*;

public class advent34 {
    public static void main(String[] args) throws IOException {
        File f = new File("advent34.txt");
        Scanner sc = new Scanner(f);
    
        String input = sc.nextLine();
        input = input.substring(15,input.length());
        String [] coordenates = input.split(", y=");
        int [] x = {Integer.parseInt(coordenates[0].split("\\.\\.")[0]),Integer.parseInt(coordenates[0].split("\\.\\.")[1])};
        int [] y = {Integer.parseInt(coordenates[1].split("\\.\\.")[0]),Integer.parseInt(coordenates[1].split("\\.\\.")[1])};
        List<Ponto> pontos = new ArrayList<Ponto>();
        for (int i = -1000; i < 1000; i++) {
            for (int j = -1000; j < 1000; j++) {
                if(firstStep(i, j, x, y)){
                    pontos.add(new Ponto(i,j));
                }
            }
        }
        
        int maxy = 0;
        for (Ponto ponto : pontos) {
            //System.out.println(ponto.toString());
            int temp = getMaxY(ponto.getX(), ponto.getY(), x, y);
            if(maxy < temp){
                maxy = temp;
            }
            
        }
        System.out.println(maxy);
        System.out.println(pontos.size());

        sc.close();
    }

    public static int getMaxY(int vx,int vy,int[] x, int[] y){
        int maxy = 0;
        int px = 0;
        int py = 0;
        for (int i = 0; i < 1000; i++) {
            px += vx;
            py += vy;
            if(vx>0){
                vx--;
            }else if(vx<0){
                vx++;
            }
            vy--;
            if(py>maxy){
                maxy=py;
            }
            if(px<=x[1] && px>=x[0] && py<=y[1] && py>=y[0]){
                break;
            }
        }
        return maxy;
    }

    public static boolean firstStep(int vx,int vy,int[] x, int[] y){
        boolean onTarget = false;
        int px = 0;
        int py = 0;
        px += vx;
        py += vy;
        if(vx>0){
            vx--;
        }else if(vx<0){
            vx++;
        }
        vy--;
        onTarget = nextStep(vx, vy, x, y, px, py);
        return onTarget;
    }

    public static boolean nextStep(int vx,int vy,int[] x, int[] y,int px,int py){
        if(px<=x[1] && px>=x[0] && py<=y[1] && py>=y[0]){
            return true;
        }else if(px>x[1] || (vx==0 && py<y[0])){
            return false;
        }
        px += vx;
        py += vy;
        if(vx>0){
            vx--;
        }else if(vx<0){
            vx++;
        }
        vy--;

        return nextStep(vx, vy, x, y, px, py);
    }
}

