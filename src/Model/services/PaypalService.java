package Model.services;

public class PaypalService implements OnlinePaymentService{


    private static final double TAX_PERCENTAGE = 0.02;
    private static final double INTEREST_MONTHLY = 0.01;

    @Override
    public double taxPayment(double amount) {
        return amount * TAX_PERCENTAGE;
    }

    @Override
    public double interest(double amount, int months) {
        return amount * INTEREST_MONTHLY * months;
    }


}
