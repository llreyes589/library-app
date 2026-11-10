package main.java.com.example.library.fine;

public class StandardFinePolicy implements FinePolicy{
    private final double finePerDay;

    public StandardFinePolicy(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public double calculate(int daysLate) {
        return Math.max(0, daysLate) * finePerDay;
    }


}
