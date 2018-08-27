public class FixBookControl {

	private FixBookUI ui;
		//naming convention
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState state;

		// library changed to lib to avoid the collapse

	private Library lib; // class name library changed to Library
	private Book currentBook;// Class name changed from book to Book



	public FixBookControl() {
		this.lib = lib.instance(); //naming convention
		state = ControlState.INITIALISED;
	}


	public void setUI(FixBookUI ui) {
		if (!state.equals(ControlState.INITIALISED))//naming convention for enum 
		{
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		ui.setState(FixBookUI.UiState.READY);//naming Convention
		state = ControlState.READY;		//naming convention
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {// naming convention
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		currentBook = lib.book(bookId); // class object changed

		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.damaged()) {//naming convention method name Damaged changed to damaged
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.UiState.FIXING);// class object UI_STATE changed to uiState(naming convention )
		state = ControlState.FIXING;// class object CONTROL_STATE changed to controlState( naming convention)
	}


	public void fixBook(boolean fix) {
		if (!state.equals(ControlState.FIXING)) {// class object CONTROL_STATE changed to ControlState( naming convention)
	}
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}
		if (fix) {
			lib.repairBook(currentBook); // class object changed
		}
		currentBook = null;
		ui.setState(FixBookUI.uiState.READY);// class object UI_STATE changed to uiState(naming convention )
		state = ControlState.READY;// class object CONTROL_STATE changed to ControlState( naming convention)
	}
	}


	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {// class object CONTROL_STATE changed to controlState( naming convention)
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}
		ui.setState(FixBookUI.UiState.COMPLETED);
	}






}
