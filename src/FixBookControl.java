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
		if (!state.equals(controlState.READY)) {// naming convention
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		currentBook = library.Book(bookId);

		if (currentBook == null) {
			ui.display("Invalid bookId");
			return;
		}
		if (!currentBook.damaged()) {//naming convention method name Damaged changed to damaged
			ui.display("\"Book has not been damaged");
			return;
		}
		ui.display(currentBook.toString());
		ui.setState(FixBookUI.uiState.FIXING);// class object UI_STATE changed to uiState(naming convention )
		state = ControlState.FIXING;// class object CONTROL_STATE changed to controlState( naming convention)
	}


	public void fixBook(boolean fix) {
		if (!state.equals(controlState.FIXING)) {// class object CONTROL_STATE changed to controlState( naming convention)
	}
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}
		if (fix) {
			library.repairBook(currentBook);
		}
		currentBook = null;
		ui.setState(FixBookUI.uiState.READY);// class object UI_STATE changed to uiState(naming convention )
		state = controlState.READY;// class object CONTROL_STATE changed to controlState( naming convention)
	}
	}


	public void scanningComplete() {
		if (!state.equals(controlState.READY)) {// class object CONTROL_STATE changed to controlState( naming convention)
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}
		ui.setState(FixBookUI.uiState.COMPLETED);
	}






}
