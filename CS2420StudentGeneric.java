package assign02;

/**
 * This class represents a University of Utah student in CS2420 with generic contact information. This will have the students contact information
 * and calculate their final scores of CS2420.
 * @param <Type> a generic type for the students information
 * 
 * @author Josi Gac & Max Barker
 * @version Sept 3, 2025
 */
public class CS2420StudentGeneric<Type> extends UofUStudent {
	private Type contactInfo;
	private double assignment, exam, lab, quiz, poll;
	private double assignmentCounter, examCounter, labCounter, quizCounter, pollCounter;
	
	/**
	 * Constructor for the Student class
	 * @param firstName first name of the student
	 * @param lastName last name of the student
	 * @param uNID uNID of the student
	 * @param contactInfo the students contact information
	 */
	public CS2420StudentGeneric(String firstName, String lastName, int uNID, Type contactInfo) {
		super(lastName, firstName, uNID);
		this.contactInfo = contactInfo;
		
	}
	
	/**
	 * Getter for the students contact information
	 * @return the students contact information
	 */
	public Type getContactInfo() {
		return this.contactInfo;
	}
	
	/**
	 * Takes in the students score on the assignment that was graded and adds it to the correct category
	 * @param score on the assignment
	 * @param category of the assignment
	 */
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
	
	/**
	 * Computes the students final score, which is an average of the grade in that category multiplied by how much it is weighted
	 * @return the students final score in the class out of 100
	 */
	public double computeFinalScore() {
		if (assignmentCounter == 0 || examCounter == 0 || labCounter == 0 || quizCounter == 0 || pollCounter == 0) {
			return 0.0;
		}
		return ((this.assignment/assignmentCounter * 0.3) + (this.exam/examCounter * 0.53) + (this.lab/labCounter * 0.07) + (this.quiz/quizCounter * 0.05) + (this.poll/pollCounter * 0.05));
	}
	
	/**
	 * Assigns the student a letter grade based on their computed final score which corresponds to the syllabus
	 * N/A will be assigned if the score if less than 0
	 * @return the letter grade matching the students final score
	 */
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
