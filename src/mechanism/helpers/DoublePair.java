package helpers;

public class DoublePair{
    public double first;
    public double second;

    public DoublePair(double a, double b){
        first = a;
        second = b;
    }

    public boolean equals(DoublePair other){
        return Math.abs(first - other.first) < 2 && Math.abs(second - other.second) <2;
    }

    public DoublePair add(DoublePair other){
        return new DoublePair(this.first + other.first, this.second + other.second);
    }

    public void reverse(){
        this.first *= -1;
        this.second *= -1;
    }
}
