public class ReturnBookControl {

	private ReturnBookUI ui;
	private enum ControlState { INITIALISED, READY, INSPECTING };
	private ControlState state;
	
	private Library lib; // changed variable name to avoid collapse // class name
	private Loan currentLoan; //class name changed 
	

	public ReturnBookControl() { // changing in constructor
		this.lib = Library.INSTANCE(); // need clarification for me (Harminder)
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(ReturnBookUI ui) {
		if (!state.equals(ControlState.INITIALISED)) {
			throw new RuntimeException("ReturnBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(ReturnBookUI.UiState.READY);
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call bookScanned except in READY state");
		}	
		Book currentBook = library.Book(bookId); // class name should be start with upper case letter// need discussion
		
		if (currentBook == null) {
			ui.display("Invalid Book Id");
			return;
		}
		if (!currentBook.onLoan()) {// method name changed
			ui.display("Book has not been borrowed");
			return;
		}		
		currentLoan = library.getLoanByBookId(bookId);	// need discussion
		double overDueFine = 0.0;
		if (currentLoan.isOverDue()) {
			overDueFine = library.calculateOverDueFine(currentLoan);
		}
		ui.display("Inspecting");
		ui.display(currentBook.toString());
		ui.display(currentLoan.toString());
		
		if (currentLoan.isOverDue()) {
			ui.display(String.format("\nOverdue fine : $%.2f", overDueFine));
		}
		ui.setState(ReturnBookUI.UiState.INSPECTING);
		state = ControlState.INSPECTING;		
	}


	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("ReturnBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(ReturnBookUI.UiState.COMPLETED);		
	}


	public void dischargeLoan(boolean isDamaged) {
		if (!state.equals(ControlState.INSPECTING)) {
			throw new RuntimeException("ReturnBookControl: cannot call dischargeLoan except in INSPECTING state");
		}	
		library.dischargeLoan(currentLoan, isDamaged);
		currentLoan = null;
		ui.setState(ReturnBookUI.UiState.READY);
		state = ControlState.READY;				
	}


}
