package punto2.model.subject;

import java.io.IOException;

public interface File {
    String readFile() throws IOException;

    boolean nombreComienzaCon(String prefijo);
}
