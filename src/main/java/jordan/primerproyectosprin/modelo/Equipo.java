package jordan.primerproyectosprin.modelo;

import lombok.Data;
import lombok.NonNull;

@Data
public class Equipo {
    @NonNull
    private String nombre;
    @NonNull
    private String conf;
    private String rank;
}
