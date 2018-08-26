import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SuppressWarnings("serial")
public class library implements Serializable {
	
	private static final String libraryFile = "library.obj";	//change from LIBRARY_FILE to libraryFile, naming convention
	private static final int loanLimit = 2;	//Change from LOAN_LIMIT to loanLimit,int naming convention 
	private static final int loanPeriod = 2;	//change form LOAN_PERIOD to loanPeriod,  naming convention
	private static final double finePerDay = 1.0;	   //Change from FINE_PER_DAY to finePerDay, naming convention
	private static final double maxFinesOwed = 5.0;   //Change from MAX_FINES_OWED to maxFinesOwed, naming convention
	private static final double damageFee = 2.0;	//Change from DAMAGE_FEE to damageFee, naming convention
	
	private static library self;
	private int bid;	//Change from BID to bid, naming convention
	private int mid;	//Change from MID to mid, naming convention
	private int lid;	//Change from LID to lid, naming convention
	private Date loadDate;
	
	private Map<Integer, book> catalog;
	private Map<Integer, member> members;
	private Map<Integer, loan> loans;
	private Map<Integer, loan> currentLoans;
	private Map<Integer, book> damagedBooks;
	

	private library() {
		catalog = new HashMap<>();
		members = new HashMap<>();
		loans = new HashMap<>();
		currentLoans = new HashMap<>();
		damagedBooks = new HashMap<>();
		bid = 1;	//Change from BID to bid, naming convention
		mid = 1;	//Change from MID to mid, naming convention		
		lid = 1;	//Change from LID to lid, naming convention		
	}

	
	public static synchronized library Instance() {		//Change form INSTANCE to Instance, class naming convention	
		if (self == null) {
			Path path = Paths.get(libraryFile);	//change from LIBRARY_FILE to libraryFile, naming convention			
			if (Files.exists(path)) {	
				try (ObjectInputStream lof = new ObjectInputStream(new FileInputStream(libraryFile));) {  //change from LIBRARY_FILE to libraryFile, naming convention
			    
					self = (library) lof.readObject();
					Calendar.getInstance().setDate(self.loadDate);
					lof.close();
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
			else self = new library();
		}
		return self;
	}

	
	public static synchronized void Save() {	//Change from SAVE to Save,class naming convention
		if (self != null) {
			self.loadDate = Calendar.getInstance().Date();
			try (ObjectOutputStream lof = new ObjectOutputStream(new FileOutputStream(libraryFile));) {	//change from LIBRARY_FILE to libraryFile, naming convention
				lof.writeObject(self);	
				lof.flush();
				lof.close();	
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
		}
	}

	
	public int BookID() {
		return bid;	//Change from BID to bid, naming convention
	}
	
	
	public int MemberID() {
		return mid;	//Change from MID to mid, naming convention
	}
	
	
	private int nextBID() {
		return bid++;	//Change from BID to bid,naming convention
	}

	
	private int nextMID() {
		return mid++;	//Change from MID to mid,naming convention
	}

	
	private int nextLID() {
		return lid++;	//Change from LID to lid,naming convention
	}

	
	public List<member> Members() {		
		return new ArrayList<member>(members.values()); 
	}


	public List<book> Books() {		
		return new ArrayList<book>(catalog.values()); 
	}


	public List<loan> CurrentLoans() {
		return new ArrayList<loan>(currentLoans.values());
	}


	public member AddMem(String lastName, String firstName, String email, int phoneNo) {	//change from Add_mem to AddMem,class naming convention	
		member member = new member(lastName, firstName, email, phoneNo, nextMID());
		members.put(member.getId(), member);		
		return member;
	}

	
	public book AddBook(String a, String t, String c) {	//Change from Add_book to AddBook,class naming convention	
		book b = new book(a, t, c, nextBID());
		catalog.put(b.ID(), b);		
		return b;
	}

	
	public member getMember(int memberId) {
		if (members.containsKey(memberId)) 
			return members.get(memberId);
		return null;
	}

	
	public book Book(int bookId) {
		if (catalog.containsKey(bookId)) 
			return catalog.get(bookId);		
		return null;
	}

	
	public int LoanLimit() {	//change in class name from loanLimit to LoanLimit
		return loanLimit;	//Change from LOAN_LIMIT to loanLimit,naming convention
	}

	
	public boolean memberCanBorrow(member member) {		
		if (member.getNumberOfCurrentLoans() == loanLimit ) //Change from LOAN_LIMIT to loanLimit,naming convention
			return false;
				
		if (member.getFinesOwed() >= maxFinesOwed) 
			return false;
				
		for (loan loan : member.getLoans()) 
			if (loan.isOverDue()) 
				return false;
			
		return true;
	}

	
	public int loansRemainingForMember(member member) {		
		return loanLimit - member.getNumberOfCurrentLoans();	//Change from LOAN_LIMIT to loanLimit,naming convention
	}

	
	public loan issueLoan(book book, member member) {
		Date dueDate = Calendar.getInstance().getDueDate(loanPeriod);	//Change from LOAN_PERIOD to loanPeriod,naming convention
		loan loan = new loan(nextLID(), book, member, dueDate);
		member.takeOutLoan(loan);
		book.Borrow();
		loans.put(loan.getId(), loan);
		currentLoans.put(book.ID(), loan);
		return loan;
	}
	
	
	public loan getLoanByBookId(int bookId) {
		if (currentLoans.containsKey(bookId)) {
			return currentLoans.get(bookId);
		}
		return null;
	}

	
	public double calculateOverDueFine(loan loan) {
		if (loan.isOverDue()) {
			long daysOverDue = Calendar.getInstance().getDaysDifference(loan.getDueDate());
			double fine = daysOverDue * finePerDay;
			return fine;
		}
		return 0.0;		
	}


	public void dischargeLoan(loan currentLoan, boolean isDamaged) {
		member member = currentLoan.Member();
		book book  = currentLoan.Book();
		
		double overDueFine = calculateOverDueFine(currentLoan);
		member.addFine(overDueFine);	
		
		member.dischargeLoan(currentLoan);
		book.Return(isDamaged);
		if (isDamaged) {
			member.addFine(damageFee);	//Change from DAMAGE_FEE to damageFee,naming convention
			damagedBooks.put(book.ID(), book);
		}
		currentLoan.Loan();
		currentLoans.remove(book.ID());
	}


	public void checkCurrentLoans() {
		for (loan loan : currentLoans.values()) {
			loan.checkOverDue();
		}		
	}


	public void repairBook(book currentBook) {
		if (damagedBooks.containsKey(currentBook.ID())) {
			currentBook.Repair();
			damagedBooks.remove(currentBook.ID());
		}
		else {
			throw new RuntimeException("Library: repairBook: book is not damaged");
		}
		
	}
	
	
}
