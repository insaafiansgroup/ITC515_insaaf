public class FixBookControl {

	private FixBookUI ui;
		//naming convention
	private enum ControlState { INITIALISED, READY, FIXING };
	private ControlState state;

		// library changed to lib to avoid the collapse
	private library lib;
	private Book currentBook;// Class name changed from book to Book 


	public FixBookControl() {
		this.lib = lib.instance(); //naming convention and other class has object name lib instead library
		state = ControlState.INITIALISED;
	}


	public void setUI(FixBookUI ui) {
		if (!state.equals(ControlState.INITIALISED))//naming convention {
			throw new RuntimeException("FixBookControl: cannot call setUI except in INITIALISED state");
		}
		this.ui = ui;
		ui.setState(FixBookUI.uiState.READY)// naming convention
		state = ControlState.READY;		
	}


	public void bookScanned(int bookId) {
		if (!state.equals(ControlState.READY)) {
			throw new RuntimeException("FixBookControl: cannot call bookScanned except in READY state");
		}
		currentBook = lib.book(bookId); // method name could be book not Book and library is changed to lib

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
		state = ControlState.FIXING;// class object CONTROL_STATE changed to ControlState( naming convention)
	}


	public void fixBook(boolean fix) {
		if (!state.equals(ControlState.FIXING)) {// class object CONTROL_STATE changed to ControlState( naming convention)
	}
			throw new RuntimeException("FixBookControl: cannot call fixBook except in FIXING state");
		}
		if (fix) {
			lib.repairBook(currentBook);// object name changed from library to lib
		}
		currentBook = null;
		ui.setState(FixBookUI.uiState.READY);// class object UI_STATE changed to uiState(naming convention )
		state = ControlState.READY;// enum CONTROL_STATE changed to ControlState( naming convention)
	}
	}


	public void scanningComplete() {
		if (!state.equals(ControlState.READY)) {// class object CONTROL_STATE changed to ControlState( naming convention)
			throw new RuntimeException("FixBookControl: cannot call scanningComplete except in READY state");
		}
		ui.setState(FixBookUI.uiState.COMPLETED);
	}






}
