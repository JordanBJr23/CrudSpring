package jordan.primerproyectosprin.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController // se expone mediante el protocolo http
public class HolaMundoController {
    @GetMapping("/holaMundo/{nombre}")
    public String saludo(@PathVariable String nombre){
        return "Hola " + nombre;
    }
    @GetMapping("/suma/{a}/{b}")
    public int suma(@PathVariable int a , @PathVariable int b){
        return a + b;
    }
}
