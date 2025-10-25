package api.debt.book.app.util;


import api.debt.book.app.dto.AppResponse;

public class AppResponseUtil {

    public static AppResponse<String> chek(boolean b) {
        if (b){
            return new AppResponse<>("Success");
        } else {
            return new AppResponse<>("Filed");
        }

    }
}
