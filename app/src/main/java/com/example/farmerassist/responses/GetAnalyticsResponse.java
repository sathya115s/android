package com.example.farmerassist.responses;

public class GetAnalyticsResponse {

    private String status;
    private String message;
    private double totalIncome;
    private double totalExpenses;
    private double netAmount;

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public double getTotalIncome() {
        return totalIncome;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getNetAmount() {
        return netAmount;
    }
}
