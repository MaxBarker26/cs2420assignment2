package assign02;

public class CS2420StudentGeneric<Type> extends UofUStudent {
	private Type contactInfo;
	private double assignment, exam, lab, quiz, poll;
	private double assignmentCounter, examCounter, labCounter, quizCounter, pollCounter;
	
	public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
		super(lastName, firstName, uNID);
		this.contactInfo = contactInfo;
		
	}
	
	public Type getContactInfo() {
		return this.contactInfo;
	}
	
	public void addScore(double score, String category) {	
		if (score < 0) {
			throw new IllegalArgumentException();
		}
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
		if (this.assignment == 0.0 || this.exam == 0.0 || this.lab == 0.0 || this.quiz == 0.0 || this.poll == 0.0) {
			return 0.0;
		}
		return ((this.assignment/assignmentCounter * 0.3) + (this.exam/examCounter * 0.53) + (this.lab/labCounter * 0.07) + (this.quiz/quizCounter * 0.05) + (this.poll/pollCounter * 0.05));

	}
	
	public String computeFinalGrade() {
		//double finalScore = this.computeFinalScore();
		if (this.computeFinalScore() >= 93) {
			return "A";
		} else if (this.computeFinalScore() >= 90) {
			return "A-";
		} else if (this.computeFinalScore() >= 87) {
			return "B+";
		} else if (this.computeFinalScore() >= 83) {
			return "B";
		} else if (this.computeFinalScore() >= 80) {
			return "B-";
		} else if (this.computeFinalScore() >= 77) {
			return "C+";
		} else if (this.computeFinalScore() >= 73) {
			return "C";
		} else if (this.computeFinalScore() >= 70) {
			return "C-";
		} else if (this.computeFinalScore() >= 67) {
			return "D+";
		} else if (this.computeFinalScore() >= 63) {
			return "D";
		} else if (this.computeFinalScore() >= 60) {
			return "D-";
		} else if (this.computeFinalScore() > 0) {
			return "E";
		}
		else 
			return "N/A";
	}
	
}
