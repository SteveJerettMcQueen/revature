package com.revature.sjm;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.revature.sjm.controller.BankRequestController;
import com.revature.sjm.domain.Account;
import com.revature.sjm.domain.Bank;
import com.revature.sjm.domain.Checking;
import com.revature.sjm.domain.Customer;
import com.revature.sjm.domain.EntryForm;
import com.revature.sjm.domain.Saving;
import com.revature.sjm.domain.Transaction;
import com.revature.sjm.service.AccountManager;
import com.revature.sjm.util.CustomerValidator;
import com.revature.sjm.view.AccountManagementRequestView;
import com.revature.sjm.view.RegistrationRequestView;
import com.revature.sjm.view.TransactionRequestView;

/**
 *
 */
public class BankApp {

	// Must exist in the database for demonstration
	public static Bank bank = new Bank(1, "Wells Fargo", new BankRequestController());
	public static Scanner scan = new Scanner(System.in);

	public BankApp() {

	}

	public void start() {
		switch (masterViewPrompt()) {
		case 0:// Customer Registration Request View
			/*
			 * Customers want to register with the bank.
			 * 
			 */
			getRegistrationRequestView();
			break;
		case 1:// Bank Log In Request View
			/*
			 * Get customer menu after successfully logging in.
			 * 
			 */
			getLogInView();
			break;
		default:
			System.out.println("Exit master view prompt");
		}

	}

	private int masterViewPrompt() {
		System.out.println("--------- Welcome From Wells Fargo ---------");
		System.out.println(" --> 0 : Customer Registration Request");
		System.out.println(" --> 1 : Log In");
		System.out.println(" --> (> 2) : Exit");
		System.out.print("Choose view: ");
		int view = scan.nextInt();
		scan.nextLine();
		return view;
	}

	private void getLogInView() {
		System.out.println("--------- Bank Log In View ---------");
		RegistrationRequestView view = new RegistrationRequestView(bank.getBankRequestController());
		System.out.print("Enter a user name: ");
		String userName = scan.nextLine();
		System.out.print("Enter a password: ");
		String password = scan.nextLine();
		Customer customer = view.logInCustomer(userName, password);
		if (customer != null) {
			getCustomerMenu(customer);
		}
	}

	private int secondaryViewPrompt() {
		System.out.println("--------- Bank View ---------");
//		System.out.println(" --> 0 : Customer Registration Request View");
		System.out.println(" --> 0 : Open A New Account View");
		System.out.println(" --> 1 : Customer Accounts View");
		System.out.println(" --> 2 : Customer Account & Transaction View");
		System.out.println(" --> (> 3) : Exit");
		System.out.print("Choose view: ");
		int view = scan.nextInt();
		scan.nextLine();
		return view;
	}

	private void getCustomerMenu(Customer customer) {
		AccountManager acntManager = new AccountManager();
		List<Account> accounts = acntManager.findCustomerAccountsById(customer.getCustId());
		switch (secondaryViewPrompt()) {
//		case 0:// Customer Registration Request View
//			/*
//			 * Customers register with the bank.
//			 * 
//			 */
//			getRegistrationRequestView();
//			break;
		case 0:// Open A New Account View
			/*
			 * One of the two registered customers wants to open a single account.
			 * 
			 */
			List<Customer> customers = new ArrayList<>();
			customers.add(customer);
			getOpenAccountView(customers);
			break;
		case 1:// Customer View Accounts
			/*
			 * One of the two registered customers wants to make some transaction on a
			 * specific account.
			 * 
			 */
			getCustomerAccountsView(customer);
			break;
		case 2:// Customer Account & Transaction View
			/*
			 * View transactions on an account by a registered customer.
			 * 
			 */
			getCustomerTransactionsView(customer, accounts);
			break;
		default:
			System.out.println("Exit");
			break;
		}
	}

	private void getRegistrationRequestView() {
		System.out.println("--------- Customer Registration View ---------");
		List<Customer> customers = new ArrayList<>();
		System.out.print("How many customers are registering?: ");
		int numReg = scan.nextInt();
		scan.nextLine();
		for (int i = 0; i < numReg; i++) {
			System.out.print("Enter your first name: ");
			String firstName = scan.nextLine();
			System.out.print("Enter your middle name: ");
			String middleName = scan.nextLine();
			System.out.print("Enter your last name: ");
			String lastName = scan.nextLine();
			System.out.print("Enter a user name: ");
			String userName = scan.nextLine();
			System.out.print("Enter a password: ");
			String password = scan.nextLine();
			System.out.print("Enter social security number: ");
			int ssn = scan.nextInt();
			scan.nextLine();
			Customer customer = new Customer(firstName, middleName, lastName, ssn);
			customer.setUserName(userName);
			customer.setPassword(password);
			CustomerValidator validator = new CustomerValidator(customer);
			RegistrationRequestView view = new RegistrationRequestView(bank.getBankRequestController());
			view.setValidator(validator);
			customers.add(view.registerCustomer());
			System.out.println("----------------------------------------------");
		}
		getOpenAccountView(customers);
	}

	private void getOpenAccountView(List<Customer> customers) {
		for (int i = 0; i < customers.size(); i++) {
			System.out.println("--------- Customer (" + customers.get(i).getFirstName() + " "
					+ customers.get(i).getLastName() + ") Open Account View ---------:");
		}
		System.out.print("Enter a name for the account: ");
		String name = scan.nextLine();
		System.out.print("Enter form [0-Cash] of [1-Electronic]: ");
		EntryForm form = getEntryForm(scan.nextInt());
		System.out.print("Enter initial balance: ");
		BigDecimal balance = new BigDecimal(scan.nextDouble());
		System.out.print("Enter [0-Checking] or [1-Saving]: ");
		Account account = getAccountByType(scan.nextInt(), name, balance, form);
		scan.nextLine();
		AccountManagementRequestView view = new AccountManagementRequestView(bank.getBankRequestController());
		view.setAccount(account);
		view.setCustomers(customers);
		view.openAccounts();
	}

