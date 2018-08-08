import java.text.SimpleDateFormat;
import java.util.Scanner;


public class MainCode { // Class name was confusing it was not advisable to have class or method name as Main or main(changed from Main to MainCode)
	// Made changes to only initialization section of this code
	
	private static Scanner sc;// changed the object name from IN to sc  new
	private static library lib;// changed the object name from Capital LIB to lib 
	private static String menu; // changed the variable name from capital MENU to menu 
	private static Calendar cal;// changed the object name from capital CAL to cal
	private static SimpleDateFormat sdf;// changed the object name from SDF to sdf 
	
	
	private static String Get_menu() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("\nLibrary Main Menu\n\n")
		//instance name sb was missing in below line
		sb.append("  M  : add member\n")
		sb.append("  LM : list members\n")
		sb.append("\n")
		sb.append("  B  : add book\n")
		sb.append("  LB : list books\n")
		sb.append("  FB : fix books\n")
		sb.append("\n")
		sb.append("  L  : take out a loan\n")
		sb.append("  R  : return a loan\n")
		sb.append("  LL : list loans\n")
		sb.append("\n")
		sb.append("  P  : pay fine\n")
		sb.append("\n")
		sb.append("  ID  : increment date\n")//The menu option was not suitable changed from T to ID
		sb.append("  Q  : quit\n")
		sb.append("\n")
		sb.append("Choice : ");
		  
		return sb.toString();
	}


	public static void main(String[] args) {		
		try {	
			// reflect the changes made above in below section (initialization section)		
			sc = new Scanner(System.in);
			lib = library.INSTANCE();
			cal = Calendar.getInstance();
			sdf = new SimpleDateFormat("dd/MM/yyyy");
	
			for (member m : lib.Members()) {
				output(m);
			}
			output(" ");
			for (book b : lib.Books()) {
				output(b);
			}
						
			menu = Get_menu();
			
			boolean e = false;
			
			while (!e) {
				
				output("\n" + sdf.format(cal.Date()));
				String c = input(menu);
				
				switch (c.toUpperCase()) {
				
				case "M": 
					addMember();
					break;
					
				case "LM": 
					listMembers();
					break;
					
				case "B": 
					addBook();
					break;
					
				case "LB": 
					listBooks();
					break;
					
				case "FB": 
					fixBooks();
					break;
					
				case "L": 
					borrowBook();
					break;
					
				case "R": 
					returnBook();
					break;
					
				case "LL": 
					listCurrentLoans();
					break;
					
				case "P": 
					payFine();
					break;
					// chaned above T to ID
				case "ID": 
					incrementDate();
					break;
					
				case "Q": 
					e = true;
					break;
					
				default: 
					output("\nInvalid option\n");
					break;
				}
				// method name cannot be all caps should follow one naming convention.
				library.save();
			}			
		} catch (RuntimeException e) {
			output(e);
		}		
		output("\nEnded\n");
	}	

		private static void payFine() {
		new PayFineUI(new PayFineControl()).run();		
	}


	private static void listCurrentLoans() {
		output("");
		// changed LIB to lib
		for (loan loan : lib.CurrentLoans()) {
			output(loan + "\n");
		}		
	}



	private static void listBooks() {
		output("");
		// in for loop class name variable type changed and method name changed
		for (Book book : lib.books()) {
			output(book + "\n");
		}		
	}



	private static void listMembers() {
		output("");
		// in for loop class name variable type changed and method name changed
		for (Member member : lib.members()) {
			output(member + "\n");
		}		
	}



	private static void borrowBook() {
		new BorrowBookUI(new BorrowBookControl()).run();		
	}


	private static void returnBook() {
		new ReturnBookUI(new ReturnBookControl()).run();		
	}


	private static void fixBooks() {
		new FixBookUI(new FixBookControl()).run();		
	}


	private static void incrementDate() {
		try {
			int days = Integer.valueOf(input("Enter number of days: ")).intValue();
			//changed object names
			cal.incrementDate(days);
			lib.checkCurrentLoans();
			output(sdf.format(cal.Date()));
			
		} catch (NumberFormatException e) {
			 output("\nInvalid number of days\n");
		}
	}


	private static void addBook() {
		
		String author = input("Enter author: ");
		String title  = input("Enter title: ");
		String callNo = input("Enter call number: ");
		//  variable type changed and method name changed (naming convention)
		Book book = lib.addBook(author, title, callNo);
		output("\n" + book + "\n");
		
	}

	
	private static void addMember() {
		try {
			String lastName = input("Enter last name: ");
			String firstName  = input("Enter first name: ");
			String email = input("Enter email: ");
			int phoneNo = Integer.valueOf(input("Enter phone number: ")).intValue();
			// variable type changed and method name changed (naming convention)
			Member member = lib.addMem(lastName, firstName, email, phoneNo);
			output("\n" + member + "\n");
			
		} catch (NumberFormatException e) {
			 output("\nInvalid phone number\n");
		}
		
	}


	private static String input(String prompt) {
		System.out.print(prompt);
		return IN.nextLine();
	}
	
	
	
	private static void output(Object object) {
		System.out.println(object);
	}

	
}
