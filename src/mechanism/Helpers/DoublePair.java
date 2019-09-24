package Helpers;

public class DoublePair{
    public double first;
    public double second;

    public DoublePair(double a, double b){
        first = a;
        second = b;
    }

    public DoublePair add(DoublePair other){
        return new DoublePair(this.first + other.first, this.second + other.second);
    }

    @Override
    public String toString(){
        return this.first + "; " + this.second;
    }
}
