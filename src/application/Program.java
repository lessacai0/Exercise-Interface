package application;

import Model.entities.Contract;
import Model.entities.Installment;
import Model.services.ContractService;
import Model.services.PaypalService;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

            Locale.setDefault(Locale.US);
            Scanner sc = new Scanner(System.in);

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            System.out.println("Enter the number of contract: ");
            System.out.print("Number: ");
            int number = sc.nextInt();
            System.out.print("Date (dd/MM/yyyy): ");
            LocalDate date = LocalDate.parse(sc.next(), dtf);
            System.out.print("Value of contract: ");
            double totalValue = sc.nextDouble();

            Contract obj = new Contract(number, date, totalValue);

            System.out.print("Enter the number of installments: ");
            int n = sc.nextInt();

            ContractService contractService = new ContractService(new PaypalService());

            contractService.processContract(obj, n);

            System.out.println("Installments:");
            for (Installment installment : obj.getInstallments()) {
                System.out.println(installment);
            }

            sc.close();
        }
    }

