public class Admin extends Staff {

    // Constructor for Manager class
    public Admin(String staffName, Role staffRole, Gender staffGender, int staffAge,  int branch_ID) {
        // Call the superclass constructor using super()
        super(staffName, staffRole, staffGender, staffAge,  branch_ID);
        setStaffRole(Role.Admin);

    }

    public static void main (String[] args){
        BranchManager Donovan = new BranchManager("Don", Role.Staff, Gender.MALE, 12, 0005);
        Donovan.print();
        Staff Don = new Staff("Don", Role.Staff, Gender.MALE, 12, 0005);
        Don.print();
        Admin D = new Admin("Don", Role.Staff, Gender.MALE, 12, 0000); /* branch 0 means admin */
        D.print();
   

   
   }

    
}