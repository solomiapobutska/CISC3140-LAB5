import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WareHouse {
    public static void main(String[] args) throws IOException {
            Scanner master = new Scanner(new File("src/data.txt"));
            String input;
            Map<String, int[]>stock=new HashMap<String, int[]>();
            while ((input=master.nextLine()) !=null) {
                String[] line=input.split("\\s+");
                String city;
                int[] p=new int[3];
                if(line.length==6) {
                    city=line[1]+line[2];
                    p[0]=Integer.valueOf(line[3]);
                    p[1]=Integer.valueOf(line[4]);
                    p[2]=Integer.valueOf(line[5]);
                }
                else {
                    city=line[1];
                    p[0]=Integer.valueOf(line[2]);
                    p[1]=Integer.valueOf(line[3]);
                    p[2]=Integer.valueOf(line[4]);
                }
                if("s".equals(line[0])) {
                    if(stock.containsKey(city)) {
                        int[] t=stock.get(city);
                        for(int i=0;
                            i<3;
                            i++) t[i]+=p[i];
                    }
                    else {
                        stock.put(city, p);
                    }
                    System.out.println(city+" "+stock.get(city)[0]+" "+stock.get(city)[1]+" "+stock.get(city)[2]);
                }
                else if("o".equals(line[0])) {
                    double price=0.0;
                    boolean x=true;
                    int[] t=stock.get(city);
                    if(t[0] >=p[0] && t[1] >=p[1] && t[2] >=p[2]) {
                        for(int i=0;
                            i<3;
                            i++) {
                            t[i] -=p[i];
                            if(i==0) price+=(2*p[i]);
                            if(i==1) price+=(7*p[i]);
                            if(i==2) price+=(8.5*p[i]);
                        }
                    }
                    else {
                        if(t[0] >=p[0]) {
                            t[0] -=p[0];
                            price+=(2*p[0]);
                        }
                        else {
                            String Temp=findMax(stock, city, p[0]-t[0], 0);
                            if(null !=Temp) {
                                System.out.println(p[0]-t[0] +"amount of Item1 shipped from "+Temp+" to "+city);
                                t[0]=0;
                                int[] cityTempstock=stock.get(Temp);
                                price+=(2*p[0])+(0.1 * p[0]-t[0]);
                                cityTempstock[0]-=(p[0]-t[0]);
                                stock.put(Temp, cityTempstock);
                                System.out.println(Temp+" "+stock.get(Temp)[0]+" "+stock.get(Temp)[1]+" "+stock.get(Temp)[2]);
                            }
                            else x=false;
                        }
                        if(t[1] >=p[1]) {
                            t[1] -=p[1];
                            price+=(7*p[1]);
                        }
                        else {
                            String Temp=findMax(stock, city, p[1]-t[1], 1);
                            if(null !=Temp) {
                                System.out.println(p[1]-t[1] +"amount of Item2 shipped from "+Temp+" to "+city);
                                t[1]=0;
                                int[] cityTempstock=stock.get(Temp);
                                price+=(7*p[1])+(0.1 * p[1]-t[1]);
                                cityTempstock[1]-=(p[1]-t[1]);
                                stock.put(Temp, cityTempstock);
                                System.out.println(Temp+" "+stock.get(Temp)[0]+" "+stock.get(Temp)[1]+" "+stock.get(Temp)[2]);
                            }
                            else x=false;
                        }
                        if(t[2] >=p[2]) {
                            t[2] -=p[2];
                            price+=(8.5*p[2]);
                        }
                        else {
                            String Temp=findMax(stock, city, p[2]-t[2], 2);
                            if(null !=Temp) {
                                System.out.println(p[2]-t[2] +"amount of Item3 shipped from "+Temp+" to "+city);
                                t[2]=0;
                                int[] cityTempstock=stock.get(Temp);
                                price+=(2*p[2])+(0.1 * p[2]-t[2]);
                                cityTempstock[2]-=(p[2]-t[2]);
                                stock.put(Temp, cityTempstock);
                                System.out.println(Temp+" "+stock.get(Temp)[0]+" "+stock.get(Temp)[1]+" "+stock.get(Temp)[2]);
                            }
                            else x=false;
                        }
                    }
                    System.out.println(city+" "+stock.get(city)[0]+" "+stock.get(city)[1]+" "+stock.get(city)[2]);
                    System.out.println("Price is "+price);
                    if(!x) System.out.println("Order was not complete ");
                }
            }
        }



    static String findMax(Map<String, int[]> stock, String city, int excess, int index) {
        int max=-1;
        String maxCity=null;
        for( String c: stock.keySet()) {
            if(!c.equals(city)) {
                int[] temp=stock.get(c);
                if(temp[index] >=max) {
                    max=temp[index];
                    maxCity=c;
                }
            }
        }
        if(max > excess) return maxCity;
        else return null;
    }
}