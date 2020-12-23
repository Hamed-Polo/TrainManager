public class TrainCarNode {
    /**
     * nextNode is for the next node in the chain
     * prevNode is for the previous node in the chain
     * carData is for the data
     */
    TrainCarNode nextNode;
    TrainCarNode prevNode;
    TrainCar carData;

    /**
     *
     * @param nextNode - TrainCarNode
     * @param prevNode - TrainCarNode
     * @param carData - TrainCar
     * Constructor for TrainCarNode, setting all the arguments to their
     * respected variables.
     */

    public TrainCarNode(TrainCarNode nextNode, TrainCarNode prevNode,
                        TrainCar carData){
        this.nextNode = nextNode;
        this.prevNode = prevNode;
        this.carData = carData;
    }

    /**
     *
     * @param nextNode - argument we'll use
     * setting the argument to nextNode
     */
    public void setNextNode(TrainCarNode nextNode) {
        this.nextNode = nextNode;
    }

    /**
     *
     * @return the next node
     */
    public TrainCarNode getNextNode(){
        return nextNode;
    }

    /**
     *
     * @param prevNode - argument we'll use
     * setting the argument to prevNode
     */
    public void setPrevNode(TrainCarNode prevNode) {
        this.prevNode = prevNode;
    }

    /**
     *
     * @return the previous node
     */
    public TrainCarNode getPrevNode(){
        return prevNode;
    }

    /**
     *
     * @param carData - argument we'll use
     * setting the argument to carData
     */
    public void setCarData(TrainCar carData) {
        this.carData = carData;
    }

    /**
     *
     * @return the data
     */
    public TrainCar getCarData() {
        return carData;
    }
}
