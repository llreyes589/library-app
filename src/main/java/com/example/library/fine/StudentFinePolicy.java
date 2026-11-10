package main.java.com.example.library.fine;

public class StudentFinePolicy implements FinePolicy{
    @Override
    public double calculate(int daysLate) {
        return Math.max(0, daysLate) * 2.0;
    }
}
