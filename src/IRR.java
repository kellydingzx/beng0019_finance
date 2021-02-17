import java.util.ArrayList;

public class IRR {
    private static double[] cashflows = {-285,41,74,123,90,55,20};
    private static double final_pay = -31;
            //{-100000,10000,20000,30000,40000,50000};
    private static double find_npv(double rate, double[] cashflows, double final_pay){
        double result = 0;
        for(int i=0; i<cashflows.length; i++){
            double r = Math.pow((1+rate),i);
            result +=  cashflows[i]/r;
        }
        result += final_pay/Math.pow((1+rate),cashflows.length-1);
        return result;
    }
    private static double function(double x){
        double res = find_npv(x,cashflows,final_pay);
        //System.out.println(res);
        return res;
    }
    private static double find_next(double x1, double x2){
        double y1 = function(x1);
        double y2 = function(x2);
        double k = (y1-y2)/(x1-x2);
        return (-y1+k*x1)/k;
    }

    public static void main(String[] args) {
        double prev = 0.07;
        double next = 0.17;
        int count = 0;
        while(Math.abs(function(next))>0.001 && count < 11){
            System.out.println(next + " npv: " + function(next));
            double temp = next;
            next = find_next(prev, next);
            prev = temp;
            count++;
        }
        System.out.println("result: "+ next + " npv: " + function(next));
    }
}
