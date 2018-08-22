public class PayFineControl {
	
	private PayFineUI ui;
			//change in CONTROL_STATE to ControlState
	private enum ContralState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED };
	private ContralState state;	//change in CONTROL_STATE to ControlState
			//changed library into lib to avoid confusion
	private Library lib;	//changed library to Library
			//changed member into mem to avoid confusion
	private Member mem;	//changed member to Member and double ';;' to single ';' 


	public PayFineControl() {
		this.lib = lib.INSTANCE();
		state = ContralState.INITIALISED;	//change in CONTROL_STATE to ControlState
	}
	
	
	public void setUI(PayFineUI ui) {
		if (!state.equals(ContralState.INITIALISED)) {	//change in CONTROL_STATE to ControlState
			throw new RuntimeException("PayFineControl: cannot call setUI except in INITIALISED state");
		}	
		this.ui = ui;
		ui.setState(PayFineUI.Ui.State.READY);	//naming Convention from UI_STATE to Ui.State
		state = ContralState.READY;	//change in CONTROL_STATE to ControlState		
	}


	public void cardSwiped(int memberId) {
		if (!state.equals(ContralState.READY)) {	//change in CONTROL_STATE to ControlState
			throw new RuntimeException("PayFineControl: cannot call cardSwiped except in READY state");
		}	
		mem = lib.getMember(memberId);
		
		if (mem == null) {
			ui.display("Invalid Member Id");
			return;
		}
		ui.display(mem.toString());
		ui.setState(PayFineUI.Ui.State.PAYING);	//naming Convention from UI_STATE to Ui.State
		state = ContralState.PAYING;	//change in CONTROL_STATE to ControlState
	}
	
	
	public void cancel() {
		ui.setState(PayFineUI.Ui.State.CANCELLED);	//naming Convention from UI_STATE to Ui.State
		state = ContralState.CANCELLED;	//change in CONTROL_STATE to ControlState
	}


	public double payFine(double amount) {
		if (!state.equals(ContralState.PAYING)) {	//change in CONTROL_STATE to ControlState
			throw new RuntimeException("PayFineControl: cannot call payFine except in PAYING state");
		}	
		double change = mem.payFine(amount);
		if (change > 0) {
			ui.display(String.format("Change: $%.2f", change));
		}
		ui.display(mem.toString());
		ui.setState(PayFineUI.UI_STATE.COMPLETED);	//naming Convention from UI_STATE to Ui.State
		state = ContralState.COMPLETED;	//change in CONTROL_STATE to ControlState
		return change;
	}
	


}
