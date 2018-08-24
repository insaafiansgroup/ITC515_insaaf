import java.util.Scanner;


public class ReturnBookUI {

	public static enum UiState { INITIALISED, READY, INSPECTING, COMPLETED }; 

	private ReturnBookControl control;
	private Scanner input;
	private UiState state;

	
	public ReturnBookUI(ReturnBookControl control) {
		this.control = control;
		input = new Scanner(System.in);
		state = UiState.INITIALISED;
		control.setUI(this);
	}


	public void run() {		
		output("Return Book Use Case UI\n");
		
		while (true) {
			
			switch (state) {
			
			case INITIALISED:
				break;
				
			case READY:
				String bookStr = input("Scan Book (<enter> completes): ");
				if (bookStr.length() == 0) {
					control.scanningComplete();
				}
				else {
					try {
						int bookId = Integer.valueOf(bookStr).intValue();
						control.bookScanned(bookId);
					}
					catch (NumberFormatException e) {
						output("Invalid bookId");
					}					
				}
				break;				
				
			case INSPECTING:
				String ans = input("Is book damaged? (Y/N): ");
				boolean isDamaged = false;
				if (ans.toUpperCase().equals("Y")) {					
					isDamaged = true;
				}
				control.dischargeLoan(isDamaged);
			
			case COMPLETED:
				output("Return processing complete");
				return;
			
			default:
				output("Unhandled state");
				throw new RuntimeException("ReturnBookUI : unhandled state :" + state);			
			}
		}
	}

	
	private String input(String prompt) {
		System.out.print(prompt);
		return input.nextLine();
	}	
		
		
	private void output(Object obj) { // changed the variable name to avoid collapse
		System.out.println(obj);
	}
	
			
	public void display(Object obj) { // changed the variable name to avoid collapse
		output(obj);
	}
	
	public void setState(UiState state) {
		this.state = state;
	}

	
}
