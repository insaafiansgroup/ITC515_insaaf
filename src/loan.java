import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class Loan implements Serializable { // class name should be start with upper case.
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };	//Changes from LOAN_STATE to LoanState, enumnaming convention
	private int id; //chage from ID to id,naming convention
	private Book b;	//chage from B to b,naming convention
	private Member m; //chage from M to m,naming convention
	private Date d;	//chage from D to d,naming convention
	private LoanState state;	//Changes from LOAN_STATE to LoanState,enum naming convention
	
	public Loan(int loanId, book book, member member, Date dueDate) { // class name should be start with upper case.
		this.id = loanId; //chage from ID to id,naming convention
		this.b = book;	//chage from B to b,naming convention
		this.m = member;   //chage from M to m,naming convention
		this.d = dueDate;   //chage from D to d,naming convention
		this.state = LoanState.CURRENT;	//Changes from LOAN_STATE to LoanState, enum naming convention
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&	//Changes from LOAN_STATE to LoanState, enum naming convention
			Calendar.getInstance().Date().after(D)) {
			this.state = LoanState.OVER_DUE;	//Changes from LOAN_STATE to LoanState,enum naming convention			
		}
	}

	
	public boolean isOverDue() {
		return state == LoanState.OVER_DUE;	//Changes from LOAN_STATE to LoanState,enum naming convention
	}

	
	public Integer getId() {
		return id;	//chage from ID to id,naming convention
	}


	public Date getDueDate() {
		return d; //chage from D to d,naming convention
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n") 	//chage from ID to id naming,naming convention
		  .append("Borrower ").append(m.getId()).append(" : ")	//chage from M to m,naming convention
		  .append(m.getLastName()).append(", ").append(m.getFirstName()).append("\n")	//chage from M to m,naming convention
		  .append("Book ").append(b.ID()).append(" : " ) //chage from B to b,naming convention
		  .append(b.Title()).append("\n")	//chage from B to b,naming convention
		  .append("DueDate: ").append(sdf.format(d)).append("\n")	//chage from D to d,naming convention
		  .append("State: ").append(state);		
		return sb.toString();
	}


	public m  ember Member() {
		return m;  //chage from M to m,naming convention
	}


	public book Book() {
		return b;	//chage from B to b,naming convention
	}


	public void Loan() {
		state = LoanState.DISCHARGED;	//Changes from LOAN_STATE to LoanState,enumnaming convention		
	}

}
