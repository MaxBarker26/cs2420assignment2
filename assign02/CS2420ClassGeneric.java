package assign02;

import java.util.ArrayList;
import java.util.Comparator;

public class CS2420ClassGeneric<Type> {
	private ArrayList<CS2420StudentGeneric<Type>> studentList;
	
	/**
	 * Creates an empty CS 2420 class.
	 */
	public CS2420ClassGeneric() {
		this.studentList = new ArrayList<CS2420StudentGeneric<Type>>();
	}
	
	/**
	 * Adds the given student to the collection of students in CS 2420, avoiding duplicates.
	 * 
	 * @param student - student to be added to this CS 2420 class
	 * @return true if the student was added, 
	 *         false if the student was not added because they already exist in the collection
	 */
	public boolean addStudent(CS2420StudentGeneric<Type> student) {
		if (!studentList.contains(student)) {
			this.studentList.add(student);
			return true;
		} else return false;
	}
	
	/**
	 * Retrieves the CS 2420 student with the given uNID.
	 * 
	 * @param uNID - uNID of student to be retrieved
	 * @return the CS 2420 student with the given uNID, or null if no such student exists in the collection
	 */
	public CS2420StudentGeneric<Type> lookup(int uNID) {
		for (CS2420StudentGeneric<Type> student : studentList) {
			if (student.getUNID() == uNID) {
				return student;
			}
		}
		return null;
	}
	
	
	/**
	 * Retrieves the CS 2420 student(s) with the given contact information.
	 * 
	 * @param contactInfo - contact information of student(s) to be retrieved
	 * @return a list of the CS 2420 student(s) with the given contact information (in any order), 
	 * 	     or an empty list if no such students exist in the collection
	 */
	public ArrayList<CS2420StudentGeneric<Type>> lookup(Type contactInfo) {
		ArrayList<CS2420StudentGeneric<Type>> contactList = new ArrayList<CS2420StudentGeneric<Type>>();
		for (CS2420StudentGeneric<Type> student: studentList) {
			if (student.getContactInfo().equals(contactInfo)) {
				contactList.add(student);
			}
		}
		return contactList;
	}
	
	/**
	 * Adds an assignment, exam, lab, quiz, or poll score for the CS 2420 student with the given uNID.
	 * 
	 * NOTE: If the category string is not one of "assignment", "exam", "lab", "quiz", or "poll",
	 *       or if no student with the uNID exists in the collection, then this method has no effect.
	 * 
	 * @param uNID - uNID of student whose score is to be added
	 * @param score - the score to be added
	 * @param category - the category in which to add the score
	 */
	public void addScore(int uNID, double score, String category) {
		if (category.equals("assignment") || category.equals("exam") || category.equals("lab") || category.equals("quiz") || category.equals("poll")) {
			for (CS2420StudentGeneric<Type> student : studentList) {
				if (student.getUNID() == uNID) {
					student.addScore(score, category);
				}
			}
		}
	}
	
	/**
	 * Computes the average score of all CS 2420 students in the collection.
	 * 
	 * @return the average score, or 0 if there are no students in the collection
	 */
	public double computeClassAverage() {
		double total = 0;
		for (CS2420StudentGeneric<Type> student: studentList) {
			total += student.computeFinalScore();
		}
		if (total == 0) {
			return 0;
		}
		return total / studentList.size();
	}
	
	/**
	 * Creates a list of contact information for all CS 2420 students in the collection.
	 *
	 * @return the duplicate-free list of contact information, in any order
	 */
	public ArrayList<Type> getContactList() {
		ArrayList<Type> contactList = new ArrayList<Type>();
		for (CS2420StudentGeneric<Type> student : studentList) {
			if (contactList.contains(student.getContactInfo())) {
				continue;
			} else {
				contactList.add(student.getContactInfo());
			}
		}
		return contactList;
	}
	
