import java.util.ArrayList;
import java.util.List;

public class BorrowBookControl {
	
	private BorrowBookUI ui;
	
	private library lib; // Change the variable name
	private member memb; // Change the variable name
	private enum CONTROL_STATE { INITIALISED, READY, RESTRICTED, SCANNING, IDENTIFIED, FINALISING, COMPLETED, CANCELLED };
	private CONTROL_STATE state;
	
	private List<book> pending; // naming conventions
	private List<loan> completed; // naming conventions
	private Book b; // naming conventions
	
	
	public BorrowBookControl() { // Changing in constructor
		this.lib = lib.INSTANCE();
		state = CONTROL_STATE.INITIALISED;
	}
	

	public void setUI(BorrowBookUI ui) {
		if (!state.equals(CONTROL_STATE.INITIALISED)) {  // added curly brackets in if condition 
		     throw new RuntimeException("BorrowBookControl: cannot call setUI except in INITIALISED state");
			 }
			
		this.ui = ui;
		ui.setState(BorrowBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

		
	public void swiped(int memberId) { // All changing in a method 
		if (!state.equals(CONTROL_STATE.READY))  {
			throw new RuntimeException("BorrowBookControl: cannot call cardSwiped except in READY state");
		}
		
		m = lib.getMember(memberId);
		if (m == null) {
			ui.display("Invalid memberId");
			return;
		}
		if (lib.memberCanBorrow(m)) {
			pending = new ArrayList<>();
			ui.setState(BorrowBookUI.UI_STATE.SCANNING);
			state = CONTROL_STATE.SCANNING; }
		else 
		{
			ui.display("Member cannot borrow at this time");
			ui.setState(BorrowBookUI.UI_STATE.RESTRICTED); }}
	
	
	public void scanned(int bookId) { // changing in method 
		b = null;
		if (!state.equals(CONTROL_STATE.SCANNING)) {
			throw new RuntimeException("BorrowBookControl: cannot call bookScanned except in SCANNING state");
		}	
		b = lib.Book(bookId);
		if (b == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!b.Available()) {
			ui.display("Book cannot be borrowed");
			return;
		}
		pending.add(b);
		for (book b : pending) {
			ui.display(b.toString());
		}
		if (lib.loansRemainingForMember(memb) - pending.size() == 0) {
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
			for (book b : pending) {
				ui.display(b.toString());
			}
			completed = new ArrayList<loan>();
			ui.setState(BorrowBookUI.UI_STATE.FINALISING);
			state = CONTROL_STATE.FINALISING;
		}
	}


	public void commitLoans() {
		if (!state.equals(CONTROL_STATE.FINALISING)) {
			throw new RuntimeException("BorrowBookControl: cannot call commitLoans except in FINALISING state");
		}	
		for (book b : PENDING) {
			loan loan = L.issueLoan(b, M);
			COMPLETED.add(loan);			
		}
		ui.display("Completed Loan Slip");
		for (loan loan : COMPLETED) {
			ui.display(loan.toString());
		}
		ui.setState(BorrowBookUI.UI_STATE.COMPLETED);
		state = CONTROL_STATE.COMPLETED;
	}

	
	public void cancel() {
		ui.setState(BorrowBookUI.UI_STATE.CANCELLED);
		state = CONTROL_STATE.CANCELLED;
	}
	
	
}
