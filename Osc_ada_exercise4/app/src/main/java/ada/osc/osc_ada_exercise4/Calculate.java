package ada.osc.osc_ada_exercise4;

import java.text.DecimalFormat;

public class Calculate {
    public static boolean validInput(float weight, float height){
        if(weight > 350 || weight < 15 || height > 2.5 || height < 0.5)
            return false;
        return true;
    }

    public static float calculate(float weight, float height) {
        return weight/(height*height);
    }
}
