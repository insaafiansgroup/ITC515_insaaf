import java.io.Serializable;


@SuppressWarnings("serial")
public class Book implements Serializable { // Changed class name 
	
	private String bookTitle; // Changed the variable name 
	private String bookAuthor; // Changed the variable name 
	private String bookCall; // Changed the variable name 
	private int bookId; // Changed the variable name 
	
	private enum State { AVAILABLE, ON_LOAN, DAMAGED, RESERVED };
	private State state;
	
	
	public Book(String author, String title, String callNo, int id) {
		this.bookTitle = author; // Changed the variable name 
		this.bookAuthor = title; // Changed the variable name 
		this.bookCall = callNo; // Changed the variable name 
		this.bookId = id; // Changed the variable name 
		this.state = State.AVAILABLE;
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();           
		sb.append("Book: ").append(bookId).append("\n")   // Changed the variable name     
		  sb.append("  Title:  ").append(bookTitle).append("\n") // Changed the variable name 
		  sb.append("  Author: ").append(bookAuthor).append("\n") // Changed the variable name 
		  sb.append("  CallNo: ").append(bookCall).append("\n") // Changed the variable name 
		  sb.append("  State:  ").append(state); // Changed the variable name 
		
		return sb.toString();
	}

	public Integer getId() {  // changed the function name 
		return bookId; // Changed the ID variable name
	}

	public String getTitle() { // changed the function name 
		return bookTitle; // changed the title variable name
	}


	
	public boolean isAvailable() { // changed the function name
		return state == State.AVAILABLE;
	}

	
	public boolean isOnLoan() { // changed the function name
		return state == State.ON_LOAN;
	}

	
	public boolean isDamaged() { // changed the function name
		return state == State.DAMAGED;
	}

	
	public void bookBorrow() { // changed the function name
		if (state.equals(State.AVAILABLE)) {
			state = State.ON_LOAN;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot borrow while book is in state: %s", state));
		}
		
	}


	public void bookReturn(boolean isDamaged) { // Function name is changed
		if (state.equals(State.ON_LOAN)) {
			if (isDamaged) {  // changed the method name within method
				state =State.DAMAGED;
			}
			else {
				state = State.AVAILABLE;
			}
		}
		else {
			throw new RuntimeException(String.format("Book: cannot Return while book is in state: %s", state));
		}		
	}

	
	public void bookRepair() { // method name is changed
		if (state.equals(State.isDamaged)) { // changed the method name within a method
			state = State.AVAILABLE;
		}
		else {
			throw new RuntimeException(String.format("Book: cannot repair while book is in state: %s", state));
		}
	}


}
