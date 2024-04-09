package Model.services;

public interface OnlinePaymentService {

    double taxPayment (double amount);
    double interest (double amount, int months);


}
