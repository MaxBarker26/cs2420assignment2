package assign02;

public class CS2420Student extends UofUStudent {
	private EmailAddress contactInfo;
	private double assignment, exam, lab, quiz, poll;
	public CS2420Student(String firstName, String lastName, int uNID, EmailAddress contactInfo) {
		super(lastName, firstName, uNID);
		this.contactInfo = contactInfo;
		
	}
	
	public EmailAddress getContactInfo() {
		return this.contactInfo;
	}
	
	public void addScore(double score, String category) {	
		switch (category) {
			case "assignment":
				this.assignment = score;
				break;
			case "exam":
				this.exam = score;
				break;
			case "lab":
				this.lab = score;
				break;
			case "quiz":
				this.quiz = score;
				break;
			case "poll":
				this.poll = score;
				break;
				
			default:
				break;
		}
	}
	
	public double computeFinalScore() {
		double total = 0;
		total = ((this.assignment * 0.3) + (this.exam * 0.53) + (this.lab * 0.07) + (this.quiz * 0.05) + (this.poll * 0.05));
		return total;
	}
	
	public String computeFinalGrade() {
		double finalScore = this.computeFinalScore();
		if (finalScore >= .93) {
			return "A";
		} else if (finalScore >= .9) {
			return "A-";
		} else if (finalScore >= .87) {
			return "B+";
		} else if (finalScore >= .83) {
			return "B";
		} else if (finalScore >= .8) {
			return "B-";
		} else if (finalScore >= .77) {
			return "C+";
		} else if (finalScore >= .73) {
			return "C";
		} else if (finalScore >= .7) {
			return "C-";
		} else if (finalScore >= .67) {
			return "D+";
		} else if (finalScore >= .63) {
			return "D";
		} else if (finalScore >= 0.6) {
			return "D-";
		} else if (finalScore >= 0) {
			return "E";
		}
		else 
			return "N/A";
	}
}
