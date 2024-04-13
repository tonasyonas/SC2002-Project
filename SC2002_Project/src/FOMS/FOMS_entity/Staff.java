

public class Staff {
    
    private int staffID;
	private String staffLoginID;
    private String staffName;
    private String branchName;
    private Role staffRole;
    private Gender staffGender;
    private int staffAge;
	private String staffPassword;
	private static String default_password = "password";
	public static int runningCount=1;

	/**
	 * Declare constants for enum Gender. MALE or FEMALE.
	 */
	public enum Gender{
		M,
		F
	}

    public enum Role {
        S,
        M,
        A
    }
	/**
	 * Constructor used by restaurant manager to create new staff.
	 * @param staffName
	 * @param staffTitle
	 * @param staffGender
     * @param Age
	 */
	public Staff(String staffName, Gender staffGender, int staffAge,  String branchName) {
		this.staffID = runningCount;
		runningCount++;
		this.staffPassword = default_password;
		this.staffName = staffName;
        this.branchName = branchName;
		this.staffGender = staffGender;
        this.staffAge = staffAge;
		this.staffRole = Role.S;
		this.staffLoginID = generateLoginId (this.staffName);
	}
	
	public int getStaffID() {
		return staffID;
	}
	/**
	 * Set staffID of staff object. Public method.
	 * @param integer staffID
	 */
	public void setStaffID(int staffID) {
		this.staffID = staffID;
	}
	/**
	 *  Public method.
	 * @return String staffName
	 */
	public String getStaffName() {
		return staffName;
	}
	/**
	 * Set staffName of staff object. Public method.
	 * @param String staffName
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}
	/**
	 * Get staffTitle. Public method.
	 * @return Role staffRole
	 */
	public Role getStaffRole() {
		return staffRole;
	}
	/**
	 * Set staffTitle of staff object. Public method.
	 * @param Role staffRole
	 */
	public void setStaffRole(Role staffRole) {
		this.staffRole = staffRole;
	}
	/**
	 * Get gender of Staff. Public method.
	 * @return enum Gender
	 */
	public Gender getStaffGender() {
		return staffGender;
	}
	/**
	 * Set staff's gender. Public method.
	 * @param enum Gender staffGender
	 */
	public void setStaffGender(Gender staffGender) {
		this.staffGender = staffGender;
	}
	
    public int getStaffAge(){
        return staffAge;
    }

    public void setStaffAge (int staffAge){
        this.staffAge = staffAge;
    }

    public String getBranchName(){
        return branchName;
    }

    public void setBranchName (String branchName){
        this.branchName = branchName;
    }

	public String getStaffLoginID(){
		return staffLoginID;
	}

	public void setStaffLoginID(String staffLoginID){
		this.staffLoginID = staffLoginID;
	}
	public String getStaffPassword (){
		return staffPassword;
	}

	public void setStaffPassword(String staffPassword){
		this.staffPassword = staffPassword;
	}
	public void print() {
		System.out.println("Staff ID: "+this.staffID);
		System.out.println("Name: "+this.staffName);
		System.out.println("Role: "+this.staffRole);
		System.out.println("Gender: "+this.staffGender);
		System.out.println("Age: " +this.staffAge);
		System.out.println("Branch: " +this.branchName);
		System.out.println("Password: "+this.staffPassword);
		System.out.println("LoginID: "+this.staffLoginID);
	}

	public String generateLoginId(String staffName) {
        // Split the full name into first name and last name (if applicable)
        String[] nameParts = staffName.split(" ");

        // Initialize variables for first name and last name
        String firstName = nameParts[0];
        String lastName = "";

        // If there are more than one part, assume the last part as the last name
        if (nameParts.length > 1) {
            lastName = nameParts[nameParts.length - 1];
        }

        // Get the first letter of the last name and capitalize it
        char lastNameInitial = lastName.isEmpty() ? '\0' : Character.toUpperCase(lastName.charAt(0));

        // Concatenate the first name with the capitalized first letter of the last name
        return firstName + (lastName.isEmpty() ? "" : lastNameInitial);
    }

	/*
	 * 
	 * basic information may need to include more methods that staff can do
	 *
	 */
    public static void main (String[] args){
    	 Staff Donovan = new Staff("Don go", Gender.M, 12, "NTU");
         Donovan.print();
    
    }

}





