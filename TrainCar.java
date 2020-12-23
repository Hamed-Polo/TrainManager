public class TrainCar {
    /**
     * length is for the length of the train car
     * weight is for the weight of the train car
     * load is a product load reference to see whether the train is empty or
     * not.
     */
    double length;
    double weight;
    ProductLoad load;

    /**
     *
     * @param length - double
     * @param weight - double
     * Constructor for TrainCar giving each variable it's respected argument
     * and setting load to null.
     */
    public TrainCar(double length, double weight){
        this.length = length;
        this.weight = weight;
        load = null;
    }

    /**
     *
     * @return the length of the train car
     */
    public double getLength() {
        return length;
    }

    /**
     *
     * @return the weight of the train car
     */
    public double getWeight() {
        return weight;
    }

    /**
     *
     * @param load - argument we'll use
     *  Setting the argument to load
     */
    public void setLoad(ProductLoad load){
        this.load = load;
    }

    /**
     *
     * @return the load for the train car
     */
    public ProductLoad getLoad(){
        return load;
    }

    /**
     *
     * @return whether the train car is empty or not
     */
    public boolean isEmpty(){
        return load == null;
    }
}
