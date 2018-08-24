import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
<<<<<<< HEAD
	private library l; // Changed the variable name
	private member m; // Changed the variable name
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
=======
	private Library lib; // Changed the object name //  changed the class name as well library to Library
	private Member memb; // Changed the object name // changed the class name as well member to Member
	private enum ControlState { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED }; // followed one naming convention for enum
	private ControlState state;
>>>>>>> waqas
	
	private List<book> pending; // naming conventions
	private List<loan> completed; // naming conventions
	private Book book1; // naming conventions and also object name should be meaningful
	
	
<<<<<<< HEAD
	public BorrowBookControl() {
		this.l = l.INSTANCE(); // Change the variable name 
		state = CONTROL_STATE.INITIALISED;
=======
	public BorrowBookControl() { // Changing in constructor
		this.lib = lib.INSTANCE();
		state = ControlState.INITIALISED;
>>>>>>> waqas
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {  // added curly brackets in if condition 
		     throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			 }
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UiState.READY);
		state = ControlState.READY;		
	}

		
	public void swiped(int memberId) { // All changing in a method 
		if (!state.equals(ControlState.READY))  {
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
<<<<<<< HEAD
			
		M = l.getMember(memberId); 
		if (M == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (l.memberCanBorrow(M)) {
			PENDING = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
=======
		}
		
		member = lib.getMember(memberId);
		if (member == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (lib.memberCanBorrow(m)) {
			pending = new ArrayList<>();
			ui.setState(BorrowBookUI.UiState.SCANNING);
			state = ControlState.SCANNING; }
>>>>>>> waqas
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UiState.RESTRICTED); }}
	
	
	public void scanned(int bookId) { // changing in method 
		borrow = null;
		if (!state.equals(ControlState.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
<<<<<<< HEAD
		B = l.Book(bookId);
		if (B == null) {
=======
		borrow = lib.book(bookId);
		if (borrow == null) {
>>>>>>> waqas
			ui.display("Invalid bookId");
			return;
		}
		if (!borrow.Available()) {
			ui.display("Book cannot be borrowed");
			return;
		}
		pending.add(borrow);
		for (Book b : pending) {
			ui.display(b.toString());
		}
<<<<<<< HEAD
		if (l.loansRemainingForMember(M) - PENDING.size() == 0) {
=======
		if (lib.loansRemainingForMember(memb) - pending.size() == 0) {
>>>>>>> waqas
			ui.display("Loan limit reached");
			complete();
		}
	}
	
	
	public void complete() { // all changing in method
		if (pending.size() == 0) {
			cancel();
		}
		else {
			ui.display("\nFinal Borrowing List");
			for (Book b : pending) {
				ui.display(b.toString());
			}
			completed = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UiState.FINALISING);
			state = ControlState.FINALISING;
		}
	}


	public void commitLoans() { // changing in method
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (Book b : pending) { 
			Loan loan = lib.issueLoan(b, memb);
			completed.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (Loan l : completed) {
			ui.display(l.toString());
		}
		ui.setState(BorrowBookUI.UiState.COMPLETED);
		state = ControlState.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UiState.CANCELLED);
		state = ControlState.CANCELLED;
	}
	
	
}
