package cpsc4620;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
 * This file is where the front end magic happens.
 * 
 * You will have to write the methods for each of the menu options.
 * 
 * This file should not need to access your DB at all, it should make calls to the DBNinja that will do all the connections.
 * 
 * You can add and remove methods as you see necessary. But you MUST have all of the menu methods (including exit!)
 * 
 * Simply removing menu methods because you don't know how to implement it will result in a major error penalty (akin to your program crashing)
 * 
 * Speaking of crashing. Your program shouldn't do it. Use exceptions, or if statements, or whatever it is you need to do to keep your program from breaking.
 * 
 */

public class Menu {
	

	public static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	private static String custName = "";
	private static String custPhone = "";
	private static int cusId;
	public static void main(String[] args) throws SQLException, IOException {

		System.out.println("Welcome to Pizzas-R-Us!");
		int menu_option = 0;

		// present a menu of options and take their selection

		PrintMenu();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		menu_option = Integer.parseInt(option);

		while (menu_option != 9) {
			switch (menu_option) {
			case 1:// enter order
				EnterOrder();
				break;
			case 2:// view customers
				viewCustomers();
				break;
			case 3:// enter customer
				EnterCustomer();
				break;
			case 4:// view order
					// open/closed/date
				ViewOrders();
				break;
			case 5:// mark order as complete
				MarkOrderAsComplete();
				break;
			case 6:// view inventory levels
				ViewInventoryLevels();
				break;
			case 7:// add to inventory
				AddInventory();
				break;
			case 8:// view reports
				PrintReports();
				break;
			}
			PrintMenu();
			option = reader.readLine();
			menu_option = Integer.parseInt(option);
		}

	}

	// allow for a new order to be placed
	public static void EnterOrder() throws SQLException, IOException {

		/*
		 * EnterOrder should do the following:
		 * 
		 * Ask if the order is delivery, pickup, or dinein if dine in....ask for table
		 * number if pickup... if delivery...
		 * 
		 * Then, build the pizza(s) for the order (there's a method for this) until
		 * there are no more pizzas for the order add the pizzas to the order
		 *
		 * Apply order discounts as needed (including to the DB)
		 * 
		 * return to menu
		 * 
		 * make sure you use the prompts below in the correct order!
		 */

		// User Input Prompts...
		int tableNumber = 0;
		int orderType;
		int cus_ID = 0;
		double orderCost = 0;
		double orderPrice = 0;

		System.out.println(
				"Is this order for: \n1.) Dine-in\n2.) Pick-up\n3.) Delivery\nEnter the number of your choice:");
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		orderType = Integer.parseInt(option);
		String ordType = "";
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
        Order o = null;
        String house;
        String street;
        String city;
        String state;
        String zip;
		switch(orderType) {
			case 1:
				ordType = "dine-in";
				o = new DineinOrder(0, 0, formattedDate, 0, 0, 0, 0);
				break;
			case 2:
				ordType = "Pick-Up";
				o = new PickupOrder(0, 0, formattedDate, 0, 0, 0, 0);
				break;
			case 3:
				ordType = "delivery";
				o = new DeliveryOrder(0, 0, formattedDate, 0, 0, 0, null);
				break;
		}
		if(orderType == 2 || orderType == 3) { 
			System.out.println("Is this order for an existing customer? Answer y/n: ");
			String isExisting = reader.readLine();
			if(isExisting.equals("y")) {
				System.out.println("Here's a list of the current customers: ");
				viewCustomers();
				System.out.println("Which customer is this order for? Enter ID Number:");
				option = reader.readLine();
				cus_ID = Integer.parseInt(option);
				if(orderType == 3) {
					System.out.println("What is the House/Apt Number for this order? (e.g., 111)");
					house = reader.readLine();
					System.out.println("What is the Street for this order? (e.g., Smile Street)");
					street = reader.readLine();
					street = house + street;
					System.out.println("What is the City for this order? (e.g., Greenville)");
					city = reader.readLine();
					System.out.println("What is the State for this order? (e.g., SC)");
					state = reader.readLine();
					System.out.println("What is the Zip Code for this order? (e.g., 20605)");
					zip = reader.readLine();
					DeliveryOrder p = (DeliveryOrder)o;
					p.setAddress(street+","+city+","+ state+","+ zip);
				}
			}
			else {
					if(orderType == 2) {
						EnterCustomer();
					}
					else {
						EnterCustomer();
						System.out.println("What is the House/Apt Number for this order? (e.g., 111)");
						house = reader.readLine();
						System.out.println("What is the Street for this order? (e.g., Smile Street)");
						street = reader.readLine();
						street = house + street;
						System.out.println("What is the City for this order? (e.g., Greenville)");
						city = reader.readLine();
						System.out.println("What is the State for this order? (e.g., SC)");
						state = reader.readLine();
						System.out.println("What is the Zip Code for this order? (e.g., 20605)");
						zip = reader.readLine();
						DeliveryOrder d = (DeliveryOrder)o;
						d.setAddress(street+","+city+","+ state+","+ zip);
					}
					cus_ID = DBNinja.getLatestCustomer();
			}
		}
		else {
		//System.out.println("ERROR: I don't understand your input for: Is this order an existing customer?");
			System.out.println("What is the table number for this order?");
			option = reader.readLine();
			tableNumber = Integer.parseInt(option);
			DineinOrder p = (DineinOrder)o;
			p.setTableNum(tableNumber);
		}


		o.setCustID(cus_ID);
		o.setOrderType(ordType);
		DBNinja.addOrder(o);
		o.setOrderID(DBNinja.getOrderId());
		DBNinja.fillOrderType(o);
		while(true) {
			System.out.println("Let's build a pizza!");
			Pizza p = buildPizza(DBNinja.getOrderId());
			orderCost += p.getBusPrice();
			orderPrice += p.getCustPrice();
			
			o.setBusPrice(orderCost);
			o.setCustPrice(orderPrice);
			System.out.println(
				"Enter -1 to stop adding pizzas...Enter anything else to continue adding pizzas to the order.");
			if(Integer.parseInt(reader.readLine()) == -1) {
				break;
			}
		}

		System.out.println("Do you want to add discounts to this order? Enter y/n?");
		option = reader.readLine();
		while(option.equals("y")) {
			System.out.println(
				"Which Order Discount do you want to add? Enter the DiscountID. Enter -1 to stop adding Discounts: ");
			ArrayList<Discount> discountList = DBNinja.getDiscountList();
			for(Discount discount : discountList) {
				System.out.println(discount);
			}
			int temp = Integer.parseInt(reader.readLine());
			if(temp == -1)
				break;
			Discount d = DBNinja.findDiscountByName(getDiscountName(temp));
			o.addDiscount(d);
			DBNinja.useOrderDiscount(o, d);
		}
		DBNinja.upDateCostAndPrice(o);
		
		System.out.println("Finished adding order...Returning to menu...");
	}
	

