package cpsc4620;

import java.io.IOException;
import java.sql.*;
import java.time.LocalTime;
import java.util.*;
import java.util.Date;

/*
 * This file is where most of your code changes will occur You will write the code to retrieve
 * information from the database, or save information to the database
 * 
 * The class has several hard coded static variables used for the connection, you will need to
 * change those to your connection information
 * 
 * This class also has static string variables for pickup, delivery and dine-in. If your database
 * stores the strings differently (i.e "pick-up" vs "pickup") changing these static variables will
 * ensure that the comparison is checking for the right string in other places in the program. You
 * will also need to use these strings if you store this as boolean fields or an integer.
 * 
 * 
 */

/**
 * A utility class to help add and retrieve information from the database
 */

public final class DBNinja {
	private static Connection conn;

	// Change these variables to however you record dine-in, pick-up and delivery, and sizes and crusts
	public final static String pickup = "Pick-Up";
	public final static String delivery = "delivery";
	public final static String dine_in = "dine-in";

	public final static String size_s = "Small";
	public final static String size_m = "Medium";
	public final static String size_l = "Large";
	public final static String size_xl = "XLarge";

	public final static String crust_thin = "Thin";
	public final static String crust_orig = "Original";
	public final static String crust_pan = "Pan";
	public final static String crust_gf = "Gluten-Free";



	
	private static boolean connect_to_db() throws SQLException, IOException {

		try {
			conn = DBConnector.make_connection();
			return true;
		} catch (SQLException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

	}

	
	public static void addOrder(Order o) throws SQLException, IOException 
	{
		connect_to_db();
		String query = "Insert into `order`(Ord_Date,Ord_Time,Ord_State,Ord_Type,Ord_Price,Ord_Cost) values(?,?,?,?,?,?); ";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setString(1, o.getDate());
		os.setString(2, LocalTime.now().toString());
		os.setInt(3, o.getIsComplete());
		os.setString(4, o.getOrderType());
		os.setDouble(5, o.getCustPrice());
		os.setDouble(6, o.getBusPrice());
		os.execute();
		conn.close();
	}
	
	public static void fillOrderType(Order o) throws SQLException, IOException {
		connect_to_db();
		PreparedStatement os;
		String query = "";
		connect_to_db();
		if(o.getOrderType().equals(dine_in)) {
			DineinOrder d = (DineinOrder)o;
			query = "Insert into dine_in values(?,?); ";
			os = conn.prepareStatement(query);
			os.setInt(1, o.getOrderID());
			os.setInt(2, d.getTableNum());
			os.execute();
		}
		else if(o.getOrderType().equals(delivery)) {
			DeliveryOrder d = (DeliveryOrder)o;
			query = "Insert into delivery values(?,?,?,?,?); ";
			os = conn.prepareStatement(query);
			os.setInt(1, o.getOrderID());
			String[] address = d.getAddress().split(",");
			String street = address[0];
			String state = address[2];
			String zip = address[3];
			os.setInt(2, o.getCustID());
			os.setString(3, street);
			os.setString(4, state);
			os.setString(5, zip);
			os.execute();
		}
		else {
			PickupOrder p = (PickupOrder)o;
			query = "Insert into pick_up values(?,?); ";
			os = conn.prepareStatement(query);
			os.setInt(1, o.getOrderID());
			os.setInt(2, p.getCustID());
			os.execute();
		}
		conn.close();
	}
	
	public static void addPizza(Pizza p) throws SQLException, IOException
	{
		/*
		 * Add the code needed to insert the pizza into into the database.
		 * Keep in mind adding pizza discounts and toppings associated with the pizza,
		 * there are other methods below that may help with that process.
		 * 
		 */
		connect_to_db();
		String query = "Insert into pizza (Pizza_Cost,Pizza_Price,Pizza_State, BP_ID,Ord_ID) values(?,?,?,?,?); ";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setDouble(1, p.getBusPrice());
		os.setDouble(2, p.getCustPrice());
		os.setString(3, p.getPizzaState());
		os.setInt(4, Integer.parseInt(p.getCrustType()));
		os.setInt(5, p.getOrderID());
		os.execute();
		conn.close();
	}
	
	
	public static void useTopping(Pizza p, Topping t, boolean isDoubled) throws SQLException, IOException //this method will update toppings inventory in SQL and add entities to the Pizzatops table. Pass in the p pizza that is using t topping
	{
		connect_to_db();
		/*
		 * This method should do 2 two things.
		 * - update the topping inventory every time we use t topping (accounting for extra toppings as well)
		 * - connect the topping to the pizza
		 *   What that means will be specific to your yimplementatinon.
		 * 
		 * Ideally, you should't let toppings go negative....but this should be dealt with BEFORE calling this method.
		 * 
		 */
		connect_to_db();
		String query = "INSERT into pizza_topping(Pizza_ID, T_ID, Extra_Topping) values(?,?,?);";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setInt(1, p.getPizzaID());
		os.setInt(2, t.getTopID());
		os.setBoolean(3, isDoubled);
		os.execute();
		query = "UPDATE topping SET Curr_Inv_Level = ? WHERE T_ID=?;";
		os = conn.prepareStatement(query);
		if(p.getSize().equals("1")) {
			if(isDoubled)
				os.setDouble(1, t.getCurINVT() - 2 * t.getPerAMT());
			else
				os.setDouble(1, t.getCurINVT() - t.getPerAMT());
		}
		if(p.getSize().equals("2")) {
			if(isDoubled)
				os.setDouble(1, t.getCurINVT() - 2 * t.getMedAMT());
			else
				os.setDouble(1, t.getCurINVT() - t.getMedAMT());
		}
		if(p.getSize().equals("3")) {
			if(isDoubled)
				os.setDouble(1, t.getCurINVT() - 2 * t.getLgAMT());
			else
				os.setDouble(1, t.getCurINVT() -  t.getLgAMT());
		}
		if(p.getSize().equals("4")) {
			if(isDoubled)
				os.setDouble(1, t.getCurINVT() - 2 * t.getXLAMT());
			else
				os.setDouble(1, t.getCurINVT() - t.getXLAMT());
		}
		os.setInt(2, t.getTopID());
		os.execute();
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	
	public static void usePizzaDiscount(Pizza p, Discount d) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * This method connects a discount with a Pizza in the database.
		 * 
		 * What that means will be specific to your implementatinon.
		 */
		String query = "INSERT into pizza_discount(Discount_ID, Pizza_ID) values(?,?);";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setInt(1, d.getDiscountID());
		os.setInt(2, p.getPizzaID());
		os.execute();
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void useOrderDiscount(Order o, Discount d) throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * This method connects a discount with an order in the database
		 * 
		 * You might use this, you might not depending on where / how to want to update
		 * this information in the dabast
		 */
		String query = "INSERT into order_discount(Discount_ID, Ord_ID) values(?,?);";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setInt(1, d.getDiscountID());
		os.setInt(2, o.getOrderID());
		os.execute();
		conn.close();			
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static int getLatestCustomer() throws SQLException, IOException {
		connect_to_db();
		String query = "Select * from customer;";
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rset = stmt.executeQuery(query);
		rset.last();
		int cus_ID = rset.getInt("Cus_ID");
		rset.close();
		return cus_ID;
	}
	
	public static void addCustomer(Customer c) throws SQLException, IOException {
		connect_to_db();
		/*
		 * This method adds a new customer to the database.
		 * 
		 */
		String query = "INSERT into customer(Cus_Fname, Cus_Lname, Cus_PhoneNumber ) values(?,?,?);";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setString(1, c.getFName());
		os.setString(2, c.getLName());
		os.setString(3, c.getPhone());
		os.execute();
		conn.close();	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}

	public static void completeOrder(Order o) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Find the specifed order in the database and mark that order as complete in the database.
		 * 
		 */
		String query = "UPDATE `order` SET Ord_State = True WHERE Ord_ID = ? ";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setInt(1, o.getOrderID());
		os.execute();
		conn.close();	
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}


	public static ArrayList<Order> getOrders(boolean openOnly) throws SQLException, IOException {
		connect_to_db();
		/*
		 * Return an arraylist of all of the orders.
		 * 	openOnly == true => only return a list of open (ie orders that have not been marked as completed)
		 *           == false => return a list of all the orders in the database
		 * Remember that in Java, we account for supertypes and subtypes
		 * which means that when we create an arrayList of orders, that really
		 * means we have an arrayList of dineinOrders, deliveryOrders, and pickupOrders.
		 * 
		 * Don't forget to order the data coming from the database appropriately.
		 * 
		 */
		ArrayList<Order> orderList = new ArrayList<Order>();
		ResultSet rset;
		Statement stmt;
		if(!openOnly) {
			String query = "SELECT * FROM (SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join pick_up on `order`.Ord_ID = pick_up.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join delivery on `order`.Ord_ID = delivery.Ord_ID union  SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, null as Cus_ID FROM `order` join dine_in on `order`.Ord_ID = dine_in.Ord_ID) AS A where Ord_State IS False order by Ord_ID;";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
		}
		else {
			String query = "SELECT * FROM (SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join pick_up on `order`.Ord_ID = pick_up.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join delivery on `order`.Ord_ID = delivery.Ord_ID union  SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, null as Cus_ID FROM `order` join dine_in on `order`.Ord_ID = dine_in.Ord_ID) AS A where Ord_State IS True order by Ord_ID;";
			stmt = conn.createStatement();
			rset = stmt.executeQuery(query);
		}
		while(rset.next())
		{
			orderList.add(new Order(rset.getInt("Ord_ID"),rset.getInt("Cus_ID"), rset.getString("Ord_Type"), rset.getString("Ord_Date"), rset.getDouble("Ord_Price"),rset.getDouble("Ord_Cost"), rset.getInt("Ord_State")));
		}
		conn.close();
		return orderList;		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION

	}
	
	public static Order getLastOrder() throws SQLException, IOException{
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();
		String query = "(SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join pick_up on `order`.Ord_ID = pick_up.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join delivery on `order`.Ord_ID = delivery.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, null as Cus_ID FROM `order` join dine_in on `order`.Ord_ID = dine_in.Ord_ID) order by Ord_ID;" ;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rset = stmt.executeQuery(query);
		rset.last();
		Order order = new Order(rset.getInt("Ord_ID"),rset.getInt("Cus_ID"), rset.getString("Ord_Type"), rset.getString("Ord_Date"), rset.getDouble("Ord_Price"),rset.getDouble("Ord_Cost"), rset.getInt("Ord_State"));	
		conn.close();
		return order;
	}
	
	public static Order getOrderById(int id) throws SQLException, IOException{
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();

		
		PreparedStatement os;
		ResultSet rset;
		String query2;
		query2 = "select * from (SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join pick_up on `order`.Ord_ID = pick_up.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join delivery on `order`.Ord_ID = delivery.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, null as Cus_ID FROM `order` join dine_in on `order`.Ord_ID = dine_in.Ord_ID) AS A where Ord_ID = ? order by Ord_ID;" ;
		os = conn.prepareStatement(query2);
		os.setInt(1, id);
		rset = os.executeQuery();
		if(!rset.next()) {
			return null;
		}
		Order order = new Order(rset.getInt("Ord_ID"),rset.getInt("Cus_ID"), rset.getString("Ord_Type"), rset.getString("Ord_Date"), rset.getDouble("Ord_Price"),rset.getDouble("Ord_Cost"), rset.getInt("Ord_State"));	
		conn.close();
		return order;
	}
	
	public static int getOrderId() throws SQLException, IOException{
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();
		String query = "select * from `order`";
		PreparedStatement os;
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		
		ResultSet rset = stmt.executeQuery(query);
		rset.last();
		int orderId = rset.getInt("Ord_ID");
		conn.close();
		return orderId;
	}
	
	public static int getOrders() throws SQLException, IOException{
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();
		String query = "select * from `order`";
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rset = stmt.executeQuery(query);
		rset.last();
		int orderId = rset.getInt("Ord_ID");
		conn.close();
		return orderId;
	}
	
	public static int getPizzaId() throws SQLException, IOException{
		/*
		 * Query the database for the LAST order added
		 * then return an Order object for that order.
		 * NOTE...there should ALWAYS be a "last order"!
		 */
		connect_to_db();
		String query = "select * from pizza;";
		Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		ResultSet rset = stmt.executeQuery(query);
		rset.last();
		int pizza_id = rset.getInt("Pizza_ID");
		conn.close();
		return pizza_id;
	}

	public static ArrayList<Order> getOrdersByDate(String date) throws SQLException, IOException{
		/*
		 * Query the database for ALL the orders placed on a specific date
		 * and return a list of those orders.
		 *  
		 */
		connect_to_db();
		
		String query;
		query = "SELECT * FROM (SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join pick_up on `order`.Ord_ID = pick_up.Ord_ID union SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, Cus_ID FROM `order` join delivery on `order`.Ord_ID = delivery.Ord_ID union  SELECT `order`.Ord_ID, Ord_Date, Ord_State, Ord_Type, Ord_Price, Ord_Cost, null as Cus_ID FROM `order` join dine_in on `order`.Ord_ID = dine_in.Ord_ID) AS A WHERE Ord_Date>= ? order by Ord_ID;";
		PreparedStatement os = conn.prepareStatement(query);
		os.setString(1, date);
		ResultSet rset = os.executeQuery();
		ArrayList<Order> orderList = new ArrayList<Order>();
		while(rset.next())
		{
			orderList.add(new Order(rset.getInt("Ord_ID"),rset.getInt("Cus_ID"), rset.getString("Ord_Type"), rset.getString("Ord_Date"), rset.getDouble("Ord_Price"),rset.getDouble("Ord_Cost"), rset.getInt("Ord_State")));
		}
		return orderList;
	}
		
	public static ArrayList<Discount> getDiscountList() throws SQLException, IOException {
		connect_to_db();
		ArrayList<Discount> discountList = new ArrayList<Discount>();
		String query = "Select * From discount;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			if(rset.getDouble("Amount_Off") == 0)
				discountList.add(new Discount(rset.getInt("Discount_ID"), rset.getString("Discount_Name"), rset.getDouble("Percent_Off"), true));
			else
				discountList.add(new Discount(rset.getInt("Discount_ID"), rset.getString("Discount_Name"), rset.getDouble("Amount_Off"), false));
		}
		conn.close();
		return discountList;
	}

