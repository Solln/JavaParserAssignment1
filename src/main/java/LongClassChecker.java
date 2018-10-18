import POJOS.ClassInfo;

import java.util.List;

public class LongClassChecker {

    public void LongClass(List<ClassInfo> classes) {
        // Counting empty space or class/method headers
        for (ClassInfo ci : classes) {
            if (ci.getLength() > 100)
                ci.setTooLong(true);
        }
    }

}
