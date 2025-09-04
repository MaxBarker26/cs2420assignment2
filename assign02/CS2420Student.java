package assign02;

public class CS2420Student extends UofUStudent {
	private EmailAddress contactInfo;
	private double assignment, exam, lab, quiz, poll;
	private double assignmentCounter, examCounter, labCounter, quizCounter, pollCounter;
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
				this.assignment += score;
				assignmentCounter++;
				break;
			case "exam":
				this.exam += score;
				examCounter++;
				break;
			case "lab":
				this.lab += score;
				labCounter++;
				break;
			case "quiz":
				this.quiz += score;
				quizCounter++;
				break;
			case "poll":
				this.poll += score;
				pollCounter++;
				break;
			default:
				break;
		}
	}
	
	public double computeFinalScore() {
		double total = 0;
		total = ((this.assignment/assignmentCounter * 0.3) + (this.exam/examCounter * 0.53) + (this.lab/labCounter * 0.07) + (this.quiz/quizCounter * 0.05) + (this.poll/pollCounter * 0.05));
		if (this.assignment == 0 || this.exam == 0 || this.lab == 0 || this.quiz == 0) {
			return 0;
		}
		return total;
	}
	
	public String computeFinalGrade() {
		double finalScore = this.computeFinalScore();
		if (finalScore >= 93) {
			return "A";
		} else if (finalScore >= 90) {
			return "A-";
		} else if (finalScore >= 87) {
			return "B+";
		} else if (finalScore >= 83) {
			return "B";
		} else if (finalScore >= 80) {
			return "B-";
		} else if (finalScore >= 77) {
			return "C+";
		} else if (finalScore >= 30) {
			return "C";
		} else if (finalScore >= 70) {
			return "C-";
		} else if (finalScore >= 67) {
			return "D+";
		} else if (finalScore >= 63) {
			return "D";
		} else if (finalScore >= 60) {
			return "D-";
		} else if (finalScore > 0) {
			return "E";
		}
		else 
			return "N/A";
	}
}