	public static Discount findDiscountByName(String name) throws SQLException, IOException{
		connect_to_db();
		PreparedStatement os;
		ResultSet rset;
		String query;
		query = "Select * FROM discount WHERE Discount_Name = ?;";
		os = conn.prepareStatement(query);
		os.setString(1, name);
		rset = os.executeQuery();
		if(!rset.next()) {
			return null;
		}
		Discount d;
		if(rset.getDouble("Amount_Off") == 0)
			d = new Discount(rset.getInt("Discount_ID"), rset.getString("Discount_Name"), rset.getDouble("Percent_Off"), true);
		else
			d = new Discount(rset.getInt("Discount_ID"), rset.getString("Discount_Name"), rset.getDouble("Amount_Off"), false);
		conn.close();
		return d;
	}


	public static ArrayList<Customer> getCustomerList() throws SQLException, IOException {
		connect_to_db();
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		String query = "Select * From customer order by Cus_FName, Cus_LName, Cus_PhoneNumber;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			customerList.add(new Customer(rset.getInt("Cus_ID"), rset.getString("Cus_FName"), rset.getString("Cus_LName"), rset.getString("Cus_PhoneNumber")));
		}
		conn.close();
		return customerList;
	}

	public static Customer findCustomerByPhone(String phoneNumber) throws SQLException, IOException{
		/*
		 * Query the database for a customer using a phone number.
		 * If found, then return a Customer object for the customer.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		String query;
		query = "Select * FROM customer WHERE Cus_PhoneNumber = ?;";
		PreparedStatement os = conn.prepareStatement(query);
		os.setString(1, phoneNumber);
		ResultSet rset = os.executeQuery();
		if(!rset.next()) {
			return null;
		}
		Customer customer = new Customer(rset.getInt("Cus_ID"), rset.getString("Cus_FName"), rset.getString("Cus_LName"), rset.getString("Cus_PhoneNumber"));
		return customer;
	}


	public static ArrayList<Topping> getToppingList() throws SQLException, IOException {
		connect_to_db();
		ArrayList<Topping> toppingList = new ArrayList<Topping>();
		String query = "select * from  topping;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			toppingList.add(new Topping(rset.getInt("T_ID"), rset.getString("T_Name"), rset.getDouble("T_Personal"), rset.getDouble("T_Medium"), rset.getInt("T_Large"), rset.getInt("T_XLarge"), 
					rset.getInt("T_Price"), rset.getInt("T_Cost"), rset.getInt("Min_Inv_Level"), rset.getInt("Curr_Inv_Level")));
		}

		conn.close();
		return toppingList;
	}

	public static Topping findToppingByName(String name) throws SQLException, IOException{
		/*
		 * Query the database for the topping using it's name.
		 * If found, then return a Topping object for the topping.
		 * If it's not found....then return null
		 *  
		 */
		connect_to_db();
		PreparedStatement os;
		ResultSet rset;
		String query;
		query = "Select * FROM topping WHERE T_Name = ?;";
		os = conn.prepareStatement(query);
		os.setString(1, name);
		rset = os.executeQuery();
		rset.next();
		Topping t = new Topping(rset.getInt("T_ID"), rset.getString("T_Name"), rset.getDouble("T_Personal"), rset.getDouble("T_Medium"), rset.getDouble("T_Large"), rset.getDouble("T_XLarge"), 
				rset.getDouble("T_Price"), rset.getDouble("T_Cost"), rset.getInt("Min_Inv_Level"), rset.getInt("Curr_Inv_Level"));
		conn.close();
		return t;
	}

	public static void upDateCostAndPrice(Pizza p) throws SQLException, IOException {
		connect_to_db();
		PreparedStatement os;
		String query = "UPDATE pizza SET Pizza_Cost = ?, Pizza_Price = ? WHERE Pizza_ID = ?;";
		os = conn.prepareStatement(query);
		os.setDouble(1, p.getBusPrice());
		os.setDouble(2, p.getCustPrice());
		os.setInt(3, p.getPizzaID());
		os.execute();
		conn.close();
		
	}
	
	public static void upDateCostAndPrice(Order o) throws SQLException, IOException {
		connect_to_db();
		PreparedStatement os;
		String query = "UPDATE `order` SET Ord_Cost = ?, Ord_Price = ? WHERE Ord_ID = ?;";
		os = conn.prepareStatement(query);
		os.setDouble(1, o.getBusPrice());
		os.setDouble(2, o.getCustPrice());
		os.setInt(3, o.getOrderID());
		os.execute();
		conn.close();
	}

	public static void addToInventory(Topping t, double quantity) throws SQLException, IOException {
		connect_to_db();
		String query = "SELECT Curr_Inv_Level FROM topping WHERE T_ID=?;";
		PreparedStatement os;
		os = conn.prepareStatement(query);
		os.setInt(1, t.getTopID());
		ResultSet rset = os.executeQuery();
		rset.next();
		quantity += rset.getDouble("Curr_Inv_Level");
		query = "UPDATE topping SET Curr_Inv_Level = ? WHERE T_ID=?;";
		os = conn.prepareStatement(query);
		os.setDouble(1, quantity);
		os.setInt(2, t.getTopID());
		os.execute();
		conn.close();
	}
	
	public static double getBaseCustPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		/* 
		 * Query the database from the base customer price for that size and crust pizza.
		 * 
		*/
		PreparedStatement os;
		ResultSet rset2;
		String query;
		query = "Select BP_Price From base_price WHERE BP_Size = ? and BP_Crust = ?;";
		os = conn.prepareStatement(query);
		os.setString(1, size);
		os.setString(2, crust);
		rset2 = os.executeQuery();
		rset2.next();
		double temp = rset2.getDouble("BP_Price");
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		return temp;
	}

	public static double getBaseBusPrice(String size, String crust) throws SQLException, IOException {
		connect_to_db();
		PreparedStatement os;
		ResultSet rset2;
		String query;
		query = "Select BP_Cost From base_price WHERE BP_Size = ? and BP_Crust = ?;";
		os = conn.prepareStatement(query);
		os.setString(1, size);
		os.setString(2, crust);
		rset2 = os.executeQuery();
		rset2.next();
		double temp = rset2.getDouble("BP_Cost");
		conn.close();
		return temp;
		//DO NOT FORGET TO CLOSE YOUR CONNECTION

	}

	public static void printInventory() throws SQLException, IOException {
		connect_to_db();
		/*
		 * Queries the database and prints the current topping list with quantities.
		 *  
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		ArrayList<Topping> toppingList = getToppingList();
		for(Topping topping : toppingList) {
			System.out.println(topping.getTopID()+" "+topping.getTopName()+" "+topping.getCurINVT());
		}
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION


	}
	
	public static void printToppingPopReport() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ToppingPopularity view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		String query = "Select * from ToppingPopularity;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			System.out.println(rset.getString("T_Name")+" "+rset.getInt("ToppingCount")); 
		}
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void printProfitByPizzaReport() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ProfitByPizza view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		String query = "Select * from ProfitByPizza;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			System.out.println(rset.getString("Size")+"  "+rset.getString("Crust")+"  "+rset.getDouble("Profit")+"  "+rset.getString("Order Month")); 
		}
		conn.close();
		
		
		
		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
	}
	
	public static void printProfitByOrderType() throws SQLException, IOException
	{
		connect_to_db();
		/*
		 * Prints the ProfitByOrderType view. Remember that this view
		 * needs to exist in your DB, so be sure you've run your createViews.sql
		 * files on your testing DB if you haven't already.
		 * 
		 * The result should be readable and sorted as indicated in the prompt.
		 * 
		 */
		String query = "Select * from ProfitByOrderType;";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			System.out.println(rset.getString("CustomerType")+" "+rset.getString("Order Month")+" "+rset.getDouble("TotalOrderPrice")+" "+rset.getDouble("TotalOrderCost")+" "+rset.getDouble("Profit")); 
		}
		conn.close();		
		//DO NOT FORGET TO CLOSE YOUR CONNECTION	
	}
	

	
	
	
	public static String getCustomerName(int CustID) throws SQLException, IOException
	{
	/*
		 * This is a helper method to fetch and format the name of a customer
		 * based on a customer ID. This is an example of how to interact with 
		 * your database from Java.  It's used in the model solution for this project...so the code works!
		 * 
		 * OF COURSE....this code would only work in your application if the table & field names match!
		 *
		 */

		 connect_to_db();

		/* 
		 * an example query using a constructed string...
		 * remember, this style of query construction could be subject to sql injection attacks!
		 * 
		 */
		String cname1 = "";
		String query = "Select Cus_Fname, Cus_Lname From customer WHERE Cus_ID=" + CustID + ";";
		Statement stmt = conn.createStatement();
		ResultSet rset = stmt.executeQuery(query);
		
		while(rset.next())
		{
			cname1 = rset.getString(1) + " " + rset.getString(2); 
		}

		/* 
		* an example of the same query using a prepared statement...
		* 
		*/
		String cname2 = "";
		PreparedStatement os;
		ResultSet rset2;
		String query2;
		query2 = "Select Cus_Fname, Cus_Lname From customer WHERE Cus_ID=?;";
		
		os = conn.prepareStatement(query2);
		os.setInt(1, CustID);
		rset2 = os.executeQuery();
		while(rset2.next())
		{
			cname2 = rset2.getString("Cus_Fname") + " " + rset2.getString("Cus_Lname"); // note the use of field names in the getSting methods
		}

		conn.close();
		return cname1; // OR cname2
	}

	/*
	 * The next 3 private methods help get the individual components of a SQL datetime object. 
	 * You're welcome to keep them or remove them.
	 */
	private static int getYear(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(0,4));
	}
	private static int getMonth(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(5, 7));
	}
	private static int getDay(String date)// assumes date format 'YYYY-MM-DD HH:mm:ss'
	{
		return Integer.parseInt(date.substring(8, 10));
	}

	public static boolean checkDate(int year, int month, int day, String dateOfOrder)
	{
		if(getYear(dateOfOrder) > year)
			return true;
		else if(getYear(dateOfOrder) < year)
			return false;
		else
		{
			if(getMonth(dateOfOrder) > month)
				return true;
			else if(getMonth(dateOfOrder) < month)
				return false;
			else
			{
				if(getDay(dateOfOrder) >= day)
					return true;
				else
					return false;
			}
		}
	}


	public static String findToppingNameByID(int opt) throws SQLException, IOException {
		// TODO Auto-generated method stub
		connect_to_db();
		String query2;
		query2 = "Select T_Name From topping WHERE T_ID=?;";
		PreparedStatement os;
		ResultSet rset2;
		os = conn.prepareStatement(query2);
		os.setInt(1, opt);
		rset2 = os.executeQuery();
		rset2.next();
		String temp = rset2.getString("T_Name");
		conn.close();
		return temp;

	}


	public static int getBaseId(String baseSize, String baseCrust) throws SQLException, IOException {
		connect_to_db();
		PreparedStatement os;
		ResultSet rset2;
		String query;
		query = "Select BP_Price From base_price WHERE BP_Size = ? and BP_Crust = ?;";
		os = conn.prepareStatement(query);
		os.setString(1, baseSize);
		os.setString(2, baseCrust);
		rset2 = os.executeQuery();
		rset2.next();
		int temp = rset2.getInt("BP_ID");
		conn.close();
		//DO NOT FORGET TO CLOSE YOUR CONNECTION
		return temp;
	}


}