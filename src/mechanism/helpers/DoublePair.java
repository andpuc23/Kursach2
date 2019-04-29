package helpers;

public class DoublePair{
    public double first;
    public double second;

    public DoublePair(double a, double b){
        first = a;
        second = b;
    }

    public boolean equals(DoublePair other){
        return first == other.first && second == other.second;
    }

    public DoublePair neg(){
        return new DoublePair(this.first*(-1), this.second*(-1));
    }
}