	private void getCustomerAccountsView(Customer customer) {
		System.out.println("--------- Customer (" + customer.getFirstName() + " " + customer.getLastName()
				+ ") Accounts View ---------:");
		AccountManagementRequestView view = new AccountManagementRequestView(bank.getBankRequestController());
		view.setCustomer(customer);
		List<Account> accounts = view.showCustomerAccounts();
		Account choosenAccount = chooseAccount(accounts);
		accounts.remove(choosenAccount);
		chooseTransactionType(customer, accounts, choosenAccount);
	}

	private void getDepositTransactionRequestView(Customer customer, Account account) {
		System.out.println("--------- Make Deposit On Account (" + account.getName() + ") ---------");
		TransactionRequestView view = new TransactionRequestView(bank.getBankRequestController());
		view.setCustomer(customer);
		view.setAccount(account);
		System.out.print("Enter form [0-Cash] of [1-Electronic]: ");
		EntryForm form = getEntryForm(scan.nextInt());
		System.out.print("Enter deposit amount: ");
		view.deposit(form, scan.nextDouble());
		scan.nextLine();
	}

	private void getWithdrawTransactionRequestView(Customer customer, Account account) {
		System.out.println("--------- Make Withdrawal On Account (" + account.getName() + ") ---------");
		TransactionRequestView view = new TransactionRequestView(bank.getBankRequestController());
		view.setCustomer(customer);
		view.setAccount(account);
		System.out.print("Enter form [0-Cash] of [1-Electronic]: ");
		EntryForm form = getEntryForm(scan.nextInt());
		System.out.print("Enter withdrawal amount: ");
		view.withdraw(form, scan.nextDouble());
		scan.nextLine();
	}

	private void getTransferTransactionRequestView(Customer customer, Account a1, Account a2) {
		System.out
				.println("--------- Make Transfer On Account (" + a1.getName() + " & " + a2.getName() + ") ---------");
		TransactionRequestView view = new TransactionRequestView(bank.getBankRequestController());
		view.setCustomer(customer);
		view.setAccount(a1);
		System.out.print("Enter form [0-Cash] of [1-Electronic]: ");
		EntryForm form = getEntryForm(scan.nextInt());
		System.out.print("Enter transfer amount: ");
		view.transfer(form, scan.nextDouble(), a2);
		scan.nextLine();
	}

	private void getCustomerTransactionsView(Customer customer, List<Account> accounts) {
		System.out.println("--------- Customer (" + customer.getFirstName() + " " + customer.getLastName()
				+ ") Transactions View ---------:");
		TransactionRequestView view = new TransactionRequestView(bank.getBankRequestController());
		Account chosenAccount = chooseAccount(accounts);
		view.setAccount(chosenAccount);
		view.setCustomer(customer);
		List<Transaction> transactions = view.showCustomerTransactions();
		for (int i = 0; i < transactions.size(); i++) {
			Transaction trans = transactions.get(i);
			System.out.println("Transaction " + i + ": (" + trans.getDescription() + " " + trans.getForm() + " "
					+ trans.getAmount() + ")");
		}
	}

	@SuppressWarnings("unused")
	private void getCloseAccountView(List<Account> accounts) {
		System.out.println("--------- Customer Close An Account View ---------");
		AccountManagementRequestView view = new AccountManagementRequestView(bank.getBankRequestController());
		Account chosenAccount = chooseAccount(accounts);
		view.setAccount(chosenAccount);
		view.closeAccount();
	}

	private Account chooseAccount(List<Account> accounts) {
		System.out.println("--------- Choose An Account ---------:");
		for (int i = 0; i < accounts.size(); i++) {
			System.out
					.println("--> " + i + " : " + accounts.get(i).getName() + "( Balance: " + accounts.get(i).getBalance() + ")");
		}
		System.out.print("Select an account: ");
		int choice = scan.nextInt();
		if (choice > accounts.size()) {
			System.exit(0);
			return null;
		} else {
			Account choosenAccount = accounts.get(choice);
			scan.nextLine();
			System.out.println("You've selected: " + choosenAccount.getName());
			return choosenAccount;
		}
	}

	private void chooseTransactionType(Customer customer, List<Account> accounts, Account chosenAccount) {
		System.out.println("--------- Choose Transaction Type ---------");
		System.out.print("Enter: \n[0-Deposit],\n[1-Withdrawal],\n[2-Transfer]: ");
		int choice = scan.nextInt();
		if (choice == 0) {
			getDepositTransactionRequestView(customer, chosenAccount);
		} else if (choice == 1) {
			getWithdrawTransactionRequestView(customer, chosenAccount);
		} else if (choice == 2) {
			getTransferTransactionRequestView(customer, chosenAccount, chooseAccount(accounts));
		} else {
			System.out.println("Exit");
		}
	}

	private EntryForm getEntryForm(int form) {
		if (form == 0) {
			return EntryForm.CASH;
		} else if (form == 1) {
			return EntryForm.ELECTRONIC;
		}
		return null;
	}

	private Account getAccountByType(int type, String name, BigDecimal balance, EntryForm form) {
		switch (type) {
		case 0:
			return new Checking(name, balance, form);
		case 1:
			return new Saving(name, balance, form);
		default:
			return new Checking(name, balance, form);
		}
	}

}
