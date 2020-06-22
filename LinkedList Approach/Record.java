class Record {
    int stockNumber;
    double price;
    boolean done = false;
//        boolean promo = false;

    public Record() {
        this.stockNumber = 0;
        this.price = 0;
    }

    public Record(int stockNum, double salePrice) {
        this.stockNumber = stockNum;
        this.price = salePrice;
//            this.promo = promo;
    }

    public void set(int num){
        this.stockNumber = num;
    }

    public void setDone(){
        this.done = true;
    }



//        public double checkPromo(double discount) {
//            double newPrice = 0;
//            if(promo = true){
//                 newPrice = discount * price;
//            }
//
//            return  newPrice;
//        }
}