package jordan.primerproyectosprin.controladores;

import jordan.primerproyectosprin.modelo.Equipo;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@RestController
@RequestMapping("/equipos")
public class EquipoControlador {

    ArrayList<Equipo> equipos = new ArrayList<Equipo>(
            List.of(
                    new Equipo("Argentina","Conmebol" ),
                    new Equipo("España","UEFA" ),
                    new Equipo("Marruecos","CAF" ),
                    new Equipo("Uruguay","Conmmebol" ),
                    new Equipo("Croacia","UEFA" )
            )

    );
    @GetMapping
    public List<Equipo>listarEquipos(){
        return equipos;
    } // fin ver equipos
    @GetMapping("/{nombre}")
    public ResponseEntity<Equipo> recuperarConNombre(@PathVariable String nombre){
        for (Equipo e: equipos){
            if (e.getNombre().equalsIgnoreCase(nombre)){
                return ResponseEntity.ok(e); //
            }
        }
        return ResponseEntity.notFound().build();
    } // fin buscar equipo por nombre
    @DeleteMapping("/{nombre}")
    public ResponseEntity eliminarEquipo(@PathVariable String nombre){
        Optional<Equipo> optEquipo = equipos.stream().filter(equipo -> equipo.getNombre().equalsIgnoreCase(nombre)).findFirst();
    if (optEquipo.isPresent()){
        equipos.remove(optEquipo.get());
    }else{
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();

    } // fin eliminar equipo

    @PostMapping
    public ResponseEntity crearEquipo(@RequestBody Equipo equipo){
        equipos.add(equipo);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{nombre}")

        return ResponseEntity.created(location).build();
    } // fin crear equipo

    @PutMapping("/{nombre}" )
    public ResponseEntity modificarEquipo(@RequestBody Equipo e){
        for(Equipo equipo : equipos){
            if (equipo.getNombre().equalsIgnoreCase(e.getNombre())){
                equipo.setConf(e.getConf());
                equipo.setRank(e.getRank());
                return ResponseEntity.ok(equipo);
            }
        }
        return ResponseEntity.notFound().build();
    } // fin modificar equipo
    @GetMapping("/{equipoA}/{equipoB}")
    public String jugar(@PathVariable String equipoA,@PathVariable String equipoB){
        Random r = new Random();
        int golesA = r.nextInt(10);
        int golesB = r.nextInt(5);
        return "Equipo " + equipoA + " (" + golesA + ") equipo " + equipoB + " (" + golesB + ")";
    }

}
