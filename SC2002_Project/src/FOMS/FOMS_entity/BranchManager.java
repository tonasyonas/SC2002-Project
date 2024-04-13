public class BranchManager extends Staff {

    // Constructor for Manager class
    public BranchManager(String staffName, Gender staffGender, int staffAge,  String branchName) {
        // Call the superclass constructor using super()
        super(staffName, staffGender, staffAge,  branchName);
        setStaffRole(Role.M);
        

    }

    public static void main (String[] args){
        BranchManager Donovan = new BranchManager("Don", Gender.M, 12, "NTU");
        Donovan.print();
        Staff Don = new Staff("Don", Gender.M, 12, "NTU");
        Don.print();
   

   
   }

    
}
