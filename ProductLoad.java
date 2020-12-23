public class ProductLoad {
    /**
     * name is for the product name
     * weight is the weight of the product in tons
     * value is the value of the product in dollars
     * isDangerous is whether the product is dangerous or not
     */
    String name;
    double weight;
    double value;
    boolean isDangerous;

    /**
     * @param name - string
     * @param weight - double
     * @param value - double
     * @param isDangerous - boolean
     * constructor for ProductLoad, giving each parameter it's respected
     * argument.
     */

    public ProductLoad(String name, double weight, double value,
                       boolean isDangerous) {
        this.name = name;
        this.weight = weight;
        this.value = value;
        this.isDangerous = isDangerous;
    }

    /**
     * @param name - the argument we'll use
     * Sets the argument to name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the product name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @param weight - the argument we'll use
     * if the argument is less than 0, throw an exception else set the
     * argument to the product weight
     */
    public void setWeight(double weight) {
        if (weight < 0){
            throw new IllegalArgumentException("Invalid weight");
        }
        else {
            this.weight = weight;
        }
    }

    /**
     *
     * @return the product weight
     */
    public double getWeight() {
        return weight;
    }
    /**
     *
     * @param value - the argument we'll use
     * if the argument is less than 0, throw an exception else set the
     * argument to the product value
     */

    public void setValue(double value) {
        if (value < 0){
            throw new IllegalArgumentException("Invalid value.");
        }
        else {
            this.value = value;
        }
    }

    /**
     *
     * @return the value for the product
     */
    public double getValue() {
        return value;
    }

    /**
     *
     * @param dangerous - the argument we'll use
     *  setting the argument to isDangerous
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    /**
     *
     * @return whether the product is dangerous or not
     */
    public boolean isDangerous() {
        return isDangerous;
    }
}