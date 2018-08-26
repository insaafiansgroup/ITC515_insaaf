import java.util.Scanner;


public class PayFineUI {
			
			//change in UI_STATE to UiState
	public static enum UiState { INITIALISED, READY, PAYING, COMPLETED, CANCELLED }; // change in enum naming

	private PayFineControl control;
	private Scanner input;
	private UiState state;	//change in UI_STATE to UiState

	
	public PayFineUI(PayFineControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;	//change in UI_STATE to UiState, enum naming
		control.setUI(this);
	}
	
	
	public void setState(UiState state) {	//change in UI_STATE to UiState, enum naming
		this.state = state;
	}


	public void run() {
		output("Pay Fine Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case READY:
				String memStr = input("Swipe member card (press <enter> to cancel): ");
				if (memStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					int memberId = Integer.valueOf(memStr).intValue();
					control.cardSwiped(memberId);
				}
				catch (NumberFormatException e) {
					output("Invalid memberId");
				}
				break;
				
			case PAYING:
				double amount = 0;
				String amtStr = input("Enter amount (<Enter> cancels) : ");
				if (amtStr.length() == 0) {
					control.cancel();
					break;
				}
				try {
					amount = Double.valueOf(amtStr).doubleValue();
				}
				catch (NumberFormatException e) {}
				if (amount <= 0) {
					output("Amount must be positive");
					break;
				}
				control.payFine(amount);
				break;
								
			case CANCELLED:
				output("Pay Fine process cancelled");
				return;
			
			case COMPLETED:
				output("Pay Fine process complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("FixBookUI : unhandled state :" + state);			
			
			}		
		}		
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		//changed object into obj to avoid confusion
	private void output(Object obj) {	//change in naming convention
		System.out.println(obj);
	}	
			
		//changed object into obj to avoid confusion
	public void display(Object obj) {	//change in naming convention
		output(obj);

	}


}
