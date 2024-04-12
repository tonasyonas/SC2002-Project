public class Staff {
    
    private int staffID;
    private String staffName;
    private int branch_ID;
    private Role staffRole;
    private Gender staffGender;
    private int staffAge;
	private String staffPassword;
    private static int runningCount=1;
	private static String default_password = "password";

	/**
	 * Declare constants for enum Gender. MALE or FEMALE.
	 */
	public enum Gender{
		MALE,
		FEMALE
	}

    public enum Role {
        Staff,
        Branch_Manager,
        Admin
    }
	/**
	 * Constructor used by restaurant manager to create new staff.
	 * @param staffName
	 * @param staffTitle
	 * @param staffGender
     * @param Age
	 */
	public Staff(String staffName, Role staffRole, Gender staffGender, int staffAge,  int branch_ID) {
		this.staffID = runningCount;
		runningCount++;
		this.staffPassword = default_password;
		this.staffName = staffName;
		this.staffRole = staffRole;
        this.branch_ID = branch_ID;
		this.staffGender = staffGender;
        this.staffAge = staffAge;
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

    public int getBranchID(){
        return branch_ID;
    }

    public void setBranchID (int branch_ID){
        this.branch_ID = branch_ID;
    }

	public String getstaffPassword (){
		return staffPassword;
	}

	public void setstaffPassword(String staffPassword){
		this.staffPassword = staffPassword;
	}
	public void print() {
		System.out.println("Staff ID: "+this.staffID);
		System.out.println("Name: "+this.staffName);
		System.out.println("Role: "+this.staffRole);
		System.out.println("Gender: "+this.staffGender);
		System.out.println("Age: " +this.staffAge);
		System.out.println("Branch: " +this.branch_ID);
		System.out.println("Password: "+this.staffPassword);
	}

	/*
	 * 
	 * basic information may need to include more methods that staff can do
	 *
	 */
    public static void main (String[] args){
    	 Staff Donovan = new Staff("Don", Role.Staff, Gender.MALE, 12, 0005);
         Donovan.print();
    
    }

}




