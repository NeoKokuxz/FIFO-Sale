public class Sell {
    int stcokSellNum;
    double rate = 1.0;
    boolean promo = false;
    boolean done = false;

    public Sell(int num, boolean promo, double rate){
        this.stcokSellNum = num;
        this.promo = promo;
        this.rate = rate;
    }

    public void set(int num){
        this.stcokSellNum = num;
    }

    public void setDone(){
        this.done = true;
    }

}