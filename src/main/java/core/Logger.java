package core;

import java.util.ArrayList;
import java.util.List;

class Logger {

    private Logger() {
    }

    private List<String> listCollection = getFillDate();

    private List<String> getFillDate() {

        if (listCollection == null) {
            return new ArrayList<>();
        } else {
            return listCollection;
        }
    }
}
