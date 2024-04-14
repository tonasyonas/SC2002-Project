package FOMS.FOMS_entity;


import FOMS.branch_manager.Restaurant;
import FOMS.branch_manager.Branch;

public class Admin extends Staff{
    public Admin(String name, String loginID, String gender, int age,
                       String salt, String hashedPassword, boolean needsPasswordReset) {
        super(name, loginID, gender, age, salt, hashedPassword, needsPasswordReset);
    }


// //   @Override
//   // public String getStaffOptions() {
//        return "asd\nasdasd\n" + super.getStaffActions();
//    }
}
