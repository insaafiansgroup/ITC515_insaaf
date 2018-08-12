public class FixBookControl {
	
	private FixBookUI ui;
		//naming convention
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState state;
	
		// library changed to lib to avoid the collapse
	private library lib;
	private book currentBook;


	public FixBookControl() {
		this.library = library.instance(); //naming convention
		state = ControlState.INITIALISED;
	}
	
	
	public void setUI(FixBookUI ui) {
		if (!state.equals(controlState.INITIALISED))//naming convention {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(FixBookUI.uiState.READY);//naming Convention 
		state = controlState.READY;		//naming convention
	}


	public void bookScanned(int bookId) {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}	
		currentBook = library.Book(bookId);
		
		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.Damaged()) {
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.UI_STATE.FIXING);
		state = CONTROL_STATE.FIXING;		
	}


	public void fixBook(boolean fix) {
		if (!state.equals(CONTROL_STATE.FIXING)) {
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}	
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		ui.setState(FixBookUI.UI_STATE.READY);
		state = CONTROL_STATE.READY;		
	}

	
	public void scanningComplete() {
		if (!state.equals(CONTROL_STATE.READY)) {
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}	
		ui.setState(FixBookUI.UI_STATE.COMPLETED);		
	}






}
