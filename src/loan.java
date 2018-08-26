import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class loan implements Serializable {
	
	public static enum LoanState { CURRENT, OVER_DUE, DISCHARGED };	//Changes from LOAN_STATE to LoanState
	private int id; //chage from ID to id
	private book b;	//chage from B to b
	private member m; //chage from M to m
	private Date d;	//chage from D to d
	private LoanState state;	//Changes from LOAN_STATE to LoanState

	
	public loan(int loanId, book book, member member, Date dueDate) {
		this.id = loanId; //chage from ID to id
		this.b = book;	//chage from B to b
		this.m = member;   //chage from M to m
		this.d = dueDate;   //chage from D to d
		this.state = LoanState.CURRENT;	//Changes from LOAN_STATE to LoanState
	}

	
	public void checkOverDue() {
		if (state == LoanState.CURRENT &&	//Changes from LOAN_STATE to LoanState
			Calendar.getInstance().Date().after(D)) {
			this.state = LoanState.OVER_DUE;	//Changes from LOAN_STATE to LoanState			
		}
	}

	
	public boolean isOverDue() {
		return state == LoanState.OVER_DUE;	//Changes from LOAN_STATE to LoanState
	}

	
	public Integer getId() {
		return id;	//chage from ID to id
	}


	public Date getDueDate() {
		return d; //chage from D to d
	}
	
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		StringBuilder sb = new StringBuilder();
		sb.append("Loan:  ").append(id).append("\n") 	//chage from ID to id
		  .append("  Borrower ").append(m.getId()).append(" : ")	//chage from M to m
		  .append(m.getLastName()).append(", ").append(m.getFirstName()).append("\n")	//chage from M to m
		  .append("  Book ").append(b.ID()).append(" : " ) //chage from B to b
		  .append(b.Title()).append("\n")	//chage from B to b
		  .append("  DueDate: ").append(sdf.format(d)).append("\n")	//chage from D to d
		  .append("  State: ").append(state);		
		return sb.toString();
	}


	public member Member() {
		return m;  //chage from M to m
	}


	public book Book() {
		return b;	//chage from B to b
	}


	public void Loan() {
		state = LoanState.DISCHARGED;	//Changes from LOAN_STATE to LoanState		
	}

}
