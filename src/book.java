import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { // Changed class name 
	
	private String bookTitle; // Changed the variable name 
	private String bookAuthor; // Changed the variable name 
	private String bookCall; // Changed the variable name 
	private int bookId; // Changed the variable name 
	
	private enum STATE { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private STATE state;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.bookTitle = author; // Changed the variable name 
		this.bookAuthor = title; // Changed the variable name 
		this.bookCall = callNo; // Changed the variable name 
		this.bookId = id; // Changed the variable name 
		this.state = STATE.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();           
		sb.append("Book: ").append(bookId).append("\n")   // Changed the variable name     
		  .append("  Title:  ").append(bookTitle).append("\n") // Changed the variable name 
		  .append("  Author: ").append(bookAuthor).append("\n") // Changed the variable name 
		  .append("  CallNo: ").append(bookCall).append("\n") // Changed the variable name 
		  .append("  State:  ").append(state); // Changed the variable name 
		
		return sb.toString();
	}

	public Integer getId() {  // changed the function name 
		return bookId; // Changed the ID variable name
	}

	public String getTitle() { // changed the function name 
		return bookTitle; // changed the title variable name
	}


	
	public boolean isAvailable() { // changed the function name
		return state == STATE.AVAILABLE;
	}

	
	public boolean isOnLoan() { // changed the function name
		return state == STATE.ON_LOAN;
	}

	
	public boolean damaged() { // changed the function name
		return state == STATE.DAMAGED;
	}

	
	public void Borrow() {
		if (state.equals(STATE.AVAILABLE)) {
			state = STATE.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void Return(boolean DAMAGED) {
		if (state.equals(STATE.ON_LOAN)) {
			if (DAMAGED) {
				state = STATE.DAMAGED;
			}
			else {
				state = STATE.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void Repair() {
		if (state.equals(STATE.DAMAGED)) {
			state = STATE.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