	/**
	 * Returns the list of CS 2420 students in this class, sorted by uNID in ascending order.
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByUNID() {
	    ArrayList<CS2420StudentGeneric<Type>> studentListCopy = new ArrayList<CS2420StudentGeneric<Type>>();
	    for(CS2420StudentGeneric<Type> student : studentList)
	   	 studentListCopy.add(student);

	    sort(studentListCopy, new OrderByUNID());

	    return studentListCopy;
	}

	/**
	 * Returns the list of CS 2420 students in this class, sorted by last name in lexicographical order.
	 * Breaks ties in last names using first names (lexicographical order).
	 * Breaks ties in first names using uNIDs (ascending order).
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByName() {
	    ArrayList<CS2420StudentGeneric<Type>> copyList = new ArrayList<CS2420StudentGeneric<Type>>();
	    for (CS2420StudentGeneric<Type> student : studentList) {
	    	copyList.add(student);
	    }
	    
	    sort(copyList, new OrderByName());
	    return copyList;
	}

	/**
	 * Returns the list of CS 2420 students in this class with a final score of at least cutoffScore, 
	 * sorted by final score in descending order.  
	 * Breaks ties in final scores using uNIDs (ascending order).
	 * 
	 * @param cutoffScore - value that a student's final score must be at or above to be included
	 *                      in the returned list
	 */
	public ArrayList<CS2420StudentGeneric<Type>> getOrderedByScore(double cutoffScore) {
	    ArrayList<CS2420StudentGeneric<Type>> copyList = new ArrayList<CS2420StudentGeneric<Type>>();
	    for (CS2420StudentGeneric<Type> student : studentList) {
	    	if (student.computeFinalScore() >= cutoffScore) {
	    		copyList.add(student);
	    		System.out.println(student.computeFinalScore());
	    	}
	    }
	    
	    sort(copyList, new OrderByScore());
	    
	    for (CS2420StudentGeneric<Type> student : copyList) {
	    	System.out.println(student.computeFinalScore());
	    }
	   
	    return copyList;
	}

	/**
	 * Performs a SELECTION SORT on the input ArrayList. 
	 * 
	 * 1. Finds the smallest item in the list. 
	 * 2. Swaps the smallest item with the first item in the list. 
	 * 3. Reconsiders the list be the remaining unsorted portion (second item to Nth item) and 
	 *    repeats steps 1, 2, and 3.
	 */
	private static <ListType> void sort(ArrayList<ListType> list, Comparator<ListType> c) {
		for(int i = 0; i < list.size() - 1; i++) {
			int j, minIndex;
			for(j = i + 1, minIndex = i; j < list.size(); j++)
				if(c.compare(list.get(j), list.get(minIndex)) < 0)
					minIndex = j;
			ListType temp = list.get(i);
			list.set(i, list.get(minIndex));
			list.set(minIndex, temp);
		}
	}

	/**
	 * Comparator that defines an ordering among CS 2420 students using their uNIDs.
	 * uNIDs are guaranteed to be unique, making a tie-breaker unnecessary.
	 */
	protected class OrderByUNID implements Comparator<CS2420StudentGeneric<Type>> {

		/**
		 * Returns a negative value if lhs (left-hand side) is smaller than rhs (right-hand side). 
		 * Returns a positive value if lhs is larger than rhs. 
		 * Returns 0 if lhs and rhs are equal.
		 */
		public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
			return lhs.getUNID() - rhs.getUNID();
		}
	}

	/**
	 * Comparator that defines an ordering among CS 2420 students using their names.
	 * Compares by last name, then first name (if last names are the same), then uNID 
	 * (if both names are the same).  uNIDs are guaranteed to be unique.
	 */
	protected class OrderByName implements Comparator<CS2420StudentGeneric<Type>> {
		public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
			if (!lhs.getLastName().equals(rhs.getLastName())) {
				return lhs.getLastName().compareTo(rhs.getLastName());
			}
			if (!lhs.getFirstName().equals(rhs.getFirstName())) {
				return lhs.getFirstName().compareTo(rhs.getFirstName());
			}
			return lhs.getUNID() - rhs.getUNID();
		}
	}
	
	protected class OrderByScore implements Comparator<CS2420StudentGeneric<Type>> {
		public int compare(CS2420StudentGeneric<Type> lhs, CS2420StudentGeneric<Type> rhs) {
			if (lhs.computeFinalScore() < rhs.computeFinalScore()) {
				return 1;
			} 
			if (lhs.computeFinalScore() > rhs.computeFinalScore()) {
				return -1;
			}
			
			return lhs.getUNID() - rhs.getUNID();
		}
	}
	
}