	public static void viewCustomers() throws SQLException, IOException {
		ArrayList<Customer> customerList = DBNinja.getCustomerList();
		for(Customer customer : customerList) {
			System.out.println(customer);
		}

	}
	
	public static void viewToppings() throws SQLException, IOException {
		DBNinja.printInventory();
	}

	// Enter a new customer in the database
	public static void EnterCustomer() throws SQLException, IOException {
		/*
		 * Ask for the name of the customer: First Name <space> Last Name
		 * 
		 * Ask for the phone number. (##########) (No dash/space)
		 * 
		 * Once you get the name and phone number, add it to the DB
		 */

		// User Input Prompts...
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("What is this customer's name (first <space> last");
		custName = reader.readLine();
		System.out.println("What is this customer's phone number (##########) (No dash/space)");
		custPhone = reader.readLine();
		String[] fullName = custName.split(" ");
		Customer c = new Customer(0,fullName[0], fullName[1], custPhone);
		DBNinja.addCustomer(c);

	}
	
	private static String getBaseSize(int id) {
		String size;
		switch(id) {
			case 1:
				size = "Small";
				break;
			case 2:
				size = "Medium";
				break;
			case 3:
				size = "Large";
				break;
			case 4:
				size = "XLarge";
				break;
			default:
				size = "None";
				break;
		}
		return size;
	}
	
	private static String getBaseCrust(int id) {
		String crust;
		switch(id) {
			case 1:
				crust = "Thin";
				break;
			case 2:
				crust = "Original";
				break;
			case 3:
				crust = "Pan";
				break;
			case 4:
				crust = "Gluten-Free";
				break;
			default:
				crust = "None";
				break;
		}
		return crust;
	}
	
	private static String getDiscountName(int discount_id) {
		String discountName = "";
		switch(discount_id) {
			case 1: 
				discountName = "Employee";
				break;
			case 2:
				discountName = "Lunch Special Medium";
				break;
			case 3:
				discountName = "Lunch Special Large";
				break;
			case 4:
				discountName = "Specialty Pizza";
				break;
			case 5:
				discountName = "Happy Hour";
				break;
			case 6:
				discountName = "Gameday Special";
		}
		return discountName;
	}
	
