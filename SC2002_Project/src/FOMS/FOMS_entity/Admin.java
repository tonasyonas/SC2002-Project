public class Admin extends Staff {

    // Constructor for Manager class
    public Admin(String staffName, Gender staffGender, int staffAge,  String branchName) {
        // Call the superclass constructor using super()
        super(staffName, staffGender, staffAge,  branchName);
        setStaffRole(Role.A);

    }

    public static void main (String[] args){
        BranchManager Donovan = new BranchManager("Don", Gender.M, 11, "NTU");
        Donovan.print();
        Staff Don = new Staff("Don", Gender.M, 12, "NTU");
        Don.print();
        Admin D = new Admin("Don", Gender.M, 12, "NTU"); /* branch 0 means admin */
        D.print();
   
   }

    
}