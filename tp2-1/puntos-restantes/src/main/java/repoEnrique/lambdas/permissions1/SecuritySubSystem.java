package oop2Enrique.lambdas.permissions1;

public class SecuritySubSystem {
    public boolean checkPermission(String userId) {
        if (userId.equals("1")) {
            return true;
        }
        return false;
    }
}