    public static boolean isPositiveInteger(String str) {
        // Check if the string is empty or contains non-digit characters
        if (str == null || str.isEmpty() || !str.matches("\\d+")) {
            return false;
        }

        // Convert the string to a number and check if it's positive
        try {
            int num = Integer.parseInt(str);
            return num >= 0;
        } catch (NumberFormatException e) {
            // If parsing fails (e.g., due to overflow), return false
            return false;
        }
    }

	// View any orders that are not marked as completed
	public static void ViewOrders() throws SQLException, IOException {
		/*
		 * This method allows the user to select between three different views of the
		 * Order history: The program must display: a. all open orders b. all completed
		 * orders c. all the orders (open and completed) since a specific date
		 * (inclusive)
		 * 
		 * After displaying the list of orders (in a condensed format) must allow the
		 * user to select a specific order for viewing its details. The details include
		 * the full order type information, the pizza information (including pizza
		 * discounts), and the order discounts.
		 * 
		 */

		// User Input Prompts...
		System.out.println(
				"Would you like to:\n(a) display all orders [open or closed]\n(b) display all open orders\n(c) display all completed [closed] orders\n(d) display orders since a specific date");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		if(option.equals("a")) {

			displayOrders(1);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			String c = reader.readLine();
			if(c.equals("-1")) {
				return;
			}
			if(!isPositiveInteger(c)) {
				System.out.println("Incorrect entry. Returning to the menu");
				return;
			}
			int choice = Integer.parseInt(c);
			Order o = DBNinja.getOrderById(choice);
			if(o != null)
				System.out.println(o);
			else {
				System.out.println("Incorrect entry. Returning to the menu");
			}				
		}
		else if(option.equals("b")) {
			displayOrders(2);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			int choice = Integer.parseInt(reader.readLine());
			if(choice == -1) {
				return;
			}
			System.out.println(DBNinja.getOrderById(choice)+"\n");
		}
		else if(option.equals("c")) {
			displayOrders(3);
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			int choice = Integer.parseInt(reader.readLine());
			if(choice == -1) {
				return;
			}
			System.out.println(DBNinja.getOrderById(choice)+"\n");		
		}
		else {
			System.out.println("What is the date you want to restrict by? (FORMAT= YYYY-MM-DD)");
			String date = reader.readLine();
			String format = "yyyy-MM-dd";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
	        try {
	            LocalDate.parse(date, formatter);
	        } catch (DateTimeParseException e) {
	        	System.out.println("I don't understand that input, returning to menu");
	        	return;
	        }
	        ArrayList<Order> orderList = DBNinja.getOrdersByDate(date);
	        for(Order order : orderList) {
				String cust_name = "INSTORE customer";
				if(!order.getOrderType().equals("dine-in")) {
					cust_name = DBNinja.getCustomerName(order.getCustID());
				}
				boolean isComplete;
				if(order.getIsComplete() == 0) {
					isComplete = false;						
				}
				else {
					isComplete = true;
				}
				System.out.println("OrderID="+order.getOrderID()+" | "+"Customer Name= "+cust_name+"," +"OrderType= "+order.getOrderType()+"," +"IsComplete ="+isComplete);
	        }
			System.out.println("Which order would you like to see in detail? Enter the number (-1 to exit): ");
			int choice = Integer.parseInt(reader.readLine());
			if(choice == -1) {
				return;
			}
			System.out.println(DBNinja.getOrderById(choice)+"\n");
		}
	}
	

	private static void displayOrders(int option) throws SQLException, IOException {
		ArrayList<Order> orderList = null;
		if(option == 1) {
			orderList = DBNinja.getOrders(false);
		}
		else if(option == 2) {
			orderList = DBNinja.getOrders(true);
		}
		else {
			orderList = DBNinja.getClosedOrders();
		}
		for(Order order : orderList) {
			System.out.println(order);
		}
	}

