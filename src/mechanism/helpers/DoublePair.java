package helpers;

public class DoublePair{
    public double first;
    public double second;

    public DoublePair(double a, double b){
        first = a;
        second = b;
    }

    public boolean equals(DoublePair other){
        return Math.abs(first - other.first) < 1 && Math.abs(second - other.second) < 1;
    }

    public DoublePair add(DoublePair other){
        return new DoublePair(this.first + other.first, this.second + other.second);
    }

    public DoublePair substract(DoublePair other){
        return new DoublePair(other.first - this.first, other.second - this.second);
    }

    public void reverse(){
        this.first *= -1;
        this.second *= -1;
    }

    @Override
    public String toString(){
        return this.first + "; " + this.second;
    }
}
