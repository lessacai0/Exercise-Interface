package Model.services;
import Model.entities.Contract;
import Model.entities.Installment;

import java.time.LocalDate;

public class ContractService {


        private OnlinePaymentService onlinePaymentService;

        public ContractService(OnlinePaymentService onlinePaymentService) {
            this.onlinePaymentService = onlinePaymentService;
        }

        public void processContract(Contract contract, int months) {
            double basicQuota = calculateBasicQuota(contract, months);

            for (int i = 1; i <= months; i++) {
                LocalDate dueDate = contract.getDate().plusMonths(i);
                double interest = calculateInterest(basicQuota, i);
                double fee = calculatePaymentFee(basicQuota + interest);
                double quota = calculateTotalQuota(basicQuota, interest, fee);
                contract.getInstallments().add(new Installment(dueDate, quota));
            }
        }

        private double calculateBasicQuota(Contract contract, int months) {
            return contract.getTotalValue() / months;
        }

        private double calculateInterest(double basicQuota, int month) {
            return onlinePaymentService.interest(basicQuota, month);
        }

        private double calculatePaymentFee(double amount) {
            return onlinePaymentService.taxPayment(amount);
        }

        private double calculateTotalQuota(double basicQuota, double interest, double fee) {
            return basicQuota + interest + fee;
        }
    }