	// When an order is completed, we need to make sure it is marked as complete
	public static void MarkOrderAsComplete() throws SQLException, IOException {
		/*
		 * All orders that are created through java (part 3, not the orders from part 2)
		 * should start as incomplete
		 * 
		 * When this method is called, you should print all of the "opoen" orders marked
		 * and allow the user to choose which of the incomplete orders they wish to mark
		 * as complete
		 * 
		 */

		// User Input Prompts...
		//System.out.println("There are no open orders currently... returning to menu...");
		displayOrders(1);
		System.out.println("Which order would you like mark as complete? Enter the OrderID: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int option = Integer.parseInt(reader.readLine());
		Order o = DBNinja.getOrderById(option);
		if(o == null) {
			System.out.println("I don't understand that input... returning to menu...");
			return;
		}
		DBNinja.completeOrder(DBNinja.getOrderById(option));

	}

	public static void ViewInventoryLevels() throws SQLException, IOException {
		DBNinja.printInventory();
	}

	public static void AddInventory() throws SQLException, IOException {
		/*
		 * This should print the current inventory and then ask the user which topping
		 * (by ID) they want to add more to and how much to add
		 */

		// User Input Prompts...
		ViewInventoryLevels();
		System.out.println("Which topping do you want to add inventory to? Enter the number: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int toppingId = Integer.parseInt(reader.readLine());
		System.out.println("How many units would you like to add? ");
		int units = Integer.parseInt(reader.readLine());
		Topping topping = DBNinja.findToppingByName(DBNinja.findToppingNameByID(toppingId));
		DBNinja.addToInventory(topping, units);
		System.out.println("Incorrect entry, not an option");

	}

	// A method that builds a pizza. Used in our add new order method
	public static Pizza buildPizza(int orderID) throws SQLException, IOException {

		/*
		 * This is a helper method for first menu option.
		 * 
		 * It should ask which size pizza the user wants and the crustType.
		 * 
		 * Once the pizza is created, it should be added to the DB.
		 * 
		 * We also need to add toppings to the pizza. (Which means we not only need to
		 * add toppings here, but also our bridge table)
		 * 
		 * We then need to add pizza discounts (again, to here and to the database)
		 * 
		 * Once the discounts are added, we can return the pizza
		 */

		Pizza ret = null;

		// User Input Prompts...
		System.out.println("What size is the pizza?");
		System.out.println("1." + DBNinja.size_s);
		System.out.println("2." + DBNinja.size_m);
		System.out.println("3." + DBNinja.size_l);
		System.out.println("4." + DBNinja.size_xl);
		
		System.out.println("Enter the corresponding number: ");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		int size = Integer.parseInt(option);
		System.out.println("What crust for this pizza?");
		System.out.println("1." + DBNinja.crust_thin);
		System.out.println("2." + DBNinja.crust_orig);
		System.out.println("3." + DBNinja.crust_pan);
		System.out.println("4." + DBNinja.crust_gf);
		System.out.println("Enter the corresponding number: ");
		option= reader.readLine();
		double cost = 0;
		double price = 0;	
		int crust= Integer.parseInt(option);
		int opt = 1;
		price = DBNinja.getBaseCustPrice(getBaseSize(size), getBaseCrust(crust));
		cost = DBNinja.getBaseBusPrice(getBaseSize(size), getBaseCrust(crust));
		Topping topping;
		Pizza pizza;
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedDate = today.format(formatter);
		pizza = new Pizza(0, String.valueOf(size), String.valueOf(crust), orderID, "completed", formattedDate, price, cost);
		DBNinja.addPizza(pizza);
		pizza.setPizzaID(DBNinja.getPizzaId());
		while(opt != -1) {
			System.out.println("Available Toppings:");
			
			viewToppings();
			System.out.println("Which topping do you want to add? Enter the TopID. Enter -1 to stop adding toppings: ");
			option= reader.readLine();
			opt = Integer.parseInt(option);
			if(opt == -1) {
				break;
			}
			topping = DBNinja.findToppingByName(DBNinja.findToppingNameByID(opt));
			System.out.println("Do you want to add extra topping? Enter y/n");
			option = reader.readLine();
			boolean flag = false;
			System.out.println(topping);
			double quantity = 0;
			if(size == 1) {	
				if(option.equals("y")) {
					quantity = 2 * topping.getPerAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
				else {
					quantity = topping.getPerAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
			}
			
			if(size == 2) {
				if(option.equals("y")) {
					quantity = 2 * topping.getMedAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
				else {
					quantity = topping.getMedAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
			}
			
			if(size == 3) {
				if(option.equals("y")) {
					quantity = 2 * topping.getLgAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
				else {
					quantity = topping.getLgAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
			}
			
			if(size == 4) {
				if(option.equals("y")) {
					quantity = 2 * topping.getXLAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
				else {
					quantity = topping.getXLAMT();
					price += quantity * topping.getCustPrice();
					cost += quantity * topping.getBusPrice();
					if(topping.getCurINVT() < quantity) {
						flag = true;
					}
				}
			}
			if(flag) {
				System.out.println("We don't have enough of that topping to add it...");
				continue;
			}	
			if(option.equals("y")) 
				DBNinja.useTopping(pizza, topping, true);
			else
				DBNinja.useTopping(pizza, topping, false);
		}
		pizza.setBusPrice(cost);
		pizza.setCustPrice(price);
		System.out.println("Do you want to add discounts to this Pizza? Enter y/n?");
		option = reader.readLine();
		while(option.equals("y")) {

			System.out.println(
				"Which Pizza Discount do you want to add? Enter the DiscountID. Enter -1 to stop adding Discounts: ");
			

			ArrayList<Discount> discountList = DBNinja.getDiscountList();
			for(Discount discount : discountList) {
				System.out.println(discount);
			}
			String o = reader.readLine();
			
			opt = Integer.parseInt(o);			
			if(opt == -1) {
				break;
			}
			Discount d = DBNinja.findDiscountByName(getDiscountName(opt));
			if(d == null) {
				System.out.println("Discount not found");
				continue;
			}
			pizza.addDiscounts(d);
			DBNinja.usePizzaDiscount(pizza, d);
		}
		pizza.setBusPrice(cost);
		pizza.setCustPrice(price);
		DBNinja.upDateCostAndPrice(pizza);
		return pizza;
	}

	public static void PrintReports() throws SQLException, NumberFormatException, IOException {
		/*
		 * This method asks the use which report they want to see and calls the DBNinja
		 * method to print the appropriate report.
		 * 
		 */

		// User Input Prompts...
		System.out.println(
				"Which report do you wish to print? Enter\n(a) ToppingPopularity\n(b) ProfitByPizza\n(c) ProfitByOrderType:");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String option = reader.readLine();
		if(option.equals("a")) {
			DBNinja.printToppingPopReport();
		}
		else if(option.equals("b")) {
			DBNinja.printProfitByPizzaReport();
		}
		else if(option.equals("c")) {
			DBNinja.printProfitByOrderType();
		}
		else {
			System.out.println("I don't understand that input... returning to menu...");
		}
	}

	// Prompt - NO CODE SHOULD TAKE PLACE BELOW THIS LINE
	// DO NOT EDIT ANYTHING BELOW HERE, THIS IS NEEDED TESTING.
	// IF YOU EDIT SOMETHING BELOW, IT BREAKS THE AUTOGRADER WHICH MEANS YOUR GRADE
	// WILL BE A 0 (zero)!!

	public static void PrintMenu() {
		System.out.println("\n\nPlease enter a menu option:");
		System.out.println("1. Enter a new order");
		System.out.println("2. View Customers ");
		System.out.println("3. Enter a new Customer ");
		System.out.println("4. View orders");
		System.out.println("5. Mark an order as completed");
		System.out.println("6. View Inventory Levels");
		System.out.println("7. Add Inventory");
		System.out.println("8. View Reports");
		System.out.println("9. Exit\n\n");
		System.out.println("Enter your option: ");
	}

	/*
	 * autograder controls....do not modiify!
	 */

	public final static String autograder_seed = "6f1b7ea9aac470402d48f7916ea6a010";

	private static void autograder_compilation_check() {

		try {
			Order o = null;
			Pizza p = null;
			Topping t = null;
			Discount d = null;
			Customer c = null;
			ArrayList<Order> alo = null;
			ArrayList<Discount> ald = null;
			ArrayList<Customer> alc = null;
			ArrayList<Topping> alt = null;
			double v = 0.0;
			String s = "";

			DBNinja.addOrder(o);
			DBNinja.addPizza(p);
			DBNinja.useTopping(p, t, false);
			DBNinja.usePizzaDiscount(p, d);
			DBNinja.useOrderDiscount(o, d);
			DBNinja.addCustomer(c);
			DBNinja.completeOrder(o);
			alo = DBNinja.getOrders(false);
			o = DBNinja.getLastOrder();
			alo = DBNinja.getOrdersByDate("01/01/1999");
			ald = DBNinja.getDiscountList();
			d = DBNinja.findDiscountByName("Discount");
			alc = DBNinja.getCustomerList();
			c = DBNinja.findCustomerByPhone("0000000000");
			alt = DBNinja.getToppingList();
			t = DBNinja.findToppingByName("Topping");
			DBNinja.addToInventory(t, 1000.0);
			v = DBNinja.getBaseCustPrice("size", "crust");
			v = DBNinja.getBaseBusPrice("size", "crust");
			DBNinja.printInventory();
			DBNinja.printToppingPopReport();
			DBNinja.printProfitByPizzaReport();
			DBNinja.printProfitByOrderType();
			s = DBNinja.getCustomerName(0);
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}

}
