public class IRR2 {
    private static double init = -96000;
    private static double flow = 1028.61;
    private static double final_pay = 97662.97;
    private static int len = 60;

    private static double find_npv(double rate){
        double result = init;
        for(int i=1; i<=len; i++){
            double r = Math.pow((1+rate),i);
            result +=  flow/r;
        }
        result += final_pay/Math.pow((1+rate),len);
        return result;
    }
    private static double function(double x){
        double res = find_npv(x);
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
        double prev = 0.008;
        double next = 0.02;
        int count = 0;
        while(Math.abs(function(next))>0.001 && count < 100){
            System.out.println(next + " npv: " + function(next));
            double temp = next;
            next = find_next(prev, next);
            prev = temp;
            count++;
        }
        System.out.println("result: "+ next + " npv: " + function(next));
    }

}
