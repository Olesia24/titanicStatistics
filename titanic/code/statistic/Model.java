package statistic;

public class Model {
    double fare;
    int pClass;
    boolean isSurvived;
    boolean isFemale;
    double age;

    public Model(double fare, int pClass, boolean isSurvived, boolean isFemale, double age) {
        this.fare = fare;
        this.pClass = pClass;
        this.isSurvived = isSurvived;
        this.isFemale = isFemale;
        this.age = age;
    }

    public double getFare() {
        return fare;
    }

    public int getpClass() {
        return pClass;
    }

    public boolean isSurvived() {
        return isSurvived;
    }

    public boolean isFemale() {
        return isFemale;
    }

    public double getAge() {
        return age;
    }
}
